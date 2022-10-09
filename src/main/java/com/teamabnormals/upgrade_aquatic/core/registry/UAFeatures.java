package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.common.levelgen.feature.*;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Carving;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PRISMARINE_CORAL = FEATURES.register("prismarine_coral", () -> new PrismarineCoralFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PICKERELWEED = FEATURES.register("pickerelweed", () -> new PickerelweedFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SEAROCKET = FEATURES.register("searocket", () -> new SearocketFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOWERING_RUSH = FEATURES.register("flowering_rush", () -> new FloweringRushFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> DRIFTWOOD = FEATURES.register("driftwood", () -> new DriftwoodFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> DUNES = FEATURES.register("dunes", () -> new BeachgrassDunesFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> RIVER_TREE = FEATURES.register("river_tree", () -> new RiverTreeFeature(TreeConfiguration.CODEC));

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (event.getName() == null) return;
		ResourceLocation biome = event.getName();
		BiomeCategory category = event.getCategory();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		if (category == BiomeCategory.OCEAN) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_OCEAN);
			generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, UAPlacedFeatures.PRISMARINE_CORAL);
			generation.addCarver(Carving.AIR, UAWorldCarvers.UAConfiguredWorldCarvers.UNDERWATER_CANYON.getHolder().get());
		}

		if (category == BiomeCategory.BEACH && !DataUtil.matchesKeys(biome, Biomes.STONY_SHORE)) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_BEACH);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_SEAROCKET);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.BEACHGRASS_DUNES);
		}

		if (category == BiomeCategory.RIVER) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.RIVER_TREE);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_RIVER);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_FLOWERING_RUSH);
		}

		if (category == BiomeCategory.SWAMP) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_SWAMP);
		}

		if (DataUtil.matchesKeys(biome, Biomes.FLOWER_FOREST)) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED_EXTRA);
		}

		if (category == BiomeCategory.BEACH || category == BiomeCategory.OCEAN || DataUtil.matchesKeys(biome, Biomes.STONY_SHORE)) {
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, UAPlacedFeatures.ORE_AMMONITE);
		}

		if ((category == BiomeCategory.JUNGLE || category == BiomeCategory.SWAMP || category == BiomeCategory.RIVER) && !biome.toString().contains("marsh")) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.PATCH_PICKERELWEED);
		}

		if (biome.toString().contains("rainforest_basin")) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, UAPlacedFeatures.DRIFTWOOD_BASIN);
		}
	}

	public static final class Configs {
		public static final TreeConfiguration RIVER_TREE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UABlocks.RIVER_LOG.get()), new StraightTrunkPlacer(3, 1, 1), BlockStateProvider.simple(UABlocks.RIVER_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().build();
	}

	public static final class UAConfiguredFeatures {
		public static final Holder<ConfiguredFeature<?, ?>> ORE_AMMONITE = register("ore_ammonite", Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, UABlocks.EMBEDDED_AMMONITE.get().defaultBlockState(), 3));
		public static final Holder<ConfiguredFeature<?, ?>> BEACHGRASS_DUNES = register("beachgrass_dunes", UAFeatures.DUNES.get(), FeatureConfiguration.NONE);
		public static final Holder<ConfiguredFeature<?, ?>> RIVER_TREE = register("river_tree", UAFeatures.RIVER_TREE.get(), Configs.RIVER_TREE_CONFIG);
		public static final Holder<ConfiguredFeature<?, ?>> PRISMARINE_CORAL = register("prismarine_coral", UAFeatures.PRISMARINE_CORAL.get(), FeatureConfiguration.NONE);

		public static final Holder<ConfiguredFeature<?, ?>> PATCH_SEAROCKET = register("patch_searocket", UAFeatures.SEAROCKET.get(), FeatureConfiguration.NONE);
		public static final Holder<ConfiguredFeature<?, ?>> PATCH_PICKERELWEED = register("patch_pickerelweed", UAFeatures.PICKERELWEED.get(), FeatureConfiguration.NONE);
		public static final Holder<ConfiguredFeature<?, ?>> PATCH_FLOWERING_RUSH = register("patch_flowering_rush", UAFeatures.FLOWERING_RUSH.get(), FeatureConfiguration.NONE);
		public static final Holder<ConfiguredFeature<?, ?>> DRIFTWOOD = register("driftwood", UAFeatures.DRIFTWOOD.get(), FeatureConfiguration.NONE);

		public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<?, ?>> register(String name, F feature, FC config) {
			return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(UpgradeAquatic.MOD_ID, name), new ConfiguredFeature<>(feature, config));
		}
	}

	public static final class UAPlacedFeatures {
		public static final Holder<PlacedFeature> ORE_AMMONITE = register("ore_ammonite", UAConfiguredFeatures.ORE_AMMONITE, commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(73))));
		public static final Holder<PlacedFeature> BEACHGRASS_DUNES = register("beachgrass_dunes", UAConfiguredFeatures.BEACHGRASS_DUNES, VegetationPlacements.worldSurfaceSquaredWithCount(14));
		public static final Holder<PlacedFeature> RIVER_TREE = register("trees_river", UAConfiguredFeatures.RIVER_TREE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.5F, 2)));
		public static final Holder<PlacedFeature> PRISMARINE_CORAL = register("prismarine_coral", UAConfiguredFeatures.PRISMARINE_CORAL, CarvingMaskPlacement.forStep(Carving.AIR), RarityFilter.onAverageOnceEvery(256), BiomeFilter.biome());

		public static final Holder<PlacedFeature> PATCH_SEAROCKET = register("patch_searocket", UAConfiguredFeatures.PATCH_SEAROCKET, RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final Holder<PlacedFeature> PATCH_PICKERELWEED = register("patch_pickerelweed", UAConfiguredFeatures.PATCH_PICKERELWEED, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final Holder<PlacedFeature> PATCH_PICKERELWEED_EXTRA = register("patch_pickerelweed_extra", UAConfiguredFeatures.PATCH_PICKERELWEED, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final Holder<PlacedFeature> PATCH_FLOWERING_RUSH = register("patch_flowering_rush", UAConfiguredFeatures.PATCH_FLOWERING_RUSH, RarityFilter.onAverageOnceEvery(12), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		public static final Holder<PlacedFeature> DRIFTWOOD_OCEAN = register("driftwood_ocean", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final Holder<PlacedFeature> DRIFTWOOD_BEACH = register("driftwood_beach", UAConfiguredFeatures.DRIFTWOOD, RarityFilter.onAverageOnceEvery(7), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final Holder<PlacedFeature> DRIFTWOOD_RIVER = register("driftwood_river", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final Holder<PlacedFeature> DRIFTWOOD_SWAMP = register("driftwood_swamp", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final Holder<PlacedFeature> DRIFTWOOD_BASIN = register("driftwood_basin", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(2));

		private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
			return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
		}

		private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
			return orePlacement(CountPlacement.of(count), modifier);
		}

		public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
			return register(name, configuredFeature, List.of(placementModifiers));
		}

		public static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> placementModifiers) {
			return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(UpgradeAquatic.MOD_ID, name), new PlacedFeature(Holder.hackyErase(configuredFeature), placementModifiers));
		}
	}
}