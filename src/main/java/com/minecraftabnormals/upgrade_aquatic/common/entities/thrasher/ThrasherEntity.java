package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher;

import com.minecraftabnormals.abnormals_core.core.endimator.ControlledEndimation;
import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.upgrade_aquatic.api.EndimatedMonsterEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.LionfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai.*;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UASounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class ThrasherEntity extends EndimatedMonsterEntity {
	public static final Predicate<Entity> ENEMY_MATCHER = (entity) -> {
		if (entity == null) {
			return false;
		}
		if (entity instanceof PlayerEntity && !(((PlayerEntity) entity).isCreative() || entity.isSpectator())) {
			return entity.isInWater();
		}
		return (entity instanceof WaterMobEntity && !(entity instanceof IMob) && !(entity instanceof ThrasherEntity) && !(entity instanceof PufferfishEntity) && !(entity instanceof SquidEntity) && !(entity instanceof LionfishEntity)) && entity.isInWater();
	};
	public static final Endimation SNAP_AT_PRAY_ANIMATION = new Endimation(10);
	public static final Endimation HURT_ANIMATION = new Endimation(10);
	public static final Endimation THRASH_ANIMATION = new Endimation(55);
	public static final Endimation SONAR_FIRE_ANIMATION = new Endimation(30);
	private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_ID = UUID.fromString("3158fbca-89d7-4c15-b1ee-448cefd023b7");
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = (new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_ID, "Knockback Resistance", 4.0D, AttributeModifier.Operation.MULTIPLY_BASE));
	private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(ThrasherEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> WATER_TIME = EntityDataManager.createKey(ThrasherEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> STUN_TIME = EntityDataManager.createKey(ThrasherEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> HITS_TILL_STUN = EntityDataManager.createKey(ThrasherEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Optional<BlockPos>> POSSIBLE_DETECTION_POINT = EntityDataManager.createKey(ThrasherEntity.class, DataSerializers.OPTIONAL_BLOCK_POS);
	private static final DataParameter<EntitySize> CAUGHT_SIZE = EntityDataManager.createKey(ThrasherEntity.class, UADataSerializers.ENTITY_SIZE);
	private static final EntitySize DEFAULT_SIZE = EntitySize.fixed(1.6F, 0.9F);
	public final ControlledEndimation STUNNED_ANIMATION = new ControlledEndimation(10, 10);
	protected int ticksSinceLastSonarFire;
	protected float prevTailAnimation;
	protected float tailAnimation;
	protected float tailSpeed;
	protected float prevFinAnimation;
	protected float finAnimation;
	protected float finSpeed;

	public ThrasherEntity(EntityType<? extends ThrasherEntity> type, World world) {
		super(type, world);
		this.experienceValue = 30;
		this.moveController = new ThrasherEntity.ThrasherMoveController(this);
		this.lookController = new ThrasherLookController(this);
		this.tailAnimation = this.rand.nextFloat();
		this.prevTailAnimation = this.tailAnimation;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.55D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.25D)
				.createMutableAttribute(Attributes.ARMOR, 8.0D);
	}

	public static boolean thrasherCondition(EntityType<? extends CreatureEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if (((World) world).getDimensionKey() != World.OVERWORLD) return false;
		return pos.getY() <= 30 && (((World) world).isNightTime() || random.nextFloat() < 0.75F);
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
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOVING, false);
		this.dataManager.register(WATER_TIME, 2500);
		this.dataManager.register(STUN_TIME, 0);
		this.dataManager.register(HITS_TILL_STUN, 0);
		this.dataManager.register(POSSIBLE_DETECTION_POINT, Optional.empty());
		this.dataManager.register(CAUGHT_SIZE, this.getDefaultSize());
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	public Endimation[] getEndimations() {
		return new Endimation[]{
				SNAP_AT_PRAY_ANIMATION,
				HURT_ANIMATION,
				THRASH_ANIMATION,
				SONAR_FIRE_ANIMATION
		};
	}

	@Override
	public Endimation getHurtAnimation() {
		return HURT_ANIMATION;
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setAir(this.getMaxAir());
		if (reason == SpawnReason.NATURAL && worldIn.getBiome(this.getPosition()).getRegistryName().equals(Biomes.DEEP_FROZEN_OCEAN.getLocation())) {
			Random rand = new Random();
			if (rand.nextFloat() < 0.25F) {
				GreatThrasherEntity greatThrasher = UAEntities.GREAT_THRASHER.get().create(this.world);
				greatThrasher.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
				this.world.addEntity(greatThrasher);
				this.remove();
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (HITS_TILL_STUN.equals(key) && this.ticksExisted > 10) {
			if (this.getHitsLeftTillStun() == 0) {
				this.setStunned(((this.getRNG().nextInt(2) + 2) * 20) + this.getRNG().nextInt(10));
			}
		} else if (CAUGHT_SIZE.equals(key)) {
			this.recalculateSize();
		}
		super.notifyDataManagerChange(key);
	}

	@Override
	public void updatePassenger(Entity passenger) {
		if (passenger instanceof LivingEntity) {
			float distance = this.getMountDistance();

			double dx = Math.cos((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			double dy = -Math.sin(this.rotationPitch * (Math.PI / 180.0D));
			double dz = Math.sin((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;

			Vector3d riderPos = new Vector3d(this.getPosX() + dx, this.getPosY(), this.getPosZ() + dz);

			double offset = passenger instanceof PlayerEntity ? this.getMountedYOffset() - 0.2D : this.getMountedYOffset() - 0.5F;

			passenger.setPosition(riderPos.x, this.getPosY() + dy + offset, riderPos.z);
		} else {
			super.updatePassenger(passenger);
		}
	}

	@Override
	protected void addPassenger(Entity passenger) {
		super.addPassenger(passenger);
		if (!this.world.isRemote && passenger instanceof LivingEntity && passenger.getRidingEntity() == this && this.getPassengers().indexOf(passenger) == 0) {
			EntitySize defaultSize = this.getDefaultSize();
			this.setCaughtSize(EntitySize.fixed(defaultSize.width + passenger.getSize(passenger.getPose()).width, defaultSize.height));
		}
	}

	@Override
	protected void removePassenger(Entity passenger) {
		super.removePassenger(passenger);
		if (!this.world.isRemote) {
			if (!this.getPassengers().isEmpty()) {
				Entity indexZeroPassenger = this.getPassengers().get(0);
				EntitySize defaultSize = this.getDefaultSize();
				if (indexZeroPassenger instanceof LivingEntity && passenger.getRidingEntity() == this) {
					this.setCaughtSize(EntitySize.fixed(defaultSize.width + passenger.getSize(passenger.getPose()).width, defaultSize.height));
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
	public double getMountedYOffset() {
		return 0.5F;
	}

	@Override
	public boolean shouldRiderFaceForward(PlayerEntity player) {
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
	public EntitySize getSize(Pose pose) {
		if (!this.getPassengers().isEmpty()) {
			return this.getCaughtSize();
		}
		return super.getSize(pose);
	}

	@Override
	public boolean isNotColliding(IWorldReader worldIn) {
		return worldIn.checkNoEntityCollision(this);
	}

	@Override
	public void onEndimationStart(Endimation animationStarted) {
		if (animationStarted == THRASH_ANIMATION) {
			ModifiableAttributeInstance knockbackResistance = this.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
			if (!knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
				knockbackResistance.applyNonPersistentModifier(KNOCKBACK_RESISTANCE_MODIFIER);
			}
		}
	}

	@Override
	public void onEndimationEnd(Endimation animationEnded) {
		if (animationEnded == THRASH_ANIMATION) {
			ModifiableAttributeInstance knockbackResistance = this.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
			if (knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
				knockbackResistance.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER);
			}
		}
	}

	@Override
	public void onEnterBubbleColumn(boolean downwards) {
	}

	@Override
	public void onEnterBubbleColumnWithAirAbove(boolean downwards) {
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entitySource = source.getTrueSource();
		if (entitySource instanceof LivingEntity) {
			Vector3d difference = new Vector3d(
					entitySource.getPosX() - this.getPosX(),
					entitySource.getPosY() - this.getPosY(),
					entitySource.getPosZ() - this.getPosZ()
			);
			if (difference.length() <= 8) {
				if (entitySource.isInWater()) {
					if (entitySource instanceof PlayerEntity && !((PlayerEntity) entitySource).isCreative() && !entitySource.isSpectator()) {
						this.setAttackTarget((LivingEntity) entitySource);
					} else if (!(entitySource instanceof PlayerEntity)) {
						this.setAttackTarget((LivingEntity) entitySource);
					}
				}
				if (this.getHitsLeftTillStun() > 0) {
					int difficultyDividend = 0;
					switch (this.world.getDifficulty()) {
						default:
						case EASY:
						case PEACEFUL:
							difficultyDividend = 10;
							break;
						case NORMAL:
							difficultyDividend = 12;
							break;
						case HARD:
							difficultyDividend = 16;
							break;
					}
					int chance = amount >= this.getStunDamageThreshold() ? 1 : difficultyDividend / (int) Math.max(1, amount);
					if (this.getRNG().nextInt(chance) == 0) {
						this.setHitsTillStun(this.getHitsLeftTillStun() - 1);
					}
				}
			} else {
				if (entitySource instanceof PlayerEntity && !((PlayerEntity) entitySource).isCreative() && !entitySource.isSpectator()) {
					this.setPossibleDetectionPoint(entitySource.getPosition().add(this.getRNG().nextInt(2), this.getRNG().nextInt(2), this.getRNG().nextInt(2)));
				} else if (!(entitySource instanceof PlayerEntity)) {
					this.setPossibleDetectionPoint(entitySource.getPosition().add(this.getRNG().nextInt(2), this.getRNG().nextInt(2), this.getRNG().nextInt(2)));
				}
			}
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	@Override
	public int getMaxAir() {
		return 4950;
	}

	@Override
	protected int determineNextAir(int currentAir) {
		return this.getMaxAir();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.65F;
	}

	@Override
	public int getVerticalFaceSpeed() {
		return 1;
	}

	@Override
	public int getHorizontalFaceSpeed() {
		return 1;
	}

	@Override
	public boolean canBeRiddenInWater() {
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	public float getTailAnimation(float ptc) {
		return MathHelper.lerp(ptc, this.prevTailAnimation, this.tailAnimation);
	}

	@OnlyIn(Dist.CLIENT)
	public float getFinAnimation(float ptc) {
		return MathHelper.lerp(ptc, this.prevFinAnimation, this.finAnimation);
	}

	public void travel(Vector3d p_213352_1_) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.1F, p_213352_1_);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (!this.isMoving() && this.getAttackTarget() == null) {
				double ySpeed = !((ThrasherLookController) this.getLookController()).isTurningForSonar() ? -0.005D : -0.0025D;
				this.setMotion(this.getMotion().add(0.0D, ySpeed, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getAttackTarget() != null && !this.getAttackTarget().isAlive() && this.ticksSinceLastSonarFire >= 55 && this.getRNG().nextFloat() < 0.05F) {
			this.setAttackTarget(null);
		}

		if (!this.isAIDisabled()) {
			if (this.isEndimationPlaying(SONAR_FIRE_ANIMATION)) {
				this.ticksSinceLastSonarFire = 0;
			} else {
				this.ticksSinceLastSonarFire++;
			}
			if (this.isInWaterRainOrBubbleColumn()) {
				this.setWaterTime(2400);
			} else {
				this.setWaterTime(this.getWaterTime() - 1);
				if (this.getWaterTime() <= 0) {
					this.attackEntityFrom(DamageSource.DRYOUT, 1.0F);
				}

				if (!this.isInWater() && !this.isStunned() && this.onGround) {
					this.setMotion(this.getMotion().add((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F, 0.5D, (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F));
					this.rotationYaw = this.rand.nextFloat() * 360.0F;
					this.rotationPitch = this.rand.nextFloat() * -50.0F;
					this.onGround = false;
					this.isAirBorne = true;
					this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
				}
			}
			if (this.isWorldRemote()) {
				if (!this.getPassengers().isEmpty() && this.isEndimationPlaying(THRASH_ANIMATION) && this.getAnimationTick() % 2 == 0 && this.getAnimationTick() > 5) {
					Entity passenger = this.getPassengers().get(0);
					for (int i = 0; i < 3; ++i) {
						if (passenger.areEyesInFluid(FluidTags.WATER)) {
							this.world.addParticle(ParticleTypes.BUBBLE, passenger.getPosX() + (this.getRNG().nextDouble() - 0.5D) * (double) passenger.getWidth(), passenger.getPosY(), passenger.getPosZ() + (this.getRNG().nextDouble() - 0.5D) * (double) passenger.getWidth(), (this.getRNG().nextDouble() - 0.5D) * 2.0D, -this.getRNG().nextDouble(), (this.getRNG().nextDouble() - 0.5D) * 2.0D);
						}
					}
				}
			}
			if (!this.world.isRemote && this.isStunned()) {
				if (!this.getPassengers().isEmpty()) {
					this.removePassengers();
				}
				this.setStunned(this.getStunTime() - 1);
			}
		}
	}

	@Override
	public void livingTick() {
		if (this.isAlive()) {
			if (this.world.isRemote) {
				this.prevTailAnimation = this.tailAnimation;
				this.prevFinAnimation = this.finAnimation;

				this.STUNNED_ANIMATION.update();

				if (!this.isInWater() || (this.isEndimationPlaying(THRASH_ANIMATION) || this.isEndimationPlaying(SONAR_FIRE_ANIMATION))) {
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

				if (this.isStunned()) {
					if (this.STUNNED_ANIMATION.getTick() >= 10) {
						this.STUNNED_ANIMATION.setDecrementing(true);
					} else {
						if (this.STUNNED_ANIMATION.isDecrementing() && this.STUNNED_ANIMATION.getTick() <= 0)
							this.STUNNED_ANIMATION.setDecrementing(false);
					}
				} else {
					this.STUNNED_ANIMATION.setDecrementing(false);
				}

				this.STUNNED_ANIMATION.tick();

				this.tailAnimation += this.tailSpeed;
				this.finAnimation += this.finSpeed;
			}

			if (!this.isStunned()) {
				List<LivingEntity> nearbyEntities = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(0.5F), ThrasherEntity.ENEMY_MATCHER);
				for (LivingEntity entities : nearbyEntities) {
					if (this.getAttackTarget() == null) {
						this.setAttackTarget(entities);
					}
				}
			}

			if (this.isMoving() && this.isInWater()) {
				Vector3d vec3d1 = this.getLook(0.0F);

				for (int i = 0; i < 2; ++i) {
					this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth() - vec3d1.x * 1.5D, this.getPosY() + this.rand.nextDouble() * (double) this.getHeight() - vec3d1.y * 1.5D, this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth() - vec3d1.z * 1.5D, 0.0D, 0.0D, 0.0D);
				}
			}
		}
		super.livingTick();
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.THRASHER_SPAWN_EGG.get());
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return this.isInWater() ? UASounds.ENTITY_THRASHER_HURT.get() : UASounds.ENTITY_THRASHER_HURT_LAND.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isInWater() ? UASounds.ENTITY_THRASHER_DEATH.get() : UASounds.ENTITY_THRASHER_DEATH_LAND.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (this.isEndimationPlaying(THRASH_ANIMATION)) {
			return null;
		}
		return this.isInWater() ? UASounds.ENTITY_THRASHER_AMBIENT.get() : UASounds.ENTITY_THRASHER_AMBIENT_LAND.get();
	}

	protected SoundEvent getFlopSound() {
		return UASounds.ENTITY_THRASHER_FLOP.get();
	}

	public SoundEvent getSonarFireSound() {
		return UASounds.ENTITY_THRASHER_SONAR_FIRE.get();
	}

	public SoundEvent getThrashingSound() {
		return UASounds.ENTITY_THRASHER_THRASH.get();
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_DOLPHIN_SWIM;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
	}

	@Override
	public int getTalkInterval() {
		return 100;
	}

	protected double getStunDamageThreshold() {
		return 6.0F;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public boolean isMoving() {
		return this.dataManager.get(MOVING);
	}

	private void setMoving(boolean moving) {
		this.dataManager.set(MOVING, moving);
	}

	public int getWaterTime() {
		return this.dataManager.get(WATER_TIME);
	}

	public void setWaterTime(int ticks) {
		this.dataManager.set(WATER_TIME, ticks);
	}

	public int getStunTime() {
		return this.dataManager.get(STUN_TIME);
	}

	public int getHitsLeftTillStun() {
		return this.dataManager.get(HITS_TILL_STUN);
	}

	public void setHitsTillStun(int hits) {
		this.dataManager.set(HITS_TILL_STUN, hits);
	}

	@Nullable
	public BlockPos getPossibleDetectionPoint() {
		return this.getDataManager().get(POSSIBLE_DETECTION_POINT).orElse(null);
	}

	public void setPossibleDetectionPoint(@Nullable BlockPos detectionPoint) {
		this.getDataManager().set(POSSIBLE_DETECTION_POINT, Optional.ofNullable(detectionPoint));
	}

	public EntitySize getCaughtSize() {
		return this.dataManager.get(CAUGHT_SIZE);
	}

	public void setCaughtSize(EntitySize caughtSize) {
		this.dataManager.set(CAUGHT_SIZE, caughtSize);
	}

	public int getTicksSinceLastSonarFire() {
		return this.ticksSinceLastSonarFire;
	}

	public boolean isStunned() {
		return this.getStunTime() > 0;
	}

	private void setStunned(int ticks) {
		this.dataManager.set(STUN_TIME, ticks);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("IsMoving", this.isMoving());
		compound.putInt("WaterTicks", this.getWaterTime());
		compound.putInt("StunnedTicks", this.getStunTime());
		compound.putInt("TicksSinceLastSonarFire", this.getTicksSinceLastSonarFire());

		if (this.getPossibleDetectionPoint() != null) {
			compound.put("DetectionPoint", NBTUtil.writeBlockPos(this.getPossibleDetectionPoint()));
		}
	}

	protected EntitySize getDefaultSize() {
		return DEFAULT_SIZE;
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setMoving(compound.getBoolean("IsMoving"));
		this.setWaterTime(compound.getInt("WaterTicks"));
		this.setStunned(compound.getInt("StunnedTicks"));
		this.ticksSinceLastSonarFire = compound.getInt("TicksSinceLastSonarFire");

		if (this.getPossibleDetectionPoint() != null) {
			this.setPossibleDetectionPoint(NBTUtil.readBlockPos(compound.getCompound("DetectionPoint")));
		}
	}

	static class ThrasherMoveController extends MovementController {
		private final ThrasherEntity thrasher;

		public ThrasherMoveController(ThrasherEntity thrasher) {
			super(thrasher);
			this.thrasher = thrasher;
		}

		public void tick() {
			if (this.action == MovementController.Action.MOVE_TO && !this.thrasher.getNavigator().noPath() && this.thrasher.getStunTime() <= 0) {
				Vector3d vec3d = new Vector3d(this.posX - this.thrasher.getPosX(), this.posY - this.thrasher.getPosY(), this.posZ - this.thrasher.getPosZ());
				double d0 = vec3d.length();
				double d1 = vec3d.y / d0;
				float f = (float) (MathHelper.atan2(vec3d.z, vec3d.x) * (double) (180F / (float) Math.PI)) - 90F;

				this.thrasher.rotationYaw = this.limitAngle(this.thrasher.rotationYaw, f, 10.0F);
				this.thrasher.renderYawOffset = this.thrasher.rotationYaw;
				this.thrasher.rotationYawHead = this.thrasher.rotationYaw;

				float f1 = (float) (this.speed * this.thrasher.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				float f2 = MathHelper.lerp(0.125F, this.thrasher.getAIMoveSpeed(), f1);

				this.thrasher.setAIMoveSpeed(f2);

				double d2 = Math.sin((double) (this.thrasher.ticksExisted + this.thrasher.getEntityId()) * 0.5D) * 0.05D;
				double d3 = Math.cos(this.thrasher.rotationYaw * ((float) Math.PI / 180F));
				double d4 = Math.sin(this.thrasher.rotationYaw * ((float) Math.PI / 180F));
				double d5 = Math.sin((double) (this.thrasher.ticksExisted + this.thrasher.getEntityId()) * 0.75D) * 0.05D;

				if (this.thrasher.isInWater()) {
					float f3 = -((float) (MathHelper.atan2(vec3d.y, MathHelper.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z)) * (double) (180F / (float) Math.PI)));
					f3 = MathHelper.clamp(MathHelper.wrapDegrees(f3), -85.0F, 85.0F);
					this.thrasher.rotationPitch = this.limitAngle(this.thrasher.rotationPitch, f3, 5.0F);
				}

				this.thrasher.setMotion(this.thrasher.getMotion().add(d2 * d3, d5 * (d4 + d3) * 0.25D + (double) f2 * d1 * 0.1D, d2 * d4));

				this.thrasher.setMoving(true);
			} else {
				this.thrasher.setAIMoveSpeed(0F);
				this.thrasher.setMoving(false);
			}
		}
	}

	public static class ThrasherLookController extends LookController {
		private boolean isTurningForSonar;

		public ThrasherLookController(ThrasherEntity thrasher) {
			super(thrasher);
		}

		public void tick() {
			if (this.isLooking) {
				this.isLooking = false;
				if (this.isTurningForSonar) {
					this.mob.rotationYaw = this.clampedRotate(this.mob.rotationYaw, this.getTargetYaw(), this.deltaLookYaw);
				} else {
					this.mob.rotationYawHead = this.clampedRotate(this.mob.rotationYawHead, this.getTargetYaw(), this.deltaLookYaw);
				}
				this.mob.rotationPitch = this.clampedRotate(this.mob.rotationPitch, this.getTargetPitch(), this.deltaLookPitch);
			} else {
				if (this.mob.getNavigator().noPath()) {
					this.mob.rotationPitch = this.clampedRotate(this.mob.rotationPitch, 0.0F, 5.0F);
				}
				if (this.isTurningForSonar) {
					this.mob.rotationYaw = this.clampedRotate(this.mob.rotationYaw, this.mob.renderYawOffset, this.deltaLookYaw);
				} else {
					this.mob.rotationYawHead = this.clampedRotate(this.mob.rotationYawHead, this.mob.renderYawOffset, this.deltaLookYaw);
				}
			}

			float wrappedDegrees = MathHelper.wrapDegrees(this.mob.rotationYawHead - this.mob.renderYawOffset);
			float angleLimit = this.mob.getPassengers().isEmpty() ? 10.0F : 5.0F;
			if (wrappedDegrees < -angleLimit) {
				this.mob.renderYawOffset -= 4.0F;
			} else if (wrappedDegrees > angleLimit) {
				this.mob.renderYawOffset += 4.0F;
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