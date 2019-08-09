package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.block.ComposterBlock;

public class UACompostables {
	
	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.3F, UABlocks.PICKERELWEED_BLUE);
		ComposterBlock.registerCompostable(0.3F, UABlocks.PICKERELWEED_PURPLE);
		ComposterBlock.registerCompostable(0.3F, UAItems.BOILED_PICKERELWEED_BLUE);
		ComposterBlock.registerCompostable(0.3F, UAItems.BOILED_PICKERELWEED_PURPLE);
		ComposterBlock.registerCompostable(0.65F, UABlocks.SEAROCKET_WHITE);
		ComposterBlock.registerCompostable(0.65F, UABlocks.SEAROCKET_PINK);
	}
	
}
