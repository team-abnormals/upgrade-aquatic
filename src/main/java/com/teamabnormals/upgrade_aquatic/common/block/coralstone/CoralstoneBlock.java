package com.teamabnormals.upgrade_aquatic.common.block.coralstone;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps.CoralstoneConversions;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralWallFanBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;
import java.util.function.Function;

@SuppressWarnings("deprecation")
public class CoralstoneBlock extends Block {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	@Nullable
	private final Block[] growableCoralBlocks;
	private final boolean chiseled;

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
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(Tags.Items.TOOLS_SHEAR) && state.getBlock() != UABlocks.CHISELED_CORALSTONE.get() && state.getBlock() != UABlocks.CORALSTONE.get()) {
			BlockState newState = this.chiseled ? UABlocks.CHISELED_CORALSTONE.get().defaultBlockState() : UABlocks.CORALSTONE.get().defaultBlockState();
			level.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundSource.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			level.setBlock(pos, newState, 2);
			return ItemInteractionResult.SUCCESS;
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isAreaLoaded(pos, 3)) return;

		Block block = state.getBlock();
		if (this.growableCoralBlocks == null && block != UABlocks.DEAD_CORALSTONE.get() && block != UABlocks.DEAD_CHISELED_CORALSTONE.get()) {
			CoralstoneBlock.tickConversion(c -> this.chiseled ? c.chiseledCoralstone() : c.coralstone(), state, level, pos, random);
		}

		if (this.growableCoralBlocks != null && random.nextFloat() < 0.12F) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.getRandom(random) : Direction.from3DDataValue(random.nextInt(5) + 1);
			BlockPos growPos = pos.relative(randDirection);
			FluidState fluidState = level.getBlockState(growPos).getFluidState();
			boolean isValidPosToGrow = level.getBlockState(growPos).canBeReplaced() && fluidState.getAmount() >= 8 && fluidState.is(FluidTags.WATER);

			if (isValidPosToGrow && state.getValue(POWERED)) {
				if (randDirection.get3DDataValue() > 1) {
					level.setBlock(growPos, growableCoralBlocks[2].defaultBlockState().setValue(CoralWallFanBlock.FACING, randDirection), 2);
				} else if (randDirection.get3DDataValue() == 1) {
					if (random.nextBoolean()) {
						level.setBlock(growPos, growableCoralBlocks[1].defaultBlockState(), 2);
					} else {
						level.setBlock(growPos, growableCoralBlocks[0].defaultBlockState(), 2);
					}
				} else {
					level.setBlock(growPos, growableCoralBlocks[3].defaultBlockState(), 2);
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
		return this.defaultBlockState().setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	public static void tickConversion(Function<CoralstoneConversions, Holder<Block>> function, BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		Registry<Block> blocks = level.registryAccess().registryOrThrow(Registries.BLOCK);
		for (int i = 0; i < 4; i++) {
			Block randomBlock = level.getBlockState(pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 2, random.nextInt(3) - 1)).getBlock();
			CoralstoneConversions conversions = blocks.getData(UADataMaps.CORALSTONE_CONVERSIONS, blocks.getResourceKey(randomBlock).get());
			if (conversions != null) {
				level.setBlock(pos, BlockUtil.transferAllBlockStates(state, function.apply(conversions).value().defaultBlockState()), 2);
				break;
			}
		}
	}
}