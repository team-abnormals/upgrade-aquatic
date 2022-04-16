package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class PrismarineCoralFeature extends Feature<NoneFeatureConfiguration> {
	protected static final BlockState CORAL_BLOCK_BLOCK(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_BLOCK.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_BLOCK.get().defaultBlockState();
	}

	protected static final BlockState CORAL_BLOCK(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL.get().defaultBlockState();
	}

	protected static final BlockState CORAL_FAN(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_FAN.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_FAN.get().defaultBlockState();
	}

	protected static final BlockState CORAL_WALL_FAN(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_WALL_FAN.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get().defaultBlockState();
	}

	protected static final BlockState CORAL_SHOWER(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_SHOWER.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get().defaultBlockState();
	}

	public PrismarineCoralFeature(Codec<NoneFeatureConfiguration> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
//		if(rand.nextDouble() <= 1) {
		PrismarineCoralShelfFeature.placeFeature(context);
//		} else {
//			FeaturePrismarineStalactite.placeFeature(worldIn, generator, rand, pos, config);
//		}
		return false;
	}

}
