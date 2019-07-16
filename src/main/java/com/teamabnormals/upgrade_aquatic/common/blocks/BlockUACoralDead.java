package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DeadCoralPlantBlock;
import net.minecraftforge.common.ToolType;

public class BlockUACoralDead extends DeadCoralPlantBlock {

	public BlockUACoralDead(Properties props) {
		super(props);
	}
	
	@Override
	public ToolType getHarvestTool(BlockState p_getHarvestTool_1_) {
		return ToolType.PICKAXE;
	}
	
}
