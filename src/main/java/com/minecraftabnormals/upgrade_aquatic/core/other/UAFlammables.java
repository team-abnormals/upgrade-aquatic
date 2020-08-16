package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;

public class UAFlammables {
	
	public static void registerFlammables() {
		DataUtils.registerFlammable(UABlocks.MULBERRY_VINE.get(), 60, 100);
		
		DataUtils.registerFlammable(UABlocks.BLUE_PICKERELWEED_BLOCK.get(), 30, 60);
		DataUtils.registerFlammable(UABlocks.PURPLE_PICKERELWEED_BLOCK.get(), 30, 60);
		DataUtils.registerFlammable(UABlocks.BOILED_BLUE_PICKERELWEED_BLOCK.get(), 30, 60);
		DataUtils.registerFlammable(UABlocks.BOILED_PURPLE_PICKERELWEED_BLOCK.get(), 30, 60);
		DataUtils.registerFlammable(UABlocks.BLUE_PICKERELWEED.get(), 60, 100);
		DataUtils.registerFlammable(UABlocks.PURPLE_PICKERELWEED.get(), 60, 100);
		DataUtils.registerFlammable(UABlocks.TALL_BLUE_PICKERELWEED.get(), 60, 100);
		DataUtils.registerFlammable(UABlocks.TALL_PURPLE_PICKERELWEED.get(), 60, 100);
	    
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.STRIPPED_DRIFTWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.STRIPPED_DRIFTWOOD.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.VERTICAL_DRIFTWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_BOOKSHELF.get(), 30, 20);
        
		DataUtils.registerFlammable(UABlocks.RIVER_LEAVES.get(), 30, 60);
		DataUtils.registerFlammable(UABlocks.RIVER_LOG.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.RIVER_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.STRIPPED_RIVER_LOG.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.STRIPPED_RIVER_WOOD.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.RIVER_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.RIVER_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.RIVER_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.RIVER_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.RIVER_FENCE_GATE.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.VERTICAL_RIVER_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.RIVER_LEAF_CARPET.get(), 30, 60);
		DataUtils.registerFlammable(UABlocks.RIVER_VERTICAL_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.RIVER_BOOKSHELF.get(), 30, 20);
	}
	
}