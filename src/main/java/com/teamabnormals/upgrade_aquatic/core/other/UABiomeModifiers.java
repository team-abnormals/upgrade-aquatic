package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABiomeTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABiomeModifierTypes.AddCarversBiomeModifier;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAPlacedFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAWorldCarvers.UAConfiguredWorldCarvers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddSpawnsBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UABiomeModifiers {

	public static void bootstrap(BootstapContext<BiomeModifier> context) {
		addFeature(context, "beach_vegetation", Biomes.BEACH, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_SEAROCKET, UAPlacedFeatures.BEACHGRASS_DUNES);
		addFeature(context, "river_tree", BiomeTags.IS_RIVER, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.RIVER_TREE);
		addFeature(context, "flowering_rush", Biomes.RIVER, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_FLOWERING_RUSH);
		addFeature(context, "pickerelweed", UABiomeTags.HAS_PICKERELWEED, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED);
		addFeature(context, "pickerelweed_extra", UABiomeTags.HAS_EXTRA_PICKERELWEED, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED_EXTRA);
		addFeature(context, "ammonite_ore", UABiomeTags.HAS_AMMONITE_ORE, Decoration.UNDERGROUND_ORES, UAPlacedFeatures.ORE_AMMONITE);
		addFeature(context, "prismarine_coral", BiomeTags.IS_OCEAN, Decoration.RAW_GENERATION, UAPlacedFeatures.PRISMARINE_CORAL);

		addFeature(context, "driftwood", BiomeTags.IS_OCEAN, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_OCEAN);
		addFeature(context, "driftwood_beach", BiomeTags.IS_BEACH, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_BEACH);
		addFeature(context, "driftwood_river", BiomeTags.IS_RIVER, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_RIVER);
		addFeature(context, "driftwood_swamp", Tags.Biomes.IS_SWAMP, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_SWAMP);
		addFeature(context, "driftwood_extra", UABiomeTags.HAS_EXTRA_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_EXTRA);

		addCarver(context, "underwater_canyon", BiomeTags.IS_OCEAN, Carving.AIR, UAConfiguredWorldCarvers.UNDERWATER_CANYON);

		addSpawn(context, "thrasher", UABiomeTags.HAS_THRASHER, new MobSpawnSettings.SpawnerData(UAEntityTypes.THRASHER.get(), 10, 1, 2));
		addSpawn(context, "nautilus", UABiomeTags.HAS_NAUTILUS, new MobSpawnSettings.SpawnerData(UAEntityTypes.NAUTILUS.get(), 50, 1, 4));
		addSpawn(context, "lionfish", UABiomeTags.HAS_LIONFISH, new MobSpawnSettings.SpawnerData(UAEntityTypes.LIONFISH.get(), 15, 1, 1));
		addSpawn(context, "pike", UABiomeTags.HAS_PIKE, new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), 15, 1, 3));
		addSpawn(context, "squid", UABiomeTags.HAS_SQUID, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 2, 1, 4));
		addSpawn(context, "perch", UABiomeTags.HAS_PERCH, new MobSpawnSettings.SpawnerData(UAEntityTypes.PERCH.get(), 5, 1, 6));
	}

	@SafeVarargs
	private static void addFeature(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
		register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
	}

	@SafeVarargs
	private static void addFeature(BootstapContext<BiomeModifier> context, String name, ResourceKey<Biome> biome, Decoration step, ResourceKey<PlacedFeature>... features) {
		register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(biome)), featureSet(context, features), step));
	}

	private static void addSpawn(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
		register(context, "add_spawn/" + name, () -> new AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
	}

	@SafeVarargs
	private static void addCarver(BootstapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Carving carving, ResourceKey<ConfiguredWorldCarver<?>>... features) {
		register(context, "add_carver/" + name, () -> new AddCarversBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), carverSet(context, features), carving));
	}

	private static void register(BootstapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
		context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(UpgradeAquatic.MOD_ID, name)), modifier.get());
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(BootstapContext<?> context, ResourceKey<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(key -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(key)).collect(Collectors.toList()));
	}

	@SafeVarargs
	private static HolderSet<ConfiguredWorldCarver<?>> carverSet(BootstapContext<?> context, ResourceKey<ConfiguredWorldCarver<?>>... carvers) {
		return HolderSet.direct(Stream.of(carvers).map(key -> context.lookup(Registries.CONFIGURED_CARVER).getOrThrow(key)).collect(Collectors.toList()));
	}
}