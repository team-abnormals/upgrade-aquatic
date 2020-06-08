package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.upgrade_aquatic.client.UARenderTypes;
import com.teamabnormals.upgrade_aquatic.client.model.ModelSonar;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class SonarWaveRenderer extends EntityRenderer<EntitySonarWave> {
	private final ModelSonar SONAR_MODEL = new ModelSonar();
	
	public SonarWaveRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.0F;
	}
	
	@Override
	public void render(EntitySonarWave sonarWave, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStack.push();
		matrixStack.translate(0.0F, -0.7F, 0.0F);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(sonarWave.rotationYaw));
	
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(UARenderTypes.getEmmisiveTransluscentEntity(this.getEntityTexture(sonarWave), true));
    	this.SONAR_MODEL.setRotationAngles(sonarWave, 0.0F, 0.0F, partialTicks, sonarWave.rotationYaw, sonarWave.rotationPitch);
		this.SONAR_MODEL.render(matrixStack, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrixStack.pop();
		super.render(sonarWave, entityYaw, partialTicks, matrixStack, bufferIn, 240);
	}

	@Override
	public ResourceLocation getEntityTexture(EntitySonarWave entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/thrasher/sonar.png");
	}
}