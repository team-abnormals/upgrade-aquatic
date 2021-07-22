package com.minecraftabnormals.upgrade_aquatic.client.model;// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class PerchModel<T extends Entity> extends SegmentedModel<T> {
	public ModelRenderer perch;
	public ModelRenderer body;
	public ModelRenderer sidefin1;
	public ModelRenderer sidefin2;
	public ModelRenderer frontfin1;
	public ModelRenderer frontfin2;
	public ModelRenderer backfin1;
	public ModelRenderer backfin2;

	public PerchModel() {
		texWidth = 32;
		texHeight = 32;

		perch = new ModelRenderer(this);
		perch.setPos(0.0F, 22.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		perch.addChild(body);
		body.texOffs(0, 0).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 5.0F, 11.0F, 0.0F, false);
		body.texOffs(15, 0).addBox(-1.0F, -3.0F, -7.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		body.texOffs(0, 7).addBox(0.0F, -8.0F, -2.0F, 0.0F, 3.0F, 9.0F, 0.0F, false);

		sidefin1 = new ModelRenderer(this);
		sidefin1.setPos(1.0F, -2.0F, -4.0F);
		body.addChild(sidefin1);
		setRotationAngle(sidefin1, 0.0F, 0.2618F, 0.0F);
		sidefin1.texOffs(15, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		sidefin2 = new ModelRenderer(this);
		sidefin2.setPos(-1.0F, -2.0F, -4.0F);
		body.addChild(sidefin2);
		setRotationAngle(sidefin2, 0.0F, -0.2618F, 0.0F);
		sidefin2.texOffs(15, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		frontfin1 = new ModelRenderer(this);
		frontfin1.setPos(0.5F, 0.0F, -2.0F);
		body.addChild(frontfin1);
		setRotationAngle(frontfin1, 0.0F, 0.0873F, 0.0F);
		frontfin1.texOffs(0, 15).addBox(-0.0872F, 0.0F, -1.0038F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		frontfin2 = new ModelRenderer(this);
		frontfin2.setPos(-0.5F, 0.0F, -2.0F);
		body.addChild(frontfin2);
		setRotationAngle(frontfin2, 0.0F, -0.0873F, 0.0F);
		frontfin2.texOffs(0, 15).addBox(0.0872F, 0.0F, -1.0038F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		backfin1 = new ModelRenderer(this);
		backfin1.setPos(0.0F, 0.0F, 5.0F);
		body.addChild(backfin1);
		backfin1.texOffs(0, 15).addBox(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		backfin2 = new ModelRenderer(this);
		backfin2.setPos(0.0F, -2.0F, 7.0F);
		body.addChild(backfin2);
		backfin2.texOffs(0, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 5.0F, 0.0F, false);
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		return ImmutableList.of(this.perch, this.body, this.sidefin1, this.sidefin2, this.frontfin1, this.frontfin2, this.backfin1, this.backfin2);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = !entityIn.isInWater() ? 1.5F : 1.0F;
		this.backfin2.yRot = -f * 0.45F * MathHelper.sin(0.6F * ageInTicks);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		perch.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}