package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CoralWallFanBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class UACoralWallFanBlock extends CoralWallFanBlock {

	public UACoralWallFanBlock(Block deadState, Properties props) {
		super(deadState, props);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return state.getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN.get();
	}

}
