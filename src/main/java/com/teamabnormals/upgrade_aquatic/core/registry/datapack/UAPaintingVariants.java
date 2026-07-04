package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class UAPaintingVariants {
	public static final ResourceKey<PaintingVariant> SIGHTLESS = createKey("sightless");
	public static final ResourceKey<PaintingVariant> MONUMENT = createKey("monument");
	public static final ResourceKey<PaintingVariant> UTENSIL = createKey("utensil");
	public static final ResourceKey<PaintingVariant> COIL = createKey("coil");

	public static void bootstrap(BootstrapContext<PaintingVariant> context) {
		register(context, SIGHTLESS, 4, 2);
		register(context, MONUMENT, 4, 3);
		register(context, UTENSIL, 1, 1);
		register(context, COIL, 3, 3);
	}

	private static ResourceKey<PaintingVariant> createKey(String name) {
		return ResourceKey.create(Registries.PAINTING_VARIANT, UpgradeAquatic.location(name));
	}

	private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
		context.register(key, new PaintingVariant(width, height, key.location()));
	}
}
