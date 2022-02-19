package com.minecraftabnormals.upgrade_aquatic.common.blocks.trees;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UAFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class RiverTree extends AbstractTreeGrower {

	@Nullable
	@Override
	protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
		return UAFeatures.RIVER_TREE.get().configured(UAFeatures.Configs.RIVER_TREE_CONFIG);
	}

}