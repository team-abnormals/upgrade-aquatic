package com.minecraftabnormals.upgrade_aquatic.core.registry.util;

import com.minecraftabnormals.upgrade_aquatic.common.items.JellyfishSpawnEggItem;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

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
}