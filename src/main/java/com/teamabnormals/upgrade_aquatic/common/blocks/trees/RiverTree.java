package com.teamabnormals.upgrade_aquatic.common.blocks.trees;

import java.util.Random;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureRiverTree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class RiverTree extends Tree {
	@Nullable
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		return UAFeatures.RIVER_TREE.get().withConfiguration(FeatureRiverTree.RIVER_TREE_CONFIG);
	}
}
