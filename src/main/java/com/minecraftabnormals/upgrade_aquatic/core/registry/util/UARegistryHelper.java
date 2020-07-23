package com.minecraftabnormals.upgrade_aquatic.core.registry.util;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.minecraftabnormals.upgrade_aquatic.common.items.InjectedBlockItem;
import com.minecraftabnormals.upgrade_aquatic.common.items.JellyfishSpawnEggItem;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class UARegistryHelper extends RegistryHelper {

	public UARegistryHelper(String modId) {
		super(modId);
	}
	
	public RegistryObject<Item> createJellyfishSpawnEggItem(String entityName, int primaryColor, int secondaryColor) {
		RegistryObject<Item> spawnEgg = this.getDeferredItemRegister().register(entityName + "_spawn_egg", () -> new JellyfishSpawnEggItem(primaryColor, secondaryColor, new Item.Properties().group(ItemGroup.MISC)));
		this.spawnEggs.add(spawnEgg);
		return spawnEgg;
	}
	
	public <B extends Block> RegistryObject<B> createInjectedBlock(String name, Item followItem, Supplier<? extends B> supplier, @Nullable ItemGroup group) {
		RegistryObject<B> block = this.getDeferredBlockRegister().register(name, supplier);
		this.getDeferredItemRegister().register(name, () -> new InjectedBlockItem(followItem, block.get(), new Item.Properties().group(group)));
		return block;
	}
	
}