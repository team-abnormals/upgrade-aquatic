package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.block.ComposterBlock;

public class UACompostables {
	
	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.3F, UAItems.BOILED_PICKERELWEED_BLUE);
		ComposterBlock.registerCompostable(0.3F, UAItems.BOILED_PICKERELWEED_PURPLE);
	}
	
}
