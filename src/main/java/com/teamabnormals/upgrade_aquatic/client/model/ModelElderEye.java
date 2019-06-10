package com.teamabnormals.upgrade_aquatic.client.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;

/**
 * ModelElderEye - SmellyModder
 */
public class ModelElderEye extends Model {
    public RendererModel eye;
    public RendererModel tail_x;
    public RendererModel tail_y;

    public ModelElderEye() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.tail_x = new RendererModel(this, 8, 24);
        this.tail_x.mirror = true;
        this.tail_x.setRotationPoint(-4.0F, -4.0F, 4.0F);
        this.tail_x.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, 0.0F);
        this.tail_y = new RendererModel(this, 8, 24);
        this.tail_y.setRotationPoint(0.0F, -8.0F, 4.0F);
        this.tail_y.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, 0.0F);
        this.setRotateAngle(tail_y, 0.0F, 0.0F, 1.5707963267948966F);
        this.eye = new RendererModel(this, 0, 0);
        this.eye.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.eye.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.eye.addChild(this.tail_x);
        this.eye.addChild(this.tail_y);
    }

    public void renderAll() { 
        this.eye.render(0.0625F);
    }

    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
