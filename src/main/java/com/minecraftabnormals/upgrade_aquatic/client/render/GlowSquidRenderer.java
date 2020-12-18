package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.GlowSquidModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.GlowSquidRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.GlowSquidEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.MissingTextureSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

/**
 * @author Ocelot
 */
public class GlowSquidRenderer extends MobRenderer<GlowSquidEntity, GlowSquidModel> {
	public GlowSquidRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GlowSquidModel(false), 0.7F);
		this.addLayer(new GlowSquidRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(GlowSquidEntity entity) {
		return MissingTextureSprite.getLocation();
	}

	@Override
	protected void applyRotations(GlowSquidEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, entityLiving.prevSquidPitch, entityLiving.squidPitch);
		float f1 = MathHelper.lerp(partialTicks, entityLiving.prevSquidYaw, entityLiving.squidYaw);
		matrixStackIn.translate(0.0D, 0.5D, 0.0D);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f));
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f1));
		matrixStackIn.translate(0.0D, -1.2F, 0.0D);
	}

	@Override
	protected float handleRotationFloat(GlowSquidEntity livingBase, float partialTicks) {
		return MathHelper.lerp(partialTicks, livingBase.lastTentacleAngle, livingBase.tentacleAngle);
	}
}