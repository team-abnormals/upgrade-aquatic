package com.teamabnormals.upgrade_aquatic.common.world;

import com.teamabnormals.upgrade_aquatic.common.world.gen.WorldGenAmmonite;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoralShelf;

public class UAWorldGen {

	public static void registerGenerators() {
		WorldGenAmmonite.setupGeneration();
		FeaturePrismarineCoralShelf.addToOceans();
	}
	
}
