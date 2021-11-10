package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.abnormals_core.common.entity.ai.PredicateAttackGoal;
import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.BoxJellyfishHuntGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwimIntoDirectionGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class BoxJellyfishEntity extends ColoredSizableJellyfishEntity {
	private final RotationController rotationController;
	private int huntingCooldown;

	public BoxJellyfishEntity(EntityType<? extends BoxJellyfishEntity> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new BoxJellyfishHuntGoal(this));
		this.goalSelector.addGoal(2, new JellyfishSwimIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));

		this.targetSelector.addGoal(1, new PredicateAttackGoal<>(this, AbstractFishEntity.class, 150, true, true, null, owner -> !((BoxJellyfishEntity) owner).hasCooldown() && !((BoxJellyfishEntity) owner).hasHuntingCooldown()));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.hasHuntingCooldown()) this.huntingCooldown--;

		if (this.isEndimationPlaying(BOOST_ANIMATION) && this.isInWater()) {
			this.setDeltaMovement(this.getDeltaMovement().scale(1.15F));
		}
	}

	@Override
	public void onEndimationStart(Endimation endimation) {
		if (endimation == SWIM_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.6F, this.getSize());
		} else if (endimation == BOOST_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.25F, this.getSize());
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("HuntingCooldown", this.huntingCooldown);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.huntingCooldown = compound.getInt("HuntingCooldown");
	}

	@Override
	public EntitySize getDimensions(Pose pose) {
		return super.getDimensions(pose).scale(this.getSize());
	}

	public void setHuntingCooldown() {
		this.huntingCooldown = this.getRandom().nextInt(1600) + 1200;
	}

	public boolean hasHuntingCooldown() {
		return this.huntingCooldown > 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.5F;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			Entity entity = source.getEntity();
			if (entity instanceof LivingEntity && this.getTarget() == null && !entity.isSpectator()) {
				if (!(entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative())) {
					this.setTarget((LivingEntity) entity);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String getBucketName() {
		switch (this.getColor()) {
			default:
			case 0:
				return "box";
			case 1:
				return "red_box";
			case 2:
				return "white_box";
		}
	}

	@Override
	public JellyTorchType getJellyTorchType() {
		switch (this.getColor()) {
			default:
			case 0:
				return JellyTorchType.BLUE;
			case 1:
				return JellyTorchType.RED;
			case 2:
				return JellyTorchType.WHITE;
		}
	}

	@Override
	public float getCooldownChance() {
		return this.getSize() >= 1.0F ? 0.05F : this.getSize() < 0.5F ? 0.1F : 0.15F;
	}

	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if (super.stingEntity(livingEntity)) {
			livingEntity.addEffect(new EffectInstance(Effects.POISON, 600, 1));
			if (this.getTarget() == null) {
				this.setTarget(livingEntity);
			}
			return true;
		}
		return false;
	}

	@Override
	protected String getBucketEntityId() {
		return "box_jellyfish";
	}

	@Override
	protected float getDefaultSize() {
		return 1.0F;
	}

	@Override
	protected float getHealthSizeMultiplier() {
		return 8.0F;
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}
}