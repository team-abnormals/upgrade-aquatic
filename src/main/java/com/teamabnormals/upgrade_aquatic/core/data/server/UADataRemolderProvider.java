package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.common.remolder.RemolderTypes.sequence;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addEntry;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addPool;

public final class UADataRemolderProvider extends RemolderProvider {

	public UADataRemolderProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(UpgradeAquatic.MOD_ID, Target.DATA_PACK, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		RegistryLookup<Biome> biomes = provider.lookupOrThrow(Registries.BIOME);

		LootItemCondition.Builder inSwamp = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP))));
		LootItemCondition.Builder inRiver = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(biomes.getOrThrow(Biomes.RIVER))));
		LootItemCondition.Builder inWarmOcean = LocationCheck.checkLocation(LocationPredicate.Builder.location().setBiomes(HolderSet.direct(biomes.getOrThrow(Biomes.WARM_OCEAN))));

		this.entry("loot_table/gameplay/fishing/fish")
				.path("loot_table/gameplay/fishing/fish")
				.remolder(sequence(
						addEntry(0, LootItem.lootTableItem(UAItems.PIKE.get()).setWeight(11).when(inSwamp.or(inRiver)).build()),
						addEntry(0, LootItem.lootTableItem(UAItems.PERCH.get()).setWeight(18).when(inSwamp).build()),
						addEntry(0, LootItem.lootTableItem(UAItems.LIONFISH.get()).setWeight(5).when(inWarmOcean).build())
				));

		this.entry("loot_table/gameplay/fishing/junk")
				.path("loot_table/gameplay/fishing/junk")
				.remolder(sequence(
						addEntry(0, LootItem.lootTableItem(UABlocks.DRIFTWOOD_LOG.get()).setWeight(10).apply(SetItemCountFunction.setCount(ConstantValue.exactly(5))).build()),
						addEntry(0, LootItem.lootTableItem(UABlocks.PICKERELWEED.get()).setWeight(12).when(inSwamp.or(inRiver)).build())
				));

		this.entry("loot_table/add_thrasher_teeth")
				.path("loot_table/chests/underwater_ruin_big", "loot_table/chests/buried_treasure")
				.remolder(addPool(LootPool.lootPool()
						.name(UpgradeAquatic.MOD_ID + ":thrasher_teeth")
						.setRolls(UniformGenerator.between(0, 1))
						.add(LootItem.lootTableItem(UAItems.THRASHER_TOOTH.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2))))
						.build()));

		this.entry("loot_table/pickerelweed_structures")
				.path("loot_table/chests/shipwreck_supply")
				.remolder(addPool(LootPool.lootPool()
						.name(UpgradeAquatic.MOD_ID + ":pickerelweed")
						.setRolls(UniformGenerator.between(1, 2))
						.add(LootItem.lootTableItem(UABlocks.PICKERELWEED.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(1))
						.build()));

		this.entry("loot_table/archaeology/ocean_ruin_cold")
				.path("loot_table/archaeology/ocean_ruin_cold")
				.remolder(sequence(
						addEntry(0, LootItem.lootTableItem(UAItems.DISC_FRAGMENT_ATLANTIS.get()).build()),
						addEntry(0, LootItem.lootTableItem(UAItems.PREDATOR_POTTERY_SHERD.get()).build())
				));

		this.entry("loot_table/entities/elder_guardian")
				.path("loot_table/entities/elder_guardian")
				.remolder(addPool(LootPool.lootPool()
						.name(UpgradeAquatic.MOD_ID + ":elder_guardian")
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(UABlocks.ELDER_EYE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
						.build()));
	}

}
