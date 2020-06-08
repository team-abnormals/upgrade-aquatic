package com.teamabnormals.upgrade_aquatic.common.blocks;

import com.teamabnormals.abnormals_core.core.utils.ItemStackUtils;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralFinBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BlockUACoralFan extends CoralFinBlock {

	public BlockUACoralFan(Block deadState, Properties props) {
		super(deadState, props);
	}
	
	@Override
	public boolean isConduitFrame(BlockState state, IWorldReader world, BlockPos pos, BlockPos conduit) {
		return state.getBlock() == UABlocks.PRISMARINE_CORAL_FAN.get();
	}
	
	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if(ItemStackUtils.isInGroup(this.asItem(), group)) {
			int targetIndex = ItemStackUtils.findIndexOfItem(Items.HORN_CORAL_FAN, items);
			if(targetIndex != -1) {
				items.add(targetIndex + 1, new ItemStack(this));
			} else {
				super.fillItemGroup(group, items);
			}
		}
	}

}