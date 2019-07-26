package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.client.model.ModelPike;
import com.teamabnormals.upgrade_aquatic.client.render.overlay.RenderLayerSpectralPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderPike extends MobRenderer<EntityPike, ModelPike<EntityPike>> {

	public RenderPike(EntityRendererManager manager) {
		super(manager, new ModelPike<>(), 0.6F);
		this.addLayer(new RenderLayerSpectralPike<>(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPike entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/pike/pike_" + entity.getPikeType() + ".png");
	}
	
	@Override
	protected void applyRotations(EntityPike entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
		if (!entityLiving.isInWater()) {
			GlStateManager.translatef(0.1F, 0.1F, -0.1F);
			GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
		}
	}
	
	@Override
	protected void preRenderCallback(EntityPike entity, float partialTickTime) {
		GlStateManager.scalef(1F, 1F, 1F);
		float scale;
		if(entity.getPikeType() == 0) {
			scale = 1F;
		} else if(entity.getPikeType() == 1) {
			scale = 1.2F;
		} else {
			scale = 1.5F;
		}
		GlStateManager.scalef(scale, scale, scale);
	}
	
}
