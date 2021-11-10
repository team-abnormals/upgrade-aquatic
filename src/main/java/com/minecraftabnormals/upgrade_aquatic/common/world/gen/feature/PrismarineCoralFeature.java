package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class PrismarineCoralFeature extends Feature<NoFeatureConfig> {
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

	public PrismarineCoralFeature(Codec<NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
//		if(rand.nextDouble() <= 1) {
		PrismarineCoralShelfFeature.placeFeature(worldIn, generator, rand, pos, config);
//		} else {
//			FeaturePrismarineStalactite.placeFeature(worldIn, generator, rand, pos, config);
//		}
		return false;
	}

}
