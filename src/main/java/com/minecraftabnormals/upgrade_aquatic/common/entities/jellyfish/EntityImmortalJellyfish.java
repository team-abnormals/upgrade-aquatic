package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishBoostGoal;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai.JellyfishSwinIntoDirectionGoal;
import com.teamabnormals.abnormals_core.core.library.endimator.Endimation;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class EntityImmortalJellyfish extends AbstractEntityJellyfish {
	private RotationController rotationController;
	public static final Endimation SWIM_ANIMATION = new Endimation(20);
	public static final Endimation BOOST_ANIMATION = new Endimation(20);
	private int healCooldown;
	private float prevHealth;

	public EntityImmortalJellyfish(EntityType<? extends AbstractEntityJellyfish> type, World world) {
		super(type, world);
		this.rotationController = new RotationController(this);
		this.prevHealth = this.getHealth();
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
    	return MobEntity.func_233666_p_().
    			func_233815_a_(Attributes.ATTACK_DAMAGE, 1.0D).
    			func_233815_a_(Attributes.MAX_HEALTH, 7.0D);
    }
	
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new JellyfishSwinIntoDirectionGoal(this, SWIM_ANIMATION));
		this.goalSelector.addGoal(3, new JellyfishBoostGoal(this, BOOST_ANIMATION));
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if(this.healCooldown > 0) {
			this.healCooldown--;
		} else {
			if(this.ticksExisted % 5 == 0) {
				this.heal(0.5F);
			}
		}
		
		if(this.prevHealth > this.getHealth()) {
			this.healCooldown = this.getRNG().nextInt(40) + 20;
		}
		
		this.prevHealth = this.getHealth();
	}
	
	@Override
	public void onEndimationStart(Endimation endimation) {
		if(endimation == SWIM_ANIMATION) {
			this.getRotationController().addVelocityForLookDirection(0.35F, 1.0F);
		} else if(endimation == BOOST_ANIMATION) {
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
	public Endimation[] getEndimations() {
		return new Endimation[] {
			SWIM_ANIMATION,
			BOOST_ANIMATION
		};
	}

	@Override
	public RotationController getRotationController() {
		return this.rotationController;
	}
	
	@Override
	protected void setBucketData(ItemStack bucket) {
		if(this.hasCustomName()) {
			bucket.setDisplayName(this.getCustomName());
		}
		BucketData data = new BucketData("immortal_jellyfish");
		bucket.getOrCreateTag().put("JellyfishTag", BucketData.write(data));
	}

	@Override
	public void readBucketData(CompoundNBT compound) {}

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
		return (new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch").func_240699_a_(TextFormatting.GRAY))
			.func_230529_a_((new TranslationTextComponent("tooltip.upgrade_aquatic.jelly_torch_" + white.toString().toLowerCase())).func_240699_a_(white.color))
			.func_230529_a_(new TranslationTextComponent("tooltip.upgrade_aquatic.yielding_jelly_torch.or").func_240699_a_(TextFormatting.GRAY))
			.func_230529_a_((new TranslationTextComponent("tooltip.upgrade_aquatic.jelly_torch_" + red.toString().toLowerCase())).func_240699_a_(red.color))
		;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}
}