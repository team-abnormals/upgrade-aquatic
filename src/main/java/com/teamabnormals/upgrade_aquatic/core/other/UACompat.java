package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.upgrade_aquatic.common.dispenser.TridentDispenseBehavior;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UADecoratedPotPatterns;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.util.ObfuscationReflectionHelper;

public class UACompat {

	public static void register() {
		registerFlammables();
		registerDispenserBehaviors();
		UADecoratedPotPatterns.registerDecoratedPotPatterns();
		ObfuscationReflectionHelper.setPrivateValue(BlockBehaviour.class, Blocks.BUBBLE_COLUMN, true, "isRandomlyTicking");
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

	private static void registerDispenserBehaviors() {
		DispenserBlock.registerBehavior(Items.TRIDENT, new TridentDispenseBehavior());
		UADispenseBehaviorRegistry.registerDispenseBehaviors();
	}
}