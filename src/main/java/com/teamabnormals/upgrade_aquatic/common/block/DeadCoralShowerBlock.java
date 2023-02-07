package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UAParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DeadCoralShowerBlock extends BaseCoralPlantBlock {
	protected static final VoxelShape SHAPE = Block.box(2.0D, 1.0D, 2.0D, 14.0D, 16.0D, 14.0D);

	public DeadCoralShowerBlock(Block.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.UP && !stateIn.canSurvive(worldIn, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		} else {
			this.tryScheduleDieTick(stateIn, worldIn, currentPos);
			if (stateIn.getValue(WATERLOGGED)) {
				worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}

			return facing == Direction.UP && !this.canSurvive(stateIn, worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level level, BlockPos pos, RandomSource rand) {
		double xOffset = rand.nextBoolean() ? -(Math.random() * 0.4) : (Math.random() * 0.4);
		double yOffset = rand.nextBoolean() ? -(Math.random() * 0.4) : (Math.random() * 0.4);
		double zOffset = rand.nextBoolean() ? -(Math.random() * 0.4) : (Math.random() * 0.4);
		double d0 = (double) pos.getX() + 0.5D + xOffset;
		double d1 = (double) pos.getY() + 0.5D + yOffset;
		double d2 = (double) pos.getZ() + 0.5D + zOffset;
		level.addParticle(UAParticleTypes.ELDER_PRISMARINE_SHOWER.get(), d0, d1, d2, 0d, 0.004d, 0d);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.above();
		return level.getBlockState(blockpos).isFaceSturdy(level, blockpos, Direction.DOWN);
	}
}
