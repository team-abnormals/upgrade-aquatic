package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABiomeTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABiomeModifierTypes.AddCarversBiomeModifier;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAPlacedFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAWorldCarvers.UAConfiguredWorldCarvers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddSpawnsBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UABiomeModifiers {

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		addFeature(context, "beachgrass", UABiomeTags.HAS_BEACHGRASS, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.BEACHGRASS_DUNES);
		addFeature(context, "searocket", UABiomeTags.HAS_SEAROCKET, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_SEAROCKET);
		addFeature(context, "river_tree", UABiomeTags.HAS_RIVER_TREE, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.RIVER_TREE);
		addFeature(context, "flowering_rush", UABiomeTags.HAS_FLOWERING_RUSH, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_FLOWERING_RUSH);
		addFeature(context, "pickerelweed", UABiomeTags.HAS_PICKERELWEED, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED);
		addFeature(context, "pickerelweed_extra", UABiomeTags.HAS_EXTRA_PICKERELWEED, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED_EXTRA);
		addFeature(context, "ammonite_ore", UABiomeTags.HAS_AMMONITE_ORE, Decoration.UNDERGROUND_ORES, UAPlacedFeatures.ORE_AMMONITE);
		addFeature(context, "prismarine_coral", UABiomeTags.HAS_PRISMARINE_CORAL, Decoration.RAW_GENERATION, UAPlacedFeatures.PRISMARINE_CORAL);

		addFeature(context, "driftwood", UABiomeTags.HAS_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_OCEAN);
		addFeature(context, "driftwood_beach", UABiomeTags.HAS_BEACH_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_BEACH);
		addFeature(context, "driftwood_river", UABiomeTags.HAS_RIVER_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_RIVER);
		addFeature(context, "driftwood_swamp", UABiomeTags.HAS_SWAMP_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_SWAMP);
		addFeature(context, "driftwood_extra", UABiomeTags.HAS_EXTRA_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_EXTRA);

		addCarver(context, "underwater_canyon", UABiomeTags.HAS_UNDERWATER_CANYON, Carving.AIR, UAConfiguredWorldCarvers.UNDERWATER_CANYON);

		addSpawn(context, "thrasher", UABiomeTags.HAS_THRASHER, new MobSpawnSettings.SpawnerData(UAEntityTypes.THRASHER.get(), 10, 1, 2));
		addSpawn(context, "nautilus", UABiomeTags.HAS_NAUTILUS, new MobSpawnSettings.SpawnerData(UAEntityTypes.NAUTILUS.get(), 50, 1, 4));
		addSpawn(context, "lionfish", UABiomeTags.HAS_LIONFISH, new MobSpawnSettings.SpawnerData(UAEntityTypes.LIONFISH.get(), 15, 1, 1));
		addSpawn(context, "pike", UABiomeTags.HAS_PIKE, new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), 28, 1, 3));
		addSpawn(context, "squid", UABiomeTags.HAS_SQUID, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 2, 1, 4));
		addSpawn(context, "perch", UABiomeTags.HAS_PERCH, new MobSpawnSettings.SpawnerData(UAEntityTypes.PERCH.get(), 5, 1, 6));
	}

	@SafeVarargs
	private static void addFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step, ResourceKey<PlacedFeature>... features) {
		register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
	}

	private static void addSpawn(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
		register(context, "add_spawn/" + name, () -> new AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
	}

	@SafeVarargs
	private static void addCarver(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Carving carving, ResourceKey<ConfiguredWorldCarver<?>>... features) {
		register(context, "add_carver/" + name, () -> new AddCarversBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), carverSet(context, features), carving));
	}

	private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
		context.register(ResourceKey.create(Keys.BIOME_MODIFIERS, UpgradeAquatic.location(name)), modifier.get());
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(key -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(key)).collect(Collectors.toList()));
	}

	@SafeVarargs
	private static HolderSet<ConfiguredWorldCarver<?>> carverSet(BootstrapContext<?> context, ResourceKey<ConfiguredWorldCarver<?>>... carvers) {
		return HolderSet.direct(Stream.of(carvers).map(key -> context.lookup(Registries.CONFIGURED_CARVER).getOrThrow(key)).collect(Collectors.toList()));
	}
}