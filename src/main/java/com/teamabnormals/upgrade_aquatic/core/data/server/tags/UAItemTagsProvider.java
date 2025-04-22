package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import com.teamabnormals.upgrade_aquatic.common.block.CoralType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABlockTags;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAItemTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.registry.UAItems.*;

public class UAItemTagsProvider extends BlueprintItemTagsProvider {

	public UAItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagLookup<Block>> tagLookup, ExistingFileHelper helper) {
		super(UpgradeAquatic.MOD_ID, output, provider, tagLookup, helper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.copyWoodsetTags();
		this.copy(UABlockTags.DRIFTWOOD_LOGS, UAItemTags.DRIFTWOOD_LOGS);
		this.copy(UABlockTags.RIVER_LOGS, UAItemTags.RIVER_LOGS);

		this.tag(ItemTags.BOATS).add(DRIFTWOOD_BOAT.getFirst().get(), RIVER_BOAT.getFirst().get());
		this.tag(ItemTags.CHEST_BOATS).add(DRIFTWOOD_BOAT.getSecond().get(), RIVER_BOAT.getSecond().get());
		this.tag(BlueprintItemTags.FURNACE_BOATS).add(DRIFTWOOD_FURNACE_BOAT.get(), RIVER_FURNACE_BOAT.get());
		this.tag(BlueprintItemTags.LARGE_BOATS).add(LARGE_DRIFTWOOD_BOAT.get(), LARGE_RIVER_BOAT.get());

		this.tag(ItemTags.TRIM_MATERIALS).add(THRASHER_TOOTH.get());
		this.tag(ItemTags.DYEABLE).add(UABlocks.BEDROLL.asItem());
		this.tag(ItemTags.FISHES).add(PIKE.get(), COOKED_PIKE.get(), PERCH.get(), COOKED_PERCH.get(), LIONFISH.get(), COOKED_LIONFISH.get());
		this.tag(ItemTags.DECORATED_POT_SHERDS).add(PREDATOR_POTTERY_SHERD.get());

		CoralType.values().forEach(coralType -> {
			this.tag(coralType.itemTag()).add(coralType.coral().get().asItem(), coralType.fan().get().asItem());
			this.tag(coralType.deadItemTag()).add(coralType.deadCoral().get().asItem(), coralType.deadFan().get().asItem());
		});

		this.tag(UAItemTags.PRISMARINE_CORALS).add(UABlocks.PRISMARINE_CORAL_SHOWER.get().asItem());
		this.tag(UAItemTags.ELDER_PRISMARINE_CORALS).add(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get().asItem());

		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.WALLS, ItemTags.WALLS);
		this.copy(BlockTags.DOORS, ItemTags.DOORS);
		this.copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);

		this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
		this.copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);

		this.tag(Tags.Items.MUSIC_DISCS).add(MUSIC_DISC_ATLANTIS.get());
	}
}