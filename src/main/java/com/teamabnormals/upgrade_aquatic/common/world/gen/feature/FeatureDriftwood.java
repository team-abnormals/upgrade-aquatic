package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.abnormals_core.core.library.api.IAddToBiomes;
import com.teamabnormals.abnormals_core.core.utils.BlockUtils;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UABlockTags;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class FeatureDriftwood extends Feature<NoFeatureConfig> implements IAddToBiomes {
	protected static final BlockState DRIFTWOOD_LOG = UABlocks.DRIFTWOOD_LOG.get().getDefaultState();

	public FeatureDriftwood(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean standing = rand.nextFloat() < 0.25F;
		Block downBlock = world.getBlockState(pos.down()).getBlock();
		if(standing && world.getBlockState(pos).getBlock() == Blocks.WATER && (downBlock.isIn(UABlockTags.DIRT_LIKE) || downBlock.isIn(BlockTags.SAND))) {
			Direction upDirection = Direction.UP;
			if(this.isDirectionOpen(world, pos, upDirection, 3, true)) {
				for(int i = 0; i < 3; i++) {
					this.placeDriftwoodLog(world, pos.offset(upDirection, i), upDirection);
					if(rand.nextBoolean()) {
						Direction horizontalDirection = Direction.byHorizontalIndex(rand.nextInt(4));
						if(world.isAirBlock(pos.offset(upDirection, i).offset(horizontalDirection)) && BlockUtils.isPosNotTouchingBlock(world, pos.offset(upDirection, i).offset(horizontalDirection), UABlocks.DRIFTWOOD_LOG.get(), horizontalDirection.getOpposite())) {
							this.placeDriftwoodLog(world, pos.offset(upDirection, i).offset(horizontalDirection), horizontalDirection);
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			Direction direction = Direction.byIndex(rand.nextInt(4) + 2);
			int length = rand.nextInt(3) + 3;
			if((world.getBiome(pos).getCategory() == Category.OCEAN && this.canFitInOcean(world, pos, direction, length) && world.getBlockState(pos.down()).getBlock() == Blocks.WATER && world.isAirBlock(pos.up()) && rand.nextFloat() < 0.25F) || (world.getBiome(pos).getCategory() != Category.OCEAN && this.isNearWater(world, pos) && downBlock.isIn(UABlockTags.DIRT_LIKE) || downBlock.isIn(BlockTags.SAND) && this.isDirectionOpen(world, pos, direction, length, false) && this.isGroundForDirectionMostlySuitable(world, pos, direction, length))) {
				for(int i = 0; i < length; i++) {
					this.placeDriftwoodLog(world, pos.offset(direction, i), direction);
					if(rand.nextBoolean()) {
						this.placeBranch(world, pos.offset(direction, i), direction, rand, length >= 5);
					}
					if(rand.nextBoolean()) {
						Direction upOrDown = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
						if(this.isBlockPlaceableAtPos(world, pos.offset(direction, i).offset(upOrDown), world.getBiome(pos.offset(direction, i).offset(upOrDown)).getCategory() == Category.OCEAN) && BlockUtils.isPosNotTouchingBlock(world, pos.offset(direction, i).offset(upOrDown), UABlocks.DRIFTWOOD_LOG.get(), Direction.UP, Direction.DOWN)) {
							this.placeDriftwoodLog(world, pos.offset(direction, i).offset(upOrDown), upOrDown);
						}
					}
				}
				return true;
			}
			return false;
		}
	}
	
	protected boolean isDirectionOpen(IWorld world, BlockPos pos, Direction direction, int length, boolean inWater) {
		if(inWater) {
			if(world.getBlockState(pos).getBlock() == Blocks.WATER && world.isAirBlock(pos.up()) && world.isAirBlock(pos.up(2))) {
				return true;
			} else {
				return false;
			}
		} else {
			for(int i = 0; i < length; i++) {
				if(!world.isAirBlock(pos.offset(direction, i))) {
					return false;
				}
			}
		}
		return true;
	}
	
	protected boolean canFitInOcean(IWorld world, BlockPos pos, Direction direction, int length) {
		for(int i = 0; i < length; i++) {
			if(world.getBlockState(pos.offset(direction, i)).getBlock() != Blocks.WATER) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isGroundForDirectionMostlySuitable(IWorld world, BlockPos pos, Direction direction, int length) {
		int foundGaps = 0;
		for(int i = 0; i < length; i++) {
			if(!world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(UABlockTags.DIRT_LIKE) && !world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(BlockTags.SAND)) {
				if(world.getBiome(pos.down().offset(direction, i)).getCategory() != Category.OCEAN) {
					foundGaps++;
				} else {
					if(world.getBlockState(pos.down().offset(direction, i)).getBlock() != Blocks.WATER) {
						foundGaps++;
					}
				}
			}
		}
		return foundGaps < Math.ceil(length / 2);
	}
	
	protected boolean isNearWater(IWorld world, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		int foundWaterSpots = 0;
		if(biome.getCategory() == Category.RIVER) {
			for(int y = pos.getY() - 2; y < pos.getY(); y++) {
				for(int x = pos.getX() - 3; x < pos.getX() + 3; x++) {
					for(int z = pos.getZ() - 3; z < pos.getZ() + 3; z++) {
						BlockPos currentPos = new BlockPos(x, y, z);
						if(world.canBlockSeeSky(currentPos) && world.getBlockState(currentPos).getBlock() == Blocks.WATER) {
							foundWaterSpots++;
						}
					}
				}
			}
		} else {
			for(int y = pos.getY() - 1; y < pos.getY(); y++) {
				for(int x = pos.getX() - 2; x < pos.getX() + 2; x++) {
					for(int z = pos.getZ() - 2; z < pos.getZ() + 2; z++) {
						BlockPos currentPos = new BlockPos(x, y, z);
						if(world.canBlockSeeSky(currentPos) && world.getBlockState(currentPos).getBlock() == Blocks.WATER) {
							foundWaterSpots++;
						}
					}
				}
			}
		}
		return foundWaterSpots >= 3;
	}
	
	protected void placeDriftwoodLog(IWorld world, BlockPos pos, Direction direction) {
		world.setBlockState(pos, DRIFTWOOD_LOG.with(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
	}
	
	protected void placeBranch(IWorld world, BlockPos startPos, Direction direction, Random rand, boolean isLarge) {
		int size = isLarge ? rand.nextInt(2) + 1 : 1;
		
		Direction branchDirection = rand.nextBoolean() ? direction.rotateY() : direction.rotateYCCW();
		
		for(int i = 1; i < size + 1; i++) {
			Block[] sideBlocks = new Block[] { world.getBlockState(startPos.offset(branchDirection, i).offset(branchDirection.rotateY())).getBlock(), world.getBlockState(startPos.offset(branchDirection, i).offset(branchDirection.rotateYCCW())).getBlock() };
			if(this.isBlockPlaceableAtPos(world, startPos.offset(branchDirection, i), world.getBiome(startPos.offset(branchDirection, i)).getCategory() == Category.OCEAN) && sideBlocks[0] != DRIFTWOOD_LOG.getBlock() && sideBlocks[1] != DRIFTWOOD_LOG.getBlock()) {
				this.placeDriftwoodLog(world, startPos.offset(branchDirection, i), branchDirection);
			} else {
				break;
			}
		}
	}
	
	protected boolean isBlockPlaceableAtPos(IWorld world, BlockPos pos, boolean inOcean) {
		Block block = world.getBlockState(pos).getBlock();
		return inOcean ? world.isAirBlock(pos) || block == Blocks.WATER : world.isAirBlock(pos);
	}

	@Override
	public Consumer<Biome> processBiomeAddition() {
		return biome -> {
			if(biome.getCategory() == Category.BEACH) {
				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(14))));
			} else if(biome.getCategory() == Category.RIVER) {
				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(125))));
			} else if(biome.getCategory() == Category.SWAMP) {
				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(21))));
			} else if(biome.getCategory() == Category.OCEAN) {
				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(UAFeatures.DRIFTWOOD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(1))));
			}
		};
	}
}