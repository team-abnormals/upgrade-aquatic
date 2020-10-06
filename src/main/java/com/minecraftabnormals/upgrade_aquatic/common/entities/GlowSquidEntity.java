package com.minecraftabnormals.upgrade_aquatic.common.entities;

import java.util.Random;

import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlowSquidEntity extends BucketableWaterMobEntity {
	public float squidPitch;
	public float prevSquidPitch;
	public float squidYaw;
	public float prevSquidYaw;
	public float squidRotation;
	public float prevSquidRotation;
	public float tentacleAngle;
	public float lastTentacleAngle;
	private float randomMotionSpeed;
	private float rotationVelocity;
	private float rotateSpeed;
	private float randomMotionVecX;
	private float randomMotionVecY;
	private float randomMotionVecZ;

	public GlowSquidEntity(EntityType<? extends GlowSquidEntity> type, World worldIn) {
		super(type, worldIn);
		this.rand.setSeed((long) this.getEntityId());
		this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new GlowSquidEntity.MoveRandomGoal(this));
		this.goalSelector.addGoal(1, new GlowSquidEntity.FleeGoal());
	}

	public static AttributeModifierMap.MutableAttribute func_234227_m_() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.MAX_HEALTH, 10.0D);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.5F;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SQUID_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SQUID_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SQUID_DEATH;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.GLOW_SQUID_SPAWN_EGG.get());
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.GLOW_SQUID_BUCKET.get());
	}

	protected boolean canTriggerWalking() {
		return false;
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		super.applyEntityCollision(entityIn);
		if (entityIn instanceof LivingEntity) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 600));
		}
	}

	public void livingTick() {
		super.livingTick();
        
		this.prevSquidPitch = this.squidPitch;
		this.prevSquidYaw = this.squidYaw;
		this.prevSquidRotation = this.squidRotation;
		this.lastTentacleAngle = this.tentacleAngle;
		this.squidRotation += this.rotationVelocity;
		if ((double) this.squidRotation > (Math.PI * 2D)) {
			if (this.world.isRemote) {
				this.squidRotation = ((float) Math.PI * 2F);
			} else {
				this.squidRotation = (float) ((double) this.squidRotation - (Math.PI * 2D));
				if (this.rand.nextInt(10) == 0) {
					this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
				}

				this.world.setEntityState(this, (byte) 19);
			}
		}

		if (this.isInWaterOrBubbleColumn()) {
			if (this.squidRotation < (float) Math.PI) {
				float f = this.squidRotation / (float) Math.PI;
				this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
				if ((double) f > 0.75D) {
					this.randomMotionSpeed = 1.0F;
					this.rotateSpeed = 1.0F;
				} else {
					this.rotateSpeed *= 0.8F;
				}
			} else {
				this.tentacleAngle = 0.0F;
				this.randomMotionSpeed *= 0.9F;
				this.rotateSpeed *= 0.99F;
			}

			if (!this.world.isRemote) {
				this.setMotion((double) (this.randomMotionVecX * this.randomMotionSpeed), (double) (this.randomMotionVecY * this.randomMotionSpeed), (double) (this.randomMotionVecZ * this.randomMotionSpeed));
			}

			Vector3d vector3d = this.getMotion();
			float f1 = MathHelper.sqrt(horizontalMag(vector3d));
			this.renderYawOffset += (-((float) MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float) Math.PI) - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
			this.squidYaw = (float) ((double) this.squidYaw + Math.PI * (double) this.rotateSpeed * 1.5D);
			this.squidPitch += (-((float) MathHelper.atan2((double) f1, vector3d.y)) * (180F / (float) Math.PI) - this.squidPitch) * 0.1F;
		} else {
			this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float) Math.PI * 0.25F;
			if (!this.world.isRemote) {
				double d0 = this.getMotion().y;
				if (this.isPotionActive(Effects.LEVITATION)) {
					d0 = 0.05D * (double) (this.getActivePotionEffect(Effects.LEVITATION).getAmplifier() + 1);
				} else if (!this.hasNoGravity()) {
					d0 -= 0.08D;
				}

				this.setMotion(0.0D, d0 * (double) 0.98F, 0.0D);
			}

			this.squidPitch = (float) ((double) this.squidPitch + (double) (-90.0F - this.squidPitch) * 0.02D);
		}

	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount) && this.getRevengeTarget() != null) {
			this.squirtInk();
			return true;
		} else {
			return false;
		}
	}

	private Vector3d func_207400_b(Vector3d p_207400_1_) {
		Vector3d vector3d = p_207400_1_.rotatePitch(this.prevSquidPitch * ((float) Math.PI / 180F));
		return vector3d.rotateYaw(-this.prevRenderYawOffset * ((float) Math.PI / 180F));
	}

	private void squirtInk() {
		this.playSound(SoundEvents.ENTITY_SQUID_SQUIRT, this.getSoundVolume(), this.getSoundPitch());
		Vector3d vector3d = this.func_207400_b(new Vector3d(0.0D, -1.0D, 0.0D)).add(this.getPosX(), this.getPosY(), this.getPosZ());

		for (int i = 0; i < 30; ++i) {
			Vector3d vector3d1 = this.func_207400_b(new Vector3d((double) this.rand.nextFloat() * 0.6D - 0.3D, -1.0D, (double) this.rand.nextFloat() * 0.6D - 0.3D));
			Vector3d vector3d2 = vector3d1.scale(0.3D + (double) (this.rand.nextFloat() * 2.0F));
			((ServerWorld) this.world).spawnParticle(UAParticles.GLOW_SQUID_INK.get(), vector3d.x, vector3d.y + 0.5D, vector3d.z, 0, vector3d2.x, vector3d2.y, vector3d2.z, (double) 0.1F);
		}
	}

	public void travel(Vector3d travelVector) {
		this.move(MoverType.SELF, this.getMotion());
	}

	public static boolean func_223365_b(EntityType<GlowSquidEntity> p_223365_0_, IWorld p_223365_1_, SpawnReason reason, BlockPos p_223365_3_, Random p_223365_4_) {
		return p_223365_3_.getY() > 45 && p_223365_3_.getY() < p_223365_1_.getSeaLevel();
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 19) {
			this.squidRotation = 0.0F;
		} else {
			super.handleStatusUpdate(id);
		}

	}

	public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
		this.randomMotionVecX = randomMotionVecXIn;
		this.randomMotionVecY = randomMotionVecYIn;
		this.randomMotionVecZ = randomMotionVecZIn;
	}

	public boolean hasMovementVector() {
		return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
	}

	class FleeGoal extends Goal {
		private int tickCounter;

		private FleeGoal() {
		}

		public boolean shouldExecute() {
			LivingEntity livingentity = GlowSquidEntity.this.getRevengeTarget();
			if (GlowSquidEntity.this.isInWater() && livingentity != null) {
				return GlowSquidEntity.this.getDistanceSq(livingentity) < 100.0D;
			} else {
				return false;
			}
		}

		public void startExecuting() {
			this.tickCounter = 0;
		}

		public void tick() {
			++this.tickCounter;
			LivingEntity livingentity = GlowSquidEntity.this.getRevengeTarget();
			if (livingentity != null) {
				Vector3d vector3d = new Vector3d(GlowSquidEntity.this.getPosX() - livingentity.getPosX(), GlowSquidEntity.this.getPosY() - livingentity.getPosY(), GlowSquidEntity.this.getPosZ() - livingentity.getPosZ());
				BlockState blockstate = GlowSquidEntity.this.world.getBlockState(new BlockPos(GlowSquidEntity.this.getPosX() + vector3d.x, GlowSquidEntity.this.getPosY() + vector3d.y, GlowSquidEntity.this.getPosZ() + vector3d.z));
				FluidState fluidstate = GlowSquidEntity.this.world.getFluidState(new BlockPos(GlowSquidEntity.this.getPosX() + vector3d.x, GlowSquidEntity.this.getPosY() + vector3d.y, GlowSquidEntity.this.getPosZ() + vector3d.z));
				if (fluidstate.isTagged(FluidTags.WATER) || blockstate.isAir()) {
					double d0 = vector3d.length();
					if (d0 > 0.0D) {
						vector3d.normalize();
						float f = 3.0F;
						if (d0 > 5.0D) {
							f = (float) ((double) f - (d0 - 5.0D) / 5.0D);
						}

						if (f > 0.0F) {
							vector3d = vector3d.scale((double) f);
						}
					}

					if (blockstate.isAir()) {
						vector3d = vector3d.subtract(0.0D, vector3d.y, 0.0D);
					}

					GlowSquidEntity.this.setMovementVector((float) vector3d.x / 20.0F, (float) vector3d.y / 20.0F, (float) vector3d.z / 20.0F);
				}

				if (this.tickCounter % 10 == 5) {
					GlowSquidEntity.this.world.addParticle(ParticleTypes.BUBBLE, GlowSquidEntity.this.getPosX(), GlowSquidEntity.this.getPosY(), GlowSquidEntity.this.getPosZ(), 0.0D, 0.0D, 0.0D);
				}

			}
		}
	}

	class MoveRandomGoal extends Goal {
		private final GlowSquidEntity squid;

		public MoveRandomGoal(GlowSquidEntity p_i48823_2_) {
			this.squid = p_i48823_2_;
		}

		public boolean shouldExecute() {
			return true;
		}

		public void tick() {
			int i = this.squid.getIdleTime();
			if (i > 100) {
				this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
			} else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.hasMovementVector()) {
				float f = this.squid.getRNG().nextFloat() * ((float) Math.PI * 2F);
				float f1 = MathHelper.cos(f) * 0.2F;
				float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
				float f3 = MathHelper.sin(f) * 0.2F;
				this.squid.setMovementVector(f1, f2, f3);
			}

		}
	}
}
