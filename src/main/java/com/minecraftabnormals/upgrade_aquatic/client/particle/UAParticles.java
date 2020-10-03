package com.minecraftabnormals.upgrade_aquatic.client.particle;


import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UpgradeAquatic.MODID);
	
	public static final RegistryObject<BasicParticleType> PRISMARINE_SHOWER = createBasicParticleType("prismarine_shower", false);
	public static final RegistryObject<BasicParticleType> ELDER_PRISMARINE_SHOWER = createBasicParticleType("elder_prismarine_shower", false);
	public static final RegistryObject<BasicParticleType> SPECTRAL_CONSUME = createBasicParticleType("spectral_consume", false);
	public static final RegistryObject<BasicParticleType> PINK_JELLY_FLAME = createBasicParticleType("pink_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> PURPLE_JELLY_FLAME = createBasicParticleType("purple_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> BLUE_JELLY_FLAME = createBasicParticleType("blue_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> GREEN_JELLY_FLAME = createBasicParticleType("green_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> YELLOW_JELLY_FLAME = createBasicParticleType("yellow_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> ORANGE_JELLY_FLAME = createBasicParticleType("orange_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> RED_JELLY_FLAME = createBasicParticleType("red_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> WHITE_JELLY_FLAME = createBasicParticleType("white_jelly_flame", false);
	public static final RegistryObject<BasicParticleType> PINK_JELLY_BLOB = createBasicParticleType("pink_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> PURPLE_JELLY_BLOB = createBasicParticleType("purple_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> BLUE_JELLY_BLOB = createBasicParticleType("blue_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> GREEN_JELLY_BLOB = createBasicParticleType("green_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> YELLOW_JELLY_BLOB = createBasicParticleType("yellow_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> ORANGE_JELLY_BLOB = createBasicParticleType("orange_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> RED_JELLY_BLOB = createBasicParticleType("red_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> WHITE_JELLY_BLOB = createBasicParticleType("white_jelly_blob", false);
	public static final RegistryObject<BasicParticleType> GLOW_SQUID_INK = createBasicParticleType("glow_squid_ink", true);

	private static RegistryObject<BasicParticleType> createBasicParticleType(String name, boolean alwaysShow) {
		RegistryObject<BasicParticleType> particleType = PARTICLES.register(name, () -> new BasicParticleType(alwaysShow));
		return particleType;
	}
	
	@Mod.EventBusSubscriber(modid = UpgradeAquatic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {
		
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			ParticleManager manager = Minecraft.getInstance().particles;
			if (PRISMARINE_SHOWER.isPresent()) {
				manager.registerFactory(PRISMARINE_SHOWER.get(), PrismarineShowerParticle.Factory::new);
			}
			if (ELDER_PRISMARINE_SHOWER.isPresent()) {
				manager.registerFactory(ELDER_PRISMARINE_SHOWER.get(), ElderPrismarineShowerParticle.Factory::new);
			}
			if (SPECTRAL_CONSUME.isPresent()) {
				manager.registerFactory(SPECTRAL_CONSUME.get(), SpectralConsumeParticle.Factory::new);
			}
			if (PINK_JELLY_FLAME.isPresent()) {
				manager.registerFactory(PINK_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (PURPLE_JELLY_FLAME.isPresent()) {
				manager.registerFactory(PURPLE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (BLUE_JELLY_FLAME.isPresent()) {
				manager.registerFactory(BLUE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (GREEN_JELLY_FLAME.isPresent()) {
				manager.registerFactory(GREEN_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (YELLOW_JELLY_FLAME.isPresent()) {
				manager.registerFactory(YELLOW_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (ORANGE_JELLY_FLAME.isPresent()) {
				manager.registerFactory(ORANGE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (RED_JELLY_FLAME.isPresent()) {
				manager.registerFactory(RED_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (WHITE_JELLY_FLAME.isPresent()) {
				manager.registerFactory(WHITE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (PINK_JELLY_BLOB.isPresent()) {
				manager.registerFactory(PINK_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (PURPLE_JELLY_BLOB.isPresent()) {
				manager.registerFactory(PURPLE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (BLUE_JELLY_BLOB.isPresent()) {
				manager.registerFactory(BLUE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (GREEN_JELLY_BLOB.isPresent()) {
				manager.registerFactory(GREEN_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (YELLOW_JELLY_BLOB.isPresent()) {
				manager.registerFactory(YELLOW_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (ORANGE_JELLY_BLOB.isPresent()) {
				manager.registerFactory(ORANGE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (RED_JELLY_BLOB.isPresent()) {
				manager.registerFactory(RED_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (WHITE_JELLY_BLOB.isPresent()) {
				manager.registerFactory(WHITE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (GLOW_SQUID_INK.isPresent()) {
				manager.registerFactory(GLOW_SQUID_INK.get(), GlowSquidInkParticle.Factory::new);
			}
		}
	}
}