package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.PikeModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.GlowingPikeRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.PikeCarriedItemRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PikeRenderer extends MobRenderer<PikeEntity, PikeModel<PikeEntity>> {

	public PikeRenderer(EntityRenderDispatcher manager) {
		super(manager, new PikeModel<>(), 0.6F);
		this.addLayer(new GlowingPikeRenderLayer<>(this));
		this.addLayer(new PikeCarriedItemRenderLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(PikeEntity pike) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, String.format("textures/entity/pike/%s.png", pike.getPikeType().name().toLowerCase()));
	}

	@Override
	protected void setupRotations(PikeEntity pike, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(pike, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * Mth.sin(0.6F * ageInTicks);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!pike.isInWater()) {
			matrixStack.translate(0.1F, 0.1F, -0.1F);
			matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

	@Override
	protected void scale(PikeEntity pike, PoseStack matrixStack, float partialTickTime) {
		float scale = pike.getPikeType().pikeSize.renderSize;
		matrixStack.scale(scale, scale, scale);
	}

}