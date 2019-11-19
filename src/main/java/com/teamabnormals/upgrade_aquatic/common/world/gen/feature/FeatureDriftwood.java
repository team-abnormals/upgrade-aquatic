package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.dimension.DimensionType;
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
		if(world.getDimension().getType() != DimensionType.OVERWORLD) return false;
		boolean standing = rand.nextFloat() < 0.25F;
		if(standing && world.getBlockState(pos).getBlock() == Blocks.WATER && world.getBlockState(pos.down()).getBlock() != Blocks.WATER) {
			
		} else {
			Direction direction = Direction.byIndex(rand.nextInt(4) + 2);
			int length = rand.nextInt(3) + 3;
			if(world.getBlockState(pos.down()).getBlock().isIn(BlockTags.DIRT_LIKE) || world.getBlockState(pos.down()).getBlock().isIn(BlockTags.SAND)) {
				if(this.isDirectionOpen(world, pos, direction, length) && this.isGroundForDirectionMostlySuitable(world, pos, direction, length)) {
					for(int i = 0; i < length; i++) {
						this.placeDriftwoodLog(world, pos.offset(direction, i), direction);
						if(rand.nextBoolean() && i < length && i > 0) {
							Direction branchDirection = rand.nextBoolean() ? direction.rotateY() : direction.rotateYCCW();
							this.placeDriftwoodLog(world, pos.offset(direction, i).offset(branchDirection), direction);
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
	
	protected boolean isDirectionOpen(IWorld world, BlockPos pos, Direction direction, int length) {
		for(int i = 0; i < length; i++) {
			if(!world.isAirBlock(pos.offset(direction, i))) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isGroundForDirectionMostlySuitable(IWorld world, BlockPos pos, Direction direction, int length) {
		int foundGaps = 0;
		for(int i = 0; i < length; i++) {
			if(!world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(BlockTags.DIRT_LIKE) && !world.getBlockState(pos.down().offset(direction, i)).getBlock().isIn(BlockTags.SAND)) {
				foundGaps++;
			}
		}
		return foundGaps < Math.ceil(length / 2);
	}
	
	protected void placeDriftwoodLog(IWorld world, BlockPos pos, Direction direction) {
		world.setBlockState(pos, DRIFTWOOD_LOG.with(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
	}
	
	protected void placeBranch(IWorld world, BlockPos pos, Direction direction, Random rand, boolean isLarge) {
		int size = isLarge ? rand.nextInt(3) : 1;
		
		Direction branchDirection = rand.nextBoolean() ? direction.rotateY() : direction.rotateYCCW();
		
		for(int i = 0; i < size; i++) {
			BlockState[] sideStates = new BlockState[] { world.getBlockState(pos.offset(branchDirection, i).offset(branchDirection.rotateY())), world.getBlockState(pos.offset(branchDirection, i).offset(branchDirection.rotateYCCW())) };
			if(world.isAirBlock(pos.offset(branchDirection, i)) && sideStates[0].getBlock() != DRIFTWOOD_LOG.getBlock() && sideStates[1].getBlock() != DRIFTWOOD_LOG.getBlock()) {
				this.placeDriftwoodLog(world, pos.offset(branchDirection, i), branchDirection);
			}
		}
	}
	
	public static void addDriftwood() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(FeatureDriftwood::process);
	}
	
	private static void process(Biome biome) {
		if(biome.getCategory() == Category.BEACH) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.DRIFTWOOD, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(15)));
		}
	}
}