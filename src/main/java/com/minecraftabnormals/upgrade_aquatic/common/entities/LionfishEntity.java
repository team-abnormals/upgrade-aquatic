package com.minecraftabnormals.upgrade_aquatic.common.entities;

import com.minecraftabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.BlockTags;
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

import java.util.Random;
import java.util.function.Predicate;

public class LionfishEntity extends BucketableWaterMobEntity {
	private static final Predicate<LivingEntity> ENEMY_MATCHER = (entity) -> {
		if (entity == null) {
			return false;
		} else {
			return !(entity instanceof LionfishEntity) && !(entity instanceof AbstractFishEntity);
		}
	};
	private static final DataParameter<Boolean> HUNGY = EntityDataManager.defineId(LionfishEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_TILL_HUNGRY = EntityDataManager.defineId(LionfishEntity.class, DataSerializers.INT);
	int lastTimeSinceHungry;

	public LionfishEntity(EntityType<? extends LionfishEntity> type, World world) {
		super(UAEntities.LIONFISH.get(), world);
		this.moveControl = new LionfishEntity.MoveHelperController(this);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D);
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
						if (((LionfishEntity) this.mob).isHungry()) {
							if (this.mob.getRandom().nextInt(60) != 0) {
								return false;
							}
						} else {
							if (this.mob.getRandom().nextInt(30) != 0) {
								return false;
							}
						}
					}

					Vector3d vec3d = this.getPosition();
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
		this.goalSelector.addGoal(4, new LionfishEntity.LionfishAttackGoal(this, 12D, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<TropicalFishEntity>(this, TropicalFishEntity.class, true) {

			@Override
			public boolean canUse() {
				return ((LionfishEntity) this.mob).isHungry() && super.canUse();
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

	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsHungry", this.isHungry());
		compound.putInt("TimeTillHungry", this.getTimeTillHungry());
	}

	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setHungry(compound.getBoolean("IsHungry"));
		this.setTimeTillHungry(compound.getInt("TimeTillHungry"));
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.85F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(UAItems.LIONFISH_SPAWN_EGG.get());
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.LIONFISH_BUCKET.get());
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	public static boolean coralCondition(EntityType<? extends Entity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
		if (((World) world).dimension() != World.OVERWORLD) return false;
		for (int yy = pos.getY() - 2; yy <= pos.getY() + 2; yy++) {
			for (int xx = pos.getX() - 6; xx <= pos.getX() + 6; xx++) {
				for (int zz = pos.getZ() - 6; zz <= pos.getZ() + 6; zz++) {
					if (world.getBlockState(new BlockPos(xx, yy, zz)).getBlock().is(BlockTags.CORAL_BLOCKS)) {
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
			entity.addEffect(new EffectInstance(Effects.POISON, 70, 1));
			this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
			if (entity instanceof PlayerEntity) {
				this.setTarget(entity);
			}
		}
	}

	public boolean hurt(DamageSource source, float amount) {
		Entity entitySource = source.getEntity();
		if (entitySource instanceof LivingEntity && !(entitySource instanceof PlayerEntity && ((PlayerEntity) entitySource).abilities.instabuild)) {
			if (entitySource instanceof PlayerEntity) {
				this.setTarget((LivingEntity) entitySource);
			}
			return super.hurt(source, amount);
		} else {
			return super.hurt(source, amount);
		}
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

	static class MoveHelperController extends MovementController {
		private final LionfishEntity lionfish;

		MoveHelperController(LionfishEntity lionfish) {
			super(lionfish);
			this.lionfish = lionfish;
		}

		public void tick() {
			if (this.lionfish.isEyeInFluid(FluidTags.WATER)) {
				this.lionfish.setDeltaMovement(this.lionfish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.lionfish.getNavigation().isDone()) {
				double d0 = this.wantedX - this.lionfish.getX();
				double d1 = this.wantedY - this.lionfish.getY();
				double d2 = this.wantedZ - this.lionfish.getZ();
				double d3 = MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 = d1 / d3;
				float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.lionfish.yRot = this.rotlerp(this.lionfish.yRot, f, 90.0F);
				this.lionfish.yBodyRot = this.lionfish.yRot;
				float f1 = (float) (this.speedModifier * this.lionfish.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
				this.lionfish.setSpeed(MathHelper.lerp(0.125F, this.lionfish.getSpeed(), f1));
				this.lionfish.setDeltaMovement(this.lionfish.getDeltaMovement().add(0.0D, (double) this.lionfish.getSpeed() * d1 * 0.03D, 0.0D));
			}
		}
	}

	static class LionfishAttackGoal extends MeleeAttackGoal {

		public LionfishAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
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
				((LionfishEntity) this.mob).attack(enemy);
				((LionfishEntity) this.mob).setHungry(false);
				((LionfishEntity) this.mob).setTimeTillHungry(mob.getRandom().nextInt(300) + 300);
				if (enemy instanceof PlayerEntity) {
					mob.setTarget(null);
					this.stop();
				}
			}
		}

	}

}