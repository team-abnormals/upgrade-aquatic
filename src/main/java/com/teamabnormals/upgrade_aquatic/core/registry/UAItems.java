package com.teamabnormals.upgrade_aquatic.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.common.item.*;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.integration.boatload.UABoatTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Predicate;

import static com.teamabnormals.blueprint.core.util.item.ItemStackUtil.is;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UAItems {
	public static final UAItemSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getItemSubHelper();

	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> DRIFTWOOD_BOAT = HELPER.createBoatAndChestBoatItem("driftwood", UABlocks.DRIFTWOOD_PLANKS, false);
	public static final RegistryObject<Item> DRIFTWOOD_FURNACE_BOAT = HELPER.createItem("driftwood_furnace_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.DRIFTWOOD_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_DRIFTWOOD_BOAT = HELPER.createItem("large_driftwood_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.LARGE_DRIFTWOOD_BOAT : () -> new Item(new Item.Properties()));
	public static final Pair<RegistryObject<Item>, RegistryObject<Item>> RIVER_BOAT = HELPER.createBoatAndChestBoatItem("river", UABlocks.RIVER_PLANKS, false);
	public static final RegistryObject<Item> RIVER_FURNACE_BOAT = HELPER.createItem("river_furnace_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.RIVER_FURNACE_BOAT : () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> LARGE_RIVER_BOAT = HELPER.createItem("large_river_boat", ModList.get().isLoaded("boatload") ? UABoatTypes.LARGE_RIVER_BOAT : () -> new Item(new Item.Properties()));

	public static final RegistryObject<Item> NAUTILUS_BUCKET = HELPER.createItem("nautilus_bucket", () -> new MobBucketItem(UAEntityTypes.NAUTILUS::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> PIKE_BUCKET = HELPER.createItem("pike_bucket", () -> new PikeBucketItem(new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> LIONFISH_BUCKET = HELPER.createItem("lionfish_bucket", () -> new MobBucketItem(UAEntityTypes.LIONFISH::get, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> PERCH_BUCKET = HELPER.createItem("perch_bucket", () -> new MobBucketItem(UAEntityTypes.PERCH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> SQUID_BUCKET = HELPER.createItem("squid_bucket", () -> new SquidBucketItem(() -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> GLOW_SQUID_BUCKET = HELPER.createItem("glow_squid_bucket", () -> new GlowSquidBucketItem(() -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> JELLYFISH_BUCKET = HELPER.createItem("jellyfish_bucket", () -> new JellyfishBucketItem(() -> Fluids.WATER, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> PRISMARINE_ROD = HELPER.createItem("prismarine_rod", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> THRASHER_TOOTH = HELPER.createItem("thrasher_tooth", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> BOILED_PICKERELWEED = HELPER.createItem("boiled_pickerelweed", () -> new BoiledPickerelweedItem(new Item.Properties().food(UAFoods.PICKERELWEED)));

	public static final RegistryObject<Item> PIKE = HELPER.createItem("pike", () -> new Item(new Item.Properties().food(UAFoods.PIKE)));
	public static final RegistryObject<Item> COOKED_PIKE = HELPER.createItem("cooked_pike", () -> new Item(new Item.Properties().food(UAFoods.COOKED_PIKE)));
	public static final RegistryObject<Item> LIONFISH = HELPER.createItem("lionfish", () -> new Item(new Item.Properties().food(UAFoods.LIONFISH)));
	public static final RegistryObject<Item> COOKED_LIONFISH = HELPER.createItem("cooked_lionfish", () -> new Item(new Item.Properties().food(UAFoods.COOKED_LIONFISH)));
	public static final RegistryObject<Item> PERCH = HELPER.createItem("perch", () -> new Item(new Item.Properties().food(UAFoods.PERCH)));
	public static final RegistryObject<Item> COOKED_PERCH = HELPER.createItem("cooked_perch", () -> new Item(new Item.Properties().food(UAFoods.COOKED_PERCH)));

	public static final RegistryObject<Item> MULBERRY = HELPER.createItem("mulberry", () -> new ItemNameBlockItem(UABlocks.MULBERRY_VINE.get(), new Item.Properties().food(UAFoods.MULBERRY)));
	public static final RegistryObject<Item> MULBERRY_JAM_BOTTLE = HELPER.createItem("mulberry_jam_bottle", () -> new MulberryJamBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(UAFoods.MULBERRY_JAM)));
	public static final RegistryObject<Item> MULBERRY_BREAD = HELPER.createItem("mulberry_bread", () -> new Item(new Item.Properties().food(UAFoods.MULBERRY_BREAD)));
	public static final RegistryObject<Item> MULBERRY_PIE = HELPER.createItem("mulberry_pie", () -> new Item(new Item.Properties().food(UAFoods.MULBERRY_PIE)));

	public static final RegistryObject<Item> MUSIC_DISC_ATLANTIS = HELPER.createItem("music_disc_atlantis", () -> new BlueprintRecordItem(14, UASoundEvents.MUSIC_DISC_ATLANTIS, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 114));
	public static final RegistryObject<Item> DISC_FRAGMENT_ATLANTIS = HELPER.createItem("disc_fragment_atlantis", () -> new DiscFragmentItem(new Item.Properties()));

	public static final RegistryObject<Item> PREDATOR_POTTERY_SHERD = HELPER.createItem("predator_pottery_sherd", () -> new Item(new Item.Properties()));

	public static final RegistryObject<ForgeSpawnEggItem> NAUTILUS_SPAWN_EGG = HELPER.createSpawnEggItem("nautilus", UAEntityTypes.NAUTILUS::get, 14596231, 16744272);
	public static final RegistryObject<ForgeSpawnEggItem> PIKE_SPAWN_EGG = HELPER.createSpawnEggItem("pike", UAEntityTypes.PIKE::get, 4806944, 13002040);
	public static final RegistryObject<ForgeSpawnEggItem> LIONFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lionfish", UAEntityTypes.LIONFISH::get, 15281931, 16111310);
	public static final RegistryObject<ForgeSpawnEggItem> PERCH_SPAWN_EGG = HELPER.createSpawnEggItem("perch", UAEntityTypes.PERCH::get, 7764021, 12555079);
	public static final RegistryObject<ForgeSpawnEggItem> THRASHER_SPAWN_EGG = HELPER.createSpawnEggItem("thrasher", UAEntityTypes.THRASHER::get, 7255507, 11730927);
	public static final RegistryObject<ForgeSpawnEggItem> GREAT_THRASHER_SPAWN_EGG = HELPER.createSpawnEggItem("great_thrasher", UAEntityTypes.GREAT_THRASHER::get, 10078409, 13294289);
	public static final RegistryObject<ForgeSpawnEggItem> FLARE_SPAWN_EGG = HELPER.createSpawnEggItem("flare", UAEntityTypes.FLARE::get, 4532619, 14494960);
	public static final RegistryObject<JellyfishSpawnEggItem> JELLYFISH_SPAWN_EGG = HELPER.createJellyfishSpawnEggItem("jellyfish", 3911164, 16019855);
	public static final RegistryObject<ForgeSpawnEggItem> GOOSE_SPAWN_EGG = HELPER.createSpawnEggItem("goose", UAEntityTypes.GOOSE::get, 16448255, 16751914);

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
				.addItemsAlphabetically(stack -> stack.is(ItemTags.DECORATED_POT_SHERDS), PREDATOR_POTTERY_SHERD)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsAfter(of(Items.SALMON_BUCKET), PIKE_BUCKET, PERCH_BUCKET, LIONFISH_BUCKET)
				.addItemsAfter(of(Items.AXOLOTL_BUCKET), NAUTILUS_BUCKET, SQUID_BUCKET, GLOW_SQUID_BUCKET)
				.addItemsBefore(of(Items.BAMBOO_RAFT), DRIFTWOOD_BOAT.getFirst(), DRIFTWOOD_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), DRIFTWOOD_FURNACE_BOAT, LARGE_DRIFTWOOD_BOAT)
				.addItemsBefore(of(Items.BAMBOO_RAFT), RIVER_BOAT.getFirst(), RIVER_BOAT.getSecond())
				.addItemsBefore(modLoaded(Items.BAMBOO_RAFT, "boatload"), RIVER_FURNACE_BOAT, LARGE_RIVER_BOAT)
				.addItemsAfter(of(Items.MUSIC_DISC_5), MUSIC_DISC_ATLANTIS)
				.tab(SPAWN_EGGS)
				.addItemsAlphabetically(is(SpawnEggItem.class), NAUTILUS_SPAWN_EGG, PIKE_SPAWN_EGG, LIONFISH_SPAWN_EGG, PERCH_SPAWN_EGG, THRASHER_SPAWN_EGG, GREAT_THRASHER_SPAWN_EGG);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, ItemLike fallback, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(ForgeRegistries.ITEMS.getValue(location)) : of(fallback)).test(stack);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(ForgeRegistries.ITEMS.getValue(location)).test(stack));
	}

	public static class UAFoods {
		public static final FoodProperties MULBERRY = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build();
		public static final FoodProperties MULBERRY_JAM = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).alwaysEat().build();
		public static final FoodProperties MULBERRY_BREAD = new FoodProperties.Builder().nutrition(9).saturationMod(0.4F).build();
		public static final FoodProperties MULBERRY_PIE = new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).build();

		public static final FoodProperties PICKERELWEED = new FoodProperties.Builder().nutrition(2).saturationMod(0.0F).alwaysEat().build();

		public static final FoodProperties PIKE = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).build();
		public static final FoodProperties COOKED_PIKE = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build();
		public static final FoodProperties LIONFISH = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 3), 1.0F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 2), 1.0F).build();
		public static final FoodProperties COOKED_LIONFISH = new FoodProperties.Builder().nutrition(6).saturationMod(0.7F).build();
		public static final FoodProperties PERCH = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).build();
		public static final FoodProperties COOKED_PERCH = new FoodProperties.Builder().nutrition(5).saturationMod(0.7F).build();
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerItemProperties() {
		ItemProperties.register(JELLYFISH_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.getTag();
			if (tag != null && tag.contains("JellyfishDisplayTag")) {
				return AbstractJellyfish.BucketDisplayInfo.readVariant(tag.getCompound("JellyfishDisplayTag"));
			}
			return 0.0F;
		});

		ItemProperties.register(PIKE_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.getTag();
			if (tag != null && tag.contains("BucketVariantTag", 3)) {
				return tag.getInt("BucketVariantTag");
			}
			return 2;
		});

		ItemProperties.register(Items.AXOLOTL_BUCKET, new ResourceLocation("variant"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.getTag();
			if (tag != null && tag.contains("Variant")) {
				return tag.getInt("Variant");
			}
			return 0;
		});
	}
}