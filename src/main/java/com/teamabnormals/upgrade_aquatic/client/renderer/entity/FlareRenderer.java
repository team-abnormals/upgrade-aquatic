package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.upgrade_aquatic.client.model.FlareModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.FlareEyesRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Flare;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.UAModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlareRenderer extends MobRenderer<Flare, FlareModel<Flare>> {

	public FlareRenderer(EntityRendererProvider.Context context) {
		super(context, new FlareModel<>(context.bakeLayer(UAModelLayers.FLARE)), 0.9F);
		this.addLayer(new FlareEyesRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Flare entity) {
		return UpgradeAquatic.location("textures/entity/flare/flare.png");
	}

	@Override
	protected void scale(Flare flare, PoseStack matrixStack, float partialTickTime) {
		int i = flare.getPhantomSize();
		float f = 1.0F + 0.15F * (float) i;
		matrixStack.scale(f, f, f);
	}

	@Override
	protected void setupRotations(Flare flare, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
		super.setupRotations(flare, matrixStack, ageInTicks, rotationYaw, partialTicks, scale);
		matrixStack.mulPose(Axis.XP.rotationDegrees(flare.getXRot()));
	}

}