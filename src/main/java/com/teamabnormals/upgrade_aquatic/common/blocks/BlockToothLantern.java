package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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
public class BlockToothLantern extends Block implements IWaterLoggable {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final VoxelShape[] SHAPES = new VoxelShape[] {
		VoxelShapes.or( // UP
				makeCuboidShape(7.0F, 0.0F, 7.0D, 9.0D, 4.0D, 9.0D), 
				makeCuboidShape(4.0F, 4.0F, 4.0F, 12.0F, 5.0F, 12.0F), 
				makeCuboidShape(5.0F, 5.0F, 5.0F, 11.0F, 13.0F, 11.0F), 
				makeCuboidShape(4.0F, 13.0F, 4.0F, 12.0F, 14.0F, 12.0F)), 
		VoxelShapes.or( // DOWN
				makeCuboidShape(7.0F, 12.0F, 7.0D, 9.0D, 16.0D, 9.0D), 
				makeCuboidShape(4.0F, 2.0F, 4.0F, 12.0F, 3.0F, 12.0F), 
				makeCuboidShape(5.0F, 3.0F, 5.0F, 11.0F, 11.0F, 11.0F), 
				makeCuboidShape(4.0F, 11.0F, 4.0F, 12.0F, 12.0F, 12.0F)), 
		VoxelShapes.or( // NORTH
				makeCuboidShape(7.0F, 12.0F, 10.0D, 9.0D, 16.0D, 12.0D),
				makeCuboidShape(7.0F, 14.0F, 10.0D, 9.0D, 16.0D, 16.0D), 
				makeCuboidShape(4.0F, 2.0F, 7.0F, 12.0F, 3.0F, 15.0F), 
				makeCuboidShape(5.0F, 3.0F, 8.0F, 11.0F, 11.0F, 14.0F), 
				makeCuboidShape(4.0F, 11.0F, 7.0F, 12.0F, 12.0F, 15.0F)), 
		VoxelShapes.or( // EAST
				makeCuboidShape(4.0F, 12.0F, 7.0D, 6.0D, 16.0D, 9.0D),
				makeCuboidShape(0.0F, 14.0F, 7.0D, 6.0D, 16.0D, 9.0D), 
				makeCuboidShape(1.0F, 2.0F, 4.0F, 9.0F, 3.0F, 12.0F), 
				makeCuboidShape(2.0F, 3.0F, 5.0F, 8.0F, 11.0F, 11.0F), 
				makeCuboidShape(1.0F, 11.0F, 4.0F, 9.0F, 12.0F, 12.0F)), 
		VoxelShapes.or( // SOUTH
				makeCuboidShape(7.0F, 12.0F, 4.0D, 9.0D, 16.0D, 6.0D),
				makeCuboidShape(7.0F, 14.0F, 0.0D, 9.0D, 16.0D, 6.0D), 
				makeCuboidShape(4.0F, 2.0F, 1.0F, 12.0F, 3.0F, 9.0F), 
				makeCuboidShape(5.0F, 3.0F, 2.0F, 11.0F, 11.0F, 8.0F), 
				makeCuboidShape(4.0F, 11.0F, 1.0F, 12.0F, 12.0F, 9.0F)), 
		VoxelShapes.or( // WEST
				makeCuboidShape(10.0F, 12.0F, 7.0D, 12.0D, 16.0D, 9.0D),
				makeCuboidShape(10.0F, 14.0F, 7.0D, 16.0D, 16.0D, 9.0D), 
				makeCuboidShape(7.0F, 2.0F, 4.0F, 15.0F, 3.0F, 12.0F), 
				makeCuboidShape(8.0F, 3.0F, 5.0F, 14.0F, 11.0F, 11.0F), 
				makeCuboidShape(7.0F, 11.0F, 4.0F, 15.0F, 12.0F, 12.0F)), 
	};

	public BlockToothLantern(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(FACING)) {
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
		Direction direction = context.getFace();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockpos = pos.offset(direction.getOpposite());
		return Block.hasEnoughSolidSide(world, blockpos, direction);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		return this.isValidPosition(state, world, currentPos) ? state : Blocks.AIR.getDefaultState();
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.with(FACING, mirror.mirror(state.get(FACING)));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
}