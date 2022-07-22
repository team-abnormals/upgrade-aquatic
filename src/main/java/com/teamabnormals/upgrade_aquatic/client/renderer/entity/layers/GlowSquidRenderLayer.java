package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.GlowSquidSpriteUploader;
import com.teamabnormals.upgrade_aquatic.client.model.UAGlowSquidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.GlowSquid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowSquidRenderLayer<T extends GlowSquid, M extends UAGlowSquidModel<T>> extends RenderLayer<T, M> {
	private final UAGlowSquidModel<T> model;

	public GlowSquidRenderLayer(UAGlowSquidModel<T> model, RenderLayerParent<T, M> renderer) {
		super(renderer);
		this.model = model;
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T squid, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		this.model.setupAnim(squid, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.model.renderToBuffer(matrixStackIn, bufferIn.getBuffer(BlueprintRenderTypes.getUnshadedTranslucentEntity(GlowSquidSpriteUploader.ATLAS_LOCATION, false)), packedLightIn, LivingEntityRenderer.getOverlayCoords(squid, 0.0F), 1, 1, 1, 1);
	}
}