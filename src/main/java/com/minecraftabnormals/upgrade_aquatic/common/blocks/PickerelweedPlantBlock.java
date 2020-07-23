package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.minecraftabnormals.upgrade_aquatic.common.entities.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class PickerelweedPlantBlock extends Block implements IGrowable, IWaterLoggable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public PickerelweedPlantBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(random.nextFloat() <= 0.03F && state.get(WATERLOGGED)) {
			this.grow(worldIn, random, pos, state);
		}
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entity) {
		if (!(entity instanceof PikeEntity)) {
			entity.setMotionMultiplier(state, new Vector3d(0.75D, 0.75D, 0.75D));
		}
	}
	
	@Override
	public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
		FluidState ifluidstate = world.getFluidState(pos.up());
		PickerelweedDoublePlantBlock doubleplantblock = (PickerelweedDoublePlantBlock) (this == UABlocks.BLUE_PICKERELWEED.get() ? UABlocks.TALL_BLUE_PICKERELWEED.get() : UABlocks.TALL_PURPLE_PICKERELWEED.get());
		if(doubleplantblock.getDefaultState().isValidPosition(world, pos) && (world.isAirBlock(pos.up()) || ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() >= 6)) {
			doubleplantblock.placeAt(world, pos, 2);
		}
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.CLAY;
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8));
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
		return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}
	
	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XYZ;
	}
	
	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return state.get(WATERLOGGED) ? 0 : 60;
	}

	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
	
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

}