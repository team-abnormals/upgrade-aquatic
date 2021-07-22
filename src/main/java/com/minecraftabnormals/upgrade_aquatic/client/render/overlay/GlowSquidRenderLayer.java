package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.minecraftabnormals.abnormals_core.client.ACRenderTypes;
import com.minecraftabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.minecraftabnormals.upgrade_aquatic.client.model.GlowSquidModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.GlowSquidEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowSquidRenderLayer extends LayerRenderer<GlowSquidEntity, GlowSquidModel> {
	private final GlowSquidModel model;

	public GlowSquidRenderLayer(IEntityRenderer<GlowSquidEntity, GlowSquidModel> renderer) {
		super(renderer);
		this.model = new GlowSquidModel(true);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, GlowSquidEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		this.model.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.model.renderToBuffer(matrixStackIn, bufferIn.getBuffer(ACRenderTypes.getEmissiveTransluscentEntity(GlowSquidSpriteUploader.ATLAS_LOCATION, false)), packedLightIn, LivingRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1, 1, 1, 1);
	}
}