package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.*;

public class UALootTableProvider extends LootTableProvider {

	public UALootTableProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, BuiltInLootTables.all(), ImmutableList.of(
				new LootTableProvider.SubProviderEntry(UABlockLoot::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(UAEntityLoot::new, LootContextParamSets.ENTITY)
		), provider);
	}

	@Override
	protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
	}

	private static class UABlockLoot extends BlockLootSubProvider {
		private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

		private static final LootItemCondition.Builder HAS_SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR));

		protected LootItemCondition.Builder doesNotHaveSilkTouch() {
			return this.hasSilkTouch().invert();
		}

		private LootItemCondition.Builder hasShearsOrSilkTouch() {
			return HAS_SHEARS.or(this.hasSilkTouch());
		}

		private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
			return this.hasShearsOrSilkTouch().invert();
		}

		private static final float[] RIVER_LEAVES_SAPLING_CHANCES = new float[]{0.15F, 0.1875F, 0.25F, 0.3F};

		protected UABlockLoot(Provider provider) {
			super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.add(BUBBLE_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(BUBBLE_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(BUBBLE_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(BUBBLE_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(BUBBLE_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(HORN_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(HORN_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(HORN_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(HORN_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(HORN_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(TUBE_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(TUBE_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(TUBE_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(TUBE_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(TUBE_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(BRAIN_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(BRAIN_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(BRAIN_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(BRAIN_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(BRAIN_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(FIRE_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(FIRE_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(FIRE_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(FIRE_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(FIRE_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(ACAN_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_ACAN_CORAL_BLOCK));
			this.dropWhenSilkTouch(ACAN_CORAL.get());
			this.dropWhenSilkTouch(ACAN_CORAL_FAN.get());
			this.dropSelf(DEAD_ACAN_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_ACAN_CORAL.get());
			this.dropWhenSilkTouch(DEAD_ACAN_CORAL_FAN.get());
			this.add(ACAN_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(ACAN_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(ACAN_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(ACAN_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(ACAN_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(FINGER_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_FINGER_CORAL_BLOCK));
			this.dropWhenSilkTouch(FINGER_CORAL.get());
			this.dropWhenSilkTouch(FINGER_CORAL_FAN.get());
			this.dropSelf(DEAD_FINGER_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_FINGER_CORAL.get());
			this.dropWhenSilkTouch(DEAD_FINGER_CORAL_FAN.get());
			this.add(FINGER_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(FINGER_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(FINGER_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(FINGER_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(FINGER_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(STAR_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_STAR_CORAL_BLOCK));
			this.dropWhenSilkTouch(STAR_CORAL.get());
			this.dropWhenSilkTouch(STAR_CORAL_FAN.get());
			this.dropSelf(DEAD_STAR_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_STAR_CORAL.get());
			this.dropWhenSilkTouch(DEAD_STAR_CORAL_FAN.get());
			this.add(STAR_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(STAR_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(STAR_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(STAR_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(STAR_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(MOSS_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_MOSS_CORAL_BLOCK));
			this.dropWhenSilkTouch(MOSS_CORAL.get());
			this.dropWhenSilkTouch(MOSS_CORAL_FAN.get());
			this.dropSelf(DEAD_MOSS_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_MOSS_CORAL.get());
			this.dropWhenSilkTouch(DEAD_MOSS_CORAL_FAN.get());
			this.add(MOSS_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(MOSS_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(MOSS_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(MOSS_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(MOSS_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(PETAL_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_PETAL_CORAL_BLOCK));
			this.dropWhenSilkTouch(PETAL_CORAL.get());
			this.dropWhenSilkTouch(PETAL_CORAL_FAN.get());
			this.dropSelf(DEAD_PETAL_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_PETAL_CORAL.get());
			this.dropWhenSilkTouch(DEAD_PETAL_CORAL_FAN.get());
			this.add(PETAL_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(PETAL_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(PETAL_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(PETAL_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(PETAL_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(BRANCH_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_BRANCH_CORAL_BLOCK));
			this.dropWhenSilkTouch(BRANCH_CORAL.get());
			this.dropWhenSilkTouch(BRANCH_CORAL_FAN.get());
			this.dropSelf(DEAD_BRANCH_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_BRANCH_CORAL.get());
			this.dropWhenSilkTouch(DEAD_BRANCH_CORAL_FAN.get());
			this.add(BRANCH_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(BRANCH_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(BRANCH_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(BRANCH_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(BRANCH_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(ROCK_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_ROCK_CORAL_BLOCK));
			this.dropWhenSilkTouch(ROCK_CORAL.get());
			this.dropWhenSilkTouch(ROCK_CORAL_FAN.get());
			this.dropSelf(DEAD_ROCK_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_ROCK_CORAL.get());
			this.dropWhenSilkTouch(DEAD_ROCK_CORAL_FAN.get());
			this.add(ROCK_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(ROCK_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(ROCK_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(ROCK_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(ROCK_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(PILLOW_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_PILLOW_CORAL_BLOCK));
			this.dropWhenSilkTouch(PILLOW_CORAL.get());
			this.dropWhenSilkTouch(PILLOW_CORAL_FAN.get());
			this.dropSelf(DEAD_PILLOW_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_PILLOW_CORAL.get());
			this.dropWhenSilkTouch(DEAD_PILLOW_CORAL_FAN.get());
			this.add(PILLOW_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(PILLOW_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(PILLOW_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(PILLOW_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(PILLOW_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(SILK_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_SILK_CORAL_BLOCK));
			this.dropWhenSilkTouch(SILK_CORAL.get());
			this.dropWhenSilkTouch(SILK_CORAL_FAN.get());
			this.dropSelf(DEAD_SILK_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_SILK_CORAL.get());
			this.dropWhenSilkTouch(DEAD_SILK_CORAL_FAN.get());
			this.add(SILK_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(SILK_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(SILK_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(SILK_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(SILK_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.add(CHROME_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHROME_CORAL_BLOCK));
			this.dropWhenSilkTouch(CHROME_CORAL.get());
			this.dropWhenSilkTouch(CHROME_CORAL_FAN.get());
			this.dropSelf(DEAD_CHROME_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(DEAD_CHROME_CORAL.get());
			this.dropWhenSilkTouch(DEAD_CHROME_CORAL_FAN.get());
			this.add(CHROME_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE));
			this.add(CHROME_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_STAIRS));
			this.add(CHROME_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, DEAD_CORALSTONE_SLAB.get()));
			this.add(CHROME_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CORALSTONE_WALL));
			this.add(CHROME_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHISELED_CORALSTONE));

			this.dropSelf(ELDER_PRISMARINE_CORAL_BLOCK.get());
			this.dropWhenSilkTouch(ELDER_PRISMARINE_CORAL.get());
			this.dropWhenSilkTouch(ELDER_PRISMARINE_CORAL_FAN.get());
			this.dropWhenSilkTouch(ELDER_PRISMARINE_CORAL_SHOWER.get());
			this.add(PRISMARINE_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, ELDER_PRISMARINE_CORALSTONE));
			this.add(PRISMARINE_CORALSTONE_STAIRS.get(), block -> this.createSingleItemTableWithSilkTouch(block, ELDER_PRISMARINE_CORALSTONE_STAIRS));
			this.add(PRISMARINE_CORALSTONE_SLAB.get(), block -> this.createCoralstoneSlabTable(block, ELDER_PRISMARINE_CORALSTONE_SLAB.get()));
			this.add(PRISMARINE_CORALSTONE_WALL.get(), block -> this.createSingleItemTableWithSilkTouch(block, ELDER_PRISMARINE_CORALSTONE_WALL));
			this.add(PRISMARINE_CHISELED_CORALSTONE.get(), block -> this.createSingleItemTableWithSilkTouch(block, CHISELED_ELDER_PRISMARINE_CORALSTONE));

			this.dropSelf(CORALSTONE.get());
			this.dropSelf(CORALSTONE_STAIRS.get());
			this.add(CORALSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CORALSTONE_WALL.get());
			this.dropSelf(CHISELED_CORALSTONE.get());

			this.dropSelf(DEAD_CORALSTONE.get());
			this.dropSelf(DEAD_CORALSTONE_STAIRS.get());
			this.add(DEAD_CORALSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(DEAD_CORALSTONE_WALL.get());
			this.dropSelf(DEAD_CHISELED_CORALSTONE.get());

			this.dropSelf(ELDER_PRISMARINE_CORALSTONE.get());
			this.dropSelf(ELDER_PRISMARINE_CORALSTONE_STAIRS.get());
			this.add(ELDER_PRISMARINE_CORALSTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(ELDER_PRISMARINE_CORALSTONE_WALL.get());
			this.dropSelf(CHISELED_ELDER_PRISMARINE_CORALSTONE.get());

			this.dropSelf(BEACHGRASS_THATCH.get());
			this.dropSelf(BEACHGRASS_THATCH_STAIRS.get());
			this.add(BEACHGRASS_THATCH_SLAB.get(), this::createSlabItemTable);

			this.dropSelf(ELDER_EYE.get());
			this.dropSelf(EMBEDDED_AMMONITE.get());

			this.dropSelf(LUMINOUS_PRISMARINE.get());
			this.dropSelf(LUMINOUS_PRISMARINE_STAIRS.get());
			this.add(LUMINOUS_PRISMARINE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(PRISMARINE_ROD_BUNDLE.get());

			this.dropSelf(MULBERRY_JAM_BLOCK.get());
			this.dropSelf(MULBERRY_PUNNET.get());

			this.dropSelf(TOOTH_BLOCK.get());
			this.dropSelf(TOOTH_BRICKS.get());
			this.dropSelf(TOOTH_BRICK_STAIRS.get());
			this.dropSelf(TOOTH_BRICK_WALL.get());
			this.add(TOOTH_BRICK_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHISELED_TOOTH_BRICKS.get());
			this.dropSelf(TOOTH_TILES.get());
			this.dropSelf(TOOTH_TILE_STAIRS.get());
			this.dropSelf(TOOTH_TILE_WALL.get());
			this.add(TOOTH_TILE_SLAB.get(), this::createSlabItemTable);
			this.add(TOOTH_DOOR.get(), this::createDoorTable);
			this.dropSelf(TOOTH_TRAPDOOR.get());
			this.dropSelf(TOOTH_LANTERN.get());

			this.dropSelf(SCUTE_BLOCK.get());
			this.dropSelf(SCUTE_SHINGLES.get());
			this.dropSelf(SCUTE_SHINGLE_STAIRS.get());
			this.dropSelf(SCUTE_SHINGLE_WALL.get());
			this.add(SCUTE_SHINGLE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(CHISELED_SCUTE_SHINGLES.get());
			this.dropSelf(SCUTE_PAVEMENT.get());
			this.dropSelf(SCUTE_PAVEMENT_STAIRS.get());
			this.dropSelf(SCUTE_PAVEMENT_WALL.get());
			this.add(SCUTE_PAVEMENT_SLAB.get(), this::createSlabItemTable);

			this.dropSelf(PICKERELWEED.get());
			this.dropOther(TALL_PICKERELWEED.get(), PICKERELWEED.get());

			this.dropSelf(PICKERELWEED_BLOCK.get());
			this.dropSelf(BOILED_PICKERELWEED_BLOCK.get());
			this.dropPottedContents(POTTED_PICKERELWEED.get());

			this.dropSelf(KELP_BLOCK.get());
			this.dropSelf(KELPY_COBBLESTONE.get());
			this.dropSelf(KELPY_COBBLESTONE_STAIRS.get());
			this.dropSelf(KELPY_COBBLESTONE_WALL.get());
			this.add(KELPY_COBBLESTONE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(KELPY_COBBLESTONE_BRICKS.get());
			this.dropSelf(KELPY_COBBLESTONE_BRICK_STAIRS.get());
			this.dropSelf(KELPY_COBBLESTONE_BRICK_WALL.get());
			this.add(KELPY_COBBLESTONE_BRICK_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(KELPY_COBBLESTONE_TILES.get());
			this.dropSelf(KELPY_COBBLESTONE_TILE_STAIRS.get());
			this.dropSelf(KELPY_COBBLESTONE_TILE_WALL.get());
			this.add(KELPY_COBBLESTONE_TILE_SLAB.get(), this::createSlabItemTable);
			this.dropSelf(KELPY_STONE_BRICKS.get());
			this.dropSelf(KELPY_STONE_BRICK_STAIRS.get());
			this.dropSelf(KELPY_STONE_BRICK_WALL.get());
			this.add(KELPY_STONE_BRICK_SLAB.get(), this::createSlabItemTable);

			this.dropSelf(RIVER_PLANKS.get());
			this.dropSelf(RIVER_LOG.get());
			this.dropSelf(RIVER_WOOD.get());
			this.dropSelf(STRIPPED_RIVER_LOG.get());
			this.dropSelf(STRIPPED_RIVER_WOOD.get());
			this.dropSelf(RIVER_SIGNS.getFirst().get());
			this.dropSelf(RIVER_HANGING_SIGNS.getFirst().get());
			this.dropSelf(RIVER_PRESSURE_PLATE.get());
			this.dropSelf(RIVER_TRAPDOOR.get());
			this.dropSelf(RIVER_BUTTON.get());
			this.dropSelf(RIVER_STAIRS.get());
			this.dropSelf(RIVER_FENCE.get());
			this.dropSelf(RIVER_FENCE_GATE.get());
			this.dropSelf(RIVER_BOARDS.get());
			this.add(RIVER_LEAF_PILE.get(), this::createLeafPileDrops);
			this.dropSelf(RIVER_SAPLING.get());
			this.dropPottedContents(POTTED_RIVER_SAPLING.get());
			this.dropSelf(RIVER_LADDER.get());
			this.add(RIVER_SLAB.get(), this::createSlabItemTable);
			this.add(RIVER_DOOR.get(), this::createDoorTable);
			this.add(RIVER_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(RIVER_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_RIVER_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(RIVER_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_RIVER_BOOKSHELF.get());
			this.add(RIVER_LEAVES.get(), (block) -> createLeavesDrops(block, RIVER_SAPLING.get(), RIVER_LEAVES_SAPLING_CHANCES));

			this.dropSelf(DRIFTWOOD_PLANKS.get());
			this.dropSelf(DRIFTWOOD_LOG.get());
			this.dropSelf(DRIFTWOOD.get());
			this.dropSelf(STRIPPED_DRIFTWOOD_LOG.get());
			this.dropSelf(STRIPPED_DRIFTWOOD.get());
			this.dropSelf(DRIFTWOOD_SIGNS.getFirst().get());
			this.dropSelf(DRIFTWOOD_HANGING_SIGNS.getFirst().get());
			this.dropSelf(DRIFTWOOD_PRESSURE_PLATE.get());
			this.dropSelf(DRIFTWOOD_TRAPDOOR.get());
			this.dropSelf(DRIFTWOOD_BUTTON.get());
			this.dropSelf(DRIFTWOOD_STAIRS.get());
			this.dropSelf(DRIFTWOOD_FENCE.get());
			this.dropSelf(DRIFTWOOD_FENCE_GATE.get());
			this.dropSelf(DRIFTWOOD_BOARDS.get());
			this.dropSelf(DRIFTWOOD_LADDER.get());
			this.add(DRIFTWOOD_SLAB.get(), this::createSlabItemTable);
			this.add(DRIFTWOOD_DOOR.get(), this::createDoorTable);
			this.add(DRIFTWOOD_BEEHIVE.get(), this::createBeeHiveDrop);
			this.add(DRIFTWOOD_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(TRAPPED_DRIFTWOOD_CHEST.get(), this::createNameableBlockEntityTable);
			this.add(DRIFTWOOD_BOOKSHELF.get(), (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			this.dropWhenSilkTouch(CHISELED_DRIFTWOOD_BOOKSHELF.get());

			this.dropSelf(PINK_JELLY_TORCH.get());
			this.dropSelf(PURPLE_JELLY_TORCH.get());
			this.dropSelf(BLUE_JELLY_TORCH.get());
			this.dropSelf(GREEN_JELLY_TORCH.get());
			this.dropSelf(YELLOW_JELLY_TORCH.get());
			this.dropSelf(ORANGE_JELLY_TORCH.get());
			this.dropSelf(RED_JELLY_TORCH.get());
			this.dropSelf(WHITE_JELLY_TORCH.get());

			this.dropSelf(WHITE_SEAROCKET.get());
			this.dropPottedContents(POTTED_WHITE_SEAROCKET.get());
			this.dropSelf(PINK_SEAROCKET.get());
			this.dropPottedContents(POTTED_PINK_SEAROCKET.get());
			this.add(FLOWERING_RUSH.get(), block -> this.createSinglePropConditionTable(block, DoublePlantBlock.HALF, DoubleBlockHalf.LOWER));

			this.add(BEACHGRASS.get(), noDrop());
			this.add(TALL_BEACHGRASS.get(), noDrop());
			this.add(BEDROLL.get(), noDrop());
			this.add(MULBERRY_VINE.get(), noDrop());
			this.add(PRISMARINE_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, ELDER_PRISMARINE_CORAL_BLOCK));
			this.dropWhenSilkTouch(PRISMARINE_CORAL.get());
			this.dropWhenSilkTouch(PRISMARINE_CORAL_FAN.get());
			this.dropWhenSilkTouch(PRISMARINE_CORAL_SHOWER.get());
		}

		protected Builder createLeafPileDrops(Block block) {
			return createMultifaceBlockDrops(block, MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR)));
		}

		protected LootTable.Builder createCoralstoneSlabTable(Block block, Block deadBlock) {
			return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
					.add(this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))
							.when(this.hasSilkTouch())
							.otherwise(this.applyExplosionDecay(deadBlock, LootItem.lootTableItem(deadBlock).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
									.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(deadBlock).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))))
							)
					)
			);
		}

		@Override
		public Iterable<Block> getKnownBlocks() {
			return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(UpgradeAquatic.MOD_ID)).collect(Collectors.toSet());
		}
	}

	private static class UAEntityLoot extends EntityLootSubProvider {

		protected UAEntityLoot(Provider provider) {
			super(FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.add(UAEntityTypes.FLARE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.PHANTOM_MEMBRANE).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer())));

			this.add(UAEntityTypes.PERCH.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(UAItems.PERCH.get()).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F))));

			this.add(UAEntityTypes.PIKE.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(UAItems.PIKE.get()).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.20F))));

			this.add(UAEntityTypes.LIONFISH.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(UAItems.LIONFISH.get()).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F))));

			this.add(UAEntityTypes.THRASHER.get(), LootTable.lootTable());
			this.add(UAEntityTypes.GREAT_THRASHER.get(), LootTable.lootTable());
			this.add(UAEntityTypes.NAUTILUS.get(), LootTable.lootTable());

			this.add(UAEntityTypes.GOOSE.get(), LootTable.lootTable());

			this.add(UAEntityTypes.BOX_JELLYFISH.get(), LootTable.lootTable());
			this.add(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), LootTable.lootTable());
			this.add(UAEntityTypes.IMMORTAL_JELLYFISH.get(), LootTable.lootTable());
		}

		@Override
		public Stream<EntityType<?>> getKnownEntityTypes() {
			return BuiltInRegistries.ENTITY_TYPE.stream().filter(entity -> BuiltInRegistries.ENTITY_TYPE.getKey(entity).getNamespace().equals(UpgradeAquatic.MOD_ID));
		}
	}
}