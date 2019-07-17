package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCSetRestTime;
import com.teamabnormals.upgrade_aquatic.common.tileentities.*;
import com.teamabnormals.upgrade_aquatic.common.world.UAWorldGen;
import com.teamabnormals.upgrade_aquatic.core.proxy.*;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UADispenseBehaviorRegistry;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
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
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(TileEntityType.class, this::registerTileEntities);
	}
	
	private void preInit(final FMLCommonSetupEvent event) {
		proxy.preInit();
		EntityNautilus.addSpawn();
		UADispenseBehaviorRegistry.registerAll();
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
