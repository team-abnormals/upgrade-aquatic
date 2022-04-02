package com.teamabnormals.upgrade_aquatic.common.entity.animal;

import com.teamabnormals.blueprint.common.entity.BucketableWaterAnimal;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;
import java.util.function.Predicate;

public class Lionfish extends BucketableWaterAnimal {
	private static final Predicate<LivingEntity> ENEMY_MATCHER = (entity) -> {
		if (entity == null) {
			return false;
		} else {
			return !(entity instanceof Lionfish) && !(entity instanceof AbstractFish);
		}
	};
	private static final EntityDataAccessor<Boolean> HUNGY = SynchedEntityData.defineId(Lionfish.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> TIME_TILL_HUNGRY = SynchedEntityData.defineId(Lionfish.class, EntityDataSerializers.INT);
	int lastTimeSinceHungry;

	public Lionfish(EntityType<? extends Lionfish> type, Level world) {
		super(UAEntityTypes.LIONFISH.get(), world);
		this.moveControl = new Lionfish.MoveHelperController(this);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.35D, 30) {

			@Override
			public boolean canUse() {
				if (this.mob.isVehicle()) {
					return false;
				} else {
					if (!this.forceTrigger) {
						if (this.mob.getNoActionTime() >= 100) {
							return false;
						}
						if (((Lionfish) this.mob).isHungry()) {
							if (this.mob.getRandom().nextInt(60) != 0) {
								return false;
							}
						} else {
							if (this.mob.getRandom().nextInt(30) != 0) {
								return false;
							}
						}
					}

					Vec3 vec3d = this.getPosition();
					if (vec3d == null) {
						return false;
					} else {
						this.wantedX = vec3d.x;
						this.wantedY = vec3d.y;
						this.wantedZ = vec3d.z;
						this.forceTrigger = false;
						return true;
					}
				}
			}

		});
		this.goalSelector.addGoal(4, new Lionfish.LionfishAttackGoal(this, 12D, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<TropicalFish>(this, TropicalFish.class, true) {

			@Override
			public boolean canUse() {
				return ((Lionfish) this.mob).isHungry() && super.canUse();
			}

		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HUNGY, true);
		this.entityData.define(TIME_TILL_HUNGRY, 0);
	}

	public boolean isHungry() {
		return this.entityData.get(HUNGY);
	}

	public void setHungry(boolean hungry) {
		this.entityData.set(HUNGY, hungry);
	}

	public int getTimeTillHungry() {
		return this.entityData.get(TIME_TILL_HUNGRY);
	}

	public void setTimeTillHungry(int ticks) {
		this.entityData.set(TIME_TILL_HUNGRY, ticks);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsHungry", this.isHungry());
		compound.putInt("TimeTillHungry", this.getTimeTillHungry());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setHungry(compound.getBoolean("IsHungry"));
		this.setTimeTillHungry(compound.getInt("TimeTillHungry"));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.85F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.LIONFISH_SPAWN_EGG.get());
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(UAItems.LIONFISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new WaterBoundPathNavigation(this, worldIn);
	}

	public static boolean coralCondition(EntityType<? extends Entity> entityType, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, Random random) {
		if (((Level) world).dimension() != Level.OVERWORLD) return false;
		for (int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for (int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for (int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if (world.getBlockState(new BlockPos(xx, yy, zz)).is(BlockTags.CORAL_BLOCKS)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.isInWater() && this.onGround && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.035F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.035F));
			this.onGround = false;
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}
		if (this.isAlive()) {
			for (LivingEntity entity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.3D), ENEMY_MATCHER)) {
				if (entity.isAlive()) {
					this.attack(entity);
				}
			}
		}
		if (!this.isHungry() && lastTimeSinceHungry < this.getTimeTillHungry()) {
			lastTimeSinceHungry++;
		}
		if (lastTimeSinceHungry >= this.getTimeTillHungry()) {
			this.setHungry(true);
			lastTimeSinceHungry = 0;
		}
	}

	private void attack(LivingEntity entity) {
		if (entity.hurt(DamageSource.mobAttack(this), 2.0F) && entity.isInWater()) {
			entity.addEffect(new MobEffectInstance(MobEffects.POISON, 70, 1));
			this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
			if (entity instanceof Player) {
				this.setTarget(entity);
			}
		}
	}

	public boolean hurt(DamageSource source, float amount) {
		Entity entitySource = source.getEntity();
		if (entitySource instanceof LivingEntity && !(entitySource instanceof Player && ((Player) entitySource).getAbilities().instabuild)) {
			if (entitySource instanceof Player) {
				this.setTarget((LivingEntity) entitySource);
			}
		}
		return super.hurt(source, amount);
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
	protected SoundEvent getAmbientSound() {
		return SoundEvents.PUFFER_FISH_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.PUFFER_FISH_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.PUFFER_FISH_HURT;
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.PUFFER_FISH_FLOP;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	static class MoveHelperController extends MoveControl {
		private final Lionfish lionfish;

		MoveHelperController(Lionfish lionfish) {
			super(lionfish);
			this.lionfish = lionfish;
		}

		public void tick() {
			if (this.lionfish.isEyeInFluid(FluidTags.WATER)) {
				this.lionfish.setDeltaMovement(this.lionfish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.lionfish.getNavigation().isDone()) {
				double d0 = this.wantedX - this.lionfish.getX();
				double d1 = this.wantedY - this.lionfish.getY();
				double d2 = this.wantedZ - this.lionfish.getZ();
				double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
				d1 = d1 / d3;
				float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.lionfish.setYRot(this.rotlerp(this.lionfish.getYRot(), f, 90.0F));
				this.lionfish.yBodyRot = this.lionfish.getYRot();
				float f1 = (float) (this.speedModifier * this.lionfish.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				this.lionfish.setSpeed(Mth.lerp(0.125F, this.lionfish.getSpeed(), f1));
				this.lionfish.setDeltaMovement(this.lionfish.getDeltaMovement().add(0.0D, (double) this.lionfish.getSpeed() * d1 * 0.03D, 0.0D));
			}
		}
	}

	static class LionfishAttackGoal extends MeleeAttackGoal {

		public LionfishAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && mob.isInWater();
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
				this.resetAttackCooldown();
				((Lionfish) this.mob).attack(enemy);
				((Lionfish) this.mob).setHungry(false);
				((Lionfish) this.mob).setTimeTillHungry(mob.getRandom().nextInt(300) + 300);
				if (enemy instanceof Player) {
					mob.setTarget(null);
					this.stop();
				}
			}
		}

	}

}