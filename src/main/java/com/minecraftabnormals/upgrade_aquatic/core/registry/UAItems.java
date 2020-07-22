package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;
import com.minecraftabnormals.upgrade_aquatic.common.items.ItemBoiledPickerelweed;
import com.minecraftabnormals.upgrade_aquatic.common.items.ItemJellyfishBucket;
import com.minecraftabnormals.upgrade_aquatic.common.items.ItemMulberryJamBottle;
import com.minecraftabnormals.upgrade_aquatic.common.items.ItemPikeBucket;
import com.minecraftabnormals.upgrade_aquatic.common.items.ItemPrismarineRod;
import com.minecraftabnormals.upgrade_aquatic.common.items.ItemSquidBucket;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.registry.other.UAFoods;
import com.minecraftabnormals.upgrade_aquatic.core.registry.util.UARegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.core.util.Reference;
import com.teamabnormals.abnormals_core.common.items.MobBucketItem;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAItems {
	public static final UARegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER;
	
	public static final RegistryObject<Item> DRIFTWOOD_BOAT             = HELPER.createBoatItem("driftwood", UABlocks.DRIFTWOOD_PLANKS);
	public static final RegistryObject<Item> RIVER_BOAT             	= HELPER.createBoatItem("river", UABlocks.RIVER_PLANKS);
	public static final RegistryObject<Item> NAUTILUS_BUCKET            = HELPER.createItem("nautilus_bucket", () -> new MobBucketItem(() -> UAEntities.NAUTILUS.get(), () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PIKE_BUCKET                = HELPER.createItem("pike_bucket", () -> new ItemPikeBucket(() -> UAEntities.PIKE.get(), () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> LIONFISH_BUCKET            = HELPER.createItem("lionfish_bucket", () -> new MobBucketItem(() -> UAEntities.LIONFISH.get(), () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> SQUID_BUCKET               = HELPER.createItem("squid_bucket", () -> new ItemSquidBucket(() -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> JELLYFISH_BUCKET           = HELPER.createItem("jellyfish_bucket", () -> new ItemJellyfishBucket(() -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PRISMARINE_ROD             = HELPER.createItem("prismarine_rod", () -> new ItemPrismarineRod(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> THRASHER_TOOTH             = HELPER.createItem("thrasher_tooth", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOILED_BLUE_PICKERELWEED   = HELPER.createItem("boiled_pickerelweed_blue", () -> new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(false)), false));
	public static final RegistryObject<Item> BOILED_PURPLE_PICKERELWEED = HELPER.createItem("boiled_pickerelweed_purple", () -> new ItemBoiledPickerelweed(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PICKERELWEED(true)), true));
	public static final RegistryObject<Item> PIKE                       = HELPER.createItem("pike", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(false))));
	public static final RegistryObject<Item> COOKED_PIKE                = HELPER.createItem("pike_cooked", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.PIKE(true))));
	public static final RegistryObject<Item> LIONFISH                   = HELPER.createItem("lionfish", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(false))));
	public static final RegistryObject<Item> COOKED_LIONFISH            = HELPER.createItem("lionfish_cooked", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.LIONFISH(true))));

	public static final RegistryObject<Item> MULBERRY            		= HELPER.createItem("mulberry", () -> new BlockNamedItem(UABlocks.MULBERRY_VINE.get(), new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.MULBERRY)));
	public static final RegistryObject<Item> MULBERRY_JAM_BOTTLE        = HELPER.createItem("mulberry_jam_bottle", () -> new ItemMulberryJamBottle(new Item.Properties().group(ItemGroup.FOOD).containerItem(Items.GLASS_BOTTLE).food(UAFoods.MULBERRY_JAM)));
	public static final RegistryObject<Item> MULBERRY_BREAD            	= HELPER.createItem("mulberry_bread", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(UAFoods.MULBERRY_BREAD)));

	public static final RegistryObject<Item> NAUTILUS_SPAWN_EGG         = HELPER.createSpawnEggItem("nautilus", () -> UAEntities.NAUTILUS.get(), 14596231, 16744272);
	public static final RegistryObject<Item> PIKE_SPAWN_EGG             = HELPER.createSpawnEggItem("pike", () -> UAEntities.PIKE.get(), 4806944, 13002040);
	public static final RegistryObject<Item> LIONFISH_SPAWN_EGG         = HELPER.createSpawnEggItem("lionfish", () -> UAEntities.LIONFISH.get(), 15281931, 16111310);
	public static final RegistryObject<Item> THRASHER_SPAWN_EGG         = HELPER.createSpawnEggItem("thrasher", () -> UAEntities.THRASHER.get(), 7255507, 11730927);
	public static final RegistryObject<Item> GREAT_THRASHER_SPAWN_EGG   = HELPER.createSpawnEggItem("great_thrasher", () -> UAEntities.GREAT_THRASHER.get(), 10078409, 13294289);
	public static final RegistryObject<Item> FLARE_SPAWN_EGG            = HELPER.createSpawnEggItem("flare", () -> UAEntities.FLARE.get(), 4532619, 14494960);
	public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG        = HELPER.createJellyfishSpawnEggItem("jellyfish", 3911164, 16019855);
//	public static final RegistryObject<Item> GOOSE_SPAWN_EGG        	= HELPER.createSpawnEggItem("goose", () -> UAEntities.GOOSE.get(), 16448255, 16751914);
	
	public static void setupItemPropertes() {
		ItemModelsProperties.func_239418_a_(JELLYFISH_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
				AbstractEntityJellyfish jellyfish = ((ItemJellyfishBucket) stack.getItem()).getEntityInStack(stack, world, null);
				return (float) JellyfishRegistry.IDS.get(jellyfish.getClass()) + (0.1F * (float) jellyfish.getIdSuffix());
			}
			return 0.0F;
		});
		
		ItemModelsProperties.func_239418_a_(PIKE_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
				return compoundnbt.getInt("BucketVariantTag");
			}
			return 2;
		});
	}
}