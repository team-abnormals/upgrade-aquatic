package com.teamabnormals.upgrade_aquatic.common.blocks.biorock;

import java.util.Random;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBiorockSlab extends SlabBlock {

	public BlockBiorockSlab(Properties properties) {
		super(properties);
	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if(!worldIn.isAreaLoaded(pos, 3)) return;
		
		for(int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			if(UABlocks.BIOROCK_SLAB_CONVERSION_MAP.containsKey(worldIn.getBlockState(blockpos).getBlock())) {
				BlockState newState = UABlocks.BIOROCK_SLAB_CONVERSION_MAP.get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState().with(TYPE, state.get(TYPE)).with(WATERLOGGED, state.get(WATERLOGGED));
				worldIn.setBlockState(pos, newState);
			}
		}
	}

}
