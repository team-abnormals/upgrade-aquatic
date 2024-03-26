package com.teamabnormals.upgrade_aquatic.core.data.server.modifiers;

import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolEntriesModifier;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolsModifier;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class UALootModifierProvider extends LootModifierProvider {

	public UALootModifierProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(UpgradeAquatic.MOD_ID, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		var inSwamp = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.SWAMP));
		var inRiver = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.RIVER));
		var inWarmOcean = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiome(Biomes.WARM_OCEAN));

		this.entry(BuiltInLootTables.FISHING_FISH.getPath())
				.selects(BuiltInLootTables.FISHING_FISH)
				.addModifier(new LootPoolEntriesModifier(
						false,
						0,
						LootItem.lootTableItem(UAItems.PIKE.get()).setWeight(11).when(inSwamp.or(inRiver)).build(),
						LootItem.lootTableItem(UAItems.PERCH.get()).setWeight(18).when(inSwamp).build(),
						LootItem.lootTableItem(UAItems.LIONFISH.get()).setWeight(5).when(inWarmOcean).build()
				));

		this.entry(BuiltInLootTables.FISHING_JUNK.getPath())
				.selects(BuiltInLootTables.FISHING_JUNK)
				.addModifier(new LootPoolEntriesModifier(false, 0,
						LootItem.lootTableItem(UABlocks.DRIFTWOOD_LOG.get()).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))).build(),
						LootItem.lootTableItem(UABlocks.PICKERELWEED.get()).setWeight(12).when(inSwamp.or(inRiver)).build()
				));

		LootPool toothPool = LootPool.lootPool()
				.name(UpgradeAquatic.MOD_ID + ":thrasher_teeth")
				.setRolls(UniformGenerator.between(0, 1))
				.add(LootItem.lootTableItem(UAItems.THRASHER_TOOTH.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))))
				.build();
		this.entry("add_thrasher_teeth")
				.selects(BuiltInLootTables.UNDERWATER_RUIN_BIG, BuiltInLootTables.BURIED_TREASURE)
				.addModifier(new LootPoolsModifier(List.of(toothPool), false));

		LootPool pickerelweedPool = LootPool.lootPool()
				.name(UpgradeAquatic.MOD_ID + ":pickerelweed")
				.setRolls(UniformGenerator.between(1, 2))
				.add(LootItem.lootTableItem(UABlocks.PICKERELWEED.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(1))
				.build();
		this.entry("pickerelweed_structures")
				.selects(BuiltInLootTables.SHIPWRECK_SUPPLY)
				.addModifier(new LootPoolsModifier(List.of(pickerelweedPool), false));

		this.entry(BuiltInLootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY.getPath())
				.selects(BuiltInLootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY)
				.addModifier(new LootPoolEntriesModifier(false, 0,
						LootItem.lootTableItem(UAItems.DISC_FRAGMENT_ATLANTIS.get()).build(),
						LootItem.lootTableItem(UAItems.PREDATOR_POTTERY_SHERD.get()).build()
				));

		this.entry("entities/elder_guardian")
				.selects("entities/elder_guardian")
				.addModifier(new LootPoolsModifier(List.of(LootPool.lootPool()
						.name(UpgradeAquatic.MOD_ID + ":elder_guardian")
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(UABlocks.ELDER_EYE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
						.build()), false));
	}

}
