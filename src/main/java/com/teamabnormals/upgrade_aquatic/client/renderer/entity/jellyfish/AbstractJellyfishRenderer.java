package com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public abstract class AbstractJellyfishRenderer<J extends AbstractJellyfish> extends MobRenderer<J, EndimatorEntityModel<J>> {

	public AbstractJellyfishRenderer(EntityRendererProvider.Context context, EndimatorEntityModel<J> entityModelIn, float shadowSizeIn) {
		super(context, entityModelIn, shadowSizeIn);
	}

	@Override
	protected void setupRotations(J jellyfish, PoseStack stack, float p_115319_, float p_115320_, float partialTicks) {
		if (isEntityUpsideDown(jellyfish)) {
			stack.translate(0.0D, jellyfish.getBbHeight() + 0.1F, 0.0D);
			stack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
		}
		float translateY = jellyfish.getBbHeight() * 0.5F;
		stack.translate(0.0F, translateY, 0.0F);
		stack.mulPose(Vector3f.YP.rotationDegrees(360.0F - Mth.rotLerp(partialTicks, jellyfish.yRotO, jellyfish.getYRot())));
		stack.mulPose(Vector3f.XP.rotationDegrees((Mth.rotLerp(partialTicks, jellyfish.xRotO, jellyfish.getXRot()) + 90.0F) % 360.0F));
		stack.translate(0.0F, -translateY, 0.0F);
	}

	public abstract ResourceLocation getOverlayTexture(J jellyfish);

}