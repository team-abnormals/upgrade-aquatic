package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.upgrade_aquatic.client.model.FlareModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.FlareEyesRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Flare;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlareRenderer extends MobRenderer<Flare, FlareModel<Flare>> {

	public FlareRenderer(EntityRendererProvider.Context context) {
		super(context, new FlareModel<>(context.bakeLayer(FlareModel.LOCATION)), 0.9F);
		this.addLayer(new FlareEyesRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Flare entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/flare/flare.png");
	}

	@Override
	protected void scale(Flare flare, PoseStack matrixStack, float partialTickTime) {
		int i = flare.getPhantomSize();
		float f = 1.0F + 0.15F * (float) i;
		matrixStack.scale(f, f, f);
	}

	@Override
	protected void setupRotations(Flare flare, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(flare, matrixStack, ageInTicks, rotationYaw, partialTicks);
		matrixStack.mulPose(Vector3f.XP.rotationDegrees(flare.getXRot()));
	}

}