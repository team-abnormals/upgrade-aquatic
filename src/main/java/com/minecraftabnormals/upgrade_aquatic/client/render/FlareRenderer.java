package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.FlareModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.FlareEyesRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.FlareEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlareRenderer extends MobRenderer<FlareEntity, FlareModel<FlareEntity>> {

	public FlareRenderer(EntityRendererManager manager) {
		super(manager, new FlareModel<>(), 0.9F);
		this.addLayer(new FlareEyesRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(FlareEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/flare/flare.png");
	}
	
	@Override
	protected void preRenderCallback(FlareEntity flare, MatrixStack matrixStack, float partialTickTime) {
		int i = flare.getPhantomSize();
		float f = 1.0F + 0.15F * (float)i;
		matrixStack.scale(f, f, f);
	}
	
	@Override
	protected void applyRotations(FlareEntity flare, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(flare, matrixStack, ageInTicks, rotationYaw, partialTicks);
		matrixStack.rotate(Vector3f.XP.rotationDegrees(flare.rotationPitch));
	}

}