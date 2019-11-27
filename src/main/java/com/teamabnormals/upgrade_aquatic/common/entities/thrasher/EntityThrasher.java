package com.teamabnormals.upgrade_aquatic.common.entities.thrasher;

import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;

import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatedMonsterEntity;
import com.teamabnormals.upgrade_aquatic.api.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai.ThrasherGrabGoal;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai.ThrasherRandomSwimGoal;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityThrasher extends EndimatedMonsterEntity {
	private static final Predicate<LivingEntity> ENEMY_MATCHER = (entity) -> {
		if (entity == null) {
			return false;
		} else {
			if(entity instanceof PlayerEntity) {
				if(((PlayerEntity)entity).isCreative()) {
					return false;
				}
				return entity.isInWater();
			}
			return entity instanceof AbstractFishEntity && entity.isInWater();
		}
	};
	private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_ID = UUID.fromString("3158fbca-89d7-4c15-b1ee-448cefd023b7");
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER = (new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_ID, "Sonar Knockback resistance", (double)0.75F, AttributeModifier.Operation.ADDITION)).setSaved(false);
	private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SONAR_ACTIVE = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TICKS_TILL_SONAR = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> WATER_TIME = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CAUGHT_ENTITY = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
	public static final	Endimation SNAP_AT_PRAY_ANIMATION = new Endimation(10);
	public static final	Endimation DAMAGE_ANIMATION = new Endimation(10);
	protected int ticksSinceLastSonar;
	protected int sonarTicks;
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
		//this.goalSelector.addGoal(2, new EntityThrasher.SonarDetectionGoal(this));
		this.goalSelector.addGoal(7, new ThrasherRandomSwimGoal(this, 1.1D, 15));
		this.goalSelector.addGoal(3, new ThrasherGrabGoal(this, 2.5F, true));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25D);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOVING, false);
		this.dataManager.register(SONAR_ACTIVE, false);
		this.dataManager.register(WATER_TIME, 2500);
		this.dataManager.register(TICKS_TILL_SONAR, (rand.nextInt(130) + 45) * 20);
		this.dataManager.register(CAUGHT_ENTITY, 0);
	}
	
	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}
	
	@Override
	public Endimation[] getAnimations() {
		return new Endimation[] {
			SNAP_AT_PRAY_ANIMATION,
			DAMAGE_ANIMATION
		};
	}
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setAir(this.getMaxAir());
		this.setTicksTillSonar((rand.nextInt(10) + 10) * 20);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
		if(!this.getPassengers().isEmpty() && (passenger instanceof AbstractFishEntity || passenger instanceof PlayerEntity)) {
			float distance = 1.2F;
			
			double dx = Math.cos((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			double dz = Math.sin((this.rotationYaw + 90) * Math.PI / 180.0D) * distance;
			
			Vec3d riderPos = new Vec3d(this.posX + dx, this.posY + this.getMountedYOffset() + this.getPassengers().get(0).getYOffset(), this.posZ + dz);
			
			passenger.setPosition(riderPos.x, riderPos.y, riderPos.z);
		} else {
			super.updatePassenger(passenger);
		}
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
	
	@Nullable
	public LivingEntity getCaughtEntity() {
		if (!this.hasCaughtEntity()) {
			return null;
		} else {
			Entity entity = this.world.getEntityByID(this.dataManager.get(CAUGHT_ENTITY));
			if(entity == null) {
				return null;
			} else if(entity != null && (entity instanceof AbstractFishEntity || entity instanceof PlayerEntity)) {
				return (LivingEntity)entity;
			}
		}
		return null;
	}
	
	@Override
	protected void onDeathUpdate() {
		this.setAttackTarget(null);
		super.onDeathUpdate();
	}
	
	public void setCaughtEntity(int entityId) {
		this.dataManager.set(CAUGHT_ENTITY, entityId);
	}

	public boolean hasCaughtEntity() {
		return this.dataManager.get(CAUGHT_ENTITY) != 0;
	}
	
	public boolean isMoving() {
		return this.dataManager.get(MOVING);
	}

	private void setMoving(boolean moving) {
		this.dataManager.set(MOVING, moving);
	}
	
	public boolean isSonarActive() {
		return this.dataManager.get(SONAR_ACTIVE);
	}

	private void setSonarActive(boolean active) {
		this.dataManager.set(SONAR_ACTIVE, active);
	}
	
	public int getTicksTillSonar() {
		return this.dataManager.get(TICKS_TILL_SONAR);
	}

	public void setTicksTillSonar(int ticks) {
		this.dataManager.set(TICKS_TILL_SONAR, ticks);
	}
	
	public int getWaterTime() {
		return this.dataManager.get(WATER_TIME);
	}

	public void setWaterTime(int ticks) {
		this.dataManager.set(WATER_TIME, ticks);
	}
	
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("IsMoving", this.isMoving());
		compound.putBoolean("SonarActive", this.isSonarActive());
		compound.putInt("WaterTicks", this.getWaterTime());
		compound.putInt("TicksTillSonar", this.getTicksTillSonar());
		//compound.putInt("CaughtEntityId", this.getCaughtEntity().getEntityId());
    }

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setMoving(compound.getBoolean("IsMoving"));
		this.setSonarActive(compound.getBoolean("SonarActive"));
		this.setWaterTime(compound.getInt("WaterTicks"));
		this.setTicksTillSonar(compound.getInt("TicksTillSonar"));
		//this.setCaughtEntity(compound.getInt("CaughtEntityId"));
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
    	Entity entitySource = source.getTrueSource();
    	if(this.isSonarActive()) {
    		this.setSonarActive(false);
    		this.setTicksTillSonar((rand.nextInt(60) + 45) * 20);
    	}
    	this.setPlayingAnimation(DAMAGE_ANIMATION);
    	if(entitySource instanceof LivingEntity) {
    		Vector3f difference = new Vector3f(
    			entitySource.getPosition().getX() - this.getPosition().getX(),
    			entitySource.getPosition().getY() - this.getPosition().getY(),
    			entitySource.getPosition().getZ() - this.getPosition().getZ()
    		);
    		if(difference.length() <= 8 && entitySource.isInWater()) {
    			this.setAttackTarget((LivingEntity)entitySource);
    		}
    	}
    	return super.attackEntityFrom(source, amount);
    }
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return worldIn.getFluidState(pos).isTagged(FluidTags.WATER) ? 10.0F + worldIn.getBrightness(pos) - 0.5F : super.getBlockPathWeight(pos, worldIn);
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
	
	protected void pulsateSonar() {
		this.playSound(SoundEvents.BLOCK_CONDUIT_ACTIVATE, 8.5F, 0.9F);
		if(this.world.isRemote) {
			Vec3d look = this.getLook(1.0F).normalize();
			this.world.addParticle(UAParticles.SONAR, this.posX, this.posY, this.posZ, look.x * 0.6F, look.y * 0.6F, look.z * 0.6F);
		}
	}
	
	public void travel(Vec3d p_213352_1_) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.1F, p_213352_1_);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (!this.isMoving() && this.getAttackTarget() == null) {
				double ySpeed = this.isSonarActive() ? -0.002D : -0.005D;
				this.setMotion(this.getMotion().add(0.0D, ySpeed, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		if (!this.isAIDisabled()) {
			if (this.isInWaterRainOrBubbleColumn()) {
				this.setWaterTime(2400);
			} else {
				this.setWaterTime(this.getWaterTime() - 1);
				if (this.getWaterTime() <= 0) {
					this.attackEntityFrom(DamageSource.DRYOUT, 1.0F);
				}

				if (this.onGround) {
					this.setMotion(this.getMotion().add((double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F)));
					this.rotationYaw = this.rand.nextFloat() * 360.0F;
					this.rotationPitch = this.rand.nextFloat() * -50.0F;
					this.onGround = false;
					this.isAirBorne = true;
				}
			}
		}
	}
	
	@Override
	public void livingTick() {
		if(this.isAlive()) {
			if (this.world.isRemote) {
				this.prevTailAnimation = this.tailAnimation;
				this.prevFinAnimation = this.finAnimation;
				if(!this.isInWater()) {
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
				this.tailAnimation += this.tailSpeed;
				this.finAnimation += this.finSpeed;
			}
			IAttributeInstance knockbackResistance = this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			if(this.isSonarActive()) {
				if(!knockbackResistance.hasModifier(KNOCKBACK_RESISTANCE_MODIFIER)) {
					knockbackResistance.applyModifier(KNOCKBACK_RESISTANCE_MODIFIER);
				}
				
				if(this.sonarTicks % 80 == 0) {
					this.pulsateSonar();
				}
				
				if(this.sonarTicks >= 159) {
					this.setTicksTillSonar((rand.nextInt(100) + 75) * 20);
					this.setSonarActive(false);
				}
				
				this.getNavigator().clearPath();
				this.finSpeed = 0.25F;
				this.sonarTicks++;
			} else {
				knockbackResistance.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER);
				this.sonarTicks = 0;
				if(this.ticksSinceLastSonar < this.getTicksTillSonar()) {
					this.ticksSinceLastSonar++;
				} else if(this.ticksSinceLastSonar >= this.getTicksTillSonar()) {
					//this.setSonarActive(true);
				}
			}
		}
		super.livingTick();
	}
	
	static class SonarDetectionGoal extends Goal {
		private final EntityThrasher thrasher;
		
		public SonarDetectionGoal(EntityThrasher thrasher) {
			this.thrasher = thrasher;
			this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
		}

		@Override
		public boolean shouldExecute() {
			return this.thrasher.isSonarActive();
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return this.thrasher.isSonarActive();
		}
		
		@Override
		public void tick() {
			AxisAlignedBB detectionBox = new AxisAlignedBB(this.thrasher.getPosition());
			if (this.thrasher.isAlive()) {
				double expansionAmount = this.thrasher.sonarTicks <= 80 ? (this.thrasher.sonarTicks / 20) * 4 : (this.thrasher.sonarTicks / 20) / 2 * 4;
				for(LivingEntity livingEntity : this.thrasher.world.getEntitiesWithinAABB(LivingEntity.class, detectionBox.grow(expansionAmount), ENEMY_MATCHER)) {
					if(livingEntity != null && livingEntity.isAlive()) {
						this.thrasher.setAttackTarget(livingEntity);
						this.thrasher.setSonarActive(false);
						this.thrasher.setTicksTillSonar((this.thrasher.getRNG().nextInt(140) + 80) * 20);
					}
				}
			}
		}
	}
	
	static class ThrasherMoveController extends MovementController {
		private final EntityThrasher thrasher;

		public ThrasherMoveController(EntityThrasher thrasher) {
			super(thrasher);
			this.thrasher = thrasher;
		}

		public void tick() {
			if (this.action == MovementController.Action.MOVE_TO && !this.thrasher.getNavigator().noPath()) {
				Vec3d vec3d = new Vec3d(this.posX - this.thrasher.posX, this.posY - this.thrasher.posY, this.posZ - this.thrasher.posZ);
				double d0 = vec3d.length();
				double d1 = vec3d.y / d0;
				float f = (float) (MathHelper.atan2(vec3d.z, vec3d.x) * (double) (180F / (float) Math.PI)) - 90F;
				
				this.thrasher.rotationYaw = this.limitAngle(this.thrasher.rotationYaw, f, 10.0F);
				this.thrasher.renderYawOffset = this.thrasher.rotationYaw;
				this.thrasher.rotationYawHead = this.thrasher.rotationYaw;
				
				float f1 = (float)(this.speed * this.thrasher.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
				float f2 = MathHelper.lerp(0.125F, this.thrasher.getAIMoveSpeed(), f1);
				
				if(this.thrasher.isSonarActive()) {
					this.thrasher.setAIMoveSpeed(0);
				} else {
					this.thrasher.setAIMoveSpeed(f2);
				}
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
	
	class ThrasherLookController extends LookController {
		private int angleLimit;

		public ThrasherLookController(EntityThrasher thrasher) {
			super(thrasher);
		}

		public void tick() {
			this.angleLimit = this.mob.getPassengers().isEmpty() ? 10 : 5;
			if(this.isLooking) {
				this.isLooking = false;
				this.mob.rotationYawHead = this.func_220675_a(this.mob.rotationYawHead, this.func_220678_h(), this.deltaLookYaw);
				this.mob.rotationPitch = this.func_220675_a(this.mob.rotationPitch, this.func_220677_g(), this.deltaLookPitch);
			} else {
				if (this.mob.getNavigator().noPath()) {
					this.mob.rotationPitch = this.func_220675_a(this.mob.rotationPitch, 0.0F, 5.0F);
				}
				this.mob.rotationYawHead = this.func_220675_a(this.mob.rotationYawHead, this.mob.renderYawOffset, this.deltaLookYaw);
			}

			float wrappedDegrees = MathHelper.wrapDegrees(this.mob.rotationYawHead - this.mob.renderYawOffset);
			if(wrappedDegrees < (float)(-this.angleLimit)) {
				this.mob.renderYawOffset -= 4.0F;
			} else if(wrappedDegrees > (float)this.angleLimit) {
				this.mob.renderYawOffset += 4.0F;
			}
		}
	}
	
}