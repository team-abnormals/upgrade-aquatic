package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.TallBeachgrassBlock;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class BeachgrassDunesFeature extends Feature<NoFeatureConfig> {

	public BeachgrassDunesFeature(Codec<NoFeatureConfig> config) {
		super(config);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		for(BlockState blockstate = world.getBlockState(pos); (world.isAirBlock(pos) || blockstate.isIn(BlockTags.LEAVES)) && pos.getY() > 0; blockstate = world.getBlockState(pos)) {
			pos = pos.down();
		}
		
		int grassesPlaced = 0;
		
		if(pos.getY() >= world.getSeaLevel() + 6) {
			for(int j = 0; j < 128; ++j) {
				BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));
				if(world.isAirBlock(blockpos) && UABlocks.BEACHGRASS.get().getDefaultState().isValidPosition(world, blockpos)) {
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
			TallBeachgrassBlock beachGrass = (TallBeachgrassBlock) UABlocks.TALL_BEACHGRASS.get();
			if(world.isAirBlock(pos) && world.isAirBlock(pos.up())) {
				beachGrass.placeAt(world, pos, 2);
			}
		} else {
			if(world.isAirBlock(pos)) {
				world.setBlockState(pos, UABlocks.BEACHGRASS.get().getDefaultState(), 2);
			}
		}
	}
}