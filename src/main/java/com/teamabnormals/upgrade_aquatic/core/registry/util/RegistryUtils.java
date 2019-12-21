package com.teamabnormals.upgrade_aquatic.core.registry.util;

import java.util.function.Supplier;

import com.teamabnormals.upgrade_aquatic.common.items.UASpawnEggItem;
import com.teamabnormals.upgrade_aquatic.common.items.itemblocks.ItemBlockUpsideDown;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.TallBlockItem;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.RegistryObject;

public class RegistryUtils {
	
	public static String nameWithModIdPrefix(String name) {
		return Reference.MODID + ":" + name;
	}
	
	public static BlockItem createWallOrFloorItem(Block floorBlock, Block wallBlock, ItemGroup itemGroup) {
		return (BlockItem) new WallOrFloorItem(floorBlock, wallBlock, new Item.Properties().group(itemGroup)).setRegistryName(floorBlock.getRegistryName());
	}
	
	public static BlockItem createUpsideDownBlockItem(Block block, ItemGroup itemGroup) {
		return (BlockItem) new ItemBlockUpsideDown(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
	}
	
	public static BlockItem createTallItemBlock(Block block, ItemGroup itemGroup) {
		return (BlockItem) new TallBlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
	}
	
	public static Item createSimpleItem(String name, ItemGroup itemGroup) {
		return new Item(new Item.Properties().group(itemGroup)).setRegistryName(Reference.MODID, name);
	}
	
	public static Item createSpawnEggForEntity(@SuppressWarnings("rawtypes") EntityType entityType, int eggColor1, int eggColor2, ItemGroup itemGroup) {
		return new SpawnEggItem(entityType, eggColor1, eggColor2, new Item.Properties().group(itemGroup)).setRegistryName(entityType.getRegistryName() + "_spawn_egg");
	}

	public static BlockItem createSimpleItemBlock(Block block, ItemGroup itemGroup) {
		return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
	}

	public static Item createItemBlockWithRarity(Block blockForInput, ItemGroup itemGroup, Rarity rarity) {
		return (BlockItem) new BlockItem(blockForInput, new Item.Properties().group(itemGroup).rarity(rarity)).setRegistryName(blockForInput.getRegistryName());
	}
	
	public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = UAItems.ITEMS.register(name, supplier);
		return item;
	}
	
	public static RegistryObject<Item> createSpawnEggItem(String entityName, Supplier<EntityType<?>> supplier, int primaryColor, int secondaryColor) {
		RegistryObject<Item> spawnEgg = UAItems.ITEMS.register(entityName + "_spawn_egg", () -> new UASpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
		UAItems.SPAWN_EGGS.add(spawnEgg);
		return spawnEgg;
	}
	
}