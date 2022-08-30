package com.teamabnormals.upgrade_aquatic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ConduitSupportingBlock extends Block {

	public ConduitSupportingBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return true;
	}

}