package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class JellyWallTorchBlock extends JellyTorchBlock {
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.FACING;
	private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5d, 3d, 11d, 10.5d, 13d, 16d), Direction.SOUTH, Block.box(5.5d, 3d, 0d, 10.5d, 13d, 5d), Direction.WEST, Block.box(11d, 3d, 5.5d, 16d, 13d, 10.5d), Direction.EAST, Block.box(0d, 3d, 5.5d, 5d, 13d, 10.5d)));
	private final JellyTorchType torchType;

	public JellyWallTorchBlock(Properties properties, JellyTorchType torchType) {
		super(properties, torchType);
		this.torchType = torchType;
		this.registerDefaultState(this.stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(state.getValue(HORIZONTAL_FACING));
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction direction = state.getValue(HORIZONTAL_FACING);
		BlockPos blockpos = pos.relative(direction.getOpposite());
		BlockState blockstate = worldIn.getBlockState(blockpos);
		return blockstate.isFaceSturdy(worldIn, blockpos, direction);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = this.defaultBlockState();
		IWorldReader iworldreader = context.getLevel();
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		BlockPos blockpos = context.getClickedPos();
		Direction[] adirection = context.getNearestLookingDirections();
		for (Direction direction : adirection) {
			if (direction.getAxis().isHorizontal()) {
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.setValue(HORIZONTAL_FACING, direction1);
				if (blockstate.canSurvive(iworldreader, blockpos)) {
					return blockstate.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
				}
			}
		}
		return null;
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		FluidState ifluidstate = worldIn.getFluidState(currentPos);
		return facing.getOpposite() == stateIn.getValue(HORIZONTAL_FACING) && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
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
	public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, WATERLOGGED);
	}
}
