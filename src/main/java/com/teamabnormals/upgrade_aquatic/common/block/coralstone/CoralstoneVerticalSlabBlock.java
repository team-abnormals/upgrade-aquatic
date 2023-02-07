package com.teamabnormals.upgrade_aquatic.common.block.coralstone;

import com.teamabnormals.blueprint.common.block.VerticalSlabBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralWallFanBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class CoralstoneVerticalSlabBlock extends VerticalSlabBlock {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	@Nullable
	private final Block[] growableCoralBlocks;

	public CoralstoneVerticalSlabBlock(Properties properties, @Nullable Block[] growableCoralBlocks) {
		super(properties);
		this.growableCoralBlocks = growableCoralBlocks;
		this.registerDefaultState(this.defaultBlockState()
				.setValue(TYPE, VerticalSlabType.NORTH)
				.setValue(WATERLOGGED, false)
				.setValue(POWERED, false)
		);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;
		Block block = state.getBlock();
		VerticalSlabType type = state.getValue(TYPE);

		if (this.growableCoralBlocks == null && block != UABlocks.DEAD_CORALSTONE_VERTICAL_SLAB.get()) {
			CoralstoneBlock.tickConversion(UABlocks.CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP, state, worldIn, pos, random);
		}

		if (this.growableCoralBlocks != null && random.nextFloat() < 0.12F && state.getValue(POWERED)) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.getRandom(random) : Direction.from3DDataValue(random.nextInt(5) + 1);
			BlockPos growPos = type.direction != null ? pos.relative(type.direction.getOpposite()) : pos.relative(randDirection);
			FluidState fluidState = worldIn.getBlockState(growPos).getFluidState();
			boolean validGrowPos = this.isValidPosToGrow(worldIn, growPos, fluidState);

			if (state.getValue(TYPE) != VerticalSlabType.DOUBLE) {
				if (validGrowPos) {
					worldIn.setBlock(growPos, this.growableCoralBlocks[2].defaultBlockState().setValue(CoralWallFanBlock.FACING, type.direction.getOpposite()), 2);
				}
			} else {
				if (validGrowPos) {
					if (randDirection.get3DDataValue() > 1) {
						worldIn.setBlock(growPos, this.growableCoralBlocks[2].defaultBlockState().setValue(CoralWallFanBlock.FACING, randDirection), 2);
					} else if (randDirection.get3DDataValue() == 1) {
						if (random.nextBoolean()) {
							worldIn.setBlock(growPos, this.growableCoralBlocks[1].defaultBlockState(), 2);
						} else {
							worldIn.setBlock(growPos, this.growableCoralBlocks[0].defaultBlockState(), 2);
						}
					} else {
						worldIn.setBlock(growPos, this.growableCoralBlocks[3].defaultBlockState(), 2);
					}
				}
			}
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!worldIn.isClientSide) {
			boolean flag = state.getValue(POWERED);
			if (flag != worldIn.hasNeighborSignal(pos)) {
				worldIn.setBlock(pos, state.cycle(POWERED), 2);
			}
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_VERTICAL_SLAB.get()) {
			BlockState newState = UABlocks.CORALSTONE_VERTICAL_SLAB.get().defaultBlockState();
			world.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundSource.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
			world.setBlock(pos, newState.setValue(TYPE, state.getValue(TYPE)).setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 2);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED, POWERED);
	}

	private boolean isValidPosToGrow(Level world, BlockPos pos, FluidState fluidState) {
		return world.getBlockState(pos).getMaterial().isReplaceable() && fluidState.getAmount() >= 8 && fluidState.is(FluidTags.WATER);
	}
}
