package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.abnormals_core.core.utils.DataUtils;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

public class UAFlammables {
	
	public static void registerFlammables() {
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
	}
	
}