package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.teamabnormals.abnormals_core.client.ACRenderTypes;
import com.teamabnormals.abnormals_core.client.ClientInfo;
import com.minecraftabnormals.upgrade_aquatic.client.model.PikeModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeType.PikeRarity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowingPikeRenderLayer<T extends PikeEntity, M extends PikeModel<T>> extends LayerRenderer<T, M> {
	
	public GlowingPikeRenderLayer(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T pike, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (pike.getPikeType().rarity != PikeRarity.LEGENDARY) return;
		
		ClientInfo.MINECRAFT.getTextureManager().bindTexture(this.getPikeOverlayTexture(pike));

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(ACRenderTypes.getEmissiveEntity(this.getPikeOverlayTexture(pike)));
		
		this.getEntityModel().setRotationAngles(pike, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	private ResourceLocation getPikeOverlayTexture(PikeEntity pike) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/pike/pike_" + pike.getPikeType().id + "_glow.png");
	}
	
}