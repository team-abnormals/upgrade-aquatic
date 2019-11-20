package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.api.util.BlockUtil;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

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
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureDriftwood extends Feature<NoFeatureConfig> {
	protected static final BlockState DRIFTWOOD_LOG = UABlocks.DRIFTWOOD_LOG.getDefaultState();

	public FeatureDriftwood(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean standing = rand.nextFloat() < 0.25F;
		Block downBlock = world.getBlockState(pos.down()).getBlock();
		if(standing && world.getBlockState(pos).getBlock() == Blocks.WATER && (downBlock.isIn(BlockTags.DIRT_LIKE) || downBlock.isIn(BlockTags.SAND))) {
			Direction upDirection = Direction.UP;
			if(this.isDirectionOpen(world, pos, upDirection, 3, true)) {
				for(int i = 0; i < 3; i++) {
					this.placeDriftwoodLog(world, pos.offset(upDirection, i), upDirection);
					if(rand.nextBoolean()) {
						Direction horizontalDirection = Direction.byHorizontalIndex(rand.nextInt(4));
						if(world.isAirBlock(pos.offset(upDirection, i).offset(horizontalDirection)) && BlockUtil.isPosNotTouchingBlock(world, pos.offset(upDirection, i).offset(horizontalDirection), UABlocks.DRIFTWOOD_LOG, horizontalDirection.getOpposite())) {
							this.placeDriftwoodLog(world, pos.offset(upDirection, i).offset(horizontalDirection), horizontalDirection);
						}
					}
				}
			} else {
				return false;
			}
		} else {
			Direction direction = Direction.byIndex(rand.nextInt(4) + 2);
			int length = rand.nextInt(3) + 3;
			if((world.getBiome(pos).getCategory() == Category.OCEAN && world.getBlockState(pos.down()).getBlock() == Blocks.WATER && world.isAirBlock(pos) && rand.nextFloat() < 0.25F) || (this.isNearWater(world, pos) && downBlock.isIn(BlockTags.DIRT_LIKE) || downBlock.isIn(BlockTags.SAND))) {
				if(this.isDirectionOpen(world, pos, direction, length, false) && this.isGroundForDirectionMostlySuitable(world, pos, direction, length)) {
					for(int i = 0; i < length; i++) {
						this.placeDriftwoodLog(world, pos.offset(direction, i), direction);
						if(rand.nextBoolean()) {
							this.placeBranch(world, pos.offset(direction, i), direction, rand, length >= 5);
						}
						if(rand.nextBoolean()) {
							Direction upOrDown = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
							if(world.isAirBlock(pos.offset(direction, i).offset(upOrDown)) && BlockUtil.isPosNotTouchingBlock(world, pos.offset(direction, i).offset(upOrDown), UABlocks.DRIFTWOOD_LOG, Direction.UP, Direction.DOWN)) {
								this.placeDriftwoodLog(world, pos.offset(direction, i).offset(upOrDown), upOrDown);
							}
						}
					}
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
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
	
	protected boolean isGroundForDirectionMostlySuitable(IWorld world, BlockPos pos, Direction direction, int length) {
		int foundGaps = 0;
		for(int i = 0; i < length; i++) {
			if(!world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(BlockTags.DIRT_LIKE) && !world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(BlockTags.SAND)) {
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
			if(world.isAirBlock(startPos.offset(branchDirection, i)) && sideBlocks[0] != DRIFTWOOD_LOG.getBlock() && sideBlocks[1] != DRIFTWOOD_LOG.getBlock()) {
				this.placeDriftwoodLog(world, startPos.offset(branchDirection, i), branchDirection);
			} else {
				break;
			}
		}
	}
	
	public static void addDriftwood() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(FeatureDriftwood::process);
	}
	
	private static void process(Biome biome) {
		if(biome.getCategory() == Category.BEACH) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.DRIFTWOOD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(20)));
		} else if(biome.getCategory() == Category.RIVER) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.DRIFTWOOD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(250)));
		} else if(biome.getCategory() == Category.SWAMP) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.DRIFTWOOD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(30)));
		} else if(biome.getCategory() == Category.OCEAN) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.DRIFTWOOD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
		}
	}
}