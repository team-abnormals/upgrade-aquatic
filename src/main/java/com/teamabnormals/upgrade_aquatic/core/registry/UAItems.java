package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.items.ItemBoiledPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.items.ItemMobBucket;
import com.teamabnormals.upgrade_aquatic.common.items.ItemPikeBucket;
import com.teamabnormals.upgrade_aquatic.common.items.ItemPrismarineRod;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAItems {
	public static Item NAUTILUS_BUCKET            = new ItemMobBucket(UAEntities.NAUTILUS, Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(Reference.MODID, "nautilus_bucket");
	public static Item PIKE_BUCKET                = new ItemPikeBucket(UAEntities.PIKE, Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(Reference.MODID, "pike_bucket");
	public static Item LIONFISH_BUCKET            = new ItemMobBucket(UAEntities.LIONFISH, Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(Reference.MODID, "lionfish_bucket");
	public static Item PRISMARINE_ROD             = new ItemPrismarineRod(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)).setRegistryName(Reference.MODID, "prismarine_rod");
	public static Item THRASHER_TOOTH             = new Item(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)).setRegistryName(Reference.MODID, "thrasher_tooth");
	public static Item BOILED_PICKERELWEED_BLUE   = new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(false)).maxStackSize(64), false).setRegistryName(Reference.MODID, "boiled_pickerelweed_blue");
	public static Item BOILED_PICKERELWEED_PURPLE = new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(true)).maxStackSize(64), true).setRegistryName(Reference.MODID, "boiled_pickerelweed_purple");
	public static Item PIKE                       = new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(false)).maxStackSize(64)).setRegistryName(Reference.MODID, "pike");
	public static Item PIKE_COOKED                = new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(true)).maxStackSize(64)).setRegistryName(Reference.MODID, "pike_cooked");
	public static Item LIONFISH                   = new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(false)).maxStackSize(64)).setRegistryName(Reference.MODID, "lionfish");
	public static Item LIONFISH_COOKED            = new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(true)).maxStackSize(64)).setRegistryName(Reference.MODID, "lionfish_cooked");
	
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		final Item[] items = {
			NAUTILUS_BUCKET, PIKE_BUCKET, LIONFISH_BUCKET, PRISMARINE_ROD, THRASHER_TOOTH, BOILED_PICKERELWEED_BLUE, BOILED_PICKERELWEED_PURPLE, PIKE, PIKE_COOKED, LIONFISH, LIONFISH_COOKED
		};
		event.getRegistry().registerAll(items);
	}
}