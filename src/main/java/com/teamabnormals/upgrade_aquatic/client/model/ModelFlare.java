package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityFlare;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelFlare - SmellyModer
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class ModelFlare<F extends EntityFlare> extends EntityModel<F> {
    public RendererModel base;
    public RendererModel tail;
    public RendererModel left_shoulder;
    public RendererModel right_shoulder;
    public RendererModel head;
    public RendererModel tail_end;
    public RendererModel left_wing;
    public RendererModel right_wing;
    public RendererModel shape13;

    public ModelFlare() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(2.5F, 1.2F, 0.0F);
        this.head.addBox(-3.5F, 0.0F, -5.0F, 7, 4, 5, 0.0F);
        this.setRotateAngle(head, 0.349066F, 0.0F, 0.0F);
        this.right_wing = new RendererModel(this, 0, 46);
        this.right_wing.setRotationPoint(-6.0F, 0.0F, 0.5F);
        this.right_wing.addBox(-13.0F, 0.0F, -5.0F, 13, 1, 10, 0.0F);
        this.setRotateAngle(right_wing, 0.0F, -0.0F, -0.17453292519943295F);
        this.left_shoulder = new RendererModel(this, 27, 16);
        this.left_shoulder.mirror = true;
        this.left_shoulder.setRotationPoint(5.0F, 0.0F, 4.5F);
        this.left_shoulder.addBox(0.0F, 0.0F, -4.5F, 6, 2, 9, 0.0F);
        this.setRotateAngle(left_shoulder, -0.0F, 0.0F, 0.17453292519943295F);
        this.tail = new RendererModel(this, 0, 20);
        this.tail.setRotationPoint(2.5F, 0.0F, 9.0F);
        this.tail.addBox(-2.5F, 0.0F, 0.0F, 5, 3, 10, 0.0F);
        this.setRotateAngle(tail, -0.17453292519943295F, 0.0F, 0.0F);
        this.shape13 = new RendererModel(this, 26, 0);
        this.shape13.setRotationPoint(0.0F, 2.0F, -3.0F);
        this.shape13.addBox(-4.5F, 0.0F, -3.0F, 9, 2, 5, 0.0F);
        this.right_shoulder = new RendererModel(this, 27, 16);
        this.right_shoulder.setRotationPoint(0.0F, 0.0F, 4.5F);
        this.right_shoulder.addBox(-6.0F, 0.0F, -4.5F, 6, 2, 9, 0.0F);
        this.setRotateAngle(right_shoulder, 0.0F, 0.0F, -0.17453292519943295F);
        this.tail_end = new RendererModel(this, 0, 33);
        this.tail_end.setRotationPoint(0.0F, 0.0F, 10.0F);
        this.tail_end.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 10, 0.0F);
        this.setRotateAngle(tail_end, -0.17453292519943295F, 0.0F, 0.0F);
        this.left_wing = new RendererModel(this, 0, 46);
        this.left_wing.mirror = true;
        this.left_wing.setRotationPoint(6.0F, 0.0F, 0.5F);
        this.left_wing.addBox(0.0F, 0.0F, -5.0F, 13, 1, 10, 0.0F);
        this.setRotateAngle(left_wing, 0.0F, 0.0F, 0.17453292519943295F);
        this.base = new RendererModel(this, 0, 8);
        this.base.setRotationPoint(-2.5F, 18.0F, -5.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 5, 3, 9, 0.0F);
        this.setRotateAngle(base, -0.174533F, 0.0F, 0.0F);
        this.base.addChild(this.head);
        this.right_shoulder.addChild(this.right_wing);
        this.base.addChild(this.left_shoulder);
        this.base.addChild(this.tail);
        this.head.addChild(this.shape13);
        this.base.addChild(this.right_shoulder);
        this.tail.addChild(this.tail_end);
        this.left_shoulder.addChild(this.left_wing);
    }

    @Override
    public void render(F entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.base.render(f5);
    }

    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(F flare, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    	float f = ((flare.getEntityId() * 3) + ageInTicks) * 0.13F;
        float f1 = 16.0F;
        
        this.left_shoulder.rotateAngleZ = MathHelper.cos(f) * f1 * ((float)Math.PI / 180F);
        this.left_wing.rotateAngleZ = this.left_shoulder.rotateAngleZ;
        
        this.right_shoulder.rotateAngleZ = -this.left_shoulder.rotateAngleZ;
        this.right_wing.rotateAngleZ = -this.left_wing.rotateAngleZ;
        
        this.tail.rotateAngleX = -(5.0F + MathHelper.cos(f * 2.0F) * 5.0F) * ((float)Math.PI / 180F);
        this.tail_end.rotateAngleX = this.tail.rotateAngleX;
    }
}
