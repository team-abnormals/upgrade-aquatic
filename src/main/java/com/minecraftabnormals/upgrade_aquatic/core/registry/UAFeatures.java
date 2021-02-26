package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.EmbeddedAmmoniteBlock;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.*;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_SHELF = FEATURES.register("prismarine_coral_shelf", () -> new PrismarineCoralShelfFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_STALACTITE = FEATURES.register("prismarine_coral_stalactite", () -> new PrismarineStalactiteFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL = FEATURES.register("prismarine_coral", () -> new PrismarineCoralFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PICKERELWEED = FEATURES.register("pickerelweed", () -> new PickerelweedFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SEAROCKET = FEATURES.register("searocket", () -> new SearocketFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> FLOWERING_RUSH = FEATURES.register("flowering_rush", () -> new FloweringRushFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DRIFTWOOD = FEATURES.register("driftwood", () -> new DriftwoodFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DUNES = FEATURES.register("dunes", () -> new BeachgrassDunesFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> RIVER_TREE = FEATURES.register("river_tree", () -> new RiverTreeFeature(BaseTreeFeatureConfig.CODEC));

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (event.getName() == null) return;
		ResourceLocation biome = event.getName();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		generation.withFeature(GenerationStage.Decoration.RAW_GENERATION, Configured.PRISMARINE_CORAL);

		if (event.getCategory() == Biome.Category.OCEAN) {
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_OCEAN);
		}

		if (event.getCategory() == Biome.Category.BEACH) {
			generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.ORE_AMMONITE);
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_BEACH);
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.PATCH_SEAROCKET);
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.BEACHGRASS_DUNES);
		}

		if (event.getCategory() == Biome.Category.RIVER) {
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.RIVER_TREE);
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_RIVER);
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.PATCH_FLOWERING_RUSH);
		}

		if (event.getCategory() == Biome.Category.SWAMP) {
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_SWAMP);
		}

		if (biome.equals(Biomes.FLOWER_FOREST.getLocation())) {
			generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.PATCH_PICKERELWEED_EXTRA);
		}

		if (event.getCategory() == Biome.Category.BEACH || event.getCategory() == Biome.Category.OCEAN || biome.equals(Biomes.STONE_SHORE.getLocation())) {
			generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.ORE_AMMONITE);
		}

		if (event.getCategory() != Biome.Category.OCEAN && event.getCategory() != Biome.Category.BEACH && event.getCategory() != Biome.Category.DESERT && event.getCategory() != Biome.Category.ICY) {
			generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.PATCH_PICKERELWEED);
		}

		if (biome.toString().contains("rainforest_basin")) {
			generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_BASIN);
		}
	}

	public static final class Configs {
		public static final BaseTreeFeatureConfig RIVER_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(UABlocks.RIVER_LOG.get().getDefaultState()), new SimpleBlockStateProvider(UABlocks.RIVER_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayerFeature(0, 0, 0))).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> ORE_AMMONITE = Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, UABlocks.EMBEDDED_AMMONITE.get().getDefaultState().with(EmbeddedAmmoniteBlock.FACING, Direction.getRandomDirection(new Random())), 3)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(20, 0, 73))).square().func_242731_b(30);
		public static final ConfiguredFeature<?, ?> BEACHGRASS_DUNES = UAFeatures.DUNES.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(18);
		public static final ConfiguredFeature<?, ?> RIVER_TREE = UAFeatures.RIVER_TREE.get().withConfiguration(Configs.RIVER_TREE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.80F, 2)));
		public static final ConfiguredFeature<?, ?> PRISMARINE_CORAL = UAFeatures.PRISMARINE_CORAL.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.0125F)));

		public static final ConfiguredFeature<?, ?> PATCH_SEAROCKET = UAFeatures.SEAROCKET.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(15);
		public static final ConfiguredFeature<?, ?> PATCH_PICKERELWEED = UAFeatures.PICKERELWEED.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(28);
		public static final ConfiguredFeature<?, ?> PATCH_PICKERELWEED_EXTRA = UAFeatures.PICKERELWEED.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(90);
		public static final ConfiguredFeature<?, ?> PATCH_FLOWERING_RUSH = UAFeatures.FLOWERING_RUSH.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(15);

		public static final ConfiguredFeature<?, ?> DRIFTWOOD_OCEAN = UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(1);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_BEACH = UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(14);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_RIVER = UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(125);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_SWAMP = UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(21);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_BASIN = UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(85);

		public static void registerConfiguredFeatures() {
			register("ore_ammonite", ORE_AMMONITE);
			register("beachgrass_dunes", BEACHGRASS_DUNES);
			register("river_tree", RIVER_TREE);
			register("prismarine_coral", PRISMARINE_CORAL);

			register("patch_searocket", PATCH_SEAROCKET);
			register("patch_pickerelweed", PATCH_PICKERELWEED);
			register("patch_pickerelweed_extra", PATCH_PICKERELWEED_EXTRA);
			register("flowering_rush", PATCH_FLOWERING_RUSH);

			register("driftwood_ocean", DRIFTWOOD_OCEAN);
			register("driftwood_beach", DRIFTWOOD_BEACH);
			register("driftwood_river", DRIFTWOOD_RIVER);
			register("driftwood_swamp", DRIFTWOOD_SWAMP);
			register("driftwood_basin", DRIFTWOOD_BASIN);
		}

		private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(UpgradeAquatic.MOD_ID, name), configuredFeature);
		}
	}
}