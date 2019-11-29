package com.teamabnormals.upgrade_aquatic.core.events;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class ClientEvents {
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRenderPre(RenderLivingEvent.Pre<?, ?> event) {
		if(event.getEntity() instanceof ClientPlayerEntity) {
			ClientPlayerEntity clientPlayer = (ClientPlayerEntity) event.getEntity();
			if(clientPlayer.getRidingEntity() instanceof EntityThrasher) {
				EntityThrasher thrasher = (EntityThrasher) clientPlayer.getRidingEntity();
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "field_205017_bL");
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "field_205018_bM");
				clientPlayer.rotationPitch = 0.0F;
				clientPlayer.rotationYaw = thrasher.rotationYaw + (90.0F % 360);
				clientPlayer.renderYawOffset = thrasher.renderYawOffset + (90.0F % 360);
			}
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onPlayerRenderPre(RenderPlayerEvent.Pre event) {
		GlStateManager.pushMatrix();
		if(event.getEntityLiving().getRidingEntity() instanceof EntityThrasher) {
			EntityThrasher thrasher = (EntityThrasher) event.getEntityLiving().getRidingEntity();
			double dx = Math.cos((MathHelper.lerp(event.getPartialRenderTick(), thrasher.prevRotationYaw, thrasher.rotationYaw)) * Math.PI / 180.0D);
			double dz = Math.sin((MathHelper.lerp(event.getPartialRenderTick(), thrasher.prevRotationYaw, thrasher.rotationYaw)) * Math.PI / 180.0D);
		
			
			GlStateManager.translatef((float) dx, 0.0F, (float) dz);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onPlayerRenderPost(RenderPlayerEvent.Post event) {
		GlStateManager.popMatrix();
	}
	
}
