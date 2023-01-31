package com.teamabnormals.upgrade_aquatic.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.upgrade_aquatic.common.levelgen.feature.*;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.valueproviders.ConstantInt;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

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

	public static final class Configs {
		public static final TreeConfiguration RIVER_TREE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UABlocks.RIVER_LOG.get()), new StraightTrunkPlacer(3, 1, 1), BlockStateProvider.simple(UABlocks.RIVER_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().build();
	}

	public static final class UAConfiguredFeatures {
		public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, UpgradeAquatic.MOD_ID);

		public static final RegistryObject<ConfiguredFeature<OreConfiguration, ?>> ORE_AMMONITE = register("ore_ammonite", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OreFeatures.NATURAL_STONE, UABlocks.EMBEDDED_AMMONITE.get().defaultBlockState(), 3)));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> BEACHGRASS_DUNES = register("beachgrass_dunes", () -> new ConfiguredFeature<>(UAFeatures.DUNES.get(), FeatureConfiguration.NONE));
		public static final RegistryObject<ConfiguredFeature<TreeConfiguration, ?>> RIVER_TREE = register("river_tree", () -> new ConfiguredFeature<>(UAFeatures.RIVER_TREE.get(), Configs.RIVER_TREE_CONFIG));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PRISMARINE_CORAL = register("prismarine_coral", () -> new ConfiguredFeature<>(UAFeatures.PRISMARINE_CORAL.get(), FeatureConfiguration.NONE));

		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_SEAROCKET = register("patch_searocket", () -> new ConfiguredFeature<>(UAFeatures.SEAROCKET.get(), FeatureConfiguration.NONE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_PICKERELWEED = register("patch_pickerelweed", () -> new ConfiguredFeature<>(UAFeatures.PICKERELWEED.get(), FeatureConfiguration.NONE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_FLOWERING_RUSH = register("patch_flowering_rush", () -> new ConfiguredFeature<>(UAFeatures.FLOWERING_RUSH.get(), FeatureConfiguration.NONE));
		public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> DRIFTWOOD = register("driftwood", () -> new ConfiguredFeature<>(UAFeatures.DRIFTWOOD.get(), FeatureConfiguration.NONE));

		private static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String name, Supplier<ConfiguredFeature<FC, F>> feature) {
			return CONFIGURED_FEATURES.register(name, feature);
		}
	}

	public static final class UAPlacedFeatures {
		public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, UpgradeAquatic.MOD_ID);

		public static final RegistryObject<PlacedFeature> ORE_AMMONITE = register("ore_ammonite", UAConfiguredFeatures.ORE_AMMONITE, commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(73))));
		public static final RegistryObject<PlacedFeature> BEACHGRASS_DUNES = register("beachgrass_dunes", UAConfiguredFeatures.BEACHGRASS_DUNES, VegetationPlacements.worldSurfaceSquaredWithCount(14));
		public static final RegistryObject<PlacedFeature> RIVER_TREE = register("trees_river", UAConfiguredFeatures.RIVER_TREE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.5F, 2)));
		public static final RegistryObject<PlacedFeature> PRISMARINE_CORAL = register("prismarine_coral", UAConfiguredFeatures.PRISMARINE_CORAL, CarvingMaskPlacement.forStep(Carving.AIR), RarityFilter.onAverageOnceEvery(256), BiomeFilter.biome());

		public static final RegistryObject<PlacedFeature> PATCH_SEAROCKET = register("patch_searocket", UAConfiguredFeatures.PATCH_SEAROCKET, RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> PATCH_PICKERELWEED = register("patch_pickerelweed", UAConfiguredFeatures.PATCH_PICKERELWEED, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> PATCH_PICKERELWEED_EXTRA = register("patch_pickerelweed_extra", UAConfiguredFeatures.PATCH_PICKERELWEED, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> PATCH_FLOWERING_RUSH = register("patch_flowering_rush", UAConfiguredFeatures.PATCH_FLOWERING_RUSH, RarityFilter.onAverageOnceEvery(12), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

		public static final RegistryObject<PlacedFeature> DRIFTWOOD_OCEAN = register("driftwood_ocean", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final RegistryObject<PlacedFeature> DRIFTWOOD_BEACH = register("driftwood_beach", UAConfiguredFeatures.DRIFTWOOD, RarityFilter.onAverageOnceEvery(7), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
		public static final RegistryObject<PlacedFeature> DRIFTWOOD_RIVER = register("driftwood_river", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final RegistryObject<PlacedFeature> DRIFTWOOD_SWAMP = register("driftwood_swamp", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(1));
		public static final RegistryObject<PlacedFeature> DRIFTWOOD_BASIN = register("driftwood_basin", UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(2));

		private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
			return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
		}

		private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
			return orePlacement(CountPlacement.of(count), modifier);
		}

		public static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... placementModifiers) {
			return register(name, configuredFeature, List.of(placementModifiers));
		}

		private static RegistryObject<PlacedFeature> register(String name, RegistryObject<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
			return PLACED_FEATURES.register(name, () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) feature.getHolder().get(), ImmutableList.copyOf(placementModifiers)));
		}
	}
}