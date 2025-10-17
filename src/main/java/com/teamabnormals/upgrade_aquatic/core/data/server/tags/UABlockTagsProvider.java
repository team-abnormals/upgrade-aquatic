package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.blueprint.core.other.tags.BlueprintBlockTags;
import com.teamabnormals.upgrade_aquatic.common.block.CoralType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.teamabnormals.upgrade_aquatic.core.other.tags.UABlockTags.*;
import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UABlockTagsProvider extends BlockTagsProvider {
	private Collection<DeferredHolder<Block, ? extends Block>> entries;

	public UABlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
		this.entries = BLOCKS.getDeferredRegister().getEntries();
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(MULBERRY_PUNNET.get());
		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(EMBEDDED_AMMONITE.get(), PRISMARINE_ROD_BUNDLE.get(), ELDER_EYE.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(RIVER_LEAVES.get());

		for (DeferredHolder<Block, ? extends Block> holder : BLOCKS.getDeferredRegister().getEntries()) {
			Block block = holder.get();
			String path = holder.getId().getPath();
			if (path.contains("luminous_prismarine") || (path.contains("dead") && path.contains("coral")) || path.contains("coral_block") || path.contains("tooth") || path.contains("scute") || path.contains("coralstone") || path.contains("kelpy_cobblestone") || path.contains("kelpy_stone")) {
				this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
			} else if (path.contains("beachgrass") || path.contains("pickerelweed_block") || path.contains("kelp_block")) {
				this.tag(BlockTags.MINEABLE_WITH_HOE).add(block);
			}
		}

		this.tag(BlockTags.STAIRS, is(StairBlock.class), BlockTags.WOODEN_STAIRS, DRIFTWOOD_STAIRS.get(), RIVER_STAIRS.get());
		this.tag(BlockTags.SLABS, is(SlabBlock.class), BlockTags.WOODEN_SLABS, DRIFTWOOD_SLAB.get(), RIVER_SLAB.get());
		this.tag(BlockTags.WALLS, is(WallBlock.class));
		this.tag(BlockTags.DOORS, is(DoorBlock.class), BlockTags.WOODEN_DOORS, DRIFTWOOD_DOOR.get(), RIVER_DOOR.get());
		this.tag(BlockTags.TRAPDOORS, is(TrapDoorBlock.class), BlockTags.WOODEN_TRAPDOORS, DRIFTWOOD_TRAPDOOR.get(), RIVER_TRAPDOOR.get());

		this.tag(BlockTags.SMALL_FLOWERS, is(FlowerBlock.class));
		this.tag(BlockTags.TALL_FLOWERS, is(TallFlowerBlock.class));
		this.tag(BlockTags.FLOWER_POTS, is(FlowerPotBlock.class));

		this.tag(BlockTags.PLANKS).add(DRIFTWOOD_PLANKS.get(), RIVER_PLANKS.get());
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(DRIFTWOOD_LOGS).addTag(RIVER_LOGS);
		this.tag(BlockTags.WOODEN_FENCES).add(DRIFTWOOD_FENCE.get(), RIVER_FENCE.get());
		this.tag(BlockTags.FENCE_GATES).add(DRIFTWOOD_FENCE_GATE.get(), RIVER_FENCE_GATE.get());
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(DRIFTWOOD_FENCE_GATE.get(), RIVER_FENCE_GATE.get());
		this.tag(BlockTags.WOODEN_BUTTONS).add(DRIFTWOOD_BUTTON.get(), RIVER_BUTTON.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(DRIFTWOOD_PRESSURE_PLATE.get(), RIVER_PRESSURE_PLATE.get());
		this.tag(BlockTags.STANDING_SIGNS).add(DRIFTWOOD_SIGNS.getFirst().get(), RIVER_SIGNS.getFirst().get());
		this.tag(BlockTags.WALL_SIGNS).add(DRIFTWOOD_SIGNS.getSecond().get(), RIVER_SIGNS.getSecond().get());
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(DRIFTWOOD_HANGING_SIGNS.getFirst().get(), RIVER_HANGING_SIGNS.getFirst().get());
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(DRIFTWOOD_HANGING_SIGNS.getSecond().get(), RIVER_HANGING_SIGNS.getSecond().get());
		this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(DRIFTWOOD_LOG.get(), RIVER_LOG.get());
		this.tag(BlockTags.LEAVES).add(RIVER_LEAVES.get());
		this.tag(BlockTags.SAPLINGS).add(RIVER_SAPLING.get());

		this.tag(Tags.Blocks.STRIPPED_LOGS).add(STRIPPED_DRIFTWOOD_LOG.get(), STRIPPED_RIVER_LOG.get());
		this.tag(Tags.Blocks.STRIPPED_WOODS).add(STRIPPED_DRIFTWOOD.get(), STRIPPED_RIVER_WOOD.get());

		this.tag(BlockTags.REPLACEABLE).add(BEACHGRASS.get(), TALL_BEACHGRASS.get());

		this.tag(DRIFTWOOD_LOGS).add(DRIFTWOOD_LOG.get(), DRIFTWOOD.get(), STRIPPED_DRIFTWOOD_LOG.get(), STRIPPED_DRIFTWOOD.get());
		this.tag(RIVER_LOGS).add(RIVER_LOG.get(), RIVER_WOOD.get(), STRIPPED_RIVER_LOG.get(), STRIPPED_RIVER_WOOD.get());
		this.tag(PIKE_SPAWNABLE_IN).add(PICKERELWEED.get(), TALL_PICKERELWEED.get());
		this.tag(PICKERELWEED_PLACEABLE).addTag(BlockTags.DIRT).add(Blocks.CLAY, Blocks.FARMLAND);

		this.tag(BlueprintBlockTags.WOODEN_BOARDS).add(DRIFTWOOD_BOARDS.get(), RIVER_BOARDS.get());
		this.tag(BlueprintBlockTags.WOODEN_CHESTS).add(DRIFTWOOD_CHEST.get(), RIVER_CHEST.get());
		this.tag(BlueprintBlockTags.WOODEN_TRAPPED_CHESTS).add(TRAPPED_DRIFTWOOD_CHEST.get(), TRAPPED_RIVER_CHEST.get());
		this.tag(BlueprintBlockTags.WOODEN_BEEHIVES).add(DRIFTWOOD_BEEHIVE.get(), RIVER_BEEHIVE.get());
		this.tag(BlueprintBlockTags.WOODEN_LADDERS).add(DRIFTWOOD_LADDER.get(), RIVER_LADDER.get());
		this.tag(BlueprintBlockTags.WOODEN_BOOKSHELVES).add(DRIFTWOOD_BOOKSHELF.get(), RIVER_BOOKSHELF.get());
		this.tag(BlueprintBlockTags.WOODEN_CHISELED_BOOKSHELVES).add(CHISELED_DRIFTWOOD_BOOKSHELF.get(), CHISELED_RIVER_BOOKSHELF.get());
		this.tag(BlueprintBlockTags.LEAF_PILES).add(RIVER_LEAF_PILE.get());

		this.tag(Tags.Blocks.COBBLESTONES_MOSSY).add(KELPY_COBBLESTONE.get());

		this.tag(Tags.Blocks.STORAGE_BLOCKS).add(PRISMARINE_ROD_BUNDLE.get(), PICKERELWEED_BLOCK.get(), MULBERRY_PUNNET.get()).addTag(STORAGE_BLOCKS_KELP);
		this.tag(STORAGE_BLOCKS_KELP).add(KELP_BLOCK.get());

		CoralType.values().stream().filter(coralType -> !coralType.vanilla() && coralType != CoralType.PRISMARINE).forEach(coralType -> {
			this.tag(BlockTags.CORAL_PLANTS).add(coralType.coral().get());
			this.tag(BlockTags.CORALS).add(coralType.fan().get());
			this.tag(BlockTags.WALL_CORALS).add(coralType.wallFan().get());
			this.tag(BlockTags.CORAL_BLOCKS).add(coralType.coralBlock().get());
		});
	}

	public static Function<Block, Boolean> is(Class<? extends Block> blockClass) {
		return blockClass::isInstance;
	}

	public void tag(TagKey<Block> tagKey, Function<Block, Boolean> condition) {
		this.tag(tagKey, condition, null);
	}

	public void tag(TagKey<Block> tagKey, Function<Block, Boolean> condition, TagKey<Block> altTagKey, Block... alts) {
		entries.forEach(holder -> {
			Block block = holder.get();
			IntrinsicTagAppender<Block> appender = this.tag(List.of(alts).contains(block) ? altTagKey : tagKey);
			if (condition.apply(block)) {
				appender.add(block);
			}
		});
	}
}
