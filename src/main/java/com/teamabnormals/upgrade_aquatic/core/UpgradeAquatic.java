package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.teamabnormals.upgrade_aquatic.core.registry.UAParticleTypes;
import com.teamabnormals.upgrade_aquatic.common.network.RotateJellyfishMessage;
import com.teamabnormals.upgrade_aquatic.core.other.UAClientCompat;
import com.teamabnormals.upgrade_aquatic.core.other.UACompat;
import com.teamabnormals.upgrade_aquatic.core.other.UADataSerializers;
import com.teamabnormals.upgrade_aquatic.core.other.UADispenseBehaviorRegistry;
import com.teamabnormals.upgrade_aquatic.core.other.UASpawns;
import com.teamabnormals.upgrade_aquatic.core.registry.UAMobEffects;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlockEntityTypes;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UAItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(value = UpgradeAquatic.MOD_ID)
public class UpgradeAquatic {
	public static final String NETWORK_PROTOCOL = "1";
	public static final String MOD_ID = "upgrade_aquatic";
	public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(ForgeRegistries.ITEMS, new UAItemSubRegistryHelper(helper)));

	public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(UpgradeAquatic.MOD_ID, "net"))
			.networkProtocolVersion(() -> NETWORK_PROTOCOL)
			.clientAcceptedVersions(NETWORK_PROTOCOL::equals)
			.serverAcceptedVersions(NETWORK_PROTOCOL::equals)
			.simpleChannel();

	public UpgradeAquatic() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();

		this.setupMessages();

		REGISTRY_HELPER.register(bus);
		UAMobEffects.MOB_EFFECTS.register(bus);
		UAMobEffects.POTIONS.register(bus);
		UAFeatures.FEATURES.register(bus);
		UAParticleTypes.PARTICLES.register(bus);
		UADataSerializers.SERIALIZERS.register(bus);
		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			GlowSquidSpriteUploader.init(bus);
		});

		context.registerConfig(ModConfig.Type.COMMON, UAConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.CLIENT, UAConfig.CLIENT_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			UACompat.registerCompat();
			UASpawns.registerSpawns();
			UAMobEffects.registerBrewingRecipes();
			UADispenseBehaviorRegistry.registerDispenseBehaviors();
			UAFeatures.Configured.registerConfiguredFeatures();
			ObfuscationReflectionHelper.setPrivateValue(BlockBehaviour.class, Blocks.BUBBLE_COLUMN, true, "f_60445_");
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		UAEntityTypes.registerRenderers();
		UABlockEntityTypes.registerRenderers();
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