package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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

	public ModelPart body;
	public ModelPart innerBody;
	public ModelPart tentacleEast;
	public ModelPart tentacleWest;
	public ModelPart bottomBody;
	public ModelPart tentacleSouth;
	public ModelPart tentacleNorth;
	public ModelPart tentacleSouthEast;
	public ModelPart tentacleSouthWest;
	public ModelPart tentacleNorthEast;
	public ModelPart tentacleNorthWest;

	public ImmortalJellyfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.innerBody = root.getChild("innerBody");
		this.tentacleEast = this.body.getChild("tentacleEast");
		this.tentacleWest = this.body.getChild("tentacleWest");
		this.bottomBody = this.body.getChild("bottomBody");
		this.tentacleSouth = this.body.getChild("tentacleSouth");
		this.tentacleNorth = this.body.getChild("tentacleNorth");
		this.tentacleSouthEast = this.body.getChild("tentacleSouthEast");
		this.tentacleSouthWest = this.body.getChild("tentacleSouthWest");
		this.tentacleNorthEast = this.body.getChild("tentacleNorthEast");
		this.tentacleNorthWest = this.body.getChild("tentacleNorthWest");
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

		float[] rotations = entity.getRotationController().getRotations(ClientInfo.getPartialTicks());
		this.body.yRot = this.innerBody.yRot = (float) Math.toRadians(rotations[0]);
		this.body.xRot = this.innerBody.xRot = (float) Math.toRadians(rotations[1]);

		if (entity.isInWater()) {
			this.tentacleNorth.xRot += 0.1F * Mth.sin(0.2F * ageInTicks);
			this.tentacleNorthEast.xRot -= 0.12F * Mth.sin(0.225F * ageInTicks);
			this.tentacleNorthWest.xRot += 0.1F * Mth.sin(0.2F * ageInTicks);

			this.tentacleSouth.xRot -= 0.1F * Mth.sin(0.2F * ageInTicks);
			this.tentacleSouthEast.xRot += 0.12F * Mth.sin(0.2F * ageInTicks);
			this.tentacleSouthWest.xRot -= 0.1F * Mth.sin(0.225F * ageInTicks);

			this.tentacleEast.zRot += 0.1F * Mth.sin(0.2F * ageInTicks);
			this.tentacleWest.zRot -= 0.1F * Mth.sin(0.225F * ageInTicks);
		}
	}
}
