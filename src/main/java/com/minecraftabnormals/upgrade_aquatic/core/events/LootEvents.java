package com.minecraftabnormals.upgrade_aquatic.core.events;

import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.LocationCheck;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class LootEvents {
	public static final ILootCondition.IBuilder IN_SWAMP = LocationCheck.builder(LocationPredicate.Builder.builder().biome(Biomes.SWAMP));
	public static final ILootCondition.IBuilder IN_SWAMP_HILLS = LocationCheck.builder(LocationPredicate.Builder.builder().biome(Biomes.SWAMP_HILLS));
	public static final ILootCondition.IBuilder IN_RIVER = LocationCheck.builder(LocationPredicate.Builder.builder().biome(Biomes.RIVER));
	public static final ILootCondition.IBuilder IN_WARM_OCEAN = LocationCheck.builder(LocationPredicate.Builder.builder().biome(Biomes.WARM_OCEAN));
	public static final ILootCondition.IBuilder IN_DEEP_WARM_OCEAN = LocationCheck.builder(LocationPredicate.Builder.builder().biome(Biomes.DEEP_WARM_OCEAN));

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		ResourceLocation name = event.getName();
		LootPool pool = event.getTable().getPool("main");

		if (name.equals(LootTables.GAMEPLAY_FISHING_FISH)) {
			addEntry(pool, ItemLootEntry.builder(UAItems.PIKE.get()).weight(11).acceptCondition(IN_SWAMP.alternative(IN_SWAMP_HILLS).alternative(IN_RIVER)).build());
			addEntry(pool, ItemLootEntry.builder(UAItems.LIONFISH.get()).weight(5).acceptCondition(IN_SWAMP.alternative(IN_SWAMP_HILLS)).build());
			addEntry(pool, ItemLootEntry.builder(UAItems.PERCH.get()).weight(18).acceptCondition(IN_WARM_OCEAN.alternative(IN_DEEP_WARM_OCEAN)).build());
		}

		if (name.equals(LootTables.GAMEPLAY_FISHING_JUNK)) {
			addEntry(pool, ItemLootEntry.builder(UABlocks.DRIFTWOOD_LOG.get()).weight(10).acceptFunction(SetCount.builder(ConstantRange.of(5))).build());
			addEntry(pool, ItemLootEntry.builder(UABlocks.BLUE_PICKERELWEED.get()).weight(12).acceptCondition(IN_SWAMP.alternative(IN_SWAMP_HILLS).alternative(IN_RIVER)).build());
			addEntry(pool, ItemLootEntry.builder(UABlocks.PURPLE_PICKERELWEED.get()).weight(12).acceptCondition(IN_SWAMP.alternative(IN_SWAMP_HILLS).alternative(IN_RIVER)).build());
		}
	}

	@SubscribeEvent
	public static void addDrops(LivingDropsEvent event) {
		Random rand = event.getEntityLiving().getRNG();
		if (event.getEntity().getType() == EntityType.ELDER_GUARDIAN) {
			int spineAmount = event.getLootingLevel() > 0 ? (rand.nextInt(3) + 1) * event.getLootingLevel() : rand.nextInt(2) + 1;
			ItemEntity eyeDrop = new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(UABlocks.ELDER_EYE.get()));
			ItemEntity spineDrop = new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(UABlocks.ELDER_GUARDIAN_SPINE.get(), spineAmount));
			event.getDrops().add(eyeDrop);
			event.getDrops().add(spineDrop);
		} else if (event.getEntity().getType() == EntityType.GUARDIAN) {
			int spineAmount = (rand.nextInt(2) + rand.nextInt(event.getLootingLevel() + 1));
			ItemEntity drop = new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), new ItemStack(UABlocks.GUARDIAN_SPINE.get(), spineAmount));
			event.getDrops().add(drop);
		}
	}

	@SuppressWarnings("unchecked")
	private static void addEntry(LootPool pool, LootEntry entry) {
		try {
			List<LootEntry> lootEntries = (List<LootEntry>) ObfuscationReflectionHelper.findField(LootPool.class, "field_186453_a").get(pool);
			if (lootEntries.stream().anyMatch(e -> e == entry)) {
				throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
			}
			lootEntries.add(entry);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
