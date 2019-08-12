package com.teamabnormals.upgrade_aquatic.common.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityThrasher extends MonsterEntity {
	private static final DataParameter<Boolean> MOVING = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SONAR_ACTIVE = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> WATER_TIME = EntityDataManager.createKey(EntityThrasher.class, DataSerializers.VARINT);
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
		this.lookController = new ThrasherLookController(this, 10);
		this.tailAnimation = this.rand.nextFloat();
		this.prevTailAnimation = this.tailAnimation;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1.1D, 10) {
			
			@Nullable
			@Override
			protected Vec3d getPosition() {
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.creature, 15, 8);
				for(int i = 0; vec3d != null && !this.creature.world.getBlockState(new BlockPos(vec3d)).allowsMovement(this.creature.world, new BlockPos(vec3d), PathType.WATER) && i++ < 10; vec3d = RandomPositionGenerator.findRandomTarget(this.creature, 10, 7)) {
					;
				}
				return vec3d;
			}
			
		});
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOVING, false);
		this.dataManager.register(SONAR_ACTIVE, false);
		this.dataManager.register(WATER_TIME, 2500);
	}
	
	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}
	
	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setAir(this.getMaxAir());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
    }

    public void readAdditional(CompoundNBT compound) {
    	super.readAdditional(compound);
    	this.setMoving(compound.getBoolean("IsMoving"));
    	this.setSonarActive(compound.getBoolean("SonarActive"));
    	this.setWaterTime(compound.getInt("WaterTicks"));
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
	
	public void travel(Vec3d p_213352_1_) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(0.1F, p_213352_1_);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (!this.isMoving() && this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
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
		}
		super.livingTick();
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
	
	class ThrasherLookController extends LookController {
		private final int angleLimit;

		public ThrasherLookController(EntityThrasher thrasher, int angleLimit) {
			super(thrasher);
			this.angleLimit = angleLimit;
		}

		public void tick() {
			if (this.isLooking) {
				this.isLooking = false;
				this.mob.rotationYawHead = this.func_220675_a(this.mob.rotationYawHead, this.func_220678_h() + 20.0F, this.deltaLookYaw);
				this.mob.rotationPitch = this.func_220675_a(this.mob.rotationPitch, this.func_220677_g() + 10.0F, this.deltaLookPitch);
			} else {
				if (this.mob.getNavigator().noPath()) {
					this.mob.rotationPitch = this.func_220675_a(this.mob.rotationPitch, 0.0F, 5.0F);
				}
				this.mob.rotationYawHead = this.func_220675_a(this.mob.rotationYawHead, this.mob.renderYawOffset, this.deltaLookYaw);
			}

			float wrappedDegrees = MathHelper.wrapDegrees(this.mob.rotationYawHead - this.mob.renderYawOffset);
			if (wrappedDegrees < (float)(-this.angleLimit)) {
				this.mob.renderYawOffset -= 4.0F;
			} else if (wrappedDegrees > (float)this.angleLimit) {
				this.mob.renderYawOffset += 4.0F;
			}
		}
	}
	
}
