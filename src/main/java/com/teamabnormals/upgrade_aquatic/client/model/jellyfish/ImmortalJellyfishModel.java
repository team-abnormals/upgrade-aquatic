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
		this.tentacleEast = root.getChild("tentacleEast");
		this.tentacleWest = root.getChild("tentacleWest");
		this.bottomBody = root.getChild("bottomBody");
		this.tentacleSouth = root.getChild("tentacleSouth");
		this.tentacleNorth = root.getChild("tentacleNorth");
		this.tentacleSouthEast = root.getChild("tentacleSouthEast");
		this.tentacleSouthWest = root.getChild("tentacleSouthWest");
		this.tentacleNorthEast = root.getChild("tentacleNorthEast");
		this.tentacleNorthWest = root.getChild("tentacleNorthWest");
	}
	
	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();
		EndimatorPartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition innerBody = root.addOrReplaceChild("innerBody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleEast = root.addOrReplaceChild("tentacleEast", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.43633232F));
		EndimatorPartDefinition tentacleWest = root.addOrReplaceChild("tentacleWest", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.0F, 3.1415927F, -0.43633232F));
		EndimatorPartDefinition bottomBody = root.addOrReplaceChild("bottomBody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleSouth = root.addOrReplaceChild("tentacleSouth", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, 3.2F, 0.43633232F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleNorth = root.addOrReplaceChild("tentacleNorth", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, -3.2F, 0.43633232F, 3.1415927F, 0.0F));
		EndimatorPartDefinition tentacleSouthEast = root.addOrReplaceChild("tentacleSouthEast", CubeListBuilder.create(), PartPose.offsetAndRotation(3.4F, 3.8F, 3.4F, 0.43633232F, 0.7853982F, 0.0F));
		EndimatorPartDefinition tentacleSouthWest = root.addOrReplaceChild("tentacleSouthWest", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.4F, 3.8F, 3.4F, 0.43633232F, -0.7853982F, 0.0F));
		EndimatorPartDefinition tentacleNorthEast = root.addOrReplaceChild("tentacleNorthEast", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.4F, 3.8F, -3.4F, 0.43633232F, -2.3561945F, 0.0F));
		EndimatorPartDefinition tentacleNorthWest = root.addOrReplaceChild("tentacleNorthWest", CubeListBuilder.create(), PartPose.offsetAndRotation(3.4F, 3.8F, -3.4F, 0.43633232F, 2.3561945F, 0.0F));
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
