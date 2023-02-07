package com.teamabnormals.upgrade_aquatic.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.stats.StatsCounter;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, value = Dist.CLIENT)
public class RenderOverlays {
	private static final Minecraft MC = Minecraft.getInstance();

	@SubscribeEvent
	public static void renderOverlays(RenderGuiOverlayEvent.Post event) {
		if (event.getOverlay() == VanillaGuiOverlay.VIGNETTE.type()) {
			int scaledWidth = MC.getWindow().getGuiScaledWidth();
			int scaledHeight = MC.getWindow().getGuiScaledHeight();
			LocalPlayer player = MC.player;
			StatsCounter statisticsManager = player.getStats();
			int sleepTime = statisticsManager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
			int configuredTime = UAConfig.CLIENT.daysTillRenderInsomniaOverlay.get();
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
			if (MC.options.getCameraType() == CameraType.FIRST_PERSON && UAConfig.CLIENT.daysTillRenderInsomniaOverlay.get() != 0 && MC.player.getCommandSenderWorld().dimension() == Level.OVERWORLD) {
				PoseStack stack = event.getPoseStack();

				stack.pushPose();
				RenderSystem.setShaderTexture(0, new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/gui/overlay/insomnia.png"));
				RenderSystem.enableBlend();
				RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
				Tesselator tessellator = Tesselator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuilder();
				bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
				bufferbuilder.vertex(0.0D, scaledHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
				bufferbuilder.vertex(scaledWidth, scaledHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
				bufferbuilder.vertex(scaledWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
				bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
				tessellator.end();
				stack.popPose();
			}
		}
	}

	@SubscribeEvent
	public static void renderScuteOverAir(RenderGuiOverlayEvent.Pre event) {
		if (event.getOverlay() == VanillaGuiOverlay.AIR_LEVEL.type()) {
			int scaledWidth = MC.getWindow().getGuiScaledWidth();
			int scaledHeight = MC.getWindow().getGuiScaledHeight();
			ForgeGui forgeGui = (ForgeGui) MC.gui;
			LocalPlayer player = MC.player;
			boolean inWater = player.isEyeInFluid(FluidTags.WATER);
			if (inWater && forgeGui.shouldDrawSurvivalElements()) {
				ItemStack turtleHelmet = ItemStack.EMPTY;
				for (ItemStack stack : player.getArmorSlots()) {
					if (stack.getItem() == Items.TURTLE_HELMET) {
						turtleHelmet = stack;
					}
				}

				if (!turtleHelmet.isEmpty()) {
					event.setCanceled(true);

					PoseStack stack = event.getPoseStack();
					stack.pushPose();
					RenderSystem.enableBlend();
					int left = scaledWidth / 2 + 91;
					int top = scaledHeight - forgeGui.rightHeight;
					int durability = turtleHelmet.getDamageValue();
					int maxDurability = turtleHelmet.getMaxDamage();

					RenderSystem.setShaderTexture(0, new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/gui/overlay/scute_bubble_depleted.png"));
					for (int i = 0; i < 10; i++) {
						int l = left - (i * 8) - 9;
						int l2 = l + 9;
						int t = top;
						int t2 = t + 9;
						Tesselator tessellator = Tesselator.getInstance();
						BufferBuilder bufferbuilder = tessellator.getBuilder();
						bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
						bufferbuilder.vertex(l, t2, 0).uv(0, 1).endVertex();
						bufferbuilder.vertex(l2, t2, 0).uv(1, 1).endVertex();
						bufferbuilder.vertex(l2, t, 0).uv(1, 0).endVertex();
						bufferbuilder.vertex(l, t, 0).uv(0, 0).endVertex();
						tessellator.end();
					}
					RenderSystem.setShaderTexture(0, new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/gui/overlay/scute_bubble.png"));
					double amount = Mth.clamp(10 - Math.floor((double) durability / maxDurability * 10.0), 1, 10);
					for (int i = 0; i < amount; i++) {
						int l = left - (i * 8) - 9;
						int l2 = l + 9;
						int t = top;
						int t2 = t + 9;
						Tesselator tessellator = Tesselator.getInstance();
						BufferBuilder bufferbuilder = tessellator.getBuilder();
						bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
						bufferbuilder.vertex(l, t2, 0).uv(0, 1).endVertex();
						bufferbuilder.vertex(l2, t2, 0).uv(1, 1).endVertex();
						bufferbuilder.vertex(l2, t, 0).uv(1, 0).endVertex();
						bufferbuilder.vertex(l, t, 0).uv(0, 0).endVertex();
						tessellator.end();
					}
					forgeGui.rightHeight += 10;

					RenderSystem.disableBlend();
					stack.popPose();
				}
			}
		}

	}

}