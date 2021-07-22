package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.common.tileentities.BedrollTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class BedrollBlock extends BedBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	private final DyeColor color;

	public BedrollBlock(DyeColor colorIn, Block.Properties builder) {
		super(colorIn, builder);
		this.color = colorIn;
		this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false).setValue(WATERLOGGED, false));
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader worldIn) {
		return new BedrollTileEntity(this.color);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART, OCCUPIED, WATERLOGGED);
	}
	
	@Override
	public Fluid takeLiquid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(WATERLOGGED, false), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}
	
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (fluidStateIn.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(WATERLOGGED, true), 3);
	            worldIn.getLiquidTicks().scheduleTick(pos, fluidStateIn.getType(), fluidStateIn.getType().getTickDelay(worldIn));
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		super.fallOn(worldIn, pos, entityIn, fallDistance * 0.2F);
	}
	
	public void updateEntityAfterFallOn(IBlockReader worldIn, Entity entityIn) {
		if (entityIn.isCrouching()) {
			super.updateEntityAfterFallOn(worldIn, entityIn);
		} else if (entityIn.getDeltaMovement().y < 0.0D) {
			entityIn.setDeltaMovement(entityIn.getDeltaMovement().x, -entityIn.getDeltaMovement().y * (double) 0.66F, entityIn.getDeltaMovement().z);
			if (!(entityIn instanceof LivingEntity)) {
				entityIn.setDeltaMovement(entityIn.getDeltaMovement().x, entityIn.getDeltaMovement().y * 0.3F, entityIn.getDeltaMovement().z);
			}
		}
	}
	
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		if (facing == getDirectionToOther(stateIn.getValue(PART), stateIn.getValue(FACING))) {
			return facingState.is(this) && facingState.getValue(PART) != stateIn.getValue(PART) ? stateIn.setValue(OCCUPIED, facingState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}
	
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			super.onRemove(state, worldIn, pos, newState, isMoving);
			worldIn.removeBlockEntity(pos);
		}
	}

	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!worldIn.isClientSide && player.isCreative()) {
			BedPart bedpart = state.getValue(PART);
			if (bedpart == BedPart.FOOT) {
				BlockPos blockpos = pos.relative(getDirectionToOther(bedpart, state.getValue(FACING)));
				BlockState blockstate = worldIn.getBlockState(blockpos);
				if (blockstate.getBlock() == this && blockstate.getValue(PART) == BedPart.HEAD) {
					worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
					worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
				}
			}
		}
		super.playerWillDestroy(worldIn, pos, state, player);
	}
	
	private static Direction getDirectionToOther(BedPart part, Direction direction) {
		return part == BedPart.FOOT ? direction : direction.getOpposite();
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction enumfacing = context.getHorizontalDirection();
		BlockPos blockpos = context.getClickedPos();
		BlockPos blockpos1 = blockpos.relative(enumfacing);
		
		if (context.getLevel().getBlockState(blockpos).getBlock() == Blocks.WATER) {
			return context.getLevel().getBlockState(blockpos1).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, enumfacing).setValue(WATERLOGGED, Boolean.TRUE) : null;
		} else {
			return context.getLevel().getBlockState(blockpos1).canBeReplaced(context) ? this.defaultBlockState().setValue(FACING, enumfacing).setValue(WATERLOGGED, Boolean.FALSE) : null;
		}
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}
	
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(worldIn, pos, state, placer, stack);
		if (!worldIn.isClientSide) {
			BlockPos blockpos = pos.relative(state.getValue(FACING));
			worldIn.setBlock(blockpos, state.setValue(PART, BedPart.HEAD), 3);
			worldIn.blockUpdated(pos, Blocks.AIR);
			state.updateNeighbourShapes(worldIn, pos, 3);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor() {
		return this.color;
	}

	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) {
		BlockPos blockpos = pos.relative(state.getValue(FACING), state.getValue(PART) == BedPart.HEAD ? 0 : 1);
		return MathHelper.getSeed(blockpos.getX(), pos.getY(), blockpos.getZ());
	}
}
