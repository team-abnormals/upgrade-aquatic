package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class MulberryJamBlock extends BreakableBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public MulberryJamBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
	}
	
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}
	
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
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
        if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity", "snail_slime_block"))) return false;
        if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "aloe_gel_block"))) return false;
        
        return super.canStickTo(state, other);
    }
	
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.makeStuckInBlock(state, new Vector3d(0.2D, 0.2D, 0.2D));
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IWorld iworld = context.getLevel();
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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
}

