package com.teamabnormals.upgrade_aquatic.common.entity.monster;

import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.stats.StatsCounter;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FlareEntity extends FlyingMob {
	private static final EntityDataAccessor<Integer> SIZE = SynchedEntityData.defineId(FlareEntity.class, EntityDataSerializers.INT);
	private Vec3 orbitOffset = Vec3.ZERO;
	private BlockPos orbitPosition = BlockPos.ZERO;
	private FlareEntity.AttackPhase attackPhase = FlareEntity.AttackPhase.CIRCLE;

	public FlareEntity(EntityType<? extends FlareEntity> type, Level world) {
		super(type, world);
		this.xpReward = 5;
		this.moveControl = new FlareEntity.MoveHelperController(this);
		this.lookControl = new FlareEntity.LookHelperController(this);
	}

	protected BodyRotationControl createBodyControl() {
		return new FlareEntity.BodyHelperController(this);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FlareEntity.PickAttackGoal());
		this.goalSelector.addGoal(2, new FlareEntity.SweepAttackGoal());
		this.goalSelector.addGoal(3, new FlareEntity.OrbitPointGoal());
		this.targetSelector.addGoal(1, new FlareEntity.AttackLivingEntityGoal());
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SIZE, 0);
	}

	public void setPhantomSize(int sizeIn) {
		this.entityData.set(SIZE, Mth.clamp(sizeIn, 0, 64));
	}

	private void updatePhantomSize() {
		this.refreshDimensions();
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((12 + this.getPhantomSize()));
	}

	public int getPhantomSize() {
		return this.entityData.get(SIZE);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return sizeIn.height * 0.35F;
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (SIZE.equals(key)) {
			this.updatePhantomSize();
		}

		super.onSyncedDataUpdated(key);
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	public void tick() {
		super.tick();
		if (this.level.isClientSide) {
			float f = Mth.cos((float) (this.getId() * 3 + this.tickCount) * 0.13F + (float) Math.PI);
			float f1 = Mth.cos((float) (this.getId() * 3 + this.tickCount + 1) * 0.13F + (float) Math.PI);
			if (f > 0.0F && f1 <= 0.0F) {
				this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
			}

			int i = this.getPhantomSize();
			float f2 = Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (1.3F + 0.21F * (float) i);
			float f3 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (1.3F + 0.21F * (float) i);
			float f4 = (0.3F + f * 0.45F) * ((float) i * 0.2F + 1.0F);
			this.level.addParticle(ParticleTypes.PORTAL, this.getX() + (double) f2, this.getY() + (double) f4, this.getZ() + (double) f3, 0.0D, 0.0D, 0.0D);
			this.level.addParticle(ParticleTypes.PORTAL, this.getX() - (double) f2, this.getY() + (double) f4, this.getZ() - (double) f3, 0.0D, 0.0D, 0.0D);
		}

		if (!this.level.isClientSide && this.level.getDifficulty() == Difficulty.PEACEFUL) {
			this.discard();
		}
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.orbitPosition = (new BlockPos(this.position())).above(5);
		this.setPhantomSize(0);
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("AX")) {
			this.orbitPosition = new BlockPos(compound.getInt("AX"), compound.getInt("AY"), compound.getInt("AZ"));
		}

		this.setPhantomSize(compound.getInt("Size"));
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("AX", this.orbitPosition.getX());
		compound.putInt("AY", this.orbitPosition.getY());
		compound.putInt("AZ", this.orbitPosition.getZ());
		compound.putInt("Size", this.getPhantomSize());
	}

	@OnlyIn(Dist.CLIENT)
	public boolean shouldRenderAtSqrDistance(double distance) {
		return true;
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.FLARE_SPAWN_EGG.get());
	}

	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.PHANTOM_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.PHANTOM_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.PHANTOM_DEATH;
	}

	protected float getSoundVolume() {
		return 1.0F;
	}

	public boolean canAttackType(EntityType<?> typeIn) {
		return true;
	}

	public EntityDimensions getDimensions(Pose poseIn) {
		int i = this.getPhantomSize();
		EntityDimensions entitysize = super.getDimensions(poseIn);
		float f = (entitysize.width + 0.2F * (float) i) / entitysize.width;
		return entitysize.scale(f);
	}

	enum AttackPhase {
		CIRCLE,
		SWOOP
	}

	public class AttackLivingEntityGoal extends Goal {
		private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0D);
		private int tickDelay = 20;

		private AttackLivingEntityGoal() {
		}

		public boolean canUse() {
			if (this.tickDelay > 0) {
				--this.tickDelay;
				return false;
			} else {
				this.tickDelay = 60;
				List<LivingEntity> list = FlareEntity.this.level.getNearbyEntities(LivingEntity.class, this.attackTargeting, FlareEntity.this, FlareEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
				if (!list.isEmpty()) {
					for (LivingEntity mob : list) {
						if (FlareEntity.this.canAttack(mob, TargetingConditions.DEFAULT)) {
							if (mob instanceof ServerPlayer) {
								StatsCounter statisticsManager = ((ServerPlayer) mob).getStats();
								if (statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
									FlareEntity.this.setTarget(mob);
									return true;
								}
							} else {
								if (!(mob instanceof FlareEntity)) {
									FlareEntity.this.setTarget(mob);
									return true;
								}
							}
						}
					}
				}

				return false;
			}
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = FlareEntity.this.getTarget();
			if (livingentity instanceof ServerPlayer) {
				StatsCounter statisticsManager = ((ServerPlayer) livingentity).getStats();
				if (statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 72000) {
					return FlareEntity.this.canAttack(livingentity, TargetingConditions.DEFAULT);
				}
			}
			return livingentity != null;
		}
	}

	class BodyHelperController extends BodyRotationControl {
		public BodyHelperController(Mob p_i49925_2_) {
			super(p_i49925_2_);
		}

		/**
		 * Update the Head and Body rendenring angles
		 */
		public void clientTick() {
			FlareEntity.this.yHeadRot = FlareEntity.this.yBodyRot;
			FlareEntity.this.yBodyRot = FlareEntity.this.getYRot();
		}
	}

	class LookHelperController extends LookControl {
		public LookHelperController(Mob entityIn) {
			super(entityIn);
		}

		/**
		 * Updates look
		 */
		public void tick() {
		}
	}

	abstract class MoveGoal extends Goal {
		public MoveGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		protected boolean touchingTarget() {
			return FlareEntity.this.orbitOffset.distanceToSqr(FlareEntity.this.getX(), FlareEntity.this.getY(), FlareEntity.this.getZ()) < 4.0D;
		}
	}

	class MoveHelperController extends MoveControl {
		private float speedFactor = 0.1F;

		public MoveHelperController(Mob entityIn) {
			super(entityIn);
		}

		public void tick() {
			if (FlareEntity.this.horizontalCollision) {
				FlareEntity.this.setYRot(FlareEntity.this.getYRot() + 180.0F);
				this.speedFactor = 0.1F;
			}

			float f = (float) (FlareEntity.this.orbitOffset.x - FlareEntity.this.getX());
			float f1 = (float) (FlareEntity.this.orbitOffset.y - FlareEntity.this.getY());
			float f2 = (float) (FlareEntity.this.orbitOffset.z - FlareEntity.this.getZ());
			double d0 = Mth.sqrt(f * f + f2 * f2);
			double d1 = 1.0D - (double) Mth.abs(f1 * 0.7F) / d0;
			f = (float) ((double) f * d1);
			f2 = (float) ((double) f2 * d1);
			d0 = Mth.sqrt(f * f + f2 * f2);
			double d2 = Mth.sqrt(f * f + f2 * f2 + f1 * f1);
			float f3 = FlareEntity.this.getYRot();
			float f4 = (float) Mth.atan2(f2, f);
			float f5 = Mth.wrapDegrees(FlareEntity.this.getYRot() + 90.0F);
			float f6 = Mth.wrapDegrees(f4 * (180F / (float) Math.PI));
			FlareEntity.this.setYRot(Mth.approachDegrees(f5, f6, 4.0F) - 90.0F);
			FlareEntity.this.yBodyRot = FlareEntity.this.getYRot();
			if (Mth.degreesDifferenceAbs(f3, FlareEntity.this.getYRot()) < 3.0F) {
				this.speedFactor = Mth.approach(this.speedFactor, 1.8F, 0.005F * (1.8F / this.speedFactor));
			} else {
				this.speedFactor = Mth.approach(this.speedFactor, 0.2F, 0.025F);
			}

			float f7 = (float) (-(Mth.atan2(-f1, d0) * (double) (180F / (float) Math.PI)));
			FlareEntity.this.setXRot(f7);
			float f8 = FlareEntity.this.getYRot() + 90.0F;
			double d3 = (double) (this.speedFactor * Mth.cos(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f / d2);
			double d4 = (double) (this.speedFactor * Mth.sin(f8 * ((float) Math.PI / 180F))) * Math.abs((double) f2 / d2);
			double d5 = (double) (this.speedFactor * Mth.sin(f7 * ((float) Math.PI / 180F))) * Math.abs((double) f1 / d2);
			Vec3 vec3d = FlareEntity.this.getDeltaMovement();
			FlareEntity.this.setDeltaMovement(vec3d.add((new Vec3(d3, d5, d4)).subtract(vec3d).scale(0.2D)));
		}
	}

	class OrbitPointGoal extends FlareEntity.MoveGoal {
		private float angle;
		private float distance;
		private float height;
		private float clockwise;

		private OrbitPointGoal() {
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			return FlareEntity.this.getTarget() == null || FlareEntity.this.attackPhase == FlareEntity.AttackPhase.CIRCLE;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			this.distance = 5.0F + FlareEntity.this.random.nextFloat() * 10.0F;
			this.height = -4.0F + FlareEntity.this.random.nextFloat() * 9.0F;
			this.clockwise = FlareEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
			this.selectNext();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (FlareEntity.this.random.nextInt(350) == 0) {
				this.height = -4.0F + FlareEntity.this.random.nextFloat() * 9.0F;
			}

			if (FlareEntity.this.random.nextInt(250) == 0) {
				++this.distance;
				if (this.distance > 15.0F) {
					this.distance = 5.0F;
					this.clockwise = -this.clockwise;
				}
			}

			if (FlareEntity.this.random.nextInt(450) == 0) {
				this.angle = FlareEntity.this.random.nextFloat() * 2.0F * (float) Math.PI;
				this.selectNext();
			}

			if (this.touchingTarget()) {
				this.selectNext();
			}

			if (FlareEntity.this.orbitOffset.y < FlareEntity.this.getY() && !FlareEntity.this.level.isEmptyBlock((new BlockPos(FlareEntity.this.position())).below(1))) {
				this.height = Math.max(1.0F, this.height);
				this.selectNext();
			}

			if (FlareEntity.this.orbitOffset.y > FlareEntity.this.getY() && !FlareEntity.this.level.isEmptyBlock((new BlockPos(FlareEntity.this.position())).above(1))) {
				this.height = Math.min(-1.0F, this.height);
				this.selectNext();
			}

		}

		private void selectNext() {
			if (BlockPos.ZERO.equals(FlareEntity.this.orbitPosition)) {
				FlareEntity.this.orbitPosition = new BlockPos(FlareEntity.this.position());
			}

			this.angle += this.clockwise * 15.0F * ((float) Math.PI / 180F);
			FlareEntity.this.orbitOffset = (new Vec3(FlareEntity.this.orbitPosition.getX(), FlareEntity.this.orbitPosition.getY(), FlareEntity.this.orbitPosition.getZ())).add(this.distance * Mth.cos(this.angle), -4.0F + this.height, this.distance * Mth.sin(this.angle));
		}
	}

	class PickAttackGoal extends Goal {
		private int tickDelay;

		private PickAttackGoal() {
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			LivingEntity livingentity = FlareEntity.this.getTarget();
			return livingentity != null && FlareEntity.this.canAttack(FlareEntity.this.getTarget(), TargetingConditions.DEFAULT);
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			this.tickDelay = 10;
			FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
			this.setAnchorAboveTarget();
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void stop() {
			FlareEntity.this.orbitPosition = FlareEntity.this.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, FlareEntity.this.orbitPosition).above(10 + FlareEntity.this.random.nextInt(20));
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (FlareEntity.this.attackPhase == FlareEntity.AttackPhase.CIRCLE) {
				--this.tickDelay;
				if (this.tickDelay <= 0) {
					FlareEntity.this.attackPhase = FlareEntity.AttackPhase.SWOOP;
					this.setAnchorAboveTarget();
					this.tickDelay = (8 + FlareEntity.this.random.nextInt(4)) * 20;
					FlareEntity.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + FlareEntity.this.random.nextFloat() * 0.1F);
				}
			}

		}

		private void setAnchorAboveTarget() {
			FlareEntity.this.orbitPosition = (new BlockPos(FlareEntity.this.getTarget().position())).above(20 + FlareEntity.this.random.nextInt(20));
			if (FlareEntity.this.orbitPosition.getY() < FlareEntity.this.level.getSeaLevel()) {
				FlareEntity.this.orbitPosition = new BlockPos(FlareEntity.this.orbitPosition.getX(), FlareEntity.this.level.getSeaLevel() + 1, FlareEntity.this.orbitPosition.getZ());
			}

		}
	}

	class SweepAttackGoal extends FlareEntity.MoveGoal {
		private SweepAttackGoal() {
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			return FlareEntity.this.getTarget() != null && FlareEntity.this.attackPhase == FlareEntity.AttackPhase.SWOOP;
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			LivingEntity livingentity = FlareEntity.this.getTarget();
			if (livingentity == null) {
				return false;
			} else if (!livingentity.isAlive()) {
				return false;
			} else if (!(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player) livingentity).isCreative()) {
				if (!this.canUse()) {
					return false;
				} else {
					if (FlareEntity.this.tickCount % 20 == 0) {
						List<Cat> list = FlareEntity.this.level.getEntitiesOfClass(Cat.class, FlareEntity.this.getBoundingBox().inflate(16.0D), EntitySelector.ENTITY_STILL_ALIVE);
						if (!list.isEmpty()) {
							for (Cat catentity : list) {
								catentity.hiss();
							}

							return false;
						}
					}

					return true;
				}
			} else {
				return false;
			}
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void stop() {
			if (FlareEntity.this.getTarget() instanceof Player) {
				FlareEntity.this.setTarget(null);
			}
			FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			LivingEntity livingentity = FlareEntity.this.getTarget();
			FlareEntity.this.orbitOffset = new Vec3(livingentity.getX(), livingentity.getY() + (double) livingentity.getBbHeight() * 0.5D, livingentity.getZ());
			if (FlareEntity.this.getBoundingBox().inflate(0.2F).intersects(livingentity.getBoundingBox())) {
				FlareEntity.this.doHurtTarget(livingentity);
				FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
				FlareEntity.this.level.levelEvent(1039, new BlockPos(FlareEntity.this.position()), 0);
			} else if (FlareEntity.this.horizontalCollision || FlareEntity.this.hurtTime > 0) {
				FlareEntity.this.attackPhase = FlareEntity.AttackPhase.CIRCLE;
			}

		}
	}
}
