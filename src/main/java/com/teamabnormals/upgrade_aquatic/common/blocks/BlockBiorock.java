package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBiorock extends Block {
	private boolean chiseled;

	public BlockBiorock(Properties properties, boolean chiseled) {
		super(properties);
		this.chiseled = chiseled;
	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if(!worldIn.isAreaLoaded(pos, 3)) return;
		
		for(int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			if(chiseled) {
				if(UABlocks.CHISELED_BIOROCK_CONVERSION_MAP.containsKey(worldIn.getBlockState(blockpos).getBlock())) {
					worldIn.setBlockState(pos, UABlocks.CHISELED_BIOROCK_CONVERSION_MAP.get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState());
				}
			} else {
				if(UABlocks.BIOROCK_CONVERSION_MAP.containsKey(worldIn.getBlockState(blockpos).getBlock())) {
					worldIn.setBlockState(pos, UABlocks.BIOROCK_CONVERSION_MAP.get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState());
				}
			}
		}
	}
}
