package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.upgrade_aquatic.common.block.TallBeachgrassBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BeachgrassDunesFeature extends Feature<NoneFeatureConfiguration> {

	public BeachgrassDunesFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();

		for (BlockState blockstate = world.getBlockState(pos); (world.isEmptyBlock(pos) || blockstate.is(BlockTags.LEAVES)) && pos.getY() > 0; blockstate = world.getBlockState(pos)) {
			pos = pos.below();
		}

		int grassesPlaced = 0;

		if (pos.getY() >= world.getSeaLevel() + 6) {
			for (int j = 0; j < 128; ++j) {
				BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));
				if (world.isEmptyBlock(blockpos) && UABlocks.BEACHGRASS.get().defaultBlockState().canSurvive(world, blockpos)) {
					this.placeBeachgrass(world, blockpos, rand);
					grassesPlaced++;
				}
			}
		} else {
			return true;
		}

		return grassesPlaced > 0;
	}

	private void placeBeachgrass(LevelAccessor world, BlockPos pos, RandomSource rand) {
		if (rand.nextFloat() < 0.30F) {
			TallBeachgrassBlock plant = (TallBeachgrassBlock) UABlocks.TALL_BEACHGRASS.get();
			if (world.isEmptyBlock(pos) && world.isEmptyBlock(pos.above())) {
				DoublePlantBlock.placeAt(world, plant.defaultBlockState(), pos, 2);
			}
		} else {
			if (world.isEmptyBlock(pos)) {
				world.setBlock(pos, UABlocks.BEACHGRASS.get().defaultBlockState(), 2);
			}
		}
	}
}