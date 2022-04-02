package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Nautilus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelNautilus - Anomalocaris101
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NautilusModel<T extends Nautilus> extends EntityModel<T> {
	public static final float SCALE = 0.6F;
	public ModelPart shell;
	public ModelPart head;
	public ModelPart Shell2;
	public ModelPart tents_top;
	public ModelPart hood;
	public ModelPart tents_bottom;
	public ModelPart tents_right;
	public ModelPart tents_left;

	public NautilusModel() {
		this.texWidth = 64;
		this.texHeight = 32;
		this.hood = new ModelPart(this, 35, 0);
		this.hood.setPos(0.0F, -3.0F, -3.5F);
		this.hood.addBox(-3.5F, -0.5F, 0.0F, 7, 1, 7, 0.0F);
		this.tents_top = new ModelPart(this, 16, 11);
		this.tents_top.setPos(0.0F, -2.5F, 2.5F);
		this.tents_top.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 4, 0.0F);
		this.Shell2 = new ModelPart(this, 0, 18);
		this.Shell2.setPos(0.0F, 0.0F, -3.0F);
		this.Shell2.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 8, 0.0F);
		this.tents_left = new ModelPart(this, 26, 11);
		this.tents_left.setPos(-2.5F, 0.0F, 2.5F);
		this.tents_left.addBox(0.0F, -2.5F, 0.0F, 0, 5, 4, 0.0F);
		this.shell = new ModelPart(this, 28, 14);
		this.shell.setPos(0.0F, 30.0F, 0.0F);
		this.shell.addBox(-3.0F, -6.0F, -6.0F, 6, 6, 12, 0.0F);
		this.setRotateAngle(shell, 0.08726646259971647F, 0.0F, 0.0F);
		this.tents_right = new ModelPart(this, 21, 11);
		this.tents_right.setPos(2.5F, 0.0F, 2.5F);
		this.tents_right.addBox(0.0F, -2.5F, 0.0F, 0, 5, 4, 0.0F);
		this.tents_bottom = new ModelPart(this, 26, 11);
		this.tents_bottom.setPos(0.0F, 2.5F, 2.5F);
		this.tents_bottom.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 4, 0.0F);
		this.head = new ModelPart(this, 15, 0);
		this.head.setPos(0.0F, 3.1F, 4.0F);
		this.head.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
		this.setRotateAngle(head, -0.1308996938995747F, 0.0F, 0.0F);
		this.head.addChild(this.hood);
		this.head.addChild(this.tents_top);
		this.shell.addChild(this.Shell2);
		this.head.addChild(this.tents_left);
		this.head.addChild(this.tents_right);
		this.head.addChild(this.tents_bottom);
		this.shell.addChild(this.head);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		matrixStack.pushPose();
		matrixStack.scale(SCALE, SCALE, SCALE);
		this.shell.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		matrixStack.popPose();
	}

	@Override
	public void setupAnim(T nautilus, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean moving = nautilus.isMoving();
		boolean inWater = nautilus.isInWater();
		float shellSway = !inWater || moving ? 1.5F : 1.0F;

		this.tents_left.yRot = -shellSway * 0.10F * Mth.cos(0.2F * ageInTicks);
		this.tents_right.yRot = -shellSway * 0.10F * -Mth.cos(0.2F * ageInTicks);
		this.tents_top.xRot = -shellSway * 0.10F * Mth.sin(0.2F * ageInTicks);
		this.tents_bottom.xRot = -shellSway * 0.10F * -Mth.sin(0.2F * ageInTicks);

		if (moving && nautilus.isInWater()) {
			this.shell.xRot = -1.0F * 0.12F * Mth.sin(0.2F * ageInTicks);
		}
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelPart ModelRenderer, float x, float y, float z) {
		ModelRenderer.xRot = x;
		ModelRenderer.yRot = y;
		ModelRenderer.zRot = z;
	}
}
