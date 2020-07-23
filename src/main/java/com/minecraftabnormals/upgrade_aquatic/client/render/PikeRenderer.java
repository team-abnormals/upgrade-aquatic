package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.PikeModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.GlowingPikeRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.PikeCarriedItemRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.PikeEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PikeRenderer extends MobRenderer<PikeEntity, PikeModel<PikeEntity>> {

	public PikeRenderer(EntityRendererManager manager) {
		super(manager, new PikeModel<>(), 0.6F);
		this.addLayer(new GlowingPikeRenderLayer<>(this));
		this.addLayer(new PikeCarriedItemRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(PikeEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/pike/pike_" + entity.getPikeType() + ".png");
	}
	
	@Override
	protected void applyRotations(PikeEntity pike, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(pike, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(f));
		if(!pike.isInWater()) {
			matrixStack.translate(0.1F, 0.1F, -0.1F);
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}
	
	@Override
	protected void preRenderCallback(PikeEntity pike, MatrixStack matrixStack, float partialTickTime) {
		float scale;
		if(pike.getPikeType() < 12) {
			if(pike.getPikeType() < 3) {
				scale = 1.2F;
			} else {
				scale = 1.5F;
			}
		} else {
			if(pike.getPikeType() == 12) {
				scale = 1.5F;
			} else if(pike.getPikeType() == 13) {
				scale = 1.7F;
			} else if(pike.getPikeType() == 14) {
				scale = 2.25F;
			} else if(pike.getPikeType() == 15 || pike.getPikeType() == 16) {
				scale = 1.2F;
			} else {
				scale = 1.35F;
			}
		}
		matrixStack.scale(scale, scale, scale);
	}
	
}