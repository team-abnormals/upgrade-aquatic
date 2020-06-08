package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BlockPrismarineRod extends RotatedPillarBlock {

	public BlockPrismarineRod(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
		return true;
	}

}