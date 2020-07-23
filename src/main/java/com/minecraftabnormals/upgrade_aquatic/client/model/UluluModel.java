package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.minecraftabnormals.upgrade_aquatic.common.entities.UluluEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;


public class UluluModel<T extends UluluEntity> extends EntityModel<T> {
	
	public ModelRenderer body;
	
	public UluluModel() {
		textureWidth = 128;
		textureHeight = 64;
		this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-12.4F, -0.2F, -11.3F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 24, 24, 24, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub
		
	}
}
