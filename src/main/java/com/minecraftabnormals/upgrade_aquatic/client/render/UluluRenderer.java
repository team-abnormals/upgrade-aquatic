package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.UluluModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.UluluEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class UluluRenderer extends MobRenderer<UluluEntity, UluluModel<UluluEntity>> {

	public UluluRenderer(EntityRendererManager manager) {
		super(manager, new UluluModel<>(), 0.9F);
	}
	
	public UluluRenderer(EntityRendererManager p_i50961_1_, UluluModel<UluluEntity> p_i50961_2_, float p_i50961_3_) {
		super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
	}
	
	public void render(UluluEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		this.shadowSize = 0.75F * (float)entityIn.getUluluSize();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(UluluEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/ululu/ululu_dry.png");
	}
	
	protected void preRenderCallback(UluluEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
	      matrixStackIn.scale(0.999F, 0.999F, 0.999F);
	      matrixStackIn.translate(0.0D, (double)0.001F, 0.0D);
	      float f1 = (float)entitylivingbaseIn.getUluluSize();
	      float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
	      float f3 = 1.0F / (f2 + 1.0F);
	      matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	   }

}
