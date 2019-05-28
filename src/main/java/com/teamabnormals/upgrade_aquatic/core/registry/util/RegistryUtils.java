package com.teamabnormals.upgrade_aquatic.core.registry.util;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.item.ItemWallOrFloor;

public class RegistryUtils {
	
	public static ItemBlock createSimpleItemBlock(Block blockForInput, ItemGroup itemGroup) {
		return (ItemBlock) new ItemBlock(blockForInput, new Item.Properties().group(itemGroup)).setRegistryName(blockForInput.getRegistryName());
	}
	
	public static ItemBlock createItemBlockWithRarity(Block blockForInput, ItemGroup itemGroup, EnumRarity rarity) {
		return (ItemBlock) new ItemBlock(blockForInput, new Item.Properties().group(itemGroup).rarity(rarity)).setRegistryName(blockForInput.getRegistryName());
	}
	
	public static ItemBlock createWallOrFloorItem(Block floorBlock, Block wallBlock, ItemGroup itemGroup) {
		return (ItemBlock) new ItemWallOrFloor(floorBlock, wallBlock, new Item.Properties().group(itemGroup)).setRegistryName(floorBlock.getRegistryName());
	}
	
	public static Item createSimpleItem(String name, ItemGroup itemGroup) {
		return new Item(new Item.Properties().group(itemGroup)).setRegistryName(Reference.MODID, name);
	}
	
	public static Item createSpawnEggForEntity(@SuppressWarnings("rawtypes") EntityType entityType, int eggColor1, int eggColor2, ItemGroup itemGroup) {
		return new ItemSpawnEgg(entityType, eggColor1, eggColor2, new Item.Properties().group(itemGroup)).setRegistryName(entityType.getRegistryName() + "_spawn_egg");
	}
	
}
