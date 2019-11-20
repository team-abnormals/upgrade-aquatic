package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class BlockStairsBase extends StairsBlock {

	public BlockStairsBase(BlockState state, Properties properties) {
		super(() -> state, properties);
	}
	
}
