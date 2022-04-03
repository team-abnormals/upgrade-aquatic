package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.PerchModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Perch;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;

public class PerchRenderer extends MobRenderer<Perch, PerchModel<Perch>> {
	private static final ResourceLocation PERCH_LOCATION = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/perch.png");

	public PerchRenderer(EntityRendererProvider.Context context) {
		super(context, new PerchModel<>(context.bakeLayer(PerchModel.LOCATION)), 0.3F);
	}

	public ResourceLocation getTextureLocation(Perch entity) {
		return PERCH_LOCATION;
	}

	protected void setupRotations(Perch entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStackIn.translate(0.1F, 0.1F, -0.1F);
			matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}
}