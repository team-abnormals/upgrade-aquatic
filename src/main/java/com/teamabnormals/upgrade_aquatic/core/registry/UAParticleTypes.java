package com.teamabnormals.upgrade_aquatic.core.registry;


import com.teamabnormals.upgrade_aquatic.client.particle.ElderPrismarineShowerParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.JellyTorchParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.PrismarineShowerParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.SpectralConsumeParticle;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UAParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, UpgradeAquatic.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PRISMARINE_SHOWER = register("prismarine_shower");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ELDER_PRISMARINE_SHOWER = register("elder_prismarine_shower");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPECTRAL_CONSUME = register("spectral_consume");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINK_JELLY_FLAME = register("pink_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURPLE_JELLY_FLAME = register("purple_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLUE_JELLY_FLAME = register("blue_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GREEN_JELLY_FLAME = register("green_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_JELLY_FLAME = register("yellow_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORANGE_JELLY_FLAME = register("orange_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RED_JELLY_FLAME = register("red_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WHITE_JELLY_FLAME = register("white_jelly_flame");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINK_JELLY_BLOB = register("pink_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURPLE_JELLY_BLOB = register("purple_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLUE_JELLY_BLOB = register("blue_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GREEN_JELLY_BLOB = register("green_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_JELLY_BLOB = register("yellow_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ORANGE_JELLY_BLOB = register("orange_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RED_JELLY_BLOB = register("red_jelly_blob");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> WHITE_JELLY_BLOB = register("white_jelly_blob");

	private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name) {
		return PARTICLES.register(name, () -> new SimpleParticleType(false));
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(PRISMARINE_SHOWER.get(), PrismarineShowerParticle.Factory::new);
		event.registerSpriteSet(ELDER_PRISMARINE_SHOWER.get(), ElderPrismarineShowerParticle.Factory::new);
		event.registerSpriteSet(SPECTRAL_CONSUME.get(), SpectralConsumeParticle.Factory::new);
		event.registerSpriteSet(PINK_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(PURPLE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(BLUE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(GREEN_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(YELLOW_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(ORANGE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(RED_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(WHITE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(PINK_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(PURPLE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(BLUE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(GREEN_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(YELLOW_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(ORANGE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(RED_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.registerSpriteSet(WHITE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
	}
}