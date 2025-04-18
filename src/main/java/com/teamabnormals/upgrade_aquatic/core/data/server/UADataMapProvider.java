package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeVariant;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps.BubbleColumnRenewable;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps.CoralstoneConversions;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps.SpottedPikeVariant;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAPikeVariants;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UADataMapProvider extends DataMapProvider {

	public UADataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void gather(Provider provider) {
		RegistryLookup<PikeVariant> pikeVariants = provider.lookupOrThrow(UARegistries.PIKE_VARIANT);
		this.builder(UADataMaps.SPOTTED_PIKE_VARIANTS)
				.add(UAPikeVariants.BROWN_NORTHERN, new SpottedPikeVariant(pikeVariants.getOrThrow(UAPikeVariants.SPOTTED_BROWN_NORTHERN), 0.2F), false)
				.add(UAPikeVariants.MAHOGANY_NORTHERN, new SpottedPikeVariant(pikeVariants.getOrThrow(UAPikeVariants.SPOTTED_MAHOGANY_NORTHERN), 0.2F), false)
				.add(UAPikeVariants.JADE_NORTHERN, new SpottedPikeVariant(pikeVariants.getOrThrow(UAPikeVariants.SPOTTED_JADE_NORTHERN), 0.2F), false)
				.add(UAPikeVariants.OLIVE_NORTHERN, new SpottedPikeVariant(pikeVariants.getOrThrow(UAPikeVariants.SPOTTED_OLIVE_NORTHERN), 0.2F), false);

		this.builder(UADataMaps.BUBBLE_COLUMN_RENEWABLES)
				.add(Blocks.SANDSTONE.builtInRegistryHolder(), new BubbleColumnRenewable(Blocks.SAND.builtInRegistryHolder()), false)
				.add(Blocks.RED_SANDSTONE.builtInRegistryHolder(), new BubbleColumnRenewable(Blocks.RED_SAND.builtInRegistryHolder()), false)
//				.add(UAConstants.ARID_SANDSTONE, new BubbleColumnRenewable(Blocks.SAND.builtInRegistryHolder()), false, new ModLoadedCondition("atmospheric"))
//				.add(UAConstants.RED_ARID_SANDSTONE, new BubbleColumnRenewable(Blocks.RED_SAND.builtInRegistryHolder()), false, new ModLoadedCondition("atmospheric"))
				.add(Blocks.COBBLESTONE.builtInRegistryHolder(), new BubbleColumnRenewable(Blocks.GRAVEL.builtInRegistryHolder()), false);

		this.builder(UADataMaps.CORALSTONE_CONVERSIONS)
				.add(Blocks.BUBBLE_CORAL_BLOCK.builtInRegistryHolder(), new CoralstoneConversions(BUBBLE_CORALSTONE, BUBBLE_CORALSTONE_STAIRS, BUBBLE_CORALSTONE_SLAB, BUBBLE_CORALSTONE_WALL, BUBBLE_CHISELED_CORALSTONE), false)
				.add(Blocks.HORN_CORAL_BLOCK.builtInRegistryHolder(), new CoralstoneConversions(HORN_CORALSTONE, HORN_CORALSTONE_STAIRS, HORN_CORALSTONE_SLAB, HORN_CORALSTONE_WALL, HORN_CHISELED_CORALSTONE), false)
				.add(Blocks.TUBE_CORAL_BLOCK.builtInRegistryHolder(), new CoralstoneConversions(TUBE_CORALSTONE, TUBE_CORALSTONE_STAIRS, TUBE_CORALSTONE_SLAB, TUBE_CORALSTONE_WALL, TUBE_CHISELED_CORALSTONE), false)
				.add(Blocks.BRAIN_CORAL_BLOCK.builtInRegistryHolder(), new CoralstoneConversions(BRAIN_CORALSTONE, BRAIN_CORALSTONE_STAIRS, BRAIN_CORALSTONE_SLAB, BRAIN_CORALSTONE_WALL, BRAIN_CHISELED_CORALSTONE), false)
				.add(Blocks.FIRE_CORAL_BLOCK.builtInRegistryHolder(), new CoralstoneConversions(FIRE_CORALSTONE, FIRE_CORALSTONE_STAIRS, FIRE_CORALSTONE_SLAB, FIRE_CORALSTONE_WALL, FIRE_CHISELED_CORALSTONE), false)
				.add(ACAN_CORAL_BLOCK, new CoralstoneConversions(ACAN_CORALSTONE, ACAN_CORALSTONE_STAIRS, ACAN_CORALSTONE_SLAB, ACAN_CORALSTONE_WALL, ACAN_CHISELED_CORALSTONE), false)
				.add(FINGER_CORAL_BLOCK, new CoralstoneConversions(FINGER_CORALSTONE, FINGER_CORALSTONE_STAIRS, FINGER_CORALSTONE_SLAB, FINGER_CORALSTONE_WALL, FINGER_CHISELED_CORALSTONE), false)
				.add(STAR_CORAL_BLOCK, new CoralstoneConversions(STAR_CORALSTONE, STAR_CORALSTONE_STAIRS, STAR_CORALSTONE_SLAB, STAR_CORALSTONE_WALL, STAR_CHISELED_CORALSTONE), false)
				.add(MOSS_CORAL_BLOCK, new CoralstoneConversions(MOSS_CORALSTONE, MOSS_CORALSTONE_STAIRS, MOSS_CORALSTONE_SLAB, MOSS_CORALSTONE_WALL, MOSS_CHISELED_CORALSTONE), false)
				.add(PETAL_CORAL_BLOCK, new CoralstoneConversions(PETAL_CORALSTONE, PETAL_CORALSTONE_STAIRS, PETAL_CORALSTONE_SLAB, PETAL_CORALSTONE_WALL, PETAL_CHISELED_CORALSTONE), false)
				.add(BRANCH_CORAL_BLOCK, new CoralstoneConversions(BRANCH_CORALSTONE, BRANCH_CORALSTONE_STAIRS, BRANCH_CORALSTONE_SLAB, BRANCH_CORALSTONE_WALL, BRANCH_CHISELED_CORALSTONE), false)
				.add(ROCK_CORAL_BLOCK, new CoralstoneConversions(ROCK_CORALSTONE, ROCK_CORALSTONE_STAIRS, ROCK_CORALSTONE_SLAB, ROCK_CORALSTONE_WALL, ROCK_CHISELED_CORALSTONE), false)
				.add(PILLOW_CORAL_BLOCK, new CoralstoneConversions(PILLOW_CORALSTONE, PILLOW_CORALSTONE_STAIRS, PILLOW_CORALSTONE_SLAB, PILLOW_CORALSTONE_WALL, PILLOW_CHISELED_CORALSTONE), false)
				.add(SILK_CORAL_BLOCK, new CoralstoneConversions(SILK_CORALSTONE, SILK_CORALSTONE_STAIRS, SILK_CORALSTONE_SLAB, SILK_CORALSTONE_WALL, SILK_CHISELED_CORALSTONE), false)
				.add(CHROME_CORAL_BLOCK, new CoralstoneConversions(CHROME_CORALSTONE, CHROME_CORALSTONE_STAIRS, CHROME_CORALSTONE_SLAB, CHROME_CORALSTONE_WALL, CHROME_CHISELED_CORALSTONE), false)
				.add(PRISMARINE_CORAL_BLOCK, new CoralstoneConversions(PRISMARINE_CORALSTONE, PRISMARINE_CORALSTONE_STAIRS, PRISMARINE_CORALSTONE_SLAB, PRISMARINE_CORALSTONE_WALL, PRISMARINE_CHISELED_CORALSTONE), false);

		this.builder(NeoForgeDataMaps.COMPOSTABLES)
				.add(RIVER_LEAVES.getId(), new Compostable(0.30F), false)
				.add(RIVER_SAPLING.getId(), new Compostable(0.30F), false)
				.add(UAItems.MULBERRY, new Compostable(0.30F), false)
				.add(UAItems.MULBERRY_BREAD, new Compostable(0.85F), false)
				.add(UAItems.MULBERRY_PIE, new Compostable(1.0F), false)
				.add(MULBERRY_PUNNET.getId(), new Compostable(1.0F), false)
				.add(MULBERRY_JAM_BLOCK.getId(), new Compostable(1.0F), false)
				.add(BEACHGRASS.getId(), new Compostable(0.30F), false)
				.add(TALL_BEACHGRASS.getId(), new Compostable(0.65F), false)
				.add(BEACHGRASS_THATCH.getId(), new Compostable(0.65F), false)
				.add(BEACHGRASS_THATCH_STAIRS.getId(), new Compostable(0.65F), false)
				.add(BEACHGRASS_THATCH_SLAB.getId(), new Compostable(0.65F), false)
				.add(PICKERELWEED.getId(), new Compostable(0.30F), false)
				.add(UAItems.BOILED_PICKERELWEED, new Compostable(0.30F), false)
				.add(PICKERELWEED_BLOCK.getId(), new Compostable(0.50F), false)
				.add(BOILED_PICKERELWEED_BLOCK.getId(), new Compostable(0.50F), false)
				.add(FLOWERING_RUSH.getId(), new Compostable(0.65F), false)
				.add(WHITE_SEAROCKET.getId(), new Compostable(0.65F), false)
				.add(PINK_SEAROCKET.getId(), new Compostable(0.65F), false)
				.add(KELP_BLOCK.getId(), new Compostable(0.50F), false);
	}
}