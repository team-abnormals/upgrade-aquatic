package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.client.model.ModelSonar;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderSonarWave extends EntityRenderer<EntitySonarWave> {
	private final ModelSonar SONAR_MODEL = new ModelSonar();
	
	public RenderSonarWave(EntityRendererManager renderManager) {
		super(renderManager);
		this.shadowSize = 0.0F;
	}
	
	@Override
	public void doRender(EntitySonarWave entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y - 0.7F, z);
		GlStateManager.rotatef(180.0F + entityYaw, 0.0F, 1.0F, 0.0F);
		this.bindEntityTexture(entity);
		
		if(this.renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
		}
		
		GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240.0F, 240.0F);
		GlStateManager.disableLighting();
		
		this.SONAR_MODEL.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		GlStateManager.enableLighting();
		if(this.renderOutlines) {
			GlStateManager.tearDownSolidRenderingTextureCombine();
			GlStateManager.disableColorMaterial();
		}
		
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySonarWave entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/thrasher/sonar.png");
	}
}