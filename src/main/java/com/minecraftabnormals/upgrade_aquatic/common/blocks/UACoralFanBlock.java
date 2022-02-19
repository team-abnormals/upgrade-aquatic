package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CoralFanBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class UACoralFanBlock extends CoralFanBlock {
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.HORN_CORAL_FAN);

	public UACoralFanBlock(Block deadState, Properties props) {
		super(deadState, props);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return state.getBlock() == UABlocks.PRISMARINE_CORAL_FAN.get();
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}
}