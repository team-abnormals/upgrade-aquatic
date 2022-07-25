package com.teamabnormals.upgrade_aquatic.core.data.server.tags;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

//TODO: Add all the other block tags to this
public class UABlockTagsProvider extends BlockTagsProvider {

	public UABlockTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
		super(dataGenerator, UpgradeAquatic.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		var mineableWithAxe = this.tag(BlockTags.MINEABLE_WITH_AXE);
		var mineableWithPickaxe = this.tag(BlockTags.MINEABLE_WITH_PICKAXE);
		mineableWithPickaxe.add(EMBEDDED_AMMONITE.get(), PRISMARINE_ROD_BUNDLE.get(), ELDER_GUARDIAN_SPINE.get(), GUARDIAN_SPINE.get(), ELDER_EYE.get());
		var mineableWithHoe = this.tag(BlockTags.MINEABLE_WITH_HOE);
		mineableWithHoe.add(RIVER_LEAVES.get(), RIVER_LEAF_CARPET.get());
		var entries = HELPER.getDeferredRegister().getEntries();
		for (var object : entries) {
			String path = object.getId().getPath();
			if (path.contains("driftwood") || (path.contains("river") && !path.contains("river_sapling") && !path.equals("river_leaves") && !path.equals("river_leaf_carpet"))) {
				mineableWithAxe.add(object.get());
			} else if (path.contains("luminous_prismarine") || path.contains("luminous_elder_prismarine") || (path.contains("dead") && path.contains("coral")) || path.contains("tooth") || path.contains("scute") || path.contains("coralstone") || path.contains("kelpy_cobblestone") || path.contains("kelpy_stone")) {
				mineableWithPickaxe.add(object.get());
			} else if (path.contains("beachgrass") || path.contains("pickerelweed_block")) {
				mineableWithHoe.add(object.get());
			}
		}
	}

}
