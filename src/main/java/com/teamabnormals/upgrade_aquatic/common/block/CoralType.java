package com.teamabnormals.upgrade_aquatic.common.block;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.upgrade_aquatic.core.other.UABlockFamilies;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAItemTags;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public record CoralType(Supplier<Block> coralBlock,
						TagKey<Item> itemTag,
						TagKey<Item> deadItemTag,
						Supplier<Block> coral,
						Supplier<Block> fan,
						Supplier<Block> wallFan,
						Supplier<Block> deadCoralBlock,
						Supplier<Block> deadCoral,
						Supplier<Block> deadFan,
						Supplier<Block> deadWallFan,
						BlockFamily coralstoneFamily, boolean vanilla) {

	public CoralType(Supplier<Block> coralBlock, TagKey<Item> itemTag, TagKey<Item> deadItemTag, Supplier<Block> coral, Supplier<Block> fan, Supplier<Block> wallFan, Supplier<Block> deadCoralBlock, Supplier<Block> deadCoral, Supplier<Block> deadFan, Supplier<Block> deadWallFan, BlockFamily coralstoneFamily) {
		this(coralBlock, itemTag, deadItemTag, coral, fan, wallFan, deadCoralBlock, deadCoral, deadFan, deadWallFan, coralstoneFamily, false);
	}

	public static final CoralType BUBBLE = new CoralType(() -> Blocks.BUBBLE_CORAL_BLOCK, UAItemTags.BUBBLE_CORALS, UAItemTags.DEAD_BUBBLE_CORALS, () -> Blocks.BUBBLE_CORAL, () -> Blocks.BUBBLE_CORAL_FAN, () -> Blocks.BUBBLE_CORAL_WALL_FAN, () -> Blocks.DEAD_BUBBLE_CORAL_BLOCK, () -> Blocks.DEAD_BUBBLE_CORAL, () -> Blocks.DEAD_BUBBLE_CORAL_FAN, () -> Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, UABlockFamilies.BUBBLE_CORALSTONE_FAMILY, true);
	public static final CoralType HORN = new CoralType(() -> Blocks.HORN_CORAL_BLOCK, UAItemTags.HORN_CORALS, UAItemTags.DEAD_HORN_CORALS, () -> Blocks.HORN_CORAL, () -> Blocks.HORN_CORAL_FAN, () -> Blocks.HORN_CORAL_WALL_FAN, () -> Blocks.DEAD_HORN_CORAL_BLOCK, () -> Blocks.DEAD_HORN_CORAL, () -> Blocks.DEAD_HORN_CORAL_FAN, () -> Blocks.DEAD_HORN_CORAL_WALL_FAN, UABlockFamilies.HORN_CORALSTONE_FAMILY, true);
	public static final CoralType TUBE = new CoralType(() -> Blocks.TUBE_CORAL_BLOCK, UAItemTags.TUBE_CORALS, UAItemTags.DEAD_TUBE_CORALS, () -> Blocks.TUBE_CORAL, () -> Blocks.TUBE_CORAL_FAN, () -> Blocks.TUBE_CORAL_WALL_FAN, () -> Blocks.DEAD_TUBE_CORAL_BLOCK, () -> Blocks.DEAD_TUBE_CORAL, () -> Blocks.DEAD_TUBE_CORAL_FAN, () -> Blocks.DEAD_TUBE_CORAL_WALL_FAN, UABlockFamilies.TUBE_CORALSTONE_FAMILY, true);
	public static final CoralType BRAIN = new CoralType(() -> Blocks.BRAIN_CORAL_BLOCK, UAItemTags.BRAIN_CORALS, UAItemTags.DEAD_BRAIN_CORALS, () -> Blocks.BRAIN_CORAL, () -> Blocks.BRAIN_CORAL_FAN, () -> Blocks.BRAIN_CORAL_WALL_FAN, () -> Blocks.DEAD_BRAIN_CORAL_BLOCK, () -> Blocks.DEAD_BRAIN_CORAL, () -> Blocks.DEAD_BRAIN_CORAL_FAN, () -> Blocks.DEAD_BRAIN_CORAL_WALL_FAN, UABlockFamilies.BRAIN_CORALSTONE_FAMILY, true);
	public static final CoralType FIRE = new CoralType(() -> Blocks.FIRE_CORAL_BLOCK, UAItemTags.FIRE_CORALS, UAItemTags.DEAD_FIRE_CORALS, () -> Blocks.FIRE_CORAL, () -> Blocks.FIRE_CORAL_FAN, () -> Blocks.FIRE_CORAL_WALL_FAN, () -> Blocks.DEAD_FIRE_CORAL_BLOCK, () -> Blocks.DEAD_FIRE_CORAL, () -> Blocks.DEAD_FIRE_CORAL_FAN, () -> Blocks.DEAD_FIRE_CORAL_WALL_FAN, UABlockFamilies.FIRE_CORALSTONE_FAMILY, true);

	public static final CoralType ACAN = new CoralType(ACAN_CORAL_BLOCK, UAItemTags.ACAN_CORALS, UAItemTags.DEAD_ACAN_CORALS, ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN, DEAD_ACAN_CORAL_BLOCK, DEAD_ACAN_CORAL, DEAD_ACAN_CORAL_FAN, DEAD_ACAN_CORAL_WALL_FAN, UABlockFamilies.ACAN_CORALSTONE_FAMILY);
	public static final CoralType FINGER = new CoralType(FINGER_CORAL_BLOCK, UAItemTags.FINGER_CORALS, UAItemTags.DEAD_FINGER_CORALS, FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN, DEAD_FINGER_CORAL_BLOCK, DEAD_FINGER_CORAL, DEAD_FINGER_CORAL_FAN, DEAD_FINGER_CORAL_WALL_FAN, UABlockFamilies.FINGER_CORALSTONE_FAMILY);
	public static final CoralType STAR = new CoralType(STAR_CORAL_BLOCK, UAItemTags.STAR_CORALS, UAItemTags.DEAD_STAR_CORALS, STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN, DEAD_STAR_CORAL_BLOCK, DEAD_STAR_CORAL, DEAD_STAR_CORAL_FAN, DEAD_STAR_CORAL_WALL_FAN, UABlockFamilies.STAR_CORALSTONE_FAMILY);
	public static final CoralType MOSS = new CoralType(MOSS_CORAL_BLOCK, UAItemTags.MOSS_CORALS, UAItemTags.DEAD_MOSS_CORALS, MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN, DEAD_MOSS_CORAL_BLOCK, DEAD_MOSS_CORAL, DEAD_MOSS_CORAL_FAN, DEAD_MOSS_CORAL_WALL_FAN, UABlockFamilies.MOSS_CORALSTONE_FAMILY);
	public static final CoralType PETAL = new CoralType(PETAL_CORAL_BLOCK, UAItemTags.PETAL_CORALS, UAItemTags.DEAD_PETAL_CORALS, PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN, DEAD_PETAL_CORAL_BLOCK, DEAD_PETAL_CORAL, DEAD_PETAL_CORAL_FAN, DEAD_PETAL_CORAL_WALL_FAN, UABlockFamilies.PETAL_CORALSTONE_FAMILY);
	public static final CoralType BRANCH = new CoralType(BRANCH_CORAL_BLOCK, UAItemTags.BRANCH_CORALS, UAItemTags.DEAD_BRANCH_CORALS, BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN, DEAD_BRANCH_CORAL_BLOCK, DEAD_BRANCH_CORAL, DEAD_BRANCH_CORAL_FAN, DEAD_BRANCH_CORAL_WALL_FAN, UABlockFamilies.BRANCH_CORALSTONE_FAMILY);
	public static final CoralType ROCK = new CoralType(ROCK_CORAL_BLOCK, UAItemTags.ROCK_CORALS, UAItemTags.DEAD_ROCK_CORALS, ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN, DEAD_ROCK_CORAL_BLOCK, DEAD_ROCK_CORAL, DEAD_ROCK_CORAL_FAN, DEAD_ROCK_CORAL_WALL_FAN, UABlockFamilies.ROCK_CORALSTONE_FAMILY);
	public static final CoralType PILLOW = new CoralType(PILLOW_CORAL_BLOCK, UAItemTags.PILLOW_CORALS, UAItemTags.DEAD_PILLOW_CORALS, PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN, DEAD_PILLOW_CORAL_BLOCK, DEAD_PILLOW_CORAL, DEAD_PILLOW_CORAL_FAN, DEAD_PILLOW_CORAL_WALL_FAN, UABlockFamilies.PILLOW_CORALSTONE_FAMILY);
	public static final CoralType SILK = new CoralType(SILK_CORAL_BLOCK, UAItemTags.SILK_CORALS, UAItemTags.DEAD_SILK_CORALS, SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN, DEAD_SILK_CORAL_BLOCK, DEAD_SILK_CORAL, DEAD_SILK_CORAL_FAN, DEAD_SILK_CORAL_WALL_FAN, UABlockFamilies.SILK_CORALSTONE_FAMILY);
	public static final CoralType CHROME = new CoralType(CHROME_CORAL_BLOCK, UAItemTags.CHROME_CORALS, UAItemTags.DEAD_CHROME_CORALS, CHROME_CORAL, CHROME_CORAL_FAN, CHROME_CORAL_WALL_FAN, DEAD_CHROME_CORAL_BLOCK, DEAD_CHROME_CORAL, DEAD_CHROME_CORAL_FAN, DEAD_CHROME_CORAL_WALL_FAN, UABlockFamilies.CHROME_CORALSTONE_FAMILY);
	public static final CoralType PRISMARINE = new CoralType(PRISMARINE_CORAL_BLOCK, UAItemTags.PRISMARINE_CORALS, UAItemTags.ELDER_PRISMARINE_CORALS, PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, ELDER_PRISMARINE_CORAL_BLOCK, ELDER_PRISMARINE_CORAL, ELDER_PRISMARINE_CORAL_FAN, ELDER_PRISMARINE_CORAL_WALL_FAN, UABlockFamilies.PRISMARINE_CORALSTONE_FAMILY);

	public static ImmutableList<CoralType> values() {
		return ImmutableList.of(
				BUBBLE, HORN, TUBE, BRAIN, FIRE,
				ACAN, FINGER, STAR, MOSS, PETAL, BRANCH, ROCK, PILLOW, SILK, CHROME,
				PRISMARINE
		);
	}
}
