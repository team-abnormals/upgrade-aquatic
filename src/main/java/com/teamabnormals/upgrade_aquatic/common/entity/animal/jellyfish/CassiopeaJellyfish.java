package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish;

import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.CassiopeaHideInSeagrassGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.CassiopeaJellyfishFlipGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishRandomSwimmingGoal;
import com.teamabnormals.upgrade_aquatic.core.other.UADamageTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class CassiopeaJellyfish extends ColoredSizableJellyfish {
	public int upsideDownCooldown;
	public int hideCooldown;

	public CassiopeaJellyfish(EntityType<? extends AbstractJellyfish> type, Level world) {
		super(type, world);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return AbstractJellyfish.createAttributes().add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CassiopeaHideInSeagrassGoal(this));
		this.goalSelector.addGoal(2, new CassiopeaJellyfishFlipGoal(this));
		this.goalSelector.addGoal(2, new JellyfishRandomSwimmingGoal(this));

		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.hasUpsideDownCooldown()) {
			this.upsideDownCooldown--;
		}

		if (this.hasHideCooldown()) {
			this.hideCooldown--;
		}
	}

	@Override
	public void addAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		super.addAdditionalSaveDataSharedWithBucket(compoundTag);
		compoundTag.putInt("UpsideDownCooldown", this.upsideDownCooldown);
		compoundTag.putInt("HideCooldown", this.hideCooldown);
	}

	@Override
	protected void readAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		super.readAdditionalSaveDataSharedWithBucket(compoundTag);
		this.upsideDownCooldown = compoundTag.getInt("UpsideDownCooldown");
		this.hideCooldown = compoundTag.getInt("HideCooldown");
	}

	public boolean hasUpsideDownCooldown() {
		return this.upsideDownCooldown > 0;
	}

	public boolean hasHideCooldown() {
		return this.hideCooldown > 0;
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return super.getDimensions(pose).scale(this.getSize());
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions size) {
		return size.height * 0.5F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	public String getVariantName() {
		return switch (this.getColor()) {
			default -> "cassiopea";
			case 1 -> "blue_cassiopea";
			case 2 -> "white_cassiopea";
		};
	}

	@Override
	public BucketDisplayInfo getBucketDisplayInfo() {
		JellyTorchType jellyTorchType = this.getJellyTorchType();
		return this.bucketDisplayInfo(this.getVariantName(), jellyTorchType.ordinal(), jellyTorchType);
	}

	@Override
	public JellyTorchType getJellyTorchType() {
		return switch (this.getColor()) {
			default -> JellyTorchType.GREEN;
			case 1 -> JellyTorchType.BLUE;
			case 2 -> JellyTorchType.WHITE;
		};
	}

	@Override
	public float getCooldownChance() {
		return this.getSize() < 1.0F ? 0.9F : 0.8F;
	}

	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if ((this.getTarget() == livingEntity || this.getLastHurtByMob() == livingEntity) && this.getRandom().nextFloat() < 0.5F) {
			return livingEntity.hurt(UADamageTypes.jellyfishSting(this.level(), this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
		}
		return false;
	}

	@Override
	protected float getHealthSizeMultiplier() {
		return 6.0F;
	}

	@Override
	protected float getDefaultSize() {
		return 0.85F;
	}
}