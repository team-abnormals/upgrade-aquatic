package com.minecraftabnormals.upgrade_aquatic.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.minecraftabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.minecraftabnormals.upgrade_aquatic.common.network.RotateJellyfishMessage;
import com.minecraftabnormals.upgrade_aquatic.core.other.*;
import com.minecraftabnormals.upgrade_aquatic.core.registry.*;
import com.minecraftabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(value = UpgradeAquatic.MOD_ID)
public class UpgradeAquatic {
	public static final String NETWORK_PROTOCOL = "1";
	public static final String MOD_ID = "upgrade_aquatic";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> {
		helper.putSubHelper(ForgeRegistries.ITEMS, new UAItemSubRegistryHelper(helper));
	});

	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(UpgradeAquatic.MOD_ID, "net"))
			.networkProtocolVersion(() -> NETWORK_PROTOCOL)
			.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
			.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
			.simpleChannel();

	public UpgradeAquatic() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		this.setupMessages();

		REGISTRY_HELPER.register(bus);
		UAEffects.EFFECTS.register(bus);
		UAEffects.POTIONS.register(bus);
		UAFeatures.FEATURES.register(bus);
		UAParticles.PARTICLES.register(bus);
		UADataSerializers.SERIALIZERS.register(bus);
		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			GlowSquidSpriteUploader.init(bus);
		});

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UAConfig.COMMON_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, UAConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			UACompat.registerCompat();
			UASpawns.registerSpawns();
			UAEffects.registerBrewingRecipes();
			UADispenseBehaviorRegistry.registerDispenseBehaviors();
			UAFeatures.Configured.registerConfiguredFeatures();
			ObfuscationReflectionHelper.setPrivateValue(AbstractBlock.class, Blocks.BUBBLE_COLUMN, true, "field_149789_z");
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		UAEntities.registerRenderers();
		UATileEntities.registerRenderers();
		event.enqueueWork(() -> {
			UAItems.registerItemProperties();
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