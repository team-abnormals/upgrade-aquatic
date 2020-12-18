package com.minecraftabnormals.upgrade_aquatic.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.network.RotateJellyfishMessage;
import com.minecraftabnormals.upgrade_aquatic.core.config.Config;
import com.minecraftabnormals.upgrade_aquatic.core.config.ConfigHelper;
import com.minecraftabnormals.upgrade_aquatic.core.other.*;
import com.minecraftabnormals.upgrade_aquatic.core.registry.*;
import com.minecraftabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(value = UpgradeAquatic.MODID)
public class UpgradeAquatic {
	public static UpgradeAquatic instance;
	public static final String NETWORK_PROTOCOL = "1";
	public static final String MODID = "upgrade_aquatic";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MODID, helper -> {
		helper.putSubHelper(ForgeRegistries.ITEMS, new UAItemSubRegistryHelper(helper));
	});

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

		REGISTRY_HELPER.register(modEventBus);
		UAEffects.EFFECTS.register(modEventBus);
		UAEffects.POTIONS.register(modEventBus);
		UAFeatures.FEATURES.register(modEventBus);
		UAParticles.PARTICLES.register(modEventBus);
		UADataSerializers.SERIALIZERS.register(modEventBus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(EventPriority.LOWEST, this::setupClient);
			GlowSquidSpriteUploader.init(modEventBus);
		});

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
		event.enqueueWork(() -> {
			UACompat.registerCompat();
			UAEntities.registerAttributes();
			UASpawns.registerSpawns();
			UAEffects.registerBrewingRecipes();
			UADispenseBehaviorRegistry.registerDispenseBehaviors();
			UAFeatures.Configured.registerConfiguredFeatures();
			ObfuscationReflectionHelper.setPrivateValue(AbstractBlock.class, Blocks.BUBBLE_COLUMN, true, "field_149789_z");
		});
	}

	private void setupClient(final FMLClientSetupEvent event) {
		UAEntities.registerRenderers();
		UATileEntities.registerRenderers();
		event.enqueueWork(() -> {
			UAItems.setupItemPropertes();
			UAClientCompat.registerClientCompat();
		});
	}

	void setupMessages() {
		int id = -1;

		CHANNEL.messageBuilder(RotateJellyfishMessage.class, id++)
				.encoder(RotateJellyfishMessage::serialize).decoder(RotateJellyfishMessage::deserialize)
				.consumer(RotateJellyfishMessage::handle)
				.add();
	}
}