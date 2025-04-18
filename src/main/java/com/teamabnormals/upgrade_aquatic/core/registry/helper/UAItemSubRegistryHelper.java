package com.teamabnormals.upgrade_aquatic.core.registry.helper;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.item.JellyfishSpawnEggItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class UAItemSubRegistryHelper extends ItemSubRegistryHelper {

	public UAItemSubRegistryHelper(RegistryHelper parent) {
		super(parent);
	}

	public DeferredItem<JellyfishSpawnEggItem> createJellyfishSpawnEggItem(String entityName, int primaryColor, int secondaryColor) {
		return this.deferredRegister.register(entityName + "_spawn_egg", () -> new JellyfishSpawnEggItem(primaryColor, secondaryColor, (new Item.Properties())));
	}
}