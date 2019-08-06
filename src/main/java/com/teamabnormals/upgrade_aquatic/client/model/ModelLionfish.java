package com.teamabnormals.upgrade_aquatic.client.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelLionfish
 * @authors MCVinnyq & SmellyModder
 * Created using Tabula 7.0.0
 */
public class ModelLionfish<T extends Entity> extends EntityModel<T> {
    public RendererModel body;
    public RendererModel topfin;
    public RendererModel bottomfin;
    public RendererModel tail;
    public RendererModel head;
    public RendererModel right_fin;
    public RendererModel left_fin;
    public RendererModel headFin;

    public ModelLionfish() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new RendererModel(this, 0, 19);
        this.body.setRotationPoint(0.0F, 19.0F, -5.0F);
        this.body.addBox(-1.5F, -2.5F, 0.0F, 3, 5, 8, 0.0F);
        this.right_fin = new RendererModel(this, 14, 11);
        this.right_fin.setRotationPoint(-1.5F, 0.5F, 5.0F);
        this.right_fin.addBox(0.0F, 0.0F, -4.0F, 0, 8, 8, 0.0F);
        this.setRotateAngle(right_fin, 0.0F, -0.0F, 0.4363323129985824F);
        this.bottomfin = new RendererModel(this, 40, 20);
        this.bottomfin.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.bottomfin.addBox(0.0F, 0.0F, 0.0F, 0, 4, 8, 0.0F);
        this.head = new RendererModel(this, 0, 11);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-1.5F, -2.5F, -3.0F, 3, 4, 3, 0.0F);
        this.left_fin = new RendererModel(this, 14, 11);
        this.left_fin.setRotationPoint(1.5F, 0.5F, 4.0F);
        this.left_fin.addBox(0.0F, 0.0F, -4.0F, 0, 8, 8, 0.0F);
        this.setRotateAngle(left_fin, 0.0F, -0.0F, -0.4363323129985824F);
        this.topfin = new RendererModel(this, 22, 19);
        this.topfin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topfin.addBox(0.0F, -7.0F, 0.0F, 0, 5, 8, 0.0F);
        this.headFin = new RendererModel(this, 0, 18);
        this.headFin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headFin.addBox(0.0F, -7.0F, -3.0F, 0, 5, 4, 0.0F);
        this.tail = new RendererModel(this, 0, -9);
        this.tail.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail.addBox(0.0F, -5.0F, 0.0F, 0, 10, 10, 0.0F);
        this.body.addChild(this.right_fin);
        this.body.addChild(this.bottomfin);
        this.body.addChild(this.head);
        this.body.addChild(this.left_fin);
        this.body.addChild(this.topfin);
        this.head.addChild(this.headFin);
        this.body.addChild(this.tail);
    }

    @Override
    public void render(T entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body.render(f5);
    }
    
    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    	float f = 1.1F;
    	float f1 = 1.0F;
    	if (!entityIn.isInWater()) {
    		f = 1.35F;
    		f1 = 1.7F;
    	}
    	
    	this.tail.rotateAngleY = -f * 0.20F * MathHelper.sin(f1 * 0.65F * ageInTicks);
    }

    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
