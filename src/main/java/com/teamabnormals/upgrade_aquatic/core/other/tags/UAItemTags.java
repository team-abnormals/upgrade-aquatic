package com.teamabnormals.upgrade_aquatic.core.other.tags;

import com.teamabnormals.blueprint.core.util.TagUtil;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class UAItemTags {
	public static final TagKey<Item> DRIFTWOOD_LOGS = itemTag("driftwood_logs");
	public static final TagKey<Item> RIVER_LOGS = itemTag("river_logs");

	public static final TagKey<Item> BUBBLE_CORALS = itemTag("bubble_corals");
	public static final TagKey<Item> HORN_CORALS = itemTag("horn_corals");
	public static final TagKey<Item> TUBE_CORALS = itemTag("tube_corals");
	public static final TagKey<Item> BRAIN_CORALS = itemTag("brain_corals");
	public static final TagKey<Item> FIRE_CORALS = itemTag("fire_corals");
	public static final TagKey<Item> ACAN_CORALS = itemTag("acan_corals");
	public static final TagKey<Item> FINGER_CORALS = itemTag("finger_corals");
	public static final TagKey<Item> STAR_CORALS = itemTag("star_corals");
	public static final TagKey<Item> MOSS_CORALS = itemTag("moss_corals");
	public static final TagKey<Item> PETAL_CORALS = itemTag("petal_corals");
	public static final TagKey<Item> BRANCH_CORALS = itemTag("branch_corals");
	public static final TagKey<Item> ROCK_CORALS = itemTag("rock_corals");
	public static final TagKey<Item> PILLOW_CORALS = itemTag("pillow_corals");
	public static final TagKey<Item> SILK_CORALS = itemTag("silk_corals");
	public static final TagKey<Item> CHROME_CORALS = itemTag("chrome_corals");
	public static final TagKey<Item> PRISMARINE_CORALS = itemTag("prismarine_corals");

	public static final TagKey<Item> DEAD_BUBBLE_CORALS = itemTag("dead_bubble_corals");
	public static final TagKey<Item> DEAD_HORN_CORALS = itemTag("dead_horn_corals");
	public static final TagKey<Item> DEAD_TUBE_CORALS = itemTag("dead_tube_corals");
	public static final TagKey<Item> DEAD_BRAIN_CORALS = itemTag("dead_brain_corals");
	public static final TagKey<Item> DEAD_FIRE_CORALS = itemTag("dead_fire_corals");
	public static final TagKey<Item> DEAD_ACAN_CORALS = itemTag("dead_acan_corals");
	public static final TagKey<Item> DEAD_FINGER_CORALS = itemTag("dead_finger_corals");
	public static final TagKey<Item> DEAD_STAR_CORALS = itemTag("dead_star_corals");
	public static final TagKey<Item> DEAD_MOSS_CORALS = itemTag("dead_moss_corals");
	public static final TagKey<Item> DEAD_PETAL_CORALS = itemTag("dead_petal_corals");
	public static final TagKey<Item> DEAD_BRANCH_CORALS = itemTag("dead_branch_corals");
	public static final TagKey<Item> DEAD_ROCK_CORALS = itemTag("dead_rock_corals");
	public static final TagKey<Item> DEAD_PILLOW_CORALS = itemTag("dead_pillow_corals");
	public static final TagKey<Item> DEAD_SILK_CORALS = itemTag("dead_silk_corals");
	public static final TagKey<Item> DEAD_CHROME_CORALS = itemTag("dead_chrome_corals");
	public static final TagKey<Item> ELDER_PRISMARINE_CORALS = itemTag("elder_prismarine_corals");

	private static TagKey<Item> itemTag(String name) {
		return TagUtil.itemTag(UpgradeAquatic.MOD_ID, name);
	}
}