package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.EndimatorModelPart;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfish;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * ModelImmortalJellyfish - Undefined
 * Created using Tabula 7.0.0
 */
public class ImmortalJellyfishModel<E extends ImmortalJellyfish> extends EndimatorEntityModel<E> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "immortal_jellyfish"), "main");

	public EndimatorModelPart body;
	public EndimatorModelPart innerBody;
	public EndimatorModelPart tentacleEast;
	public EndimatorModelPart tentacleWest;
	public EndimatorModelPart bottomBody;
	public EndimatorModelPart tentacleSouth;
	public EndimatorModelPart tentacleNorth;
	public EndimatorModelPart tentacleSouthEast;
	public EndimatorModelPart tentacleSouthWest;
	public EndimatorModelPart tentacleNorthEast;
	public EndimatorModelPart tentacleNorthWest;

	@SuppressWarnings("all")
	public ImmortalJellyfishModel(ModelPart root) {
		this.body = (EndimatorModelPart) root.getChild("body");
		this.innerBody = (EndimatorModelPart) root.getChild("innerBody");
		this.tentacleEast = (EndimatorModelPart) this.body.getChild("tentacleEast");
		this.tentacleWest = (EndimatorModelPart) this.body.getChild("tentacleWest");
		this.bottomBody = (EndimatorModelPart) this.body.getChild("bottomBody");
		this.tentacleSouth = (EndimatorModelPart) this.body.getChild("tentacleSouth");
		this.tentacleNorth = (EndimatorModelPart) this.body.getChild("tentacleNorth");
		this.tentacleSouthEast = (EndimatorModelPart) this.body.getChild("tentacleSouthEast");
		this.tentacleSouthWest = (EndimatorModelPart) this.body.getChild("tentacleSouthWest");
		this.tentacleNorthEast = (EndimatorModelPart) this.body.getChild("tentacleNorthEast");
		this.tentacleNorthWest = (EndimatorModelPart) this.body.getChild("tentacleNorthWest");
		this.body.setShouldScaleChildren(false);
		this.endimator = Endimator.compile(root);
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();
		EndimatorPartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -4.0F, -4.0F, 8, 7, 8), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition innerBody = root.addOrReplaceChild("innerBody", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, -2.0F, -1.5F, 3, 5, 3), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleEast = body.addOrReplaceChild("tentacleEast", CubeListBuilder.create().addBox(-8.0F, 0.0F, 0.0F, 8, 8, 0), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.43633232F));
		EndimatorPartDefinition tentacleWest = body.addOrReplaceChild("tentacleWest", CubeListBuilder.create().addBox(-8.0F, 0.0F, 0.0F, 8, 8, 0), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.0F, 3.1415927F, -0.43633232F));
		EndimatorPartDefinition bottomBody = body.addOrReplaceChild("bottomBody", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -1.0F, -5.0F, 10, 1, 10), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleSouth = body.addOrReplaceChild("tentacleSouth", CubeListBuilder.create().texOffs(0, -8).addBox(0.0F, 0.0F, 0.0F, 0, 8, 8), PartPose.offsetAndRotation(0.0F, 4.0F, 3.2F, 0.43633232F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleNorth = body.addOrReplaceChild("tentacleNorth", CubeListBuilder.create().texOffs(0, -8).addBox(0.0F, 0.0F, 0.0F, 0, 8, 8), PartPose.offsetAndRotation(0.0F, 4.0F, -3.2F, 0.43633232F, 3.1415927F, 0.0F));
		EndimatorPartDefinition tentacleSouthEast = body.addOrReplaceChild("tentacleSouthEast", CubeListBuilder.create().texOffs(0, -8).addBox(0.0F, 0.0F, 0.0F, 0, 8, 8), PartPose.offsetAndRotation(3.4F, 3.8F, 3.4F, 0.43633232F, 0.7853982F, 0.0F));
		EndimatorPartDefinition tentacleSouthWest = body.addOrReplaceChild("tentacleSouthWest", CubeListBuilder.create().texOffs(0, -8).addBox(0.0F, 0.0F, 0.0F, 0, 8, 8), PartPose.offsetAndRotation(-3.4F, 3.8F, 3.4F, 0.43633232F, -0.7853982F, 0.0F));
		EndimatorPartDefinition tentacleNorthEast = body.addOrReplaceChild("tentacleNorthEast", CubeListBuilder.create().texOffs(0, -8).addBox(0.0F, 0.0F, 0.0F, 0, 8, 8), PartPose.offsetAndRotation(-3.4F, 3.8F, -3.4F, 0.43633232F, -2.3561945F, 0.0F));
		EndimatorPartDefinition tentacleNorthWest = body.addOrReplaceChild("tentacleNorthWest", CubeListBuilder.create().texOffs(0, -8).addBox(0.0F, 0.0F, 0.0F, 0, 8, 8), PartPose.offsetAndRotation(3.4F, 3.8F, -3.4F, 0.43633232F, 2.3561945F, 0.0F));
		return new EndimatorLayerDefinition(root, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.innerBody.render(matrixStackIn, bufferIn, 240, packedOverlayIn, red, green, blue, alpha);
		this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		//TODO: Use Endimator in 1.19
		float amplifier = Math.min(limbSwingAmount, 0.25F) * 4.0F;
		float progress = Mth.sin(limbSwing) * amplifier;
		float tentacleAngle = 0.45F * progress;
		this.tentacleNorth.xRot = tentacleAngle;
		this.tentacleNorthEast.xRot = tentacleAngle;
		this.tentacleNorthWest.xRot = tentacleAngle;
		this.tentacleSouth.xRot = tentacleAngle;
		this.tentacleSouthEast.xRot = tentacleAngle;
		this.tentacleSouthWest.xRot = -tentacleAngle;
		this.tentacleEast.zRot = tentacleAngle;
		this.tentacleWest.zRot = tentacleAngle;
		progress = Mth.abs(progress);
		float xzScale = 1.0F + 0.15F * progress;
		float yScale = 1.0F - 0.25F * progress;
		this.body.setScale(xzScale, yScale, xzScale);
		this.body.setOffset(0.0F, 0.04166666666F * progress, 0.0F);
		this.innerBody.setScale(xzScale, yScale, xzScale);
		this.innerBody.y = 20.0F + 1.5F * progress;
	}
}
