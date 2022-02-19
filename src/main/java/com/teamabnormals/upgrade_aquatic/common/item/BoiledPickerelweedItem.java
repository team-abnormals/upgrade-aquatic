package com.teamabnormals.upgrade_aquatic.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoiledPickerelweedItem extends Item {
	final boolean isPurple;

	public BoiledPickerelweedItem(Properties properties, boolean isPurple) {
		super(properties);
		this.isPurple = isPurple;
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		ItemStack itemstack = super.finishUsingItem(stack, worldIn, entityLiving);
		int adder = isPurple ? 75 : 100;
		entityLiving.setAirSupply(Math.min(entityLiving.getAirSupply() + adder, 300));
		return itemstack;
	}

}
