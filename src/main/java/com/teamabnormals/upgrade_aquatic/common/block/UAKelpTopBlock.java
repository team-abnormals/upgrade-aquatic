package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class UAKelpTopBlock extends KelpBlock {
	private final KelpType kelpType;

	public UAKelpTopBlock(KelpType kelpType, Properties props) {
		super(props);
		this.kelpType = kelpType;
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
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

	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
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
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos)) {
			if (facing == Direction.DOWN) {
				return Blocks.AIR.defaultBlockState();
			}
			worldIn.scheduleTick(currentPos, this, 1);
		}

		if (facing == Direction.UP && facingState.getBlock() == this) {
			return this.getPlantBlock().defaultBlockState();
		} else {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			return stateIn;
		}
	}

	public Block getPlantBlock() {
		return switch (this.kelpType) {
			case TONGUE -> UABlocks.TONGUE_KELP_PLANT.get();
			case THORNY -> UABlocks.THORNY_KELP_PLANT.get();
			case OCHRE -> UABlocks.OCHRE_KELP_PLANT.get();
			case POLAR -> UABlocks.POLAR_KELP_PLANT.get();
		};
	}

}
