package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.ModelFlare;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.RenderLayerFlareEyes;
import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityFlare;
import com.minecraftabnormals.upgrade_aquatic.core.util.Reference;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlareRenderer extends MobRenderer<EntityFlare, ModelFlare<EntityFlare>> {

	public FlareRenderer(EntityRendererManager manager) {
		super(manager, new ModelFlare<>(), 0.9F);
		this.addLayer(new RenderLayerFlareEyes<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(EntityFlare entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/flare/flare.png");
	}
	
	@Override
	protected void preRenderCallback(EntityFlare flare, MatrixStack matrixStack, float partialTickTime) {
		int i = flare.getPhantomSize();
		float f = 1.0F + 0.15F * (float)i;
		matrixStack.scale(f, f, f);
	}
	
	@Override
	protected void applyRotations(EntityFlare flare, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(flare, matrixStack, ageInTicks, rotationYaw, partialTicks);
		matrixStack.rotate(Vector3f.XP.rotationDegrees(flare.rotationPitch));
	}

}