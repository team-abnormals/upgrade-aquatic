package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.blueprint.core.util.item.filling.TargetedItemCategoryFiller;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class FloweringRushBlock extends Block implements SimpleWaterloggedBlock, BonemealableBlock {
	private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private static final TargetedItemCategoryFiller FILLER = new TargetedItemCategoryFiller(() -> Items.PEONY);

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

	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return false;
	}

	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.is(BlockTags.BAMBOO_PLANTABLE_ON);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		BlockState groundState = worldIn.getBlockState(currentPos.below());
		FluidState fluidState = state.getFluidState();
		FluidState upperFluidState = state.getFluidState();
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			return fluidState.getAmount() >= 8 && this.isValidGround(groundState, worldIn, currentPos.below()) && worldIn.getBlockState(currentPos.above()).getBlock() == UABlocks.FLOWERING_RUSH.get() && worldIn.getBlockState(currentPos.above()).getValue(HALF) == DoubleBlockHalf.UPPER ? state : Blocks.AIR.defaultBlockState();
		} else {
			return upperFluidState.isEmpty() && worldIn.getBlockState(currentPos.below()).getBlock() == UABlocks.FLOWERING_RUSH.get() && worldIn.getBlockState(currentPos.below()).getValue(HALF) == DoubleBlockHalf.LOWER ? state : Blocks.AIR.defaultBlockState();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			BlockPos blockpos = pos.below();
			FluidState ifluidstate = worldIn.getFluidState(pos);
			return worldIn.getBlockState(pos.above()).isAir() && this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos) && ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() >= 8;
		} else {
			BlockState blockstate = worldIn.getBlockState(pos.below());
			if (state.getBlock() != this) return super.canSurvive(state, worldIn, pos);
			return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return super.getStateForPlacement(context).setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
		popResource(worldIn, pos, new ItemStack(this));
	}

	@Override
	public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
	}

	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (blockstate.getBlock() == this && blockstate.getValue(HALF) != doubleblockhalf) {
			worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			if (!worldIn.isClientSide && !player.isCreative()) {
				dropResources(state, worldIn, pos, null, player, player.getMainHandItem());
				dropResources(blockstate, worldIn, pos, null, player, player.getMainHandItem());
			}
			if (blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				worldIn.destroyBlock(blockpos, false);
			}
		}

		super.playerWillDestroy(worldIn, pos, state, player);
	}

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}
}