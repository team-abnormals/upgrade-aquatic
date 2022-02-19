package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BaseCoralFanBlock;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;

public class UACoralFanDeadBlock extends BaseCoralFanBlock {
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.DEAD_HORN_CORAL_FAN);

	public UACoralFanDeadBlock() {
		super(Block.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).noCollission().strength(0F));
	}

	public UACoralFanDeadBlock(Block.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader world, BlockPos pos, BlockPos conduit) {
		return state.getBlock() == UABlocks.ELDER_PRISMARINE_CORAL_FAN.get();
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}
}