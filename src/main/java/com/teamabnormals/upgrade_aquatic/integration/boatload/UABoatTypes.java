package com.teamabnormals.upgrade_aquatic.integration.boatload;

import com.teamabnormals.boatload.common.item.ChestBoatItem;
import com.teamabnormals.boatload.common.item.FurnaceBoatItem;
import com.teamabnormals.boatload.common.item.LargeBoatItem;
import com.teamabnormals.boatload.core.api.BoatloadBoatType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class UABoatTypes {
	public static final BoatloadBoatType DRIFTWOOD = BoatloadBoatType.register(BoatloadBoatType.create(new ResourceLocation(UpgradeAquatic.MOD_ID, "driftwood"), () -> UABlocks.DRIFTWOOD_PLANKS.get().asItem(), () -> UAItems.DRIFTWOOD_BOAT.get(), () -> UAItems.DRIFTWOOD_CHEST_BOAT.get(), () -> UAItems.DRIFTWOOD_FURNACE_BOAT.get(), () -> UAItems.LARGE_DRIFTWOOD_BOAT.get()));
	public static final BoatloadBoatType RIVER = BoatloadBoatType.register(BoatloadBoatType.create(new ResourceLocation(UpgradeAquatic.MOD_ID, "river"), () -> UABlocks.RIVER_PLANKS.get().asItem(), () -> UAItems.RIVER_BOAT.get(), () -> UAItems.RIVER_CHEST_BOAT.get(), () -> UAItems.RIVER_FURNACE_BOAT.get(), () -> UAItems.LARGE_RIVER_BOAT.get()));

	public static final Supplier<Item> DRIFTWOOD_CHEST_BOAT = () -> new ChestBoatItem(DRIFTWOOD);
	public static final Supplier<Item> DRIFTWOOD_FURNACE_BOAT = () -> new FurnaceBoatItem(DRIFTWOOD);
	public static final Supplier<Item> LARGE_DRIFTWOOD_BOAT = () -> new LargeBoatItem(DRIFTWOOD);

	public static final Supplier<Item> RIVER_CHEST_BOAT = () -> new ChestBoatItem(RIVER);
	public static final Supplier<Item> RIVER_FURNACE_BOAT = () -> new FurnaceBoatItem(RIVER);
	public static final Supplier<Item> LARGE_RIVER_BOAT = () -> new LargeBoatItem(RIVER);
}