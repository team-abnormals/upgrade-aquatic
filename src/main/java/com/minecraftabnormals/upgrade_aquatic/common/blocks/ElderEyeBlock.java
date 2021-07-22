package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.common.tileentities.ElderEyeTileEntity;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock.Properties;

public class ElderEyeBlock extends DirectionalBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape BOX_SIZE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	protected static final VoxelShape DOWN_BOX_SIZE = Block.box(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	
	public ElderEyeBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(FACING, Direction.SOUTH)
			.setValue(POWERED, Boolean.valueOf(false))
			.setValue(ACTIVE, Boolean.valueOf(false))
			.setValue(WATERLOGGED, Boolean.valueOf(false))
		);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return state.getValue(FACING) == Direction.DOWN ? DOWN_BOX_SIZE : BOX_SIZE;
	}
	
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	public int getDirectSignal(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.getValue(POWERED) ? 15 : 0;
	}

	public int getSignal(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.getValue(POWERED) ? 15 : 0;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ElderEyeTileEntity();
	}
	
//	@Override
//	public boolean hasCustomBreakingProgress(BlockState state) {
//		return true;
//	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED, ACTIVE, WATERLOGGED);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState()
			.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER))
			.setValue(FACING, context.getNearestLookingDirection().getOpposite());
	}
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		 if (!isMoving && state.getBlock() != newState.getBlock()) {
			 if (state.getValue(POWERED)) {
				 this.updateRedstoneNeighbors(state, worldIn, pos);
			 }

			 super.onRemove(state, worldIn, pos, newState, isMoving);
		 }
	 }
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		boolean flag = state.getValue(ACTIVE);
		if(flag) {
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.CONDUIT_DEACTIVATE, SoundCategory.BLOCKS, 0.3F, 1.0F);
			worldIn.setBlockAndUpdate(pos, state.setValue(ACTIVE, false).setValue(POWERED, false));
		} else {
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.CONDUIT_ACTIVATE, SoundCategory.BLOCKS, 0.3F, 1.0F);
			worldIn.setBlockAndUpdate(pos, state.setValue(ACTIVE, true).setValue(POWERED, false));
		}
		this.updateRedstoneNeighbors(state, worldIn, pos);
		return ActionResultType.SUCCESS;
	}
	
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		this.updateRedstoneNeighbors(stateIn, (World)worldIn, currentPos);
		return stateIn;
	}
	
	public void updateRedstoneNeighbors(BlockState p_196378_1_, World p_196378_2_, BlockPos p_196378_3_) {
		p_196378_2_.updateNeighborsAt(p_196378_3_, this);
		p_196378_2_.updateNeighborsAt(p_196378_3_.relative(Direction.NORTH), this);
		p_196378_2_.updateNeighborsAt(p_196378_3_.relative(Direction.SOUTH), this);
		p_196378_2_.updateNeighborsAt(p_196378_3_.relative(Direction.WEST), this);
		p_196378_2_.updateNeighborsAt(p_196378_3_.relative(Direction.EAST), this);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.getValue(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	public Fluid takeLiquid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(false)), 3);
	        return Fluids.WATER;
	    } else {
	    	return Fluids.EMPTY;
	    }
	}
	
	@Override
	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.getValue(WATERLOGGED) && fluidStateIn.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
	
	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}
