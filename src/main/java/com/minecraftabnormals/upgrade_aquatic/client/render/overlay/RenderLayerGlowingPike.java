package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.minecraftabnormals.upgrade_aquatic.client.UARenderTypes;
import com.minecraftabnormals.upgrade_aquatic.client.model.ModelPike;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.minecraftabnormals.upgrade_aquatic.core.util.Reference;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.abnormals_core.client.ClientInfo;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayerGlowingPike<T extends EntityPike, M extends ModelPike<T>> extends LayerRenderer<T, M> {
	
	public RenderLayerGlowingPike(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T pike, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if(pike.getPikeType() != 7 && pike.getPikeType() != 13 && pike.getPikeType() != 12) return;
		
		ClientInfo.MINECRAFT.getTextureManager().bindTexture(this.getPikeOverlayTexture(pike));

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(UARenderTypes.getEmissiveEntity(this.getPikeOverlayTexture(pike)));
		
		this.getEntityModel().setRotationAngles(pike, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	private ResourceLocation getPikeOverlayTexture(EntityPike pike) {
		return new ResourceLocation(Reference.MODID, "textures/entity/pike/pike_" + pike.getPikeType() + "_glow.png");
	}
	
}