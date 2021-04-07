package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.FloweringRushBlock;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class FloweringRushFeature extends Feature<NoFeatureConfig> {
	private static final BlockState FLOWERING_RUSH = UABlocks.FLOWERING_RUSH.get().getDefaultState();

	public FloweringRushFeature(Codec<NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean flag = false;

		for(int i = 0; i < 64; ++i) {
			BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(4) - rand.nextInt(4));
			if(blockpos.getY() < worldIn.getWorld().getHeight() - 2 && worldIn.getBlockState(blockpos.down()).getBlock().isIn(BlockTags.BAMBOO_PLANTABLE_ON)) {
	            if(worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.isAirBlock(blockpos.up())) {
	            	this.placeFloweringRush(worldIn, blockpos);
	            	flag = true;
	            }
			}
		}

		return flag;
	}
	
	private void placeFloweringRush(IWorld world, BlockPos pos) {
		world.setBlockState(pos, FLOWERING_RUSH.with(FloweringRushBlock.WATERLOGGED, true), 2);
		world.setBlockState(pos.up(), FLOWERING_RUSH.with(FloweringRushBlock.HALF, DoubleBlockHalf.UPPER), 2);
	}
}