package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityUlulu;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

//Made with Blockbench 3.5.1
//Exported for Minecraft version 1.15
//Paste this class into your mod and generate all required imports


public class ModelUlulu<T extends EntityUlulu> extends EntityModel<T> {
	

	public ModelUlulu() {
		textureWidth = 128;
		textureHeight = 64;

		
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
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
