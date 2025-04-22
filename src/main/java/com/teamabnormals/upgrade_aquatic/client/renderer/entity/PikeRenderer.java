package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.upgrade_aquatic.client.model.PikeModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.GlowingPikeRenderLayer;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.PikeCarriedItemRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.teamabnormals.upgrade_aquatic.core.other.UAModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PikeRenderer extends MobRenderer<Pike, PikeModel<Pike>> {

	public PikeRenderer(EntityRendererProvider.Context context) {
		super(context, new PikeModel<>(context.bakeLayer(UAModelLayers.PIKE)), 0.6F);
		this.addLayer(new GlowingPikeRenderLayer<>(this));
		this.addLayer(new PikeCarriedItemRenderLayer(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(Pike pike) {
		return pike.getVariant().value().assetId().withPath(assetId -> "textures/" + assetId + ".png");
	}

	@Override
	protected void setupRotations(Pike pike, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
		super.setupRotations(pike, matrixStack, ageInTicks, rotationYaw, partialTicks, scale);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		matrixStack.mulPose(Axis.YP.rotationDegrees(f));
		if (!pike.isInWater()) {
			matrixStack.translate(0.1F, 0.1F, -0.1F);
			matrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	protected void scale(Pike pike, PoseStack matrixStack, float partialTickTime) {
		float scale = pike.getVariant().value().size();
		matrixStack.scale(scale, scale, scale);
	}

}