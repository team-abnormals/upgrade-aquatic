package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.common.block.entity.BedrollBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class BedrollBlock extends BedBlock implements SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

	public BedrollBlock(Block.Properties builder) {
		super(DyeColor.WHITE, builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT).setValue(OCCUPIED, false).setValue(WATERLOGGED, false));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new BedrollBlockEntity(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART, OCCUPIED, WATERLOGGED);
	}

	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
		super.fallOn(worldIn, state, pos, entityIn, fallDistance * 0.2F);
	}

	public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
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
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		if (facing == getDirectionToOther(stateIn.getValue(PART), stateIn.getValue(FACING))) {
			return facingState.is(this) && facingState.getValue(PART) != stateIn.getValue(PART) ? stateIn.setValue(OCCUPIED, facingState.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}

	@Override
	public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
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
		return super.playerWillDestroy(worldIn, pos, state, player);
	}

	private static Direction getDirectionToOther(BedPart part, Direction direction) {
		return part == BedPart.FOOT ? direction : direction.getOpposite();
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		BlockPos relativePos = pos.relative(state.getValue(FACING));
		if (!level.isClientSide()) {
			level.setBlock(relativePos, state.setValue(PART, BedPart.HEAD), 3);
			level.blockUpdated(pos, Blocks.AIR);
			state.updateNeighbourShapes(level, pos, 3);
		}

		if (level.getBlockEntity(relativePos) instanceof BedrollBlockEntity bedroll) {
			bedroll.applyComponentsFromItemStack(stack);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor() {
		return DyeColor.WHITE;
	}
}
