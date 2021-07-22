package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.EmbeddedAmmoniteBlock;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.*;
import com.minecraftabnormals.upgrade_aquatic.core.UAConfig;
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

	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_SHELF = FEATURES.register("prismarine_coral_shelf", () -> new PrismarineCoralShelfFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_STALACTITE = FEATURES.register("prismarine_coral_stalactite", () -> new PrismarineStalactiteFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL = FEATURES.register("prismarine_coral", () -> new PrismarineCoralFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> PICKERELWEED = FEATURES.register("pickerelweed", () -> new PickerelweedFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> SEAROCKET = FEATURES.register("searocket", () -> new SearocketFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> FLOWERING_RUSH = FEATURES.register("flowering_rush", () -> new FloweringRushFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> DRIFTWOOD = FEATURES.register("driftwood", () -> new DriftwoodFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<NoFeatureConfig>> DUNES = FEATURES.register("dunes", () -> new BeachgrassDunesFeature(NoFeatureConfig.CODEC));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> RIVER_TREE = FEATURES.register("river_tree", () -> new RiverTreeFeature(BaseTreeFeatureConfig.CODEC));

	@SubscribeEvent
	public static void onBiomeLoad(BiomeLoadingEvent event) {
		if (event.getName() == null) return;
		ResourceLocation biome = event.getName();
		Biome.Category category = event.getCategory();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		generation.addFeature(GenerationStage.Decoration.RAW_GENERATION, Configured.PRISMARINE_CORAL);

		if (category == Biome.Category.OCEAN) {
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_OCEAN);
		}

		if (category == Biome.Category.BEACH) {
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.ORE_AMMONITE);
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_BEACH);
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.PATCH_SEAROCKET);
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.BEACHGRASS_DUNES);
		}

		if (category == Biome.Category.RIVER) {
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.RIVER_TREE);
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_RIVER);
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.PATCH_FLOWERING_RUSH);
		}

		if (category == Biome.Category.SWAMP) {
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_SWAMP);
		}

		if (biome.equals(Biomes.FLOWER_FOREST.location())) {
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.PATCH_PICKERELWEED_EXTRA);
		}

		if (category == Biome.Category.BEACH || category == Biome.Category.OCEAN || biome.equals(Biomes.STONE_SHORE.location())) {
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.ORE_AMMONITE);
		}

		if ((category == Biome.Category.JUNGLE || category == Biome.Category.SWAMP || category == Biome.Category.RIVER) && !biome.toString().contains("marsh")) {
			generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Configured.PATCH_PICKERELWEED);
		}

		if (biome.toString().contains("rainforest_basin")) {
			generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_BASIN);
		}
	}

	public static final class Configs {
		public static final BaseTreeFeatureConfig RIVER_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(UABlocks.RIVER_LOG.get().defaultBlockState()), new SimpleBlockStateProvider(UABlocks.RIVER_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayerFeature(0, 0, 0))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> ORE_AMMONITE = Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, UABlocks.EMBEDDED_AMMONITE.get().defaultBlockState().setValue(EmbeddedAmmoniteBlock.FACING, Direction.getRandom(new Random())), 3)).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(20, 0, 73))).squared().count(30);
		public static final ConfiguredFeature<?, ?> BEACHGRASS_DUNES = UAFeatures.DUNES.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(UAConfig.COMMON.beachgrassFrequency.get());
		public static final ConfiguredFeature<?, ?> RIVER_TREE = UAFeatures.RIVER_TREE.get().configured(Configs.RIVER_TREE_CONFIG).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.80F, 2)));
		public static final ConfiguredFeature<?, ?> PRISMARINE_CORAL = UAFeatures.PRISMARINE_CORAL.get().configured(IFeatureConfig.NONE).decorated(Placement.CARVING_MASK.configured(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.0125F)));

		public static final ConfiguredFeature<?, ?> PATCH_SEAROCKET = UAFeatures.SEAROCKET.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.searocketFrequency.get());
		public static final ConfiguredFeature<?, ?> PATCH_PICKERELWEED = UAFeatures.PICKERELWEED.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.pickerelweedFrequency.get());
		public static final ConfiguredFeature<?, ?> PATCH_PICKERELWEED_EXTRA = UAFeatures.PICKERELWEED.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.pickerelweedExtraFrequency.get());
		public static final ConfiguredFeature<?, ?> PATCH_FLOWERING_RUSH = UAFeatures.FLOWERING_RUSH.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.floweringRushFrequency.get());

		public static final ConfiguredFeature<?, ?> DRIFTWOOD_OCEAN = UAFeatures.DRIFTWOOD.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(1);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_BEACH = UAFeatures.DRIFTWOOD.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(14);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_RIVER = UAFeatures.DRIFTWOOD.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(125);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_SWAMP = UAFeatures.DRIFTWOOD.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(21);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_BASIN = UAFeatures.DRIFTWOOD.get().configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(85);

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