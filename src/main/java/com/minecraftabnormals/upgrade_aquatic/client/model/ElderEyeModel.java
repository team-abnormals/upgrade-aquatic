package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;

/**
 * ModelElderEye - SmellyModder
 */
public class ElderEyeModel {
	public ModelPart eye;
	public ModelPart tail_x;
	public ModelPart tail_y;

	public ElderEyeModel() {
		int[] textureSizes = {32, 32};
		this.tail_x = new ModelPart(textureSizes[0], textureSizes[1], 8, 24);
		this.tail_x.mirror = true;
		this.tail_x.setPos(-4.0F, -4.0F, 4.0F);
		this.tail_x.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, 0.0F);
		this.tail_y = new ModelPart(textureSizes[0], textureSizes[1], 8, 24);
		this.tail_y.setPos(0.0F, -8.0F, 4.0F);
		this.tail_y.addBox(0.0F, 0.0F, 0.0F, 8, 0, 8, 0.0F);
		this.setRotateAngle(tail_y, 0.0F, 0.0F, 1.5707963267948966F);
		this.eye = new ModelPart(textureSizes[0], textureSizes[1], 0, 0);
		this.eye.setPos(0.0F, 24.0F, 0.0F);
		this.eye.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.eye.addChild(this.tail_x);
		this.eye.addChild(this.tail_y);
	}

	public void render(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn) {
		this.eye.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}

	public void setRotateAngle(ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}
}
