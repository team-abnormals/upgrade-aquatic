package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBeachgrassTall;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureDunes extends Feature<NoFeatureConfig> {

	public FeatureDunes(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		for(BlockState blockstate = world.getBlockState(pos); (world.isAirBlock(pos) || blockstate.isIn(BlockTags.LEAVES)) && pos.getY() > 0; blockstate = world.getBlockState(pos)) {
			pos = pos.down();
		}
		
		int grassesPlaced = 0;
		
		if(pos.getY() >= world.getSeaLevel() + 6) {
			for(int j = 0; j < 128; ++j) {
				BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));
				if(world.isAirBlock(blockpos) && UABlocks.BEACHGRASS.getDefaultState().isValidPosition(world, blockpos)) {
					this.placeBeachgrass(world, blockpos, rand);
					grassesPlaced++;
				}
			}	
		} else {
			return true;
		}

		return grassesPlaced > 0;
	}
	
	private void placeBeachgrass(IWorld world, BlockPos pos, Random rand) {
		if(rand.nextFloat() < 0.30F) {
			BlockBeachgrassTall beachGrass = (BlockBeachgrassTall) UABlocks.TALL_BEACHGRASS;
			if(world.isAirBlock(pos) && world.isAirBlock(pos.up())) {
				beachGrass.placeAt(world, pos, 2);
			}
		} else {
			if(world.isAirBlock(pos)) {
				world.setBlockState(pos, UABlocks.BEACHGRASS.getDefaultState(), 2);
			}
		}
	}
	
	public static void addDunes() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(FeatureDunes::process);
	}
	
	private static void process(Biome biome) {
		if(biome.getCategory() == Category.BEACH) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.DUNES.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(18)));
		}
	}

}