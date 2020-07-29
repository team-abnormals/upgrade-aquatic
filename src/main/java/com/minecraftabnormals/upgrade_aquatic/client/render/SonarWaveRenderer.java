package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.teamabnormals.abnormals_core.client.ACRenderTypes;
import com.minecraftabnormals.upgrade_aquatic.client.model.SonarWaveModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.SonarWaveEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class SonarWaveRenderer extends EntityRenderer<SonarWaveEntity> {
	private final SonarWaveModel SONAR_MODEL = new SonarWaveModel();
	
	public SonarWaveRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.0F;
	}
	
	@Override
	public void render(SonarWaveEntity sonarWave, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStack.push();
		matrixStack.translate(0.0F, -0.7F, 0.0F);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(sonarWave.rotationYaw));
	
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(ACRenderTypes.getEmissiveTransluscentEntity(this.getEntityTexture(sonarWave), true));
    	this.SONAR_MODEL.setRotationAngles(sonarWave, 0.0F, 0.0F, partialTicks, sonarWave.rotationYaw, sonarWave.rotationPitch);
		this.SONAR_MODEL.render(matrixStack, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
		super.render(sonarWave, entityYaw, partialTicks, matrixStack, bufferIn, 240);
	}

	@Override
	public ResourceLocation getEntityTexture(SonarWaveEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/thrasher/sonar.png");
	}
}