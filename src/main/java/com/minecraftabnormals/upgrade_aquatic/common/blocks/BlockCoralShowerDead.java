package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralPlantBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockCoralShowerDead extends DeadCoralPlantBlock {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 1.0D, 2.0D, 14.0D, 16.0D, 14.0D);

	public BlockCoralShowerDead() {
		super(Block.Properties.create(Material.ROCK, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0F));
	}
	
	public BlockCoralShowerDead(Block.Properties properties) {
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && !stateIn.isValidPosition(worldIn, currentPos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			this.updateIfDry(stateIn, worldIn, currentPos);
			if (stateIn.get(WATERLOGGED)) {
				worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			}

			return facing == Direction.UP && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
		}
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double xOffset = rand.nextBoolean() ? -(Math.random() * 0.4) : (Math.random() * 0.4);
		double yOffset = rand.nextBoolean() ? -(Math.random() * 0.4) : (Math.random() * 0.4);
		double zOffset = rand.nextBoolean() ? -(Math.random() * 0.4) : (Math.random() * 0.4);
		double d0 = (double) pos.getX() + 0.5D + xOffset;
		double d1 = (double) pos.getY() + 0.5D + yOffset;
		double d2 = (double) pos.getZ() + 0.5D + zOffset;
		worldIn.addParticle(UAParticles.ELDER_PRISMARINE_SHOWER, d0, d1, d2, 0d, 0.004d, 0d);
	}
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.up();
		return Block.hasSolidSide(worldIn.getBlockState(blockpos), worldIn, blockpos, Direction.DOWN);
	}
	
}
