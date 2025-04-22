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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
	public void buildRecipes(RecipeOutput consumer) {
		conversionRecipe(consumer, Items.PINK_DYE, PINK_SEAROCKET.get(), "pink_dye");
		conversionRecipe(consumer, Items.WHITE_DYE, WHITE_SEAROCKET.get(), "white_dye");
		conversionRecipe(consumer, Items.CYAN_DYE, PICKERELWEED.get(), "cyan_dye");
		conversionRecipe(consumer, Items.PINK_DYE, FLOWERING_RUSH.get(), "pink_dye", 2);
		conversionRecipe(consumer, Items.MAGENTA_DYE, UAItems.MULBERRY.get(), "magenta_dye");

		foodCookingRecipes(consumer, UAItems.PIKE.get(), UAItems.COOKED_PIKE.get());
		foodCookingRecipes(consumer, UAItems.PERCH.get(), UAItems.COOKED_PERCH.get());
		foodCookingRecipes(consumer, UAItems.LIONFISH.get(), UAItems.COOKED_LIONFISH.get());
		foodCookingRecipes(consumer, PICKERELWEED.get(), UAItems.BOILED_PICKERELWEED.get());

		ShapedRecipeBuilder.shaped(DECORATIONS, BEDROLL.get()).define('#', Items.LEATHER).define('X', Items.WHITE_WOOL).pattern("##X").pattern("###").group("bedroll").unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER)).save(consumer);

		oreRecipes(consumer, List.of(EMBEDDED_AMMONITE.get()), MISC, Items.NAUTILUS_SHELL, 1.0F, 200, "nautilus_shell");

		storageRecipes(consumer, MISC, Items.TURTLE_SCUTE, BUILDING_BLOCKS, SCUTE_BLOCK.get());

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, SCUTE_SHINGLES.get(), 8).define('#', Blocks.STONE_BRICKS).define('S', Items.TURTLE_SCUTE).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_scute", has(Items.TURTLE_SCUTE)).save(consumer);
		generateRecipes(consumer, SCUTE_SHINGLES_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_SHINGLE_SLAB.get(), SCUTE_SHINGLES.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_SHINGLE_STAIRS.get(), SCUTE_SHINGLES.get());
		stonecutterRecipe(consumer, DECORATIONS, SCUTE_SHINGLE_WALL.get(), SCUTE_SHINGLES.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, CHISELED_SCUTE_SHINGLES.get(), SCUTE_SHINGLES.get());

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, SCUTE_PAVEMENT.get(), 4).define('#', SCUTE_SHINGLES.get()).pattern("##").pattern("##").unlockedBy("has_scute_shingles", has(SCUTE_SHINGLES.get())).save(consumer);
		generateRecipes(consumer, SCUTE_PAVEMENT_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_PAVEMENT_SLAB.get(), SCUTE_PAVEMENT.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_PAVEMENT_STAIRS.get(), SCUTE_PAVEMENT.get());
		stonecutterRecipe(consumer, DECORATIONS, SCUTE_PAVEMENT_WALL.get(), SCUTE_PAVEMENT.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_PAVEMENT.get(), SCUTE_SHINGLES.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_PAVEMENT_SLAB.get(), SCUTE_SHINGLES.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_PAVEMENT_STAIRS.get(), SCUTE_SHINGLES.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, SCUTE_PAVEMENT_WALL.get(), SCUTE_SHINGLES.get());

		storageRecipes(consumer, MISC, UAItems.THRASHER_TOOTH.get(), BUILDING_BLOCKS, TOOTH_BLOCK.get());

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, TOOTH_BRICKS.get(), 8).define('#', Blocks.STONE_BRICKS).define('S', UAItems.THRASHER_TOOTH.get()).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_thrasher_tooth", has(UAItems.THRASHER_TOOTH.get())).save(consumer);
		generateRecipes(consumer, TOOTH_BRICKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_BRICK_SLAB.get(), TOOTH_BRICKS.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_BRICK_STAIRS.get(), TOOTH_BRICKS.get());
		stonecutterRecipe(consumer, DECORATIONS, TOOTH_BRICK_WALL.get(), TOOTH_BRICKS.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, CHISELED_TOOTH_BRICKS.get(), TOOTH_BRICKS.get());

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, TOOTH_TILES.get(), 4).define('#', TOOTH_BRICKS.get()).pattern("##").pattern("##").unlockedBy("has_tooth_bricks", has(TOOTH_BRICKS.get())).save(consumer);
		generateRecipes(consumer, TOOTH_TILES_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_TILE_SLAB.get(), TOOTH_TILES.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_TILE_STAIRS.get(), TOOTH_TILES.get());
		stonecutterRecipe(consumer, DECORATIONS, TOOTH_TILE_WALL.get(), TOOTH_TILES.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_TILES.get(), TOOTH_BRICKS.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_TILE_SLAB.get(), TOOTH_BRICKS.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_TILE_STAIRS.get(), TOOTH_BRICKS.get());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, TOOTH_TILE_WALL.get(), TOOTH_BRICKS.get());

		generateRecipes(consumer, DRIFTWOOD_PLANKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		planksFromLogs(consumer, DRIFTWOOD_PLANKS.get(), UAItemTags.DRIFTWOOD_LOGS, 4);
		woodFromLogs(consumer, DRIFTWOOD.get(), DRIFTWOOD_LOG.get());
		woodFromLogs(consumer, STRIPPED_DRIFTWOOD.get(), STRIPPED_DRIFTWOOD_LOG.get());
		hangingSign(consumer, DRIFTWOOD_HANGING_SIGNS.getFirst().get(), STRIPPED_DRIFTWOOD_LOG.get());

		BoatloadRecipeProvider.boatRecipes(consumer, UABoatTypes.DRIFTWOOD);
		WoodworksRecipeProvider.baseRecipes(consumer, DRIFTWOOD_PLANKS.get(), DRIFTWOOD_SLAB.get(), DRIFTWOOD_BOARDS.get(), DRIFTWOOD_BOOKSHELF.get(), CHISELED_DRIFTWOOD_BOOKSHELF.get(), DRIFTWOOD_LADDER.get(), DRIFTWOOD_BEEHIVE.get(), DRIFTWOOD_CHEST.get(), TRAPPED_DRIFTWOOD_CHEST.get(), UpgradeAquatic.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, DRIFTWOOD_PLANKS_FAMILY, UAItemTags.DRIFTWOOD_LOGS, DRIFTWOOD_BOARDS.get(), DRIFTWOOD_LADDER.get(), UpgradeAquatic.MOD_ID);

		generateRecipes(consumer, RIVER_PLANKS_FAMILY, FeatureFlags.REGISTRY.allFlags());
		planksFromLogs(consumer, RIVER_PLANKS.get(), UAItemTags.RIVER_LOGS, 4);
		woodFromLogs(consumer, RIVER_WOOD.get(), RIVER_LOG.get());
		woodFromLogs(consumer, STRIPPED_RIVER_WOOD.get(), STRIPPED_RIVER_LOG.get());
		hangingSign(consumer, RIVER_HANGING_SIGNS.getFirst().get(), STRIPPED_RIVER_LOG.get());

		BoatloadRecipeProvider.boatRecipes(consumer, UABoatTypes.RIVER);
		WoodworksRecipeProvider.baseRecipes(consumer, RIVER_PLANKS.get(), RIVER_SLAB.get(), RIVER_BOARDS.get(), RIVER_BOOKSHELF.get(), CHISELED_RIVER_BOOKSHELF.get(), RIVER_LADDER.get(), RIVER_BEEHIVE.get(), RIVER_CHEST.get(), TRAPPED_RIVER_CHEST.get(), UpgradeAquatic.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(consumer, RIVER_PLANKS_FAMILY, UAItemTags.RIVER_LOGS, RIVER_BOARDS.get(), RIVER_LADDER.get(), UpgradeAquatic.MOD_ID);
		WoodworksRecipeProvider.conditionalLeafPileRecipes(consumer, RIVER_LEAVES.get(), RIVER_LEAF_PILE.get(), UpgradeAquatic.MOD_ID);

		ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, CORALSTONE.get(), 8).define('#', Blocks.STONE_BRICKS).define('S', Items.NAUTILUS_SHELL).pattern("###").pattern("#S#").pattern("###").unlockedBy("has_nautilus_shell", has(Items.NAUTILUS_SHELL)).save(consumer);
		generateRecipes(consumer, CORALSTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, CORALSTONE_SLAB.get(), CORALSTONE.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, CORALSTONE_STAIRS.get(), CORALSTONE.get());
		stonecutterRecipe(consumer, DECORATIONS, CORALSTONE_WALL.get(), CORALSTONE.get());

		generateRecipes(consumer, DEAD_CORALSTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, DEAD_CORALSTONE_SLAB.get(), DEAD_CORALSTONE.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, DEAD_CORALSTONE_STAIRS.get(), DEAD_CORALSTONE.get());
		stonecutterRecipe(consumer, DECORATIONS, DEAD_CORALSTONE_WALL.get(), DEAD_CORALSTONE.get());

		generateRecipes(consumer, ELDER_PRISMARINE_CORALSTONE_FAMILY, FeatureFlags.REGISTRY.allFlags());
		stonecutterRecipe(consumer, BUILDING_BLOCKS, ELDER_PRISMARINE_CORALSTONE_SLAB.get(), ELDER_PRISMARINE_CORALSTONE.get(), 2);
		stonecutterRecipe(consumer, BUILDING_BLOCKS, ELDER_PRISMARINE_CORALSTONE_STAIRS.get(), ELDER_PRISMARINE_CORALSTONE.get());
		stonecutterRecipe(consumer, DECORATIONS, ELDER_PRISMARINE_CORALSTONE_WALL.get(), ELDER_PRISMARINE_CORALSTONE.get());

		CoralType.values().forEach(coralType -> {
			BlockFamily family = coralType.coralstoneFamily();
			coralBlockRecipe(consumer, coralType.coralBlock().get(), coralType.coral().get(), coralType.itemTag());
			coralBlockRecipe(consumer, coralType.deadCoralBlock().get(), coralType.deadCoral().get(), coralType.deadItemTag());
			generateRecipes(consumer, coralType.coralstoneFamily(), FeatureFlags.REGISTRY.allFlags());
			stonecutterRecipe(consumer, BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
			stonecutterRecipe(consumer, BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
			stonecutterRecipe(consumer, DECORATIONS, family.get(Variant.WALL), family.getBaseBlock());
		});

		ModLoadedCondition cnc = new ModLoadedCondition("caverns_and_chasms");

		conditionalRecipe(consumer, ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICKS.get(), 4).define('#', KELPY_COBBLESTONE.get()).pattern("##").pattern("##").unlockedBy(getHasName(KELPY_COBBLESTONE.get()), has(KELPY_COBBLESTONE.get())), cnc);
		conditionalRecipe(consumer, stairBuilder(KELPY_COBBLESTONE_BRICK_STAIRS.get(), Ingredient.of(KELPY_COBBLESTONE_BRICKS.get())).unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS.get()), has(KELPY_COBBLESTONE_BRICKS.get())), cnc);
		conditionalRecipe(consumer, slabBuilder(RecipeCategory.BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_SLAB.get(), Ingredient.of(KELPY_COBBLESTONE_BRICKS.get())).unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS.get()), has(KELPY_COBBLESTONE_BRICKS.get())), cnc);
		conditionalRecipe(consumer, wallBuilder(RecipeCategory.DECORATIONS, KELPY_COBBLESTONE_BRICK_WALL.get(), Ingredient.of(KELPY_COBBLESTONE_BRICKS.get())).unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS.get()), has(KELPY_COBBLESTONE_BRICKS.get())), cnc);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_SLAB.get(), KELPY_COBBLESTONE_BRICKS.get(), 2);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_STAIRS.get(), KELPY_COBBLESTONE_BRICKS.get());
		conditionalStonecutterRecipe(consumer, cnc, DECORATIONS, KELPY_COBBLESTONE_BRICK_WALL.get(), KELPY_COBBLESTONE_BRICKS.get());
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICKS.get(), KELPY_COBBLESTONE.get());
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_SLAB.get(), KELPY_COBBLESTONE.get(), 2);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICK_STAIRS.get(), KELPY_COBBLESTONE.get());
		conditionalStonecutterRecipe(consumer, cnc, DECORATIONS, KELPY_COBBLESTONE_BRICK_WALL.get(), KELPY_COBBLESTONE.get());

		conditionalRecipe(consumer, ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES.get(), 4).define('#', KELPY_COBBLESTONE_BRICKS.get()).pattern("##").pattern("##").unlockedBy(getHasName(KELPY_COBBLESTONE_BRICKS.get()), has(KELPY_COBBLESTONE_BRICKS.get())), cnc);
		conditionalRecipe(consumer, stairBuilder(KELPY_COBBLESTONE_TILE_STAIRS.get(), Ingredient.of(KELPY_COBBLESTONE_TILES.get())).unlockedBy(getHasName(KELPY_COBBLESTONE_TILES.get()), has(KELPY_COBBLESTONE_TILES.get())), cnc);
		conditionalRecipe(consumer, slabBuilder(RecipeCategory.BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB.get(), Ingredient.of(KELPY_COBBLESTONE_TILES.get())).unlockedBy(getHasName(KELPY_COBBLESTONE_TILES.get()), has(KELPY_COBBLESTONE_TILES.get())), cnc);
		conditionalRecipe(consumer, wallBuilder(RecipeCategory.DECORATIONS, KELPY_COBBLESTONE_TILE_WALL.get(), Ingredient.of(KELPY_COBBLESTONE_TILES.get())).unlockedBy(getHasName(KELPY_COBBLESTONE_TILES.get()), has(KELPY_COBBLESTONE_TILES.get())), cnc);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB.get(), KELPY_COBBLESTONE_TILES.get(), 2);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_STAIRS.get(), KELPY_COBBLESTONE_TILES.get());
		conditionalStonecutterRecipe(consumer, cnc, DECORATIONS, KELPY_COBBLESTONE_TILE_WALL.get(), KELPY_COBBLESTONE_TILES.get());
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES.get(), KELPY_COBBLESTONE_BRICKS.get());
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB.get(), KELPY_COBBLESTONE_BRICKS.get(), 2);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_STAIRS.get(), KELPY_COBBLESTONE_BRICKS.get());
		conditionalStonecutterRecipe(consumer, cnc, DECORATIONS, KELPY_COBBLESTONE_TILE_WALL.get(), KELPY_COBBLESTONE_BRICKS.get());
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES.get(), KELPY_COBBLESTONE.get());
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_SLAB.get(), KELPY_COBBLESTONE.get(), 2);
		conditionalStonecutterRecipe(consumer, cnc, BUILDING_BLOCKS, KELPY_COBBLESTONE_TILE_STAIRS.get(), KELPY_COBBLESTONE.get());
		conditionalStonecutterRecipe(consumer, cnc, DECORATIONS, KELPY_COBBLESTONE_TILE_WALL.get(), KELPY_COBBLESTONE.get());

		conditionalRecipe(consumer, ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, KELPY_COBBLESTONE_BRICKS.get()).requires(UAConstants.COBBLESTONE_BRICKS.get()).requires(Blocks.KELP).group("kelpy_cobblestone_bricks").unlockedBy("has_kelp", has(Blocks.KELP)), getModConversionRecipeName(KELPY_COBBLESTONE_BRICKS.get(), Blocks.KELP), cnc);
		conditionalRecipe(consumer, ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, KELPY_COBBLESTONE_TILES.get()).requires(UAConstants.COBBLESTONE_TILES.get()).requires(Blocks.KELP).group("kelpy_cobblestone_tiles").unlockedBy("has_kelp", has(Blocks.KELP)), getModConversionRecipeName(KELPY_COBBLESTONE_TILES.get(), Blocks.KELP), cnc);
	}

	public void coralBlockRecipe(RecipeOutput consumer, Block coralBlock, Block coral, TagKey<Item> itemTag) {
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