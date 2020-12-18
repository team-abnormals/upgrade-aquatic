package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.JellyTorchBlock.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwinIntoDirectionGoal;
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
	private RotationController rotationController;
	private final BucketProcessor<ImmortalJellyfishEntity> bucketProcessor;
	private int healCooldown;
	private float prevHealth;

	public ImmortalJellyfishEntity(EntityType<? extends AbstractJellyfishEntity> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
		this.bucketProcessor = new BucketProcessor<>("immortal_jellyfish", this);
		this.prevHealth = this.getHealth();
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_()
    			.createMutableAttribute(Attributes.ATTACK_DAMAGE, 1.0D)
    			.createMutableAttribute(Attributes.MAX_HEALTH, 7.0D);
    }
	
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new JellyfishSwinIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (this.healCooldown > 0) {
			this.healCooldown--;
		} else {
			if (this.ticksExisted % 5 == 0) {
				this.heal(0.5F);
			}
		}
		
		if (this.prevHealth > this.getHealth()) {
			this.healCooldown = this.getRNG().nextInt(40) + 20;
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
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.healCooldown = compound.getInt("HealCooldown");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
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
		return this.getRNG().nextFloat() < 0.75F ? JellyTorchType.WHITE : JellyTorchType.RED;
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
		return (new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").mergeStyle(TextFormatting.GRAY))
			.append((new TranslationTextComponent("tooltip.upgrade_aquatic.jelly_torch_" + white.toString().toLowerCase())).mergeStyle(white.color))
			.append(new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch.or").mergeStyle(TextFormatting.GRAY))
			.append((new TranslationTextComponent("tooltip.upgrade_aquatic.jelly_torch_" + red.toString().toLowerCase())).mergeStyle(red.color))
		;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public BucketProcessor<?> getBucketProcessor() {
		return this.bucketProcessor;
	}
}