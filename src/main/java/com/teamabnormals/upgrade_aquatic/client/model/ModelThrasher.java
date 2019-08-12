package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.api.client.UAEntityModel;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityThrasher;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

/**
 * ModelThrasher - SmellyModder
 * Created using Tabula 7.0.0
 */
public class ModelThrasher<E extends EntityThrasher> extends UAEntityModel<E> {
	public RendererModel neck;
	public RendererModel body;
	public RendererModel top_jaw;
	public RendererModel bottom_jaw;
	public RendererModel left_fin;
	public RendererModel right_fin;
	public RendererModel fin;
	public RendererModel tail_holder;
	public RendererModel right_fin_2;
	public RendererModel left_fin_2;
	public RendererModel tail_holder_2;
	public RendererModel tail;
	public RendererModel tail_linear;

	public ModelThrasher() {
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.right_fin_2 = new RendererModel(this, 33, 16);
        this.right_fin_2.setRotationPoint(-6.0F, 5.0F, 6.0F);
        this.right_fin_2.addBox(-13.0F, 0.0F, -4.0F, 13, 1, 8, 0.0F);
        this.setRotateAngle(right_fin_2, 0.0F, 0.17453292519943295F, -0.20943951023931953F);
        this.top_jaw = new RendererModel(this, 0, 0);
        this.top_jaw.setRotationPoint(0.0F, -2.5F, -4.0F);
        this.top_jaw.addBox(-6.0F, -2.5F, -10.0F, 12, 5, 10, 0.0F);
        this.tail = new RendererModel(this, 30, 70);
        this.tail.setRotationPoint(0.0F, 0.1F, 8.0F);
        this.tail.addBox(0.0F, -3.0F, 0.0F, 0, 6, 18, 0.0F);
        this.neck = new RendererModel(this, 0, 24);
        this.neck.setRotationPoint(0.0F, 17.0F, -10.0F);
        this.neck.addBox(-6.0F, -5.0F, -4.0F, 12, 11, 8, 0.0F);
        this.body = new RendererModel(this, 0, 43);
        this.body.setRotationPoint(0.0F, -4.0F, 4.0F);
        this.body.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 13, 0.0F);
        this.fin = new RendererModel(this, 50, 50);
        this.fin.setRotationPoint(0.0F, -5.0F, -2.5F);
        this.fin.addBox(0.0F, -7.0F, 0.0F, 0, 7, 12, 0.0F);
        this.setRotateAngle(fin, -0.17453292519943295F, 0.0F, 0.0F);
        this.tail_holder = new RendererModel(this, 0, 66);
        this.tail_holder.setRotationPoint(0.0F, 2.5F, 13.0F);
        this.tail_holder.addBox(-4.0F, -1.0F, 0.0F, 8, 7, 8, 0.0F);
        this.tail_linear = new RendererModel(this, 30, 70);
        this.tail_linear.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.tail_linear.addBox(0.0F, 0.0F, 0.0F, 6, 0, 18, 0.0F);
        this.right_fin = new RendererModel(this, 0, 16);
        this.right_fin.setRotationPoint(-6.0F, 0.5F, -0.5F);
        this.right_fin.addBox(-10.0F, 0.0F, -2.5F, 10, 1, 6, 0.0F);
        this.setRotateAngle(right_fin, 0.0F, 0.17453292519943295F, -0.20943951023931953F);
        this.bottom_jaw = new RendererModel(this, 44, 0);
        this.bottom_jaw.setRotationPoint(0.0F, 4.0F, -4.0F);
        this.bottom_jaw.addBox(-6.0F, -2.0F, -10.0F, 12, 4, 10, 0.0F);
        this.left_fin = new RendererModel(this, 0, 16);
        this.left_fin.mirror = true;
        this.left_fin.setRotationPoint(6.0F, 0.5F, -0.5F);
        this.left_fin.addBox(0.0F, 0.0F, -2.5F, 10, 1, 6, 0.0F);
        this.setRotateAngle(left_fin, 0.0F, -0.17453292519943295F, 0.20943951023931953F);
        this.left_fin_2 = new RendererModel(this, 33, 16);
        this.left_fin_2.mirror = true;
        this.left_fin_2.setRotationPoint(6.0F, 5.0F, 6.0F);
        this.left_fin_2.addBox(-0.0F, 0.0F, -4.0F, 13, 1, 8, 0.0F);
        this.setRotateAngle(left_fin_2, 0.0F, -0.17453292519943295F, 0.20943951023931953F);
        this.tail_holder_2 = new RendererModel(this, 0, 81);
        this.tail_holder_2.setRotationPoint(0.0F, 2.5F, 8.0F);
        this.tail_holder_2.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 8, 0.0F);
        this.body.addChild(this.right_fin_2);
        this.neck.addChild(this.top_jaw);
        this.tail_holder_2.addChild(this.tail);
        this.neck.addChild(this.body);
        this.neck.addChild(this.fin);
        this.body.addChild(this.tail_holder);
        this.tail.addChild(this.tail_linear);
        this.neck.addChild(this.right_fin);
        this.neck.addChild(this.bottom_jaw);
        this.neck.addChild(this.left_fin);
        this.body.addChild(this.left_fin_2);
        this.tail_holder.addChild(this.tail_holder_2);
        
        this.setDefaultBoxValues();
	}

	@Override
	public void render(E entity, float f, float f1, float f2, float f3, float f4, float f5) { 
		this.neck.render(f5);
	}

	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
    }
    
	@Override
	public void setRotationAngles(E thrasher, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(thrasher, limbSwing, limbSwing, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		this.setDefaultBoxValues();
		RendererModel[] boxes = new RendererModel[] {this.body, this.tail_holder, this.tail_holder_2};
		for(int i = 1; i < boxes.length + 1; i++) {
			boxes[i - 1].rotateAngleY = MathHelper.sin(ageInTicks) * (0.1F * i);
		}
		this.right_fin.rotateAngleZ = (float) (-MathHelper.cos(ageInTicks) * 0.13);
		this.right_fin_2.rotateAngleZ = (float) (-MathHelper.cos(ageInTicks) * (0.125 * 0.5 - 0.1F));
		this.left_fin.rotateAngleZ = (float) (MathHelper.cos(ageInTicks) * 0.13);
		this.left_fin_2.rotateAngleZ = (float) (MathHelper.cos(ageInTicks) * (0.125 * 0.5 - 0.1F));
	}
	
}
