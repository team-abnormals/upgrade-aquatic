package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;
import java.util.function.Supplier;

import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.TorchBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class JellyTorchBlock extends TorchBlock implements IBucketPickupHandler, ILiquidContainer {
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public JellyTorchBlock(Properties props, JellyTorchType torchType) {
		super(props, null);
		this.torchType = torchType;
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		double xOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double yOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double zOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
        double d0 = (double) pos.getX() + 0.5d + xOffset;
        double d1 = (double) pos.getY() + 0.5d + yOffset;
        double d2 = (double) pos.getZ() + 0.5d + zOffset;
        world.addParticle(this.torchType.flameParticle.get(), d0, d1, d2, 0d, 0.0d, 0d);
    }

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	    return super.updatePostPlacement(stateIn, facing, stateIn, worldIn, currentPos, facingPos);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
	        return Fluids.EMPTY;
		}
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
	
	public enum JellyTorchType {
		PINK(
			TextFormatting.LIGHT_PURPLE,
			() -> UABlocks.PINK_JELLY_TORCH.get(),
			() -> UAParticles.PINK_JELLY_FLAME,
			() -> UAParticles.PINK_JELLY_BLOB
		), 
		PURPLE(
			TextFormatting.DARK_PURPLE, 
			() -> UABlocks.PURPLE_JELLY_TORCH.get(),
			() -> UAParticles.PURPLE_JELLY_FLAME,
			() -> UAParticles.PURPLE_JELLY_BLOB
		), 
		BLUE(
			TextFormatting.BLUE, 
			() -> UABlocks.BLUE_JELLY_TORCH.get(),
			() -> UAParticles.BLUE_JELLY_FLAME,
			() -> UAParticles.BLUE_JELLY_BLOB
		), 
		GREEN(
			TextFormatting.GREEN, 
			() -> UABlocks.GREEN_JELLY_TORCH.get(),
			() -> UAParticles.GREEN_JELLY_FLAME,
			() -> UAParticles.GREEN_JELLY_BLOB
		), 
		YELLOW(
			TextFormatting.YELLOW,
			() -> UABlocks.YELLOW_JELLY_TORCH.get(),
			() -> UAParticles.YELLOW_JELLY_FLAME,
			() -> UAParticles.YELLOW_JELLY_BLOB
		), 
		ORANGE(
			TextFormatting.GOLD, 
			() -> UABlocks.ORANGE_JELLY_TORCH.get(),
			() -> UAParticles.ORANGE_JELLY_FLAME,
			() -> UAParticles.ORANGE_JELLY_BLOB
		),
		RED(
			TextFormatting.RED,
			() -> UABlocks.RED_JELLY_TORCH.get(),
			() -> UAParticles.RED_JELLY_FLAME,
			() -> UAParticles.RED_JELLY_BLOB
		),
		WHITE(
			TextFormatting.WHITE,
			() -> UABlocks.WHITE_JELLY_TORCH.get(),
			() -> UAParticles.WHITE_JELLY_FLAME,
			() -> UAParticles.WHITE_JELLY_BLOB
		);
		
		public final TextFormatting color;
		public final Supplier<Block> torch;
		public final Supplier<BasicParticleType> flameParticle;
		public final Supplier<BasicParticleType> blobParticle;
		
		private JellyTorchType(TextFormatting color, Supplier<Block> torch, Supplier<BasicParticleType> flameParticle, Supplier<BasicParticleType> blobParticle) {
			this.color = color;
			this.torch = torch;
			this.flameParticle = flameParticle;
			this.blobParticle = blobParticle;
		}
	}
}