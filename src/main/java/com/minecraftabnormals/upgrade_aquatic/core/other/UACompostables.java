package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.minecraftabnormals.abnormals_core.core.utils.DataUtils;

public class UACompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(0.30F, UABlocks.RIVER_LEAVES.get());
        DataUtils.registerCompostable(0.30F, UABlocks.RIVER_SAPLING.get());
        DataUtils.registerCompostable(0.30F, UABlocks.RIVER_LEAF_CARPET.get());
		DataUtils.registerCompostable(0.30F, UAItems.MULBERRY.get());

		DataUtils.registerCompostable(0.30F, UABlocks.BEACHGRASS.get());
		DataUtils.registerCompostable(0.50F, UABlocks.TALL_BEACHGRASS.get());
		DataUtils.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH.get());
		DataUtils.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH_STAIRS.get());
		DataUtils.registerCompostable(0.65F, UABlocks.BEACHGRASS_THATCH_SLAB.get());
		
		DataUtils.registerCompostable(0.30F, UABlocks.BLUE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.30F, UAItems.BOILED_BLUE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.50F, UABlocks.BLUE_PICKERELWEED_BLOCK.get());
		DataUtils.registerCompostable(0.50F, UABlocks.BOILED_BLUE_PICKERELWEED_BLOCK.get());
		
		DataUtils.registerCompostable(0.30F, UABlocks.PURPLE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.30F, UAItems.BOILED_PURPLE_PICKERELWEED.get());
		DataUtils.registerCompostable(0.50F, UABlocks.PURPLE_PICKERELWEED_BLOCK.get());
		DataUtils.registerCompostable(0.50F, UABlocks.BOILED_PURPLE_PICKERELWEED_BLOCK.get());
		
		DataUtils.registerCompostable(0.65F, UABlocks.FLOWERING_RUSH.get());
		DataUtils.registerCompostable(0.65F, UABlocks.WHITE_SEAROCKET.get());
		DataUtils.registerCompostable(0.65F, UABlocks.PINK_SEAROCKET.get());
		
		DataUtils.registerCompostable(0.30F, UABlocks.TONGUE_KELP.get());
		DataUtils.registerCompostable(0.30F, UABlocks.THORNY_KELP.get());
		DataUtils.registerCompostable(0.30F, UABlocks.OCHRE_KELP.get());
		DataUtils.registerCompostable(0.30F, UABlocks.POLAR_KELP.get());
		
		DataUtils.registerCompostable(0.50F, UABlocks.KELP_BLOCK.get());
		DataUtils.registerCompostable(0.50F, UABlocks.TONGUE_KELP_BLOCK.get());
		DataUtils.registerCompostable(0.50F, UABlocks.THORNY_KELP_BLOCK.get());
		DataUtils.registerCompostable(0.50F, UABlocks.OCHRE_KELP_BLOCK.get());
		DataUtils.registerCompostable(0.50F, UABlocks.POLAR_KELP_BLOCK.get());
	}
	
}
