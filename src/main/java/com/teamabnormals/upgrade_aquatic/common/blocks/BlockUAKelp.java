package com.teamabnormals.upgrade_aquatic.common.blocks;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.KelpBlock;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockUAKelp extends KelpBlock {
	@SuppressWarnings("unused")
	private KelpType kelpType;
	private Block kelpTopBlock;
	
	public BlockUAKelp(KelpType kelpType, Block kelpTopBlock, Properties props) {
		super((KelpTopBlock) kelpTopBlock, props);
		this.kelpType = kelpType;
		this.kelpTopBlock = kelpTopBlock;
	}
	
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.kelpTopBlock);
	}
}
