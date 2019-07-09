package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralPlantBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class BlockCoralShowerDead extends DeadCoralPlantBlock {

	public BlockCoralShowerDead() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0F));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && !stateIn.isValidPosition(worldIn, currentPos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			this.updateIfDry(stateIn, worldIn, currentPos);
			if (stateIn.get(WATERLOGGED)) {
				worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			}

			return facing == Direction.UP && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
		}
	}
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.up();
		return Block.hasSolidSide(worldIn.getBlockState(blockpos), worldIn, blockpos, Direction.DOWN);
	}
	
}
