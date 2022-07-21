package com.teamabnormals.upgrade_aquatic.client.model;

import com.google.common.collect.ImmutableList;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.AgeableListModel;
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

public class GooseModel<T extends Entity> extends AgeableListModel<T> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "goose"), "main");

	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public GooseModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leftLeg = this.body.getChild("leftLeg");
		this.rightLeg = this.body.getChild("rightLeg");
		this.leftWing = this.body.getChild("leftWing");
		this.rightWing = this.body.getChild("rightWing");
		this.neck = root.getChild("neck");
		this.head = this.neck.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(22, 10).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 5.0F, 7.0F, false), PartPose.offsetAndRotation(0.0F, 17.0F, 1.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(9, 23).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, false), PartPose.offsetAndRotation(1.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(9, 23).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 3.0F, 3.0F, false), PartPose.offsetAndRotation(-1.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(2, 10).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 7.0F, false), PartPose.offsetAndRotation(2.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(2, 10).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 7.0F, false), PartPose.offsetAndRotation(-2.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(23, 1).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, false), PartPose.offsetAndRotation(0.0F, 16.0F, -2.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 2).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F, false).texOffs(13, 5).addBox(-1.5F, -0.5F, -4.0F, 3.0F, 1.0F, 1.0F, false), PartPose.offsetAndRotation(0.0F, -4.0F, -1.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of(this.neck);
	}

	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body);
	}

	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.neck.xRot = headPitch * ((float) Math.PI / 180F);
		this.neck.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.rightWing.zRot = ageInTicks;
		this.leftWing.zRot = -ageInTicks;
	}
}