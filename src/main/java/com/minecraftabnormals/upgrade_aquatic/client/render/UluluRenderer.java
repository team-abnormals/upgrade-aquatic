package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.UluluModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.UluluEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class UluluRenderer extends MobRenderer<UluluEntity, UluluModel<UluluEntity>> {

	public UluluRenderer(EntityRenderDispatcher manager) {
		super(manager, new UluluModel<>(), 0.9F);
	}

	public UluluRenderer(EntityRenderDispatcher p_i50961_1_, UluluModel<UluluEntity> p_i50961_2_, float p_i50961_3_) {
		super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
	}

	public void render(UluluEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		this.shadowRadius = 0.75F * (float) entityIn.getUluluSize();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(UluluEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/ululu/ululu_dry.png");
	}

	protected void scale(UluluEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(0.999F, 0.999F, 0.999F);
		matrixStackIn.translate(0.0D, 0.001F, 0.0D);
		float f1 = (float) entitylivingbaseIn.getUluluSize();
		float f2 = Mth.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

}
