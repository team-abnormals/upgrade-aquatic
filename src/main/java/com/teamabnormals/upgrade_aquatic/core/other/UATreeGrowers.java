package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class UATreeGrowers {
	public static final TreeGrower RIVER = new TreeGrower("upgrade_aquatic:river", Optional.empty(), Optional.of(UAConfiguredFeatures.RIVER_TREE), Optional.empty());
}