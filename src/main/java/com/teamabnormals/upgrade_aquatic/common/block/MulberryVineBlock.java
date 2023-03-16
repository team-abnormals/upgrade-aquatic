package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.other.UACriteriaTriggers;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class MulberryVineBlock extends Block implements IForgeShearable, BonemealableBlock {
	protected static final VoxelShape SHAPE = Block.box(5.0, 4.0, 5.0, 13.0, 16.0, 13.0);

	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 4);
	public static final BooleanProperty DOUBLE = BooleanProperty.create("double");


	public MulberryVineBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(DOUBLE, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE, DOUBLE);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Vec3 vec3 = state.getOffset(level, pos);
		return SHAPE.move(vec3.x, vec3.y, vec3.z);
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return (useContext.getItemInHand().getItem() == this.asItem() && !state.getValue(DOUBLE) && state.getValue(AGE) < 2) || super.canBeReplaced(state, useContext);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState offsetState, LevelAccessor level, BlockPos pos, BlockPos offsetPos) {
		return direction == Direction.UP && !this.canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, offsetState, level, pos, offsetPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState aboveState = level.getBlockState(pos.above());
		return (aboveState.is(BlockTags.LOGS) || aboveState.is(BlockTags.LEAVES)) && !level.isWaterAt(pos);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter arg0, BlockPos arg1, BlockState state, boolean arg3) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public boolean isBonemealSuccess(Level arg0, RandomSource arg1, BlockPos arg2, BlockState state) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 4;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (player.getItemInHand(handIn).getItem() == Items.SHEARS && state.getValue(DOUBLE)) {
			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> {
				onBroken.broadcastBreakEvent(handIn);
			});
			level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			if (state.getValue(AGE) == 4) popResource(level, pos, new ItemStack(UAItems.MULBERRY.get(), 1));
			level.setBlock(pos, state.setValue(DOUBLE, false), 2);
			return InteractionResult.SUCCESS;
		} else if (flag) {
			popResource(level, pos, new ItemStack(UAItems.MULBERRY.get(), state.getValue(DOUBLE) ? 2 : 1));
			level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
			level.setBlock(pos, state.setValue(AGE, 1), 2);

			if (player instanceof ServerPlayer && player.isAlive()) {
				ServerPlayer serverPlayer = (ServerPlayer) player;
				if (!player.level.isClientSide()) {
					UACriteriaTriggers.PICK_MULBERRIES.trigger(serverPlayer);
				}
			}

			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, level, pos, player, handIn, hit);
		}
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
		int i = state.getValue(AGE);
		if (i < 4 && ForgeHooks.onCropsGrowPre(level, pos, state, true)) {
			level.setBlockAndUpdate(pos, state.setValue(AGE, i + 1));
			ForgeHooks.onCropsGrowPost(level, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		super.tick(state, level, pos, rand);
		int i = state.getValue(AGE);
		if (i < 4 && level.getRawBrightness(pos.above(), 0) >= 7 && ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt(5) == 0)) {
			level.setBlock(pos, state.setValue(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(level, pos, state);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand) {
		if (level.isRainingAt(pos.above())) {
			if (rand.nextInt(15) == 1) {
				BlockPos blockpos = pos.below();
				BlockState blockstate = level.getBlockState(blockpos);
				if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(level, blockpos, Direction.UP)) {
					double d0 = ((float) pos.getX() + rand.nextFloat());
					double d1 = pos.getY() - 0.05D;
					double d2 = ((float) pos.getZ() + rand.nextFloat());
					level.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		if (state.getBlock() == this && !state.getValue(DOUBLE) && state.getValue(AGE) < 2) {
			return state.setValue(DOUBLE, true);
		}
		return super.getStateForPlacement(context);
	}
}


