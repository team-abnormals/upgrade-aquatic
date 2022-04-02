package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.*;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Flare;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.GreatThrasher;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.common.entity.projectile.SonarWave;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UAEntityTypes {
	public static final EntitySubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getEntitySubHelper();

	public static final RegistryObject<EntityType<SonarWave>> SONAR_WAVE = HELPER.createEntity("sonar_wave", SonarWave::new, SonarWave::new, MobCategory.MISC, 1.0F, 1.0F);
	public static final RegistryObject<EntityType<Nautilus>> NAUTILUS = HELPER.createLivingEntity("nautilus", Nautilus::new, MobCategory.WATER_AMBIENT, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<Pike>> PIKE = HELPER.createLivingEntity("pike", Pike::new, MobCategory.WATER_AMBIENT, 0.7F, 0.4F);
	public static final RegistryObject<EntityType<Perch>> PERCH = HELPER.createLivingEntity("perch", Perch::new, MobCategory.WATER_AMBIENT, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<Lionfish>> LIONFISH = HELPER.createLivingEntity("lionfish", Lionfish::new, MobCategory.WATER_AMBIENT, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<Thrasher>> THRASHER = HELPER.createLivingEntity("thrasher", Thrasher::new, MobCategory.MONSTER, 1.6F, 0.9F);
	public static final RegistryObject<EntityType<GreatThrasher>> GREAT_THRASHER = HELPER.createLivingEntity("great_thrasher", GreatThrasher::new, MobCategory.MONSTER, 2.8F, 1.575F);
	public static final RegistryObject<EntityType<Flare>> FLARE = HELPER.createLivingEntity("flare", Flare::new, MobCategory.MONSTER, 0.9F, 0.5F);
	public static final RegistryObject<EntityType<Ululu>> ULULU = HELPER.createLivingEntity("ululu", Ululu::new, MobCategory.MONSTER, 2.04F, 2.04F);
	public static final RegistryObject<EntityType<Goose>> GOOSE = HELPER.createLivingEntity("goose", Goose::new, MobCategory.CREATURE, 0.5F, 0.9F);
	public static final RegistryObject<EntityType<GlowSquid>> GLOW_SQUID = HELPER.createLivingEntity("glow_squid", GlowSquid::new, MobCategory.WATER_CREATURE, 0.8F, 0.8F);

	public static final RegistryObject<EntityType<BoxJellyfish>> BOX_JELLYFISH = HELPER.createLivingEntity("box_jellyfish", BoxJellyfish::new, MobCategory.WATER_CREATURE, 0.75F, 0.625F);
	public static final RegistryObject<EntityType<CassiopeaJellyfish>> CASSIOPEA_JELLYFISH = HELPER.createLivingEntity("cassiopea_jellyfish", CassiopeaJellyfish::new, MobCategory.WATER_CREATURE, 0.6875F, 0.25F);
	public static final RegistryObject<EntityType<ImmortalJellyfish>> IMMORTAL_JELLYFISH = HELPER.createLivingEntity("immortal_jellyfish", ImmortalJellyfish::new, MobCategory.WATER_CREATURE, 0.625F, 0.5F);

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(NAUTILUS.get(), Nautilus.registerAttributes().build());
		event.put(PIKE.get(), Pike.registerAttributes().build());
		event.put(LIONFISH.get(), Lionfish.registerAttributes().build());
		event.put(PERCH.get(), Perch.createAttributes().build());
		event.put(THRASHER.get(), Thrasher.registerAttributes().build());
		event.put(GREAT_THRASHER.get(), GreatThrasher.registerAttributes().build());
		event.put(FLARE.get(), Flare.registerAttributes().build());
		event.put(ULULU.get(), Ululu.registerAttributes().build());
		event.put(GOOSE.get(), Goose.registerAttributes().build());
		event.put(GLOW_SQUID.get(), GlowSquid.createAttributes().build());

		event.put(BOX_JELLYFISH.get(), BoxJellyfish.registerAttributes().build());
		event.put(CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfish.registerAttributes().build());
		event.put(IMMORTAL_JELLYFISH.get(), ImmortalJellyfish.registerAttributes().build());
	}

	public static void registerRenderers() {
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.NAUTILUS.get(), NautilusRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.PIKE.get(), PikeRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.LIONFISH.get(), LionfishRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.PERCH.get(), PerchRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.THRASHER.get(), ThrasherRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GREAT_THRASHER.get(), GreatThrasherRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.FLARE.get(), FlareRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.SONAR_WAVE.get(), SonarWaveRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.ULULU.get(), UluluRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GOOSE.get(), GooseRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GLOW_SQUID.get(), GlowSquidRenderer::new);
//
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.BOX_JELLYFISH.get(), BoxJellyfishRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishRenderer::new);
//		RenderingRegistry.registerEntityRenderingHandler(UAEntities.IMMORTAL_JELLYFISH.get(), ImmortalJellyfishRenderer::new);
	}
}