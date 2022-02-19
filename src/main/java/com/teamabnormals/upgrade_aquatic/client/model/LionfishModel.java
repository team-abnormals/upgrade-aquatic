package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

/**
 * ModelLionfish
 *
 * @authors MCVinnyq & SmellyModder
 * Created using Tabula 7.0.0
 */
public class LionfishModel<T extends Entity> extends EntityModel<T> {
	public ModelPart body;
	public ModelPart topfin;
	public ModelPart bottomfin;
	public ModelPart tail;
	public ModelPart head;
	public ModelPart right_fin;
	public ModelPart left_fin;
	public ModelPart headFin;

	public LionfishModel() {
		this.texWidth = 64;
		this.texHeight = 32;
		this.body = new ModelPart(this, 0, 19);
		this.body.setPos(0.0F, 19.0F, -5.0F);
		this.body.addBox(-1.5F, -2.5F, 0.0F, 3, 5, 8, 0.0F);
		this.right_fin = new ModelPart(this, 14, 11);
		this.right_fin.setPos(-1.5F, 0.5F, 5.0F);
		this.right_fin.addBox(0.0F, 0.0F, -4.0F, 0, 8, 8, 0.0F);
		this.setRotateAngle(right_fin, 0.0F, -0.0F, 0.4363323129985824F);
		this.bottomfin = new ModelPart(this, 40, 20);
		this.bottomfin.setPos(0.0F, 2.0F, 0.0F);
		this.bottomfin.addBox(0.0F, 0.0F, 0.0F, 0, 4, 8, 0.0F);
		this.head = new ModelPart(this, 0, 11);
		this.head.setPos(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -2.5F, -3.0F, 3, 4, 3, 0.0F);
		this.left_fin = new ModelPart(this, 14, 11);
		this.left_fin.setPos(1.5F, 0.5F, 4.0F);
		this.left_fin.addBox(0.0F, 0.0F, -4.0F, 0, 8, 8, 0.0F);
		this.setRotateAngle(left_fin, 0.0F, -0.0F, -0.4363323129985824F);
		this.topfin = new ModelPart(this, 22, 19);
		this.topfin.setPos(0.0F, 0.0F, 0.0F);
		this.topfin.addBox(0.0F, -7.0F, 0.0F, 0, 5, 8, 0.0F);
		this.headFin = new ModelPart(this, 0, 18);
		this.headFin.setPos(0.0F, 0.0F, 0.0F);
		this.headFin.addBox(0.0F, -7.0F, -3.0F, 0, 5, 4, 0.0F);
		this.tail = new ModelPart(this, 0, -9);
		this.tail.setPos(0.0F, 0.0F, 8.0F);
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
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T lionfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean outOfWater = !lionfish.isInWater();

		float multiplier = outOfWater ? 1.35F : 1.1F;
		float thetaModifier = outOfWater ? 1.7F : 1.0F;

		this.tail.yRot = -multiplier * 0.20F * Mth.sin(thetaModifier * 0.65F * ageInTicks);
	}

	public void setRotateAngle(ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}
}
