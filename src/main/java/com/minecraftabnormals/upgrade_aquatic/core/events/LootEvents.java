package com.minecraftabnormals.upgrade_aquatic.core.events;

import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.List;
import java.util.Random;

//TODO: Replace with Loot Modifiers
@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID)
public class LootEvents {
	public static final LootItemCondition.Builder IN_SWAMP = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.SWAMP));
	public static final LootItemCondition.Builder IN_RIVER = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.RIVER));
	public static final LootItemCondition.Builder IN_WARM_OCEAN = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.WARM_OCEAN));

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		ResourceLocation name = event.getName();
		LootPool pool = event.getTable().getPool("main");

		if (name.equals(BuiltInLootTables.FISHING_FISH)) {
			addEntry(pool, LootItem.lootTableItem(UAItems.PIKE.get()).setWeight(11).when(IN_SWAMP.or(IN_RIVER)).build());
			addEntry(pool, LootItem.lootTableItem(UAItems.PERCH.get()).setWeight(18).when(IN_SWAMP).build());
			addEntry(pool, LootItem.lootTableItem(UAItems.LIONFISH.get()).setWeight(5).when(IN_WARM_OCEAN).build());
		}

		if (name.equals(BuiltInLootTables.FISHING_JUNK)) {
			addEntry(pool, LootItem.lootTableItem(UABlocks.DRIFTWOOD_LOG.get()).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))).build());
			addEntry(pool, LootItem.lootTableItem(UABlocks.BLUE_PICKERELWEED.get()).setWeight(12).when(IN_SWAMP.or(IN_RIVER)).build());
			addEntry(pool, LootItem.lootTableItem(UABlocks.PURPLE_PICKERELWEED.get()).setWeight(12).when(IN_SWAMP.or(IN_RIVER)).build());
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
	private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
		try {
			List<LootPoolEntryContainer> lootEntries = (List<LootPoolEntryContainer>) ObfuscationReflectionHelper.findField(LootPool.class, "f_79023_").get(pool);
			if (lootEntries.stream().anyMatch(e -> e == entry)) {
				throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
			}
			lootEntries.add(entry);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
