package com.teamabnormals.upgrade_aquatic.common.block.grower;

import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class RiverTreeGrower extends AbstractTreeGrower {

	@Nullable
	@Override
	protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean p_225546_2_) {
		return UAConfiguredFeatures.RIVER_TREE;
	}

}