package com.teamabnormals.upgrade_aquatic.client.render.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.core.config.Config;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class OverlayInsomnia {
	private static final Minecraft MC = Minecraft.getInstance();
	
	@SubscribeEvent
    public static void renderInsomniaOverlay(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
			int scaledWidth = MC.mainWindow.getScaledWidth();
			int scaledHeight = MC.mainWindow.getScaledHeight();
			ClientPlayerEntity player = MC.player;
			StatisticsManager statisticsManager = player.getStats();
			int sleepTime = statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
			int configuredTime = Config.CLIENT.daysTillRenderInsomniaOverlay.get();
			float opacity = 0;
			if(sleepTime == 24000 * configuredTime) {
				opacity = 0.25F;
			} else if(sleepTime == 24000 * configuredTime + 100) {
				opacity = 0.45F;
			} else if(sleepTime == 24000 * configuredTime + 200) {
				opacity = 0.65F;
			} else if(sleepTime == 24000 * configuredTime + 300) {
				opacity = 0.85F;
			} else if(sleepTime == 24000 * configuredTime + 400) {
				opacity = 0.90F;
			} else if(sleepTime >= 24000 * configuredTime + 500) {
				opacity = 1F;
			} else if(sleepTime < 24000 * configuredTime) {
				opacity = 0F;
			}
			if(MC.gameSettings.thirdPersonView == 0 && Config.CLIENT.daysTillRenderInsomniaOverlay.get() != 0 && MC.player.dimension == DimensionType.OVERWORLD) {
				GlStateManager.pushMatrix();
			
				MC.textureManager.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/overlay/insomnia.png"));
				GlStateManager.enableBlend();
				GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				GlStateManager.color3f(1.0F, 1.0F, 1.0F);
				GlStateManager.color4f(1.0F, 1.0F, 1.0F, opacity);
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();
				bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
				bufferbuilder.pos(0.0D, (double)scaledHeight, -90.0D).tex(0.0D, 1.0D).endVertex();
				bufferbuilder.pos((double)scaledWidth, (double)scaledHeight, -90.0D).tex(1.0D, 1.0D).endVertex();
				bufferbuilder.pos((double)scaledWidth, 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
				bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
				tessellator.draw();
				
				GlStateManager.popMatrix();
			}
		}
	}
	
}
