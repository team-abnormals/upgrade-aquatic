package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.AbstractBlock.Properties;

public class JellyTorchBlock extends TorchBlock implements IBucketPickupHandler, ILiquidContainer {
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public JellyTorchBlock(Properties props, JellyTorchType torchType) {
		super(props, null);
		this.torchType = torchType;
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
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
        world.addParticle(JellyTorchType.getTorchParticleType(this.torchType), d0, d1, d2, 0d, 0.0d, 0d);
    }

	@Override
	public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
	    return super.updateShape(stateIn, facing, stateIn, worldIn, currentPos, facingPos);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
	}
	
	@Override
	public Fluid takeLiquid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
	        return Fluids.EMPTY;
		}
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.getValue(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.getValue(WATERLOGGED) && fluidStateIn.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
	
	public enum JellyTorchType {
		PINK(
			TextFormatting.LIGHT_PURPLE,
			() -> UABlocks.PINK_JELLY_TORCH.get()
		), 
		PURPLE(
			TextFormatting.DARK_PURPLE, 
			() -> UABlocks.PURPLE_JELLY_TORCH.get()
		), 
		BLUE(
			TextFormatting.BLUE, 
			() -> UABlocks.BLUE_JELLY_TORCH.get()
		), 
		GREEN(
			TextFormatting.GREEN, 
			() -> UABlocks.GREEN_JELLY_TORCH.get()
		), 
		YELLOW(
			TextFormatting.YELLOW,
			() -> UABlocks.YELLOW_JELLY_TORCH.get()
		), 
		ORANGE(
			TextFormatting.GOLD, 
			() -> UABlocks.ORANGE_JELLY_TORCH.get()
		),
		RED(
			TextFormatting.RED,
			() -> UABlocks.RED_JELLY_TORCH.get()
		),
		WHITE(
			TextFormatting.WHITE,
			() -> UABlocks.WHITE_JELLY_TORCH.get()
		);
		
		public final TextFormatting color;
		public final Supplier<Block> torch;
		
		private JellyTorchType(TextFormatting color, Supplier<Block> torch) {
			this.color = color;
			this.torch = torch;
		}
		
		public static BasicParticleType getTorchParticleType(JellyTorchType type) {
			switch(type) {
				default:
	         	case PINK:
	         		return UAParticles.PINK_JELLY_FLAME.get();
	         	case PURPLE:
	         		return UAParticles.PURPLE_JELLY_FLAME.get();
	         	case BLUE:
	                return UAParticles.BLUE_JELLY_FLAME.get();
	         	case GREEN:
	                return UAParticles.GREEN_JELLY_FLAME.get();
	         	case YELLOW:
	                return UAParticles.YELLOW_JELLY_FLAME.get();
	         	case ORANGE:
	                return UAParticles.ORANGE_JELLY_FLAME.get();
	         	case RED:
	                return UAParticles.RED_JELLY_FLAME.get();
	         	case WHITE:
	                return UAParticles.WHITE_JELLY_FLAME.get();
	        }
	    }

		public static BasicParticleType getBlobParticleType(JellyTorchType type) {
			switch (type) {
				default:
				case PINK:
	         		return UAParticles.PINK_JELLY_BLOB.get();
	         	case PURPLE:
	         		return UAParticles.PURPLE_JELLY_BLOB.get();
	         	case BLUE:
	                return UAParticles.BLUE_JELLY_BLOB.get();
	         	case GREEN:
	                return UAParticles.GREEN_JELLY_BLOB.get();
	         	case YELLOW:
	                return UAParticles.YELLOW_JELLY_BLOB.get();
	         	case ORANGE:
	                return UAParticles.ORANGE_JELLY_BLOB.get();
	         	case RED:
	                return UAParticles.RED_JELLY_BLOB.get();
	         	case WHITE:
	                return UAParticles.WHITE_JELLY_BLOB.get();
	        }
	    }
	}
}