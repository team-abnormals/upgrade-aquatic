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
		this.tentacleW = this.body.getChild("tentacleW");
		this.tentacleS = this.body.getChild("tentacleS");
		this.tentacleE = this.body.getChild("tentacleE");
		this.tentacleN = this.body.getChild("tentacleN");
		this.tentacleNE = this.body.getChild("tentacleNE");
		this.tentacleSE = this.body.getChild("tentacleSE");
		this.tentacleNW = this.body.getChild("tentacleNW");
		this.tentacleSW = this.body.getChild("tentacleSW");
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();
		EndimatorPartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().addBox(-6.0F, -5.0F, -6.0F, 12, 10, 12), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleW = body.addOrReplaceChild("tentacleW", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1), PartPose.offsetAndRotation(3.0F, 5.0F, 0.0F, 0.0F, 1.5707964F, 0.0F));
		EndimatorPartDefinition tentacleS = body.addOrReplaceChild("tentacleS", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1), PartPose.offsetAndRotation(0.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleE = body.addOrReplaceChild("tentacleE", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1), PartPose.offsetAndRotation(-3.0F, 5.0F, 0.0F, 0.0F, 1.5707964F, 0.0F));
		EndimatorPartDefinition tentacleN = body.addOrReplaceChild("tentacleN", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1), PartPose.offsetAndRotation(0.0F, 5.0F, -3.0F, 0.0F, 3.1415927F, 0.0F));
		EndimatorPartDefinition tentacleNE = body.addOrReplaceChild("tentacleNE", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1), PartPose.offsetAndRotation(-3.0F, 5.0F, -3.0F, 0.0F, -2.3561945F, 0.0F));
		EndimatorPartDefinition tentacleSE = body.addOrReplaceChild("tentacleSE", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1), PartPose.offsetAndRotation(-3.0F, 5.0F, 3.0F, 0.0F, 5.497787F, 0.0F));
		EndimatorPartDefinition tentacleNW = body.addOrReplaceChild("tentacleNW", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1), PartPose.offsetAndRotation(3.0F, 5.0F, -3.0F, 0.0F, 2.3561945F, 0.0F));
		EndimatorPartDefinition tentacleSW = body.addOrReplaceChild("tentacleSW", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1), PartPose.offsetAndRotation(3.0F, 5.0F, 3.0F, 0.0F, 0.7853982F, 0.0F));
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
