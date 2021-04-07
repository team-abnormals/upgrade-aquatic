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
	private RotationController rotationController;
	private int huntingCooldown;

	public BoxJellyfishEntity(EntityType<? extends BoxJellyfishEntity> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_().createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);
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
			this.setMotion(this.getMotion().scale(1.15F));
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
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("HuntingCooldown", this.huntingCooldown);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.huntingCooldown = compound.getInt("HuntingCooldown");
	}

	@Override
	public EntitySize getSize(Pose pose) {
		return super.getSize(pose).scale(this.getSize());
	}

	public void setHuntingCooldown() {
		this.huntingCooldown = this.getRNG().nextInt(1600) + 1200;
	}

	public boolean hasHuntingCooldown() {
		return this.huntingCooldown > 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize size) {
		return size.height * 0.5F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount)) {
			Entity entity = source.getTrueSource();
			if (entity instanceof LivingEntity && this.getAttackTarget() == null && !entity.isSpectator()) {
				if (!(entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative())) {
					this.setAttackTarget((LivingEntity) entity);
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
			livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 600, 1));
			if (this.getAttackTarget() == null) {
				this.setAttackTarget(livingEntity);
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
	public int getMaxSpawnedInChunk() {
		return 3;
	}
}