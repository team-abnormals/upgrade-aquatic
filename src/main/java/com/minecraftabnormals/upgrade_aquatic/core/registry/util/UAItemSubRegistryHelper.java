package com.minecraftabnormals.upgrade_aquatic.core.registry.util;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.common.items.JellyfishSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class UAItemSubRegistryHelper extends ItemSubRegistryHelper {

	public UAItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public RegistryObject<JellyfishSpawnEggItem> createJellyfishSpawnEggItem(String entityName, int primaryColor, int secondaryColor) {
		JellyfishSpawnEggItem eggItem = new JellyfishSpawnEggItem(primaryColor, secondaryColor, (new Item.Properties()).group(ItemGroup.MISC));
		RegistryObject<JellyfishSpawnEggItem> spawnEgg = this.deferredRegister.register(entityName + "_spawn_egg", () -> eggItem);
		this.spawnEggs.add(eggItem);
		return spawnEgg;
	}
}