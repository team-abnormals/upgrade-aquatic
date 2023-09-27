package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintTallFlowerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FloweringRushBlock extends BlueprintTallFlowerBlock implements SimpleWaterloggedBlock, BonemealableBlock {
	private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public FloweringRushBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		return SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, WATERLOGGED);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return state.is(BlockTags.DIRT) || state.is(BlockTags.SAND) || state.is(Blocks.GRAVEL);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		BlockState groundState = level.getBlockState(pos.below());
		FluidState fluidState = state.getFluidState();
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return fluidState.getAmount() >= 8 && this.mayPlaceOn(groundState, level, pos.below()) && level.getBlockState(pos.above()).getBlock() == this && level.getBlockState(pos.above()).getValue(HALF) == DoubleBlockHalf.UPPER ? state : Blocks.AIR.defaultBlockState();
		} else {
			return fluidState.isEmpty() && level.getBlockState(pos.below()).getBlock() == this && level.getBlockState(pos.below()).getValue(HALF) == DoubleBlockHalf.LOWER ? state : Blocks.AIR.defaultBlockState();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			BlockPos belowPos = pos.below();
			FluidState fluidState = level.getFluidState(pos);
			return level.getBlockState(pos.above()).isAir() && this.mayPlaceOn(level.getBlockState(belowPos), level, belowPos) && fluidState.is(FluidTags.WATER) && fluidState.getAmount() >= 8;
		} else {
			BlockState belowState = level.getBlockState(pos.below());
			if (state.getBlock() != this) return super.canSurvive(state, level, pos);
			return belowState.getBlock() == this && belowState.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
		BlockState state = super.getStateForPlacement(context);
		if (state != null) {
			return state.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
		}
		return null;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}