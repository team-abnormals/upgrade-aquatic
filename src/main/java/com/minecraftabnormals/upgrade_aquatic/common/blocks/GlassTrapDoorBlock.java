package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.util.Direction;

public class GlassTrapDoorBlock extends TrapDoorBlock {

	public GlassTrapDoorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.isIn(this) ? true : super.isSideInvisible(state, adjacentBlockState, side);
	}
}
