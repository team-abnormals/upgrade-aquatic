package com.teamabnormals.upgrade_aquatic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

public class MulberryJamBlock extends HalfTransparentBlock implements SimpleWaterloggedBlock {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public MulberryJamBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
	}

	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	public boolean isStickyBlock(BlockState state) {
		return true;
	}

	@Override
	public boolean isSlimeBlock(BlockState state) {
		return true;
	}

	public boolean canStickTo(BlockState state, BlockState other) {
		if (other.getBlock() == Blocks.SLIME_BLOCK) return false;
		if (other.getBlock() == Blocks.HONEY_BLOCK) return true;
		if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity", "snail_slime_block")))
			return false;
		if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "aloe_gel_block")))
			return false;

		return super.canStickTo(state, other);
	}

	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vec3(0.2D, 0.2D, 0.2D));
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		LevelAccessor iworld = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		boolean flag = iworld.getFluidState(blockpos).getType() == Fluids.WATER;
		return this.defaultBlockState().setValue(WATERLOGGED, flag);
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
}

