package com.teamabnormals.upgrade_aquatic.common.world;

import java.util.function.Consumer;

import net.minecraft.world.biome.Biome;

public interface IAddToBiomes {
	Consumer<Biome> processBiomeAddition();
}