package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;

public class BlockEmbeddedAmmonite extends DirectionalBlock {

	public BlockEmbeddedAmmonite(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
	}
}
