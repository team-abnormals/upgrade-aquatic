package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.items.*;
import com.teamabnormals.upgrade_aquatic.common.item.*;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.common.item.BlueprintSpawnEggItem;
import com.teamabnormals.blueprint.common.item.WaterAnimalBucketItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAItems {
	public static final UAItemSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> DRIFTWOOD_BOAT = HELPER.createBoatItem("driftwood", UABlocks.DRIFTWOOD_PLANKS);
	public static final RegistryObject<Item> RIVER_BOAT = HELPER.createBoatItem("river", UABlocks.RIVER_PLANKS);

	public static final RegistryObject<Item> NAUTILUS_BUCKET = HELPER.createItem("nautilus_bucket", () -> new WaterAnimalBucketItem(UAEntities.NAUTILUS::get, () -> Fluids.WATER, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> PIKE_BUCKET = HELPER.createItem("pike_bucket", () -> new PikeBucketItem(() -> Fluids.WATER, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> LIONFISH_BUCKET = HELPER.createItem("lionfish_bucket", () -> new WaterAnimalBucketItem(UAEntities.LIONFISH::get, () -> Fluids.WATER, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> PERCH_BUCKET = HELPER.createItem("perch_bucket", () -> new MobBucketItem(UAEntities.PERCH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> SQUID_BUCKET = HELPER.createItem("squid_bucket", () -> new SquidBucketItem(() -> Fluids.WATER, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> GLOW_SQUID_BUCKET = HELPER.createItem("glow_squid_bucket", () -> new GlowSquidBucketItem(() -> Fluids.WATER, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
	public static final RegistryObject<Item> JELLYFISH_BUCKET = HELPER.createItem("jellyfish_bucket", () -> new JellyfishBucketItem(() -> Fluids.WATER, new Item.Properties().stacksTo(1))); // Disabled temporarily -> .group(ItemGroup.MISC)

	public static final RegistryObject<Item> PRISMARINE_ROD = HELPER.createItem("prismarine_rod", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> THRASHER_TOOTH = HELPER.createItem("thrasher_tooth", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> GLOWING_INK_SAC = HELPER.createItem("glowing_ink_sac", () -> new GlowingInkItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> BOILED_BLUE_PICKERELWEED = HELPER.createItem("boiled_blue_pickerelweed", () -> new BoiledPickerelweedItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.BLUE_PICKERELWEED), false));
	public static final RegistryObject<Item> BOILED_PURPLE_PICKERELWEED = HELPER.createItem("boiled_purple_pickerelweed", () -> new BoiledPickerelweedItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.PURPLE_PICKERELWEED), true));

	public static final RegistryObject<Item> PIKE = HELPER.createItem("pike", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.PIKE)));
	public static final RegistryObject<Item> COOKED_PIKE = HELPER.createItem("cooked_pike", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.COOKED_PIKE)));
	public static final RegistryObject<Item> LIONFISH = HELPER.createItem("lionfish", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.LIONFISH)));
	public static final RegistryObject<Item> COOKED_LIONFISH = HELPER.createItem("cooked_lionfish", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.COOKED_LIONFISH)));
	public static final RegistryObject<Item> PERCH = HELPER.createItem("perch", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.PERCH)));
	public static final RegistryObject<Item> COOKED_PERCH = HELPER.createItem("cooked_perch", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.COOKED_PERCH)));

	public static final RegistryObject<Item> MULBERRY = HELPER.createItem("mulberry", () -> new ItemNameBlockItem(UABlocks.MULBERRY_VINE.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.MULBERRY)));
	public static final RegistryObject<Item> MULBERRY_JAM_BOTTLE = HELPER.createItem("mulberry_jam_bottle", () -> new MulberryJamBottleItem(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(Foods.MULBERRY_JAM)));
	public static final RegistryObject<Item> MULBERRY_BREAD = HELPER.createItem("mulberry_bread", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.MULBERRY_BREAD)));
	public static final RegistryObject<Item> MULBERRY_PIE = HELPER.createItem("mulberry_pie", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.MULBERRY_PIE)));

	public static final RegistryObject<BlueprintSpawnEggItem> NAUTILUS_SPAWN_EGG = HELPER.createSpawnEggItem("nautilus", UAEntities.NAUTILUS::get, 14596231, 16744272);
	public static final RegistryObject<BlueprintSpawnEggItem> PIKE_SPAWN_EGG = HELPER.createSpawnEggItem("pike", UAEntities.PIKE::get, 4806944, 13002040);
	public static final RegistryObject<BlueprintSpawnEggItem> LIONFISH_SPAWN_EGG = HELPER.createSpawnEggItem("lionfish", UAEntities.LIONFISH::get, 15281931, 16111310);
	public static final RegistryObject<BlueprintSpawnEggItem> PERCH_SPAWN_EGG = HELPER.createSpawnEggItem("perch", UAEntities.PERCH::get, 7764021, 12555079);
	public static final RegistryObject<BlueprintSpawnEggItem> THRASHER_SPAWN_EGG = HELPER.createSpawnEggItem("thrasher", UAEntities.THRASHER::get, 7255507, 11730927);
	public static final RegistryObject<BlueprintSpawnEggItem> GREAT_THRASHER_SPAWN_EGG = HELPER.createSpawnEggItem("great_thrasher", UAEntities.GREAT_THRASHER::get, 10078409, 13294289);
	public static final RegistryObject<BlueprintSpawnEggItem> FLARE_SPAWN_EGG = HELPER.createSpawnEggItem("flare", UAEntities.FLARE::get, 4532619, 14494960);
	public static final RegistryObject<JellyfishSpawnEggItem> JELLYFISH_SPAWN_EGG = HELPER.createJellyfishSpawnEggItem("jellyfish", 3911164, 16019855);
	public static final RegistryObject<BlueprintSpawnEggItem> GLOW_SQUID_SPAWN_EGG = HELPER.createSpawnEggItem("glow_squid", UAEntities.GLOW_SQUID::get, 0x358080, 0x42D7A5);
	public static final RegistryObject<BlueprintSpawnEggItem> GOOSE_SPAWN_EGG = HELPER.createSpawnEggItem("goose", UAEntities.GOOSE::get, 16448255, 16751914);

	public static class Foods {
		public static final FoodProperties MULBERRY = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build();
		public static final FoodProperties MULBERRY_JAM = new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).alwaysEat().build();
		public static final FoodProperties MULBERRY_BREAD = new FoodProperties.Builder().nutrition(9).saturationMod(0.4F).build();
		public static final FoodProperties MULBERRY_PIE = new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).build();

		public static final FoodProperties PURPLE_PICKERELWEED = new FoodProperties.Builder().nutrition(3).saturationMod(0.0F).alwaysEat().build();
		public static final FoodProperties BLUE_PICKERELWEED = new FoodProperties.Builder().nutrition(2).saturationMod(0.0F).alwaysEat().build();

		public static final FoodProperties PIKE = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build();
		public static final FoodProperties COOKED_PIKE = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build();
		public static final FoodProperties LIONFISH = new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.POISON, 550, 3), 1.0F).effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 320, 2), 1.0F).nutrition(2).saturationMod(0.3F).build();
		public static final FoodProperties COOKED_LIONFISH = new FoodProperties.Builder().nutrition(6).saturationMod(0.7F).build();
		public static final FoodProperties PERCH = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build();
		public static final FoodProperties COOKED_PERCH = new FoodProperties.Builder().nutrition(5).saturationMod(0.7F).build();
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerItemProperties() {
		ItemProperties.register(JELLYFISH_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity, num) -> {
			if (world == null) {
				world = (ClientLevel) ClientInfo.getClientPlayerLevel();
			}

			CompoundTag compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
				AbstractJellyfishEntity jellyfish = ((JellyfishBucketItem) stack.getItem()).getEntityInStack(stack, world, null);
				if (jellyfish != null) {
					return (float) JellyfishRegistry.IDS.get(jellyfish.getClass()) + (0.1F * (float) jellyfish.getIdSuffix());
				}
			}
			return 0.0F;
		});

		ItemProperties.register(PIKE_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity, num) -> {
			CompoundTag compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
				return compoundnbt.getInt("BucketVariantTag");
			}
			return 2;
		});
	}
}