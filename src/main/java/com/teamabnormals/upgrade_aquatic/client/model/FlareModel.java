package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.common.entity.monster.FlareEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelFlare - SmellyModer
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class FlareModel<F extends FlareEntity> extends EntityModel<F> {
	public ModelPart base;
	public ModelPart tail;
	public ModelPart left_shoulder;
	public ModelPart right_shoulder;
	public ModelPart head;
	public ModelPart tail_end;
	public ModelPart left_wing;
	public ModelPart right_wing;
	public ModelPart shape13;

	public FlareModel() {
		this.texWidth = 64;
		this.texHeight = 64;
		this.head = new ModelPart(this, 0, 0);
		this.head.setPos(2.5F, 1.2F, 0.0F);
		this.head.addBox(-3.5F, 0.0F, -5.0F, 7, 4, 5, 0.0F);
		this.setRotateAngle(head, 0.349066F, 0.0F, 0.0F);
		this.right_wing = new ModelPart(this, 0, 46);
		this.right_wing.setPos(-6.0F, 0.0F, 0.5F);
		this.right_wing.addBox(-13.0F, 0.0F, -5.0F, 13, 1, 10, 0.0F);
		this.setRotateAngle(right_wing, 0.0F, -0.0F, -0.17453292519943295F);
		this.left_shoulder = new ModelPart(this, 27, 16);
		this.left_shoulder.mirror = true;
		this.left_shoulder.setPos(5.0F, 0.0F, 4.5F);
		this.left_shoulder.addBox(0.0F, 0.0F, -4.5F, 6, 2, 9, 0.0F);
		this.setRotateAngle(left_shoulder, -0.0F, 0.0F, 0.17453292519943295F);
		this.tail = new ModelPart(this, 0, 20);
		this.tail.setPos(2.5F, 0.0F, 9.0F);
		this.tail.addBox(-2.5F, 0.0F, 0.0F, 5, 3, 10, 0.0F);
		this.setRotateAngle(tail, -0.17453292519943295F, 0.0F, 0.0F);
		this.shape13 = new ModelPart(this, 26, 0);
		this.shape13.setPos(0.0F, 2.0F, -3.0F);
		this.shape13.addBox(-4.5F, 0.0F, -3.0F, 9, 2, 5, 0.0F);
		this.right_shoulder = new ModelPart(this, 27, 16);
		this.right_shoulder.setPos(0.0F, 0.0F, 4.5F);
		this.right_shoulder.addBox(-6.0F, 0.0F, -4.5F, 6, 2, 9, 0.0F);
		this.setRotateAngle(right_shoulder, 0.0F, 0.0F, -0.17453292519943295F);
		this.tail_end = new ModelPart(this, 0, 33);
		this.tail_end.setPos(0.0F, 0.0F, 10.0F);
		this.tail_end.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 10, 0.0F);
		this.setRotateAngle(tail_end, -0.17453292519943295F, 0.0F, 0.0F);
		this.left_wing = new ModelPart(this, 0, 46);
		this.left_wing.mirror = true;
		this.left_wing.setPos(6.0F, 0.0F, 0.5F);
		this.left_wing.addBox(0.0F, 0.0F, -5.0F, 13, 1, 10, 0.0F);
		this.setRotateAngle(left_wing, 0.0F, 0.0F, 0.17453292519943295F);
		this.base = new ModelPart(this, 0, 8);
		this.base.setPos(-2.5F, 18.0F, -5.0F);
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
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.base.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotateAngle(ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(F flare, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ((flare.getId() * 3) + ageInTicks) * 0.13F;
		float f1 = 16.0F;

		this.left_shoulder.zRot = Mth.cos(f) * f1 * ((float) Math.PI / 180F);
		this.left_wing.zRot = this.left_shoulder.zRot;

		this.right_shoulder.zRot = -this.left_shoulder.zRot;
		this.right_wing.zRot = -this.left_wing.zRot;

		this.tail.xRot = -(5.0F + Mth.cos(f * 2.0F) * 5.0F) * ((float) Math.PI / 180F);
		this.tail_end.xRot = this.tail.xRot;
	}
}