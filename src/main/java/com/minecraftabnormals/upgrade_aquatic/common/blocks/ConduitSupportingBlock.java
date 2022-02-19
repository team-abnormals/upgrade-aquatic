package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ConduitSupportingBlock extends Block {

	public ConduitSupportingBlock(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return true;
	}

}