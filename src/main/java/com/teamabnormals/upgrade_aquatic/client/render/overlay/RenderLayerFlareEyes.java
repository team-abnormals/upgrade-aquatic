package com.teamabnormals.upgrade_aquatic.client.render.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;
import com.teamabnormals.upgrade_aquatic.client.UARenderTypes;
import com.teamabnormals.upgrade_aquatic.client.model.ModelFlare;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityFlare;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayerFlareEyes<T extends EntityFlare, M extends ModelFlare<T>> extends LayerRenderer<T, M> {	
	private static final ResourceLocation EYES_LAYER = new ResourceLocation(Reference.MODID, "textures/entity/flare/flare_eyes.png");
	
	public RenderLayerFlareEyes(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T flare, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientInfo.MINECRAFT.getTextureManager().bindTexture(EYES_LAYER);

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(UARenderTypes.getEmissiveEntity(EYES_LAYER));
		
		this.getEntityModel().setRotationAngles(flare, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}