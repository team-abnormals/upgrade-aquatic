package com.teamabnormals.upgrade_aquatic.common.world;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureAmmonite;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePickerelweed;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoral;

public class UAWorldGen {

	public static void registerGenerators() {
		FeatureAmmonite.setupGeneration();
		FeaturePrismarineCoral.addAmmonites();
		FeaturePickerelweed.addPickerelweed();
	}
	
}
