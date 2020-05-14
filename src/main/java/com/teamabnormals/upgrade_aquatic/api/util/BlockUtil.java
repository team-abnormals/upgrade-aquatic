package com.teamabnormals.upgrade_aquatic.api.util;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BlockUtil {
	
	public static boolean isPosNotTouchingBlock(IWorld world, BlockPos pos, Block blockToCheck, Direction... blacklistedDirections) {
		for(Direction directions : Direction.values()) {
			List<Direction> blacklistedDirectionsList = Arrays.asList(blacklistedDirections);
			if(!blacklistedDirectionsList.contains(directions)) {
				if(world.getBlockState(pos.offset(directions)).getBlock() == blockToCheck) {
					return false;
				}
			}
		}
		return true;
	}
	
}
