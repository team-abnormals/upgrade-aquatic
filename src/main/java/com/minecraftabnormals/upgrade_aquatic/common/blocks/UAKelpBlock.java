package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;
import net.minecraft.world.level.block.state.BlockState;

public class UAKelpBlock extends KelpPlantBlock {
	private final KelpType kelpType;
	private final Block kelpTopBlock;

	public UAKelpBlock(KelpType kelpType, Block kelpTopBlock, Properties props) {
		super(props);
		this.kelpType = kelpType;
		this.kelpTopBlock = kelpTopBlock;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.kelpTopBlock);
	}

	@Override
	protected GrowingPlantHeadBlock getHeadBlock() {
		return switch (this.kelpType) {
			case TONGUE -> (GrowingPlantHeadBlock) UABlocks.TONGUE_KELP.get();
			case THORNY -> (GrowingPlantHeadBlock) UABlocks.THORNY_KELP.get();
			case OCHRE -> (GrowingPlantHeadBlock) UABlocks.OCHRE_KELP.get();
			case POLAR -> (GrowingPlantHeadBlock) UABlocks.POLAR_KELP.get();
		};
	}
}
