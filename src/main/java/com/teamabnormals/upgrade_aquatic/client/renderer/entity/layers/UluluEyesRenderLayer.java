package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.model.UluluModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Ululu;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class UluluEyesRenderLayer<T extends Ululu, M extends UluluModel<T>> extends RenderLayer<T, M> {
	private static final ResourceLocation EYES_LAYER = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/ululu/ululu_emissive.png");

	public UluluEyesRenderLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, T ululu, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getParentModel().setupAnim(ululu, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getParentModel().renderToBuffer(poseStack, bufferIn.getBuffer(BlueprintRenderTypes.getUnshadedCutoutEntity(EYES_LAYER, false)), 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}