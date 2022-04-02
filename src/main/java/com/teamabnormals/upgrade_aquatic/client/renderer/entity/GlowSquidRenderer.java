package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.GlowSquidModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.GlowSquidRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.GlowSquid;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;

/**
 * @author Ocelot
 */
public class GlowSquidRenderer extends MobRenderer<GlowSquid, GlowSquidModel> {
	public GlowSquidRenderer(EntityRenderDispatcher renderManagerIn) {
		super(renderManagerIn, new GlowSquidModel(false), 0.7F);
		this.addLayer(new GlowSquidRenderLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(GlowSquid entity) {
		return MissingTextureAtlasSprite.getLocation();
	}

	@Override
	protected void setupRotations(GlowSquid entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		float f = Mth.lerp(partialTicks, entityLiving.xBodyRotO, entityLiving.xBodyRot);
		float f1 = Mth.lerp(partialTicks, entityLiving.zBodyRotO, entityLiving.zBodyRot);
		matrixStackIn.translate(0.0D, 0.5D, 0.0D);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - rotationYaw));
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(f));
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f1));
		matrixStackIn.translate(0.0D, -1.2F, 0.0D);
	}

	@Override
	protected float getBob(GlowSquid livingBase, float partialTicks) {
		return Mth.lerp(partialTicks, livingBase.oldTentacleAngle, livingBase.tentacleAngle);
	}
}