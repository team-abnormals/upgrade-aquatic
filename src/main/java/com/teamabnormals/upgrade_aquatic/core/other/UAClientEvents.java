package com.teamabnormals.upgrade_aquatic.core.other;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.ChatFormatting;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, value = Dist.CLIENT)
public class UAClientEvents {

	@SubscribeEvent
	public static void onEntityRenderPre(RenderLivingEvent.Pre<?, ?> event) {
		if (event.getEntity() instanceof LocalPlayer clientPlayer) {
			if (clientPlayer.getVehicle() instanceof Thrasher thrasher) {
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "f_20931_");
				ObfuscationReflectionHelper.setPrivateValue(LivingEntity.class, clientPlayer, 1.0F, "f_20932_");
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
		ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
		Player player = event.getEntity();
		if (name == null || player == null)
			return;

		if (player.getAbilities().instabuild && UAConfig.CLIENT.showUnobtainableDescription.get() && name.getNamespace().equals(UpgradeAquatic.MOD_ID)) {
			String id = name.getPath();
			if (id.contains("jelly"))
				event.getToolTip().add(Component.translatable("tooltip.upgrade_aquatic.unobtainable").withStyle(ChatFormatting.GRAY));
		}
	}
}