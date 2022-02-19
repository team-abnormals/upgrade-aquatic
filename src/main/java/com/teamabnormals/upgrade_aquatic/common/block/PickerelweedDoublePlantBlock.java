package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.pike.PikeEntity;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class PickerelweedDoublePlantBlock extends Block implements BonemealableBlock, SimpleWaterloggedBlock {
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty FAKE_WATERLOGGED = BooleanProperty.create("fake_waterlogged");

	public PickerelweedDoublePlantBlock(Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(FAKE_WATERLOGGED, false));
	}

	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		return SHAPE;
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
		return this == UABlocks.TALL_BLUE_PICKERELWEED.get() ? new ItemStack(UABlocks.BLUE_PICKERELWEED.get()) : new ItemStack(UABlocks.PURPLE_PICKERELWEED.get());
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
		if (!(entity instanceof PikeEntity)) {
			entity.makeStuckInBlock(state, new Vec3(0.75D, 0.75D, 0.75D));
		}
	}

	protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.CLAY;
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			if (doubleblockhalf == DoubleBlockHalf.UPPER && !stateIn.getValue(FAKE_WATERLOGGED)) {
				stateIn = stateIn.setValue(FAKE_WATERLOGGED, true);
				if (worldIn.getBlockState(currentPos.below()).hasProperty(FAKE_WATERLOGGED))
					worldIn.setBlock(currentPos.below(), worldIn.getBlockState(currentPos.below()).setValue(FAKE_WATERLOGGED, true), 2);
			} else if (doubleblockhalf == DoubleBlockHalf.LOWER && !stateIn.getValue(FAKE_WATERLOGGED)) {
				stateIn = stateIn.setValue(FAKE_WATERLOGGED, true);
				if (worldIn.getBlockState(currentPos.above()).hasProperty(FAKE_WATERLOGGED))
					worldIn.setBlock(currentPos.above(), worldIn.getBlockState(currentPos.above()).setValue(FAKE_WATERLOGGED, true), 2);
			}
		}
		if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.is(this) && facingState.getValue(HALF) != doubleblockhalf) {
			return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockPos blockpos = context.getClickedPos();
		return blockpos.getY() < context.getLevel().getMaxBuildHeight() - 1 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context).setValue(WATERLOGGED, ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8).setValue(FAKE_WATERLOGGED, ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8) : null;
	}

	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			return this.isValidGround(worldIn.getBlockState(pos.below()), worldIn, pos);
		} else {
			BlockState blockstate = worldIn.getBlockState(pos.below());
			if (state.getBlock() != this)
				this.isValidGround(worldIn.getBlockState(pos.below()), worldIn, pos);
			return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	public void placeAt(LevelAccessor p_196390_1_, BlockPos p_196390_2_, int flags) {
		FluidState ifluidstate = p_196390_1_.getFluidState(p_196390_2_);
		FluidState ifluidstateUp = p_196390_1_.getFluidState(p_196390_2_.above());
		boolean applyFakeWaterLogging = ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8 || ifluidstateUp.is(FluidTags.WATER) && ifluidstateUp.getAmount() == 8;
		p_196390_1_.setBlock(p_196390_2_, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, ifluidstate.is(FluidTags.WATER) && ifluidstate.getAmount() == 8).setValue(FAKE_WATERLOGGED, applyFakeWaterLogging), flags);
		p_196390_1_.setBlock(p_196390_2_.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(WATERLOGGED, ifluidstateUp.is(FluidTags.WATER) && ifluidstateUp.getAmount() == 8).setValue(FAKE_WATERLOGGED, applyFakeWaterLogging), flags);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, WATERLOGGED, FAKE_WATERLOGGED);
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return state.getValue(WATERLOGGED) ? 0 : 60;
	}

	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
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
		if (!worldIn.isClientSide) {
			cont:
			for (int i = 0; i < 128; ++i) {
				BlockPos blockpos = pos;
				BlockState blockstate = this == UABlocks.TALL_BLUE_PICKERELWEED.get() ? UABlocks.BLUE_PICKERELWEED.get().defaultBlockState() : UABlocks.PURPLE_PICKERELWEED.get().defaultBlockState();

				for (int j = 0; j < i / 16; ++j) {
					blockpos = blockpos.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
					if (Block.isShapeFullBlock(worldIn.getBlockState(blockpos).getCollisionShape(worldIn, blockpos))) {
						continue cont;
					}
				}

				if (blockstate.canSurvive(worldIn, blockpos) && worldIn.getBlockState(blockpos).getMaterial().isReplaceable() && rand.nextFloat() <= 0.10F) {
					BlockState blockstate1 = worldIn.getBlockState(blockpos);
					if (blockstate1.getFluidState().is(FluidTags.WATER) && worldIn.getFluidState(blockpos).getAmount() == 8) {
						worldIn.setBlock(blockpos, blockstate.setValue(WATERLOGGED, true), 3);
					} else {
						worldIn.setBlock(blockpos, blockstate.setValue(WATERLOGGED, false), 3);
					}
				}
			}
		}
	}

	@Override
	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
		if (!worldIn.isClientSide) {
			if (player.getAbilities().instabuild) {
				removeBottomHalf(worldIn, pos, state, player);
			} else {
				dropResources(state, worldIn, pos, null, player, player.getMainHandItem());
			}
		}

		super.playerWillDestroy(worldIn, pos, state, player);
	}

	@Override
	public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
		super.playerDestroy(worldIn, player, pos, Blocks.AIR.defaultBlockState(), te, stack);
	}

	protected static void removeBottomHalf(Level world, BlockPos pos, BlockState state, Player player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		if (doubleblockhalf == DoubleBlockHalf.UPPER) {
			BlockPos blockpos = pos.below();
			BlockState blockstate = world.getBlockState(blockpos);
			if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
				world.setBlock(blockpos, world.getFluidState(blockpos).getAmount() == 8 ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 51);
				world.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			}
		} else if (doubleblockhalf == DoubleBlockHalf.LOWER) {
			BlockPos blockpos = pos.above();
			BlockState blockstate = world.getBlockState(blockpos);
			if (blockstate.getBlock() == state.getBlock() && blockstate.getValue(HALF) == DoubleBlockHalf.UPPER) {
				world.setBlock(blockpos, world.getFluidState(blockpos).getAmount() == 8 ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 51);
				world.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			}
		}
	}
}