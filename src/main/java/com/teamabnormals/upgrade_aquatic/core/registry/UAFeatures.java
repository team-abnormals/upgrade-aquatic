package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.levelgen.feature.*;
import com.teamabnormals.upgrade_aquatic.common.levelgen.treedecorators.MulberryVinesDecorator;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
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
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UpgradeAquatic.MOD_ID);
	public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PRISMARINE_CORAL = FEATURES.register("prismarine_coral", () -> new PrismarineCoralFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PICKERELWEED = FEATURES.register("pickerelweed", () -> new PickerelweedFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> SEAROCKET = FEATURES.register("searocket", () -> new SearocketFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOWERING_RUSH = FEATURES.register("flowering_rush", () -> new FloweringRushFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> DRIFTWOOD = FEATURES.register("driftwood", () -> new DriftwoodFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> DUNES = FEATURES.register("dunes", () -> new BeachgrassDunesFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<TreeConfiguration>> RIVER_TREE = FEATURES.register("river_tree", () -> new RiverTreeFeature(TreeConfiguration.CODEC));

	public static final RegistryObject<TreeDecoratorType<?>> MULBERRY_VINES = TREE_DECORATORS.register("mulberry_vines", () -> new TreeDecoratorType<>(MulberryVinesDecorator.CODEC));

	public static final class Configs {
		public static final TreeConfiguration RIVER_TREE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(UABlocks.RIVER_LOG.get()), new StraightTrunkPlacer(3, 1, 1), BlockStateProvider.simple(UABlocks.RIVER_LEAVES.get()), new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0), new TwoLayersFeatureSize(0, 0, 0)).ignoreVines().decorators(List.of(new MulberryVinesDecorator())).build();
	}

	public static final class UAConfiguredFeatures {
		public static final ResourceKey<ConfiguredFeature<?, ?>> RIVER_TREE = createKey("river_tree");
		public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_AMMONITE = createKey("ore_ammonite");
		public static final ResourceKey<ConfiguredFeature<?, ?>> BEACHGRASS_DUNES = createKey("beachgrass_dunes");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PRISMARINE_CORAL = createKey("prismarine_coral");

		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SEAROCKET = createKey("patch_searocket");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PICKERELWEED = createKey("patch_pickerelweed");
		public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FLOWERING_RUSH = createKey("patch_flowering_rush");
		public static final ResourceKey<ConfiguredFeature<?, ?>> DRIFTWOOD = createKey("driftwood");

		public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
			register(context, RIVER_TREE, UAFeatures.RIVER_TREE.get(), Configs.RIVER_TREE_CONFIG);
			register(context, ORE_AMMONITE, Feature.ORE, new OreConfiguration(new BlockMatchTest(Blocks.STONE), UABlocks.EMBEDDED_AMMONITE.get().defaultBlockState(), 3));
			register(context, BEACHGRASS_DUNES, UAFeatures.DUNES.get(), FeatureConfiguration.NONE);
			register(context, PRISMARINE_CORAL, UAFeatures.PRISMARINE_CORAL.get(), FeatureConfiguration.NONE);

			register(context, PATCH_SEAROCKET, UAFeatures.SEAROCKET.get(), FeatureConfiguration.NONE);
			register(context, PATCH_PICKERELWEED, UAFeatures.PICKERELWEED.get(), FeatureConfiguration.NONE);
			register(context, PATCH_FLOWERING_RUSH, UAFeatures.FLOWERING_RUSH.get(), FeatureConfiguration.NONE);
			register(context, DRIFTWOOD, UAFeatures.DRIFTWOOD.get(), FeatureConfiguration.NONE);
		}

		public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
			return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(UpgradeAquatic.MOD_ID, name));
		}

		public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
			context.register(key, new ConfiguredFeature<>(feature, config));
		}
	}

	public static final class UAPlacedFeatures {
		public static final ResourceKey<PlacedFeature> ORE_AMMONITE = createKey("ore_ammonite");
		public static final ResourceKey<PlacedFeature> BEACHGRASS_DUNES = createKey("beachgrass_dunes");
		public static final ResourceKey<PlacedFeature> RIVER_TREE = createKey("river_tree");
		public static final ResourceKey<PlacedFeature> PRISMARINE_CORAL = createKey("prismarine_coral");

		public static final ResourceKey<PlacedFeature> PATCH_SEAROCKET = createKey("patch_searocket");
		public static final ResourceKey<PlacedFeature> PATCH_PICKERELWEED = createKey("patch_pickerelweed");
		public static final ResourceKey<PlacedFeature> PATCH_PICKERELWEED_EXTRA = createKey("patch_pickerelweed_extra");
		public static final ResourceKey<PlacedFeature> PATCH_FLOWERING_RUSH = createKey("patch_flowering_rush");

		public static final ResourceKey<PlacedFeature> DRIFTWOOD_OCEAN = createKey("driftwood_ocean");
		public static final ResourceKey<PlacedFeature> DRIFTWOOD_BEACH = createKey("driftwood_beach");
		public static final ResourceKey<PlacedFeature> DRIFTWOOD_RIVER = createKey("driftwood_river");
		public static final ResourceKey<PlacedFeature> DRIFTWOOD_SWAMP = createKey("driftwood_swamp");
		public static final ResourceKey<PlacedFeature> DRIFTWOOD_EXTRA = createKey("driftwood_extra");

		public static void bootstrap(BootstapContext<PlacedFeature> context) {
			register(context, ORE_AMMONITE, UAConfiguredFeatures.ORE_AMMONITE, commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(73))));
			register(context, BEACHGRASS_DUNES, UAConfiguredFeatures.BEACHGRASS_DUNES, VegetationPlacements.worldSurfaceSquaredWithCount(14));
			register(context, RIVER_TREE, UAConfiguredFeatures.RIVER_TREE, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.5F, 2)));
			register(context, PRISMARINE_CORAL, UAConfiguredFeatures.PRISMARINE_CORAL, CarvingMaskPlacement.forStep(Carving.AIR), RarityFilter.onAverageOnceEvery(256), BiomeFilter.biome());

			register(context, PATCH_SEAROCKET, UAConfiguredFeatures.PATCH_SEAROCKET, RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_PICKERELWEED, UAConfiguredFeatures.PATCH_PICKERELWEED, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_PICKERELWEED_EXTRA, UAConfiguredFeatures.PATCH_PICKERELWEED, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, PATCH_FLOWERING_RUSH, UAConfiguredFeatures.PATCH_FLOWERING_RUSH, RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

			register(context, DRIFTWOOD_OCEAN, UAConfiguredFeatures.DRIFTWOOD, RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, DRIFTWOOD_BEACH, UAConfiguredFeatures.DRIFTWOOD, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
			register(context, DRIFTWOOD_RIVER, UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(2));
			register(context, DRIFTWOOD_SWAMP, UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(4));
			register(context, DRIFTWOOD_EXTRA, UAConfiguredFeatures.DRIFTWOOD, VegetationPlacements.worldSurfaceSquaredWithCount(5));
		}

		private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
			return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
		}

		private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
			return orePlacement(CountPlacement.of(count), modifier);
		}

		public static ResourceKey<PlacedFeature> createKey(String name) {
			return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(UpgradeAquatic.MOD_ID, name));
		}

		public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
			context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), modifiers));
		}

		public static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
			register(context, key, feature, List.of(modifiers));
		}
	}
}