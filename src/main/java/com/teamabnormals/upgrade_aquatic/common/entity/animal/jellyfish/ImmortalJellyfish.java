package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish;

import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishBoostGoal;
import com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish.JellyfishSwimIntoDirectionGoal;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class ImmortalJellyfish extends AbstractJellyfish {
	private final BucketProcessor<ImmortalJellyfish> bucketProcessor;
	private final RotationController rotationController;
	private int healCooldown;
	private float prevHealth;

	public ImmortalJellyfish(EntityType<? extends AbstractJellyfish> type, Level world) {
		super(type, world);
		this.rotationController = new RotationController(this);
		this.bucketProcessor = new BucketProcessor<>("immortal_jellyfish", this);
		this.prevHealth = this.getHealth();
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return createMobAttributes()
				.add(Attributes.ATTACK_DAMAGE, 1.0D)
				.add(Attributes.MAX_HEALTH, 7.0D);
	}


	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new JellyfishSwimIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));
	}

	@Override
	public void tick() {
		super.tick();

		if (this.healCooldown > 0) {
			this.healCooldown--;
		} else {
			if (this.tickCount % 5 == 0) {
				this.heal(0.5F);
			}
		}

		if (this.prevHealth > this.getHealth()) {
			this.healCooldown = this.getRandom().nextInt(40) + 20;
		}

		this.prevHealth = this.getHealth();
	}

	@Override
	public void onEndimationStart(Endimation endimation) {
		if (endimation == SWIM_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.35F, 1.0F);
		} else if (endimation == BOOST_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.2F, 1.0F);
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.healCooldown = compound.getInt("HealCooldown");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("HealCooldown", this.healCooldown);
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}

	@Override
	public String getBucketName() {
		return "immortal";
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
	public int getIdSuffix() {
		return 7;
	}

	@Override
	public Component getYieldingTorchMessage() {
		JellyTorchType white = JellyTorchType.WHITE;
		JellyTorchType red = JellyTorchType.RED;
		return (new TranslatableComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").withStyle(ChatFormatting.GRAY))
				.append((new TranslatableComponent("tooltip.upgrade_aquatic." + white.toString().toLowerCase() + "_jelly_torch")).withStyle(white.color))
				.append(new TranslatableComponent("tooltip.upgrade_aquatic.yielding_jelly_torch.or").withStyle(ChatFormatting.GRAY))
				.append((new TranslatableComponent("tooltip.upgrade_aquatic." + red.toString().toLowerCase() + "_jelly_torch")).withStyle(red.color))
				;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 3;
	}

	@Override
	public BucketProcessor<?> getBucketProcessor() {
		return this.bucketProcessor;
	}
}