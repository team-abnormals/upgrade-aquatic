package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.CassiopeaHideInSeagrassGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.CassiopeaJellyfishFlipGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwimIntoDirectionGoal;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADamageSources;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class CassiopeaJellyfishEntity extends ColoredSizableJellyfishEntity {
	public int upsideDownCooldown;
	public int hideCooldown;
	private RotationController rotationController;

	public CassiopeaJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CassiopeaHideInSeagrassGoal(this));
		this.goalSelector.addGoal(2, new CassiopeaJellyfishFlipGoal(this));
		this.goalSelector.addGoal(2, new JellyfishSwimIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));

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

		if (this.isInWater()) {
			if (this.isEndimationPlaying(BOOST_ANIMATION)) {
				this.setDeltaMovement(this.getDeltaMovement().scale(1.15F));
			} else if (this.isEndimationPlaying(SWIM_ANIMATION)) {
				this.setDeltaMovement(this.getDeltaMovement().scale(1.05F));
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("UpsideDownCooldown", this.upsideDownCooldown);
		compound.putInt("HideCooldown", this.hideCooldown);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.upsideDownCooldown = compound.getInt("UpsideDownCooldown");
		this.hideCooldown = compound.getInt("HideCooldown");
	}

	public boolean hasUpsideDownCooldown() {
		return this.upsideDownCooldown > 0;
	}

	public boolean hasHideCooldown() {
		return this.hideCooldown > 0;
	}

	@Override
	public EntitySize getDimensions(Pose pose) {
		return super.getDimensions(pose).scale(this.getSize());
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.5F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	public void onEndimationStart(Endimation endimation) {
		float sizeForce = this.getSize() < 0.6F ? 0.85F : this.getSize();
		if (endimation == SWIM_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.3F, sizeForce);
		} else if (endimation == BOOST_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.2F, sizeForce);
		}
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}

	@Override
	public String getBucketName() {
		switch (this.getColor()) {
			default:
			case 0:
				return "cassiopea";
			case 1:
				return "blue_cassiopea";
			case 2:
				return "white_cassiopea";
		}
	}

	@Override
	public JellyTorchType getJellyTorchType() {
		switch (this.getColor()) {
			default:
			case 0:
				return JellyTorchType.GREEN;
			case 1:
				return JellyTorchType.BLUE;
			case 2:
				return JellyTorchType.WHITE;
		}
	}

	@Override
	public float getCooldownChance() {
		return this.getSize() < 1.0F ? 0.9F : 0.8F;
	}

	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if ((this.getTarget() == livingEntity || this.getLastHurtByMob() == livingEntity) && this.getRandom().nextFloat() < 0.5F) {
			return livingEntity.hurt(UADamageSources.causeJellyfishDamage(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
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

	@Override
	protected String getBucketEntityId() {
		return "cassiopea_jellyfish";
	}
}