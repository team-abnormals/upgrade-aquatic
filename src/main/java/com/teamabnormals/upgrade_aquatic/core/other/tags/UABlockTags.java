package com.teamabnormals.upgrade_aquatic.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class UABlockTags {
	public static final TagKey<Block> DRIFTWOOD_LOGS = blockTag("driftwood_logs");
	public static final TagKey<Block> RIVER_LOGS = blockTag("river_logs");

	public static final TagKey<Block> PIKE_SPAWNERS = blockTag("pike_spawners");
	public static final TagKey<Block> PICKERELWEED_PLACEABLE = blockTag("pickerelweed_placeable");

	public static final TagKey<Block> STORAGE_BLOCKS_PRISMARINE_ROD = TagUtil.blockTag("c", "storage_blocks/prismarine_rod");
	public static final TagKey<Block> STORAGE_BLOCKS_PICKERELWEED = TagUtil.blockTag("c", "storage_blocks/pickerelweed");
	public static final TagKey<Block> STORAGE_BLOCKS_KELP = TagUtil.blockTag("c", "storage_blocks/kelp");
	public static final TagKey<Block> STORAGE_BLOCKS_MULBERRY = TagUtil.blockTag("c", "storage_blocks/mulberry");

	private static TagKey<Block> blockTag(String name) {
		return TagUtil.blockTag(UpgradeAquatic.MOD_ID, name);
	}
}