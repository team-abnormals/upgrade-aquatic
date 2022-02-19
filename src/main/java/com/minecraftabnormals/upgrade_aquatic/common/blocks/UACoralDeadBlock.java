package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UACoralDeadBlock extends BaseCoralPlantBlock {

	public UACoralDeadBlock(Properties props) {
		super(props);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return state.getBlock() == UABlocks.ELDER_PRISMARINE_CORAL.get();
	}

}