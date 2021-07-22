package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.abnormals_core.core.util.GenerationPiece;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class DriftwoodFeature extends Feature<NoFeatureConfig> {
	protected static final BlockState DRIFTWOOD_LOG = UABlocks.DRIFTWOOD_LOG.get().defaultBlockState();

	public DriftwoodFeature(Codec<NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean standing = rand.nextFloat() < 0.25F;
		Block downBlock = world.getBlockState(pos.below()).getBlock();
		if (standing && world.getBlockState(pos).getBlock() == Blocks.WATER && (downBlock.is(Tags.Blocks.DIRT) || downBlock.is(Tags.Blocks.SAND))) {
			Direction upDirection = Direction.UP;
			if (this.isDirectionOpen(world, pos, upDirection, 3)) {
				for (int i = 0; i < 3; i++) {
					this.placeDriftwoodLog(world, pos.relative(upDirection, i), upDirection, null);
					if (rand.nextBoolean()) {
						Direction horizontalDirection = Direction.from2DDataValue(rand.nextInt(4));
						if (world.isEmptyBlock(pos.relative(upDirection, i).relative(horizontalDirection)) && BlockUtil.isPosNotTouchingBlock(world, pos.relative(upDirection, i).relative(horizontalDirection), UABlocks.DRIFTWOOD_LOG.get(), horizontalDirection.getOpposite())) {
							this.placeDriftwoodLog(world, pos.relative(upDirection, i).relative(horizontalDirection), horizontalDirection, null);
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			Direction direction = Direction.from2DDataValue(rand.nextInt(4));
			int length = rand.nextInt(3) + 3;
			if ((rand.nextFloat() < 0.25F && world.getBiome(pos).getBiomeCategory() == Category.OCEAN && this.canFitInOcean(world, pos, direction, length) && world.getBlockState(pos.below()).getBlock() == Blocks.WATER && world.isEmptyBlock(pos.above())) || (world.getBiome(pos).getBiomeCategory() != Category.OCEAN && this.isNearWater(world, pos) && downBlock.is(Tags.Blocks.DIRT) || downBlock.is(Tags.Blocks.SAND) && this.isDirectionOpen(world, pos, direction, length) && this.isGroundForDirectionMostlySuitable(world, pos, direction, length))) {
				GenerationPiece driftwood = new GenerationPiece((iworld, part) -> {
					return world.isEmptyBlock(part.pos) || world.getFluidState(part.pos).getType().is(FluidTags.WATER);
				});
				for (int i = 0; i < length; i++) {
					this.placeDriftwoodLog(world, pos.relative(direction, i), direction, driftwood);
					if (rand.nextBoolean()) {
						this.placeBranch(world, pos.relative(direction, i), direction, rand, length >= 5, driftwood);
					}
					if (rand.nextBoolean()) {
						Direction upOrDown = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
						if (this.isBlockPlaceableAtPos(world, pos.relative(direction, i).relative(upOrDown), world.getBiome(pos.relative(direction, i).relative(upOrDown)).getBiomeCategory() == Category.OCEAN) && BlockUtil.isPosNotTouchingBlock(world, pos.relative(direction, i).relative(upOrDown), UABlocks.DRIFTWOOD_LOG.get(), Direction.UP, Direction.DOWN)) {
							this.placeDriftwoodLog(world, pos.relative(direction, i).relative(upOrDown), upOrDown, driftwood);
						}
					}
				}
				driftwood.tryToPlace(world);
				return true;
			}
			return false;
		}
	}
	
	private boolean isDirectionOpen(IWorld world, BlockPos pos, Direction direction, int length) {
		BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);
		if (direction == Direction.UP) return world.getFluidState(mutable).getType().is(FluidTags.WATER) && world.isEmptyBlock(mutable.above()) && world.isEmptyBlock(mutable.above(2));
		for (int i = 0; i < length; i++) {
			mutable.relative(direction, i);
			if (!world.isEmptyBlock(mutable) && !world.getFluidState(mutable).getType().is(FluidTags.WATER)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean canFitInOcean(IWorld world, BlockPos pos, Direction direction, int length) {
		for (int i = 0; i < length; i++) {
			if (world.getBlockState(pos.relative(direction, i)).getBlock() != Blocks.WATER) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isGroundForDirectionMostlySuitable(IWorld world, BlockPos pos, Direction direction, int length) {
		int foundGaps = 0;
		for (int i = 0; i < length; i++) {
			if (!world.getBlockState(pos.below().relative(direction, i)).getBlock().is(Tags.Blocks.DIRT) && !world.getBlockState(pos.below().relative(direction, i)).getBlock().is(BlockTags.SAND)) {
				if (world.getBiome(pos.below().relative(direction, i)).getBiomeCategory() != Category.OCEAN) {
					foundGaps++;
				} else {
					if (world.getBlockState(pos.below().relative(direction, i)).getBlock() != Blocks.WATER) {
						foundGaps++;
					}
				}
			}
		}
		return foundGaps < Math.ceil(length / 2);
	}
	
	private boolean isNearWater(IWorld world, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		int foundWaterSpots = 0;
		if (biome.getBiomeCategory() == Category.RIVER) {
			for (int y = pos.getY() - 2; y < pos.getY(); y++) {
				for (int x = pos.getX() - 3; x < pos.getX() + 3; x++) {
					for (int z = pos.getZ() - 3; z < pos.getZ() + 3; z++) {
						BlockPos currentPos = new BlockPos(x, y, z);
						if (world.canSeeSkyFromBelowWater(currentPos) && world.getBlockState(currentPos).getBlock() == Blocks.WATER) {
							foundWaterSpots++;
						}
					}
				}
			}
		} else {
			for (int y = pos.getY() - 1; y < pos.getY(); y++) {
				for (int x = pos.getX() - 2; x < pos.getX() + 2; x++) {
					for (int z = pos.getZ() - 2; z < pos.getZ() + 2; z++) {
						BlockPos currentPos = new BlockPos(x, y, z);
						if (world.canSeeSkyFromBelowWater(currentPos) && world.getBlockState(currentPos).getBlock() == Blocks.WATER) {
							foundWaterSpots++;
						}
					}
				}
			}
		}
		return foundWaterSpots >= 3;
	}
	
	private void placeDriftwoodLog(IWorld world, BlockPos pos, Direction direction, @Nullable GenerationPiece driftwood) {
		if (driftwood != null) driftwood.addBlockPiece(DRIFTWOOD_LOG.setValue(RotatedPillarBlock.AXIS, direction.getAxis()), pos);
		else world.setBlock(pos, DRIFTWOOD_LOG.setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
	}
	
	private void placeBranch(IWorld world, BlockPos startPos, Direction direction, Random rand, boolean isLarge, GenerationPiece driftwood) {
		int size = isLarge ? rand.nextInt(2) + 1 : 1;
		
		Direction branchDirection = rand.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise();
		
		for(int i = 1; i < size + 1; i++) {
			Block[] sideBlocks = new Block[] { world.getBlockState(startPos.relative(branchDirection, i).relative(branchDirection.getClockWise())).getBlock(), world.getBlockState(startPos.relative(branchDirection, i).relative(branchDirection.getCounterClockWise())).getBlock() };
			if (this.isBlockPlaceableAtPos(world, startPos.relative(branchDirection, i), world.getBiome(startPos.relative(branchDirection, i)).getBiomeCategory() == Category.OCEAN) && sideBlocks[0] != DRIFTWOOD_LOG.getBlock() && sideBlocks[1] != DRIFTWOOD_LOG.getBlock()) {
				this.placeDriftwoodLog(world, startPos.relative(branchDirection, i), branchDirection, driftwood);
			} else {
				break;
			}
		}
	}
	
	private boolean isBlockPlaceableAtPos(IWorld world, BlockPos pos, boolean inOcean) {
		Block block = world.getBlockState(pos).getBlock();
		return inOcean ? world.isEmptyBlock(pos) || block == Blocks.WATER : world.isEmptyBlock(pos);
	}
}