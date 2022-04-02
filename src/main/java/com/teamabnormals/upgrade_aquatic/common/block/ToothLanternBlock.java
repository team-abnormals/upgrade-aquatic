package com.teamabnormals.upgrade_aquatic.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class ToothLanternBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final VoxelShape[] SHAPES = new VoxelShape[]{
			Shapes.or( // UP
					box(7.0F, 0.0F, 7.0D, 9.0D, 4.0D, 9.0D),
					box(4.0F, 4.0F, 4.0F, 12.0F, 5.0F, 12.0F),
					box(5.0F, 5.0F, 5.0F, 11.0F, 13.0F, 11.0F),
					box(4.0F, 13.0F, 4.0F, 12.0F, 14.0F, 12.0F)),
			Shapes.or( // DOWN
					box(7.0F, 12.0F, 7.0D, 9.0D, 16.0D, 9.0D),
					box(4.0F, 2.0F, 4.0F, 12.0F, 3.0F, 12.0F),
					box(5.0F, 3.0F, 5.0F, 11.0F, 11.0F, 11.0F),
					box(4.0F, 11.0F, 4.0F, 12.0F, 12.0F, 12.0F)),
			Shapes.or( // NORTH
					box(7.0F, 12.0F, 10.0D, 9.0D, 16.0D, 12.0D),
					box(7.0F, 14.0F, 10.0D, 9.0D, 16.0D, 16.0D),
					box(4.0F, 2.0F, 7.0F, 12.0F, 3.0F, 15.0F),
					box(5.0F, 3.0F, 8.0F, 11.0F, 11.0F, 14.0F),
					box(4.0F, 11.0F, 7.0F, 12.0F, 12.0F, 15.0F)),
			Shapes.or( // EAST
					box(4.0F, 12.0F, 7.0D, 6.0D, 16.0D, 9.0D),
					box(0.0F, 14.0F, 7.0D, 6.0D, 16.0D, 9.0D),
					box(1.0F, 2.0F, 4.0F, 9.0F, 3.0F, 12.0F),
					box(2.0F, 3.0F, 5.0F, 8.0F, 11.0F, 11.0F),
					box(1.0F, 11.0F, 4.0F, 9.0F, 12.0F, 12.0F)),
			Shapes.or( // SOUTH
					box(7.0F, 12.0F, 4.0D, 9.0D, 16.0D, 6.0D),
					box(7.0F, 14.0F, 0.0D, 9.0D, 16.0D, 6.0D),
					box(4.0F, 2.0F, 1.0F, 12.0F, 3.0F, 9.0F),
					box(5.0F, 3.0F, 2.0F, 11.0F, 11.0F, 8.0F),
					box(4.0F, 11.0F, 1.0F, 12.0F, 12.0F, 9.0F)),
			Shapes.or( // WEST
					box(10.0F, 12.0F, 7.0D, 12.0D, 16.0D, 9.0D),
					box(10.0F, 14.0F, 7.0D, 16.0D, 16.0D, 9.0D),
					box(7.0F, 2.0F, 4.0F, 15.0F, 3.0F, 12.0F),
					box(8.0F, 3.0F, 5.0F, 14.0F, 11.0F, 11.0F),
					box(7.0F, 11.0F, 4.0F, 15.0F, 12.0F, 12.0F)),
	};

	public ToothLanternBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case UP -> SHAPES[0];
			default -> SHAPES[1];
			case NORTH -> SHAPES[2];
			case EAST -> SHAPES[3];
			case SOUTH -> SHAPES[4];
			case WEST -> SHAPES[5];
		};
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace();
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		return Block.canSupportCenter(world, blockpos, direction);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		return this.canSurvive(state, world, currentPos) ? state : Blocks.AIR.defaultBlockState();
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.setValue(FACING, mirror.mirror(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
	}
}