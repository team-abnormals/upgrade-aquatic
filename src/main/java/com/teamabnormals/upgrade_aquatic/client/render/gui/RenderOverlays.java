package com.teamabnormals.upgrade_aquatic.client.render.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.core.config.Config;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class RenderOverlays {
	private static final Minecraft MC = Minecraft.getInstance();
	
	@SubscribeEvent
	public static void renderOverlays(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
			int scaledWidth = MC.getMainWindow().getScaledWidth();
			int scaledHeight = MC.getMainWindow().getScaledHeight();
			ClientPlayerEntity player = MC.player;
			StatisticsManager statisticsManager = player.getStats();
			int sleepTime = statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
			int configuredTime = Config.CLIENT.daysTillRenderInsomniaOverlay.get();
			float opacity = 0;
			if (sleepTime == 24000 * configuredTime) {
				opacity = 0.25F;
			} else if (sleepTime == 24000 * configuredTime + 100) {
				opacity = 0.45F;
			} else if (sleepTime == 24000 * configuredTime + 200) {
				opacity = 0.65F;
			} else if (sleepTime == 24000 * configuredTime + 300) {
				opacity = 0.85F;
			} else if (sleepTime == 24000 * configuredTime + 400) {
				opacity = 0.90F;
			} else if (sleepTime >= 24000 * configuredTime + 500) {
				opacity = 1F;
			} else if (sleepTime < 24000 * configuredTime) {
				opacity = 0F;
			}
			if (MC.gameSettings.thirdPersonView == 0 && Config.CLIENT.daysTillRenderInsomniaOverlay.get() != 0 && MC.player.dimension == DimensionType.OVERWORLD) {
				RenderSystem.pushMatrix();
				
				MC.textureManager.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/overlay/insomnia.png"));
				RenderSystem.enableBlend();
				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				RenderSystem.color3f(1.0F, 1.0F, 1.0F);
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, opacity);
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();
				bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
				bufferbuilder.pos(0.0D, (double) scaledHeight, -90.0D).tex(0.0F, 1.0F).endVertex();
				bufferbuilder.pos((double) scaledWidth, (double) scaledHeight, -90.0D).tex(1.0F, 1.0F).endVertex();
				bufferbuilder.pos((double) scaledWidth, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
				bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
				tessellator.draw();
				
				RenderSystem.popMatrix();
			}
		}
	}
	
	@SubscribeEvent
	public static void renderScuteOverAir(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
			int scaledWidth = MC.getMainWindow().getScaledWidth();
			int scaledHeight = MC.getMainWindow().getScaledHeight();
			ClientPlayerEntity player = MC.player;
			boolean inWater = player.areEyesInFluid(FluidTags.WATER);
			if (inWater) {
				ItemStack turtleHelmet = ItemStack.EMPTY;
				for (ItemStack stack : player.getArmorInventoryList()) {
					if (stack.getItem() == Items.TURTLE_HELMET) {
						turtleHelmet = stack;
					}
				}
				
				if (!turtleHelmet.isEmpty()) {
					event.setCanceled(true);
					
					RenderSystem.pushMatrix();
					RenderSystem.enableBlend();
					int left = scaledWidth / 2 + 91;
					int top = scaledHeight - ForgeIngameGui.right_height;
					int durability = turtleHelmet.getDamage();
					int maxDurability = turtleHelmet.getMaxDamage();
					
					MC.textureManager.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/overlay/scute_bubble_depleted.png"));
					for (int i = 0; i < 10; i++) {
						int l = left - (i * 8) - 9;
						int l2 = l + 9;
						int t = top;
						int t2 = t + 9;
						Tessellator tessellator = Tessellator.getInstance();
						BufferBuilder bufferbuilder = tessellator.getBuffer();
						bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
						bufferbuilder.pos(l, t2, 0).tex(0, 1).endVertex();
						bufferbuilder.pos(l2, t2, 0).tex(1, 1).endVertex();
						bufferbuilder.pos(l2, t, 0).tex(1, 0).endVertex();
						bufferbuilder.pos(l, t, 0).tex(0, 0).endVertex();
						tessellator.draw();
					}
					MC.textureManager.bindTexture(new ResourceLocation(Reference.MODID, "textures/gui/overlay/scute_bubble.png"));
					double amount = MathHelper.clamp(10 - Math.floor((double) durability / maxDurability * 10.0), 1, 10);
					for (int i = 0; i < amount; i++) {
						int l = left - (i * 8) - 9;
						int l2 = l + 9;
						int t = top;
						int t2 = t + 9;
						Tessellator tessellator = Tessellator.getInstance();
						BufferBuilder bufferbuilder = tessellator.getBuffer();
						bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
						bufferbuilder.pos(l, t2, 0).tex(0, 1).endVertex();
						bufferbuilder.pos(l2, t2, 0).tex(1, 1).endVertex();
						bufferbuilder.pos(l2, t, 0).tex(1, 0).endVertex();
						bufferbuilder.pos(l, t, 0).tex(0, 0).endVertex();
						tessellator.draw();
					}
					ForgeIngameGui.right_height += 10;
					
					RenderSystem.disableBlend();
					RenderSystem.popMatrix();
				}
			}
		}
		if(event.getType() == ElementType.TEXT) {
			if(MC.player.isPassenger()) {
				String formattedMessage = I18n.format("mount.onboard", MC.gameSettings.keyBindSneak.getLocalizedName());
				if(MC.ingameGUI.overlayMessage.equals(formattedMessage) && MC.player.getRidingEntity() instanceof EntityThrasher) {
					MC.ingameGUI.setOverlayMessage("", false);
				}
			}
		}
	}
}