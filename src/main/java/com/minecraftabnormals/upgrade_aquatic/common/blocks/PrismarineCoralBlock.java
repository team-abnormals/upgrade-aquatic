package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PrismarineCoralBlock extends CoralBlock {

	public PrismarineCoralBlock(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return true;
	}

}