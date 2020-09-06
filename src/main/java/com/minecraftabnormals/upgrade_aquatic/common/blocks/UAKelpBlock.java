package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;

import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.KelpBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class UAKelpBlock extends KelpBlock {
	private KelpType kelpType;
	private Block kelpTopBlock;

	public UAKelpBlock(KelpType kelpType, Block kelpTopBlock, Properties props) {
		super(props);
		this.kelpType = kelpType;
		this.kelpTopBlock = kelpTopBlock;
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.kelpTopBlock);
	}

	@Override
	protected AbstractTopPlantBlock getTopPlantBlock() {
		switch (this.kelpType) {
		default:
		case TONGUE:
			return (AbstractTopPlantBlock) UABlocks.TONGUE_KELP.get();
		case THORNY:
			return (AbstractTopPlantBlock) UABlocks.THORNY_KELP.get();
		case OCHRE:
			return (AbstractTopPlantBlock) UABlocks.OCHRE_KELP.get();
		case POLAR:
			return (AbstractTopPlantBlock) UABlocks.POLAR_KELP.get();
		}
	}
}
