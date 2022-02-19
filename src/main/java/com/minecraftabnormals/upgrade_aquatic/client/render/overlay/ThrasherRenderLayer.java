package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.teamabnormals.blueprint.client.ACRenderTypes;
import com.teamabnormals.blueprint.client.ClientInfo;
import com.minecraftabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.GreatThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
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
public class ThrasherRenderLayer<T extends ThrasherEntity, M extends ThrasherModel<T>> extends RenderLayer<T, M> {
	private static final ResourceLocation THRASHER_FROST = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/thrasher_emissive.png");
	private static final ResourceLocation GREAT_THRASHER_FROST = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/great_thrasher_emissive.png");

	public ThrasherRenderLayer(RenderLayerParent<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T thrasher, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientInfo.MINECRAFT.getTextureManager().bind(this.getThrasherFrostLayer(thrasher));

		int stunnedAnimation = (int) (thrasher.STUNNED_ANIMATION.getAnimationProgress() * 240);
		VertexConsumer ivertexbuilder = bufferIn.getBuffer(ACRenderTypes.getEmissiveEntity(this.getThrasherFrostLayer(thrasher)));

		this.getParentModel().setupAnim(thrasher, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, stunnedAnimation, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	public ResourceLocation getThrasherFrostLayer(ThrasherEntity thrasher) {
		return thrasher instanceof GreatThrasherEntity ? GREAT_THRASHER_FROST : THRASHER_FROST;
	}
}