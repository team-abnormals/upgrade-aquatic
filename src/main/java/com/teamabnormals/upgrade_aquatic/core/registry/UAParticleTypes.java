package com.teamabnormals.upgrade_aquatic.core.registry;


import com.teamabnormals.upgrade_aquatic.client.particle.ElderPrismarineShowerParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.JellyTorchParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.PrismarineShowerParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.SpectralConsumeParticle;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UAParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<SimpleParticleType> PRISMARINE_SHOWER = registerSimpleParticleType("prismarine_shower", false);
	public static final RegistryObject<SimpleParticleType> ELDER_PRISMARINE_SHOWER = registerSimpleParticleType("elder_prismarine_shower", false);
	public static final RegistryObject<SimpleParticleType> SPECTRAL_CONSUME = registerSimpleParticleType("spectral_consume", false);
	public static final RegistryObject<SimpleParticleType> PINK_JELLY_FLAME = registerSimpleParticleType("pink_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> PURPLE_JELLY_FLAME = registerSimpleParticleType("purple_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> BLUE_JELLY_FLAME = registerSimpleParticleType("blue_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> GREEN_JELLY_FLAME = registerSimpleParticleType("green_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> YELLOW_JELLY_FLAME = registerSimpleParticleType("yellow_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> ORANGE_JELLY_FLAME = registerSimpleParticleType("orange_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> RED_JELLY_FLAME = registerSimpleParticleType("red_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> WHITE_JELLY_FLAME = registerSimpleParticleType("white_jelly_flame", false);
	public static final RegistryObject<SimpleParticleType> PINK_JELLY_BLOB = registerSimpleParticleType("pink_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> PURPLE_JELLY_BLOB = registerSimpleParticleType("purple_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> BLUE_JELLY_BLOB = registerSimpleParticleType("blue_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> GREEN_JELLY_BLOB = registerSimpleParticleType("green_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> YELLOW_JELLY_BLOB = registerSimpleParticleType("yellow_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> ORANGE_JELLY_BLOB = registerSimpleParticleType("orange_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> RED_JELLY_BLOB = registerSimpleParticleType("red_jelly_blob", false);
	public static final RegistryObject<SimpleParticleType> WHITE_JELLY_BLOB = registerSimpleParticleType("white_jelly_blob", false);

	private static RegistryObject<SimpleParticleType> registerSimpleParticleType(String name, boolean alwaysShow) {
		return PARTICLES.register(name, () -> new SimpleParticleType(alwaysShow));
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
		ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
		if (PRISMARINE_SHOWER.isPresent()) {
			particleEngine.register(PRISMARINE_SHOWER.get(), PrismarineShowerParticle.Factory::new);
		}
		if (ELDER_PRISMARINE_SHOWER.isPresent()) {
			particleEngine.register(ELDER_PRISMARINE_SHOWER.get(), ElderPrismarineShowerParticle.Factory::new);
		}
		if (SPECTRAL_CONSUME.isPresent()) {
			particleEngine.register(SPECTRAL_CONSUME.get(), SpectralConsumeParticle.Factory::new);
		}
		if (PINK_JELLY_FLAME.isPresent()) {
			particleEngine.register(PINK_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (PURPLE_JELLY_FLAME.isPresent()) {
			particleEngine.register(PURPLE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (BLUE_JELLY_FLAME.isPresent()) {
			particleEngine.register(BLUE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (GREEN_JELLY_FLAME.isPresent()) {
			particleEngine.register(GREEN_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (YELLOW_JELLY_FLAME.isPresent()) {
			particleEngine.register(YELLOW_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (ORANGE_JELLY_FLAME.isPresent()) {
			particleEngine.register(ORANGE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (RED_JELLY_FLAME.isPresent()) {
			particleEngine.register(RED_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (WHITE_JELLY_FLAME.isPresent()) {
			particleEngine.register(WHITE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		}
		if (PINK_JELLY_BLOB.isPresent()) {
			particleEngine.register(PINK_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (PURPLE_JELLY_BLOB.isPresent()) {
			particleEngine.register(PURPLE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (BLUE_JELLY_BLOB.isPresent()) {
			particleEngine.register(BLUE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (GREEN_JELLY_BLOB.isPresent()) {
			particleEngine.register(GREEN_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (YELLOW_JELLY_BLOB.isPresent()) {
			particleEngine.register(YELLOW_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (ORANGE_JELLY_BLOB.isPresent()) {
			particleEngine.register(ORANGE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (RED_JELLY_BLOB.isPresent()) {
			particleEngine.register(RED_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
		if (WHITE_JELLY_BLOB.isPresent()) {
			particleEngine.register(WHITE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		}
	}
}