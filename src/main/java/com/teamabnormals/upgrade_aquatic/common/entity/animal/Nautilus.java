package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.teamabnormals.blueprint.common.entity.BucketableWaterAnimal;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Nautilus extends BucketableWaterAnimal {
	private static final EntityDataAccessor<Boolean> MOVING = SynchedEntityData.defineId(Nautilus.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> FLEEING = SynchedEntityData.defineId(Nautilus.class, EntityDataSerializers.BOOLEAN);

	public Nautilus(EntityType<? extends Nautilus> type, Level worldIn) {
		super(type, worldIn);
		this.moveControl = new Nautilus.MoveHelperController(this);
	}

	public Nautilus(Level world, double posX, double posY, double posZ) {
		this(UAEntityTypes.NAUTILUS.get(), world);
		this.setPos(posX, posY, posZ);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.65D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 9.0F, 1.5D, 1.2D, EntitySelector.NO_SPECTATORS::test) {

			@Override
			public void start() {
				((Nautilus) this.mob).setFleeing(true);
				super.start();
			}

			@Override
			public void stop() {
				((Nautilus) this.mob).setFleeing(false);
				super.stop();
			}

		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<Squid>(this, Squid.class, 9.0F, 1.5D, 1.2D, EntitySelector.NO_SPECTATORS::test) {

			@Override
			public void start() {
				((Nautilus) this.mob).setFleeing(true);
				super.start();
			}

			@Override
			public void stop() {
				((Nautilus) this.mob).setFleeing(false);
				super.stop();
			}

		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<Thrasher>(this, Thrasher.class, 9.0F, 1.5D, 1.2D, EntitySelector.NO_SPECTATORS::test) {

			@Override
			public void start() {
				((Nautilus) this.mob).setFleeing(true);
				super.start();
			}

			@Override
			public void stop() {
				((Nautilus) this.mob).setFleeing(false);
				super.stop();
			}

		});
		this.goalSelector.addGoal(4, new Nautilus.SwimGoal(this));
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new WaterBoundPathNavigation(this, worldIn);
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

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Moving", this.isMoving());
		compound.putBoolean("Fleeing", this.isMoving());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setMoving(compound.getBoolean("Moving"));
		this.setMoving(compound.getBoolean("Fleeing"));
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(UAItems.NAUTILUS_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

	@Override
	public void aiStep() {
		if (this.isMoving() && this.isInWater() && this.wasEyeInWater) {
			Vec3 vec3d1 = this.getViewVector(0.0F);

			if (this.getCommandSenderWorld().getGameTime() % 2 == 0) {
				this.level.addParticle(ParticleTypes.BUBBLE, this.getX() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.x * 0.75D, this.getY() + this.random.nextDouble() * (double) this.getBbHeight() - vec3d1.y * 1D, this.getZ() + (this.random.nextDouble() - 0.5D) * (double) this.getBbWidth() - vec3d1.z * 0.75D, 0.0D, 0.0D, 0.0D);
			}
		}
		super.aiStep();
	}

	public void travel(Vec3 p_213352_1_) {
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
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.65F;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.NAUTILUS_SPAWN_EGG.get());
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 8;
	}

	static class MoveHelperController extends MoveControl {
		private final Nautilus nautilus;

		MoveHelperController(Nautilus nautilus) {
			super(nautilus);
			this.nautilus = nautilus;
		}

		public void tick() {
			if (this.nautilus.isEyeInFluid(FluidTags.WATER)) {
				this.nautilus.setDeltaMovement(this.nautilus.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.nautilus.getNavigation().isDone()) {
				double d0 = this.wantedX - this.nautilus.getX();
				double d1 = this.wantedY - this.nautilus.getY();
				double d2 = this.wantedZ - this.nautilus.getZ();
				double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
				d1 = d1 / d3;
				float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.nautilus.setYRot(this.rotlerp(this.nautilus.getYRot(), f, 90.0F));
				this.nautilus.yBodyRot = this.nautilus.getYRot();
				float f1 = (float) (this.speedModifier * this.nautilus.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				this.nautilus.setSpeed(Mth.lerp(0.125F, this.nautilus.getSpeed(), f1));
				this.nautilus.setDeltaMovement(this.nautilus.getDeltaMovement().add(0.0D, (double) this.nautilus.getSpeed() * d1 * 0.03D, 0.0D));
				nautilus.setMoving(true);
			} else {
				this.nautilus.setSpeed(0.0F);
				nautilus.setMoving(false);
			}
		}
	}

	static class SwimGoal extends RandomSwimmingGoal {
		public final Nautilus nautilus;

		public SwimGoal(Nautilus nautilus) {
			super(nautilus, 1.0D, 30);
			this.nautilus = nautilus;
		}

		public boolean canUse() {
			return super.canUse();
		}
	}
}