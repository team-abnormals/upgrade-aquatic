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
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UAModelLayers {
    // TODO: Move ModelLayerLocations here instead of inside each Model class
    
    @SubscribeEvent
    public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NautilusModel.LOCATION, NautilusModel::createBodyLayer);
        event.registerLayerDefinition(PikeModel.LOCATION, PikeModel::createBodyLayer);
        event.registerLayerDefinition(LionfishModel.LOCATION, LionfishModel::createBodyLayer);
        event.registerLayerDefinition(PerchModel.LOCATION, PerchModel::createBodyLayer);
        event.registerLayerDefinition(ThrasherModel.LOCATION, ThrasherModel::createBodyLayer);
        event.registerLayerDefinition(FlareModel.LOCATION, FlareModel::createBodyLayer);
        event.registerLayerDefinition(SonarWaveModel.LOCATION, SonarWaveModel::createBodyLayer);
        event.registerLayerDefinition(UAGlowSquidModel.LOCATION, UAGlowSquidModel::createBodyLayer);
        event.registerLayerDefinition(GooseModel.LOCATION, GooseModel::createBodyLayer);
        event.registerLayerDefinition(BoxJellyfishModel.LOCATION, BoxJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(CassiopeaJellyfishModel.LOCATION, CassiopeaJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(ImmortalJellyfishModel.LOCATION, ImmortalJellyfishModel::createBodyLayer);
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
