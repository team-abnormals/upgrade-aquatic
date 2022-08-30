package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish;

import com.teamabnormals.blueprint.common.entity.BucketableWaterAnimal;
import com.teamabnormals.blueprint.core.endimator.Endimatable;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.other.UADamageSources;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
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

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author SmellyModder (Luke Tonon)
 */
public abstract class AbstractJellyfish extends BucketableWaterAnimal implements Endimatable {
	protected static final EntityDataAccessor<Integer> COOLDOWN = SynchedEntityData.defineId(AbstractJellyfish.class, EntityDataSerializers.INT);

	private static final Predicate<LivingEntity> CAN_STING = (entity) -> {
		if (entity instanceof Player && ((Player) entity).isCreative()) return false;
		return !entity.isSpectator() && !(entity instanceof AbstractJellyfish || entity instanceof Turtle);
	};

	public AbstractJellyfish(EntityType<? extends AbstractJellyfish> type, Level world) {
		super(type, world);
		this.lookControl = new JellyfishLookControl(this);
		this.moveControl = new SmoothSwimmingMoveControl(this, 45, 10, 1.0F, 0.0F, true);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 1.25F).add(Attributes.ATTACK_DAMAGE, 2.0D);
	}

	@SuppressWarnings("deprecation")
	public static <J extends AbstractJellyfish> boolean defaultSpawnCondition(EntityType<J> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
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
		this.readAdditionalSaveDataSharedWithBucket(compound);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		this.addAdditionalSaveDataSharedWithBucket(compound);
	}

	protected void readAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		this.setCooldown(compoundTag.getInt("CooldownTicks"));
	}

	protected void addAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		compoundTag.putInt("CooldownTicks", this.getCooldown());
	}

	@Override
	public void tick() {
		super.tick();

		if (this.isEffectiveAi()) {
			if (this.isPassenger()) {
				this.setYRot(0.0F);
				this.setXRot(0.0F);
				this.setAirSupply(this.getMaxAirSupply());
			} else if (this.isInWater() && this.getNavigation().isDone()) {
				float yaw = this.getYRot();
				float pitch = this.getXRot();
				float pitchFactor = Mth.cos(pitch * ((float) Math.PI / 180F));
				float x = -Mth.sin(yaw * ((float) Math.PI / 180F)) * pitchFactor;
				float y = -Mth.sin(pitch * ((float) Math.PI / 180F));
				float z = Mth.cos(yaw * ((float) Math.PI / 180F)) * pitchFactor;
				Vec3 motion = new Vec3(x, y, z).normalize().scale(0.01666666666F);
				this.push(motion.x, motion.y, motion.z);
			}

			if (!this.level.isClientSide && (Mth.abs(this.xRotO - this.getXRot()) >= 1.0F || Mth.abs(this.yRotO - this.getYRot()) >= 1.0F)) {
				this.hasImpulse = true;
			}
		}

		if (this.hasCooldown()) {
			if (this.isEffectiveAi()) {
				this.setCooldown(this.getCooldown() - 1);
				if (!this.hasCooldown()) {
					this.playSound(UASoundEvents.ENTITY_JELLYFISH_COOLDOWN_END.get(), 1.0F, this.random.nextFloat() * 0.15F + 1.0F);
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

	@Override
	protected PathNavigation createNavigation(Level level) {
		return new WaterBoundPathNavigation(this, level);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();
		if (item == Items.WATER_BUCKET && this.isAlive()) {
			this.playSound(UASoundEvents.ITEM_BUCKET_FILL_JELLYFISH.get(), 1.0F, 1.0F);
			itemstack.shrink(1);
			ItemStack bucket = this.getBucketItemStack();
			this.saveToBucketTag(bucket);
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
				this.playSound(UASoundEvents.ENTITY_JELLYFISH_COOLDOWN_START.get(), 1.0F, this.random.nextFloat() * 0.15F + 1.0F);
			}
			itemstack.shrink(1);
			player.addItem(this.getTorchByType(this.getJellyTorchType()));
			this.playSound(UASoundEvents.ENTITY_JELLYFISH_HARVEST.get(), 1.0F, this.random.nextFloat() * 0.15F + 1.0F);
			return InteractionResult.SUCCESS;
		}
		return super.mobInteract(player, hand);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? UASoundEvents.ENTITY_JELLYFISH_AMBIENT.get() : null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return UASoundEvents.ENTITY_JELLYFISH_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return UASoundEvents.ENTITY_JELLYFISH_DEATH.get();
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
	@SuppressWarnings("deprecation")
	public void saveToBucketTag(ItemStack bucket) {
		super.saveToBucketTag(bucket);
		CompoundTag compoundTag = bucket.getOrCreateTag();
		compoundTag.putString("EntityType", Registry.ENTITY_TYPE.getKey(this.getType()).toString());
		compoundTag.put("JellyfishDisplayTag", this.getBucketDisplayInfo().write());
		this.addAdditionalSaveDataSharedWithBucket(compoundTag);
	}

	@Override
	public void loadFromBucketTag(CompoundTag compoundTag) {
		super.loadFromBucketTag(compoundTag);
		this.readAdditionalSaveDataSharedWithBucket(compoundTag);
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
	public ItemStack getBucketItemStack() {
		return new ItemStack(UAItems.JELLYFISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return UASoundEvents.ITEM_BUCKET_FILL_JELLYFISH.get();
	}

	@Override
	public ItemStack getPickedResult(HitResult target) {
		return new ItemStack(UAItems.JELLYFISH_SPAWN_EGG.get());
	}

	@Override
	public void calculateEntityAnimation(LivingEntity entity, boolean p_233629_2_) {
		super.calculateEntityAnimation(entity, true);
	}

	public abstract BucketDisplayInfo getBucketDisplayInfo();

	public abstract JellyTorchType getJellyTorchType();

	public abstract float getCooldownChance();

	protected final BucketDisplayInfo bucketDisplayInfo(String name, int subId, JellyTorchType... yieldingTorchTypes) {
		return new BucketDisplayInfo(name, JellyfishRegistry.IDS.get(this.getClass()), subId, yieldingTorchTypes);
	}

	protected boolean stingEntity(LivingEntity livingEntity) {
		if (livingEntity.hurt(UADamageSources.causeJellyfishDamage(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue())) {
			this.playSound(UASoundEvents.ENTITY_JELLYFISH_STING.get(), 0.5F, this.random.nextFloat() * 0.2F + 1.0F);
			return true;
		}
		return false;
	}

	protected ItemStack getTorchByType(JellyTorchType type) {
		return new ItemStack(type.torch.get());
	}

	public static class JellyfishLookControl extends LookControl {

		public JellyfishLookControl(Mob mob) {
			super(mob);
		}

		@Override
		protected boolean resetXRotOnTick() {
			return false;
		}

	}

	public static record BucketDisplayInfo(String name, int id, int subId, JellyTorchType... yieldingTorchTypes) {
		public static float readVariant(CompoundTag compoundTag) {
			return compoundTag.getInt("Id") + 0.1F * compoundTag.getInt("SubId");
		}

		public static void appendHoverText(List<Component> tooltip, CompoundTag compoundTag) {
			String name = compoundTag.getString("Name");
			if (!name.isEmpty()) tooltip.add((new TranslatableComponent("tooltip.upgrade_aquatic." + name + "_jellyfish").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY)));
			if (!compoundTag.contains("YieldingTorchTypes", 11)) return;
			int[] yieldingTorchTypes = ((IntArrayTag) compoundTag.get("YieldingTorchTypes")).getAsIntArray();
			int length = yieldingTorchTypes.length;
			if (length <= 0) return;
			MutableComponent component = new TranslatableComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").withStyle(ChatFormatting.GRAY);
			while (true) {
				JellyTorchType torchType = JellyTorchType.getByOrdinal(yieldingTorchTypes[length - 1]);
				component = component.append((new TranslatableComponent("tooltip.upgrade_aquatic." + torchType.toString().toLowerCase() + "_jelly_torch")).withStyle(torchType.color));
				if (--length > 0) {
					component = component.append(new TranslatableComponent("tooltip.upgrade_aquatic.yielding_jelly_torch.or").withStyle(ChatFormatting.GRAY));
				} else break;
			}
			tooltip.add(component);
		}

		public CompoundTag write() {
			CompoundTag compoundTag = new CompoundTag();
			compoundTag.putString("Name", this.name);
			compoundTag.putInt("Id", this.id);
			compoundTag.putInt("SubId", this.subId);
			IntArrayTag yieldingTorchTypesTag = new IntArrayTag(new int[0]);
			for (JellyTorchType jellyTorchType : this.yieldingTorchTypes) {
				yieldingTorchTypesTag.add(IntTag.valueOf(jellyTorchType.ordinal()));
			}
			compoundTag.put("YieldingTorchTypes", yieldingTorchTypesTag);
			return compoundTag;
		}
	}
}