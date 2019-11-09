package com.teamabnormals.upgrade_aquatic.common.world;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureAmmonite;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureFloweringRush;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePickerelweed;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoral;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureSearocket;

public class UAWorldGen {

	public static void registerGenerators() {
		FeatureAmmonite.setupGeneration();
		FeaturePrismarineCoral.addFeature();
		FeaturePickerelweed.addPickerelweed();
		FeatureSearocket.addSearocket();
		FeatureFloweringRush.addFloweringRush();
	}
	
}
