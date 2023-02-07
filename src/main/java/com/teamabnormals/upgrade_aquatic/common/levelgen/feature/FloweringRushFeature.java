package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.upgrade_aquatic.common.block.FloweringRushBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FloweringRushFeature extends Feature<NoneFeatureConfiguration> {
	public FloweringRushFeature(Codec<NoneFeatureConfiguration> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel worldIn = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		boolean flag = false;

		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(4) - rand.nextInt(4));
			if (blockpos.getY() < worldIn.getLevel().getMaxBuildHeight() - 2 && worldIn.getBlockState(blockpos.below()).is(BlockTags.BAMBOO_PLANTABLE_ON)) {
				if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.isEmptyBlock(blockpos.above())) {
					this.placeFloweringRush(worldIn, blockpos);
					flag = true;
				}
			}
		}

		return flag;
	}

	private void placeFloweringRush(LevelAccessor world, BlockPos pos) {
		world.setBlock(pos, UABlocks.FLOWERING_RUSH.get().defaultBlockState().setValue(FloweringRushBlock.WATERLOGGED, true), 2);
		world.setBlock(pos.above(), UABlocks.FLOWERING_RUSH.get().defaultBlockState().setValue(FloweringRushBlock.HALF, DoubleBlockHalf.UPPER), 2);
	}
}