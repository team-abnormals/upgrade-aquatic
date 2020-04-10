package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

import net.minecraft.block.ComposterBlock;

public class UACompostables {
	
	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.3F, UABlocks.BEACHGRASS.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.TALL_BEACHGRASS.get());
		ComposterBlock.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH.get());
		ComposterBlock.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH_STAIRS.get());
		ComposterBlock.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH_SLAB.get());
		ComposterBlock.registerCompostable(0.3F, UABlocks.BLUE_PICKERELWEED.get());
		ComposterBlock.registerCompostable(0.3F, UABlocks.PURPLE_PICKERELWEED.get());
		ComposterBlock.registerCompostable(0.3F, UAItems.BOILED_PICKERELWEED_BLUE.get());
		ComposterBlock.registerCompostable(0.3F, UAItems.BOILED_PICKERELWEED_PURPLE.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.BLUE_PICKERELWEED_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.PURPLE_PICKERELWEED_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.BOILED_BLUE_PICKERELWEED_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.BOILED_PURPLE_PICKERELWEED_BLOCK.get());
		ComposterBlock.registerCompostable(0.65F, UABlocks.FLOWERING_RUSH.get());
		ComposterBlock.registerCompostable(0.65F, UABlocks.WHITE_SEAROCKET.get());
		ComposterBlock.registerCompostable(0.65F, UABlocks.PINK_SEAROCKET.get());
		ComposterBlock.registerCompostable(0.3F, UABlocks.TONGUE_KELP.get());
		ComposterBlock.registerCompostable(0.3F, UABlocks.THORNY_KELP.get());
		ComposterBlock.registerCompostable(0.3F, UABlocks.OCHRE_KELP.get());
		ComposterBlock.registerCompostable(0.3F, UABlocks.POLAR_KELP.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.KELP_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.TONGUE_KELP_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.THORNY_KELP_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.OCHRE_KELP_BLOCK.get());
		ComposterBlock.registerCompostable(0.5F, UABlocks.POLAR_KELP_BLOCK.get());
	}
	
}
