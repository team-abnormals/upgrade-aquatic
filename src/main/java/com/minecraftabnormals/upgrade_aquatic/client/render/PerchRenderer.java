package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.PerchModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.PerchEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class PerchRenderer extends MobRenderer<PerchEntity, PerchModel<PerchEntity>> {
	private static final ResourceLocation PERCH_LOCATION = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/perch.png");

	public PerchRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PerchModel<>(), 0.3F);
	}

	public ResourceLocation getEntityTexture(PerchEntity entity) {
		return PERCH_LOCATION;
	}

	protected void applyRotations(PerchEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStackIn.translate(0.1F, 0.1F, -0.1F);
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}
}