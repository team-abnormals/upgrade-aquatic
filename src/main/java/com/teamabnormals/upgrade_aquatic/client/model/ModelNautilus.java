package com.teamabnormals.upgrade_aquatic.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;

/**
 * ModelNautilus - Anomalocaris101
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelNautilus<T extends EntityNautilus> extends EntityModel<T> {
    public double modelScale = 1.5D;
    public RendererModel shell;
    public RendererModel head;
    public RendererModel Shell2;
    public RendererModel tents_top;
    public RendererModel hood;
    public RendererModel tents_bottom;
    public RendererModel tents_right;
    public RendererModel tents_left;

    public ModelNautilus() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.hood = new RendererModel(this, 35, 0);
        this.hood.setRotationPoint(0.0F, -3.0F, -3.5F);
        this.hood.addBox(-3.5F, -0.5F, 0.0F, 7, 1, 7, 0.0F);
        this.tents_top = new RendererModel(this, 16, 11);
        this.tents_top.setRotationPoint(0.0F, -2.5F, 2.5F);
        this.tents_top.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 4, 0.0F);
        this.Shell2 = new RendererModel(this, 0, 18);
        this.Shell2.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.Shell2.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 8, 0.0F);
        this.tents_left = new RendererModel(this, 26, 11);
        this.tents_left.setRotationPoint(-2.5F, 0.0F, 2.5F);
        this.tents_left.addBox(0.0F, -2.5F, 0.0F, 0, 5, 4, 0.0F);
        this.shell = new RendererModel(this, 28, 14);
        this.shell.setRotationPoint(0.0F, 30.0F, 0.0F);
        this.shell.addBox(-3.0F, -6.0F, -6.0F, 6, 6, 12, 0.0F);
        this.setRotateAngle(shell, 0.08726646259971647F, 0.0F, 0.0F);
        this.tents_right = new RendererModel(this, 21, 11);
        this.tents_right.setRotationPoint(2.5F, 0.0F, 2.5F);
        this.tents_right.addBox(0.0F, -2.5F, 0.0F, 0, 5, 4, 0.0F);
        this.tents_bottom = new RendererModel(this, 26, 11);
        this.tents_bottom.setRotationPoint(0.0F, 2.5F, 2.5F);
        this.tents_bottom.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 4, 0.0F);
        this.head = new RendererModel(this, 15, 0);
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
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        GlStateManager.pushMatrix();
        GlStateManager.scaled(1D / modelScale, 1D / modelScale, 1D / modelScale);
        this.shell.render(f5);
        GlStateManager.popMatrix();
    }
    
    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    	float f = 1.0F;
    	if (!entityIn.isInWater() || entityIn.isMoving()) {
        	f = 1.5F;
    	}

        this.tents_left.rotateAngleY = -f * 0.10F * MathHelper.cos(0.2F * ageInTicks);
        this.tents_right.rotateAngleY = -f * 0.10F * -MathHelper.cos(0.2F * ageInTicks);
        this.tents_top.rotateAngleX = -f * 0.10F * MathHelper.sin(0.2F * ageInTicks);
        this.tents_bottom.rotateAngleX = -f * 0.10F * -MathHelper.sin(0.2F * ageInTicks);
        
        if(entityIn.isMoving()) {
        	this.shell.rotateAngleX = -1.0F * 0.12F * MathHelper.sin(0.2F * ageInTicks);
        }
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
