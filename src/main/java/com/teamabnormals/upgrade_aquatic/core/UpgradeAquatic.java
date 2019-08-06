package com.teamabnormals.upgrade_aquatic.core;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.upgrade_aquatic.api.util.EntityUtil;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityBedroll;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.common.world.UAWorldGen;
import com.teamabnormals.upgrade_aquatic.core.config.Config;
import com.teamabnormals.upgrade_aquatic.core.config.ConfigHelper;
import com.teamabnormals.upgrade_aquatic.core.proxy.ClientProxy;
import com.teamabnormals.upgrade_aquatic.core.proxy.ServerProxy;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEffects;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UACompostables;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UADispenseBehaviorRegistry;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ConduitTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(value = Reference.MODID)
public class UpgradeAquatic {
	public static UpgradeAquatic instance;
	public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	public static final String NETWORK_PROTOCOL = "1";
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Reference.MODID, "net"))
		.networkProtocolVersion(() -> NETWORK_PROTOCOL)
		.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
		.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
		.simpleChannel();
	
	public UpgradeAquatic() {
		instance = this;
		
		this.setupMessages();
		
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(TileEntityType.class, this::registerTileEntities);
		
		modEventBus.addListener((ModConfig.ModConfigEvent event) -> {
			final ModConfig config = event.getConfig();
			if (config.getSpec() == Config.CLIENTSPEC) {
				ConfigHelper.updateClientConfig(config);
			}
		});
		
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, Config.CLIENTSPEC);
	}
	
	private void setupCommon(final FMLCommonSetupEvent event) {
		proxy.preInit();
		EntityNautilus.addSpawn();
		EntityPike.addSpawn();
		EntityLionfish.addSpawn();
		UADispenseBehaviorRegistry.registerAll();
		UAEffects.registerRecipes();
		UAWorldGen.registerGenerators();
		UACompostables.registerCompostables();
		this.changeVanillaFields();
	}

	@SuppressWarnings("unused")
	private void Init(final FMLCommonSetupEvent event) {}
	
	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register(UATileEntities.ELDER_EYE = (TileEntityType<TileEntityElderEye>) TileEntityType.Builder.create(TileEntityElderEye::new, UABlocks.ELDER_EYE).build(null).setRegistryName(Reference.MODID, "elder_eye"));
		event.getRegistry().register(UATileEntities.BEDROLL = (TileEntityType<TileEntityBedroll>) TileEntityType.Builder.create(TileEntityBedroll::new, UABlocks.BEDROLL_WHITE).build(null).setRegistryName(Reference.MODID, "bedroll"));
	}
	
	void setupMessages() {}
	
	void changeVanillaFields() {
		ConduitTileEntity.field_205042_e = ArrayUtils.addAll(ConduitTileEntity.field_205042_e,
			UABlocks.PRISMARINE_CORAL, UABlocks.PRISMARINE_CORAL_BLOCK, UABlocks.PRISMARINE_CORAL_FAN, UABlocks.PRISMARINE_CORAL_SHOWER, UABlocks.PRISMARINE_CORAL_WALL_FAN,
			UABlocks.ELDER_PRISMARINE_CORAL, UABlocks.ELDER_PRISMARINE_CORAL_BLOCK, UABlocks.ELDER_PRISMARINE_CORAL_FAN, UABlocks.ELDER_PRISMARINE_CORAL_SHOWER, UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN,
			Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE_WALL, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_STAIRS,
			Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICK_STAIRS
		);
		/*
		 * Override of Wandering Trader Trades
		 */
		VillagerTrades.field_221240_b = EntityUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[] {
				new EntityUtil.ItemsForEmeraldsTrade(Items.SEA_PICKLE, 2, 1, 5, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.SLIME_BALL, 4, 1, 5, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.GLOWSTONE, 2, 1, 5, 1), 
				new EntityUtil.ItemsForEmeraldsTrade(Items.FERN, 1, 1, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.SUGAR_CANE, 1, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SEAROCKET_WHITE, 1, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SEAROCKET_PINK, 1, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PICKERELWEED_BLUE, 1, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PICKERELWEED_PURPLE, 1, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.PUMPKIN, 1, 1, 4, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.KELP, 3, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.CACTUS, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.DANDELION, 1, 1, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.POPPY, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BLUE_ORCHID, 1, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.ALLIUM, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.AZURE_BLUET, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.RED_TULIP, 1, 1, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.ORANGE_TULIP, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.WHITE_TULIP, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.PINK_TULIP, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.OXEYE_DAISY, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.CORNFLOWER, 1, 1, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.LILY_OF_THE_VALLEY, 1, 1, 7, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.WHEAT_SEEDS, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BEETROOT_SEEDS, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.PUMPKIN_SEEDS, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.MELON_SEEDS, 1, 1, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.ACACIA_SAPLING, 5, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BIRCH_SAPLING, 5, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.DARK_OAK_SAPLING, 5, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.JUNGLE_SAPLING, 5, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.OAK_SAPLING, 5, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.SPRUCE_SAPLING, 5, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.RED_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.WHITE_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BLUE_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.PINK_DYE, 1, 3, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.BLACK_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.GREEN_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.LIGHT_GRAY_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.MAGENTA_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.YELLOW_DYE, 1, 3, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.GRAY_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.PURPLE_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.LIGHT_BLUE_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.LIME_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.ORANGE_DYE, 1, 3, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.BROWN_DYE, 1, 3, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.CYAN_DYE, 1, 3, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.VINE, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BROWN_MUSHROOM, 1, 1, 12, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.RED_MUSHROOM, 1, 1, 12, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.PACKED_ICE, 3, 1, 6, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BLUE_ICE, 6, 1, 6, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.GUNPOWDER, 1, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.PODZOL, 3, 3, 6, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.BRAIN_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.BUBBLE_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.FIRE_CORAL_BLOCK, 3, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(UABlocks.FINGER_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.ACAN_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.BRANCH_CORAL_BLOCK, 3, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(UABlocks.MOSS_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PETAL_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.PILLOW_CORAL_BLOCK, 3, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SILK_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.SILK_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(UABlocks.ROCK_CORAL_BLOCK, 3, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.HORN_CORAL_BLOCK, 3, 1, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.TUBE_CORAL_BLOCK, 3, 1, 8, 1),
				new EntityUtil.ItemsForEmeraldsTrade(Items.LILY_PAD, 1, 2, 5, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.SAND, 1, 8, 8, 1), new EntityUtil.ItemsForEmeraldsTrade(Items.RED_SAND, 1, 4, 6, 1)}, 2,
				new VillagerTrades.ITrade[] {
					new EntityUtil.ItemsForEmeraldsTrade(Items.TROPICAL_FISH_BUCKET, 5, 1, 4, 1),
					new EntityUtil.ItemsForEmeraldsTrade(UAItems.PIKE_BUCKET, 5, 1, 4, 1),
					new EntityUtil.ItemsForEmeraldsTrade(UAItems.LIONFISH_BUCKET, 5, 1, 4, 1),
					new EntityUtil.ItemsForEmeraldsTrade(Items.PUFFERFISH_BUCKET, 5, 1, 4, 1),
					new EntityUtil.ItemsForEmeraldsTrade(Items.NAUTILUS_SHELL, 5, 1, 5, 1),
					new EntityUtil.ItemsForEmeraldsTrade(UAItems.NAUTILUS_BUCKET, 5, 1, 4, 1)
			})
		);
	}
}
