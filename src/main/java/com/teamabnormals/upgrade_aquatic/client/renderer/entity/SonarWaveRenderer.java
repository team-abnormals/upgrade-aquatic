package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.teamabnormals.blueprint.client.BlueprintRenderTypes;
import com.teamabnormals.upgrade_aquatic.client.model.SonarWaveModel;
import com.teamabnormals.upgrade_aquatic.common.entity.projectile.SonarWave;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SonarWaveRenderer extends EntityRenderer<SonarWave> {
	private final SonarWaveModel sonarWaveModel;

	public SonarWaveRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.sonarWaveModel = new SonarWaveModel(context.bakeLayer(SonarWaveModel.LOCATION));
		this.shadowRadius = 0.0F;
	}

	@Override
	public void render(SonarWave sonarWave, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		poseStack.pushPose();
		poseStack.translate(0.0F, -0.7F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(sonarWave.getYRot()));

		VertexConsumer vertexConsumer = bufferIn.getBuffer(BlueprintRenderTypes.getUnshadedTranslucentEntity(this.getTextureLocation(sonarWave), true));
		this.sonarWaveModel.setupAnim(sonarWave, 0.0F, 0.0F, partialTicks, sonarWave.getYRot(), sonarWave.getXRot());
		this.sonarWaveModel.renderToBuffer(poseStack, vertexConsumer, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
		super.render(sonarWave, entityYaw, partialTicks, poseStack, bufferIn, 240);
	}

	@Override
	public ResourceLocation getTextureLocation(SonarWave entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/sonar.png");
	}
}