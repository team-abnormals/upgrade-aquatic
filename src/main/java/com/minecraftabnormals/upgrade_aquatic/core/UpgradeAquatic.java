package com.minecraftabnormals.upgrade_aquatic.core;

import com.minecraftabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.client.render.FlareRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.GlowSquidRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.GooseRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.GreatThrasherRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.LionfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.NautilusRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.PikeRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.SonarWaveRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.ThrasherRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.UluluRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.BoxJellyfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.CassiopeaJellyfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.ImmortalJellyfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.tileentity.ElderEyeTileEntityRenderer;
import com.minecraftabnormals.upgrade_aquatic.common.network.RotateJellyfishMessage;
import com.minecraftabnormals.upgrade_aquatic.core.config.Config;
import com.minecraftabnormals.upgrade_aquatic.core.config.ConfigHelper;
import com.minecraftabnormals.upgrade_aquatic.core.other.UAColors;
import com.minecraftabnormals.upgrade_aquatic.core.other.UACompostables;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.minecraftabnormals.upgrade_aquatic.core.other.UADispenseBehaviorRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.other.UAEntitySpawns;
import com.minecraftabnormals.upgrade_aquatic.core.other.UAFlammables;
import com.minecraftabnormals.upgrade_aquatic.core.other.UARenderLayers;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEffects;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAFeatures;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAItems;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UATileEntities;
import com.minecraftabnormals.upgrade_aquatic.core.registry.util.UARegistryHelper;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(value = UpgradeAquatic.MODID)
public class UpgradeAquatic {
	public static UpgradeAquatic instance;
	public static final String NETWORK_PROTOCOL = "1";
	public static final String MODID = "upgrade_aquatic";
	public static final UARegistryHelper REGISTRY_HELPER = new UARegistryHelper(MODID);
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(UpgradeAquatic.MODID, "net"))
		.networkProtocolVersion(() -> NETWORK_PROTOCOL)
		.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
		.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
		.simpleChannel();
	
	public UpgradeAquatic() {
		instance = this;
		this.setupMessages();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setupCommon);

		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredTileEntityRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredEntityRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredSoundRegister().register(modEventBus);
		
		UAEffects.EFFECTS.register(modEventBus);
		UAEffects.POTIONS.register(modEventBus);
		UAFeatures.FEATURES.register(modEventBus);
		UAParticles.PARTICLES.register(modEventBus);
		UADataSerializers.SERIALIZERS.register(modEventBus);
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
			GlowSquidSpriteUploader.init(modEventBus);
		});
		
		modEventBus.addListener((ModConfig.ModConfigEvent event) -> {
			final ModConfig config = event.getConfig();
			if(config.getSpec() == Config.CLIENTSPEC) {
				ConfigHelper.updateClientConfig(config);
			}
		});
		
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, Config.CLIENTSPEC);
	}
	
	private void setupCommon(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			UAEntitySpawns.processSpawnAdditions();
			UAEntities.registerAttributes();
			UADispenseBehaviorRegistry.registerDispenseBehaviors();
			UAEffects.registerBrewingRecipes();
			UACompostables.registerCompostables();
			UAFlammables.registerFlammables();
			UAFeatures.setupGeneration();
			ObfuscationReflectionHelper.setPrivateValue(AbstractBlock.class, Blocks.BUBBLE_COLUMN, true, "field_149789_z");
		});
	}
	
	@OnlyIn(Dist.CLIENT)
	private void setupClient(final FMLClientSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			UAColors.registerBlockColors();
			UAItems.setupItemPropertes();
			UARenderLayers.setBlockRenderLayers();
		});

		//Tile Entities
		ClientRegistry.bindTileEntityRenderer(UATileEntities.ELDER_EYE.get(), ElderEyeTileEntityRenderer::new);

		//Entities
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.NAUTILUS.get(), NautilusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.PIKE.get(), PikeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.LIONFISH.get(), LionfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.THRASHER.get(), ThrasherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GREAT_THRASHER.get(), GreatThrasherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.FLARE.get(), FlareRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.SONAR_WAVE.get(), SonarWaveRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.ULULU.get(), UluluRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GOOSE.get(), GooseRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GLOW_SQUID.get(), GlowSquidRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(UAEntities.BOX_JELLYFISH.get(), BoxJellyfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.IMMORTAL_JELLYFISH.get(), ImmortalJellyfishRenderer::new);
	}
	
	/*
	 * Temporary fix for forge bug
	 */
	@OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		REGISTRY_HELPER.processSpawnEggColors(event);
	}
	
	void setupMessages() {
		int id = -1;
		
		CHANNEL.messageBuilder(RotateJellyfishMessage.class, id++)
		.encoder(RotateJellyfishMessage::serialize).decoder(RotateJellyfishMessage::deserialize)
		.consumer(RotateJellyfishMessage::handle)
		.add();
	}
}