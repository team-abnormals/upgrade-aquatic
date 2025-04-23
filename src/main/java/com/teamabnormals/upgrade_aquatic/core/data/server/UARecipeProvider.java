package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.boatload.core.data.server.BoatloadRecipeProvider;
import com.teamabnormals.upgrade_aquatic.common.block.CoralType;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.UAConstants;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAItemTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.integration.boatload.UABoatTypes;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.upgrade_aquatic.core.other.UABlockFamilies.*;
import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;
import static net.minecraft.data.recipes.RecipeCategory.*;

public class UARecipeProvider extends BlueprintRecipeProvider {

	public UARecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(UpgradeAquatic.MOD_ID, output, provider);
	}

	@Override
	public void buildRecipes(RecipeOutput output) {
		conversionRecipe(output, Items.PINK_DYE, PINK_SEAROCKET, "pink_dye");
		conversionRecipe(output, Items.WHITE_DYE, WHITE_SEAROCKET, "white_dye");
		conversionRecipe(output, Items.CYAN_DYE, PICKERELWEED, "cyan_dye");
		conversionRecipe(output, Items.PINK_DYE, FLOWERING_RUSH, "pink_dye", 2);
		conversionRecipe(output, Items.MAGENTA_DYE, UAItems.MULBERRY, "magenta_dye");

		foodCookingRecipes(output, UAItems.PIKE, UAItems.COOKED_PIKE);
		foodCookingRecipes(output, UAItems.PERCH, UAItems.COOKED_PERCH);
		foodCookingRecipes(output, UAItems.LIONFISH, UAItems.COOKED_LIONFISH);
		foodCookingRecipes(output, PICKERELWEED, UAItems.BOILED_PICKERELWEED);

		ShapedRecipeBuilder.shaped(DECORATIONS, BEDROLL).define('#', Items.LEATHER).define('X', Items.WHITE_WOOL).pattern("##X").pattern("###").group("bedroll").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(output);

		oreRecipes(output, List.of(EMBEDDED_AMMONITE), MISC, Items.NAUTILUS_SHELL, 1.0F, 200, "nautilus_shell");

		storageRecipes(output, MISC, Items.TURTLE_SCUTE, BUILDING_BLOCKS, SCUTE_BLOCK);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, SCUTE_SHINGLES, 8).define('#', Blocks.STONE_BRICKS).define('S', Items.TURTLE_SCUTE).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_scute", has(Items.TURTLE_SCUTE)).save(output);
		generateRecipes(output, SCUTE_SHINGLES_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_SHINGLE_SLAB, SCUTE_SHINGLES, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_SHINGLE_STAIRS, SCUTE_SHINGLES);
		stonecutterRecipe(output, DECORATIONS, SCUTE_SHINGLE_WALL, SCUTE_SHINGLES);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_SCUTE_SHINGLES, SCUTE_SHINGLES);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, SCUTE_PAVEMENT, 4).define('#', SCUTE_SHINGLES).pattern("##").pattern("##").unlockedBy("has_scute_shingles", has(SCUTE_SHINGLES)).save(output);
		generateRecipes(output, SCUTE_PAVEMENT_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_PAVEMENT_SLAB, SCUTE_PAVEMENT, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_PAVEMENT_STAIRS, SCUTE_PAVEMENT);
		stonecutterRecipe(output, DECORATIONS, SCUTE_PAVEMENT_WALL, SCUTE_PAVEMENT);
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_PAVEMENT, SCUTE_SHINGLES);
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_PAVEMENT_SLAB, SCUTE_SHINGLES, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_PAVEMENT_STAIRS, SCUTE_SHINGLES);
		stonecutterRecipe(output, BUILDING_BLOCKS, SCUTE_PAVEMENT_WALL, SCUTE_SHINGLES);

		storageRecipes(output, MISC, UAItems.THRASHER_TOOTH, BUILDING_BLOCKS, TOOTH_BLOCK);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, TOOTH_BRICKS, 8).define('#', Blocks.STONE_BRICKS).define('S', UAItems.THRASHER_TOOTH).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_thrasher_tooth", has(UAItems.THRASHER_TOOTH)).save(output);
		generateRecipes(output, TOOTH_BRICKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_BRICK_SLAB, TOOTH_BRICKS, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_BRICK_STAIRS, TOOTH_BRICKS);
		stonecutterRecipe(output, DECORATIONS, TOOTH_BRICK_WALL, TOOTH_BRICKS);
		stonecutterRecipe(output, BUILDING_BLOCKS, CHISELED_TOOTH_BRICKS, TOOTH_BRICKS);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, TOOTH_TILES, 4).define('#', TOOTH_BRICKS).pattern("##").pattern("##").unlockedBy("has_tooth_bricks", has(TOOTH_BRICKS)).save(output);
		generateRecipes(output, TOOTH_TILES_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_TILE_SLAB, TOOTH_TILES, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_TILE_STAIRS, TOOTH_TILES);
		stonecutterRecipe(output, DECORATIONS, TOOTH_TILE_WALL, TOOTH_TILES);
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_TILES, TOOTH_BRICKS);
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_TILE_SLAB, TOOTH_BRICKS, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_TILE_STAIRS, TOOTH_BRICKS);
		stonecutterRecipe(output, BUILDING_BLOCKS, TOOTH_TILE_WALL, TOOTH_BRICKS);

		generateRecipes(output, DRIFTWOOD_PLANKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		planksFromLogs(output, DRIFTWOOD_PLANKS, UAItemTags.DRIFTWOOD_LOGS, 4);
		woodFromLogs(output, DRIFTWOOD, DRIFTWOOD_LOG);
		woodFromLogs(output, STRIPPED_DRIFTWOOD, STRIPPED_DRIFTWOOD_LOG);
		hangingSign(output, DRIFTWOOD_HANGING_SIGNS.getFirst(), STRIPPED_DRIFTWOOD_LOG);

		BoatloadRecipeProvider.boatRecipes(output, UABoatTypes.DRIFTWOOD);
		WoodworksRecipeProvider.baseRecipes(output, DRIFTWOOD_PLANKS.get(), DRIFTWOOD_SLAB.get(), DRIFTWOOD_BOARDS.get(), DRIFTWOOD_BOOKSHELF.get(), CHISELED_DRIFTWOOD_BOOKSHELF.get(), DRIFTWOOD_LADDER.get(), DRIFTWOOD_BEEHIVE.get(), DRIFTWOOD_CHEST.get(), TRAPPED_DRIFTWOOD_CHEST.get(), UpgradeAquatic.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, DRIFTWOOD_PLANKS_FAMILY, UAItemTags.DRIFTWOOD_LOGS, DRIFTWOOD_BOARDS.get(), DRIFTWOOD_LADDER.get(), UpgradeAquatic.MOD_ID);

		generateRecipes(output, RIVER_PLANKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		planksFromLogs(output, RIVER_PLANKS, UAItemTags.RIVER_LOGS, 4);
		woodFromLogs(output, RIVER_WOOD, RIVER_LOG);
		woodFromLogs(output, STRIPPED_RIVER_WOOD, STRIPPED_RIVER_LOG);
		hangingSign(output, RIVER_HANGING_SIGNS.getFirst(), STRIPPED_RIVER_LOG);

		BoatloadRecipeProvider.boatRecipes(output, UABoatTypes.RIVER);
		WoodworksRecipeProvider.baseRecipes(output, RIVER_PLANKS.get(), RIVER_SLAB.get(), RIVER_BOARDS.get(), RIVER_BOOKSHELF.get(), CHISELED_RIVER_BOOKSHELF.get(), RIVER_LADDER.get(), RIVER_BEEHIVE.get(), RIVER_CHEST.get(), TRAPPED_RIVER_CHEST.get(), UpgradeAquatic.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(output, RIVER_PLANKS_FAMILY, UAItemTags.RIVER_LOGS, RIVER_BOARDS.get(), RIVER_LADDER.get(), UpgradeAquatic.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(output, RIVER_LEAVES, RIVER_LEAF_PILE, UpgradeAquatic.MOD_ID);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, CORALSTONE, 8).define('#', Blocks.STONE_BRICKS).define('S', Items.NAUTILUS_SHELL).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_nautilus_shell", has(Items.NAUTILUS_SHELL)).save(output);
		generateRecipes(output, CORALSTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, CORALSTONE_SLAB, CORALSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, CORALSTONE_STAIRS, CORALSTONE);
		stonecutterRecipe(output, DECORATIONS, CORALSTONE_WALL, CORALSTONE);

		generateRecipes(output, DEAD_CORALSTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, DEAD_CORALSTONE_SLAB, DEAD_CORALSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, DEAD_CORALSTONE_STAIRS, DEAD_CORALSTONE);
		stonecutterRecipe(output, DECORATIONS, DEAD_CORALSTONE_WALL, DEAD_CORALSTONE);

		generateRecipes(output, ELDER_PRISMARINE_CORALSTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, ELDER_PRISMARINE_CORALSTONE_SLAB, ELDER_PRISMARINE_CORALSTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, ELDER_PRISMARINE_CORALSTONE_STAIRS, ELDER_PRISMARINE_CORALSTONE);
		stonecutterRecipe(output, DECORATIONS, ELDER_PRISMARINE_CORALSTONE_WALL, ELDER_PRISMARINE_CORALSTONE);

		ShapedRecipeBuilder.shaped(MISC, UAItems.PRISMARINE_ROD).define('#', Items.PRISMARINE_SHARD).pattern("#").pattern("#").unlockedBy("has_prismarine_shard", has(Items.PRISMARINE_SHARD)).save(output);
		ShapedRecipeBuilder.shaped(COMBAT, Items.TRIDENT).define('#', UAItems.PRISMARINE_ROD).define('X', UAItems.THRASHER_TOOTH).pattern(" XX").pattern(" #X").pattern("#  ").unlockedBy("has_prismarine_rod", has(UAItems.PRISMARINE_ROD)).unlockedBy("has_thrasher_tooth", has(UAItems.THRASHER_TOOTH)).save(output, UpgradeAquatic.location("trident"));
		ShapedRecipeBuilder.shaped(DECORATIONS, TOOTH_LANTERN).define('#', UAItems.THRASHER_TOOTH).define('X', Items.PRISMARINE_CRYSTALS).pattern("#").pattern("X").pattern("#").unlockedBy("has_thrasher_tooth", has(UAItems.THRASHER_TOOTH)).save(output);

		doorBuilder(TOOTH_DOOR, Ingredient.of(UAItems.THRASHER_TOOTH)).unlockedBy(getHasName(UAItems.THRASHER_TOOTH), has(UAItems.THRASHER_TOOTH)).save(output);
		twoByTwoPacker(output, REDSTONE, TOOTH_TRAPDOOR, UAItems.THRASHER_TOOTH);

		ShapelessRecipeBuilder.shapeless(FOOD, UAItems.MULBERRY_JAM_BOTTLE, 4).requires(MULBERRY_JAM_BLOCK).requires(Items.GLASS_BOTTLE, 4).unlockedBy("has_mulberry_jam_block", has(MULBERRY_JAM_BLOCK)).save(output);
		twoByTwoPacker(output, REDSTONE, MULBERRY_JAM_BLOCK, UAItems.MULBERRY_JAM_BOTTLE);
		ShapelessRecipeBuilder.shapeless(FOOD, UAItems.MULBERRY_BREAD).requires(Items.BREAD).requires(UAItems.MULBERRY_JAM_BOTTLE).unlockedBy("has_mulberry_jam_bottle", has(UAItems.MULBERRY_JAM_BOTTLE)).save(output);
		ShapelessRecipeBuilder.shapeless(FOOD, UAItems.MULBERRY_PIE).requires(UAItems.MULBERRY).requires(Items.SUGAR).requires(Tags.Items.EGGS).unlockedBy("has_mulberry", has(UAItems.MULBERRY)).save(output);
		ShapelessRecipeBuilder.shapeless(FOOD, UAItems.MULBERRY_JAM_BOTTLE).requires(UAItems.MULBERRY).requires(Items.SUGAR).requires(Items.GLASS_BOTTLE).unlockedBy("has_mulberry", has(UAItems.MULBERRY)).save(output, getModConversionRecipeName(UAItems.MULBERRY, Items.GLASS_BOTTLE));

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, Blocks.DARK_PRISMARINE).define('S', Items.PRISMARINE_SHARD).define('I', Items.INK_SAC).pattern("SSS").pattern("SIS").pattern("SSS").unlockedBy("has_prismarine_shard", has(Items.PRISMARINE_SHARD)).save(output);
		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, LUMINOUS_PRISMARINE).define('S', Items.PRISMARINE_SHARD).define('I', Items.GLOW_INK_SAC).pattern("SSS").pattern("SIS").pattern("SSS").unlockedBy("has_prismarine_shard", has(Items.PRISMARINE_SHARD)).save(output);

		generateRecipes(output, LUMINOUS_PRISMARINE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, LUMINOUS_PRISMARINE_SLAB, LUMINOUS_PRISMARINE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, LUMINOUS_PRISMARINE_STAIRS, LUMINOUS_PRISMARINE);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, BEACHGRASS_THATCH, 4).define('#', BEACHGRASS).pattern("##").pattern("##").unlockedBy("has_beachgrass", has(BEACHGRASS)).save(output);
		generateRecipes(output, BEACHGRASS_THATCH_FAMILY, FeatureFlags.REGISTRY.allFlags());

		conversionRecipe(output, Items.BONE_MEAL, UAItems.THRASHER_TOOTH, "bone_meal", 6);
		threeByThreePacker(output, MISC, UAItems.MUSIC_DISC_ATLANTIS, UAItems.DISC_FRAGMENT_ATLANTIS);

		CoralType.values().forEach(coralType -> {
			BlockFamily family = coralType.coralstoneFamily();
			coralBlockRecipe(output, coralType.coralBlock().get(), coralType.coral().get(), coralType.itemTag());
			coralBlockRecipe(output, coralType.deadCoralBlock().get(), coralType.deadCoral().get(), coralType.deadItemTag());
			generateRecipes(output, coralType.coralstoneFamily(), FeatureFlags.REGISTRY.allFlags());
			stonecutterRecipe(output, BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
			stonecutterRecipe(output, BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
			stonecutterRecipe(output, DECORATIONS, family.get(Variant.WALL), family.getBaseBlock());
		});

		storageRecipes(output, MISC, Items.KELP, DECORATIONS, KELP_BLOCK);
		storageRecipes(output, MISC, PICKERELWEED, DECORATIONS, PICKERELWEED_BLOCK);
		conditionalStorageRecipes(output, new ModLoadedCondition("berry_good"), MISC, UAItems.MULBERRY, DECORATIONS, MULBERRY_PUNNET);
		storageRecipesWithCustomUnpacking(output, FOOD, UAItems.BOILED_PICKERELWEED, DECORATIONS, BOILED_PICKERELWEED_BLOCK, "boiled_pickerelweed_from_boiled_pickerelweed_block", null);
		storageRecipesWithCustomUnpacking(output, MISC, UAItems.PRISMARINE_ROD, DECORATIONS, PRISMARINE_ROD_BUNDLE, "prismarine_rod_from_prismarine_rod_bundle", null);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, KELPY_COBBLESTONE).requires(Blocks.COBBLESTONE).requires(Items.KELP).group("kelpy_cobblestone").unlockedBy("has_kelp", has(Items.KELP)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, KELPY_STONE_BRICKS).requires(Blocks.STONE_BRICKS).requires(Items.KELP).group("kelpy_stone_bricks").unlockedBy("has_kelp", has(Items.KELP)).save(output);

		generateRecipes(output, KELPY_COBBLESTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, KELPY_COBBLESTONE_SLAB, KELPY_COBBLESTONE, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, KELPY_COBBLESTONE_STAIRS, KELPY_COBBLESTONE);
		stonecutterRecipe(output, DECORATIONS, KELPY_COBBLESTONE_WALL, KELPY_COBBLESTONE);

		generateRecipes(output, KELPY_STONE_BRICKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(output, BUILDING_BLOCKS, KELPY_STONE_BRICK_SLAB, KELPY_STONE_BRICKS, 2);
		stonecutterRecipe(output, BUILDING_BLOCKS, KELPY_STONE_BRICK_STAIRS, KELPY_STONE_BRICKS);
		stonecutterRecipe(output, DECORATIONS, KELPY_STONE_BRICK_WALL, KELPY_STONE_BRICKS);

		ModLoadedCondition cnc = new ModLoadedCondition("caverns_and_chasms");

		conditionalRecipe(output, ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICKS, 4).define('#', KELPY_COBBLESTONE).pattern("##").pattern("##").unlockedBy(getHasName(KELPY_COBBLESTONE), has(KELPY_COBBLESTONE)), cnc);
		conditionalRecipe(output, stairBuilder(KELPY_COBBLESTONE_BRICK_STAIRS, Ingredient.of(KELPY_COBBLESTONE_BRICKS)).unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS), has(KELPY_COBBLESTONE_BRICKS)), cnc);
		conditionalRecipe(output, slabBuilder(RecipeCategory.BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_SLAB, Ingredient.of(KELPY_COBBLESTONE_BRICKS)).unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS), has(KELPY_COBBLESTONE_BRICKS)), cnc);
		conditionalRecipe(output, wallBuilder(RecipeCategory.DECORATIONS, KELPY_COBBLESTONE_BRICK_WALL, Ingredient.of(KELPY_COBBLESTONE_BRICKS)).unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS), has(KELPY_COBBLESTONE_BRICKS)), cnc);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_SLAB, KELPY_COBBLESTONE_BRICKS, 2);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_STAIRS, KELPY_COBBLESTONE_BRICKS);
		conditionalStonecutterRecipe(output, cnc, DECORATIONS, KELPY_COBBLESTONE_BRICK_WALL, KELPY_COBBLESTONE_BRICKS);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICKS, KELPY_COBBLESTONE);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_SLAB, KELPY_COBBLESTONE, 2);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_STAIRS, KELPY_COBBLESTONE);
		conditionalStonecutterRecipe(output, cnc, DECORATIONS, KELPY_COBBLESTONE_BRICK_WALL, KELPY_COBBLESTONE);

		conditionalRecipe(output, ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES, 4).define('#', KELPY_COBBLESTONE_BRICKS).pattern("##").pattern("##").unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS), has(KELPY_COBBLESTONE_BRICKS)), cnc);
		conditionalRecipe(output, stairBuilder(KELPY_COBBLESTONE_TILE_STAIRS, Ingredient.of(KELPY_COBBLESTONE_TILES)).unlockedBy(getHasName(KELPY_COBBLESTONE_TILES), has(KELPY_COBBLESTONE_TILES)), cnc);
		conditionalRecipe(output, slabBuilder(RecipeCategory.BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB, Ingredient.of(KELPY_COBBLESTONE_TILES)).unlockedBy(getHasName(KELPY_COBBLESTONE_TILES), has(KELPY_COBBLESTONE_TILES)), cnc);
		conditionalRecipe(output, wallBuilder(RecipeCategory.DECORATIONS, KELPY_COBBLESTONE_TILE_WALL, Ingredient.of(KELPY_COBBLESTONE_TILES)).unlockedBy(getHasName(KELPY_COBBLESTONE_TILES), has(KELPY_COBBLESTONE_TILES)), cnc);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB, KELPY_COBBLESTONE_TILES, 2);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_STAIRS, KELPY_COBBLESTONE_TILES);
		conditionalStonecutterRecipe(output, cnc, DECORATIONS, KELPY_COBBLESTONE_TILE_WALL, KELPY_COBBLESTONE_TILES);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES, KELPY_COBBLESTONE_BRICKS);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB, KELPY_COBBLESTONE_BRICKS, 2);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_STAIRS, KELPY_COBBLESTONE_BRICKS);
		conditionalStonecutterRecipe(output, cnc, DECORATIONS, KELPY_COBBLESTONE_TILE_WALL, KELPY_COBBLESTONE_BRICKS);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES, KELPY_COBBLESTONE);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB, KELPY_COBBLESTONE, 2);
		conditionalStonecutterRecipe(output, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_STAIRS, KELPY_COBBLESTONE);
		conditionalStonecutterRecipe(output, cnc, DECORATIONS, KELPY_COBBLESTONE_TILE_WALL, KELPY_COBBLESTONE);

		conditionalRecipe(output, ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICKS).requires(UAConstants.COBBLESTONE_BRICKS).requires(Blocks.KELP).group("kelpy_cobblestone_bricks").unlockedBy("has_kelp", has(Blocks.KELP)), getModConversionRecipeName(KELPY_COBBLESTONE_BRICKS, Blocks.KELP), cnc);
		conditionalRecipe(output, ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES).requires(UAConstants.COBBLESTONE_TILES).requires(Blocks.KELP).group("kelpy_cobblestone_tiles").unlockedBy("has_kelp", has(Blocks.KELP)), getModConversionRecipeName(KELPY_COBBLESTONE_TILES, Blocks.KELP), cnc);
	}

	public void coralBlockRecipe(RecipeOutput consumer, ItemLike coralBlock, ItemLike coral, TagKey<Item> itemTag) {
		ShapedRecipeBuilder.shaped(DECORATIONS, coralBlock).define('#', itemTag).pattern("##").pattern("##").unlockedBy(getHasName(coral), has(itemTag)).save(consumer, ResourceLocation.fromNamespaceAndPath(this.getModID(), RecipeBuilder.getDefaultRecipeId(coralBlock).getPath()));
	}

	public void conditionalStonecutterRecipe(RecipeOutput consumer, ICondition condition, RecipeCategory category, ItemLike output, ItemLike input) {
		conditionalStonecutterRecipe(consumer, condition, category, output, input, 1);
	}

	public void conditionalStonecutterRecipe(RecipeOutput consumer, ICondition condition, RecipeCategory category, ItemLike output, ItemLike input, int count) {
		conditionalRecipe(consumer, SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input)), this.getModConversionRecipeName(output, input).withSuffix("_stonecutting"), condition);
	}


	@Override
	public void smeltingRecipe(RecipeOutput recipeOutput, List<ItemLike> inputs, RecipeCategory category, ItemLike output, float xp, int cookTime, String group) {
		for (ItemLike item : inputs) {
			SimpleCookingRecipeBuilder.smelting(Ingredient.of(item), category, output, xp, cookTime)
					.unlockedBy(getHasName(item), has(item))
					.group(group)
					.save(recipeOutput, ResourceLocation.fromNamespaceAndPath(this.getModID(), getItemName(output) + "_from_smelting_" + getItemName(item)));
		}
	}

	@Override
	public void blastingRecipe(RecipeOutput recipeOutput, List<ItemLike> inputs, RecipeCategory category, ItemLike output, float xp, int cookTime, String group) {
		for (ItemLike item : inputs) {
			SimpleCookingRecipeBuilder.blasting(Ingredient.of(item), category, output, xp, cookTime)
					.unlockedBy(getHasName(item), has(item))
					.group(group)
					.save(recipeOutput, ResourceLocation.fromNamespaceAndPath(this.getModID(), getItemName(output) + "_from_blasting_" + getItemName(item)));
		}
	}
}