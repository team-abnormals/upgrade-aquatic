package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DeadCoralPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class UACoralDeadBlock extends DeadCoralPlantBlock {

	public UACoralDeadBlock(Properties props) {
		super(props);
	}
	
	@Override
	public ToolType getHarvestTool(BlockState p_getHarvestTool_1_) {
		return ToolType.PICKAXE;
	}
	
	@Override
	public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
		return state.getBlock() == UABlocks.ELDER_PRISMARINE_CORAL.get();
	}
	
}