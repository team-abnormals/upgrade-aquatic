package com.teamabnormals.upgrade_aquatic.common.entities.thrasher;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.api.endimator.ControlledEndimation;
import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatedMonsterEntity;
import com.teamabnormals.upgrade_aquatic.api.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai.*;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UASounds;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.DeepFrozenOceanBiome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityThrasher extends EndimatedMonsterEntity {
	public static final Predicate<Entity> ENEMY_MATCHER = (entity) -> {
		if (entity == null) {
			return false;
		}
		if(entity instanceof PlayerEntity && !(((PlayerEntity)entity).isCreative() || ((PlayerEntity)entity).isSpectator())) {
			return entity.isInWater();
		}
		return (entity instanceof WaterMobEntity && !(entity instanceof IMob) && !(entity instanceof EntityThrasher) && !(entity instanceof PufferfishEntity) && !(entity instanceof SquidEntity) && !(entity instanceof EntityLionfish)) && entity.isInWater();
	};
	private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_ID = UUID.fromString("3158fbca-89d7-4c15-b1ee-448cefd023b7");
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = (new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_ID, "Knockback Resistance", 4.0D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
	public static final IAttribute STUN_DAMAGE_THRESHOLD = new RangedAttribute(null, "thrasher.stun_threshold", 6.0D, 6.0D, 12.0D).setShouldWatch(true);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> WATER_TIME = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> STUN_TIME = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> HITS_TILL_STUN = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	private static final DataParameter<Optional<BlockPos>> POSSIBLE_DETECTION_POINT = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.OPTIONAL_BLOCK_POS);
	public static final Endimation SNAP_AT_PRAY_ANIMATION = new Endimation(10);
	public static final Endimation HURT_ANIMATION = new Endimation(10);
	public static final Endimation THRASH_ANIMATION = new Endimation(55);
	public static final Endimation SONAR_FIRE_ANIMATION = new Endimation(30);
	public final ControlledEndimation STUNNED_ANIMATION = new ControlledEndimation(10, 10);
	protected int ticksSinceLastSonarFire;
	protected float prevTailAnimation;
	protected float tailAnimation;
	protected float tailSpeed;
	protected float prevFinAnimation;
	protected float finAnimation;
	protected float finSpeed;
	
	public EntityThrasher(EntityType<? extends EntityThrasher> type, World world) {
		super(type, world);
		this.experienceValue = 30;
		this.moveController = new EntityThrasher.ThrasherMoveController(this);
		this.lookController = new ThrasherLookController(this);
		this.tailAnimation = this.rand.nextFloat();
		this.prevTailAnimation = this.tailAnimation;
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
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(STUN_DAMAGE_THRESHOLD);
		
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.25D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOVING, false);
		this.dataManager.register(WATER_TIME, 2500);
		this.dataManager.register(STUN_TIME, 0);
		this.dataManager.register(HITS_TILL_STUN, 0);
		this.dataManager.register(POSSIBLE_DETECTION_POINT, Optional.empty());
	}
	
	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}
	
	@Override
	public Endimation[] getAnimations() {
		return new Endimation[] {
			SNAP_AT_PRAY_ANIMATION,
			HURT_ANIMATION,
			THRASH_ANIMATION,
			SONAR_FIRE_ANIMATION
		};
	}
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setAir(this.getMaxAir());
		if(reason == SpawnReason.NATURAL && worldIn.getBiome(this.getPosition()) instanceof DeepFrozenOceanBiome) {
			Random rand = new Random();
			if(rand.nextFloat() < 0.25F) {
				EntityGreatThrasher greatThrasher = UAEntities.GREAT_THRASHER.get().create(this.world);
				greatThrasher.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
				this.world.addEntity(greatThrasher);
				this.remove();
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if(HITS_TILL_STUN.equals(key)) {
			if(this.getHitsLeftTillStun() == 0) {
				this.setStunned(((this.getRNG().nextInt(2) + 2) * 20) + this.getRNG().nextInt(10));
			}
		}
		super.notifyDataManagerChange(key);
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
		if(passenger instanceof LivingEntity) {
			float distance = this.getMountDistance();
			
			double dx = Math.cos((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			double dy = -Math.sin(this.rotationPitch * (Math.PI / 180.0D));
			double dz = Math.sin((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			
			Vec3d riderPos = new Vec3d(this.getPosX() + dx, this.getPosY(), this.getPosZ() + dz);
			
			double offset = passenger instanceof PlayerEntity ? this.getMountedYOffset() - 0.2D : this.getMountedYOffset() - 0.5F;
			
			passenger.setPosition(riderPos.x, this.getPosY() + dy + offset, riderPos.z);
		} else {
			super.updatePassenger(passenger);
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
	public boolean isNotColliding(IWorldReader worldIn) {
		return worldIn.checkNoEntityCollision(this);
	}
	
	@Override
	protected void onAnimationStart(Endimation animationStarted) {
		if(animationStarted == THRASH_ANIMATION) {
			IAttributeInstance knockbackResistance = this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			if(!knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
				knockbackResistance.applyModifier(KNOCKBACK_RESISTANCE_MODIFIER);
			}
		}
	}
	
	@Override
	protected void onAnimationEnd(Endimation animationEnded) {
		if(animationEnded == THRASH_ANIMATION) {
			IAttributeInstance knockbackResistance = this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			if(knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
				knockbackResistance.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER);
			}
		}
	}
	
	@Override
	public void onEnterBubbleColumn(boolean downwards) {}

	@Override
	public void onEnterBubbleColumnWithAirAbove(boolean downwards) {}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entitySource = source.getTrueSource();
		this.setPlayingAnimation(HURT_ANIMATION);
		if(entitySource instanceof LivingEntity) {
			Vec3d difference = new Vec3d(
				entitySource.getPosition().getX() - this.getPosition().getX(),
				entitySource.getPosition().getY() - this.getPosition().getY(),
				entitySource.getPosition().getZ() - this.getPosition().getZ()
			);
			if(difference.length() <= 8) {
				if(entitySource.isInWater()) {
					if(entitySource instanceof PlayerEntity && !((PlayerEntity)entitySource).isCreative() && !((PlayerEntity)entitySource).isSpectator()) {
						this.setAttackTarget((LivingEntity)entitySource);
					} else if(!(entitySource instanceof PlayerEntity)) {
						this.setAttackTarget((LivingEntity)entitySource);
					}
				}
				if(this.getHitsLeftTillStun() > 0) {
					int difficultyDividend = 0;
					switch(this.world.getDifficulty()) {
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
					if(this.getRNG().nextInt(chance) == 0) {
						this.setHitsTillStun(this.getHitsLeftTillStun() - 1);
					}
				}
			} else {
				if(entitySource instanceof PlayerEntity && !((PlayerEntity)entitySource).isCreative() && !((PlayerEntity)entitySource).isSpectator()) {
					this.setPossibleDetectionPoint(entitySource.getPosition().add(this.getRNG().nextInt(2), this.getRNG().nextInt(2), this.getRNG().nextInt(2)));
				} else if(!(entitySource instanceof PlayerEntity)) {
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
	
	public void travel(Vec3d p_213352_1_) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.1F, p_213352_1_);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (!this.isMoving() && this.getAttackTarget() == null) {
				double ySpeed = -0.005D;
				this.setMotion(this.getMotion().add(0.0D, ySpeed, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(this.getAttackTarget() != null && !this.getAttackTarget().isAlive() && this.ticksSinceLastSonarFire >= 55 && this.getRNG().nextFloat() < 0.05F) {
			this.setAttackTarget(null);
		}
		
		if(!this.isAIDisabled()) {
			if(this.isAnimationPlaying(SONAR_FIRE_ANIMATION)) {
				this.ticksSinceLastSonarFire = 0;
			} else {
				this.ticksSinceLastSonarFire++;
			}
			if(this.isInWaterRainOrBubbleColumn()) {
				this.setWaterTime(2400);
			} else {
				this.setWaterTime(this.getWaterTime() - 1);
				if(this.getWaterTime() <= 0) {
					this.attackEntityFrom(DamageSource.DRYOUT, 1.0F);
				}

				if(!this.isInWater() && !this.isStunned() && this.onGround) {
					this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F)));
					this.rotationYaw = this.rand.nextFloat() * 360.0F;
					this.rotationPitch = this.rand.nextFloat() * -50.0F;
					this.onGround = false;
					this.isAirBorne = true;
					this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
				}
			}
			if(this.isWorldRemote()) {
				if(!this.getPassengers().isEmpty() && this.isAnimationPlaying(THRASH_ANIMATION) && this.getAnimationTick() % 2 == 0 && this.getAnimationTick() > 5) {
					Entity passenger = this.getPassengers().get(0);
					for(int i = 0; i < 3; ++i) {
						if(passenger.areEyesInFluid(FluidTags.WATER)) {
							this.world.addParticle(ParticleTypes.BUBBLE, passenger.getPosX() + (this.getRNG().nextDouble() - 0.5D) * (double)passenger.getWidth(), passenger.getPosY(), passenger.getPosZ() + (this.getRNG().nextDouble() - 0.5D) * (double)passenger.getWidth(), (this.getRNG().nextDouble() - 0.5D) * 2.0D, -this.getRNG().nextDouble(), (this.getRNG().nextDouble() - 0.5D) * 2.0D);
						}
					}
				}
			}
			if(this.isStunned()) {
				if(!this.getPassengers().isEmpty() && !this.isWorldRemote()) {
					this.removePassengers();
				}
				this.setStunned(this.getStunTime() - 1);
			}
		}
	}
	
	@Override
	public void livingTick() {
		if(this.isAlive()) {
			if(this.world.isRemote) {
				this.prevTailAnimation = this.tailAnimation;
				this.prevFinAnimation = this.finAnimation;
				this.STUNNED_ANIMATION.update();
				
				if(!this.isInWater() || (this.isAnimationPlaying(THRASH_ANIMATION) || this.isAnimationPlaying(SONAR_FIRE_ANIMATION))) {
					this.tailSpeed = 1.1F;
					this.finSpeed = 0.875F;
				} else if(this.isMoving()) {
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
				
				if(this.isStunned()) {
					if(this.STUNNED_ANIMATION.getCurrentValue() >= 10) {
						this.STUNNED_ANIMATION.manipulateTimer(true);
					} else {
						if(this.STUNNED_ANIMATION.shouldDecrement && this.STUNNED_ANIMATION.getCurrentValue() <= 0) {
							this.STUNNED_ANIMATION.manipulateTimer(false);
						}
					}
				} else {
					if(this.STUNNED_ANIMATION.getCurrentValue() == 10) {
						this.STUNNED_ANIMATION.setTimerToStop(true);
					}
					
					this.STUNNED_ANIMATION.manipulateTimer(false);
				}
				
				this.STUNNED_ANIMATION.updateTimerValues();
				
				this.tailAnimation += this.tailSpeed;
				this.finAnimation += this.finSpeed;
			}
			
			if(!this.isStunned()) {
				List<LivingEntity> nearbyEntities = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(0.5F), EntityThrasher.ENEMY_MATCHER);
				for(LivingEntity entities : nearbyEntities) {
					if(this.getAttackTarget() == null) {
						this.setAttackTarget((LivingEntity) entities);
					}
				}
			}
			
			if(this.isMoving() && this.isInWater()) {
				Vec3d vec3d1 = this.getLook(0.0F);

				for(int i = 0; i < 2; ++i) {
					this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth() - vec3d1.x * 1.5D, this.getPosY() + this.rand.nextDouble() * (double)this.getHeight() - vec3d1.y * 1.5D, this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth() - vec3d1.z * 1.5D, 0.0D, 0.0D, 0.0D);
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
		return this.isInWater() ? UASounds.THRASHER_HURT.get() : UASounds.THRASHER_HURT_LAND.get();
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return this.isInWater() ? UASounds.THRASHER_DEATH.get() : UASounds.THRASHER_DEATH_LAND.get();
	}
	
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if(this.isAnimationPlaying(THRASH_ANIMATION)) {
			return null;
		}
		return this.isInWater() ? UASounds.THRASHER_AMBIENT.get() : UASounds.THRASHER_AMBIENT_LAND.get();
	}
	
	protected SoundEvent getFlopSound() {
		return UASounds.THRASHER_FLOP.get();
	}
	
	public SoundEvent getSonarFireSound() {
		return UASounds.THRASHER_SONAR_FIRE.get();
	}
	
	public SoundEvent getThrashingSound() {
		return UASounds.THRASHER_THRASH.get();
	}
	
	protected SoundEvent getStunSound() {
		return UASounds.THRASHER_STUN.get();
	}
	
	@Override
	protected SoundEvent getSwimSound() {
		return UASounds.THRASHER_SWIM.get();
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {}
	
	@Override
	public int getTalkInterval() {
		return 100;
	}
	
	public double getStunDamageThreshold() {
		return this.getAttribute(STUN_DAMAGE_THRESHOLD).getValue();
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	public static void addSpawn() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(EntityThrasher::processSpawning);
	}
	
	private static void processSpawning(Biome biome) {
		if(biome.getCategory() == Category.OCEAN && BiomeDictionary.hasType(biome, Type.COLD)) {
			biome.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(UAEntities.THRASHER.get(), 90, 1, 2));
		}
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

	public void setStunned(int ticks) {
		this.dataManager.set(STUN_TIME, ticks);
	}
	
	public int getHitsLeftTillStun() {
		return this.dataManager.get(HITS_TILL_STUN);
	}

	public void setHitsTillStun(int hits) {
		this.dataManager.set(HITS_TILL_STUN, hits);
	}
	
	public void setPossibleDetectionPoint(@Nullable BlockPos detectionPoint) {
		this.getDataManager().set(POSSIBLE_DETECTION_POINT, Optional.ofNullable(detectionPoint));
	}
	
	@Nullable
	public BlockPos getPossibleDetectionPoint() {
		return this.getDataManager().get(POSSIBLE_DETECTION_POINT).orElse((BlockPos)null);
	}
	
	public int getTicksSinceLastSonarFire() {
		return this.ticksSinceLastSonarFire;
	}
	
	public boolean isStunned() {
		return this.getStunTime() > 0;
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("IsMoving", this.isMoving());
		compound.putInt("WaterTicks", this.getWaterTime());
		compound.putInt("StunnedTicks", this.getStunTime());
		compound.putInt("HitsTillStun", this.getHitsLeftTillStun());
		compound.putInt("TicksSinceLastSonarFire", this.getTicksSinceLastSonarFire());
		
		if(this.getPossibleDetectionPoint() != null) {
			compound.put("DetectionPoint", NBTUtil.writeBlockPos(this.getPossibleDetectionPoint()));
		}
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setMoving(compound.getBoolean("IsMoving"));
		this.setWaterTime(compound.getInt("WaterTicks"));
		this.setStunned(compound.getInt("StunnedTicks"));
		this.setHitsTillStun(compound.getInt("HitsTillStun"));
		this.ticksSinceLastSonarFire = compound.getInt("TicksSinceLastSonarFire");
		
		if(this.getPossibleDetectionPoint() != null) {
			this.setPossibleDetectionPoint(NBTUtil.readBlockPos(compound.getCompound("DetectionPoint")));
		}
	}
	
	static class ThrasherMoveController extends MovementController {
		private final EntityThrasher thrasher;

		public ThrasherMoveController(EntityThrasher thrasher) {
			super(thrasher);
			this.thrasher = thrasher;
		}

		public void tick() {
			if (this.action == MovementController.Action.MOVE_TO && !this.thrasher.getNavigator().noPath() && this.thrasher.getStunTime() <= 0) {
				Vec3d vec3d = new Vec3d(this.posX - this.thrasher.getPosX(), this.posY - this.thrasher.getPosY(), this.posZ - this.thrasher.getPosZ());
				double d0 = vec3d.length();
				double d1 = vec3d.y / d0;
				float f = (float) (MathHelper.atan2(vec3d.z, vec3d.x) * (double) (180F / (float) Math.PI)) - 90F;
				
				this.thrasher.rotationYaw = this.limitAngle(this.thrasher.rotationYaw, f, 10.0F);
				this.thrasher.renderYawOffset = this.thrasher.rotationYaw;
				this.thrasher.rotationYawHead = this.thrasher.rotationYaw;
				
				float f1 = (float)(this.speed * this.thrasher.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
				float f2 = MathHelper.lerp(0.125F, this.thrasher.getAIMoveSpeed(), f1);
				
				this.thrasher.setAIMoveSpeed(f2);
				
				double d2 = Math.sin((double)(this.thrasher.ticksExisted + this.thrasher.getEntityId()) * 0.5D) * 0.05D;
				double d3 = Math.cos((double)(this.thrasher.rotationYaw * ((float)Math.PI / 180F)));
				double d4 = Math.sin((double)(this.thrasher.rotationYaw * ((float)Math.PI / 180F)));
				double d5 = Math.sin((double)(this.thrasher.ticksExisted + this.thrasher.getEntityId()) * 0.75D) * 0.05D;
				
				if (this.thrasher.isInWater()) {
					float f3 = -((float)(MathHelper.atan2(vec3d.y, (double)MathHelper.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z)) * (double)(180F / (float)Math.PI)));
					f3 = MathHelper.clamp(MathHelper.wrapDegrees(f3), -85.0F, 85.0F);
					this.thrasher.rotationPitch = this.limitAngle(this.thrasher.rotationPitch, f3, 5.0F);
				}
				
				this.thrasher.setMotion(this.thrasher.getMotion().add(d2 * d3, d5 * (d4 + d3) * 0.25D + (double)f2 * d1 * 0.1D, d2 * d4));
				
				this.thrasher.setMoving(true);
			} else {
				this.thrasher.setAIMoveSpeed(0F);
				this.thrasher.setMoving(false);
			}
		}
	}
	
	public class ThrasherLookController extends LookController {
		private int angleLimit;
		private boolean isTurningForSonar;

		public ThrasherLookController(EntityThrasher thrasher) {
			super(thrasher);
		}

		public void tick() {
			this.angleLimit = this.mob.getPassengers().isEmpty() ? 10 : 5;
			if(this.isLooking) {
				this.isLooking = false;
				if(this.isTurningForSonar) {
					this.mob.rotationYaw = this.clampedRotate(this.mob.rotationYaw, this.getTargetYaw(), this.deltaLookYaw);
				} else {
					this.mob.rotationYawHead = this.clampedRotate(this.mob.rotationYawHead, this.getTargetYaw(), this.deltaLookYaw);
				}
				this.mob.rotationPitch = this.clampedRotate(this.mob.rotationPitch, this.getTargetPitch(), this.deltaLookPitch);
			} else {
				if(this.mob.getNavigator().noPath()) {
					this.mob.rotationPitch = this.clampedRotate(this.mob.rotationPitch, 0.0F, 5.0F);
				}
				if(this.isTurningForSonar) {
					this.mob.rotationYaw = this.clampedRotate(this.mob.rotationYaw, this.mob.renderYawOffset, this.deltaLookYaw);
				} else {
					this.mob.rotationYawHead = this.clampedRotate(this.mob.rotationYawHead, this.mob.renderYawOffset, this.deltaLookYaw);
				}
			}

			float wrappedDegrees = MathHelper.wrapDegrees(this.mob.rotationYawHead - this.mob.renderYawOffset);
			if(wrappedDegrees < (float)(-this.angleLimit)) {
				this.mob.renderYawOffset -= 4.0F;
			} else if(wrappedDegrees > (float)this.angleLimit) {
				this.mob.renderYawOffset += 4.0F;
			}
		}
		
		public void setTurningForSonar(boolean isTurning) {
			this.isTurningForSonar = isTurning;
		}
	}
	
}