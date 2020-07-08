package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

import com.teamabnormals.upgrade_aquatic.client.particle.UAParticles;

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

public class BlockJellyTorch extends TorchBlock implements IBucketPickupHandler, ILiquidContainer {
	public enum JellyTorchType {
		PINK(TextFormatting.LIGHT_PURPLE), 
		PURPLE(TextFormatting.DARK_PURPLE), 
		BLUE(TextFormatting.BLUE), 
		GREEN(TextFormatting.GREEN), 
		YELLOW(TextFormatting.YELLOW), 
		ORANGE(TextFormatting.GOLD),
		RED(TextFormatting.RED),
		WHITE(TextFormatting.WHITE);
		
		public final TextFormatting color;
		
		private JellyTorchType(TextFormatting color) {
			this.color = color;
		}
	}
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BlockJellyTorch(Properties props, JellyTorchType torchType) {
		super(props);
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
        world.addParticle(getTorchParticleType(this.torchType), d0, d1, d2, 0d, 0.0d, 0d);
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
	
	public static BasicParticleType getTorchParticleType(JellyTorchType type) {
		switch(type) {
			default:
         	case PINK:
         		return UAParticles.PINK_JELLY_FLAME;
         	case PURPLE:
         		return UAParticles.PURPLE_JELLY_FLAME;
         	case BLUE:
                return UAParticles.BLUE_JELLY_FLAME;
         	case GREEN:
                return UAParticles.GREEN_JELLY_FLAME;
         	case YELLOW:
                return UAParticles.YELLOW_JELLY_FLAME;
         	case ORANGE:
                return UAParticles.ORANGE_JELLY_FLAME;
         	case RED:
                return UAParticles.RED_JELLY_FLAME;
         	case WHITE:
                return UAParticles.WHITE_JELLY_FLAME;
        }
    }
	
	public static BasicParticleType getBlobParticleType(JellyTorchType type) {
		switch(type) {
			default:
         	case PINK:
         		return UAParticles.PINK_JELLY_BLOB;
         	case PURPLE:
         		return UAParticles.PURPLE_JELLY_BLOB;
         	case BLUE:
                return UAParticles.BLUE_JELLY_BLOB;
         	case GREEN:
                return UAParticles.GREEN_JELLY_BLOB;
         	case YELLOW:
                return UAParticles.YELLOW_JELLY_BLOB;
         	case ORANGE:
                return UAParticles.ORANGE_JELLY_BLOB;
         	case RED:
                return UAParticles.RED_JELLY_BLOB;
         	case WHITE:
                return UAParticles.WHITE_JELLY_BLOB;
        }
    }
}
