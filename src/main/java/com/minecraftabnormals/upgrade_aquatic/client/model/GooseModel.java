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
		texWidth = 64;
		texHeight = 32;

		neck = new ModelRenderer(this);
		neck.setPos(0.0F, 16.0F, -2.0F);
		neck.texOffs(23, 1).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -4.0F, -1.0F);
		neck.addChild(head);
		head.texOffs(0, 2).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		head.texOffs(13, 5).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 17.0F, 1.0F);
		body.texOffs(22, 10).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 5.0F, 7.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setPos(1.0F, 4.0F, 0.0F);
		body.addChild(leftLeg);
		leftLeg.texOffs(9, 23).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setPos(-1.0F, 4.0F, 0.0F);
		body.addChild(rightLeg);
		rightLeg.texOffs(9, 23).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		leftWing = new ModelRenderer(this);
		leftWing.setPos(2.0F, 0.0F, -3.0F);
		body.addChild(leftWing);
		leftWing.texOffs(2, 10).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, true);

		rightWing = new ModelRenderer(this);
		rightWing.setPos(-2.0F, 0.0F, -3.0F);
		body.addChild(rightWing);
		rightWing.texOffs(2, 10).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 7.0F, 0.0F, false);
	}

	protected Iterable<ModelRenderer> headParts() {
		return ImmutableList.of(this.neck);
	}

	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(this.body);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.neck.xRot = headPitch * ((float)Math.PI / 180F);
		this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.rightWing.zRot = ageInTicks;
		this.leftWing.zRot = -ageInTicks;
	}
}