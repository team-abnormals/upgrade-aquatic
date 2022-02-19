package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.network.RotateJellyfishMessage;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADamageSources;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UASounds;
import com.teamabnormals.blueprint.common.entity.BucketableWaterAnimal;
import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.blueprint.core.endimator.entity.IEndimatedEntity;
import com.teamabnormals.blueprint.core.util.MathUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import java.util.Random;
import java.util.function.Predicate;

/**
 * @author SmellyModder(Luke Tonon)
 */
public abstract class AbstractJellyfishEntity extends BucketableWaterAnimal implements IEndimatedEntity {
	public static final Endimation SWIM_ANIMATION = new Endimation(20);
	public static final Endimation BOOST_ANIMATION = new Endimation(20);
	protected static final EntityDataAccessor<Integer> COOLDOWN = SynchedEntityData.defineId(AbstractJellyfishEntity.class, EntityDataSerializers.INT);
	private static final Predicate<LivingEntity> CAN_STING = (entity) -> {
		if (entity instanceof Player) {
			return !entity.isSpectator() && !((Player) entity).isCreative();
		}
		return !entity.isSpectator() && !(entity instanceof AbstractJellyfishEntity || entity instanceof Turtle);
	};
	public float[] lockedRotations = new float[2];
	private Endimation playingEndimation = BLANK_ANIMATION;
	private int animationTick;

	public AbstractJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, Level world) {
		super(type, world);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@SuppressWarnings("deprecation")
	public static <J extends AbstractJellyfishEntity> boolean defaultSpawnCondition(EntityType<J> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
		return pos.getY() > 45 && pos.getY() < world.getSeaLevel();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COOLDOWN, 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setCooldown(compound.getInt("CooldownTicks"));
		this.lockedRotations[0] = compound.getFloat("LockedYaw");
		this.lockedRotations[1] = compound.getFloat("LockedPitch");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("CooldownTicks", this.getCooldown());
		compound.putFloat("LockedYaw", this.lockedRotations[0]);
		compound.putFloat("LockedPitch", this.lockedRotations[1]);
	}

	@Override
	public void tick() {
		super.tick();
		this.endimateTick();
		this.getRotationController().tick();

		this.setYRot(0);
		this.yHeadRot = this.yBodyRot = 0;

		if (this.isEffectiveAi()) {
			if (!this.isPassenger()) {
				this.getRotationController().rotate(this.lockedRotations[0], this.lockedRotations[1], 25);
			} else {
				this.getRotationController().rotate(0.0F, 0.0F, 1);
				this.setAirSupply(this.getMaxAirSupply());
			}
		}

		if (this.hasCooldown()) {
			if (this.isEffectiveAi()) {
				this.setCooldown(this.getCooldown() - 1);
				if (!this.hasCooldown()) {
					this.playSound(UASounds.ENTITY_JELLYFISH_COOLDOWN_END.get(), 1.0F, this.random.nextFloat() * 0.15F + 1.0F);
				}
			}

			if (this.level.isClientSide && this.level.getGameTime() % 4 == 0) {
				for (int i = 0; i < 2; i++) {
					this.level.addParticle(JellyTorchType.getBlobParticleType(this.getJellyTorchType()), this.getRandomX(0.5D), this.getY() + this.getEyeHeight(), this.getRandomZ(0.5D), MathUtil.makeNegativeRandomly(this.random.nextDouble() * 0.05F, this.getRandom()), -this.random.nextDouble() * 0.05F, MathUtil.makeNegativeRandomly(this.random.nextDouble() * 0.05F, this.getRandom()));
				}
			}
		}

		if (this.isAlive()) {
			for (LivingEntity entities : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.15D), CAN_STING)) {
				if (entities.isAlive()) {
					this.stingEntity(entities);
				}
			}

			if (this.isInWater() && this.isNoEndimationPlaying()) {
				this.setDeltaMovement(this.getDeltaMovement().subtract(0.0F, 0.005F, 0.0F));
			}
		}
	}

	@Override
	public void travel(Vec3 Vector3d) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(0.01F, Vector3d);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.75D));
		} else {
			super.travel(Vector3d);
		}
	}

	//TODO: Rework this
	public boolean willBeBoostedOutOfWater(float yaw, float pitch) {
		yaw = yaw + this.getYRot();
		pitch = pitch - 90.0F;
		float x = -Mth.sin(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F));
		float y = -Mth.sin(pitch * ((float) Math.PI / 180F));
		float z = Mth.cos(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F));
		Vec3 motion = new Vec3(x, y, z).normalize();
		return this.level.isEmptyBlock(new BlockPos(this.position().add(motion)));
	}

	@Override
	public PathNavigation getNavigation() {
		return new WaterBoundPathNavigation(this, this.level);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();
		if (item == Items.WATER_BUCKET && this.isAlive()) {
			this.playSound(UASounds.ITEM_BUCKET_FILL_JELLYFISH.get(), 1.0F, 1.0F);
			itemstack.shrink(1);
			ItemStack bucket = this.getBucket();
			this.setBucketData(bucket);
			if (!this.level.isClientSide) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
			}

			if (itemstack.isEmpty()) {
				player.setItemInHand(hand, bucket);
			} else if (!player.getInventory().add(bucket)) {
				player.drop(bucket, false);
			}

			this.discard();
			return InteractionResult.SUCCESS;
		} else if (itemstack.isEmpty() && this.getName().getString().toLowerCase().trim().equals("jellysox345")) {
			this.startRiding(player);
			return InteractionResult.SUCCESS;
		} else if (item == UAItems.PRISMARINE_ROD.get() && !this.hasCooldown()) {
			Random rand = new Random();
			if (this.isEffectiveAi() && rand.nextFloat() < this.getCooldownChance()) {
				this.setCooldown(20 * (rand.nextInt(16) + 15));
				this.playSound(UASounds.ENTITY_JELLYFISH_COOLDOWN_START.get(), 1.0F, this.random.nextFloat() * 0.15F + 1.0F);
			}
			itemstack.shrink(1);
			player.addItem(this.getTorchByType(this.getJellyTorchType()));
			this.playSound(UASounds.ENTITY_JELLYFISH_HARVEST.get(), 1.0F, this.random.nextFloat() * 0.15F + 1.0F);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? UASounds.ENTITY_JELLYFISH_AMBIENT.get() : null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return UASounds.ENTITY_JELLYFISH_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UASounds.ENTITY_JELLYFISH_DEATH.get();
	}

	@Override
	public void playAmbientSound() {
		SoundEvent soundevent = this.getAmbientSound();
		if (soundevent != null) {
			this.playSound(soundevent, 0.25F, this.getVoicePitch());
		}
	}

	@Override
	public int getAmbientSoundInterval() {
		return this.random.nextInt(200) + 200;
	}

	@Override
	protected void setBucketData(ItemStack bucket) {
		if (this.hasCustomName()) {
			bucket.setHoverName(this.getCustomName());
		}
		bucket.getOrCreateTag().put("JellyfishTag", this.getBucketProcessor().write());
	}

	public int getCooldown() {
		return this.entityData.get(COOLDOWN);
	}

	public void setCooldown(int ticks) {
		this.entityData.set(COOLDOWN, ticks);
	}

	public boolean hasCooldown() {
		return this.getCooldown() > 0;
	}

	@Override
	public Endimation getPlayingEndimation() {
		return this.playingEndimation;
	}

	@Override
	public void setPlayingEndimation(Endimation endimationToPlay) {
		this.onEndimationEnd(this.playingEndimation);
		this.playingEndimation = endimationToPlay;
		this.setAnimationTick(0);
	}

	@Override
	public int getAnimationTick() {
		return this.animationTick;
	}

	@Override
	public void setAnimationTick(int animationTick) {
		this.animationTick = animationTick;
	}

	@Override
	public Endimation[] getEndimations() {
		return new Endimation[]{
				SWIM_ANIMATION,
				BOOST_ANIMATION
		};
	}

	@Override
	public ItemStack getBucket() {
		return new ItemStack(UAItems.JELLYFISH_BUCKET.get());
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.JELLYFISH_SPAWN_EGG.get());
	}

	public abstract RotationController getRotationController();

	public abstract BucketProcessor<?> getBucketProcessor();

	public abstract String getBucketName();

	public abstract JellyTorchType getJellyTorchType();

	public abstract float getCooldownChance();

	public int getIdSuffix() {
		return this.getJellyTorchType().ordinal();
	}

	protected boolean stingEntity(LivingEntity livingEntity) {
		if (livingEntity.hurt(UADamageSources.causeJellyfishDamage(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue())) {
			this.playSound(UASounds.ENTITY_JELLYFISH_STING.get(), 0.5F, this.random.nextFloat() * 0.2F + 1.0F);
			return true;
		}
		return false;
	}

	public Component getYieldingTorchMessage() {
		JellyTorchType torchType = this.getJellyTorchType();
		return (new TranslatableComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").withStyle(ChatFormatting.GRAY)).append((new TranslatableComponent("tooltip.upgrade_aquatic." + torchType.toString().toLowerCase() + "_jelly_torch")).withStyle(torchType.color));
	}

	protected ItemStack getTorchByType(JellyTorchType type) {
		return new ItemStack(type.torch.get());
	}

	public static class RotationController {
		public boolean rotating;
		private final AbstractJellyfishEntity jellyfish;
		private float prevYaw, yaw, startingYaw;
		private float prevPitch, pitch, startingPitch;
		private float setYaw, setPitch;
		private int tickLength;
		private int ticksSinceNotRotating;

		RotationController(AbstractJellyfishEntity jellyfish) {
			this.jellyfish = jellyfish;
			this.startingPitch = jellyfish.getXRot();
			this.startingYaw = jellyfish.getYRot();
			this.tickLength = 1;
		}

		protected void tick() {
			this.prevYaw = this.yaw;
			this.prevPitch = this.pitch;

			if (!this.rotating) {
				this.ticksSinceNotRotating++;

				if (this.ticksSinceNotRotating > 5) {
					if (this.setYaw != 0.0F) this.startingYaw = this.yaw;
					if (this.setPitch != 0.0F) this.startingPitch = this.pitch;

					this.setYaw = 0.0F;
					this.setPitch = 0.0F;
					this.tickLength = 20;
				}
			}

			this.yaw = this.clamp((this.setYaw - this.startingYaw) <= 0, this.yaw + ((this.setYaw - this.startingYaw) / this.tickLength), this.startingYaw, this.setYaw);
			this.pitch = this.clamp((this.setPitch - this.startingPitch) <= 0, this.pitch + ((this.setPitch - this.startingPitch) / this.tickLength), this.startingPitch, this.setPitch);
			this.rotating = false;
		}

		private float clamp(boolean invert, float num, float min, float max) {
			if (invert) {
				return Math.max(num, max);
			} else {
				if (num < min) {
					return min;
				} else {
					return Math.min(num, max);
				}
			}
		}

		public void rotate(float yaw, float pitch, int tickLength) {
			if (this.setYaw != yaw) this.startingYaw = this.yaw;
			if (this.setPitch != pitch) this.startingPitch = this.pitch;

			this.setYaw = yaw;
			this.setPitch = pitch;
			this.tickLength = tickLength;
			this.rotating = true;
			this.ticksSinceNotRotating = 0;

			if (!this.jellyfish.level.isClientSide) {
				UpgradeAquatic.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.jellyfish), new RotateJellyfishMessage(this.jellyfish.getId(), tickLength, yaw, pitch));
			}
		}

		public float[] getRotations(float ptc) {
			return new float[]{Mth.lerp(ptc, this.prevYaw, this.yaw), Mth.lerp(ptc, this.prevPitch, this.pitch)};
		}

		public void addVelocityForLookDirection(float force, float sizeScale) {
			float[] rotations = this.getRotations(1.0F);
			float yaw = rotations[0] + this.jellyfish.getYRot();
			float pitch = rotations[1] - 90.0F;

			float x = -Mth.sin(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F));
			float y = -Mth.sin(pitch * ((float) Math.PI / 180F));
			float z = Mth.cos(yaw * ((float) Math.PI / 180F)) * Mth.cos(pitch * ((float) Math.PI / 180F));

			Vec3 motion = new Vec3(x, y, z).normalize().scale(force).scale(sizeScale);

			this.jellyfish.push(motion.x, motion.y, motion.z);
		}
	}

	public static class BucketProcessor<J extends AbstractJellyfishEntity> {
		protected final J jellyfish;
		private final String entityId;

		public BucketProcessor(String entityId, J jellyfish) {
			this.entityId = entityId;
			this.jellyfish = jellyfish;
		}

		public CompoundTag write() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("EntityId", this.entityId);
			return nbt;
		}

		public void read(CompoundTag nbt) {
		}
	}
}