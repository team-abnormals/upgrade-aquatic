package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teamabnormals.upgrade_aquatic.client.model.ModelUlulu;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityUlulu;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class UluluRenderer extends MobRenderer<EntityUlulu, ModelUlulu<EntityUlulu>> {

	public UluluRenderer(EntityRendererManager manager) {
		super(manager, new ModelUlulu<>(), 0.9F);
	}
	
	public UluluRenderer(EntityRendererManager p_i50961_1_, ModelUlulu<EntityUlulu> p_i50961_2_, float p_i50961_3_) {
		super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
	}
	
	public void render(EntityUlulu entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		this.shadowSize = 0.75F * (float)entityIn.getSlimeSize();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityUlulu entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/ululu/ululu_dry.png");
	}
	
	protected void preRenderCallback(EntityUlulu entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
	      float f = 0.999F;
	      matrixStackIn.scale(0.999F, 0.999F, 0.999F);
	      matrixStackIn.translate(0.0D, (double)0.001F, 0.0D);
	      float f1 = (float)entitylivingbaseIn.getSlimeSize();
	      float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor, entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
	      float f3 = 1.0F / (f2 + 1.0F);
	      matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	   }

}
