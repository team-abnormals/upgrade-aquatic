package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.minecraftabnormals.upgrade_aquatic.client.UARenderTypes;
import com.minecraftabnormals.upgrade_aquatic.client.model.ModelUlulu;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityUlulu;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.abnormals_core.client.ClientInfo;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class RenderLayerUluluEyes<T extends EntityUlulu, M extends ModelUlulu<T>> extends LayerRenderer<T, M> {

	private static final ResourceLocation EYES_LAYER = new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/ululu/ululu_emissive.png");

	
	public RenderLayerUluluEyes(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T ululu,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		ClientInfo.MINECRAFT.getTextureManager().bindTexture(EYES_LAYER);

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(UARenderTypes.getEmissiveEntity(EYES_LAYER));
		
		this.getEntityModel().setRotationAngles(ululu, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		
	}
	
	

}
