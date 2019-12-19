package com.teamabnormals.upgrade_aquatic.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;

/**
 * ModelSonar - SmellyModder
 * Created using Tabula 7.0.0
 */
public class ModelSonar extends EntityModel<EntitySonarWave> {
    public RendererModel wave;

    public ModelSonar() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.wave = new RendererModel(this, 0, 0);
        this.wave.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.wave.addBox(-8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F);
    }

    @Override
    public void render(EntitySonarWave entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.wave.offsetX, this.wave.offsetY, this.wave.offsetZ);
        GlStateManager.translatef(this.wave.rotationPointX * f5, this.wave.rotationPointY * f5, this.wave.rotationPointZ * f5);
        double scale = 0.6 + entity.getGrowProgress();
        GlStateManager.scaled(scale, scale, scale);
        GlStateManager.translatef(-this.wave.offsetX, -this.wave.offsetY, -this.wave.offsetZ);
        GlStateManager.translatef(-this.wave.rotationPointX * f5, -this.wave.rotationPointY * f5, -this.wave.rotationPointZ * f5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F - (entity.getGrowProgress() * 0.25F));
        this.wave.rotateAngleX = MathHelper.lerp(ClientInfo.getPartialTicks(), entity.prevRotationPitch, entity.rotationPitch) * (float) (Math.PI / 180F);
        this.wave.render(f5);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    @Override
    public void setRotationAngles(EntitySonarWave entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    	this.wave.rotateAngleY = entityIn.rotationYaw;
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}