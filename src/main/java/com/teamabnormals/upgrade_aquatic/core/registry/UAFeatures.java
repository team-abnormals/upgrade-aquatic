package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.block.EmbeddedAmmoniteBlock;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.*;
import com.teamabnormals.upgrade_aquatic.common.levelgen.feature.*;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.*;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.CarvingMaskDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

import net.minecraft.data.worldgen.Features;
import net.minecraft.util.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PRISMARINE_CORAL_SHELF = FEATURES.register("prismarine_coral_shelf", () -> new PrismarineCoralShelfFeature(NoneFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> PRISMARINE_CORAL_STALACTITE = FEATURES.register("prismarine_coral_stalactite", () -> new PrismarineStalactiteFeature(NoneFeatureConfiguration.CODEC));
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
		Biome.BiomeCategory category = event.getCategory();
		BiomeGenerationSettingsBuilder generation = event.getGeneration();

		generation.addFeature(GenerationStep.Decoration.RAW_GENERATION, Configured.PRISMARINE_CORAL);

		if (category == Biome.BiomeCategory.OCEAN) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_OCEAN);
		}

		if (category == Biome.BiomeCategory.BEACH) {
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.ORE_AMMONITE);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_BEACH);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.PATCH_SEAROCKET);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.BEACHGRASS_DUNES);
		}

		if (category == Biome.BiomeCategory.RIVER) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.RIVER_TREE);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_RIVER);
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.PATCH_FLOWERING_RUSH);
		}

		if (category == Biome.BiomeCategory.SWAMP) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_SWAMP);
		}

		if (biome.equals(Biomes.FLOWER_FOREST.location())) {
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.PATCH_PICKERELWEED_EXTRA);
		}

		if (category == Biome.BiomeCategory.BEACH || category == Biome.BiomeCategory.OCEAN || biome.equals(Biomes.STONE_SHORE.location())) {
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.ORE_AMMONITE);
		}

		if ((category == Biome.BiomeCategory.JUNGLE || category == Biome.BiomeCategory.SWAMP || category == Biome.BiomeCategory.RIVER) && !biome.toString().contains("marsh")) {
			generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Configured.PATCH_PICKERELWEED);
		}

		if (biome.toString().contains("rainforest_basin")) {
			generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Configured.DRIFTWOOD_BASIN);
		}
	}

	public static final class Configs {
		public static final TreeConfiguration RIVER_TREE_CONFIG = (new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider(UABlocks.RIVER_LOG.get().defaultBlockState()), new SimpleStateProvider(UABlocks.RIVER_LEAVES.get().defaultBlockState()), new BlobFoliagePlacer(UniformInt.fixed(0), UniformInt.fixed(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().heightmap(Heightmap.Types.MOTION_BLOCKING).build();
	}

	public static final class Configured {
		public static final ConfiguredFeature<?, ?> ORE_AMMONITE = Feature.ORE.configured(new OreConfiguration(OreConfiguration.Predicates.NATURAL_STONE, UABlocks.EMBEDDED_AMMONITE.get().defaultBlockState().setValue(EmbeddedAmmoniteBlock.FACING, Direction.getRandom(new Random())), 3)).decorated(FeatureDecorator.RANGE.configured(new RangeDecoratorConfiguration(20, 0, 73))).squared().count(30);
		public static final ConfiguredFeature<?, ?> BEACHGRASS_DUNES = UAFeatures.DUNES.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(UAConfig.COMMON.beachgrassFrequency.get());
		public static final ConfiguredFeature<?, ?> RIVER_TREE = UAFeatures.RIVER_TREE.get().configured(Configs.RIVER_TREE_CONFIG).decorated(Features.Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.80F, 2)));
		public static final ConfiguredFeature<?, ?> PRISMARINE_CORAL = UAFeatures.PRISMARINE_CORAL.get().configured(FeatureConfiguration.NONE).decorated(FeatureDecorator.CARVING_MASK.configured(new CarvingMaskDecoratorConfiguration(GenerationStep.Carving.LIQUID, 0.0125F)));

		public static final ConfiguredFeature<?, ?> PATCH_SEAROCKET = UAFeatures.SEAROCKET.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.searocketFrequency.get());
		public static final ConfiguredFeature<?, ?> PATCH_PICKERELWEED = UAFeatures.PICKERELWEED.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.pickerelweedFrequency.get());
		public static final ConfiguredFeature<?, ?> PATCH_PICKERELWEED_EXTRA = UAFeatures.PICKERELWEED.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.pickerelweedExtraFrequency.get());
		public static final ConfiguredFeature<?, ?> PATCH_FLOWERING_RUSH = UAFeatures.FLOWERING_RUSH.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.ADD_32).decorated(Features.Decorators.HEIGHTMAP_SQUARE).count(UAConfig.COMMON.floweringRushFrequency.get());

		public static final ConfiguredFeature<?, ?> DRIFTWOOD_OCEAN = UAFeatures.DRIFTWOOD.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(1);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_BEACH = UAFeatures.DRIFTWOOD.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(14);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_RIVER = UAFeatures.DRIFTWOOD.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(125);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_SWAMP = UAFeatures.DRIFTWOOD.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(21);
		public static final ConfiguredFeature<?, ?> DRIFTWOOD_BASIN = UAFeatures.DRIFTWOOD.get().configured(FeatureConfiguration.NONE).decorated(Features.Decorators.HEIGHTMAP_DOUBLE_SQUARE).count(85);

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

		private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(UpgradeAquatic.MOD_ID, name), configuredFeature);
		}
	}
}