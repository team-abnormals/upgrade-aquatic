package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.common.advancement.UACriteriaTriggers;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;
import java.util.Random;

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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 Vector3d = state.getOffset(worldIn, pos);
		return SHAPE.move(Vector3d.x, Vector3d.y, Vector3d.z);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getItemInHand().getItem() == this.asItem() && !state.getValue(DOUBLE) && state.getValue(AGE) < 2 || super.canBeReplaced(state, useContext);
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean moving) {
		if (world.getBlockState(pos.above()) == Blocks.AIR.defaultBlockState()) {
			world.removeBlock(pos, false);
		}
	}

	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 1;
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter arg0, BlockPos arg1, BlockState state, boolean arg3) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public boolean isBonemealSuccess(Level arg0, Random arg1, BlockPos arg2, BlockState state) {
		return state.getValue(AGE) < 4;
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		int i = state.getValue(AGE);
		boolean flag = i == 4;
		if (!flag && player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			return InteractionResult.PASS;
		} else if (player.getItemInHand(handIn).getItem() == Items.SHEARS && state.getValue(DOUBLE)) {
			player.getItemInHand(handIn).hurtAndBreak(1, player, (onBroken) -> {
				onBroken.broadcastBreakEvent(handIn);
			});
			worldIn.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			if (state.getValue(AGE) == 4) popResource(worldIn, pos, new ItemStack(UAItems.MULBERRY.get(), 1));
			worldIn.setBlock(pos, state.setValue(DOUBLE, false), 2);
			return InteractionResult.SUCCESS;
		} else if (flag) {
			popResource(worldIn, pos, new ItemStack(UAItems.MULBERRY.get(), state.getValue(DOUBLE) ? 2 : 1));
			worldIn.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + worldIn.random.nextFloat() * 0.4F);
			worldIn.setBlock(pos, state.setValue(AGE, 1), 2);

			if (player instanceof ServerPlayer && player.isAlive()) {
				ServerPlayer serverPlayer = (ServerPlayer) player;
				if (!player.level.isClientSide()) {
					UACriteriaTriggers.PICK_MULBERRIES.trigger(serverPlayer);
				}
			}

			return InteractionResult.SUCCESS;
		} else {
			return super.use(state, worldIn, pos, player, handIn, hit);
		}
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, Random rand, BlockPos pos, BlockState state) {
		int i = state.getValue(AGE);
		if (i < 4 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
			worldIn.setBlockAndUpdate(pos, state.setValue(AGE, i + 1));
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		int i = state.getValue(AGE);
		if (i < 4 && worldIn.getRawBrightness(pos.above(), 0) >= 7 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0)) {
			worldIn.setBlock(pos, state.setValue(AGE, i + 1), 2);
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		if (worldIn.isRainingAt(pos.above())) {
			if (rand.nextInt(15) == 1) {
				BlockPos blockpos = pos.below();
				BlockState blockstate = worldIn.getBlockState(blockpos);
				if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(worldIn, blockpos, Direction.UP)) {
					double d0 = ((float) pos.getX() + rand.nextFloat());
					double d1 = pos.getY() - 0.05D;
					double d2 = ((float) pos.getZ() + rand.nextFloat());
					worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	@Override
	public boolean isLadder(BlockState state, LevelReader world, BlockPos pos, LivingEntity entity) {
		return true;
	}

	protected boolean isStateValid(Level worldIn, BlockPos pos) {
		BlockState block = worldIn.getBlockState(pos.above());
		return block.is(BlockTags.LEAVES) || block.is(BlockTags.LOGS);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 1.0F;
	}

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		if (state.getBlock() == this && !state.getValue(DOUBLE) && state.getValue(AGE) < 2) {
			return state.setValue(DOUBLE, true);
		}
		if (isStateValid(world, pos)) {
			return defaultBlockState();
		}
		return null;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}
}


