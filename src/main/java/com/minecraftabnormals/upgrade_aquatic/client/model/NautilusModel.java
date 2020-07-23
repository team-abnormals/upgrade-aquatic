package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.minecraftabnormals.upgrade_aquatic.common.entities.NautilusEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelNautilus - Anomalocaris101
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NautilusModel<T extends NautilusEntity> extends EntityModel<T> {
    public static final float SCALE = 0.6F;
    public ModelRenderer shell;
    public ModelRenderer head;
    public ModelRenderer Shell2;
    public ModelRenderer tents_top;
    public ModelRenderer hood;
    public ModelRenderer tents_bottom;
    public ModelRenderer tents_right;
    public ModelRenderer tents_left;

    public NautilusModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.hood = new ModelRenderer(this, 35, 0);
        this.hood.setRotationPoint(0.0F, -3.0F, -3.5F);
        this.hood.addBox(-3.5F, -0.5F, 0.0F, 7, 1, 7, 0.0F);
        this.tents_top = new ModelRenderer(this, 16, 11);
        this.tents_top.setRotationPoint(0.0F, -2.5F, 2.5F);
        this.tents_top.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 4, 0.0F);
        this.Shell2 = new ModelRenderer(this, 0, 18);
        this.Shell2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.Shell2.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 8, 0.0F);
        this.tents_left = new ModelRenderer(this, 26, 11);
        this.tents_left.setRotationPoint(-2.5F, 0.0F, 2.5F);
        this.tents_left.addBox(0.0F, -2.5F, 0.0F, 0, 5, 4, 0.0F);
        this.shell = new ModelRenderer(this, 28, 14);
        this.shell.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.shell.addBox(-3.0F, -6.0F, -6.0F, 6, 6, 12, 0.0F);
        this.setRotateAngle(shell, 0.08726646259971647F, 0.0F, 0.0F);
        this.tents_right = new ModelRenderer(this, 21, 11);
        this.tents_right.setRotationPoint(2.5F, 0.0F, 2.5F);
        this.tents_right.addBox(0.0F, -2.5F, 0.0F, 0, 5, 4, 0.0F);
        this.tents_bottom = new ModelRenderer(this, 26, 11);
        this.tents_bottom.setRotationPoint(0.0F, 2.5F, 2.5F);
        this.tents_bottom.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 4, 0.0F);
        this.head = new ModelRenderer(this, 15, 0);
        this.head.setRotationPoint(0.0F, 3.1F, 4.0F);
        this.head.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
        this.setRotateAngle(head, -0.1308996938995747F, 0.0F, 0.0F);
        this.head.addChild(this.hood);
        this.head.addChild(this.tents_top);
        this.shell.addChild(this.Shell2);
        this.head.addChild(this.tents_left);
        this.head.addChild(this.tents_right);
        this.head.addChild(this.tents_bottom);
        this.shell.addChild(this.head);
    }
    
    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	matrixStack.push();
        matrixStack.scale(SCALE, SCALE, SCALE);
    	this.shell.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    	matrixStack.pop();
    }
    
    @Override
    public void setRotationAngles(T nautilus, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	boolean moving = nautilus.isMoving();
    	boolean inWater = nautilus.isInWater();
    	float shellSway = !inWater || moving ? 1.5F : 1.0F;

        this.tents_left.rotateAngleY = -shellSway * 0.10F * MathHelper.cos(0.2F * ageInTicks);
        this.tents_right.rotateAngleY = -shellSway * 0.10F * -MathHelper.cos(0.2F * ageInTicks);
        this.tents_top.rotateAngleX = -shellSway * 0.10F * MathHelper.sin(0.2F * ageInTicks);
        this.tents_bottom.rotateAngleX = -shellSway * 0.10F * -MathHelper.sin(0.2F * ageInTicks);
        
        if(moving && nautilus.isInWater()) {
        	this.shell.rotateAngleX = -1.0F * 0.12F * MathHelper.sin(0.2F * ageInTicks);
        }
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
