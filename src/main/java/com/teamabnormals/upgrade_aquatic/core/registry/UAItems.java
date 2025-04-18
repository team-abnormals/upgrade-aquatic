package com.teamabnormals.upgrade_aquatic.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.item.BlueprintBoatItem;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.item.*;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAJukeboxSongs;
import com.teamabnormals.upgrade_aquatic.core.registry.helper.UAItemSubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.integration.boatload.UABoatTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class UAItems {
	public static final UAItemSubRegistryHelper ITEMS = UpgradeAquatic.REGISTRY_HELPER.getItemSubHelper();

	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> DRIFTWOOD_BOAT = ITEMS.createBoatAndChestBoatItem("driftwood", UABlocks.DRIFTWOOD_PLANKS, false);
	public static final DeferredItem<Item> DRIFTWOOD_FURNACE_BOAT = ITEMS.createItem("driftwood_furnace_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.DRIFTWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_DRIFTWOOD_BOAT = ITEMS.createItem("large_driftwood_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.LARGE_DRIFTWOOD_BOAT : () -> new Item(new Item.Properties()));
	public static final Pair<DeferredItem<BlueprintBoatItem>, DeferredItem<BlueprintBoatItem>> RIVER_BOAT = ITEMS.createBoatAndChestBoatItem("river", UABlocks.RIVER_PLANKS, false);
	public static final DeferredItem<Item> RIVER_FURNACE_BOAT = ITEMS.createItem("river_furnace_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.RIVER_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> LARGE_RIVER_BOAT = ITEMS.createItem("large_river_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.LARGE_RIVER_BOAT : () -> new Item(new Item.Properties()));

	public static final DeferredItem<Item> NAUTILUS_BUCKET = ITEMS.createItem("nautilus_bucket", () -> new MobBucketItem(UAEntityTypes.NAUTILUS.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> PIKE_BUCKET = ITEMS.createItem("pike_bucket", () -> new PikeBucketItem(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> LIONFISH_BUCKET = ITEMS.createItem("lionfish_bucket", () -> new MobBucketItem(UAEntityTypes.LIONFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> PERCH_BUCKET = ITEMS.createItem("perch_bucket", () -> new MobBucketItem(UAEntityTypes.PERCH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> SQUID_BUCKET = ITEMS.createItem("squid_bucket", () -> new SquidBucketItem(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> GLOW_SQUID_BUCKET = ITEMS.createItem("glow_squid_bucket", () -> new GlowSquidBucketItem(new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> JELLYFISH_BUCKET = ITEMS.createItem("jellyfish_bucket", () -> new JellyfishBucketItem(Fluids.WATER, new Item.Properties().stacksTo(1)));

	public static final DeferredItem<Item> PRISMARINE_ROD = ITEMS.createItem("prismarine_rod", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> THRASHER_TOOTH = ITEMS.createItem("thrasher_tooth", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> ELDER_EYE = ITEMS.createItem("elder_eye", () -> new ElderEyeBlockItem(UABlocks.ELDER_EYE.get(), new Item.Properties().rarity(Rarity.UNCOMMON)));
	public static final DeferredItem<Item> BOILED_PICKERELWEED = ITEMS.createItem("boiled_pickerelweed", () -> new BoiledPickerelweedItem(new Item.Properties().food(UAFoods.PICKERELWEED)));

	public static final DeferredItem<Item> PIKE = ITEMS.createItem("pike", () -> new Item(new Item.Properties().food(UAFoods.PIKE)));
	public static final DeferredItem<Item> COOKED_PIKE = ITEMS.createItem("cooked_pike", () -> new Item(new Item.Properties().food(UAFoods.COOKED_PIKE)));
	public static final DeferredItem<Item> LIONFISH = ITEMS.createItem("lionfish", () -> new Item(new Item.Properties().food(UAFoods.LIONFISH)));
	public static final DeferredItem<Item> COOKED_LIONFISH = ITEMS.createItem("cooked_lionfish", () -> new Item(new Item.Properties().food(UAFoods.COOKED_LIONFISH)));
	public static final DeferredItem<Item> PERCH = ITEMS.createItem("perch", () -> new Item(new Item.Properties().food(UAFoods.PERCH)));
	public static final DeferredItem<Item> COOKED_PERCH = ITEMS.createItem("cooked_perch", () -> new Item(new Item.Properties().food(UAFoods.COOKED_PERCH)));

	public static final DeferredItem<Item> MULBERRY = ITEMS.createItem("mulberry", () -> new ItemNameBlockItem(UABlocks.MULBERRY_VINE.get(), new Item.Properties().food(UAFoods.MULBERRY)));
	public static final DeferredItem<Item> MULBERRY_JAM_BOTTLE = ITEMS.createItem("mulberry_jam_bottle", () -> new MulberryJamBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(UAFoods.MULBERRY_JAM)));
	public static final DeferredItem<Item> MULBERRY_BREAD = ITEMS.createItem("mulberry_bread", () -> new Item(new Item.Properties().food(UAFoods.MULBERRY_BREAD)));
	public static final DeferredItem<Item> MULBERRY_PIE = ITEMS.createItem("mulberry_pie", () -> new Item(new Item.Properties().food(UAFoods.MULBERRY_PIE)));

	public static final DeferredItem<Item> MUSIC_DISC_ATLANTIS = ITEMS.createItem("music_disc_atlantis", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(UAJukeboxSongs.JUKEBOX_SONG_ATLANTIS)));
	public static final DeferredItem<Item> DISC_FRAGMENT_ATLANTIS = ITEMS.createItem("disc_fragment_atlantis", () -> new DiscFragmentItem(new Item.Properties()));

	public static final DeferredItem<Item> PREDATOR_POTTERY_SHERD = ITEMS.createItem("predator_pottery_sherd", () -> new Item(new Item.Properties()));

	public static final DeferredItem<DeferredSpawnEggItem> NAUTILUS_SPAWN_EGG = ITEMS.createSpawnEggItem("nautilus", UAEntityTypes.NAUTILUS::get, 14596231, 16744272);
	public static final DeferredItem<DeferredSpawnEggItem> PIKE_SPAWN_EGG = ITEMS.createSpawnEggItem("pike", UAEntityTypes.PIKE::get, 4806944, 13002040);
	public static final DeferredItem<DeferredSpawnEggItem> LIONFISH_SPAWN_EGG = ITEMS.createSpawnEggItem("lionfish", UAEntityTypes.LIONFISH::get, 15281931, 16111310);
	public static final DeferredItem<DeferredSpawnEggItem> PERCH_SPAWN_EGG = ITEMS.createSpawnEggItem("perch", UAEntityTypes.PERCH::get, 7764021, 12555079);
	public static final DeferredItem<DeferredSpawnEggItem> THRASHER_SPAWN_EGG = ITEMS.createSpawnEggItem("thrasher", UAEntityTypes.THRASHER::get, 7255507, 11730927);
	public static final DeferredItem<DeferredSpawnEggItem> GREAT_THRASHER_SPAWN_EGG = ITEMS.createSpawnEggItem("great_thrasher", UAEntityTypes.GREAT_THRASHER::get, 10078409, 13294289);
	public static final DeferredItem<DeferredSpawnEggItem> FLARE_SPAWN_EGG = ITEMS.createSpawnEggItem("flare", UAEntityTypes.FLARE::get, 4532619, 14494960);
	public static final DeferredItem<JellyfishSpawnEggItem> JELLYFISH_SPAWN_EGG = ITEMS.createJellyfishSpawnEggItem("jellyfish", 3911164, 16019855);
	public static final DeferredItem<DeferredSpawnEggItem> GOOSE_SPAWN_EGG = ITEMS.createSpawnEggItem("goose", UAEntityTypes.GOOSE::get, 16448255, 16751914);

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(UpgradeAquatic.MOD_ID)
				.tab(FOOD_AND_DRINKS)
				.addItemsAfter(of(Items.GLOW_BERRIES), MULBERRY)
				.addItemsAfter(of(Items.BREAD), MULBERRY_BREAD)
				.addItemsAfter(of(Items.PUMPKIN_PIE), MULBERRY_PIE)
				.addItemsAfter(of(Items.HONEY_BOTTLE), MULBERRY_JAM_BOTTLE)
				.addItemsBefore(of(Items.TROPICAL_FISH), PIKE, COOKED_PIKE, PERCH, COOKED_PERCH, LIONFISH, COOKED_LIONFISH)
				.addItemsBefore(of(Items.DRIED_KELP), BOILED_PICKERELWEED)
				.tab(INGREDIENTS)
				.addItemsAfter(of(Items.PRISMARINE_SHARD), PRISMARINE_ROD)
				.addItemsAfter(of(Items.NAUTILUS_SHELL), THRASHER_TOOTH)
				.addItemsAfter(of(Items.HEART_OF_THE_SEA), DISC_FRAGMENT_ATLANTIS)
				.addPotterySherdsAlphabetically(PREDATOR_POTTERY_SHERD)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsAfter(of(Items.SALMON_BUCKET), PIKE_BUCKET, PERCH_BUCKET, LIONFISH_BUCKET)
				.addItemsAfter(of(Items.AXOLOTL_BUCKET), NAUTILUS_BUCKET, SQUID_BUCKET, GLOW_SQUID_BUCKET)
				.addItemsBefore(of(Items.BAMBOO_RAFT), DRIFTWOOD_BOAT.getFirst(), DRIFTWOOD_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), DRIFTWOOD_FURNACE_BOAT, LARGE_DRIFTWOOD_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), RIVER_BOAT.getFirst(), RIVER_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), RIVER_FURNACE_BOAT, LARGE_RIVER_BOAT)
				.addItemsAfter(of(Items.MUSIC_DISC_5), MUSIC_DISC_ATLANTIS)
				.tab(SPAWN_EGGS)
				.addSpawnEggsAlphabetically(NAUTILUS_SPAWN_EGG, PIKE_SPAWN_EGG, LIONFISH_SPAWN_EGG, PERCH_SPAWN_EGG, THRASHER_SPAWN_EGG, GREAT_THRASHER_SPAWN_EGG);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static class UAFoods {
		public static final FoodProperties MULBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F).build();
		public static final FoodProperties MULBERRY_JAM = new FoodProperties.Builder().nutrition(4).saturationModifier(0.2F).alwaysEdible().build();
		public static final FoodProperties MULBERRY_BREAD = new FoodProperties.Builder().nutrition(9).saturationModifier(0.4F).build();
		public static final FoodProperties MULBERRY_PIE = new FoodProperties.Builder().nutrition(7).saturationModifier(0.6F).build();

		public static final FoodProperties PICKERELWEED = new FoodProperties.Builder().nutrition(2).saturationModifier(0.0F).alwaysEdible().build();

		public static final FoodProperties PIKE = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).build();
		public static final FoodProperties COOKED_PIKE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build();
		public static final FoodProperties LIONFISH = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 3), 1.0F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 2), 1.0F).build();
		public static final FoodProperties COOKED_LIONFISH = new FoodProperties.Builder().nutrition(6).saturationModifier(0.7F).build();
		public static final FoodProperties PERCH = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).build();
		public static final FoodProperties COOKED_PERCH = new FoodProperties.Builder().nutrition(5).saturationModifier(0.7F).build();
	}
}