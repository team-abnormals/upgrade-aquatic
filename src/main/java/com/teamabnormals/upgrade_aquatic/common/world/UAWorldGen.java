package com.teamabnormals.upgrade_aquatic.common.world;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureAmmonite;

public class UAWorldGen {

	public static void registerGenerators() {
		FeatureAmmonite.setupGeneration();
	}
	
}