package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCSetRestTime;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityBedroll;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.common.world.UAWorldGen;
import com.teamabnormals.upgrade_aquatic.core.config.Config;
import com.teamabnormals.upgrade_aquatic.core.config.ConfigHelper;
import com.teamabnormals.upgrade_aquatic.core.proxy.ClientProxy;
import com.teamabnormals.upgrade_aquatic.core.proxy.ServerProxy;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEffects;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UADispenseBehaviorRegistry;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

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
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		this.setupMessages();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
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
	
	private void preInit(final FMLCommonSetupEvent event) {
		proxy.preInit();
		EntityNautilus.addSpawn();
		UADispenseBehaviorRegistry.registerAll();
		UAEffects.registerRecipes();
		UAWorldGen.registerGenerators();
	}

	@SuppressWarnings("unused")
	private void Init(final FMLCommonSetupEvent event) {}
	
	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register(UATileEntities.ELDER_EYE = (TileEntityType<TileEntityElderEye>) TileEntityType.Builder.create(TileEntityElderEye::new, UABlocks.ELDER_EYE).build(null).setRegistryName(Reference.MODID, "elder_eye"));
		event.getRegistry().register(UATileEntities.BEDROLL = (TileEntityType<TileEntityBedroll>) TileEntityType.Builder.create(TileEntityBedroll::new, UABlocks.BEDROLL_WHITE).build(null).setRegistryName(Reference.MODID, "bedroll"));
	}
	
	void setupMessages() {
    	CHANNEL.messageBuilder(MessageCSetRestTime.class, 0)
    	.encoder(MessageCSetRestTime::serialize).decoder(MessageCSetRestTime::deserialize)
    	.consumer(MessageCSetRestTime::handle)
    	.add();
	}
}
