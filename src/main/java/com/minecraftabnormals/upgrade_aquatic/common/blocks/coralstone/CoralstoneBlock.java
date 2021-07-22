package com.minecraftabnormals.upgrade_aquatic.common.blocks.coralstone;

import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralWallFanBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock.Properties;

@SuppressWarnings("deprecation")
public class CoralstoneBlock extends Block {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	@Nullable
	private Block[] growableCoralBlocks;
	private boolean chiseled;

	public CoralstoneBlock(Properties properties, boolean chiseled) {
		this(properties, chiseled, null);
	}

	public CoralstoneBlock(Properties properties, boolean chiseled, @Nullable Block[] growableCoralBlocks) {
		super(properties);
		this.chiseled = chiseled;
		this.growableCoralBlocks = growableCoralBlocks;
		this.registerDefaultState(this.stateDefinition.any().setValue(POWERED, false));
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CHISELED_CORALSTONE.get() && state.getBlock() != UABlocks.CORALSTONE.get()) {
			BlockState newState = this.chiseled ? UABlocks.CHISELED_CORALSTONE.get().defaultBlockState() : UABlocks.CORALSTONE.get().defaultBlockState();
			world.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
			world.setBlock(pos, newState, 2);
			return ActionResultType.SUCCESS;
		}
		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;

		Block block = state.getBlock();
		if (this.growableCoralBlocks == null && block != UABlocks.DEAD_CORALSTONE.get() && block != UABlocks.DEAD_CHISELED_CORALSTONE.get()) {
			CoralstoneBlock.tickConversion(this.chiseled ? UABlocks.CHISELED_CORALSTONE_CONVERSION_MAP : UABlocks.CORALSTONE_CONVERSION_MAP, state, worldIn, pos, random);
		}

		if (this.growableCoralBlocks != null && random.nextFloat() < 0.12F) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.getRandom(random) : Direction.from3DDataValue(random.nextInt(5) + 1);
			BlockPos growPos = pos.relative(randDirection);
			FluidState fluidState = worldIn.getBlockState(growPos).getFluidState();
			boolean isValidPosToGrow = worldIn.getBlockState(growPos).getMaterial().isReplaceable() && fluidState.getAmount() >= 8 && fluidState.is(FluidTags.WATER);

			if (isValidPosToGrow && state.getValue(POWERED)) {
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
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	public static void tickConversion(Map<Supplier<Block>, Supplier<Block>> conversionMap, BlockState state, ServerWorld world, BlockPos pos, Random random) {
		for (int i = 0; i < 4; i++) {
			Block randomBlock = world.getBlockState(pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1)).getBlock();
			conversionMap.forEach((input, output) -> {
				if (input.get() == randomBlock) {
					world.setBlock(pos, BlockUtil.transferAllBlockStates(state, output.get().defaultBlockState()), 2);
				}
			});
		}
	}
}