package com.minecraftabnormals.upgrade_aquatic.core.events;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MODID)
public class LootEvents {
	private static final Set<ResourceLocation> TOOTH_RUINS_INJECTIONS = Sets.newHashSet(LootTables.CHESTS_UNDERWATER_RUIN_BIG);
	private static final Set<ResourceLocation> TOOTH_TREASURE_INJECTIONS = Sets.newHashSet(LootTables.CHESTS_BURIED_TREASURE);
	private static final Set<ResourceLocation> PICKERELWEED_LOOT_INJECTIONS = Sets.newHashSet(LootTables.CHESTS_SHIPWRECK_SUPPLY);
	private static final Set<ResourceLocation> PICKERELWEED_FISHINGJUNK_LOOT_INJECTIONS = Sets.newHashSet(LootTables.GAMEPLAY_FISHING_JUNK);
	
	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		if (TOOTH_RUINS_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(UpgradeAquatic.MODID, "injections/tooth_ruins")).weight(1).quality(0)).name("tooth_ruins").build();
			event.getTable().addPool(pool);
		}
		if (TOOTH_TREASURE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(UpgradeAquatic.MODID, "injections/tooth_treasure")).weight(1).quality(0)).name("tooth_treasure").build();
			event.getTable().addPool(pool);
		}
		if (PICKERELWEED_LOOT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(UpgradeAquatic.MODID, "injections/pickerelweed_structures")).weight(1).quality(0)).name("pickerelweed_structure").build();
			event.getTable().addPool(pool);
		}
		if(PICKERELWEED_FISHINGJUNK_LOOT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(UpgradeAquatic.MODID, "injections/pickerelweed_fishjunk")).weight(1).quality(0)).name("pickerelweed_fishing").build();
			event.getTable().addPool(pool);
		}
	}
	
	@SubscribeEvent
	public static void addDrops(LivingDropsEvent event) {
		Random rand = event.getEntityLiving().getRNG();
		if(event.getEntity().getType() == EntityType.ELDER_GUARDIAN) {
			int spineAmount = event.getLootingLevel() > 0 ? (rand.nextInt(3) + 1) * event.getLootingLevel() : rand.nextInt(2) + 1;
			ItemEntity eyeDrop = new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(UABlocks.ELDER_EYE.get()));
			ItemEntity spineDrop = new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(UABlocks.ELDER_GUARDIAN_SPINE.get(), spineAmount));
			event.getDrops().add(eyeDrop);
			event.getDrops().add(spineDrop);
		} else if(event.getEntity().getType() == EntityType.GUARDIAN) {
			int spineAmount = (rand.nextInt(2) + 1) + event.getLootingLevel();
			ItemEntity drop = new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(UABlocks.GUARDIAN_SPINE.get(), spineAmount));
			event.getDrops().add(drop);
		}
	}
}
