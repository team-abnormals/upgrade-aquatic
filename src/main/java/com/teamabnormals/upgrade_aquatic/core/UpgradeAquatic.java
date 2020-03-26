package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.common.items.UASpawnEggItem;
import com.teamabnormals.upgrade_aquatic.common.network.MessageCAnimation;
import com.teamabnormals.upgrade_aquatic.common.world.UAWorldGen;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.config.Config;
import com.teamabnormals.upgrade_aquatic.core.config.ConfigHelper;
import com.teamabnormals.upgrade_aquatic.core.proxy.ClientProxy;
import com.teamabnormals.upgrade_aquatic.core.proxy.ServerProxy;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEffects;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UASounds;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UACompostables;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UADispenseBehaviorRegistry;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UAEntitySpawns;
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

		UABlocks.BLOCKS.register(modEventBus);
		UAItems.ITEMS.register(modEventBus);
		UAEffects.EFFECTS.register(modEventBus);
		UAEffects.POTIONS.register(modEventBus);
		UATileEntities.TILE_ENTITY_TYPES.register(modEventBus);
		UAEntities.ENTITY_TYPES.register(modEventBus);
		UASounds.SOUNDS.register(modEventBus);
		UAFeatures.FEATURES.register(modEventBus);
		
		modEventBus.addListener((ModConfig.ModConfigEvent event) -> {
			final ModConfig config = event.getConfig();
			if(config.getSpec() == Config.CLIENTSPEC) {
				ConfigHelper.updateClientConfig(config);
			}
		});
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::registerItemColors);
		});
		
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, Config.CLIENTSPEC);
	}
	
	private void setupCommon(final FMLCommonSetupEvent event) {
		proxy.preInit();
		UAEntitySpawns.addSpawnsToBiomes();
		UADispenseBehaviorRegistry.registerDispenseBehaviors();
		UAEffects.registerBrewingRecipes();
		UAWorldGen.registerGenerators();
		UACompostables.registerCompostables();
	}
	
	@OnlyIn(Dist.CLIENT)
	private void registerItemColors(ColorHandlerEvent.Item event) {
		for(RegistryObject<Item> items : UAItems.SPAWN_EGGS) {
			Item item = items.get();
			if(item instanceof UASpawnEggItem) {
				event.getItemColors().register((itemColor, itemsIn) -> {
					return ((UASpawnEggItem) item).getColor(itemsIn);
				}, item);
			}
		}
	}
	
	void setupMessages() {
		int id = -1;
		
		CHANNEL.messageBuilder(MessageCAnimation.class, id++)
		.encoder(MessageCAnimation::serialize).decoder(MessageCAnimation::deserialize)
		.consumer(MessageCAnimation::handle)
		.add();
	}
}