package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.client.model.NautilusModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Nautilus;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.tags.FluidTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class NautilusRenderer extends MobRenderer<Nautilus, NautilusModel<Nautilus>> {
	private static final Map<List<String>, String> SKINS = Util.make(Maps.newHashMap(), (skins) -> {
		skins.put(Arrays.asList("smelly", "thefaceofgaming"), "smelly");
		skins.put(Arrays.asList("abnormal", "abnautilus", "abnortilus", "mca"), "mca");
		skins.put(Arrays.asList("five", "epic"), "five");
		skins.put(Arrays.asList("neon membrane", "cell membrane", "cell"), "cell");
		skins.put(Arrays.asList("tb"), "tb");
		skins.put(Arrays.asList("bagel", "shy guy", "legobagel"), "bagel");
		skins.put(Arrays.asList("sadcat"), "sadcat");
		skins.put(Arrays.asList("cameron", "cam", "cringe"), "cameron");
		skins.put(Arrays.asList("snake", "snautilus", "snakeblock", "snake block"), "snake_block");
		skins.put(Arrays.asList("snail", "snail nautilus"), "snail");
	});

	public NautilusRenderer(EntityRenderDispatcher renderManager) {
		super(renderManager, new NautilusModel<>(), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(Nautilus nautilus) {
		String textureSuffix = "";

		if (nautilus.hasCustomName()) {
			String name = nautilus.getName().getString().toLowerCase().trim();
			for (Map.Entry<List<String>, String> entries : SKINS.entrySet()) {
				if (entries.getKey().contains(name)) {
					textureSuffix = "_" + entries.getValue();
				}
			}
		}
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/nautilus/nautilus" + textureSuffix + ".png");
	}

	@Override
	protected void setupRotations(Nautilus nautilus, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(nautilus, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 0.3F * Mth.sin(0.6F * ageInTicks);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!nautilus.isInWater() && !nautilus.isEyeInFluid(FluidTags.WATER)) {
			matrixStack.translate(0.2F, 0.14F, 0.0F);
			matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}
}
