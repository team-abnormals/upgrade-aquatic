package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemWallOrFloor;

public class RegistryUtils {
	
	public static ItemBlock createSimpleItemBlock(Block blockForInput, ItemGroup itemGroup) {
		return (ItemBlock) new ItemBlock(blockForInput, new Item.Properties().group(itemGroup)).setRegistryName(blockForInput.getRegistryName());
	}
	
	public static ItemBlock createWallOrFloorItem(Block floorBlock, Block wallBlock, ItemGroup itemGroup) {
		return (ItemBlock) new ItemWallOrFloor(floorBlock, wallBlock, new Item.Properties().group(itemGroup)).setRegistryName(floorBlock.getRegistryName());
	}
	
	public static Item createSimpleItem(String name, ItemGroup itemGroup) {
		return new Item(new Item.Properties().group(itemGroup)).setRegistryName(Reference.MODID, name);
	}
}
