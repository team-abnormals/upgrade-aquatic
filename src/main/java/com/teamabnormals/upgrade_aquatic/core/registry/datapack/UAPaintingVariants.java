package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class UAPaintingVariants {
	public static final ResourceKey<PaintingVariant> SIGHTLESS = createKey("sightless");
	public static final ResourceKey<PaintingVariant> MONUMENT = createKey("monument");
	public static final ResourceKey<PaintingVariant> UTENSIL = createKey("utensil");
	public static final ResourceKey<PaintingVariant> COIL = createKey("coil");
	
	public static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, SIGHTLESS, 64, 32);
		register(context, MONUMENT, 64,48);
		register(context, UTENSIL, 16, 16);
		register(context, COIL, 48, 48);
	}
	
	private static ResourceKey<PaintingVariant> createKey(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(UpgradeAquatic.MOD_ID, name));
	}
	
	private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
		context.register(key, new PaintingVariant(width, height, key.location()));
	}
}
