package com.minecraftabnormals.upgrade_aquatic.common.entities;

import com.minecraftabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class NautilusEntity extends BucketableWaterMobEntity {
	private static final DataParameter<Boolean> MOVING = EntityDataManager.defineId(NautilusEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FLEEING = EntityDataManager.defineId(NautilusEntity.class, DataSerializers.BOOLEAN);

	public NautilusEntity(EntityType<? extends NautilusEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new NautilusEntity.MoveHelperController(this);
	}

	public NautilusEntity(World world, double posX, double posY, double posZ) {
		this(UAEntities.NAUTILUS.get(), world);
		this.setPos(posX, posY, posZ);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.65D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<PlayerEntity>(this, PlayerEntity.class, 9.0F, 1.5D, 1.2D, EntityPredicates.NO_SPECTATORS::test) {

			@Override
			public void start() {
				((NautilusEntity) this.mob).setFleeing(true);
				super.start();
			}

			@Override
			public void stop() {
				((NautilusEntity) this.mob).setFleeing(false);
				super.stop();
			}

		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<SquidEntity>(this, SquidEntity.class, 9.0F, 1.5D, 1.2D, EntityPredicates.NO_SPECTATORS::test) {

			@Override
			public void start() {
				((NautilusEntity) this.mob).setFleeing(true);
				super.start();
			}

			@Override
			public void stop() {
				((NautilusEntity) this.mob).setFleeing(false);
				super.stop();
			}

		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<ThrasherEntity>(this, ThrasherEntity.class, 9.0F, 1.5D, 1.2D, EntityPredicates.NO_SPECTATORS::test) {

			@Override
			public void start() {
				((NautilusEntity) this.mob).setFleeing(true);
				super.start();
			}

			@Override
			public void stop() {
				((NautilusEntity) this.mob).setFleeing(false);
				super.stop();
			}

		});
		this.goalSelector.addGoal(4, new NautilusEntity.SwimGoal(this));
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.COD_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.TURTLE_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.TURTLE_HURT;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MOVING, false);
		this.entityData.define(FLEEING, false);
	}

	public boolean isFleeing() {
		return this.entityData.get(FLEEING);
	}

	public void setFleeing(boolean p_203706_1_) {
		this.entityData.set(FLEEING, p_203706_1_);
	}

	public boolean isMoving() {
		return this.entityData.get(MOVING);
	}

	public void setMoving(boolean p_203706_1_) {
		this.entityData.set(MOVING, p_203706_1_);
	}

	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Moving", this.isMoving());
		compound.putBoolean("Fleeing", this.isMoving());
	}

	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setMoving(compound.getBoolean("Moving"));
		this.setMoving(compound.getBoolean("Fleeing"));
	}

	public ItemStack getBucket() {
		return new ItemStack(UAItems.NAUTILUS_BUCKET.get());
	}

	@Override
	public void aiStep() {
		if (this.isMoving() && this.isInWater() && this.wasEyeInWater) {
			Vector3d vec3d1 = this.getViewVector(0.0F);

			if (this.getCommandSenderWorld().getGameTime() % 2 == 0) {
				this.level.addParticle(ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.x * 0.75D, this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - vec3d1.y * 1D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.z * 0.75D, 0.0D, 0.0D, 0.0D);
			}
		}
		super.aiStep();
	}

	public void travel(Vector3d p_213352_1_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.01F, p_213352_1_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(p_213352_1_);
		}
	}

	@Override
	public void onInsideBubbleColumn(boolean downwards) {
	}

	@Override
	public void onAboveBubbleCol(boolean downwards) {
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.65F;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.NAUTILUS_SPAWN_EGG.get());
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 8;
	}

	static class MoveHelperController extends MovementController {
		private final NautilusEntity nautilus;

		MoveHelperController(NautilusEntity nautilus) {
			super(nautilus);
			this.nautilus = nautilus;
		}

		public void tick() {
			if (this.nautilus.isEyeInFluid(FluidTags.WATER)) {
				this.nautilus.setDeltaMovement(this.nautilus.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.nautilus.getNavigation().isDone()) {
				double d0 = this.wantedX - this.nautilus.getX();
				double d1 = this.wantedY - this.nautilus.getY();
				double d2 = this.wantedZ - this.nautilus.getZ();
				double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 = d1 / d3;
				float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.nautilus.yRot = this.rotlerp(this.nautilus.yRot, f, 90.0F);
				this.nautilus.yBodyRot = this.nautilus.yRot;
				float f1 = (float) (this.speedModifier * this.nautilus.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				this.nautilus.setSpeed(MathHelper.lerp(0.125F, this.nautilus.getSpeed(), f1));
				this.nautilus.setDeltaMovement(this.nautilus.getDeltaMovement().add(0.0D, (double) this.nautilus.getSpeed() * d1 * 0.03D, 0.0D));
				nautilus.setMoving(true);
			} else {
				this.nautilus.setSpeed(0.0F);
				nautilus.setMoving(false);
			}
		}
	}

	static class SwimGoal extends RandomSwimmingGoal {
		public final NautilusEntity nautilus;

		public SwimGoal(NautilusEntity nautilus) {
			super(nautilus, 1.0D, 30);
			this.nautilus = nautilus;
		}

		public boolean canUse() {
			return super.canUse();
		}
	}
}