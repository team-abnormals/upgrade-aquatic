package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import net.minecraft.block.AbstractBlock.Properties;

public class PrismarineCoralBlock extends CoralBlock {

	public PrismarineCoralBlock(Block block, Properties properties) {
		super(block, properties);
	}
	
	@Override
	public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
		return true;
	}

}