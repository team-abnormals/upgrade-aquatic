package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.upgrade_aquatic.common.block.CoralType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABlockTags;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAItemTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class UAItemTagsProvider extends BlueprintItemTagsProvider {

	public UAItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagLookup<Block>> tagLookup, ExistingFileHelper helper) {
		super(UpgradeAquatic.MOD_ID, output, provider, tagLookup, helper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.copyWoodsetTags();
		this.copy(UABlockTags.DRIFTWOOD_LOGS, UAItemTags.DRIFTWOOD_LOGS);
		this.copy(UABlockTags.RIVER_LOGS, UAItemTags.RIVER_LOGS);
		this.copy(UABlockTags.BEDROLLS, UAItemTags.BEDROLLS);

		this.tag(ItemTags.BOATS).add(UAItems.DRIFTWOOD_BOAT.getFirst().get(), UAItems.RIVER_BOAT.getFirst().get());
		this.tag(ItemTags.CHEST_BOATS).add(UAItems.DRIFTWOOD_BOAT.getSecond().get(), UAItems.RIVER_BOAT.getSecond().get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(UAItems.DRIFTWOOD_FURNACE_BOAT.get(), UAItems.RIVER_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(UAItems.LARGE_DRIFTWOOD_BOAT.get(), UAItems.LARGE_RIVER_BOAT.get());

		this.tag(ItemTags.TRIM_MATERIALS).add(UAItems.THRASHER_TOOTH.get());

		CoralType.values().forEach(coralType -> {
			this.tag(coralType.itemTag()).add(coralType.coral().get().asItem(), coralType.fan().get().asItem());
			this.tag(coralType.deadItemTag()).add(coralType.deadCoral().get().asItem(), coralType.deadFan().get().asItem());
		});

		this.tag(UAItemTags.PRISMARINE_CORALS).add(UABlocks.PRISMARINE_CORAL_SHOWER.get().asItem());
		this.tag(UAItemTags.ELDER_PRISMARINE_CORALS).add(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get().asItem());
	}
}