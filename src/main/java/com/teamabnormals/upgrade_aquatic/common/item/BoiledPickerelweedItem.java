package com.teamabnormals.upgrade_aquatic.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoiledPickerelweedItem extends Item {

	public BoiledPickerelweedItem(Properties properties) {
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		ItemStack newStack = super.finishUsingItem(stack, level, entity);
		entity.setAirSupply(Math.min(entity.getAirSupply() + 100, 300));
		return newStack;
	}
}
