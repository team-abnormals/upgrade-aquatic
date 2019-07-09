package com.teamabnormals.upgrade_aquatic.common.items.itemblocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;

public class ItemBlockUpsideDown extends BlockItem {

	public ItemBlockUpsideDown(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}

	@Nullable
	protected BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate1 = null;
		IWorldReader iworldreader = context.getWorld();
		BlockPos blockpos = context.getPos();
	
		for(Direction direction : context.getNearestLookingDirections()) {
			if (direction == Direction.UP) {
				BlockState blockstate2 = this.getBlock().getStateForPlacement(context);
	            if (blockstate2 != null && blockstate2.isValidPosition(iworldreader, blockpos)) {
	            	blockstate1 = blockstate2;
	            	break;
	            }
			}
		}
	
		return blockstate1 != null && iworldreader.func_217350_a(blockstate1, blockpos, ISelectionContext.dummy()) ? blockstate1 : null;
	}
	
}
