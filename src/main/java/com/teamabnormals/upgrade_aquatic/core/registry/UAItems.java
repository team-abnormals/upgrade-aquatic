package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.List;

import com.google.common.collect.Lists;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityUABoat;
import com.teamabnormals.upgrade_aquatic.common.items.ItemBoiledPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.items.ItemMobBucket;
import com.teamabnormals.upgrade_aquatic.common.items.ItemPikeBucket;
import com.teamabnormals.upgrade_aquatic.common.items.ItemPrismarineRod;
import com.teamabnormals.upgrade_aquatic.common.items.ItemUABoat;
import com.teamabnormals.upgrade_aquatic.core.registry.util.RegistryUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MODID);
	public static final List<RegistryObject<Item>> SPAWN_EGGS = Lists.newArrayList();
	
	public static final RegistryObject<Item> DRIFTWOOD_BOAT             = RegistryUtils.createItem("driftwood_boat", () -> new ItemUABoat(EntityUABoat.Type.DRIFTWOOD, new Item.Properties().group(ItemGroup.TRANSPORTATION).maxStackSize(1)));
	public static final RegistryObject<Item> NAUTILUS_BUCKET            = RegistryUtils.createItem("nautilus_bucket", () -> new ItemMobBucket(UAEntities.NAUTILUS.get(), () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PIKE_BUCKET                = RegistryUtils.createItem("pike_bucket", () -> new ItemPikeBucket(UAEntities.PIKE.get(), () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> LIONFISH_BUCKET            = RegistryUtils.createItem("lionfish_bucket", () -> new ItemMobBucket(UAEntities.LIONFISH.get(), () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PRISMARINE_ROD             = RegistryUtils.createItem("prismarine_rod", () -> new ItemPrismarineRod(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> THRASHER_TOOTH             = RegistryUtils.createItem("thrasher_tooth", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOILED_PICKERELWEED_BLUE   = RegistryUtils.createItem("boiled_pickerelweed_blue", () -> new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(false)), false));
	public static final RegistryObject<Item> BOILED_PICKERELWEED_PURPLE = RegistryUtils.createItem("boiled_pickerelweed_purple", () -> new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(true)), true));
	public static final RegistryObject<Item> PIKE                       = RegistryUtils.createItem("pike", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(false))));
	public static final RegistryObject<Item> PIKE_COOKED                = RegistryUtils.createItem("pike_cooked", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(true))));
	public static final RegistryObject<Item> LIONFISH                   = RegistryUtils.createItem("lionfish", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(false))));
	public static final RegistryObject<Item> LIONFISH_COOKED            = RegistryUtils.createItem("lionfish_cooked", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(true))));

	public static final RegistryObject<Item> NAUTILUS_SPAWN_EGG         = RegistryUtils.createSpawnEggItem("nautilus", () -> UAEntities.NAUTILUS.get(), 14596231, 16744272);
	public static final RegistryObject<Item> PIKE_SPAWN_EGG             = RegistryUtils.createSpawnEggItem("pike", () -> UAEntities.PIKE.get(), 4806944, 13002040);
	public static final RegistryObject<Item> LIONFISH_SPAWN_EGG         = RegistryUtils.createSpawnEggItem("lionfish", () -> UAEntities.LIONFISH.get(), 15281931, 16111310);
	public static final RegistryObject<Item> THRASHER_SPAWN_EGG         = RegistryUtils.createSpawnEggItem("thrasher", () -> UAEntities.THRASHER.get(), 7255507, 11730927);
	public static final RegistryObject<Item> FLARE_SPAWN_EGG            = RegistryUtils.createSpawnEggItem("flare", () -> UAEntities.FLARE.get(), 4532619, 14494960);
}