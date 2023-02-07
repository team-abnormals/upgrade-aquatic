package com.teamabnormals.upgrade_aquatic.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABiomeTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABiomeModifierTypes.AddCarversBiomeModifier;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAPlacedFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAWorldCarvers.UAConfiguredWorldCarvers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddFeaturesBiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddSpawnsBiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UABiomeModifierProvider {
	private static final RegistryAccess ACCESS = RegistryAccess.builtinCopy();
	private static final Registry<Biome> BIOME_REGISTRY = ACCESS.registryOrThrow(Registry.BIOME_REGISTRY);
	private static final Registry<PlacedFeature> PLACED_FEATURES = ACCESS.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
	private static final Registry<ConfiguredWorldCarver<?>> CARVERS = ACCESS.registryOrThrow(Registry.CONFIGURED_CARVER_REGISTRY);
	private static final HashMap<ResourceLocation, BiomeModifier> MODIFIERS = new HashMap<>();

	public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		addFeature("beach_vegetation", Biomes.BEACH, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_SEAROCKET, UAPlacedFeatures.BEACHGRASS_DUNES);
		addFeature("river_tree", BiomeTags.IS_RIVER, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.RIVER_TREE);
		addFeature("flowering_rush", Biomes.RIVER, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_FLOWERING_RUSH);
		addFeature("pickerelweed", UABiomeTags.HAS_PICKERELWEED, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED);
		addFeature("pickerelweed_extra", UABiomeTags.HAS_EXTRA_PICKERELWEED, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED_EXTRA);
		addFeature("ammonite_ore", UABiomeTags.HAS_AMMONITE_ORE, Decoration.UNDERGROUND_ORES, UAPlacedFeatures.ORE_AMMONITE);
		addFeature("prismarine_coral", BiomeTags.IS_OCEAN, Decoration.RAW_GENERATION, UAPlacedFeatures.PRISMARINE_CORAL);

		addFeature("driftwood", BiomeTags.IS_OCEAN, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_OCEAN);
		addFeature("driftwood_beach", BiomeTags.IS_BEACH, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_BEACH);
		addFeature("driftwood_river", BiomeTags.IS_RIVER, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_RIVER);
		addFeature("driftwood_swamp", Tags.Biomes.IS_SWAMP, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_SWAMP);
		addFeature("driftwood_extra", UABiomeTags.HAS_EXTRA_DRIFTWOOD, Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_EXTRA);

		addCarver("underwater_canyon", BiomeTags.IS_OCEAN, Carving.AIR, UAConfiguredWorldCarvers.UNDERWATER_CANYON);

		addSpawn("thrasher", UABiomeTags.HAS_THRASHER, new MobSpawnSettings.SpawnerData(UAEntityTypes.THRASHER.get(), 10, 1, 2));
		addSpawn("nautilus", UABiomeTags.HAS_NAUTILUS, new MobSpawnSettings.SpawnerData(UAEntityTypes.NAUTILUS.get(), 50, 1, 4));
		addSpawn("lionfish", UABiomeTags.HAS_LIONFISH, new MobSpawnSettings.SpawnerData(UAEntityTypes.LIONFISH.get(), 15, 1, 1));
		addSpawn("pike", UABiomeTags.HAS_PIKE, new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), 20, 1, 2));
		addSpawn("squid", UABiomeTags.HAS_SQUID, new MobSpawnSettings.SpawnerData(EntityType.SQUID, 5, 1, 2));
		addSpawn("pike_extra", UABiomeTags.HAS_EXTRA_PIKE, new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), 10, 1, 2));
		addSpawn("perch", UABiomeTags.HAS_PERCH, new MobSpawnSettings.SpawnerData(UAEntityTypes.PERCH.get(), 5, 1, 6));
//		addSpawn("box_jellyfish", UABiomeTags.HAS_JELLYFISH, new MobSpawnSettings.SpawnerData(UAEntityTypes.BOX_JELLYFISH.get(), 6, 1, 2));
//		addSpawn("immortal_jellyfish", UABiomeTags.HAS_JELLYFISH, new MobSpawnSettings.SpawnerData(UAEntityTypes.IMMORTAL_JELLYFISH.get(), 7, 1, 3));
//		addSpawn("cassiopea_jellyfish", UABiomeTags.HAS_CASSIOPEA_JELLYFISH, new MobSpawnSettings.SpawnerData(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), 7, 1, 3));

		return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, UpgradeAquatic.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, ACCESS), ForgeRegistries.Keys.BIOME_MODIFIERS, MODIFIERS);
	}

	@SafeVarargs
	private static void addFeature(String name, ResourceKey<Biome> biome, GenerationStep.Decoration step, RegistryObject<PlacedFeature>... features) {
		addFeature(name, biomeSet(biome), step, features);
	}

	@SafeVarargs
	private static void addFeature(String name, TagKey<Biome> biomes, GenerationStep.Decoration step, RegistryObject<PlacedFeature>... features) {
		addFeature(name, new HolderSet.Named<>(BIOME_REGISTRY, biomes), step, features);
	}

	@SafeVarargs
	private static void addFeature(String name, HolderSet<Biome> biomes, GenerationStep.Decoration step, RegistryObject<PlacedFeature>... features) {
		addModifier("add_feature/" + name, new AddFeaturesBiomeModifier(biomes, featureSet(features), step));
	}

	@SafeVarargs
	private static void addCarver(String name, TagKey<Biome> tagKey, GenerationStep.Carving step, RegistryObject<ConfiguredWorldCarver<?>>... carvers) {
		addModifier("add_carver/" + name, new AddCarversBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), carverSet(carvers), step));
	}

	private static void addSpawn(String name, TagKey<Biome> tagKey, MobSpawnSettings.SpawnerData... spawns) {
		addModifier("add_spawn/" + name, new AddSpawnsBiomeModifier(new HolderSet.Named<>(BIOME_REGISTRY, tagKey), List.of(spawns)));
	}

	private static void addModifier(String name, BiomeModifier modifier) {
		MODIFIERS.put(new ResourceLocation(UpgradeAquatic.MOD_ID, name), modifier);
	}

	@SafeVarargs
	private static HolderSet<Biome> biomeSet(ResourceKey<Biome>... biomes) {
		return HolderSet.direct(Stream.of(biomes).map(BIOME_REGISTRY::getOrCreateHolderOrThrow).collect(Collectors.toList()));
	}

	@SafeVarargs
	private static HolderSet<PlacedFeature> featureSet(RegistryObject<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> PLACED_FEATURES.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}

	@SafeVarargs
	private static HolderSet<ConfiguredWorldCarver<?>> carverSet(RegistryObject<ConfiguredWorldCarver<?>>... carvers) {
		return HolderSet.direct(Stream.of(carvers).map(registryObject -> CARVERS.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}
}