package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.client.model.*;
import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.BoxJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.CassiopeaJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.model.jellyfish.ImmortalJellyfishModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.*;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.BoxJellyfishRenderer;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.CassiopeaJellyfishRenderer;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish.ImmortalJellyfishRenderer;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UAModelLayers {
	public static final ModelLayerLocation NAUTILUS = register("nautilus");
	public static final ModelLayerLocation PIKE = register("pike");
	public static final ModelLayerLocation LIONFISH = register("lionfish");
	public static final ModelLayerLocation PERCH = register("perch");
	public static final ModelLayerLocation THRASHER = register("thrasher");
	public static final ModelLayerLocation FLARE = register("flare");
	public static final ModelLayerLocation SONAR_WAVE = register("sonar_wave");
	public static final ModelLayerLocation GLOW_SQUID = register("glow_squid");
	public static final ModelLayerLocation GOOSE = register("goose");
	public static final ModelLayerLocation BOX_JELLYFISH = register("box_jellyfish");
	public static final ModelLayerLocation CASSIOPEA_JELLYFISH = register("cassiopea_jellyfish");
	public static final ModelLayerLocation IMMORTAL_JELLYFISH = register("immortal_jellyfish");

	public static ModelLayerLocation register(String name) {
		return register(name, "main");
	}

	public static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(UpgradeAquatic.location(name), layer);
	}

	@SubscribeEvent
	public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
		event.registerLayerDefinition(NAUTILUS, NautilusModel::createBodyLayer);
		event.registerLayerDefinition(PIKE, PikeModel::createBodyLayer);
		event.registerLayerDefinition(LIONFISH, LionfishModel::createBodyLayer);
		event.registerLayerDefinition(PERCH, PerchModel::createBodyLayer);
		event.registerLayerDefinition(THRASHER, ThrasherModel::createBodyLayer);
		event.registerLayerDefinition(FLARE, FlareModel::createBodyLayer);
		event.registerLayerDefinition(SONAR_WAVE, SonarWaveModel::createBodyLayer);
		event.registerLayerDefinition(GLOW_SQUID, UAGlowSquidModel::createBodyLayer);
		event.registerLayerDefinition(GOOSE, GooseModel::createBodyLayer);
		event.registerLayerDefinition(BOX_JELLYFISH, BoxJellyfishModel::createBodyLayer);
		event.registerLayerDefinition(CASSIOPEA_JELLYFISH, CassiopeaJellyfishModel::createBodyLayer);
		event.registerLayerDefinition(IMMORTAL_JELLYFISH, ImmortalJellyfishModel::createBodyLayer);
	}

	@SubscribeEvent
	public static void registerRenderers(RegisterRenderers event) {
		event.registerEntityRenderer(UAEntityTypes.NAUTILUS.get(), NautilusRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.PIKE.get(), PikeRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.LIONFISH.get(), LionfishRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.PERCH.get(), PerchRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.THRASHER.get(), ThrasherRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.GREAT_THRASHER.get(), GreatThrasherRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.FLARE.get(), FlareRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.SONAR_WAVE.get(), SonarWaveRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.GOOSE.get(), GooseRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.BOX_JELLYFISH.get(), BoxJellyfishRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.CASSIOPEA_JELLYFISH.get(), CassiopeaJellyfishRenderer::new);
		event.registerEntityRenderer(UAEntityTypes.IMMORTAL_JELLYFISH.get(), ImmortalJellyfishRenderer::new);
		event.registerEntityRenderer(EntityType.GLOW_SQUID, UAGlowSquidRenderer::new);
	}
}
