package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.teamabnormals.blueprint.client.ACRenderTypes;
import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.upgrade_aquatic.client.model.PikeModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.PikeType.PikeRarity;
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
public class GlowingPikeRenderLayer<T extends Pike, M extends PikeModel<T>> extends RenderLayer<T, M> {

	public GlowingPikeRenderLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T pike, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (pike.getPikeType().rarity != PikeRarity.LEGENDARY) return;

		ClientInfo.MINECRAFT.getTextureManager().bind(this.getPikeOverlayTexture(pike));

		VertexConsumer ivertexbuilder = bufferIn.getBuffer(ACRenderTypes.getEmissiveEntity(this.getPikeOverlayTexture(pike)));

		this.getParentModel().setupAnim(pike, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	private ResourceLocation getPikeOverlayTexture(Pike pike) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, String.format("textures/entity/pike/%s_glow.png", pike.getPikeType().name().toLowerCase()));
	}

}