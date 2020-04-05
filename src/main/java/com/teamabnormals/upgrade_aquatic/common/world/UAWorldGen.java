package com.teamabnormals.upgrade_aquatic.common.world;

import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureAmmonite;

import net.minecraftforge.registries.ForgeRegistries;

public class UAWorldGen {

	public static void registerGenerators() {
		UAFeatures.FEATURES.getEntries().stream().filter(feature -> (feature.get() instanceof IAddToBiomes)).forEach((feature) -> {
			ForgeRegistries.BIOMES.forEach(((IAddToBiomes) feature.get()).processBiomeAddition());
		});
		FeatureAmmonite.setupGeneration();
	}
	
}