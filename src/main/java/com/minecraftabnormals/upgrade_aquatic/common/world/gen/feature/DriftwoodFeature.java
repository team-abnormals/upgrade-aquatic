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
	protected static final BlockState DRIFTWOOD_LOG = UABlocks.DRIFTWOOD_LOG.get().getDefaultState();

	public DriftwoodFeature(Codec<NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean standing = rand.nextFloat() < 0.25F;
		Block downBlock = world.getBlockState(pos.down()).getBlock();
		if (standing && world.getBlockState(pos).getBlock() == Blocks.WATER && (downBlock.isIn(Tags.Blocks.DIRT) || downBlock.isIn(Tags.Blocks.SAND))) {
			Direction upDirection = Direction.UP;
			if (this.isDirectionOpen(world, pos, upDirection, 3)) {
				for (int i = 0; i < 3; i++) {
					this.placeDriftwoodLog(world, pos.offset(upDirection, i), upDirection, null);
					if (rand.nextBoolean()) {
						Direction horizontalDirection = Direction.byHorizontalIndex(rand.nextInt(4));
						if (world.isAirBlock(pos.offset(upDirection, i).offset(horizontalDirection)) && BlockUtil.isPosNotTouchingBlock(world, pos.offset(upDirection, i).offset(horizontalDirection), UABlocks.DRIFTWOOD_LOG.get(), horizontalDirection.getOpposite())) {
							this.placeDriftwoodLog(world, pos.offset(upDirection, i).offset(horizontalDirection), horizontalDirection, null);
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			Direction direction = Direction.byHorizontalIndex(rand.nextInt(4));
			int length = rand.nextInt(3) + 3;
			if ((rand.nextFloat() < 0.25F && world.getBiome(pos).getCategory() == Category.OCEAN && this.canFitInOcean(world, pos, direction, length) && world.getBlockState(pos.down()).getBlock() == Blocks.WATER && world.isAirBlock(pos.up())) || (world.getBiome(pos).getCategory() != Category.OCEAN && this.isNearWater(world, pos) && downBlock.isIn(Tags.Blocks.DIRT) || downBlock.isIn(Tags.Blocks.SAND) && this.isDirectionOpen(world, pos, direction, length) && this.isGroundForDirectionMostlySuitable(world, pos, direction, length))) {
				GenerationPiece driftwood = new GenerationPiece((iworld, part) -> {
					return world.isAirBlock(part.pos) || world.getFluidState(part.pos).getFluid().isIn(FluidTags.WATER);
				});
				for (int i = 0; i < length; i++) {
					this.placeDriftwoodLog(world, pos.offset(direction, i), direction, driftwood);
					if (rand.nextBoolean()) {
						this.placeBranch(world, pos.offset(direction, i), direction, rand, length >= 5, driftwood);
					}
					if (rand.nextBoolean()) {
						Direction upOrDown = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
						if (this.isBlockPlaceableAtPos(world, pos.offset(direction, i).offset(upOrDown), world.getBiome(pos.offset(direction, i).offset(upOrDown)).getCategory() == Category.OCEAN) && BlockUtil.isPosNotTouchingBlock(world, pos.offset(direction, i).offset(upOrDown), UABlocks.DRIFTWOOD_LOG.get(), Direction.UP, Direction.DOWN)) {
							this.placeDriftwoodLog(world, pos.offset(direction, i).offset(upOrDown), upOrDown, driftwood);
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
		BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(pos);
		if (direction == Direction.UP) return world.getFluidState(mutable).getFluid().isIn(FluidTags.WATER) && world.isAirBlock(mutable.up()) && world.isAirBlock(mutable.up(2));
		for (int i = 0; i < length; i++) {
			mutable.offset(direction, i);
			if (!world.isAirBlock(mutable) && !world.getFluidState(mutable).getFluid().isIn(FluidTags.WATER)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean canFitInOcean(IWorld world, BlockPos pos, Direction direction, int length) {
		for (int i = 0; i < length; i++) {
			if (world.getBlockState(pos.offset(direction, i)).getBlock() != Blocks.WATER) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isGroundForDirectionMostlySuitable(IWorld world, BlockPos pos, Direction direction, int length) {
		int foundGaps = 0;
		for (int i = 0; i < length; i++) {
			if (!world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(Tags.Blocks.DIRT) && !world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(BlockTags.SAND)) {
				if (world.getBiome(pos.down().offset(direction, i)).getCategory() != Category.OCEAN) {
					foundGaps++;
				} else {
					if (world.getBlockState(pos.down().offset(direction, i)).getBlock() != Blocks.WATER) {
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
		if (biome.getCategory() == Category.RIVER) {
			for (int y = pos.getY() - 2; y < pos.getY(); y++) {
				for (int x = pos.getX() - 3; x < pos.getX() + 3; x++) {
					for (int z = pos.getZ() - 3; z < pos.getZ() + 3; z++) {
						BlockPos currentPos = new BlockPos(x, y, z);
						if (world.canBlockSeeSky(currentPos) && world.getBlockState(currentPos).getBlock() == Blocks.WATER) {
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
						if (world.canBlockSeeSky(currentPos) && world.getBlockState(currentPos).getBlock() == Blocks.WATER) {
							foundWaterSpots++;
						}
					}
				}
			}
		}
		return foundWaterSpots >= 3;
	}
	
	private void placeDriftwoodLog(IWorld world, BlockPos pos, Direction direction, @Nullable GenerationPiece driftwood) {
		if (driftwood != null) driftwood.addBlockPiece(DRIFTWOOD_LOG.with(RotatedPillarBlock.AXIS, direction.getAxis()), pos);
		else world.setBlockState(pos, DRIFTWOOD_LOG.with(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
	}
	
	private void placeBranch(IWorld world, BlockPos startPos, Direction direction, Random rand, boolean isLarge, GenerationPiece driftwood) {
		int size = isLarge ? rand.nextInt(2) + 1 : 1;
		
		Direction branchDirection = rand.nextBoolean() ? direction.rotateY() : direction.rotateYCCW();
		
		for(int i = 1; i < size + 1; i++) {
			Block[] sideBlocks = new Block[] { world.getBlockState(startPos.offset(branchDirection, i).offset(branchDirection.rotateY())).getBlock(), world.getBlockState(startPos.offset(branchDirection, i).offset(branchDirection.rotateYCCW())).getBlock() };
			if (this.isBlockPlaceableAtPos(world, startPos.offset(branchDirection, i), world.getBiome(startPos.offset(branchDirection, i)).getCategory() == Category.OCEAN) && sideBlocks[0] != DRIFTWOOD_LOG.getBlock() && sideBlocks[1] != DRIFTWOOD_LOG.getBlock()) {
				this.placeDriftwoodLog(world, startPos.offset(branchDirection, i), branchDirection, driftwood);
			} else {
				break;
			}
		}
	}
	
	private boolean isBlockPlaceableAtPos(IWorld world, BlockPos pos, boolean inOcean) {
		Block block = world.getBlockState(pos).getBlock();
		return inOcean ? world.isAirBlock(pos) || block == Blocks.WATER : world.isAirBlock(pos);
	}
}