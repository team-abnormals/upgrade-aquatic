package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.abnormals_core.client.ClientInfo;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

/**
 * ModelSonar - SmellyModder
 * Created using Tabula 7.0.0
 */
public class ModelSonar extends EntityModel<EntitySonarWave> {
    public ModelRenderer wave;
    private EntitySonarWave sonarWave;

    public ModelSonar() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.wave = new ModelRenderer(this, 0, 0);
        this.wave.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.wave.addBox(-8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F);
    }
    
    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	matrixStack.push();
    	float progress = this.sonarWave.getGrowProgress();
    	float scale = 0.6F + progress;
    	matrixStack.scale(scale, scale, scale);
    	this.wave.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha - (0.25F * progress));
    	matrixStack.pop();
    }
    
    @Override
    public void setRotationAngles(EntitySonarWave sonar, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	this.sonarWave = sonar;
    	
    	this.wave.rotateAngleX = (float) -Math.toRadians(MathHelper.lerp(ClientInfo.getPartialTicks(), sonar.prevRotationPitch, sonar.rotationPitch));
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}