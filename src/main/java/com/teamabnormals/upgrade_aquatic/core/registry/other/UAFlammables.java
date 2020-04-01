package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.util.DataUtils;

public class UAFlammables {
	public static void registerFlammables() {
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_LOG.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_LOG_STRIPPED.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_STRIPPED.get(), 5, 5);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_PLANKS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_SLAB.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_STAIRS.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_FENCE.get(), 5, 20);
		DataUtils.registerFlammable(UABlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20);
	}
}
