package com.teamabnormals.upgrade_aquatic.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class PerchModel<T extends Entity> extends ListModel<T> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "perch"), "main");

	public ModelPart perch;
	public ModelPart body;
	public ModelPart sidefin1;
	public ModelPart sidefin2;
	public ModelPart frontfin1;
	public ModelPart frontfin2;
	public ModelPart backfin1;
	public ModelPart backfin2;

	public PerchModel(ModelPart root) {
		this.perch = root.getChild("perch");
		this.body = this.perch.getChild("body");
		this.sidefin1 = this.body.getChild("sidefin1");
		this.sidefin2 = this.body.getChild("sidefin2");
		this.frontfin1 = this.body.getChild("frontfin1");
		this.frontfin2 = this.body.getChild("frontfin2");
		this.backfin1 = this.body.getChild("backfin1");
		this.backfin2 = this.body.getChild("backfin2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition perch = root.addOrReplaceChild("perch", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition body = perch.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -4.0F, 2.0F, 5.0F, 11.0F, false).texOffs(15, 0).addBox(-1.0F, -3.0F, -7.0F, 2.0F, 3.0F, 3.0F, false).texOffs(0, 7).addBox(0.0F, -8.0F, -2.0F, 0.0F, 3.0F, 9.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition sidefin1 = body.addOrReplaceChild("sidefin1", CubeListBuilder.create().texOffs(15, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(1.0F, -2.0F, -4.0F, 0.0F, 0.2618F, 0.0F));
		PartDefinition sidefin2 = body.addOrReplaceChild("sidefin2", CubeListBuilder.create().texOffs(15, 2).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(-1.0F, -2.0F, -4.0F, 0.0F, -0.2618F, 0.0F));
		PartDefinition frontfin1 = body.addOrReplaceChild("frontfin1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.0872F, 0.0F, -1.0038F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(0.5F, 0.0F, -2.0F, 0.0F, 0.0873F, 0.0F));
		PartDefinition frontfin2 = body.addOrReplaceChild("frontfin2", CubeListBuilder.create().texOffs(0, 15).addBox(0.0872F, 0.0F, -1.0038F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(-0.5F, 0.0F, -2.0F, 0.0F, -0.0873F, 0.0F));
		PartDefinition backfin1 = body.addOrReplaceChild("backfin1", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition backfin2 = body.addOrReplaceChild("backfin2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(0.0F, -2.0F, 7.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(this.perch, this.body, this.sidefin1, this.sidefin2, this.frontfin1, this.frontfin2, this.backfin1, this.backfin2);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = !entityIn.isInWater() ? 1.5F : 1.0F;
		this.backfin2.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		perch.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}