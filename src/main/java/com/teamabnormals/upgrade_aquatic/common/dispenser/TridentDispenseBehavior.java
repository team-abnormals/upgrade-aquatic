package com.teamabnormals.upgrade_aquatic.common.dispenser;

import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.block.LevelEvent;

public class TridentDispenseBehavior extends ProjectileDispenseBehavior {

	private boolean success = true;

	public TridentDispenseBehavior() {
		super(Items.TRIDENT);
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public ItemStack execute(BlockSource source, ItemStack stack) {
		if (!TridentItem.isTooDamagedToUse(stack)) {
			this.setSuccess(true);
			stack.hurtAndBreak(1, source.level(), null, item -> {
			});
			return super.execute(source, stack);
		} else {
			this.setSuccess(false);
			return stack;
		}
	}

	@Override
	protected void playSound(BlockSource source) {
		if (this.isSuccess()) {
			source.level().playSound(null, source.pos(), SoundEvents.TRIDENT_THROW.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
			super.playSound(source);
		} else {
			source.level().levelEvent(LevelEvent.SOUND_DISPENSER_FAIL, source.pos(), 0);
		}
	}
}