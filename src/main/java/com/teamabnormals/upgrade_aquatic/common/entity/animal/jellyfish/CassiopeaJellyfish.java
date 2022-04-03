package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish;

import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.CassiopeaHideInSeagrassGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.CassiopeaJellyfishFlipGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishBoostGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishSwimIntoDirectionGoal;
import com.teamabnormals.upgrade_aquatic.core.other.UADamageSources;
import com.teamabnormals.upgrade_aquatic.core.registry.UAPlayableEndimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
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
	private final RotationController rotationController;

	public CassiopeaJellyfish(EntityType<? extends AbstractJellyfish> type, Level world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new CassiopeaHideInSeagrassGoal(this));
		this.goalSelector.addGoal(2, new CassiopeaJellyfishFlipGoal(this));
		this.goalSelector.addGoal(2, new JellyfishSwimIntoDirectionGoal(this, UAPlayableEndimations.JELLYFISH_SWIM));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, UAPlayableEndimations.JELLYFISH_BOOST));

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
			if (this.isEndimationPlaying(UAPlayableEndimations.JELLYFISH_BOOST)) {
				this.setDeltaMovement(this.getDeltaMovement().scale(1.15F));
			} else if (this.isEndimationPlaying(UAPlayableEndimations.JELLYFISH_SWIM)) {
				this.setDeltaMovement(this.getDeltaMovement().scale(1.05F));
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("UpsideDownCooldown", this.upsideDownCooldown);
		compound.putInt("HideCooldown", this.hideCooldown);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
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

	@Override
	public void onEndimationStart(PlayableEndimation endimation, PlayableEndimation oldEndimation) {
		float sizeForce = this.getSize() < 0.6F ? 0.85F : this.getSize();
		if (endimation == UAPlayableEndimations.JELLYFISH_SWIM) {
			this.getRotationController().addVelocityForLookDirection(0.3F, sizeForce);
		} else if (endimation == UAPlayableEndimations.JELLYFISH_BOOST) {
			this.getRotationController().addVelocityForLookDirection(0.2F, sizeForce);
		}
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}

	@Override
	public String getBucketName() {
		return switch (this.getColor()) {
			default -> "cassiopea";
			case 1 -> "blue_cassiopea";
			case 2 -> "white_cassiopea";
		};
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