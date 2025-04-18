package com.teamabnormals.upgrade_aquatic.core.other;

import net.minecraft.data.BlockFamily;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UABlockFamilies {
	public static final BlockFamily DRIFTWOOD_PLANKS_FAMILY = new BlockFamily.Builder(DRIFTWOOD_PLANKS.get()).button(DRIFTWOOD_BUTTON.get()).fence(DRIFTWOOD_FENCE.get()).fenceGate(DRIFTWOOD_FENCE_GATE.get()).pressurePlate(DRIFTWOOD_PRESSURE_PLATE.get()).sign(DRIFTWOOD_SIGNS.getFirst().get(), DRIFTWOOD_SIGNS.getSecond().get()).slab(DRIFTWOOD_SLAB.get()).stairs(DRIFTWOOD_STAIRS.get()).door(DRIFTWOOD_DOOR.get()).trapdoor(DRIFTWOOD_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
	public static final BlockFamily RIVER_PLANKS_FAMILY = new BlockFamily.Builder(RIVER_PLANKS.get()).button(RIVER_BUTTON.get()).fence(RIVER_FENCE.get()).fenceGate(RIVER_FENCE_GATE.get()).pressurePlate(RIVER_PRESSURE_PLATE.get()).sign(RIVER_SIGNS.getFirst().get(), RIVER_SIGNS.getSecond().get()).slab(RIVER_SLAB.get()).stairs(RIVER_STAIRS.get()).door(RIVER_DOOR.get()).trapdoor(RIVER_TRAPDOOR.get()).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();

	public static final BlockFamily SCUTE_SHINGLES_FAMILY = new BlockFamily.Builder(SCUTE_SHINGLES.get()).wall(SCUTE_SHINGLE_WALL.get()).stairs(SCUTE_SHINGLE_STAIRS.get()).slab(SCUTE_SHINGLE_SLAB.get()).chiseled(CHISELED_SCUTE_SHINGLES.get()).getFamily();
	public static final BlockFamily SCUTE_PAVEMENT_FAMILY = new BlockFamily.Builder(SCUTE_PAVEMENT.get()).wall(SCUTE_PAVEMENT_WALL.get()).stairs(SCUTE_PAVEMENT_STAIRS.get()).slab(SCUTE_PAVEMENT_SLAB.get()).getFamily();

	public static final BlockFamily TOOTH_BRICKS_FAMILY = new BlockFamily.Builder(TOOTH_BRICKS.get()).wall(TOOTH_BRICK_WALL.get()).stairs(TOOTH_BRICK_STAIRS.get()).slab(TOOTH_BRICK_SLAB.get()).chiseled(CHISELED_TOOTH_BRICKS.get()).getFamily();
	public static final BlockFamily TOOTH_TILES_FAMILY = new BlockFamily.Builder(TOOTH_TILES.get()).wall(TOOTH_TILE_WALL.get()).stairs(TOOTH_TILE_STAIRS.get()).slab(TOOTH_TILE_SLAB.get()).getFamily();

	public static final BlockFamily KELPY_COBBLESTONE_BRICKS_FAMILY = new BlockFamily.Builder(KELPY_COBBLESTONE_BRICKS.get()).wall(KELPY_COBBLESTONE_BRICK_WALL.get()).stairs(KELPY_COBBLESTONE_BRICK_STAIRS.get()).slab(KELPY_COBBLESTONE_BRICK_SLAB.get()).getFamily();
	public static final BlockFamily KELPY_COBBLESTONE_TILES_FAMILY = new BlockFamily.Builder(KELPY_COBBLESTONE_TILES.get()).wall(KELPY_COBBLESTONE_TILE_WALL.get()).stairs(KELPY_COBBLESTONE_TILE_STAIRS.get()).slab(KELPY_COBBLESTONE_TILE_SLAB.get()).getFamily();

	public static final BlockFamily CORALSTONE_FAMILY = new BlockFamily.Builder(CORALSTONE.get()).wall(CORALSTONE_WALL.get()).stairs(CORALSTONE_STAIRS.get()).slab(CORALSTONE_SLAB.get()).chiseled(CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily BUBBLE_CORALSTONE_FAMILY = new BlockFamily.Builder(BUBBLE_CORALSTONE.get()).wall(BUBBLE_CORALSTONE_WALL.get()).stairs(BUBBLE_CORALSTONE_STAIRS.get()).slab(BUBBLE_CORALSTONE_SLAB.get()).chiseled(BUBBLE_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily HORN_CORALSTONE_FAMILY = new BlockFamily.Builder(HORN_CORALSTONE.get()).wall(HORN_CORALSTONE_WALL.get()).stairs(HORN_CORALSTONE_STAIRS.get()).slab(HORN_CORALSTONE_SLAB.get()).chiseled(HORN_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily TUBE_CORALSTONE_FAMILY = new BlockFamily.Builder(TUBE_CORALSTONE.get()).wall(TUBE_CORALSTONE_WALL.get()).stairs(TUBE_CORALSTONE_STAIRS.get()).slab(TUBE_CORALSTONE_SLAB.get()).chiseled(TUBE_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily BRAIN_CORALSTONE_FAMILY = new BlockFamily.Builder(BRAIN_CORALSTONE.get()).wall(BRAIN_CORALSTONE_WALL.get()).stairs(BRAIN_CORALSTONE_STAIRS.get()).slab(BRAIN_CORALSTONE_SLAB.get()).chiseled(BRAIN_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily FIRE_CORALSTONE_FAMILY = new BlockFamily.Builder(FIRE_CORALSTONE.get()).wall(FIRE_CORALSTONE_WALL.get()).stairs(FIRE_CORALSTONE_STAIRS.get()).slab(FIRE_CORALSTONE_SLAB.get()).chiseled(FIRE_CHISELED_CORALSTONE.get()).getFamily();

	public static final BlockFamily ACAN_CORALSTONE_FAMILY = new BlockFamily.Builder(ACAN_CORALSTONE.get()).wall(ACAN_CORALSTONE_WALL.get()).stairs(ACAN_CORALSTONE_STAIRS.get()).slab(ACAN_CORALSTONE_SLAB.get()).chiseled(ACAN_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily FINGER_CORALSTONE_FAMILY = new BlockFamily.Builder(FINGER_CORALSTONE.get()).wall(FINGER_CORALSTONE_WALL.get()).stairs(FINGER_CORALSTONE_STAIRS.get()).slab(FINGER_CORALSTONE_SLAB.get()).chiseled(FINGER_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily STAR_CORALSTONE_FAMILY = new BlockFamily.Builder(STAR_CORALSTONE.get()).wall(STAR_CORALSTONE_WALL.get()).stairs(STAR_CORALSTONE_STAIRS.get()).slab(STAR_CORALSTONE_SLAB.get()).chiseled(STAR_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily MOSS_CORALSTONE_FAMILY = new BlockFamily.Builder(MOSS_CORALSTONE.get()).wall(MOSS_CORALSTONE_WALL.get()).stairs(MOSS_CORALSTONE_STAIRS.get()).slab(MOSS_CORALSTONE_SLAB.get()).chiseled(MOSS_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily PETAL_CORALSTONE_FAMILY = new BlockFamily.Builder(PETAL_CORALSTONE.get()).wall(PETAL_CORALSTONE_WALL.get()).stairs(PETAL_CORALSTONE_STAIRS.get()).slab(PETAL_CORALSTONE_SLAB.get()).chiseled(PETAL_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily BRANCH_CORALSTONE_FAMILY = new BlockFamily.Builder(BRANCH_CORALSTONE.get()).wall(BRANCH_CORALSTONE_WALL.get()).stairs(BRANCH_CORALSTONE_STAIRS.get()).slab(BRANCH_CORALSTONE_SLAB.get()).chiseled(BRANCH_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily ROCK_CORALSTONE_FAMILY = new BlockFamily.Builder(ROCK_CORALSTONE.get()).wall(ROCK_CORALSTONE_WALL.get()).stairs(ROCK_CORALSTONE_STAIRS.get()).slab(ROCK_CORALSTONE_SLAB.get()).chiseled(ROCK_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily PILLOW_CORALSTONE_FAMILY = new BlockFamily.Builder(PILLOW_CORALSTONE.get()).wall(PILLOW_CORALSTONE_WALL.get()).stairs(PILLOW_CORALSTONE_STAIRS.get()).slab(PILLOW_CORALSTONE_SLAB.get()).chiseled(PILLOW_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily SILK_CORALSTONE_FAMILY = new BlockFamily.Builder(SILK_CORALSTONE.get()).wall(SILK_CORALSTONE_WALL.get()).stairs(SILK_CORALSTONE_STAIRS.get()).slab(SILK_CORALSTONE_SLAB.get()).chiseled(SILK_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily CHROME_CORALSTONE_FAMILY = new BlockFamily.Builder(CHROME_CORALSTONE.get()).wall(CHROME_CORALSTONE_WALL.get()).stairs(CHROME_CORALSTONE_STAIRS.get()).slab(CHROME_CORALSTONE_SLAB.get()).chiseled(CHROME_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily PRISMARINE_CORALSTONE_FAMILY = new BlockFamily.Builder(PRISMARINE_CORALSTONE.get()).wall(PRISMARINE_CORALSTONE_WALL.get()).stairs(PRISMARINE_CORALSTONE_STAIRS.get()).slab(PRISMARINE_CORALSTONE_SLAB.get()).chiseled(PRISMARINE_CHISELED_CORALSTONE.get()).getFamily();
	public static final BlockFamily ELDER_PRISMARINE_CORALSTONE_FAMILY = new BlockFamily.Builder(ELDER_PRISMARINE_CORALSTONE.get()).wall(ELDER_PRISMARINE_CORALSTONE_WALL.get()).stairs(ELDER_PRISMARINE_CORALSTONE_STAIRS.get()).slab(ELDER_PRISMARINE_CORALSTONE_SLAB.get()).chiseled(CHISELED_ELDER_PRISMARINE_CORALSTONE.get()).getFamily();
	public static final BlockFamily DEAD_CORALSTONE_FAMILY = new BlockFamily.Builder(DEAD_CORALSTONE.get()).wall(DEAD_CORALSTONE_WALL.get()).stairs(DEAD_CORALSTONE_STAIRS.get()).slab(DEAD_CORALSTONE_SLAB.get()).chiseled(DEAD_CHISELED_CORALSTONE.get()).getFamily();

}
