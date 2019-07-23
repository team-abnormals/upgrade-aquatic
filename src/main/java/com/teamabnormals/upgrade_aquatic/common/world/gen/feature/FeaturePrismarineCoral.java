package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
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

public class FeaturePrismarineCoral extends Feature<NoFeatureConfig> {
	protected static final BlockState CORAL_BLOCK_BLOCK(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_BLOCK.getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_BLOCK.getDefaultState();
	}
	protected static final BlockState CORAL_BLOCK(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL.getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL.getDefaultState();
	}
	protected static final BlockState CORAL_FAN(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_FAN.getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_FAN.getDefaultState();
	}
	protected static final BlockState CORAL_WALL_FAN(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_WALL_FAN.getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.getDefaultState();
	}
	protected static final BlockState CORAL_SHOWER(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_SHOWER.getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.getDefaultState();
	}

	public FeaturePrismarineCoral(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
//		if(rand.nextDouble() <= 1) {
			FeaturePrismarineCoralShelf.placeFeature(worldIn, generator, rand, pos, config);
//		} else {
//			FeaturePrismarineStalactite.placeFeature(worldIn, generator, rand, pos, config);
//		}
		return false;
	}
	
	public static void addToOceans() {
		List<Biome> generatableBiomes = Lists.newArrayList();
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
			biome.addFeature(GenerationStage.Decoration.RAW_GENERATION, Biome.createDecoratedFeature(UAFeatures.PRISMARINE_CORAL, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.0125F)));
		}
	}
	
}
