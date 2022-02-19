package com.minecraftabnormals.upgrade_aquatic.core.registry.util;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.common.items.JellyfishSpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class UAItemSubRegistryHelper extends ItemSubRegistryHelper {

	public UAItemSubRegistryHelper(RegistryHelper parent) {
		super(parent, parent.getItemSubHelper().getDeferredRegister());
	}

	public RegistryObject<JellyfishSpawnEggItem> createJellyfishSpawnEggItem(String entityName, int primaryColor, int secondaryColor) {
		JellyfishSpawnEggItem eggItem = new JellyfishSpawnEggItem(primaryColor, secondaryColor, (new Item.Properties())); // Disabled temporarily -> .group(ItemGroup.MISC)
		RegistryObject<JellyfishSpawnEggItem> spawnEgg = this.deferredRegister.register(entityName + "_spawn_egg", () -> eggItem);
		this.spawnEggs.add(eggItem);
		return spawnEgg;
	}
}