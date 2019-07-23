package com.teamabnormals.upgrade_aquatic.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBoiledPickerelweed extends Item {
	
	public ItemBoiledPickerelweed(Properties properties) {
		super(properties);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		ItemStack itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
		if(entityLiving.getAir() + 100 <= 300) {
			entityLiving.setAir(entityLiving.getAir() + 100);
		} else {
			entityLiving.setAir(300);
		}
		return itemstack;
	}
	
}
