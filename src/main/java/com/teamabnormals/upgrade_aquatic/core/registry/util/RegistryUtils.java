package com.teamabnormals.upgrade_aquatic.core.registry.util;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.common.items.UASpawnEggItem;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;

public class RegistryUtils {
	public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
		RegistryObject<I> item = UAItems.ITEMS.register(name, supplier);
		return item;
	}
	
	public static RegistryObject<Item> createSpawnEggItem(String entityName, Supplier<EntityType<?>> supplier, int primaryColor, int secondaryColor) {
		RegistryObject<Item> spawnEgg = UAItems.ITEMS.register(entityName + "_spawn_egg", () -> new UASpawnEggItem(supplier, primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
		UAItems.SPAWN_EGGS.add(spawnEgg);
		return spawnEgg;
	}
	
	public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = UABlocks.BLOCKS.register(name, supplier);
		UAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(group)));
		return block;
	}
	
	public static <B extends Block> RegistryObject<B> createRareBlock(String name, Supplier<? extends B> supplier, Rarity rarity, @Nullable ItemGroup group) {
		RegistryObject<B> block = UABlocks.BLOCKS.register(name, supplier);
		UAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(rarity).group(group)));
		return block;
	}
	
	public static <B extends Block> RegistryObject<B> createWallOrFloorBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = UABlocks.BLOCKS.register(name, supplier);
		UAItems.ITEMS.register(name, () -> new WallOrFloorItem(block.get(), wallSupplier.get(), new Item.Properties().group(group)));
		return block;
	}
	
	public static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
		RegistryObject<B> block = UABlocks.BLOCKS.register(name, supplier);
		return block;
	}
	
	public static <B extends Block> RegistryObject<B> createCompatBlock(String name, String modName, Supplier<? extends B> supplier, @Nullable ItemGroup group) {
		ItemGroup determinedGroup = ModList.get().isLoaded(modName) || modName == "indev" ? group : null;
		RegistryObject<B> block = UABlocks.BLOCKS.register(name, supplier);
		UAItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(determinedGroup)));
		return block;
	}
	
}