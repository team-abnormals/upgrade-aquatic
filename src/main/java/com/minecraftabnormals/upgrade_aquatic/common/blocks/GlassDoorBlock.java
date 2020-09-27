package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.Direction;

public class GlassDoorBlock extends DoorBlock {

	public GlassDoorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.isIn(this) ? true : super.isSideInvisible(state, adjacentBlockState, side);
	}
}
