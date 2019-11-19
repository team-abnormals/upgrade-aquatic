package com.teamabnormals.upgrade_aquatic.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
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
	
	public static boolean canPlace(World world, PlayerEntity player, BlockPos pos, BlockState state) {
		ISelectionContext selectionContext = player == null ? ISelectionContext.dummy() : ISelectionContext.forEntity(player);
		VoxelShape voxelshape = state.getCollisionShape(world, pos, selectionContext);
		return voxelshape.isEmpty() && world.checkNoEntityCollision(null, voxelshape.withOffset(pos.getX(), pos.getY(), pos.getZ()));
	}
	
	public static SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}
	
}
