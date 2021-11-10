package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwimIntoDirectionGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class ImmortalJellyfishEntity extends AbstractJellyfishEntity {
	private final BucketProcessor<ImmortalJellyfishEntity> bucketProcessor;
	private final RotationController rotationController;
	private int healCooldown;
	private float prevHealth;

	public ImmortalJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
		this.bucketProcessor = new BucketProcessor<>("immortal_jellyfish", this);
		this.prevHealth = this.getHealth();
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createMobAttributes()
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
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.healCooldown = compound.getInt("HealCooldown");
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
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
	public ITextComponent getYieldingTorchMessage() {
		JellyTorchType white = JellyTorchType.WHITE;
		JellyTorchType red = JellyTorchType.RED;
		return (new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").withStyle(TextFormatting.GRAY))
				.append((new TranslationTextComponent("tooltip.upgrade_aquatic." + white.toString().toLowerCase() + "_jelly_torch")).withStyle(white.color))
				.append(new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch.or").withStyle(TextFormatting.GRAY))
				.append((new TranslationTextComponent("tooltip.upgrade_aquatic." + red.toString().toLowerCase() + "_jelly_torch")).withStyle(red.color))
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