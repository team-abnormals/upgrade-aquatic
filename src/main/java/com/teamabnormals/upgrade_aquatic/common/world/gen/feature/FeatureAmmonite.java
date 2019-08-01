package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class FeatureAmmonite {
	
	public static void setupGeneration() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(FeatureAmmonite::process);
	}
	
	private static void process(Biome biome) {
		if(biome.getCategory() == Category.BEACH || biome.getCategory() == Category.OCEAN) {
			CountRangeConfig placement = new CountRangeConfig(24, 20, 0, 73);
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, UABlocks.EMBEDDED_AMMONITE.getDefaultState(), 3), Placement.COUNT_BIASED_RANGE, placement));
		}
	}
	
}
