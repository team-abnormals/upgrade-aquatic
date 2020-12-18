package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelLionfish
 * @authors MCVinnyq & SmellyModder
 * Created using Tabula 7.0.0
 */
public class LionfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer topfin;
    public ModelRenderer bottomfin;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer right_fin;
    public ModelRenderer left_fin;
    public ModelRenderer headFin;

    public LionfishModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.body = new ModelRenderer(this, 0, 19);
        this.body.setRotationPoint(0.0F, 19.0F, -5.0F);
        this.body.addBox(-1.5F, -2.5F, 0.0F, 3, 5, 8, 0.0F);
        this.right_fin = new ModelRenderer(this, 14, 11);
        this.right_fin.setRotationPoint(-1.5F, 0.5F, 5.0F);
        this.right_fin.addBox(0.0F, 0.0F, -4.0F, 0, 8, 8, 0.0F);
        this.setRotateAngle(right_fin, 0.0F, -0.0F, 0.4363323129985824F);
        this.bottomfin = new ModelRenderer(this, 40, 20);
        this.bottomfin.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.bottomfin.addBox(0.0F, 0.0F, 0.0F, 0, 4, 8, 0.0F);
        this.head = new ModelRenderer(this, 0, 11);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-1.5F, -2.5F, -3.0F, 3, 4, 3, 0.0F);
        this.left_fin = new ModelRenderer(this, 14, 11);
        this.left_fin.setRotationPoint(1.5F, 0.5F, 4.0F);
        this.left_fin.addBox(0.0F, 0.0F, -4.0F, 0, 8, 8, 0.0F);
        this.setRotateAngle(left_fin, 0.0F, -0.0F, -0.4363323129985824F);
        this.topfin = new ModelRenderer(this, 22, 19);
        this.topfin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topfin.addBox(0.0F, -7.0F, 0.0F, 0, 5, 8, 0.0F);
        this.headFin = new ModelRenderer(this, 0, 18);
        this.headFin.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headFin.addBox(0.0F, -7.0F, -3.0F, 0, 5, 4, 0.0F);
        this.tail = new ModelRenderer(this, 0, -9);
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
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T lionfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	boolean outOfWater = !lionfish.isInWater();
    	
    	float multiplier = outOfWater ? 1.35F : 1.1F;
    	float thetaModifier = outOfWater ? 1.7F : 1.0F;
    	
    	this.tail.rotateAngleY = -multiplier * 0.20F * MathHelper.sin(thetaModifier * 0.65F * ageInTicks);
    }

    public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
