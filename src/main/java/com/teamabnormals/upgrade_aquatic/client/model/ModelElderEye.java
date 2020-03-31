package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * ModelElderEye - SmellyModder
 */
public class ModelElderEye {
    public ModelRenderer eye;
    public ModelRenderer tail_x;
    public ModelRenderer tail_y;

    public ModelElderEye() {
    	int[] textureSizes = {32, 32};
        this.tail_x = new ModelRenderer(textureSizes[0], textureSizes[1], 8, 24);
        this.tail_x.mirror = true;
        this.tail_x.setRotationPoint(-4.0F, -4.0F, 4.0F);
        this.tail_x.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, 0.0F);
        this.tail_y = new ModelRenderer(textureSizes[0], textureSizes[1], 8, 24);
        this.tail_y.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.tail_y.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, 0.0F);
        this.setRotateAngle(tail_y, 0.0F, 0.0F, 1.5707963267948966F);
        this.eye = new ModelRenderer(textureSizes[0], textureSizes[1], 0, 0);
        this.eye.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.eye.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.eye.addChild(this.tail_x);
        this.eye.addChild(this.tail_y);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) { 
        this.eye.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
