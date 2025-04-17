package com.teamabnormals.upgrade_aquatic.core.data.client;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.UABlockFamilies;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UABlockStateProvider extends BlueprintBlockStateProvider {

	public UABlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.hangingSignBlocks(UABlocks.STRIPPED_DRIFTWOOD_LOG, UABlocks.DRIFTWOOD_HANGING_SIGNS.getFirst(), UABlocks.DRIFTWOOD_HANGING_SIGNS.getSecond());
		this.bookshelfBlock(DRIFTWOOD_PLANKS, DRIFTWOOD_BOOKSHELF);
		this.chiseledBookshelfBlock(CHISELED_DRIFTWOOD_BOOKSHELF, BOTTOM_BOOKSHELF_POSITIONS);

		this.hangingSignBlocks(UABlocks.STRIPPED_RIVER_LOG, UABlocks.RIVER_HANGING_SIGNS.getFirst(), UABlocks.RIVER_HANGING_SIGNS.getSecond());
		this.bookshelfBlock(RIVER_PLANKS, RIVER_BOOKSHELF);
		this.chiseledBookshelfBlock(CHISELED_RIVER_BOOKSHELF, DEFAULT_BOOKSHELF_POSITIONS);

		this.blockFamily(UABlockFamilies.KELPY_COBBLESTONE_BRICKS_FAMILY);
		this.blockFamily(UABlockFamilies.KELPY_COBBLESTONE_TILES_FAMILY);
	}
}