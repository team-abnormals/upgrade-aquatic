package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UAParticleTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.function.Supplier;

public class JellyTorchBlock extends TorchBlock implements SimpleWaterloggedBlock {
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public JellyTorchBlock(Properties props, JellyTorchType torchType) {
		super(props, null);
		this.torchType = torchType;
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand) {
		double xOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double yOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double zOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double d0 = (double) pos.getX() + 0.5d + xOffset;
		double d1 = (double) pos.getY() + 0.5d + yOffset;
		double d2 = (double) pos.getZ() + 0.5d + zOffset;
		world.addParticle(JellyTorchType.getTorchParticleType(this.torchType), d0, d1, d2, 0d, 0.0d, 0d);
	}

	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		return super.updateShape(stateIn, facing, stateIn, worldIn, currentPos, facingPos);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return super.getStateForPlacement(context).setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	public enum JellyTorchType {
		PINK(
				ChatFormatting.LIGHT_PURPLE,
				() -> UABlocks.PINK_JELLY_TORCH.get()
		),
		PURPLE(
				ChatFormatting.DARK_PURPLE,
				() -> UABlocks.PURPLE_JELLY_TORCH.get()
		),
		BLUE(
				ChatFormatting.BLUE,
				() -> UABlocks.BLUE_JELLY_TORCH.get()
		),
		GREEN(
				ChatFormatting.GREEN,
				() -> UABlocks.GREEN_JELLY_TORCH.get()
		),
		YELLOW(
				ChatFormatting.YELLOW,
				() -> UABlocks.YELLOW_JELLY_TORCH.get()
		),
		ORANGE(
				ChatFormatting.GOLD,
				() -> UABlocks.ORANGE_JELLY_TORCH.get()
		),
		RED(
				ChatFormatting.RED,
				() -> UABlocks.RED_JELLY_TORCH.get()
		),
		WHITE(
				ChatFormatting.WHITE,
				() -> UABlocks.WHITE_JELLY_TORCH.get()
		);

		public final ChatFormatting color;
		public final Supplier<Block> torch;

		JellyTorchType(ChatFormatting color, Supplier<Block> torch) {
			this.color = color;
			this.torch = torch;
		}

		public static SimpleParticleType getTorchParticleType(JellyTorchType type) {
			return switch (type) {
				case PINK -> UAParticleTypes.PINK_JELLY_FLAME.get();
				case PURPLE -> UAParticleTypes.PURPLE_JELLY_FLAME.get();
				case BLUE -> UAParticleTypes.BLUE_JELLY_FLAME.get();
				case GREEN -> UAParticleTypes.GREEN_JELLY_FLAME.get();
				case YELLOW -> UAParticleTypes.YELLOW_JELLY_FLAME.get();
				case ORANGE -> UAParticleTypes.ORANGE_JELLY_FLAME.get();
				case RED -> UAParticleTypes.RED_JELLY_FLAME.get();
				case WHITE -> UAParticleTypes.WHITE_JELLY_FLAME.get();
			};
		}

		public static SimpleParticleType getBlobParticleType(JellyTorchType type) {
			return switch (type) {
				case PINK -> UAParticleTypes.PINK_JELLY_BLOB.get();
				case PURPLE -> UAParticleTypes.PURPLE_JELLY_BLOB.get();
				case BLUE -> UAParticleTypes.BLUE_JELLY_BLOB.get();
				case GREEN -> UAParticleTypes.GREEN_JELLY_BLOB.get();
				case YELLOW -> UAParticleTypes.YELLOW_JELLY_BLOB.get();
				case ORANGE -> UAParticleTypes.ORANGE_JELLY_BLOB.get();
				case RED -> UAParticleTypes.RED_JELLY_BLOB.get();
				case WHITE -> UAParticleTypes.WHITE_JELLY_BLOB.get();
			};
		}
	}
}