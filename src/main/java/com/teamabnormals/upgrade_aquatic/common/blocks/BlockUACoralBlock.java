package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlock;
import net.minecraftforge.common.ToolType;

public class BlockUACoralBlock extends CoralBlock {

	public BlockUACoralBlock(Block deadState, Properties builder) {
		super(deadState, builder);
	}

	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
}
