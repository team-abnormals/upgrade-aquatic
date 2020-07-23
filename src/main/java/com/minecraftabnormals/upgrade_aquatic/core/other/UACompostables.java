package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class UACompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.3F, UABlocks.BEACHGRASS.get());
		DataUtils.registerCompostable(0.5F, UABlocks.TALL_BEACHGRASS.get());
		DataUtils.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH.get());
		DataUtils.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH_STAIRS.get());
		DataUtils.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH_SLAB.get());
		DataUtils.registerCompostable(0.3F, UAItems.MULBERRY.get());
		DataUtils.registerCompostable(0.3F, UABlocks.BLUE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.3F, UABlocks.PURPLE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.3F, UAItems.BOILED_BLUE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.3F, UAItems.BOILED_PURPLE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.5F, UABlocks.BLUE_PICKERELWEED_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.PURPLE_PICKERELWEED_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.BOILED_BLUE_PICKERELWEED_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.BOILED_PURPLE_PICKERELWEED_BLOCK.get());
		DataUtils.registerCompostable(0.65F, UABlocks.FLOWERING_RUSH.get());
		DataUtils.registerCompostable(0.65F, UABlocks.WHITE_SEAROCKET.get());
		DataUtils.registerCompostable(0.65F, UABlocks.PINK_SEAROCKET.get());
		DataUtils.registerCompostable(0.3F, UABlocks.TONGUE_KELP.get());
		DataUtils.registerCompostable(0.3F, UABlocks.THORNY_KELP.get());
		DataUtils.registerCompostable(0.3F, UABlocks.OCHRE_KELP.get());
		DataUtils.registerCompostable(0.3F, UABlocks.POLAR_KELP.get());
		DataUtils.registerCompostable(0.5F, UABlocks.KELP_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.TONGUE_KELP_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.THORNY_KELP_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.OCHRE_KELP_BLOCK.get());
		DataUtils.registerCompostable(0.5F, UABlocks.POLAR_KELP_BLOCK.get());
	}
	
}
