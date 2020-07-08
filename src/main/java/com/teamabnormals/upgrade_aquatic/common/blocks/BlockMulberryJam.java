package com.teamabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.IWaterLoggable;
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

public class BlockMulberryJam extends BreakableBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public BlockMulberryJam(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
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
        if (other.getBlock() == Blocks.HONEY_BLOCK) return false;
        if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity", "snail_slime_block"))) return false;
        if (other.getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "aloe_gel_block"))) return false;
        
        return super.canStickTo(state, other);
    }
	
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.setMotionMultiplier(state, new Vector3d(0.2D, 0.2D, 0.2D));
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos blockpos = context.getPos();
		boolean flag = iworld.getFluidState(blockpos).getFluid() == Fluids.WATER;
		return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(flag));
	}
	
	@SuppressWarnings("deprecation")
    @Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);	
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
}

