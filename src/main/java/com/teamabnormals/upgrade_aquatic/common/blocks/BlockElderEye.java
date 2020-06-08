package com.teamabnormals.upgrade_aquatic.common.blocks;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockElderEye extends DirectionalBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape BOX_SIZE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	protected static final VoxelShape DOWN_BOX_SIZE = Block.makeCuboidShape(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D);
	
	public BlockElderEye(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
			.with(FACING, Direction.SOUTH)
			.with(POWERED, Boolean.valueOf(false))
			.with(ACTIVE, Boolean.valueOf(false))
			.with(WATERLOGGED, Boolean.valueOf(false))
		);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return state.get(FACING) == Direction.DOWN ? DOWN_BOX_SIZE : BOX_SIZE;
	}
	
	public boolean canProvidePower(BlockState state) {
		return true;
	}

	public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.get(POWERED) ? 15 : 0;
	}

	public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return blockState.get(POWERED) ? 15 : 0;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityElderEye();
	}
	
//	@Override
//	public boolean hasCustomBreakingProgress(BlockState state) {
//		return true;
//	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWERED, ACTIVE, WATERLOGGED);
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return this.getDefaultState()
			.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER))
			.with(FACING, context.getNearestLookingDirection().getOpposite());
	}
	
	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		 if (!isMoving && state.getBlock() != newState.getBlock()) {
			 if (state.get(POWERED)) {
				 this.updateRedstoneNeighbors(state, worldIn, pos);
			 }

			 super.onReplaced(state, worldIn, pos, newState, isMoving);
		 }
	 }
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		boolean flag = state.get(ACTIVE);
		if(flag) {
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.BLOCKS, 0.3F, 1.0F);
			worldIn.setBlockState(pos, state.with(ACTIVE, false).with(POWERED, false));
		} else {
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.BLOCKS, 0.3F, 1.0F);
			worldIn.setBlockState(pos, state.with(ACTIVE, true).with(POWERED, false));
		}
		this.updateRedstoneNeighbors(state, worldIn, pos);
		return ActionResultType.SUCCESS;
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		this.updateRedstoneNeighbors(stateIn, worldIn.getWorld(), currentPos);
		return stateIn;
	}
	
	public void updateRedstoneNeighbors(BlockState p_196378_1_, World p_196378_2_, BlockPos p_196378_3_) {
		p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_, this);
		p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_.offset(Direction.NORTH), this);
		p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_.offset(Direction.SOUTH), this);
		p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_.offset(Direction.WEST), this);
		p_196378_2_.notifyNeighborsOfStateChange(p_196378_3_.offset(Direction.EAST), this);
	}

	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
	        return Fluids.WATER;
	    } else {
	    	return Fluids.EMPTY;
	    }
	}
	
	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}
