package com.teamabnormals.upgrade_aquatic.core.data.server.modifiers;

import com.mojang.serialization.JsonOps;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABiomeTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABiomeModifierTypes.AddCarversBiomeModifier;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAPlacedFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAWorldCarvers.UAConfiguredWorldCarvers;
import net.minecraft.core.HolderSet;
import net.minecraft.core.HolderSet.Direct;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
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

	public static JsonCodecProvider<BiomeModifier> create(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		RegistryAccess access = RegistryAccess.builtinCopy();
		Registry<Biome> biomeRegistry = access.registryOrThrow(Registry.BIOME_REGISTRY);
		Registry<PlacedFeature> placedFeatures = access.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY);
		Registry<ConfiguredWorldCarver<?>> carvers = access.registryOrThrow(Registry.CONFIGURED_CARVER_REGISTRY);
		HashMap<ResourceLocation, BiomeModifier> modifiers = new HashMap<>();

		addModifier(modifiers, "add_features/beach", new AddFeaturesBiomeModifier(tag(biomeRegistry, BiomeTags.IS_BEACH), of(placedFeatures, UAPlacedFeatures.DRIFTWOOD_BEACH, UAPlacedFeatures.PATCH_SEAROCKET, UAPlacedFeatures.BEACHGRASS_DUNES), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_features/river", new AddFeaturesBiomeModifier(tag(biomeRegistry, BiomeTags.IS_RIVER), of(placedFeatures, UAPlacedFeatures.RIVER_TREE, UAPlacedFeatures.DRIFTWOOD_RIVER, UAPlacedFeatures.PATCH_FLOWERING_RUSH), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/driftwood/swamp", new AddFeaturesBiomeModifier(tag(biomeRegistry, Tags.Biomes.IS_SWAMP), of(placedFeatures, UAPlacedFeatures.DRIFTWOOD_SWAMP), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/driftwood/rainforest_basin", new AddFeaturesBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_EXTRA_DRIFTWOOD), of(placedFeatures, UAPlacedFeatures.DRIFTWOOD_BASIN), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/driftwood/ocean", new AddFeaturesBiomeModifier(tag(biomeRegistry, BiomeTags.IS_OCEAN), of(placedFeatures, UAPlacedFeatures.DRIFTWOOD_OCEAN), GenerationStep.Decoration.VEGETAL_DECORATION));

		addModifier(modifiers, "add_feature/pickerelweed", new AddFeaturesBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_PICKERELWEED), of(placedFeatures, UAPlacedFeatures.PATCH_PICKERELWEED), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/extra_pickerelweed", new AddFeaturesBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_EXTRA_PICKERELWEED), of(placedFeatures, UAPlacedFeatures.PATCH_PICKERELWEED_EXTRA), GenerationStep.Decoration.VEGETAL_DECORATION));
		addModifier(modifiers, "add_feature/ammonite_ore", new AddFeaturesBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_AMMONITE_ORE), of(placedFeatures, UAPlacedFeatures.ORE_AMMONITE), GenerationStep.Decoration.UNDERGROUND_ORES));
		addModifier(modifiers, "add_feature/prismarine_coral", new AddFeaturesBiomeModifier(tag(biomeRegistry, BiomeTags.IS_OCEAN), of(placedFeatures, UAPlacedFeatures.PRISMARINE_CORAL), GenerationStep.Decoration.RAW_GENERATION));

		addModifier(modifiers, "add_carver/prismarine_coral", new AddCarversBiomeModifier(tag(biomeRegistry, BiomeTags.IS_OCEAN), ofCarver(carvers, UAConfiguredWorldCarvers.UNDERWATER_CANYON), GenerationStep.Carving.AIR));

		addModifier(modifiers, "add_monster/thrasher", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_THRASHER), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.THRASHER.get(), 10, 1, 2))));
		addModifier(modifiers, "add_animal/nautilus", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_NAUTILUS), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.NAUTILUS.get(), 50, 1, 4))));
		addModifier(modifiers, "add_animal/lionfish", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_LIONFISH), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.LIONFISH.get(), 15, 1, 1))));

		addModifier(modifiers, "add_animal/pike", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_PIKE), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), 11, 1, 2))));
		addModifier(modifiers, "add_animal/squid", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_SQUID), List.of(new MobSpawnSettings.SpawnerData(EntityType.SQUID, 5, 1, 2))));
		addModifier(modifiers, "add_animal/pike/extra", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_EXTRA_PIKE), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.PIKE.get(), 5, 1, 2))));
		addModifier(modifiers, "add_animal/perch", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_PERCH), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.PERCH.get(), 5, 1, 6))));

//		addModifier(modifiers, "add_animal/jellyfish/box", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_JELLYFISH), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.BOX_JELLYFISH.get(), 6, 1, 2))));
//		addModifier(modifiers, "add_animal/jellyfish/immortal", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_JELLYFISH), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.IMMORTAL_JELLYFISH.get(), 7, 1, 3))));
//		addModifier(modifiers, "add_animal/jellyfish/cassiopea", new AddSpawnsBiomeModifier(tag(biomeRegistry, UABiomeTags.HAS_CASSIOPEA_JELLYFISH), List.of(new MobSpawnSettings.SpawnerData(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), 7, 1, 3))));

		return JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, UpgradeAquatic.MOD_ID, RegistryOps.create(JsonOps.INSTANCE, access), ForgeRegistries.Keys.BIOME_MODIFIERS, modifiers);
	}

	private static HolderSet<Biome> tag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
		return new HolderSet.Named<>(biomeRegistry, tagKey);
	}

	private static void addModifier(HashMap<ResourceLocation, BiomeModifier> modifiers, String name, BiomeModifier modifier) {
		modifiers.put(new ResourceLocation(UpgradeAquatic.MOD_ID, name), modifier);
	}

	@SafeVarargs
	@SuppressWarnings("ConstantConditions")
	private static HolderSet<PlacedFeature> of(Registry<PlacedFeature> placedFeatures, RegistryObject<PlacedFeature>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}

	@SafeVarargs
	@SuppressWarnings("ConstantConditions")
	private static Direct<ConfiguredWorldCarver<?>> ofCarver(Registry<ConfiguredWorldCarver<?>> placedFeatures, RegistryObject<ConfiguredWorldCarver<?>>... features) {
		return HolderSet.direct(Stream.of(features).map(registryObject -> placedFeatures.getOrCreateHolderOrThrow(registryObject.getKey())).collect(Collectors.toList()));
	}
}