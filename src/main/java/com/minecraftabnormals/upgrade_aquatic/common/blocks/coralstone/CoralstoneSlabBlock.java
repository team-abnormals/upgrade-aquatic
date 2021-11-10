package com.minecraftabnormals.upgrade_aquatic.common.blocks.coralstone;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralWallFanBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class CoralstoneSlabBlock extends SlabBlock {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	@Nullable
	private final Block[] growableCoralBlocks;

	public CoralstoneSlabBlock(Properties properties, @Nullable Block[] growableCoralBlocks) {
		super(properties);
		this.growableCoralBlocks = growableCoralBlocks;
		this.registerDefaultState(this.defaultBlockState()
				.setValue(TYPE, SlabType.BOTTOM)
				.setValue(WATERLOGGED, false)
				.setValue(POWERED, false)
		);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;
		Block block = state.getBlock();

		if (this.growableCoralBlocks == null && block != UABlocks.DEAD_CORALSTONE_SLAB.get()) {
			CoralstoneBlock.tickConversion(UABlocks.CORALSTONE_SLAB_CONVERSION_MAP, state, worldIn, pos, random);
		}

		if (this.growableCoralBlocks != null && random.nextFloat() < 0.12F && state.getValue(POWERED)) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.getRandom(random) : Direction.from3DDataValue(random.nextInt(5) + 1);
			BlockPos growPos = pos.relative(randDirection);
			FluidState fluidState = worldIn.getBlockState(growPos).getFluidState();

			if (state.getValue(TYPE) == SlabType.BOTTOM) {
				if (this.isValidPosToGrow(worldIn, pos.relative(Direction.DOWN), fluidState) && growableCoralBlocks.length > 3) {
					worldIn.setBlock(pos.relative(Direction.DOWN), growableCoralBlocks[3].defaultBlockState(), 2);
				}
			} else if (state.getValue(TYPE) == SlabType.TOP) {
				if (this.isValidPosToGrow(worldIn, pos.relative(Direction.UP), fluidState)) {
					if (random.nextBoolean()) {
						worldIn.setBlock(pos.relative(Direction.UP), growableCoralBlocks[0].defaultBlockState(), 2);
					} else {
						worldIn.setBlock(pos.relative(Direction.UP), growableCoralBlocks[1].defaultBlockState(), 2);
					}
				}
			} else if (state.getValue(TYPE) == SlabType.DOUBLE && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
				if (randDirection.get3DDataValue() > 1) {
					worldIn.setBlock(growPos, growableCoralBlocks[2].defaultBlockState().setValue(CoralWallFanBlock.FACING, randDirection), 2);
				} else if (randDirection.get3DDataValue() == 1) {
					if (random.nextBoolean()) {
						worldIn.setBlock(growPos, growableCoralBlocks[1].defaultBlockState(), 2);
					} else {
						worldIn.setBlock(growPos, growableCoralBlocks[0].defaultBlockState(), 2);
					}
				} else {
					worldIn.setBlock(growPos, growableCoralBlocks[3].defaultBlockState(), 2);
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
		if (stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_SLAB.get()) {
			BlockState newState = UABlocks.CORALSTONE_SLAB.get().defaultBlockState();
			world.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
			world.setBlock(pos, newState.setValue(TYPE, state.getValue(TYPE)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 2);
			return ActionResultType.SUCCESS;
		}
		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED, POWERED);
	}

	private boolean isValidPosToGrow(World world, BlockPos pos, FluidState fluidState) {
		return world.getBlockState(pos).getMaterial().isReplaceable() && fluidState.getAmount() >= 8 && fluidState.is(FluidTags.WATER);
	}
}