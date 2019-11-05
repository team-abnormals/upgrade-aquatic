package com.teamabnormals.upgrade_aquatic.client.particle;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class UAParticles {
	public static final BasicParticleType PRISMARINE_SHOWER = createBasicParticleType(false, "prismarine_shower");
	public static final BasicParticleType ELDER_PRISMARINE_SHOWER = createBasicParticleType(false, "elder_prismarine_shower");
	public static final BasicParticleType SPECTRAL_CONSUME = createBasicParticleType(false, "spectral_consume");
	public static final BasicParticleType PINK_JELLY_FLAME = createBasicParticleType(false, "pink_jelly_flame");
	public static final BasicParticleType PURPLE_JELLY_FLAME = createBasicParticleType(false, "purple_jelly_flame");
	public static final BasicParticleType BLUE_JELLY_FLAME = createBasicParticleType(false, "blue_jelly_flame");
	public static final BasicParticleType GREEN_JELLY_FLAME = createBasicParticleType(false, "green_jelly_flame");
	public static final BasicParticleType YELLOW_JELLY_FLAME = createBasicParticleType(false, "yellow_jelly_flame");
	public static final BasicParticleType ORANGE_JELLY_FLAME = createBasicParticleType(false, "orange_jelly_flame");
	public static final BasicParticleType RED_JELLY_FLAME = createBasicParticleType(false, "red_jelly_flame");
	public static final BasicParticleType WHITE_JELLY_FLAME = createBasicParticleType(false, "white_jelly_flame");
	public static final BasicParticleType SONAR = createBasicParticleType(true, "sonar");
	
	@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleTypes {
		
		@SubscribeEvent
		public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) {
			event.getRegistry().registerAll(
				PRISMARINE_SHOWER, ELDER_PRISMARINE_SHOWER,
				SPECTRAL_CONSUME,
				PINK_JELLY_FLAME, PURPLE_JELLY_FLAME, BLUE_JELLY_FLAME, GREEN_JELLY_FLAME, YELLOW_JELLY_FLAME, ORANGE_JELLY_FLAME, RED_JELLY_FLAME, WHITE_JELLY_FLAME,
				SONAR
			);
		}
		
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {
		
		@SubscribeEvent
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			Minecraft.getInstance().particles.registerFactory(PRISMARINE_SHOWER, ParticlePrismarineShower.Factory::new);
			Minecraft.getInstance().particles.registerFactory(ELDER_PRISMARINE_SHOWER, ParticleElderPrismarineShower.Factory::new);
			Minecraft.getInstance().particles.registerFactory(SPECTRAL_CONSUME, ParticleSpectralConsume.Factory::new);
			Minecraft.getInstance().particles.registerFactory(PINK_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(PURPLE_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(BLUE_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(GREEN_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(YELLOW_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(ORANGE_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(RED_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(WHITE_JELLY_FLAME, ParticleJellyTorch.Factory::new);
			Minecraft.getInstance().particles.registerFactory(SONAR, ParticleSonar.Factory::new);
		}
		
	}
	
	private static BasicParticleType createBasicParticleType(boolean alwaysShow, String name) {
		BasicParticleType particleType = new BasicParticleType(alwaysShow);
		particleType.setRegistryName(Reference.MODID, name);
		return particleType;
	}
}
