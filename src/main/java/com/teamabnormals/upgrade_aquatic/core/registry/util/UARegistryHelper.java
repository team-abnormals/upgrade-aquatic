package com.teamabnormals.upgrade_aquatic.core.registry.util;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.items.ItemJellyfishSpawnEgg;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.RegistryObject;

public class UARegistryHelper extends RegistryHelper {

	public UARegistryHelper(String modId) {
		super(modId);
	}
	
	public RegistryObject<Item> createJellyfishSpawnEggItem(String entityName, int primaryColor, int secondaryColor) {
		RegistryObject<Item> spawnEgg = this.getDeferredItemRegister().register(entityName + "_spawn_egg", () -> new ItemJellyfishSpawnEgg(primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
		this.spawnEggs.add(spawnEgg);
		return spawnEgg;
	}
	
	public <B extends Block> RegistryObject<B> createWallOrFloorBlock(String name, Supplier<? extends B> supplier, Supplier<? extends B> wallSupplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = this.getDeferredBlockRegister().register(name, supplier);
		this.getDeferredItemRegister().register(name, () -> new WallOrFloorItem(block.get(), wallSupplier.get(), new Item.Properties().group(group)));
		return block;
	}
	
	public <B extends Block> RegistryObject<B> createRareBlock(String name, Supplier<? extends B> supplier, Rarity rarity, @Nullable ItemGroup group) {
		RegistryObject<B> block = this.getDeferredBlockRegister().register(name, supplier);
		this.getDeferredItemRegister().register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(rarity).group(group)));
		return block;
	}

}