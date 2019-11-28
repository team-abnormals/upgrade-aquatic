package com.teamabnormals.upgrade_aquatic.core.registry;

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
	
	public static final RegistryObject<Item> DRIFTWOOD_BOAT             = RegistryUtils.createItem("driftwood_boat", () -> new ItemUABoat(EntityUABoat.Type.DRIFTWOOD, new Item.Properties().group(ItemGroup.TRANSPORTATION).maxStackSize(1)));
	public static final RegistryObject<Item> NAUTILUS_BUCKET            = RegistryUtils.createItem("nautilus_bucket", () -> new ItemMobBucket(UAEntities.NAUTILUS, () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PIKE_BUCKET                = RegistryUtils.createItem("pike_bucket", () -> new ItemPikeBucket(UAEntities.PIKE, () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> LIONFISH_BUCKET            = RegistryUtils.createItem("lionfish_bucket", () -> new ItemMobBucket(UAEntities.LIONFISH, () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PRISMARINE_ROD             = RegistryUtils.createItem("prismarine_rod", () -> new ItemPrismarineRod(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> THRASHER_TOOTH             = RegistryUtils.createItem("thrasher_tooth", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOILED_PICKERELWEED_BLUE   = RegistryUtils.createItem("boiled_pickerelweed_blue", () -> new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(false)), false));
	public static final RegistryObject<Item> BOILED_PICKERELWEED_PURPLE = RegistryUtils.createItem("boiled_pickerelweed_purple", () -> new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(true)), true));
	public static final RegistryObject<Item> PIKE                       = RegistryUtils.createItem("pike", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(false))));
	public static final RegistryObject<Item> PIKE_COOKED                = RegistryUtils.createItem("pike_cooked", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(true))));
	public static final RegistryObject<Item> LIONFISH                   = RegistryUtils.createItem("lionfish", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(false))));
	public static final RegistryObject<Item> LIONFISH_COOKED            = RegistryUtils.createItem("lionfish_cooked", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(true))));
}