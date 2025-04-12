package com.teamabnormals.upgrade_aquatic.core.other;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class UAConstants {
	public static final String CAVERNS_AND_CHASMS = "caverns_and_chasms";

	public static final ResourceLocation GLOW_BERRY_BASKET = ResourceLocation.fromNamespaceAndPath("berry_good", "glow_berry_basket");

	public static final ResourceLocation BAMBOO_LADDER = ResourceLocation.fromNamespaceAndPath("woodworks", "bamboo_ladder");
	public static final ResourceLocation BAMBOO_BEEHIVE = ResourceLocation.fromNamespaceAndPath("woodworks", "bamboo_beehive");
	public static final ResourceLocation BAMBOO_BOOKSHELF = ResourceLocation.fromNamespaceAndPath("woodworks", "bamboo_bookshelf");
	public static final ResourceLocation BAMBOO_CLOSET = ResourceLocation.fromNamespaceAndPath("woodworks", "bamboo_closet");
	public static final ResourceLocation TRAPPED_BAMBOO_CLOSET = ResourceLocation.fromNamespaceAndPath("woodworks", "trapped_bamboo_closet");

	public static final DeferredRegister.Items CAVERNS_AND_CHASMS_ITEMS = DeferredRegister.Items.createItems(UAConstants.CAVERNS_AND_CHASMS);

	public static DeferredItem<Item> COBBLESTONE_BRICKS = CAVERNS_AND_CHASMS_ITEMS.register("cobblestone_bricks", () -> new Item(new Item.Properties()));
	public static DeferredItem<Item> COBBLESTONE_TILES = CAVERNS_AND_CHASMS_ITEMS.register("cobblestone_tiles", () -> new Item(new Item.Properties()));
}
