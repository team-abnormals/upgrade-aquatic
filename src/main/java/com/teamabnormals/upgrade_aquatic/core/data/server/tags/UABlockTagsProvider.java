package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UABlockTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

//TODO: Add all the other block tags to this
public class UABlockTagsProvider extends BlockTagsProvider {

	public UABlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		IntrinsicTagAppender<Block> mineableWithPickaxe = this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
		IntrinsicTagAppender<Block> mineableWithHoe = this.tag(BlockTags.MINEABLE_WITH_HOE);

		this.tag(BlockTags.MINEABLE_WITH_AXE).add(MULBERRY_PUNNET.get());
		mineableWithPickaxe.add(EMBEDDED_AMMONITE.get(), PRISMARINE_ROD_BUNDLE.get(), ELDER_EYE.get());
		mineableWithHoe.add(RIVER_LEAVES.get());

		for (DeferredHolder<Block, ? extends Block> block : HELPER.getDeferredRegister().getEntries()) {
			String path = block.getId().getPath();
			if (path.contains("luminous_prismarine") || (path.contains("dead") && path.contains("coral")) || path.contains("coral_block") || path.contains("tooth") || path.contains("scute") || path.contains("coralstone") || path.contains("kelpy_cobblestone") || path.contains("kelpy_stone")) {
				mineableWithPickaxe.add(block.get());
			} else if (path.contains("beachgrass") || path.contains("pickerelweed_block") || path.contains("kelp_block")) {
				mineableWithHoe.add(block.get());
			}
		}

		this.tag(BlockTags.PLANKS).add(DRIFTWOOD_PLANKS.get(), RIVER_PLANKS.get());
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(UABlockTags.DRIFTWOOD_LOGS).addTag(UABlockTags.RIVER_LOGS);
		this.tag(BlockTags.WOODEN_SLABS).add(DRIFTWOOD_SLAB.get(), RIVER_SLAB.get());
		this.tag(BlockTags.WOODEN_STAIRS).add(DRIFTWOOD_STAIRS.get(), RIVER_STAIRS.get());
		this.tag(BlockTags.WOODEN_FENCES).add(DRIFTWOOD_FENCE.get(), RIVER_FENCE.get());
		this.tag(BlockTags.FENCE_GATES).add(DRIFTWOOD_FENCE_GATE.get(), RIVER_FENCE_GATE.get());
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(DRIFTWOOD_FENCE_GATE.get(), RIVER_FENCE_GATE.get());
		this.tag(BlockTags.WOODEN_DOORS).add(DRIFTWOOD_DOOR.get(), RIVER_DOOR.get());
		this.tag(BlockTags.WOODEN_TRAPDOORS).add(DRIFTWOOD_TRAPDOOR.get(), RIVER_TRAPDOOR.get());
		this.tag(BlockTags.WOODEN_BUTTONS).add(DRIFTWOOD_BUTTON.get(), RIVER_BUTTON.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(DRIFTWOOD_PRESSURE_PLATE.get(), RIVER_PRESSURE_PLATE.get());
		this.tag(BlockTags.STANDING_SIGNS).add(DRIFTWOOD_SIGNS.getFirst().get(), RIVER_SIGNS.getFirst().get());
		this.tag(BlockTags.WALL_SIGNS).add(DRIFTWOOD_SIGNS.getSecond().get(), RIVER_SIGNS.getSecond().get());
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(DRIFTWOOD_HANGING_SIGNS.getFirst().get(), RIVER_HANGING_SIGNS.getFirst().get());
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(DRIFTWOOD_HANGING_SIGNS.getSecond().get(), RIVER_HANGING_SIGNS.getSecond().get());
		this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(DRIFTWOOD_LOG.get(), RIVER_LOG.get());
		this.tag(BlockTags.LEAVES).add(RIVER_LEAVES.get());
		this.tag(BlockTags.SAPLINGS).add(RIVER_SAPLING.get());

		this.tag(UABlockTags.DRIFTWOOD_LOGS).add(DRIFTWOOD_LOG.get(), DRIFTWOOD.get(), STRIPPED_DRIFTWOOD_LOG.get(), STRIPPED_DRIFTWOOD.get());
		this.tag(UABlockTags.RIVER_LOGS).add(RIVER_LOG.get(), RIVER_WOOD.get(), STRIPPED_RIVER_LOG.get(), STRIPPED_RIVER_WOOD.get());
		this.tag(UABlockTags.BEDROLLS).add(BEDROLL.get(), BLACK_BEDROLL.get(), BLUE_BEDROLL.get(), BROWN_BEDROLL.get(), CYAN_BEDROLL.get(), GRAY_BEDROLL.get(), GREEN_BEDROLL.get(), LIGHT_BLUE_BEDROLL.get(), LIGHT_GRAY_BEDROLL.get(), LIME_BEDROLL.get(), MAGENTA_BEDROLL.get(), ORANGE_BEDROLL.get(), PINK_BEDROLL.get(), PURPLE_BEDROLL.get(), RED_BEDROLL.get(), YELLOW_BEDROLL.get(), WHITE_BEDROLL.get());
		this.tag(UABlockTags.PIKE_SPAWNERS).add(PICKERELWEED.get(), TALL_PICKERELWEED.get());
		this.tag(UABlockTags.PICKERELWEED_PLACEABLE).addTag(BlockTags.DIRT).add(Blocks.CLAY, Blocks.FARMLAND);

		this.tag(BlueprintBlockTags.WOODEN_BOARDS).add(DRIFTWOOD_BOARDS.get(), RIVER_BOARDS.get());
		this.tag(BlueprintBlockTags.WOODEN_CHESTS).add(DRIFTWOOD_CHEST.get(), RIVER_CHEST.get());
		this.tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(TRAPPED_DRIFTWOOD_CHEST.get(), TRAPPED_RIVER_CHEST.get());
		this.tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(DRIFTWOOD_BEEHIVE.get(), RIVER_BEEHIVE.get());
		this.tag(BlueprintBlockTags.WOODEN_LADDERS).add(DRIFTWOOD_LADDER.get(), RIVER_LADDER.get());
		this.tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(DRIFTWOOD_BOOKSHELF.get(), RIVER_BOOKSHELF.get());
		this.tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES).add(CHISELED_DRIFTWOOD_BOOKSHELF.get(), CHISELED_RIVER_BOOKSHELF.get());
		this.tag(BlueprintBlockTags.LEAF_PILES).add(RIVER_LEAF_PILE.get());
	}
}
