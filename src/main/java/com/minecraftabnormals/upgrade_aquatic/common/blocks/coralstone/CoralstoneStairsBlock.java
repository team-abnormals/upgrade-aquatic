package com.minecraftabnormals.upgrade_aquatic.common.blocks.coralstone;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralWallFanBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CoralstoneStairsBlock extends StairsBlock {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	@Nullable
	private Block[] growableCoralBlocks;

	public CoralstoneStairsBlock(Supplier<BlockState> state, Properties properties, @Nullable Block[] growableCoralBlocks) {
		super(state, properties);
		this.growableCoralBlocks = growableCoralBlocks;
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(HALF, Half.BOTTOM)
				.setValue(SHAPE, StairsShape.STRAIGHT)
				.setValue(WATERLOGGED, false)
				.setValue(POWERED, false)
		);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED, FACING, HALF, SHAPE, WATERLOGGED);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;

		if (this.growableCoralBlocks == null && state.getBlock() != UABlocks.DEAD_CORALSTONE_STAIRS.get()) {
			CoralstoneBlock.tickConversion(UABlocks.CORALSTONE_STAIRS_CONVERSION_MAP, state, worldIn, pos, random);
		}

		if (this.growableCoralBlocks != null && random.nextFloat() < 0.24F && state.getValue(POWERED)) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.getRandom(random) : Direction.from3DDataValue(random.nextInt(5) + 1);
			BlockPos growPos = pos.relative(randDirection);
			FluidState fluidState = worldIn.getBlockState(growPos).getFluidState();
			BlockState coralState;

			if (randDirection.get3DDataValue() > 1) {
				coralState = growableCoralBlocks[2].defaultBlockState().setValue(CoralWallFanBlock.FACING, randDirection);
				if (coralState.canSurvive(worldIn, growPos) && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
					worldIn.setBlock(growPos, coralState, 2);
				}
			} else if (randDirection.get3DDataValue() == 1) {
				coralState = random.nextBoolean() ? growableCoralBlocks[1].defaultBlockState() : growableCoralBlocks[0].defaultBlockState();
				if (coralState.canSurvive(worldIn, growPos) && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
					worldIn.setBlock(growPos, coralState, 2);
				}
			} else {
				coralState = growableCoralBlocks[3].defaultBlockState();
				if (coralState.canSurvive(worldIn, growPos) && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
					worldIn.setBlock(growPos, coralState, 2);
				}
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!worldIn.isClientSide) {
			boolean flag = state.getValue(POWERED);
			if (flag != worldIn.hasNeighborSignal(pos)) {
				worldIn.setBlock(pos, state.cycle(POWERED), 2);
			}
		}
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_STAIRS.get()) {
			BlockState newState = UABlocks.CORALSTONE_STAIRS.get().defaultBlockState()
					.setValue(FACING, state.getValue(FACING))
					.setValue(HALF, state.getValue(HALF))
					.setValue(SHAPE, state.getValue(SHAPE))
					.setValue(WATERLOGGED, state.getValue(WATERLOGGED)
					);
			world.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
			world.setBlock(pos, newState, 2);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}

	private boolean isValidPosToGrow(World world, BlockPos pos, FluidState fluidState) {
		return world.getBlockState(pos).getMaterial().isReplaceable() && fluidState.getAmount() >= 8 && fluidState.is(FluidTags.WATER);
	}
}
