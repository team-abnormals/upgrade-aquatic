package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.teamabnormals.blueprint.client.ACRenderTypes;
import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.upgrade_aquatic.client.model.FlareModel;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.FlareEntity;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlareEyesRenderLayer<T extends FlareEntity, M extends FlareModel<T>> extends RenderLayer<T, M> {
	private static final ResourceLocation EYES_LAYER = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/flare/flare_eyes.png");

	public FlareEyesRenderLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T flare, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientInfo.MINECRAFT.getTextureManager().bind(EYES_LAYER);

		VertexConsumer ivertexbuilder = bufferIn.getBuffer(ACRenderTypes.getEmissiveEntity(EYES_LAYER));

		this.getParentModel().setupAnim(flare, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}