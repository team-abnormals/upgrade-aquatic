package com.teamabnormals.upgrade_aquatic.core.events;

import java.util.Set;

import com.google.common.collect.Sets;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class LootEvents {
	private static final Set<ResourceLocation> PICKERELWEED_LOOT_INJECTIONS = Sets.newHashSet(
		LootTables.CHESTS_WOODLAND_MANSION, LootTables.CHESTS_PILLAGER_OUTPOST, LootTables.CHESTS_SHIPWRECK_SUPPLY
	);
	
	private static final Set<ResourceLocation> PICKERELWEED_FISHING_LOOT_INJECTIONS = Sets.newHashSet(LootTables.GAMEPLAY_FISHING_JUNK);
	
	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		if (PICKERELWEED_LOOT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.func_216171_a(new ResourceLocation(Reference.MODID, "injections/pickerelweed_structures")).weight(1).quality(0)).build();
			event.getTable().addPool(pool);
		}
		if(PICKERELWEED_FISHING_LOOT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.func_216171_a(new ResourceLocation(Reference.MODID, "injections/pickerelweed_fishjunk")).weight(1).quality(0)).build();
			event.getTable().addPool(pool);
		}
	}
}
