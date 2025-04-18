package com.teamabnormals.upgrade_aquatic.core.other;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, value = Dist.CLIENT)
public class UAClientEvents {

	@SubscribeEvent
	public static void onEntityRenderPre(RenderLivingEvent.Pre<?, ?> event) {
		if (event.getEntity() instanceof LocalPlayer clientPlayer) {
			if (clientPlayer.getVehicle() instanceof Thrasher thrasher) {
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "swimAmount");
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "swimAmountO");
				clientPlayer.setXRot(0.0F);
				clientPlayer.setYRot(thrasher.getYRot() + (90.0F % 360));
				clientPlayer.yBodyRot = thrasher.yBodyRot + (90.0F % 360);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerRenderPre(RenderPlayerEvent.Pre event) {
		PoseStack stack = event.getPoseStack();
		stack.pushPose();
		if (event.getEntity().getVehicle() instanceof Thrasher thrasher) {
			double dx = Math.cos((Mth.lerp(event.getPartialTick(), thrasher.yRotO, thrasher.getYRot())) * Math.PI / 180.0D);
			double dz = Math.sin((Mth.lerp(event.getPartialTick(), thrasher.yRotO, thrasher.getYRot())) * Math.PI / 180.0D);

			stack.translate((float) dx, 0.0F, (float) dz);
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onPlayerRenderPost(RenderPlayerEvent.Post event) {
		event.getPoseStack().popPose();
	}

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Item item = event.getItemStack().getItem();
		ResourceLocation name = BuiltInRegistries.ITEM.getKey(item);
		Player player = event.getEntity();
		if (name.equals(BuiltInRegistries.ITEM.getDefaultKey()) || player == null)
			return;

		if (player.getAbilities().instabuild && UAConfig.CLIENT.showUnobtainableDescription.get() && name.getNamespace().equals(UpgradeAquatic.MOD_ID)) {
			String id = name.getPath();
			if (id.contains("jelly"))
				event.getToolTip().add(Component.translatable("tooltip.upgrade_aquatic.unobtainable").withStyle(ChatFormatting.GRAY));
		}
	}
}