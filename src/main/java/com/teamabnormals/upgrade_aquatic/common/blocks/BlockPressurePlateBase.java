package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraftforge.common.ToolType;

public class BlockPressurePlateBase extends PressurePlateBlock {

	public BlockPressurePlateBase(Sensitivity sensitivity, Properties props) {
		super(sensitivity, props);
	}

	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
	
}
