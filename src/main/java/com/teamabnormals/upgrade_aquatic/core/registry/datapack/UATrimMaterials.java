package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class UATrimMaterials {
	public static final ResourceKey<TrimMaterial> TOOTH = createKey("tooth");

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, TOOTH, UAItems.THRASHER_TOOTH.get(), Style.EMPTY.withColor(0xE8F4F0), Map.of());
	}

	private static ResourceKey<TrimMaterial> createKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, UpgradeAquatic.location(name));
	}

	private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
		ResourceLocation location = key.location();
		context.register(key, TrimMaterial.create(location.getNamespace() + "_" + location.getPath(), item, -1.0F, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style), overrides));
	}
}