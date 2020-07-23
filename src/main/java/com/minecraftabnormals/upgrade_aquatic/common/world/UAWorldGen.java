package com.minecraftabnormals.upgrade_aquatic.common.world;

import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.AmmoniteFeature;

public class UAWorldGen {

	public static void registerGenerators() {
		AmmoniteFeature.setupGeneration();
	}
	
}