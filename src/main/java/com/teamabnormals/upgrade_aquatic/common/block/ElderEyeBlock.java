package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.common.block.entity.ElderEyeBlockEntity;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class ElderEyeBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	public static final IntegerProperty POWER = BlockStateProperties.POWER;
	public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	protected static final VoxelShape BOX_SIZE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	protected static final VoxelShape DOWN_BOX_SIZE = Block.box(4.0D, 8.0D, 4.0D, 12.0D, 16.0D, 12.0D);

	public ElderEyeBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.SOUTH)
				.setValue(POWER, 0)
				.setValue(ACTIVE, false)
				.setValue(WATERLOGGED, false)
		);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		return state.getValue(FACING) == Direction.DOWN ? DOWN_BOX_SIZE : BOX_SIZE;
	}

	public boolean isSignalSource(BlockState state) {
		return true;
	}

	public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
		return blockState.getValue(POWER);
	}

	public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
		return blockState.getValue(POWER);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POWER, ACTIVE, WATERLOGGED);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState()
				.setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER)
				.setValue(FACING, context.getNearestLookingDirection().getOpposite());
	}

	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!isMoving && state.getBlock() != newState.getBlock()) {
			if (state.getValue(POWER) > 0) {
				this.updateRedstoneNeighbors(state, worldIn, pos);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		boolean flag = state.getValue(ACTIVE);
		if (flag) {
			worldIn.playSound(null, pos, SoundEvents.CONDUIT_DEACTIVATE, SoundSource.BLOCKS, 0.3F, 1.0F);
			worldIn.setBlockAndUpdate(pos, state.setValue(ACTIVE, false).setValue(POWER, 0));
		} else {
			worldIn.playSound(null, pos, SoundEvents.CONDUIT_ACTIVATE, SoundSource.BLOCKS, 0.3F, 1.0F);
			worldIn.setBlockAndUpdate(pos, state.setValue(ACTIVE, true).setValue(POWER, 0));
		}
		this.updateRedstoneNeighbors(state, worldIn, pos);
		return InteractionResult.SUCCESS;
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		this.updateRedstoneNeighbors(stateIn, (Level) worldIn, currentPos);
		return stateIn;
	}

	public void updateRedstoneNeighbors(BlockState p_196378_1_, Level p_196378_2_, BlockPos p_196378_3_) {
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
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ElderEyeBlockEntity(pos, state);
	}

	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
		return createTickerHelper(blockEntity, UABlockEntityTypes.ELDER_EYE.get(), ElderEyeBlockEntity::tick);
	}
}
