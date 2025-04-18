package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeVariant;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public final class UARegistries {
	public static final ResourceKey<Registry<PikeVariant>> PIKE_VARIANT = create("pike_variant");

	public static void registerRegistries(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(PIKE_VARIANT, PikeVariant.DIRECT_CODEC, PikeVariant.DIRECT_CODEC);
	}

	private static <T> ResourceKey<Registry<T>> create(String name) {
		return ResourceKey.createRegistryKey(UpgradeAquatic.location(name));
	}
}