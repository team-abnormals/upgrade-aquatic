package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class GooseModel<T extends Entity> extends AgeableModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer leftWing;
	private final ModelRenderer rightWing;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	

	public GooseModel() {
		textureWidth = 64;
		textureHeight = 32;

		neck = new ModelRenderer(this);
		neck.setRotationPoint(0.0F, 16.0F, -2.0F);
		neck.setTextureOffset(23, 1).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -4.0F, -1.0F);
		neck.addChild(head);
		head.setTextureOffset(0, 2).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(13, 5).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 17.0F, 1.0F);
		body.setTextureOffset(22, 10).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 5.0F, 7.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(1.0F, 4.0F, 0.0F);
		body.addChild(leftLeg);
		leftLeg.setTextureOffset(9, 23).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-1.0F, 4.0F, 0.0F);
		body.addChild(rightLeg);
		rightLeg.setTextureOffset(9, 23).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(2.0F, 0.0F, -3.0F);
		body.addChild(leftWing);
		leftWing.setTextureOffset(2, 10).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, true);

		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(-2.0F, 0.0F, -3.0F);
		body.addChild(rightWing);
		rightWing.setTextureOffset(2, 10).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
	}

	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.neck);
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.neck.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.neck.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightWing.rotateAngleZ = ageInTicks;
		this.leftWing.rotateAngleZ = -ageInTicks;
	}
}