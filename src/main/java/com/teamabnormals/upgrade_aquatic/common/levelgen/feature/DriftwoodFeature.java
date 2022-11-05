package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.blueprint.core.util.GenerationPiece;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Random;

public class DriftwoodFeature extends Feature<NoneFeatureConfiguration> {

	public DriftwoodFeature(Codec<NoneFeatureConfiguration> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		Random rand = context.random();
		BlockPos pos = context.origin();

		boolean standing = rand.nextFloat() < 0.25F;
		BlockState downState = world.getBlockState(pos.below());
		if (standing && world.getBlockState(pos).getBlock() == Blocks.WATER && (downState.is(BlockTags.DIRT) || downState.is(Tags.Blocks.SAND))) {
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
			pos = pos.below();
			if ((rand.nextInt(150) == 0 && Biome.getBiomeCategory(world.getBiome(pos)) == BiomeCategory.OCEAN && this.canFitInOcean(world, pos, direction, length) && world.getBlockState(pos.below()).is(Blocks.WATER) && world.isEmptyBlock(pos.above())) || (Biome.getBiomeCategory(world.getBiome(pos)) != BiomeCategory.OCEAN && this.isNearWater(world, pos) && (downState.is(BlockTags.DIRT) || downState.is(BlockTags.SAND)) && this.isDirectionOpen(world, pos, direction, length) && this.isGroundForDirectionMostlySuitable(world, pos, direction, length))) {
				GenerationPiece driftwood = new GenerationPiece((iworld, part) -> {
					return world.isEmptyBlock(part.pos) || world.getFluidState(part.pos).is(FluidTags.WATER);
				});
				for (int i = 0; i < length; i++) {
					this.placeDriftwoodLog(world, pos.relative(direction, i), direction, driftwood);
					if (rand.nextBoolean()) {
						this.placeBranch(world, pos.relative(direction, i), direction, rand, length >= 5, driftwood);
					}
					if (rand.nextBoolean()) {
						Direction upOrDown = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
						if (this.isBlockPlaceableAtPos(world, pos.relative(direction, i).relative(upOrDown), Biome.getBiomeCategory(world.getBiome(pos.relative(direction, i).relative(upOrDown))) == BiomeCategory.OCEAN) && BlockUtil.isPosNotTouchingBlock(world, pos.relative(direction, i).relative(upOrDown), UABlocks.DRIFTWOOD_LOG.get(), Direction.UP, Direction.DOWN)) {
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

	private boolean isDirectionOpen(LevelAccessor world, BlockPos pos, Direction direction, int length) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(pos);
		if (direction == Direction.UP)
			return world.getFluidState(mutable).getType().is(FluidTags.WATER) && world.isEmptyBlock(mutable.above()) && world.isEmptyBlock(mutable.above(2));
		for (int i = 0; i < length; i++) {
			mutable.relative(direction, i);
			if (!world.isEmptyBlock(mutable) && !world.getFluidState(mutable).getType().is(FluidTags.WATER)) {
				return false;
			}
		}
		return true;
	}

	private boolean canFitInOcean(LevelAccessor world, BlockPos pos, Direction direction, int length) {
		for (int i = 0; i < length; i++) {
			if (!world.getBlockState(pos.relative(direction, i)).is(Blocks.WATER)) {
				return false;
			}
		}
		return true;
	}

	private boolean isGroundForDirectionMostlySuitable(LevelAccessor world, BlockPos pos, Direction direction, int length) {
		int foundGaps = 0;
		for (int i = 0; i < length; i++) {
			if (!world.getBlockState(pos.below().relative(direction, i)).is(BlockTags.DIRT) && !world.getBlockState(pos.below().relative(direction, i)).is(BlockTags.SAND)) {
				if (Biome.getBiomeCategory(world.getBiome(pos.below().relative(direction, i))) != BiomeCategory.OCEAN) {
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

	private boolean isNearWater(LevelAccessor world, BlockPos pos) {
		Holder<Biome> biome = world.getBiome(pos);
		int foundWaterSpots = 0;
		if (Biome.getBiomeCategory(biome) == BiomeCategory.RIVER) {
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

	private void placeDriftwoodLog(LevelAccessor world, BlockPos pos, Direction direction, @Nullable GenerationPiece driftwood) {
		if (driftwood != null)
			driftwood.addBlockPiece(UABlocks.DRIFTWOOD_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), pos);
		else world.setBlock(pos, UABlocks.DRIFTWOOD_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
	}

	private void placeBranch(LevelAccessor world, BlockPos startPos, Direction direction, Random rand, boolean isLarge, GenerationPiece driftwood) {
		int size = isLarge ? rand.nextInt(2) + 1 : 1;

		Direction branchDirection = rand.nextBoolean() ? direction.getClockWise() : direction.getCounterClockWise();

		for (int i = 1; i < size + 1; i++) {
			Block[] sideBlocks = new Block[]{world.getBlockState(startPos.relative(branchDirection, i).relative(branchDirection.getClockWise())).getBlock(), world.getBlockState(startPos.relative(branchDirection, i).relative(branchDirection.getCounterClockWise())).getBlock()};
			if (this.isBlockPlaceableAtPos(world, startPos.relative(branchDirection, i), Biome.getBiomeCategory(world.getBiome(startPos.relative(branchDirection, i))) == BiomeCategory.OCEAN) && sideBlocks[0] != UABlocks.DRIFTWOOD_LOG.get() && sideBlocks[1] != UABlocks.DRIFTWOOD_LOG.get()) {
				this.placeDriftwoodLog(world, startPos.relative(branchDirection, i), branchDirection, driftwood);
			} else {
				break;
			}
		}
	}

	private boolean isBlockPlaceableAtPos(LevelAccessor world, BlockPos pos, boolean inOcean) {
		Block block = world.getBlockState(pos).getBlock();
		return inOcean ? world.isEmptyBlock(pos) || block == Blocks.WATER : world.isEmptyBlock(pos);
	}
}