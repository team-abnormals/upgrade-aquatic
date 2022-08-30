package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Flare;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelFlare - SmellyModer
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class FlareModel<F extends Flare> extends EntityModel<F> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "flare"), "main");

	public ModelPart base;
	public ModelPart tail;
	public ModelPart left_shoulder;
	public ModelPart right_shoulder;
	public ModelPart head;
	public ModelPart tail_end;
	public ModelPart left_wing;
	public ModelPart right_wing;
	public ModelPart shape13;

	public FlareModel(ModelPart root) {
		this.base = root.getChild("base");
		this.head = this.base.getChild("head");
		this.shape13 = this.head.getChild("shape13");
		this.left_shoulder = this.base.getChild("left_shoulder");
		this.left_wing = this.left_shoulder.getChild("left_wing");
		this.tail = this.base.getChild("tail");
		this.tail_end = this.tail.getChild("tail_end");
		this.right_shoulder = this.base.getChild("right_shoulder");
		this.right_wing = this.right_shoulder.getChild("right_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition base = root.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, 0.0F, 0.0F, 5.0F, 3.0F, 9.0F, false), PartPose.offsetAndRotation(-2.5F, 18.0F, -5.0F, -0.174533F, 0.0F, 0.0F));
		PartDefinition head = base.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 4.0F, 5.0F, false), PartPose.offsetAndRotation(2.5F, 1.2F, 0.0F, 0.349066F, 0.0F, 0.0F));
		PartDefinition shape13 = head.addOrReplaceChild("shape13", CubeListBuilder.create().texOffs(26, 0).addBox(-4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 5.0F, false), PartPose.offsetAndRotation(0.0F, 2.0F, -3.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition left_shoulder = base.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(27, 16).mirror().addBox(0.0F, 0.0F, -4.5F, 6.0F, 2.0F, 9.0F, true), PartPose.offsetAndRotation(5.0F, 0.0F, 4.5F, -0.0F, 0.0F, 0.17453292F));
		PartDefinition left_wing = left_shoulder.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(0.0F, 0.0F, -5.0F, 13.0F, 1.0F, 10.0F, true), PartPose.offsetAndRotation(6.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.17453292F));
		PartDefinition tail = base.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 20).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 10.0F, false), PartPose.offsetAndRotation(2.5F, 0.0F, 9.0F, -0.17453292F, 0.0F, 0.0F));
		PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 10.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 10.0F, -0.17453292F, 0.0F, 0.0F));
		PartDefinition right_shoulder = base.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(27, 16).addBox(-6.0F, 0.0F, -4.5F, 6.0F, 2.0F, 9.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 4.5F, 0.0F, 0.0F, -0.17453292F));
		PartDefinition right_wing = right_shoulder.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(0, 46).addBox(-13.0F, 0.0F, -5.0F, 13.0F, 1.0F, 10.0F, false), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.5F, 0.0F, -0.0F, -0.17453292F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.base.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
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