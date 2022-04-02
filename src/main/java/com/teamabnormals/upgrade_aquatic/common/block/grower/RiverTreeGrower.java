package com.teamabnormals.upgrade_aquatic.common.block.grower;

import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class RiverTreeGrower extends AbstractTreeGrower {

	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
		return UAFeatures.RIVER_TREE.get().configured(UAFeatures.Configs.RIVER_TREE_CONFIG);
	}

}