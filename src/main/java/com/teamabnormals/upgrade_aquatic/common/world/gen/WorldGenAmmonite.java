package com.teamabnormals.upgrade_aquatic.common.world.gen;

import java.util.List;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class WorldGenAmmonite {
	
	public static void setupGeneration() {
		List<Biome> generatableBiomes = Lists.newArrayList();
		generatableBiomes.add(Biomes.BEACH);
		generatableBiomes.add(Biomes.SNOWY_BEACH);
		generatableBiomes.add(Biomes.MUSHROOM_FIELD_SHORE);
		generatableBiomes.add(Biomes.STONE_SHORE);
		generatableBiomes.add(Biomes.DEEP_OCEAN);
		generatableBiomes.add(Biomes.OCEAN);
		generatableBiomes.add(Biomes.DEEP_LUKEWARM_OCEAN);
		generatableBiomes.add(Biomes.LUKEWARM_OCEAN);
		generatableBiomes.add(Biomes.DEEP_WARM_OCEAN);
		generatableBiomes.add(Biomes.WARM_OCEAN);
		generatableBiomes.add(Biomes.DEEP_COLD_OCEAN);
		generatableBiomes.add(Biomes.COLD_OCEAN);
		generatableBiomes.add(Biomes.FROZEN_OCEAN);
		generatableBiomes.add(Biomes.DEEP_FROZEN_OCEAN);
		for(Biome biome : generatableBiomes) {
			// Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
			CountRangeConfig placement = new CountRangeConfig(24, 20, 0, 73);
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, UABlocks.EMBEDDED_AMMONITE.getDefaultState(), 3), Placement.COUNT_BIASED_RANGE, placement));
		}
		
	}
	
}
