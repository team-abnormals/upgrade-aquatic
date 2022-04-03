package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;

/**
 * ModelLionfish
 *
 * @author MCVinnyq
 * @author SmellyModder
 * Created using Tabula 7.0.0
 */
public class LionfishModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "lionfish"), "main");

	public ModelPart body;
	public ModelPart topfin;
	public ModelPart bottomfin;
	public ModelPart tail;
	public ModelPart head;
	public ModelPart right_fin;
	public ModelPart left_fin;
	public ModelPart headFin;

	public LionfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.right_fin = this.body.getChild("right_fin");
		this.bottomfin = this.body.getChild("bottomfin");
		this.head = this.body.getChild("head");
		this.headFin = this.head.getChild("headFin");
		this.left_fin = this.body.getChild("left_fin");
		this.topfin = this.body.getChild("topfin");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-1.5F, -2.5F, 0.0F, 3.0F, 5.0F, 8.0F, false), PartPose.offsetAndRotation(0.0F, 19.0F, -5.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(14, 11).addBox(0.0F, 0.0F, -4.0F, 0.0F, 8.0F, 8.0F, false), PartPose.offsetAndRotation(-1.5F, 0.5F, 5.0F, 0.0F, -0.0F, 0.43633232F));
		PartDefinition bottomfin = body.addOrReplaceChild("bottomfin", CubeListBuilder.create().texOffs(40, 20).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 8.0F, false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-1.5F, -2.5F, -3.0F, 3.0F, 4.0F, 3.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition headFin = head.addOrReplaceChild("headFin", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -7.0F, -3.0F, 0.0F, 5.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(14, 11).addBox(0.0F, 0.0F, -4.0F, 0.0F, 8.0F, 8.0F, false), PartPose.offsetAndRotation(1.5F, 0.5F, 4.0F, 0.0F, -0.0F, -0.43633232F));
		PartDefinition topfin = body.addOrReplaceChild("topfin", CubeListBuilder.create().texOffs(22, 19).addBox(0.0F, -7.0F, 0.0F, 0.0F, 5.0F, 8.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, -9).addBox(0.0F, -5.0F, 0.0F, 0.0F, 10.0F, 10.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
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
}
