package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish;

import com.teamabnormals.blueprint.common.entity.ai.PredicateAttackGoal;
import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishRandomSwimmingGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class BoxJellyfish extends ColoredSizableJellyfish {
	private int huntingCooldown;

	public BoxJellyfish(EntityType<? extends BoxJellyfish> type, Level world) {
		super(type, world);
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return AbstractJellyfish.createAttributes().add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 4.0F, true) {
			@Override
			public void stop() {
				super.stop();
				if (this.mob.getTarget() == null) ((BoxJellyfish) this.mob).setHuntingCooldown();
			}
		});
		this.goalSelector.addGoal(2, new JellyfishRandomSwimmingGoal(this));

		this.targetSelector.addGoal(1, new PredicateAttackGoal<>(this, AbstractFish.class, 150, true, true, null, owner -> !((BoxJellyfish) owner).hasCooldown() && !((BoxJellyfish) owner).hasHuntingCooldown()));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.hasHuntingCooldown()) this.huntingCooldown--;
	}

	@Override
	protected void addAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		super.addAdditionalSaveDataSharedWithBucket(compoundTag);
		compoundTag.putInt("HuntingCooldown", this.huntingCooldown);
	}

	@Override
	protected void readAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		super.readAdditionalSaveDataSharedWithBucket(compoundTag);
		this.huntingCooldown = compoundTag.getInt("HuntingCooldown");
	}

	@Override
	public EntityDimensions getDimensions(Pose pose) {
		return super.getDimensions(pose).scale(this.getSize());
	}

	public void setHuntingCooldown() {
		this.huntingCooldown = this.getRandom().nextInt(1600) + 1200;
	}

	public boolean hasHuntingCooldown() {
		return this.huntingCooldown > 0;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions size) {
		return size.height * 0.5F;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			Entity entity = source.getEntity();
			if (entity instanceof LivingEntity && this.getTarget() == null && !entity.isSpectator()) {
				if (!(entity instanceof Player && ((Player) entity).isCreative())) {
					this.setTarget((LivingEntity) entity);
				}
			}
			return true;
		}
		return false;
	}

	public String getVariantName() {
		return switch (this.getColor()) {
			default -> "box";
			case 1 -> "red_box";
			case 2 -> "white_box";
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
			default -> JellyTorchType.BLUE;
			case 1 -> JellyTorchType.RED;
			case 2 -> JellyTorchType.WHITE;
		};
	}

	@Override
	public float getCooldownChance() {
		return this.getSize() >= 1.0F ? 0.05F : this.getSize() < 0.5F ? 0.1F : 0.15F;
	}

	@Override
	public boolean stingEntity(LivingEntity livingEntity) {
		if (super.stingEntity(livingEntity)) {
			livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 1));
			if (this.getTarget() == null) {
				this.setTarget(livingEntity);
			}
			return true;
		}
		return false;
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
	public int getMaxSpawnClusterSize() {
		return 3;
	}
}