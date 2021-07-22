package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.minecraftabnormals.abnormals_core.client.ACRenderTypes;
import com.minecraftabnormals.abnormals_core.client.ClientInfo;
import com.minecraftabnormals.upgrade_aquatic.client.model.UluluModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.UluluEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class UluluEyesRenderLayer<T extends UluluEntity, M extends UluluModel<T>> extends LayerRenderer<T, M> {
	private static final ResourceLocation EYES_LAYER = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/ululu/ululu_emissive.png");

	public UluluEyesRenderLayer(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T ululu, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientInfo.MINECRAFT.getTextureManager().bind(EYES_LAYER);

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(ACRenderTypes.getEmissiveEntity(EYES_LAYER));
		
		this.getParentModel().setupAnim(ululu, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}