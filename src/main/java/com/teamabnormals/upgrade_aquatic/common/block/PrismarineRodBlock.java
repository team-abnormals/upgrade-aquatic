package com.teamabnormals.upgrade_aquatic.common.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class PrismarineRodBlock extends RotatedPillarBlock {

	public PrismarineRodBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return true;
	}

}