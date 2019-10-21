package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.SlabType;

public class BlockSlabBase extends SlabBlock {
	
	public BlockSlabBase(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, Boolean.valueOf(false)));
	}
}
