package com.minecraftabnormals.upgrade_aquatic.core.events;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UAConfig;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void onEntityRenderPre(RenderLivingEvent.Pre<?, ?> event) {
		if (event.getEntity() instanceof ClientPlayerEntity) {
			ClientPlayerEntity clientPlayer = (ClientPlayerEntity) event.getEntity();
			if (clientPlayer.getVehicle() instanceof ThrasherEntity) {
				ThrasherEntity thrasher = (ThrasherEntity) clientPlayer.getVehicle();
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "swimAmount");
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "swimAmountO");
				clientPlayer.xRot = 0.0F;
				clientPlayer.yRot = thrasher.yRot + (90.0F % 360);
				clientPlayer.yBodyRot = thrasher.yBodyRot + (90.0F % 360);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerRenderPre(RenderPlayerEvent.Pre event) {
		MatrixStack stack = event.getMatrixStack();
		stack.pushPose();
		if (event.getEntityLiving().getVehicle() instanceof ThrasherEntity) {
			ThrasherEntity thrasher = (ThrasherEntity) event.getEntityLiving().getVehicle();
			double dx = Math.cos((MathHelper.lerp(event.getPartialRenderTick(), thrasher.yRotO, thrasher.yRot)) * Math.PI / 180.0D);
			double dz = Math.sin((MathHelper.lerp(event.getPartialRenderTick(), thrasher.yRotO, thrasher.yRot)) * Math.PI / 180.0D);

			stack.translate((float) dx, 0.0F, (float) dz);
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerRenderPost(RenderPlayerEvent.Post event) {
		event.getMatrixStack().popPose();
	}

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Item item = event.getItemStack().getItem();
		ResourceLocation name = item.getRegistryName();
		PlayerEntity player = event.getPlayer();
		if (name == null || player == null)
			return;

		if (player.abilities.instabuild && UAConfig.CLIENT.showUnobtainableDescription.get() && name.getNamespace().equals(UpgradeAquatic.MOD_ID)) {
			String id = name.getPath();
			if (id.contains("jelly") || id.contains("tongue_kelp") || id.contains("polar_kelp") || id.contains("ochre_kelp") || id.contains("thorny_kelp"))
				event.getToolTip().add(new TranslationTextComponent("tooltip.upgrade_aquatic.unobtainable").withStyle(TextFormatting.GRAY));
		}
	}
}