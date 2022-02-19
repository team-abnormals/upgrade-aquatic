package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class JellyWallTorchBlock extends JellyTorchBlock {
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalDirectionalBlock.FACING;
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5d, 3d, 11d, 10.5d, 13d, 16d), Direction.SOUTH, Block.box(5.5d, 3d, 0d, 10.5d, 13d, 5d), Direction.WEST, Block.box(11d, 3d, 5.5d, 16d, 13d, 10.5d), Direction.EAST, Block.box(0d, 3d, 5.5d, 5d, 13d, 10.5d)));
	private final JellyTorchType torchType;

	public JellyWallTorchBlock(Properties properties, JellyTorchType torchType) {
		super(properties, torchType);
		this.torchType = torchType;
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(HORIZONTAL_FACING));
	}

	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		Direction direction = state.getValue(HORIZONTAL_FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		BlockState blockstate = worldIn.getBlockState(blockpos);
		return blockstate.isFaceSturdy(worldIn, blockpos, direction);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockstate = this.defaultBlockState();
		LevelReader iworldreader = context.getLevel();
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockPos blockpos = context.getClickedPos();
		Direction[] adirection = context.getNearestLookingDirections();
		for (Direction direction : adirection) {
			if (direction.getAxis().isHorizontal()) {
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.setValue(HORIZONTAL_FACING, direction1);
				if (blockstate.canSurvive(iworldreader, blockpos)) {
					return blockstate.setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
				}
			}
		}
		return null;
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		FluidState ifluidstate = worldIn.getFluidState(currentPos);
		return facing.getOpposite() == stateIn.getValue(HORIZONTAL_FACING) && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn.setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
		Direction direction = state.getValue(HORIZONTAL_FACING);
		double xOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double yOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double zOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double d0 = (double) pos.getX() + 0.5d + xOffset;
		double d1 = (double) pos.getY() + 0.45d + yOffset;
		double d2 = (double) pos.getZ() + 0.5d + zOffset;
		double d3 = 0.18d;
		double d4 = 0.3d;
		Direction facing = direction.getOpposite();
		world.addParticle(JellyTorchType.getTorchParticleType(this.torchType), d0 + d4 * (double) facing.getStepX(), d1 + d3, d2 + d4 * (double) facing.getStepZ(), 0d, 0.004d, 0d);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(HORIZONTAL_FACING, rot.rotate(state.getValue(HORIZONTAL_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
	}

	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, WATERLOGGED);
	}
}
