package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.client.particle.UAParticles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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

public class BlockJellyTorchWall extends BlockJellyTorch {
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.makeCuboidShape(5.5d, 3d, 11d, 10.5d, 13d, 16d), Direction.SOUTH, Block.makeCuboidShape(5.5d, 3d, 0d, 10.5d, 13d, 5d), Direction.WEST, Block.makeCuboidShape(11d, 3d, 5.5d, 16d, 13d, 10.5d), Direction.EAST, Block.makeCuboidShape(0d, 3d, 5.5d, 5d, 13d, 10.5d)));
    private JellyTorchType torchType;

    public BlockJellyTorchWall(Properties properties, JellyTorchType torchType) {
        super(properties, torchType);
        this.torchType = torchType;
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state.get(HORIZONTAL_FACING));
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Direction direction = state.get(HORIZONTAL_FACING);
        BlockPos blockpos = pos.offset(direction.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        return Block.hasSolidSide(blockstate, worldIn, blockpos, direction);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        BlockPos blockpos = context.getPos();
        Direction[] adirection = context.getNearestLookingDirections();
        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.with(HORIZONTAL_FACING, direction1);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
                }
            }
        }
        return null;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
    	IFluidState ifluidstate = worldIn.getFluidState(currentPos);
    	return facing.getOpposite() == stateIn.get(HORIZONTAL_FACING) && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction direction = state.get(HORIZONTAL_FACING);
        double xOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double yOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double zOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
        double d0 = (double) pos.getX() + 0.5d + xOffset;
        double d1 = (double) pos.getY() + 0.45d + yOffset;
        double d2 = (double) pos.getZ() + 0.5d + zOffset;
        double d3 = 0.18d;
        double d4 = 0.3d;
        Direction facing = direction.getOpposite();
        UAParticles.JELLY_TORCH.spawn(world, d0 + d4 * (double) facing.getXOffset(), d1 + d3, d2 + d4 * (double) facing.getZOffset(), 0d, 0.004d, 0d, torchType.ordinal());
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
    }
}
