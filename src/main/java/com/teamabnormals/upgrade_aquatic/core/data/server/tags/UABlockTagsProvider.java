package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

//TODO: Add all the other block tags to this
public class UABlockTagsProvider extends BlockTagsProvider {

	public UABlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(output, provider, UpgradeAquatic.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		IntrinsicTagAppender<Block> mineableWithAxe = this.tag(BlockTags.MINEABLE_WITH_AXE);
		IntrinsicTagAppender<Block> mineableWithPickaxe = this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
		IntrinsicTagAppender<Block> mineableWithHoe = this.tag(BlockTags.MINEABLE_WITH_HOE);

		mineableWithAxe.add(MULBERRY_PUNNET.get());
		mineableWithAxe.add(RIVER_BOARDS.get(), RIVER_BOOKSHELF.get(), RIVER_LADDER.get(), RIVER_BEEHIVE.get(), RIVER_CHEST.get(), TRAPPED_RIVER_CHEST.get());
		mineableWithAxe.add(DRIFTWOOD_BOARDS.get(), DRIFTWOOD_BOOKSHELF.get(), DRIFTWOOD_LADDER.get(), DRIFTWOOD_BEEHIVE.get(), DRIFTWOOD_CHEST.get(), TRAPPED_DRIFTWOOD_CHEST.get());
		mineableWithPickaxe.add(EMBEDDED_AMMONITE.get(), PRISMARINE_ROD_BUNDLE.get(), ELDER_GUARDIAN_SPINE.get(), GUARDIAN_SPINE.get(), ELDER_EYE.get());
		mineableWithHoe.add(RIVER_LEAVES.get(), RIVER_LEAF_PILE.get());

		for (RegistryObject<Block> block : HELPER.getDeferredRegister().getEntries()) {
			String path = block.getId().getPath();
			if (path.contains("luminous_prismarine") || (path.contains("dead") && path.contains("coral")) || path.contains("coral_block") || path.contains("tooth") || path.contains("scute") || path.contains("coralstone") || path.contains("kelpy_cobblestone") || path.contains("kelpy_stone")) {
				mineableWithPickaxe.add(block.get());
			} else if (path.contains("beachgrass") || path.contains("pickerelweed_block") || path.contains("kelp_block")) {
				mineableWithHoe.add(block.get());
			}
		}
	}

}
