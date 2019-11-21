package com.teamabnormals.upgrade_aquatic.core;

import org.apache.commons.lang3.ArrayUtils;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.common.world.UAWorldGen;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
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
import net.minecraft.tileentity.ConduitTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
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
		
		modEventBus.addListener(this::setupCommon);

		UAItems.ITEMS.register(modEventBus);
		UATileEntities.TILE_ENTITY_TYPES.register(modEventBus);
		UAFeatures.FEATURES.register(modEventBus);
		
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
	
	void setupMessages() {}
	
	void changeVanillaFields() {
		ConduitTileEntity.field_205042_e = ArrayUtils.addAll(ConduitTileEntity.field_205042_e,
			UABlocks.PRISMARINE_CORAL, UABlocks.PRISMARINE_CORAL_BLOCK, UABlocks.PRISMARINE_CORAL_FAN, UABlocks.PRISMARINE_CORAL_SHOWER, UABlocks.PRISMARINE_CORAL_WALL_FAN, UABlocks.PRISMARINE_ROD_BUNDLE,
			UABlocks.ELDER_PRISMARINE_CORAL, UABlocks.ELDER_PRISMARINE_CORAL_BLOCK, UABlocks.ELDER_PRISMARINE_CORAL_FAN, UABlocks.ELDER_PRISMARINE_CORAL_SHOWER, UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN,
			Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE_WALL, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_SLAB, Blocks.DARK_PRISMARINE_STAIRS,
			Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_BRICK_STAIRS
		);
	}
}
