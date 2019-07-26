package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.items.ItemBoiledPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.items.ItemMobBucket;
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
	public static Item PICKEREL_BUCKET            = new ItemMobBucket(UAEntities.PICKEREL, Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(Reference.MODID, "pickerel_bucket");
	public static Item PRISMARINE_ROD             = new ItemPrismarineRod(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64)).setRegistryName(Reference.MODID, "prismarine_rod");
	public static Item BOILED_PICKERELWEED_BLUE   = new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(false)).maxStackSize(64), false).setRegistryName(Reference.MODID, "boiled_pickerelweed_blue");
	public static Item BOILED_PICKERELWEED_PURPLE = new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(true)).maxStackSize(64), true).setRegistryName(Reference.MODID, "boiled_pickerelweed_purple");
	public static Item PICKEREL                   = new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKEREL(false)).maxStackSize(64)).setRegistryName(Reference.MODID, "pickerel");
	public static Item PICKEREL_COOKED            = new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKEREL(true)).maxStackSize(64)).setRegistryName(Reference.MODID, "pickerel_cooked");
	
	@SubscribeEvent
	public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		final Item[] items = {
			NAUTILUS_BUCKET, PICKEREL_BUCKET, PRISMARINE_ROD, BOILED_PICKERELWEED_BLUE, BOILED_PICKERELWEED_PURPLE, PICKEREL, PICKEREL_COOKED
		};
		event.getRegistry().registerAll(items);
	}
}