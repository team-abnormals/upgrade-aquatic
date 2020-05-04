package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.client.render.*;
import com.teamabnormals.upgrade_aquatic.client.render.jellyfish.*;
import com.teamabnormals.upgrade_aquatic.client.tileentity.*;
import com.teamabnormals.upgrade_aquatic.common.items.UASpawnEggItem;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCAnimation;
import com.teamabnormals.upgrade_aquatic.common.network.MessageRotateJellyfish;
import com.teamabnormals.upgrade_aquatic.common.world.UAWorldGen;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.config.Config;
import com.teamabnormals.upgrade_aquatic.core.config.ConfigHelper;
import com.teamabnormals.upgrade_aquatic.core.registry.*;
import com.teamabnormals.upgrade_aquatic.core.registry.other.*;
import com.teamabnormals.upgrade_aquatic.core.registry.util.DataUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
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

@Mod(value = Reference.MODID)
public class UpgradeAquatic {
	public static UpgradeAquatic instance;
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

		UABlocks.BLOCKS.register(modEventBus);
		UAItems.ITEMS.register(modEventBus);
		UAEffects.EFFECTS.register(modEventBus);
		UAEffects.POTIONS.register(modEventBus);
		UATileEntities.TILE_ENTITY_TYPES.register(modEventBus);
		UAEntities.ENTITY_TYPES.register(modEventBus);
		UASounds.SOUNDS.register(modEventBus);
		UAFeatures.FEATURES.register(modEventBus);
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
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
		UAEntitySpawns.processSpawnAdditions();
		UADispenseBehaviorRegistry.registerDispenseBehaviors();
		UAEffects.registerBrewingRecipes();
		UAWorldGen.registerGenerators();
		UACompostables.registerCompostables();
		UAFlammables.registerFlammables();
		DataUtils.registerStrippable(UABlocks.DRIFTWOOD_LOG.get(), UABlocks.STRIPPED_DRIFTWOOD_LOG.get());
		DataUtils.registerStrippable(UABlocks.DRIFTWOOD.get(), UABlocks.STRIPPED_DRIFTWOOD.get());
		DataUtils.registerStrippable(UABlocks.RIVER_LOG.get(), UABlocks.STRIPPED_RIVER_LOG.get());
		DataUtils.registerStrippable(UABlocks.RIVER_WOOD.get(), UABlocks.STRIPPED_RIVER_WOOD.get());
		UAHooks.makeBubbleColumnTickRandomly();
	}
	
	@OnlyIn(Dist.CLIENT)
	private void setupClient(final FMLClientSetupEvent event) {
		UAColors.registerBlockColors();
		UARenderLayers.setBlockRenderLayers();
		
		//Tile Entities
		ClientRegistry.bindTileEntityRenderer(UATileEntities.ELDER_EYE.get(), TileEntityElderEyeRenderer::new);
		
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.NAUTILUS.get(), NautilusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.PIKE.get(), PikeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.LIONFISH.get(), LionfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.THRASHER.get(), ThrasherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GREAT_THRASHER.get(), GreatThrasherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.BOAT.get(), UABoatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.FLARE.get(), FlareRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.SONAR_WAVE.get(), SonarWaveRenderer::new);
		
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.BOX_JELLYFISH.get(), BoxJellyfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishRenderer::new);
	}
	
	/*
	 * Temporary fix for forge bug
	 */
	@OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		for(RegistryObject<Item> items : UAItems.SPAWN_EGGS) {
			//RegistryObject#isPresent causes a null pointer when it's false :crying: thanks forge
			if(ObfuscationReflectionHelper.getPrivateValue(RegistryObject.class, items, "value") != null) {
				Item item = items.get();
				if(item instanceof UASpawnEggItem) {
					event.getItemColors().register((itemColor, itemsIn) -> {
						return ((UASpawnEggItem) item).getColor(itemsIn);
					}, item);
				}
			}
		}
	}
	
	void setupMessages() {
		int id = -1;
		
		CHANNEL.messageBuilder(MessageCAnimation.class, id++)
		.encoder(MessageCAnimation::serialize).decoder(MessageCAnimation::deserialize)
		.consumer(MessageCAnimation::handle)
		.add();
		
		CHANNEL.messageBuilder(MessageRotateJellyfish.class, id++)
		.encoder(MessageRotateJellyfish::serialize).decoder(MessageRotateJellyfish::deserialize)
		.consumer(MessageRotateJellyfish::handle)
		.add();
	}
}