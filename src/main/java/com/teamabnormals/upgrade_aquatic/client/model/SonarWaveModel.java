package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.upgrade_aquatic.common.entity.projectile.SonarWave;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * ModelSonar - SmellyModder
 * Created using Tabula 7.0.0
 */
public class SonarWaveModel extends EntityModel<SonarWave> {
	public ModelRenderer wave;
	private SonarWave sonarWave;

	public SonarWaveModel() {
		this.texWidth = 16;
		this.texHeight = 16;
		this.wave = new ModelRenderer(this, 0, 0);
		this.wave.setPos(0.0F, 19.0F, 0.0F);
		this.wave.addBox(-8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		matrixStack.pushPose();
		float progress = this.sonarWave.getGrowProgress();
		float scale = 0.6F + progress;
		matrixStack.scale(scale, scale, scale);
		this.wave.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha - (0.25F * progress));
		matrixStack.popPose();
	}

	@Override
	public void setupAnim(SonarWave sonar, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.sonarWave = sonar;

		this.wave.xRot = (float) -Math.toRadians(MathHelper.lerp(ClientInfo.getPartialTicks(), sonar.xRotO, sonar.xRot));
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}
}