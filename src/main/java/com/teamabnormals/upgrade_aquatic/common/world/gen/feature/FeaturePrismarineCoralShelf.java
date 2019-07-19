package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.api.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralWallFanBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;

public class FeaturePrismarineCoralShelf extends Feature<NoFeatureConfig> {
	private static final BlockState CORAL_BLOCK_BLOCK = UABlocks.PRISMARINE_CORAL_BLOCK.getDefaultState();
	private static final BlockState CORAL_BLOCK = UABlocks.PRISMARINE_CORAL.getDefaultState();
	private static final BlockState CORAL_FAN = UABlocks.PRISMARINE_CORAL_FAN.getDefaultState();
	private static final BlockState CORAL_WALL_FAN = UABlocks.PRISMARINE_CORAL_WALL_FAN.getDefaultState();
	private static final BlockState CORAL_SHOWER = UABlocks.PRISMARINE_CORAL_SHOWER.getDefaultState();

	public FeaturePrismarineCoralShelf(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config);
	}
	
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		Direction direction = Direction.random(rand);
		if(direction == Direction.UP || direction == Direction.DOWN) {
			direction = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
		if(this.shouldPlace(world, pos, direction, rand)) {
			int a = rand.nextInt(4) + 2;
		    int c = rand.nextInt(5) + 3;
		    int b = 4;
			if(rand.nextInt(6) < 2 && pos.getY() > 11) {
				pos = pos.offset(direction.getOpposite());
				this.addShelf(world, pos, rand, a, b, c);
				if(rand.nextBoolean()) {
					this.addShelf(world, pos.offset(direction.getOpposite()).up(rand.nextInt(2) + 2), rand, 3, 3, c + 1);
					if(rand.nextBoolean()) {
						this.addShelf(world, pos.offset(direction.getOpposite()).down(rand.nextInt(2) + 2), rand, 3, 4, c + 1);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean shouldPlace(IWorld world, BlockPos pos, Direction randDirection, Random rand) {
		for(int i = 0; i < 13; i++) {
			BlockPos checkPos = pos.offset(randDirection, rand.nextInt(2) + 1).down(i);
			if(world.getBlockState(checkPos).getBlock() == Blocks.MAGMA_BLOCK || world.getBlockState(checkPos).getBlock() == Blocks.OBSIDIAN) {
				if(world.getBlockState(pos.offset(randDirection.getOpposite())).getBlock() == Blocks.STONE) {
					return true;
				}
			} else if(!world.getBlockState(checkPos).getMaterial().isReplaceable()) {
				return false;
			}
		}
		return false;
	}
	
	private void addShelf(IWorld world, BlockPos pos, Random rand, int a, int b, int c) {
		MathUtil.Equation r = (theta) -> {
			return (Math.cos(b * theta) / c + 1) * a;
		};
		for (int i = -(a / c + a); i < a / c + a; i++) {
			for (int j = -(a / c + a); j < a / c + a; j++) {
				double radius = r.compute(Math.atan2(j , i));
				BlockPos placingPos = pos.add(i, 0, j);
				if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN && (i * i + j * j) < radius * radius) {
					world.setBlockState(placingPos, CORAL_BLOCK_BLOCK, 2);
					if(rand.nextBoolean()) {
						boolean gen = rand.nextBoolean();
						if(gen && world.getBlockState(placingPos.up()).getMaterial().isReplaceable()) {
							world.setBlockState(placingPos.up(), CORAL_BLOCK, 2);
						} else if(!gen && world.getBlockState(placingPos.up()).getMaterial().isReplaceable()) {
							world.setBlockState(placingPos.up(), CORAL_FAN, 2);
						}
						world.setBlockState(placingPos.down(), CORAL_SHOWER, 2);
						for(Direction direction : Direction.Plane.HORIZONTAL) {
							if (rand.nextFloat() < 0.85F) {
				            	BlockPos blockpos1 = placingPos.offset(direction);
				            	if (world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
				            		BlockState blockstate1 = CORAL_WALL_FAN.with(DeadCoralWallFanBlock.FACING, direction);
				            		world.setBlockState(blockpos1, blockstate1, 2);
				            	}
							}
				        }
					}
				}
			}
		}
	}
	
	public static void addToOceans() {
		List<Biome> generatableBiomes = Lists.newArrayList();
		generatableBiomes.add(Biomes.STONE_SHORE);
		generatableBiomes.add(Biomes.DEEP_OCEAN);
		generatableBiomes.add(Biomes.OCEAN);
		generatableBiomes.add(Biomes.DEEP_LUKEWARM_OCEAN);
		generatableBiomes.add(Biomes.LUKEWARM_OCEAN);
		generatableBiomes.add(Biomes.DEEP_WARM_OCEAN);
		generatableBiomes.add(Biomes.WARM_OCEAN);
		generatableBiomes.add(Biomes.DEEP_COLD_OCEAN);
		generatableBiomes.add(Biomes.COLD_OCEAN);
		generatableBiomes.add(Biomes.FROZEN_OCEAN);
		generatableBiomes.add(Biomes.DEEP_FROZEN_OCEAN);
		for(Biome biome : generatableBiomes) {
			biome.addFeature(GenerationStage.Decoration.RAW_GENERATION, Biome.createDecoratedFeature(UAFeatures.PRISMARINE_CORAL_SHELF, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.0125F)));
		}
	}
	
}
