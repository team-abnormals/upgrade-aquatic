package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.abnormals_core.core.library.api.IAddToBiomes;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class FeaturePrismarineCoral extends Feature<NoFeatureConfig> implements IAddToBiomes {
	protected static final BlockState CORAL_BLOCK_BLOCK(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_BLOCK.get().getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_BLOCK.get().getDefaultState();
	}
	protected static final BlockState CORAL_BLOCK(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL.get().getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL.get().getDefaultState();
	}
	protected static final BlockState CORAL_FAN(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_FAN.get().getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_FAN.get().getDefaultState();
	}
	protected static final BlockState CORAL_WALL_FAN(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_WALL_FAN.get().getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get().getDefaultState();
	}
	protected static final BlockState CORAL_SHOWER(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_SHOWER.get().getDefaultState() : UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get().getDefaultState();
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
	
	@Override
	public Consumer<Biome> processBiomeAddition() {
		return biome -> {
			biome.addFeature(GenerationStage.Decoration.RAW_GENERATION, UAFeatures.PRISMARINE_CORAL.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.LIQUID, 0.0125F))));
		};
	}
	
}
