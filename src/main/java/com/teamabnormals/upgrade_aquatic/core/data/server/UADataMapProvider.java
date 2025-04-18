package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class UADataMapProvider extends DataMapProvider {

	public UADataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void gather(Provider provider) {
		this.builder(NeoForgeDataMaps.COMPOSTABLES)
				.add(UABlocks.RIVER_LEAVES.getId(), new Compostable(0.30F), false)
				.add(UABlocks.RIVER_SAPLING.getId(), new Compostable(0.30F), false)
				.add(UAItems.MULBERRY, new Compostable(0.30F), false)
				.add(UAItems.MULBERRY_BREAD, new Compostable(0.85F), false)
				.add(UAItems.MULBERRY_PIE, new Compostable(1.0F), false)
				.add(UABlocks.MULBERRY_PUNNET.getId(), new Compostable(1.0F), false)
				.add(UABlocks.MULBERRY_JAM_BLOCK.getId(), new Compostable(1.0F), false)
				.add(UABlocks.BEACHGRASS.getId(), new Compostable(0.30F), false)
				.add(UABlocks.TALL_BEACHGRASS.getId(), new Compostable(0.65F), false)
				.add(UABlocks.BEACHGRASS_THATCH.getId(), new Compostable(0.65F), false)
				.add(UABlocks.BEACHGRASS_THATCH_STAIRS.getId(), new Compostable(0.65F), false)
				.add(UABlocks.BEACHGRASS_THATCH_SLAB.getId(), new Compostable(0.65F), false)
				.add(UABlocks.PICKERELWEED.getId(), new Compostable(0.30F), false)
				.add(UAItems.BOILED_PICKERELWEED, new Compostable(0.30F), false)
				.add(UABlocks.PICKERELWEED_BLOCK.getId(), new Compostable(0.50F), false)
				.add(UABlocks.BOILED_PICKERELWEED_BLOCK.getId(), new Compostable(0.50F), false)
				.add(UABlocks.FLOWERING_RUSH.getId(), new Compostable(0.65F), false)
				.add(UABlocks.WHITE_SEAROCKET.getId(), new Compostable(0.65F), false)
				.add(UABlocks.PINK_SEAROCKET.getId(), new Compostable(0.65F), false)
				.add(UABlocks.KELP_BLOCK.getId(), new Compostable(0.50F), false);
	}
}