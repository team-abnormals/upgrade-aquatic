package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class UAKelpTopBlock extends KelpTopBlock {
	private final KelpType kelpType;

	public UAKelpTopBlock(KelpType kelpType, Properties props) {
		super(props);
		this.kelpType = kelpType;
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.canSurvive(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		} else {
			BlockPos blockpos = pos.above();
			BlockState blockstate = worldIn.getBlockState(blockpos);
			if (blockstate.getBlock() == Blocks.WATER && state.getValue(AGE) < 25 && random.nextDouble() < this.kelpType.getGrowChance()) {
				worldIn.setBlockAndUpdate(blockpos, state.cycle(AGE));
			}
		}
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		Block block = blockstate.getBlock();
		if (block == Blocks.MAGMA_BLOCK) {
			return false;
		} else {
			return block == this || block == this.getPlantBlock() || blockstate.isFaceSturdy(worldIn, blockpos, Direction.UP);
		}
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos)) {
			if (facing == Direction.DOWN) {
				return Blocks.AIR.defaultBlockState();
			}
			worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
		}

		if (facing == Direction.UP && facingState.getBlock() == this) {
			return this.getPlantBlock().defaultBlockState();
		} else {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			return stateIn;
		}
	}

	public Block getPlantBlock() {
		switch (this.kelpType) {
			default:
			case TONGUE:
				return UABlocks.TONGUE_KELP_PLANT.get();
			case THORNY:
				return UABlocks.THORNY_KELP_PLANT.get();
			case OCHRE:
				return UABlocks.OCHRE_KELP_PLANT.get();
			case POLAR:
				return UABlocks.POLAR_KELP_PLANT.get();
		}
	}

}
