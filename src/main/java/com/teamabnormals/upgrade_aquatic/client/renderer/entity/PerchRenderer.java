package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.upgrade_aquatic.client.model.PerchModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Perch;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PerchRenderer extends MobRenderer<Perch, PerchModel<Perch>> {
	private static final ResourceLocation PERCH_LOCATION = UpgradeAquatic.location("textures/entity/perch.png");

	public PerchRenderer(EntityRendererProvider.Context context) {
		super(context, new PerchModel<>(context.bakeLayer(PerchModel.LOCATION)), 0.3F);
	}

	public ResourceLocation getTextureLocation(Perch entity) {
		return PERCH_LOCATION;
	}

	protected void setupRotations(Perch entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks, scale);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStackIn.translate(0.1F, 0.1F, -0.1F);
			matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}