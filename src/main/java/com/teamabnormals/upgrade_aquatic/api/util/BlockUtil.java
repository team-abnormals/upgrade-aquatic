package com.teamabnormals.upgrade_aquatic.api.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtil {

	public static boolean isBlockInWater(World world, BlockPos pos) {
		if(world.getBlockState(pos).getFluidState().isTagged(FluidTags.WATER)) {
			return true;
		}
		for(Direction direction : Direction.values()) {
			if(world.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}
	
}
