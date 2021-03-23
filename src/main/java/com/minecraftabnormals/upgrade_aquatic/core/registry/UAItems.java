package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.abnormals_core.client.ClientInfo;
import com.minecraftabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;
import com.minecraftabnormals.abnormals_core.common.items.MobBucketItem;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.items.*;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAItems {
	public static final UAItemSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getItemSubHelper();
	
	public static final RegistryObject<Item> DRIFTWOOD_BOAT             = HELPER.createBoatItem("driftwood", UABlocks.DRIFTWOOD_PLANKS);
	public static final RegistryObject<Item> RIVER_BOAT             	= HELPER.createBoatItem("river", UABlocks.RIVER_PLANKS);
	
	public static final RegistryObject<Item> NAUTILUS_BUCKET            = HELPER.createItem("nautilus_bucket", () -> new MobBucketItem(UAEntities.NAUTILUS::get, () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PIKE_BUCKET                = HELPER.createItem("pike_bucket", () -> new PikeBucketItem(() -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> LIONFISH_BUCKET            = HELPER.createItem("lionfish_bucket", () -> new MobBucketItem(UAEntities.LIONFISH::get, () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> PERCH_BUCKET               = HELPER.createItem("perch_bucket", () -> new FishBucketItem(UAEntities.PERCH, () -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> SQUID_BUCKET               = HELPER.createItem("squid_bucket", () -> new SquidBucketItem(() -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> GLOW_SQUID_BUCKET          = HELPER.createItem("glow_squid_bucket", () -> new GlowSquidBucketItem(() -> Fluids.WATER, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)));
	public static final RegistryObject<Item> JELLYFISH_BUCKET           = HELPER.createItem("jellyfish_bucket", () -> new JellyfishBucketItem(() -> Fluids.WATER, new Item.Properties().maxStackSize(1))); // Disabled temporarily -> .group(ItemGroup.MISC)
	
	public static final RegistryObject<Item> PRISMARINE_ROD             = HELPER.createItem("prismarine_rod", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> THRASHER_TOOTH             = HELPER.createItem("thrasher_tooth", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> GLOWING_INK_SAC            = HELPER.createItem("glowing_ink_sac", () -> new GlowingInkItem(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BOILED_BLUE_PICKERELWEED   = HELPER.createItem("boiled_blue_pickerelweed", () -> new BoiledPickerelweedItem(new Item.Properties().group(ItemGroup.FOOD).food(Foods.BLUE_PICKERELWEED), false));
	public static final RegistryObject<Item> BOILED_PURPLE_PICKERELWEED = HELPER.createItem("boiled_purple_pickerelweed", () -> new BoiledPickerelweedItem(new Item.Properties().group(ItemGroup.FOOD).food(Foods.PURPLE_PICKERELWEED), true));
	
	public static final RegistryObject<Item> PIKE                       = HELPER.createItem("pike", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.PIKE)));
	public static final RegistryObject<Item> COOKED_PIKE                = HELPER.createItem("cooked_pike", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.COOKED_PIKE)));
	public static final RegistryObject<Item> LIONFISH                   = HELPER.createItem("lionfish", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.LIONFISH)));
	public static final RegistryObject<Item> COOKED_LIONFISH            = HELPER.createItem("cooked_lionfish", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.COOKED_LIONFISH)));
	public static final RegistryObject<Item> PERCH                      = HELPER.createItem("perch", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.PERCH)));
	public static final RegistryObject<Item> COOKED_PERCH               = HELPER.createItem("cooked_perch", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.COOKED_PERCH)));

	public static final RegistryObject<Item> MULBERRY            		= HELPER.createItem("mulberry", () -> new BlockNamedItem(UABlocks.MULBERRY_VINE.get(), new Item.Properties().group(ItemGroup.FOOD).food(Foods.MULBERRY)));
	public static final RegistryObject<Item> MULBERRY_JAM_BOTTLE        = HELPER.createItem("mulberry_jam_bottle", () -> new MulberryJamBottleItem(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(16).containerItem(Items.GLASS_BOTTLE).food(Foods.MULBERRY_JAM)));
	public static final RegistryObject<Item> MULBERRY_BREAD            	= HELPER.createItem("mulberry_bread", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.MULBERRY_BREAD)));
    public static final RegistryObject<Item> MULBERRY_PIE               = HELPER.createItem("mulberry_pie", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.MULBERRY_PIE)));

	public static final RegistryObject<AbnormalsSpawnEggItem> NAUTILUS_SPAWN_EGG        = HELPER.createSpawnEggItem("nautilus", UAEntities.NAUTILUS::get, 14596231, 16744272);
	public static final RegistryObject<AbnormalsSpawnEggItem> PIKE_SPAWN_EGG            = HELPER.createSpawnEggItem("pike", UAEntities.PIKE::get, 4806944, 13002040);
	public static final RegistryObject<AbnormalsSpawnEggItem> LIONFISH_SPAWN_EGG        = HELPER.createSpawnEggItem("lionfish", UAEntities.LIONFISH::get, 15281931, 16111310);
	public static final RegistryObject<AbnormalsSpawnEggItem> PERCH_SPAWN_EGG           = HELPER.createSpawnEggItem("perch", UAEntities.PERCH::get, 7764021, 12555079);
	public static final RegistryObject<AbnormalsSpawnEggItem> THRASHER_SPAWN_EGG        = HELPER.createSpawnEggItem("thrasher", UAEntities.THRASHER::get, 7255507, 11730927);
	public static final RegistryObject<AbnormalsSpawnEggItem> GREAT_THRASHER_SPAWN_EGG  = HELPER.createSpawnEggItem("great_thrasher", UAEntities.GREAT_THRASHER::get, 10078409, 13294289);
	public static final RegistryObject<AbnormalsSpawnEggItem> FLARE_SPAWN_EGG           = HELPER.createSpawnEggItem("flare", UAEntities.FLARE::get, 4532619, 14494960);
	public static final RegistryObject<JellyfishSpawnEggItem> JELLYFISH_SPAWN_EGG       = HELPER.createJellyfishSpawnEggItem("jellyfish", 3911164, 16019855);
	public static final RegistryObject<AbnormalsSpawnEggItem> GLOW_SQUID_SPAWN_EGG 		= HELPER.createSpawnEggItem("glow_squid", UAEntities.GLOW_SQUID::get, 0x358080, 0x42D7A5);
	public static final RegistryObject<AbnormalsSpawnEggItem> GOOSE_SPAWN_EGG        	= HELPER.createSpawnEggItem("goose", UAEntities.GOOSE::get, 16448255, 16751914);

	public static class Foods {
		public static final Food MULBERRY = new Food.Builder().hunger(3).saturation(0.1F).build();
		public static final Food MULBERRY_JAM = new Food.Builder().hunger(4).saturation(0.2F).setAlwaysEdible().build();
		public static final Food MULBERRY_BREAD = new Food.Builder().hunger(9).saturation(0.4F).build();
		public static final Food MULBERRY_PIE = new Food.Builder().hunger(7).saturation(0.6F).build();

		public static final Food PURPLE_PICKERELWEED = new Food.Builder().hunger(3).saturation(0.0F).setAlwaysEdible().build();
		public static final Food BLUE_PICKERELWEED = new Food.Builder().hunger(2).saturation(0.0F).setAlwaysEdible().build();

		public static final Food PIKE = new Food.Builder().hunger(3).saturation(0.3F).build();
		public static final Food COOKED_PIKE = new Food.Builder().hunger(8).saturation(0.8F).build();
		public static final Food LIONFISH = new Food.Builder().effect(() -> new EffectInstance(Effects.POISON, 550, 3), 1.0F).effect(() -> new EffectInstance(Effects.NAUSEA, 320, 2), 1.0F).hunger(2).saturation(0.3F).build();
		public static final Food COOKED_LIONFISH = new Food.Builder().hunger(6).saturation(0.7F).build();
		public static final Food PERCH = new Food.Builder().hunger(2).saturation(0.2F).build();
		public static final Food COOKED_PERCH = new Food.Builder().hunger(5).saturation(0.7F).build();
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerItemProperties() {
		ItemModelsProperties.registerProperty(JELLYFISH_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity) -> {
			if (world == null) {
				world = (ClientWorld) ClientInfo.getClientPlayerWorld();
			}
			
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("JellyfishTag")) {
				AbstractJellyfishEntity jellyfish = ((JellyfishBucketItem) stack.getItem()).getEntityInStack(stack, world, null);
				if (jellyfish != null) {
					return (float) JellyfishRegistry.IDS.get(jellyfish.getClass()) + (0.1F * (float) jellyfish.getIdSuffix());
				}
			}
			return 0.0F;
		});
		
		ItemModelsProperties.registerProperty(PIKE_BUCKET.get(), new ResourceLocation("variant"), (stack, world, entity) -> {
			CompoundNBT compoundnbt = stack.getTag();
			if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
				return compoundnbt.getInt("BucketVariantTag");
			}
			return 2;
		});
	}
}