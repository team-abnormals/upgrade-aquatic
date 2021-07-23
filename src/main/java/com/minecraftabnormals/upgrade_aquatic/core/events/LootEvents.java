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
	public static final ILootCondition.IBuilder IN_SWAMP = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.SWAMP));
	public static final ILootCondition.IBuilder IN_SWAMP_HILLS = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.SWAMP_HILLS));
	public static final ILootCondition.IBuilder IN_RIVER = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.RIVER));
	public static final ILootCondition.IBuilder IN_WARM_OCEAN = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.WARM_OCEAN));
	public static final ILootCondition.IBuilder IN_DEEP_WARM_OCEAN = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.DEEP_WARM_OCEAN));

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		ResourceLocation name = event.getName();
		LootPool pool = event.getTable().getPool("main");

		if (name.equals(LootTables.FISHING_FISH)) {
			addEntry(pool, ItemLootEntry.lootTableItem(UAItems.PIKE.get()).setWeight(11).when(IN_SWAMP.or(IN_SWAMP_HILLS).or(IN_RIVER)).build());
			addEntry(pool, ItemLootEntry.lootTableItem(UAItems.PERCH.get()).setWeight(18).when(IN_SWAMP.or(IN_SWAMP_HILLS)).build());
			addEntry(pool, ItemLootEntry.lootTableItem(UAItems.LIONFISH.get()).setWeight(5).when(IN_WARM_OCEAN.or(IN_DEEP_WARM_OCEAN)).build());
		}

		if (name.equals(LootTables.FISHING_JUNK)) {
			addEntry(pool, ItemLootEntry.lootTableItem(UABlocks.DRIFTWOOD_LOG.get()).setWeight(10).apply(SetCount.setCount(ConstantRange.exactly(5))).build());
			addEntry(pool, ItemLootEntry.lootTableItem(UABlocks.BLUE_PICKERELWEED.get()).setWeight(12).when(IN_SWAMP.or(IN_SWAMP_HILLS).or(IN_RIVER)).build());
			addEntry(pool, ItemLootEntry.lootTableItem(UABlocks.PURPLE_PICKERELWEED.get()).setWeight(12).when(IN_SWAMP.or(IN_SWAMP_HILLS).or(IN_RIVER)).build());
		}
	}

	@SubscribeEvent
	public static void addDrops(LivingDropsEvent event) {
		Random rand = event.getEntityLiving().getRandom();
		if (event.getEntity().getType() == EntityType.ELDER_GUARDIAN) {
			int spineAmount = event.getLootingLevel() > 0 ? (rand.nextInt(3) + 1) * event.getLootingLevel() : rand.nextInt(2) + 1;
			ItemEntity eyeDrop = new ItemEntity(event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(UABlocks.ELDER_EYE.get()));
			ItemEntity spineDrop = new ItemEntity(event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(UABlocks.ELDER_GUARDIAN_SPINE.get(), spineAmount));
			event.getDrops().add(eyeDrop);
			event.getDrops().add(spineDrop);
		} else if (event.getEntity().getType() == EntityType.GUARDIAN) {
			int spineAmount = (rand.nextInt(2) + rand.nextInt(event.getLootingLevel() + 1));
			ItemEntity drop = new ItemEntity(event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), new ItemStack(UABlocks.GUARDIAN_SPINE.get(), spineAmount));
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
