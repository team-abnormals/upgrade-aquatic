package com.minecraftabnormals.upgrade_aquatic.common.blocks.trees;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class RiverTree extends Tree {

	@Nullable
	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
		return UAFeatures.RIVER_TREE.get().configured(UAFeatures.Configs.RIVER_TREE_CONFIG);
	}

}