package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish;

import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishBoostGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishSwimIntoDirectionGoal;
import com.teamabnormals.upgrade_aquatic.core.registry.UAPlayableEndimations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class ImmortalJellyfish extends AbstractJellyfish {
	private final RotationController rotationController;
	private int healCooldown;
	private float prevHealth;

	public ImmortalJellyfish(EntityType<? extends AbstractJellyfish> type, Level world) {
		super(type, world);
		this.rotationController = new RotationController(this);
		this.prevHealth = this.getHealth();
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return createMobAttributes()
				.add(Attributes.ATTACK_DAMAGE, 1.0D)
				.add(Attributes.MAX_HEALTH, 7.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new JellyfishSwimIntoDirectionGoal(this, UAPlayableEndimations.JELLYFISH_SWIM));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, UAPlayableEndimations.JELLYFISH_BOOST));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.healCooldown > 0) {
			this.healCooldown--;
		} else if (this.tickCount % 5 == 0) {
			this.heal(0.5F);
		}

		if (this.prevHealth > this.getHealth()) {
			this.healCooldown = this.getRandom().nextInt(40) + 20;
		}

		this.prevHealth = this.getHealth();
	}

	@Override
	public void onEndimationStart(PlayableEndimation endimation, PlayableEndimation oldEndimation) {
		if (endimation == UAPlayableEndimations.JELLYFISH_SWIM) {
			this.getRotationController().addVelocityForLookDirection(0.35F, 1.0F);
		} else if (endimation == UAPlayableEndimations.JELLYFISH_BOOST) {
			this.getRotationController().addVelocityForLookDirection(0.2F, 1.0F);
		}
	}

	@Override
	protected void readAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		super.readAdditionalSaveDataSharedWithBucket(compoundTag);
		this.healCooldown = compoundTag.getInt("HealCooldown");
	}

	@Override
	protected void addAdditionalSaveDataSharedWithBucket(CompoundTag compoundTag) {
		super.addAdditionalSaveDataSharedWithBucket(compoundTag);
		compoundTag.putInt("HealCooldown", this.healCooldown);
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}

	@Override
	public BucketDisplayInfo getBucketDisplayInfo() {
		return this.bucketDisplayInfo("immortal", 7, JellyTorchType.WHITE, JellyTorchType.RED);
	}

	@Override
	public JellyTorchType getJellyTorchType() {
		return this.getRandom().nextFloat() < 0.75F ? JellyTorchType.WHITE : JellyTorchType.RED;
	}

	@Override
	public float getCooldownChance() {
		return 0.8F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}
}