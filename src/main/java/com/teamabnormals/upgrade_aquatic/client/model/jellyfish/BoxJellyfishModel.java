package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.resources.ResourceLocation;

/**
 * BoxJelly - SnakeBlock
 * Created using Tabula 7.0.0
 */
public class BoxJellyfishModel<E extends BoxJellyfish> extends EndimatorEntityModel<E> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "box_jellyfish"), "main");

	public ModelPart body;
	public ModelPart tentacleW;
	public ModelPart tentacleS;
	public ModelPart tentacleE;
	public ModelPart tentacleN;
	public ModelPart tentacleNE;
	public ModelPart tentacleSE;
	public ModelPart tentacleNW;
	public ModelPart tentacleSW;

	public BoxJellyfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tentacleW = root.getChild("tentacleW");
		this.tentacleS = root.getChild("tentacleS");
		this.tentacleE = root.getChild("tentacleE");
		this.tentacleN = root.getChild("tentacleN");
		this.tentacleNE = root.getChild("tentacleNE");
		this.tentacleSE = root.getChild("tentacleSE");
		this.tentacleNW = root.getChild("tentacleNW");
		this.tentacleSW = root.getChild("tentacleSW");
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();
		EndimatorPartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleW = root.addOrReplaceChild("tentacleW", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 5.0F, 0.0F, 0.0F, 1.5707964F, 0.0F));
		EndimatorPartDefinition tentacleS = root.addOrReplaceChild("tentacleS", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleE = root.addOrReplaceChild("tentacleE", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 5.0F, 0.0F, 0.0F, 1.5707964F, 0.0F));
		EndimatorPartDefinition tentacleN = root.addOrReplaceChild("tentacleN", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, -3.0F, 0.0F, 3.1415927F, 0.0F));
		EndimatorPartDefinition tentacleNE = root.addOrReplaceChild("tentacleNE", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 5.0F, -3.0F, 0.0F, -2.3561945F, 0.0F));
		EndimatorPartDefinition tentacleSE = root.addOrReplaceChild("tentacleSE", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 5.0F, 3.0F, 0.0F, 5.497787F, 0.0F));
		EndimatorPartDefinition tentacleNW = root.addOrReplaceChild("tentacleNW", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 5.0F, -3.0F, 0.0F, 2.3561945F, 0.0F));
		EndimatorPartDefinition tentacleSW = root.addOrReplaceChild("tentacleSW", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 5.0F, 3.0F, 0.0F, 0.7853982F, 0.0F));
		return new EndimatorLayerDefinition(root, 48, 42);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float[] rotations = entity.getRotationController().getRotations(ClientInfo.getPartialTicks());
		this.body.yRot = (float) Math.toRadians(rotations[0]);
		this.body.xRot = (float) Math.toRadians(rotations[1]);
	}
}
