package com.teamabnormals.upgrade_aquatic.core.registry;


import com.teamabnormals.upgrade_aquatic.client.particle.ElderPrismarineShowerParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.JellyTorchParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.PrismarineShowerParticle;
import com.teamabnormals.upgrade_aquatic.client.particle.SpectralConsumeParticle;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UAParticleTypes {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UpgradeAquatic.MOD_ID);

	public static final RegistryObject<SimpleParticleType> PRISMARINE_SHOWER = register("prismarine_shower");
	public static final RegistryObject<SimpleParticleType> ELDER_PRISMARINE_SHOWER = register("elder_prismarine_shower");
	public static final RegistryObject<SimpleParticleType> SPECTRAL_CONSUME = register("spectral_consume");
	public static final RegistryObject<SimpleParticleType> PINK_JELLY_FLAME = register("pink_jelly_flame");
	public static final RegistryObject<SimpleParticleType> PURPLE_JELLY_FLAME = register("purple_jelly_flame");
	public static final RegistryObject<SimpleParticleType> BLUE_JELLY_FLAME = register("blue_jelly_flame");
	public static final RegistryObject<SimpleParticleType> GREEN_JELLY_FLAME = register("green_jelly_flame");
	public static final RegistryObject<SimpleParticleType> YELLOW_JELLY_FLAME = register("yellow_jelly_flame");
	public static final RegistryObject<SimpleParticleType> ORANGE_JELLY_FLAME = register("orange_jelly_flame");
	public static final RegistryObject<SimpleParticleType> RED_JELLY_FLAME = register("red_jelly_flame");
	public static final RegistryObject<SimpleParticleType> WHITE_JELLY_FLAME = register("white_jelly_flame");
	public static final RegistryObject<SimpleParticleType> PINK_JELLY_BLOB = register("pink_jelly_blob");
	public static final RegistryObject<SimpleParticleType> PURPLE_JELLY_BLOB = register("purple_jelly_blob");
	public static final RegistryObject<SimpleParticleType> BLUE_JELLY_BLOB = register("blue_jelly_blob");
	public static final RegistryObject<SimpleParticleType> GREEN_JELLY_BLOB = register("green_jelly_blob");
	public static final RegistryObject<SimpleParticleType> YELLOW_JELLY_BLOB = register("yellow_jelly_blob");
	public static final RegistryObject<SimpleParticleType> ORANGE_JELLY_BLOB = register("orange_jelly_blob");
	public static final RegistryObject<SimpleParticleType> RED_JELLY_BLOB = register("red_jelly_blob");
	public static final RegistryObject<SimpleParticleType> WHITE_JELLY_BLOB = register("white_jelly_blob");

	private static RegistryObject<SimpleParticleType> register(String name) {
		return PARTICLES.register(name, () -> new SimpleParticleType(false));
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
		event.register(PRISMARINE_SHOWER.get(), PrismarineShowerParticle.Factory::new);
		event.register(ELDER_PRISMARINE_SHOWER.get(), ElderPrismarineShowerParticle.Factory::new);
		event.register(SPECTRAL_CONSUME.get(), SpectralConsumeParticle.Factory::new);
		event.register(PINK_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(PURPLE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(BLUE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(GREEN_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(YELLOW_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(ORANGE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(RED_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(WHITE_JELLY_FLAME.get(), JellyTorchParticle.Factory::new);
		event.register(PINK_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(PURPLE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(BLUE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(GREEN_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(YELLOW_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(ORANGE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(RED_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
		event.register(WHITE_JELLY_BLOB.get(), JellyTorchParticle.Factory::new);
	}
}