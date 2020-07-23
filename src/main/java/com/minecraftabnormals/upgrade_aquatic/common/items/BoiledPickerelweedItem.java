package com.minecraftabnormals.upgrade_aquatic.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BoiledPickerelweedItem extends Item {
	final boolean isPurple;
	
	public BoiledPickerelweedItem(Properties properties, boolean isPurple) {
		super(properties);
		this.isPurple = isPurple;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
		int adder = isPurple ? 75 : 100;
		if(entityLiving.getAir() + adder <= 300) {
			entityLiving.setAir(entityLiving.getAir() + adder);
		} else {
			entityLiving.setAir(300);
		}
		return itemstack;
	}
	
}
