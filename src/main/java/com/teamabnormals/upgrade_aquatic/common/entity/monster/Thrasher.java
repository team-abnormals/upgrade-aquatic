package com.teamabnormals.upgrade_aquatic.common.entity.monster;

import com.teamabnormals.blueprint.core.endimator.Endimatable;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.endimator.TimedEndimation;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.thrasher.*;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Lionfish;
import com.teamabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.teamabnormals.upgrade_aquatic.core.other.UAEntityTypeTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UAPlayableEndimations;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class Thrasher extends Monster implements Endimatable {
	public static final Predicate<Entity> ENEMY_MATCHER = (entity) -> {
		if (entity == null) {
			return false;
		}
		if (entity instanceof Player && !(((Player) entity).isCreative() || entity.isSpectator())) {
			return entity.isInWater();
		}
		return entity.getType().is(UAEntityTypeTags.THRASHER_SONAR_TARGETS) && entity.isInWater();
	};
	private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_ID = UUID.fromString("3158fbca-89d7-4c15-b1ee-448cefd023b7");
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = (new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_ID, "Knockback Resistance", 4.0D, AttributeModifier.Operation.MULTIPLY_BASE));
	private static final EntityDataAccessor<Boolean> MOVING = SynchedEntityData.defineId(Thrasher.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> WATER_TIME = SynchedEntityData.defineId(Thrasher.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> STUN_TIME = SynchedEntityData.defineId(Thrasher.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> HITS_TILL_STUN = SynchedEntityData.defineId(Thrasher.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Optional<BlockPos>> POSSIBLE_DETECTION_POINT = SynchedEntityData.defineId(Thrasher.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
	private static final EntityDataAccessor<EntityDimensions> CAUGHT_SIZE = SynchedEntityData.defineId(Thrasher.class, UADataSerializers.ENTITY_SIZE);
	private static final EntityDimensions DEFAULT_SIZE = EntityDimensions.fixed(1.6F, 0.9F);
	public final TimedEndimation stunAnimation = new TimedEndimation(10, 10);
	protected int ticksSinceLastSonarFire;
	protected float prevTailAnimation;
	protected float tailAnimation;
	protected float tailSpeed;
	protected float prevFinAnimation;
	protected float finAnimation;
	protected float finSpeed;

	public Thrasher(EntityType<? extends Thrasher> type, Level world) {
		super(type, world);
		this.xpReward = 30;
		this.moveControl = new Thrasher.ThrasherMoveController(this);
		this.lookControl = new ThrasherLookController(this);
		this.tailAnimation = this.random.nextFloat();
		this.prevTailAnimation = this.tailAnimation;
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.55D)
				.add(Attributes.FOLLOW_RANGE, 32.0D)
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1.25D)
				.add(Attributes.ARMOR, 8.0D);
	}

	public static boolean thrasherCondition(EntityType<? extends PathfinderMob> entityType, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, Random random) {
		if (((Level) world).dimension() != Level.OVERWORLD) return false;
		return pos.getY() <= 30 && (((Level) world).isNight() || random.nextFloat() < 0.75F);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new ThrasherThrashGoal(this));
		this.goalSelector.addGoal(2, new ThrasherFireSonarGoal(this));
		this.goalSelector.addGoal(3, new ThrasherFindDetectionPointGoal(this));
		this.goalSelector.addGoal(4, new ThrasherGrabGoal(this, 2.5F, true));
		this.goalSelector.addGoal(5, new ThrasherRandomSwimGoal(this, 1.1D, 15));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MOVING, false);
		this.entityData.define(WATER_TIME, 2500);
		this.entityData.define(STUN_TIME, 0);
		this.entityData.define(HITS_TILL_STUN, 0);
		this.entityData.define(POSSIBLE_DETECTION_POINT, Optional.empty());
		this.entityData.define(CAUGHT_SIZE, this.getDefaultSize());
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.setAirSupply(this.getMaxAirSupply());
		if (reason == MobSpawnType.NATURAL && worldIn.getBiome(this.blockPosition()).value().getRegistryName().equals(Biomes.DEEP_FROZEN_OCEAN.location())) {
			Random rand = new Random();
			if (rand.nextFloat() < 0.25F) {
				GreatThrasher greatThrasher = UAEntityTypes.GREAT_THRASHER.get().create(this.level);
				if (greatThrasher != null) {
					greatThrasher.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
					this.level.addFreshEntity(greatThrasher);
					this.discard();
				}
			}
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (HITS_TILL_STUN.equals(key) && this.tickCount > 10) {
			if (this.getHitsLeftTillStun() == 0) {
				this.setStunned(((this.getRandom().nextInt(2) + 2) * 20) + this.getRandom().nextInt(10));
			}
		} else if (CAUGHT_SIZE.equals(key)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(key);
	}

	@Override
	public void positionRider(Entity passenger) {
		if (passenger instanceof LivingEntity) {
			float distance = this.getMountDistance();

			double dx = Math.cos((this.getYRot() + 90) * Math.PI / 180.0D) * distance;
			double dy = -Math.sin(this.getXRot() * (Math.PI / 180.0D));
			double dz = Math.sin((this.getYRot() + 90) * Math.PI / 180.0D) * distance;

			Vec3 riderPos = new Vec3(this.getX() + dx, this.getY(), this.getZ() + dz);

			double offset = passenger instanceof Player ? this.getPassengersRidingOffset() - 0.2D : this.getPassengersRidingOffset() - 0.5F;

			passenger.setPos(riderPos.x, this.getY() + dy + offset, riderPos.z);
		} else {
			super.positionRider(passenger);
		}
	}

	@Override
	protected void addPassenger(Entity passenger) {
		super.addPassenger(passenger);
		if (!this.level.isClientSide && passenger instanceof LivingEntity && passenger.getVehicle() == this && this.getPassengers().indexOf(passenger) == 0) {
			EntityDimensions defaultSize = this.getDefaultSize();
			this.setCaughtSize(EntityDimensions.fixed(defaultSize.width + passenger.getDimensions(passenger.getPose()).width, defaultSize.height));
		}
	}

	@Override
	protected void removePassenger(Entity passenger) {
		super.removePassenger(passenger);
		if (!this.level.isClientSide) {
			if (!this.getPassengers().isEmpty()) {
				Entity indexZeroPassenger = this.getPassengers().get(0);
				EntityDimensions defaultSize = this.getDefaultSize();
				if (indexZeroPassenger instanceof LivingEntity && passenger.getVehicle() == this) {
					this.setCaughtSize(EntityDimensions.fixed(defaultSize.width + passenger.getDimensions(passenger.getPose()).width, defaultSize.height));
				} else {
					this.setCaughtSize(defaultSize);
				}
			} else {
				this.setCaughtSize(this.getDefaultSize());
			}
		}
	}

	public float getMountDistance() {
		return 1.2F;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 0.5F;
	}

	@Override
	public boolean shouldRiderFaceForward(Player player) {
		return true;
	}

	@Override
	public boolean canRiderInteract() {
		return true;
	}

	@Override
	public boolean shouldRiderSit() {
		return false;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		if (!this.getPassengers().isEmpty()) {
			return this.getCaughtSize();
		}
		return super.getDimensions(pose);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader worldIn) {
		return worldIn.isUnobstructed(this);
	}

	@Override
	public void onEndimationStart(PlayableEndimation endimation, PlayableEndimation oldEndimation) {
		if (endimation == UAPlayableEndimations.THRASHER_THRASH) {
			AttributeInstance knockbackResistance = this.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
			if (!knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
				knockbackResistance.addTransientModifier(KNOCKBACK_RESISTANCE_MODIFIER);
			}
		}
	}

	@Override
	public void onEndimationEnd(PlayableEndimation endimation, PlayableEndimation newEndimation) {
		if (endimation == UAPlayableEndimations.THRASHER_THRASH) {
			AttributeInstance knockbackResistance = this.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
			if (knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
				knockbackResistance.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER);
			}
		}
	}

	@Override
	public void onInsideBubbleColumn(boolean downwards) {
	}

	@Override
	public void onAboveBubbleCol(boolean downwards) {
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		Entity entitySource = source.getEntity();
		if (entitySource instanceof LivingEntity) {
			Vec3 difference = new Vec3(entitySource.getX() - this.getX(), entitySource.getY() - this.getY(), entitySource.getZ() - this.getZ());
			if (difference.length() <= 8) {
				if (entitySource.isInWater()) {
					if (entitySource instanceof Player && !((Player) entitySource).isCreative() && !entitySource.isSpectator()) {
						this.setTarget((LivingEntity) entitySource);
					} else if (!(entitySource instanceof Player)) {
						this.setTarget((LivingEntity) entitySource);
					}
				}
				if (this.getHitsLeftTillStun() > 0) {
					int difficultyDividend = switch (this.level.getDifficulty()) {
						case EASY, PEACEFUL -> 10;
						case NORMAL -> 12;
						case HARD -> 16;
					};
					int chance = amount >= this.getStunDamageThreshold() ? 1 : difficultyDividend / (int) Math.max(1, amount);
					if (this.getRandom().nextInt(chance) == 0) {
						this.setHitsTillStun(this.getHitsLeftTillStun() - 1);
					}
				}
			} else {
				if (entitySource instanceof Player && !((Player) entitySource).isCreative() && !entitySource.isSpectator()) {
					this.setPossibleDetectionPoint(entitySource.blockPosition().offset(this.getRandom().nextInt(2), this.getRandom().nextInt(2), this.getRandom().nextInt(2)));
				} else if (!(entitySource instanceof Player)) {
					this.setPossibleDetectionPoint(entitySource.blockPosition().offset(this.getRandom().nextInt(2), this.getRandom().nextInt(2), this.getRandom().nextInt(2)));
				}
			}
		}
		if (!this.level.isClientSide() && this.isNoEndimationPlaying()) NetworkUtil.setPlayingAnimation(this, UAPlayableEndimations.THRASHER_HURT);
		return super.hurt(source, amount);
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new WaterBoundPathNavigation(this, worldIn);
	}

	@Override
	public int getMaxAirSupply() {
		return 4950;
	}

	@Override
	protected int increaseAirSupply(int currentAir) {
		return this.getMaxAirSupply();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.65F;
	}

	@Override
	public int getMaxHeadXRot() {
		return 1;
	}

	@Override
	public int getMaxHeadYRot() {
		return 1;
	}

	@Override
	public boolean rideableUnderWater() {
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	public float getTailAnimation(float ptc) {
		return Mth.lerp(ptc, this.prevTailAnimation, this.tailAnimation);
	}

	@OnlyIn(Dist.CLIENT)
	public float getFinAnimation(float ptc) {
		return Mth.lerp(ptc, this.prevFinAnimation, this.finAnimation);
	}

	public void travel(Vec3 vec3) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.1F, vec3);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (!this.isMoving() && this.getTarget() == null) {
				double ySpeed = !((ThrasherLookController) this.getLookControl()).isTurningForSonar() ? -0.005D : -0.0025D;
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ySpeed, 0.0D));
			}
		} else {
			super.travel(vec3);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getTarget() != null && !this.getTarget().isAlive() && this.ticksSinceLastSonarFire >= 55 && this.getRandom().nextFloat() < 0.05F) {
			this.setTarget(null);
		}

		if (!this.isNoAi()) {
			if (this.isEndimationPlaying(UAPlayableEndimations.THRASHER_SONAR_FIRE)) {
				this.ticksSinceLastSonarFire = 0;
			} else {
				this.ticksSinceLastSonarFire++;
			}
			if (this.isInWaterRainOrBubble()) {
				this.setWaterTime(2400);
			} else {
				this.setWaterTime(this.getWaterTime() - 1);
				if (this.getWaterTime() <= 0) {
					this.hurt(DamageSource.DRY_OUT, 1.0F);
				}

				if (!this.isInWater() && !this.isStunned() && this.onGround) {
					this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F, 0.5D, (this.random.nextFloat() * 2.0F - 1.0F) * 0.2F));
					this.setYRot(this.random.nextFloat() * 360.0F);
					this.setXRot(this.random.nextFloat() * -50.0F);
					this.onGround = false;
					this.hasImpulse = true;
					this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
				}
			}
			if (this.level.isClientSide()) {
				if (!this.getPassengers().isEmpty() && this.isEndimationPlaying(UAPlayableEndimations.THRASHER_THRASH) && this.getAnimationTick() % 2 == 0 && this.getAnimationTick() > 5) {
					Entity passenger = this.getPassengers().get(0);
					for (int i = 0; i < 3; ++i) {
						if (passenger.isEyeInFluid(FluidTags.WATER)) {
							this.level.addParticle(ParticleTypes.BUBBLE, passenger.getX() + (this.getRandom().nextDouble() - 0.5D) * (double) passenger.getBbWidth(), passenger.getY(), passenger.getZ() + (this.getRandom().nextDouble() - 0.5D) * (double) passenger.getBbWidth(), (this.getRandom().nextDouble() - 0.5D) * 2.0D, -this.getRandom().nextDouble(), (this.getRandom().nextDouble() - 0.5D) * 2.0D);
						}
					}
				}
			}
			if (!this.level.isClientSide && this.isStunned()) {
				if (!this.getPassengers().isEmpty()) {
					this.ejectPassengers();
				}
				this.setStunned(this.getStunTime() - 1);
			}
		}
	}

	@Override
	public void aiStep() {
		if (this.isAlive()) {
			if (this.level.isClientSide) {
				this.prevTailAnimation = this.tailAnimation;
				this.prevFinAnimation = this.finAnimation;

				if (!this.isInWater() || (this.isEndimationPlaying(UAPlayableEndimations.THRASHER_THRASH) || this.isEndimationPlaying(UAPlayableEndimations.THRASHER_SONAR_FIRE))) {
					this.tailSpeed = 1.1F;
					this.finSpeed = 0.875F;
				} else if (this.isMoving()) {
					if (this.tailSpeed < 0.5F) {
						this.tailSpeed = 2.2F;
					} else {
						this.tailSpeed += (0.25F - this.tailSpeed) * 0.1F;
					}

					if (this.finSpeed < 0.5F) {
						this.finSpeed = 0.875F;
					} else {
						this.finSpeed += (0.045F - this.tailSpeed) * 0.05F;
					}
				} else {
					this.tailSpeed += (0.125F - this.tailSpeed) * 0.1F;
					this.finSpeed += (0.01125F - this.finSpeed) * 0.05F;
				}

				var stunAnimation = this.stunAnimation;
				if (this.isStunned()) {
					if (stunAnimation.getTick() >= 10) {
						stunAnimation.setDecrementing(true);
					} else if (stunAnimation.isDecrementing() && stunAnimation.getTick() <= 0) {
						stunAnimation.setDecrementing(false);
					}
				} else {
					stunAnimation.setDecrementing(false);
				}
				stunAnimation.tick();

				this.tailAnimation += this.tailSpeed;
				this.finAnimation += this.finSpeed;
			}

			if (!this.isStunned()) {
				List<LivingEntity> nearbyEntities = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.5F), Thrasher.ENEMY_MATCHER);
				for (LivingEntity entities : nearbyEntities) {
					if (this.getTarget() == null) {
						this.setTarget(entities);
					}
				}
			}

			if (this.isMoving() && this.isInWater()) {
				Vec3 vec3d1 = this.getViewVector(0.0F);

				for (int i = 0; i < 2; ++i) {
					this.level.addParticle(ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.x * 1.5D, this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - vec3d1.y * 1.5D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.z * 1.5D, 0.0D, 0.0D, 0.0D);
				}
			}
		}
		super.aiStep();
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.THRASHER_SPAWN_EGG.get());
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return this.isInWater() ? UASoundEvents.ENTITY_THRASHER_HURT.get() : UASoundEvents.ENTITY_THRASHER_HURT_LAND.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isInWater() ? UASoundEvents.ENTITY_THRASHER_DEATH.get() : UASoundEvents.ENTITY_THRASHER_DEATH_LAND.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (this.isEndimationPlaying(UAPlayableEndimations.THRASHER_THRASH)) return null;
		return this.isInWater() ? UASoundEvents.ENTITY_THRASHER_AMBIENT.get() : UASoundEvents.ENTITY_THRASHER_AMBIENT_LAND.get();
	}

	protected SoundEvent getFlopSound() {
		return UASoundEvents.ENTITY_THRASHER_FLOP.get();
	}

	public SoundEvent getSonarFireSound() {
		return UASoundEvents.ENTITY_THRASHER_SONAR_FIRE.get();
	}

	public SoundEvent getThrashingSound() {
		return UASoundEvents.ENTITY_THRASHER_THRASH.get();
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.DOLPHIN_SWIM;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
	}

	@Override
	public int getAmbientSoundInterval() {
		return 100;
	}

	protected double getStunDamageThreshold() {
		return 6.0F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	public boolean isMoving() {
		return this.entityData.get(MOVING);
	}

	private void setMoving(boolean moving) {
		this.entityData.set(MOVING, moving);
	}

	public int getWaterTime() {
		return this.entityData.get(WATER_TIME);
	}

	public void setWaterTime(int ticks) {
		this.entityData.set(WATER_TIME, ticks);
	}

	public int getStunTime() {
		return this.entityData.get(STUN_TIME);
	}

	public int getHitsLeftTillStun() {
		return this.entityData.get(HITS_TILL_STUN);
	}

	public void setHitsTillStun(int hits) {
		this.entityData.set(HITS_TILL_STUN, hits);
	}

	@Nullable
	public BlockPos getPossibleDetectionPoint() {
		return this.getEntityData().get(POSSIBLE_DETECTION_POINT).orElse(null);
	}

	public void setPossibleDetectionPoint(@Nullable BlockPos detectionPoint) {
		this.getEntityData().set(POSSIBLE_DETECTION_POINT, Optional.ofNullable(detectionPoint));
	}

	public EntityDimensions getCaughtSize() {
		return this.entityData.get(CAUGHT_SIZE);
	}

	public void setCaughtSize(EntityDimensions caughtSize) {
		this.entityData.set(CAUGHT_SIZE, caughtSize);
	}

	public int getTicksSinceLastSonarFire() {
		return this.ticksSinceLastSonarFire;
	}

	public boolean isStunned() {
		return this.getStunTime() > 0;
	}

	private void setStunned(int ticks) {
		this.entityData.set(STUN_TIME, ticks);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsMoving", this.isMoving());
		compound.putInt("WaterTicks", this.getWaterTime());
		compound.putInt("StunnedTicks", this.getStunTime());
		compound.putInt("TicksSinceLastSonarFire", this.getTicksSinceLastSonarFire());

		if (this.getPossibleDetectionPoint() != null) {
			compound.put("DetectionPoint", NbtUtils.writeBlockPos(this.getPossibleDetectionPoint()));
		}
	}

	protected EntityDimensions getDefaultSize() {
		return DEFAULT_SIZE;
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setMoving(compound.getBoolean("IsMoving"));
		this.setWaterTime(compound.getInt("WaterTicks"));
		this.setStunned(compound.getInt("StunnedTicks"));
		this.ticksSinceLastSonarFire = compound.getInt("TicksSinceLastSonarFire");

		if (this.getPossibleDetectionPoint() != null) {
			this.setPossibleDetectionPoint(NbtUtils.readBlockPos(compound.getCompound("DetectionPoint")));
		}
	}

	static class ThrasherMoveController extends MoveControl {
		private final Thrasher thrasher;

		public ThrasherMoveController(Thrasher thrasher) {
			super(thrasher);
			this.thrasher = thrasher;
		}

		public void tick() {
			if (this.operation == MoveControl.Operation.MOVE_TO && !this.thrasher.getNavigation().isDone() && this.thrasher.getStunTime() <= 0) {
				Vec3 vec3d = new Vec3(this.wantedX - this.thrasher.getX(), this.wantedY - this.thrasher.getY(), this.wantedZ - this.thrasher.getZ());
				double d0 = vec3d.length();
				double d1 = vec3d.y / d0;
				float f = (float) (Mth.atan2(vec3d.z, vec3d.x) * (double) (180F / (float) Math.PI)) - 90F;

				this.thrasher.setYRot(this.rotlerp(this.thrasher.getYRot(), f, 10.0F));
				this.thrasher.yBodyRot = this.thrasher.getYRot();
				this.thrasher.yHeadRot = this.thrasher.getYRot();

				float f1 = (float) (this.speedModifier * this.thrasher.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				float f2 = Mth.lerp(0.125F, this.thrasher.getSpeed(), f1);

				this.thrasher.setSpeed(f2);

				double d2 = Math.sin((double) (this.thrasher.tickCount + this.thrasher.getId()) * 0.5D) * 0.05D;
				double d3 = Math.cos(this.thrasher.getYRot() * ((float) Math.PI / 180F));
				double d4 = Math.sin(this.thrasher.getYRot() * ((float) Math.PI / 180F));
				double d5 = Math.sin((double) (this.thrasher.tickCount + this.thrasher.getId()) * 0.75D) * 0.05D;

				if (this.thrasher.isInWater()) {
					float f3 = -((float) (Mth.atan2(vec3d.y, Mth.sqrt((float) (vec3d.x * vec3d.x + vec3d.z * vec3d.z))) * (double) (180F / (float) Math.PI)));
					f3 = Mth.clamp(Mth.wrapDegrees(f3), -85.0F, 85.0F);
					this.thrasher.setXRot(this.rotlerp(this.thrasher.getXRot(), f3, 5.0F));
				}

				this.thrasher.setDeltaMovement(this.thrasher.getDeltaMovement().add(d2 * d3, d5 * (d4 + d3) * 0.25D + (double) f2 * d1 * 0.1D, d2 * d4));

				this.thrasher.setMoving(true);
			} else {
				this.thrasher.setSpeed(0F);
				this.thrasher.setMoving(false);
			}
		}
	}

	public static class ThrasherLookController extends LookControl {
		private boolean isTurningForSonar;

		public ThrasherLookController(Thrasher thrasher) {
			super(thrasher);
		}

		public void tick() {
			if (this.lookAtCooldown > 0) {
				this.lookAtCooldown--;
				if (this.isTurningForSonar) {
					this.getYRotD().ifPresent(yRot -> this.mob.setYRot(this.rotateTowards(this.mob.getYRot(), yRot, this.yMaxRotSpeed)));
				} else {
					this.getYRotD().ifPresent(yRot -> this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, yRot, this.yMaxRotSpeed));
				}
				this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), this.getXRotD().get(), this.xMaxRotAngle));
			} else {
				if (this.mob.getNavigation().isDone()) {
					this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), 0.0F, 5.0F));
				}
				if (this.isTurningForSonar) {
					this.mob.setYRot(this.rotateTowards(this.mob.getYRot(), this.mob.yBodyRot, this.yMaxRotSpeed));
				} else {
					this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
				}
			}

			float wrappedDegrees = Mth.wrapDegrees(this.mob.yHeadRot - this.mob.yBodyRot);
			float angleLimit = this.mob.getPassengers().isEmpty() ? 10.0F : 5.0F;
			if (wrappedDegrees < -angleLimit) {
				this.mob.yBodyRot -= 4.0F;
			} else if (wrappedDegrees > angleLimit) {
				this.mob.yBodyRot += 4.0F;
			}
		}

		public boolean isTurningForSonar() {
			return this.isTurningForSonar;
		}

		public void setTurningForSonar(boolean isTurning) {
			this.isTurningForSonar = isTurning;
		}
	}
}