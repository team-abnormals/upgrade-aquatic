package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UADecoratedPotPatterns;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;

public class UACompat {

	public static void registerCompat() {
		registerCompostables();
		registerFlammables();
		UADecoratedPotPatterns.registerDecoratedPotPatterns();
	}

	public static void registerCompostables() {
		DataUtil.registerCompostable(UABlocks.RIVER_LEAVES.get(), 0.30F);
		DataUtil.registerCompostable(UABlocks.RIVER_SAPLING.get(), 0.30F);
		DataUtil.registerCompostable(UAItems.MULBERRY.get(), 0.30F);
		DataUtil.registerCompostable(UAItems.MULBERRY_BREAD.get(), 0.85F);
		DataUtil.registerCompostable(UAItems.MULBERRY_PIE.get(), 1.0F);
		DataUtil.registerCompostable(UABlocks.MULBERRY_PUNNET.get(), 1.0F);
		DataUtil.registerCompostable(UABlocks.MULBERRY_JAM_BLOCK.get(), 1.0F);

		DataUtil.registerCompostable(UABlocks.BEACHGRASS.get(), 0.30F);
		DataUtil.registerCompostable(UABlocks.TALL_BEACHGRASS.get(), 0.65F);
		DataUtil.registerCompostable(UABlocks.BEACHGRASS_THATCH.get(), 0.65F);
		DataUtil.registerCompostable(UABlocks.BEACHGRASS_THATCH_STAIRS.get(), 0.65F);
		DataUtil.registerCompostable(UABlocks.BEACHGRASS_THATCH_SLAB.get(), 0.65F);

		DataUtil.registerCompostable(UABlocks.PICKERELWEED.get(), 0.30F);
		DataUtil.registerCompostable(UAItems.BOILED_PICKERELWEED.get(), 0.30F);
		DataUtil.registerCompostable(UABlocks.PICKERELWEED_BLOCK.get(), 0.50F);
		DataUtil.registerCompostable(UABlocks.BOILED_PICKERELWEED_BLOCK.get(), 0.50F);

		DataUtil.registerCompostable(UABlocks.FLOWERING_RUSH.get(), 0.65F);
		DataUtil.registerCompostable(UABlocks.WHITE_SEAROCKET.get(), 0.65F);
		DataUtil.registerCompostable(UABlocks.PINK_SEAROCKET.get(), 0.65F);

		DataUtil.registerCompostable(UABlocks.KELP_BLOCK.get(), 0.50F);
	}

	public static void registerFlammables() {
		DataUtil.registerFlammable(UABlocks.MULBERRY_VINE.get(), 60, 100);

		DataUtil.registerFlammable(UABlocks.PICKERELWEED_BLOCK.get(), 30, 60);
		DataUtil.registerFlammable(UABlocks.BOILED_PICKERELWEED_BLOCK.get(), 30, 60);
		DataUtil.registerFlammable(UABlocks.PICKERELWEED.get(), 60, 100);
		DataUtil.registerFlammable(UABlocks.TALL_PICKERELWEED.get(), 60, 100);

		DataUtil.registerFlammable(UABlocks.BEACHGRASS.get(), 60, 20);
		DataUtil.registerFlammable(UABlocks.TALL_BEACHGRASS.get(), 60, 20);
		DataUtil.registerFlammable(UABlocks.BEACHGRASS_THATCH.get(), 60, 20);
		DataUtil.registerFlammable(UABlocks.BEACHGRASS_THATCH_STAIRS.get(), 60, 20);
		DataUtil.registerFlammable(UABlocks.BEACHGRASS_THATCH_SLAB.get(), 60, 20);

		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_LOG.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.STRIPPED_DRIFTWOOD_LOG.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.STRIPPED_DRIFTWOOD.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(UABlocks.DRIFTWOOD_BEEHIVE.get(), 5, 20);

		DataUtil.registerFlammable(UABlocks.RIVER_LEAVES.get(), 30, 60);
		DataUtil.registerFlammable(UABlocks.RIVER_LOG.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.RIVER_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.STRIPPED_RIVER_LOG.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.STRIPPED_RIVER_WOOD.get(), 5, 5);
		DataUtil.registerFlammable(UABlocks.RIVER_PLANKS.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_SLAB.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_STAIRS.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_FENCE.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_FENCE_GATE.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_BOARDS.get(), 5, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_LEAF_PILE.get(), 30, 60);
		DataUtil.registerFlammable(UABlocks.RIVER_BOOKSHELF.get(), 30, 20);
		DataUtil.registerFlammable(UABlocks.RIVER_BEEHIVE.get(), 5, 20);
	}

}