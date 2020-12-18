package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelPickerel - Five & SmellyModder
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class PikeModel<T extends PikeEntity> extends EntityModel<T> {
    public ModelRenderer body_front;
    public ModelRenderer neck;
    public ModelRenderer body_back;
    public ModelRenderer left_fin_1;
    public ModelRenderer left_fin_2;
    public ModelRenderer right_fin_1;
    public ModelRenderer right_fin_2;
    public ModelRenderer nose;
    public ModelRenderer tailfin;
    public ModelRenderer top_dorsal;
    public ModelRenderer left_dorsal;
    public ModelRenderer right_dorsal;

    public PikeModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.left_fin_2 = new ModelRenderer(this, 10, 27);
        this.left_fin_2.setRotationPoint(1.1F, 3.0F, 1.5F);
        this.left_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.setRotateAngle(left_fin_2, 0.0F, 0.0F, -0.8726646259971648F);
        this.body_front = new ModelRenderer(this, 0, 0);
        this.body_front.setRotationPoint(0.0F, 19.5F, 1.0F);
        this.body_front.addBox(-1.5F, -1.5F, -3.5F, 3, 5, 7, 0.0F);
        this.nose = new ModelRenderer(this, 0, 28);
        this.nose.setRotationPoint(0.5F, 1.0F, -2.0F);
        this.nose.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.left_fin_1 = new ModelRenderer(this, 18, 28);
        this.left_fin_1.setRotationPoint(1.1F, 3.0F, -3.0F);
        this.left_fin_1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(left_fin_1, 0.0F, 0.0F, -0.8726646259971648F);
        this.right_fin_2 = new ModelRenderer(this, 10, 27);
        this.right_fin_2.setRotationPoint(-1.0F, 3.0F, 2.5F);
        this.right_fin_2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.setRotateAngle(right_fin_2, 0.0F, 0.0F, 0.8726646259971648F);
        this.tailfin = new ModelRenderer(this, 22, 15);
        this.tailfin.setRotationPoint(0.0F, 0.5F, 7.0F);
        this.tailfin.addBox(0.0F, 0.0F, 0.0F, 0, 5, 5, 0.0F);
        this.right_fin_1 = new ModelRenderer(this, 18, 28);
        this.right_fin_1.setRotationPoint(-1.1F, 3.0F, -3.0F);
        this.right_fin_1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 2, 0.0F);
        this.setRotateAngle(right_fin_1, 0.0F, 0.0F, 0.8726646259971648F);
        this.top_dorsal = new ModelRenderer(this, 24, 24);
        this.top_dorsal.setRotationPoint(0.0F, -1.5F, 5.0F);
        this.top_dorsal.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4, 0.0F);
        this.left_dorsal = new ModelRenderer(this, 24, 26);
        this.left_dorsal.setRotationPoint(1.1F, 5.0F, 5.0F);
        this.left_dorsal.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4, 0.0F);
        this.setRotateAngle(left_dorsal, 0.0F, 0.0F, -0.8726646259971648F);
        this.neck = new ModelRenderer(this, 20, 12);
        this.neck.setRotationPoint(-1.5F, -0.5F, -6.5F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.right_dorsal = new ModelRenderer(this, 24, 26);
        this.right_dorsal.setRotationPoint(-1.1F, 5.0F, 5.0F);
        this.right_dorsal.addBox(0.0F, 0.0F, 0.0F, 0, 2, 4, 0.0F);
        this.setRotateAngle(right_dorsal, 0.0F, 0.0F, 0.8726646259971648F);
        this.body_back = new ModelRenderer(this, 0, 12);
        this.body_back.setRotationPoint(0.0F, -2.0F, 3.5F);
        this.body_back.addBox(-1.5F, 0.5F, 0.0F, 3, 5, 7, 0.0F);
        this.body_front.addChild(this.left_fin_2);
        this.neck.addChild(this.nose);
        this.body_front.addChild(this.left_fin_1);
        this.body_front.addChild(this.right_fin_2);
        this.body_back.addChild(this.tailfin);
        this.body_front.addChild(this.right_fin_1);
        this.body_back.addChild(this.top_dorsal);
        this.body_back.addChild(this.left_dorsal);
        this.body_front.addChild(this.neck);
        this.body_back.addChild(this.right_dorsal);
        this.body_front.addChild(this.body_back);
    }
    
    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	this.body_front.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
    
    @Override
    public void setRotationAngles(T pike, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	boolean outOfWater = !pike.isInWater();
    	
    	float multiplier = outOfWater ? -1.35F : -1.1F;
    	float thetaModifier = outOfWater ? 1.7F : 1.0F;
    	
    	this.body_back.rotateAngleY = multiplier * 0.25F * MathHelper.sin(thetaModifier * 0.6F * ageInTicks);
    }

    public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }
}
