package com.minecraftabnormals.upgrade_aquatic.client.particle;


import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<SimpleParticleType> PRISMARINE_SHOWER = createBasicParticleType("prismarine_shower", false);
	public static final RegistryObject<SimpleParticleType> ELDER_PRISMARINE_SHOWER = createBasicParticleType("elder_prismarine_shower", false);
	public static final RegistryObject<SimpleParticleType> SPECTRAL_CONSUME = createBasicParticleType("spectral_consume", false);
	public static final RegistryObject<SimpleParticleType> PINK_JELLY_FLAME = createBasicParticleType("pink_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> PURPLE_JELLY_FLAME = createBasicParticleType("purple_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> BLUE_JELLY_FLAME = createBasicParticleType("blue_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> GREEN_JELLY_FLAME = createBasicParticleType("green_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> YELLOW_JELLY_FLAME = createBasicParticleType("yellow_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> ORANGE_JELLY_FLAME = createBasicParticleType("orange_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> RED_JELLY_FLAME = createBasicParticleType("red_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> WHITE_JELLY_FLAME = createBasicParticleType("white_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> PINK_JELLY_BLOB = createBasicParticleType("pink_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> PURPLE_JELLY_BLOB = createBasicParticleType("purple_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> BLUE_JELLY_BLOB = createBasicParticleType("blue_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> GREEN_JELLY_BLOB = createBasicParticleType("green_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> YELLOW_JELLY_BLOB = createBasicParticleType("yellow_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> ORANGE_JELLY_BLOB = createBasicParticleType("orange_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> RED_JELLY_BLOB = createBasicParticleType("red_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> WHITE_JELLY_BLOB = createBasicParticleType("white_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> GLOW_SQUID_INK = createBasicParticleType("glow_squid_ink", true);

	private static RegistryObject<SimpleParticleType> createBasicParticleType(String name, boolean alwaysShow) {
		RegistryObject<SimpleParticleType> particleType = PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
		return particleType;
	}

	@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			ParticleEngine manager = Minecraft.getInstance().particleEngine;
			if (PRISMARINE_SHOWER.isPresent()) {
				manager.register(PRISMARINE_SHOWER.get(), PrismarineShowerParticle.Factory::new);
			}
			if (ELDER_PRISMARINE_SHOWER.isPresent()) {
				manager.register(ELDER_PRISMARINE_SHOWER.get(), ElderPrismarineShowerParticle.Factory::new);
			}
			if (SPECTRAL_CONSUME.isPresent()) {
				manager.register(SPECTRAL_CONSUME.get(), SpectralConsumeParticle.Factory::new);
			}
			if (PINK_JELLY_FLAME.isPresent()) {
				manager.register(PINK_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (PURPLE_JELLY_FLAME.isPresent()) {
				manager.register(PURPLE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (BLUE_JELLY_FLAME.isPresent()) {
				manager.register(BLUE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (GREEN_JELLY_FLAME.isPresent()) {
				manager.register(GREEN_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (YELLOW_JELLY_FLAME.isPresent()) {
				manager.register(YELLOW_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (ORANGE_JELLY_FLAME.isPresent()) {
				manager.register(ORANGE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (RED_JELLY_FLAME.isPresent()) {
				manager.register(RED_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (WHITE_JELLY_FLAME.isPresent()) {
				manager.register(WHITE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
			}
			if (PINK_JELLY_BLOB.isPresent()) {
				manager.register(PINK_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (PURPLE_JELLY_BLOB.isPresent()) {
				manager.register(PURPLE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (BLUE_JELLY_BLOB.isPresent()) {
				manager.register(BLUE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (GREEN_JELLY_BLOB.isPresent()) {
				manager.register(GREEN_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (YELLOW_JELLY_BLOB.isPresent()) {
				manager.register(YELLOW_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (ORANGE_JELLY_BLOB.isPresent()) {
				manager.register(ORANGE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (RED_JELLY_BLOB.isPresent()) {
				manager.register(RED_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (WHITE_JELLY_BLOB.isPresent()) {
				manager.register(WHITE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
			}
			if (GLOW_SQUID_INK.isPresent()) {
				manager.register(GLOW_SQUID_INK.get(), GlowSquidInkParticle.Factory::new);
			}
		}
	}
}