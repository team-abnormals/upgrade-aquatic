package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.EntitySubRegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.client.render.*;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.BoxJellyfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.CassiopeaJellyfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish.ImmortalJellyfishRenderer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.*;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.BoxJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.CassiopeaJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ImmortalJellyfishEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.GreatThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.SonarWaveEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UAEntities {
	public static final EntitySubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getEntitySubHelper();
	
	public static final RegistryObject<EntityType<SonarWaveEntity>> SONAR_WAVE          = HELPER.createEntity("sonar_wave", SonarWaveEntity::new, SonarWaveEntity::new, EntityClassification.MISC, 1.0F, 1.0F);
	public static final RegistryObject<EntityType<NautilusEntity>> NAUTILUS             = HELPER.createLivingEntity("nautilus", NautilusEntity::new, EntityClassification.CREATURE, 0.5F, 0.5F);
	public static final RegistryObject<EntityType<PikeEntity>> PIKE                     = HELPER.createLivingEntity("pike", PikeEntity::new, EntityClassification.CREATURE, 0.7F, 0.4F);
	public static final RegistryObject<EntityType<LionfishEntity>> LIONFISH             = HELPER.createLivingEntity("lionfish", LionfishEntity::new, EntityClassification.CREATURE, 0.6F, 0.5F);
	public static final RegistryObject<EntityType<ThrasherEntity>> THRASHER             = HELPER.createLivingEntity("thrasher", ThrasherEntity::new, EntityClassification.MONSTER, 1.6F, 0.9F);
	public static final RegistryObject<EntityType<GreatThrasherEntity>> GREAT_THRASHER	= HELPER.createLivingEntity("great_thrasher", GreatThrasherEntity::new, EntityClassification.MONSTER, 2.8F, 1.575F);
	public static final RegistryObject<EntityType<FlareEntity>> FLARE                   = HELPER.createLivingEntity("flare", FlareEntity::new, EntityClassification.MONSTER, 0.9F, 0.5F);
	public static final RegistryObject<EntityType<UluluEntity>> ULULU					= HELPER.createLivingEntity("ululu", UluluEntity::new, EntityClassification.MONSTER, 2.04F, 2.04F);
	public static final RegistryObject<EntityType<GooseEntity>> GOOSE 				  	= HELPER.createLivingEntity("goose", GooseEntity::new, EntityClassification.CREATURE, 0.5F, 0.9F);
	public static final RegistryObject<EntityType<GlowSquidEntity>> GLOW_SQUID 			= HELPER.createLivingEntity("glow_squid", GlowSquidEntity::new, EntityClassification.WATER_CREATURE, 0.8F, 0.8F);

	public static final RegistryObject<EntityType<BoxJellyfishEntity>> BOX_JELLYFISH             = HELPER.createLivingEntity("box_jellyfish", BoxJellyfishEntity::new, EntityClassification.WATER_CREATURE, 0.75F, 0.625F);
	public static final RegistryObject<EntityType<CassiopeaJellyfishEntity>> CASSIOPEA_JELLYFISH = HELPER.createLivingEntity("cassiopea_jellyfish", CassiopeaJellyfishEntity::new, EntityClassification.WATER_CREATURE, 0.6875F, 0.25F);
	public static final RegistryObject<EntityType<ImmortalJellyfishEntity>> IMMORTAL_JELLYFISH   = HELPER.createLivingEntity("immortal_jellyfish", ImmortalJellyfishEntity::new, EntityClassification.WATER_CREATURE, 0.625F, 0.5F);

	public static void registerAttributes() {
		GlobalEntityTypeAttributes.put(NAUTILUS.get(), NautilusEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(PIKE.get(), PikeEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(LIONFISH.get(), LionfishEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(THRASHER.get(), ThrasherEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(GREAT_THRASHER.get(), GreatThrasherEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(FLARE.get(), FlareEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(ULULU.get(), UluluEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(GOOSE.get(), GooseEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(GLOW_SQUID.get(), GlowSquidEntity.func_234227_m_().create());
		
		GlobalEntityTypeAttributes.put(BOX_JELLYFISH.get(), BoxJellyfishEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(IMMORTAL_JELLYFISH.get(), ImmortalJellyfishEntity.registerAttributes().create());
	}

	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.NAUTILUS.get(), NautilusRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.PIKE.get(), PikeRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.LIONFISH.get(), LionfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.THRASHER.get(), ThrasherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GREAT_THRASHER.get(), GreatThrasherRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.FLARE.get(), FlareRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.SONAR_WAVE.get(), SonarWaveRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.ULULU.get(), UluluRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GOOSE.get(), GooseRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.GLOW_SQUID.get(), GlowSquidRenderer::new);

		RenderingRegistry.registerEntityRenderingHandler(UAEntities.BOX_JELLYFISH.get(), BoxJellyfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(UAEntities.IMMORTAL_JELLYFISH.get(), ImmortalJellyfishRenderer::new);
	}
}