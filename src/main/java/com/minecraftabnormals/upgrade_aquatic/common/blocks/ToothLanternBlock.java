package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class ToothLanternBlock extends Block implements IWaterLoggable {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final VoxelShape[] SHAPES = new VoxelShape[] {
		VoxelShapes.or( // UP
				box(7.0F, 0.0F, 7.0D, 9.0D, 4.0D, 9.0D), 
				box(4.0F, 4.0F, 4.0F, 12.0F, 5.0F, 12.0F), 
				box(5.0F, 5.0F, 5.0F, 11.0F, 13.0F, 11.0F), 
				box(4.0F, 13.0F, 4.0F, 12.0F, 14.0F, 12.0F)), 
		VoxelShapes.or( // DOWN
				box(7.0F, 12.0F, 7.0D, 9.0D, 16.0D, 9.0D), 
				box(4.0F, 2.0F, 4.0F, 12.0F, 3.0F, 12.0F), 
				box(5.0F, 3.0F, 5.0F, 11.0F, 11.0F, 11.0F), 
				box(4.0F, 11.0F, 4.0F, 12.0F, 12.0F, 12.0F)), 
		VoxelShapes.or( // NORTH
				box(7.0F, 12.0F, 10.0D, 9.0D, 16.0D, 12.0D),
				box(7.0F, 14.0F, 10.0D, 9.0D, 16.0D, 16.0D), 
				box(4.0F, 2.0F, 7.0F, 12.0F, 3.0F, 15.0F), 
				box(5.0F, 3.0F, 8.0F, 11.0F, 11.0F, 14.0F), 
				box(4.0F, 11.0F, 7.0F, 12.0F, 12.0F, 15.0F)), 
		VoxelShapes.or( // EAST
				box(4.0F, 12.0F, 7.0D, 6.0D, 16.0D, 9.0D),
				box(0.0F, 14.0F, 7.0D, 6.0D, 16.0D, 9.0D), 
				box(1.0F, 2.0F, 4.0F, 9.0F, 3.0F, 12.0F), 
				box(2.0F, 3.0F, 5.0F, 8.0F, 11.0F, 11.0F), 
				box(1.0F, 11.0F, 4.0F, 9.0F, 12.0F, 12.0F)), 
		VoxelShapes.or( // SOUTH
				box(7.0F, 12.0F, 4.0D, 9.0D, 16.0D, 6.0D),
				box(7.0F, 14.0F, 0.0D, 9.0D, 16.0D, 6.0D), 
				box(4.0F, 2.0F, 1.0F, 12.0F, 3.0F, 9.0F), 
				box(5.0F, 3.0F, 2.0F, 11.0F, 11.0F, 8.0F), 
				box(4.0F, 11.0F, 1.0F, 12.0F, 12.0F, 9.0F)), 
		VoxelShapes.or( // WEST
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
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.getValue(FACING)) {
		case UP:
			return SHAPES[0];
		case DOWN:
			default:
			return SHAPES[1];
		case NORTH:
			return SHAPES[2];	
		case EAST:
		    return SHAPES[3]; 
		case SOUTH:
	        return SHAPES[4];
	    case WEST:
	        return SHAPES[5];
		}
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getClickedFace();
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8);
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		return Block.canSupportCenter(world, blockpos, direction);
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
	}
}