package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class UACompostables {
	
	public static void registerCompostables() {
		DataUtils.registerCompostable(UABlocks.RIVER_LEAVES.get(), 0.30F);
        DataUtils.registerCompostable(UABlocks.RIVER_SAPLING.get(), 0.30F);
        DataUtils.registerCompostable(UABlocks.RIVER_LEAF_CARPET.get(), 0.30F);
		DataUtils.registerCompostable(UAItems.MULBERRY.get(), 0.30F);
		DataUtils.registerCompostable(UAItems.MULBERRY_BREAD.get(), 0.85F);
		DataUtils.registerCompostable(UAItems.MULBERRY_PIE.get(), 1.0F);
		DataUtils.registerCompostable(UAItems.MULBERRY_PUNNET.get(), 1.0F);
		DataUtils.registerCompostable(UAItems.MULBERRY_JAM_BLOCK.get(), 1.0F);

		DataUtils.registerCompostable(UABlocks.BEACHGRASS.get(), 0.30F);
		DataUtils.registerCompostable(UABlocks.TALL_BEACHGRASS.get(), 0.65F);
		DataUtils.registerCompostable(UABlocks.BEACHGRASS_THATCH.get(), 0.65F);
		DataUtils.registerCompostable(UABlocks.BEACHGRASS_THATCH_STAIRS.get(), 0.65F);
		DataUtils.registerCompostable(UABlocks.BEACHGRASS_THATCH_SLAB.get(), 0.65F);
		DataUtils.registerCompostable(UABlocks.BEACHGRASS_THATCH_VERTICAL_SLAB.get(), 0.65F);
		
		DataUtils.registerCompostable(UABlocks.BLUE_PICKERELWEED.get(), 0.30F);
		DataUtils.registerCompostable(UAItems.BOILED_BLUE_PICKERELWEED.get(), 0.30F);
		DataUtils.registerCompostable(UABlocks.BLUE_PICKERELWEED_BLOCK.get(), 0.50F);
		DataUtils.registerCompostable(UABlocks.BOILED_BLUE_PICKERELWEED_BLOCK.get(), 0.50F);
		
		DataUtils.registerCompostable(UABlocks.PURPLE_PICKERELWEED.get(), 0.30F);
		DataUtils.registerCompostable(UAItems.BOILED_PURPLE_PICKERELWEED.get(), 0.30F);
		DataUtils.registerCompostable(UABlocks.PURPLE_PICKERELWEED_BLOCK.get(), 0.50F);
		DataUtils.registerCompostable(UABlocks.BOILED_PURPLE_PICKERELWEED_BLOCK.get(), 0.50F);
		
		DataUtils.registerCompostable(UABlocks.FLOWERING_RUSH.get(), 0.65F);
		DataUtils.registerCompostable(UABlocks.WHITE_SEAROCKET.get(), 0.65F);
		DataUtils.registerCompostable(UABlocks.PINK_SEAROCKET.get(), 0.65F);
		
		DataUtils.registerCompostable(UABlocks.TONGUE_KELP.get(), 0.30F);
		DataUtils.registerCompostable(UABlocks.THORNY_KELP.get(), 0.30F);
		DataUtils.registerCompostable(UABlocks.OCHRE_KELP.get(), 0.30F);
		DataUtils.registerCompostable(UABlocks.POLAR_KELP.get(), 0.30F);
		
		DataUtils.registerCompostable(UABlocks.KELP_BLOCK.get(), 0.50F);
		DataUtils.registerCompostable(UABlocks.TONGUE_KELP_BLOCK.get(), 0.50F);
		DataUtils.registerCompostable(UABlocks.THORNY_KELP_BLOCK.get(), 0.50F);
		DataUtils.registerCompostable(UABlocks.OCHRE_KELP_BLOCK.get(), 0.50F);
		DataUtils.registerCompostable(UABlocks.POLAR_KELP_BLOCK.get(), 0.50F);
	}
	
}
