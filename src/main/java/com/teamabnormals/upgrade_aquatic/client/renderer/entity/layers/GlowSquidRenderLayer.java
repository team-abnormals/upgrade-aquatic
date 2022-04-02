package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.teamabnormals.blueprint.client.ACRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.teamabnormals.upgrade_aquatic.client.model.GlowSquidModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.GlowSquid;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowSquidRenderLayer extends RenderLayer<GlowSquid, GlowSquidModel> {
	private final GlowSquidModel model;

	public GlowSquidRenderLayer(RenderLayerParent<GlowSquid, GlowSquidModel> renderer) {
		super(renderer);
		this.model = new GlowSquidModel(true);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, GlowSquid entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		this.model.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.model.renderToBuffer(matrixStackIn, bufferIn.getBuffer(ACRenderTypes.getEmissiveTransluscentEntity(GlowSquidSpriteUploader.ATLAS_LOCATION, false)), packedLightIn, LivingEntityRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1, 1, 1, 1);
	}
}