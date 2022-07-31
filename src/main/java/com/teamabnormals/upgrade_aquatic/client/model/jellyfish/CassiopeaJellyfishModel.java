package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.resources.ResourceLocation;

/**
 * ModelCassiopeaJellyfish - SnakeBlock
 * Created using Tabula 7.0.0
 */
public class CassiopeaJellyfishModel<E extends CassiopeaJellyfish> extends EndimatorEntityModel<E> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "cassiopea_jellyfish"), "main");

	public ModelPart cap;
	public ModelPart cross1;
	public ModelPart cross2;
	public ModelPart thing;
	public ModelPart tentacleT;
	public ModelPart tentacleE;
	public ModelPart tentacleN;
	public ModelPart tentacleW;

	public CassiopeaJellyfishModel(ModelPart root) {
		this.cap = root.getChild("cap");
		this.cross1 = this.cap.getChild("cross1");
		this.cross2 = this.cap.getChild("cross2");
		this.thing = this.cap.getChild("thing");
		this.tentacleT = this.thing.getChild("tentacleT");
		this.tentacleE = this.thing.getChild("tentacleE");
		this.tentacleN = this.thing.getChild("tentacleN");
		this.tentacleW = this.thing.getChild("tentacleW");
	}

	public static EndimatorLayerDefinition createBodyLayer() {
		EndimatorPartDefinition root = EndimatorPartDefinition.root();
		EndimatorPartDefinition cap = root.addOrReplaceChild("cap", CubeListBuilder.create().addBox(-5.5F, -1.5F, -5.5F, 11, 3, 11), PartPose.offsetAndRotation(0.0F, 21.5F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition cross1 = cap.addOrReplaceChild("cross1", CubeListBuilder.create().texOffs(18, 24).addBox(-3.5F, 0.0F, 0.0F, 7, 13, 0), PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition cross2 = cap.addOrReplaceChild("cross2", CubeListBuilder.create().texOffs(18, 24).addBox(-3.5F, 0.0F, 0.0F, 7, 13, 0), PartPose.offsetAndRotation(0.0F, 1.5F, 0.0F, 0.0F, 1.5707964F, 0.0F));
		EndimatorPartDefinition thing = cap.addOrReplaceChild("thing", CubeListBuilder.create().texOffs(0, 14).addBox(-4.5F, -0.5F, -4.5F, 9, 1, 9), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleT = thing.addOrReplaceChild("tentacleT", CubeListBuilder.create().texOffs(0, 32).addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0), PartPose.offsetAndRotation(0.0F, 0.5F, 4.5F, 0.0F, 0.0F, 0.0F));
		EndimatorPartDefinition tentacleE = thing.addOrReplaceChild("tentacleE", CubeListBuilder.create().texOffs(0, 24).addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0), PartPose.offsetAndRotation(-4.5F, 0.5F, 0.0F, 0.0F, -1.5707964F, 0.0F));
		EndimatorPartDefinition tentacleN = thing.addOrReplaceChild("tentacleN", CubeListBuilder.create().texOffs(0, 32).addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0), PartPose.offsetAndRotation(0.0F, 0.5F, -4.5F, 0.0F, -3.1415927F, 0.0F));
		EndimatorPartDefinition tentacleW = thing.addOrReplaceChild("tentacleW", CubeListBuilder.create().texOffs(0, 24).addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0), PartPose.offsetAndRotation(4.5F, 0.5F, 0.0F, 0.0F, 1.5707964F, 0.0F));
		return new EndimatorLayerDefinition(root, 44, 52);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.cap.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float[] rotations = entity.getRotationController().getRotations(ClientInfo.getPartialTicks());
		this.cap.yRot = (float) Math.toRadians(rotations[0]);
		this.cap.xRot = (float) Math.toRadians(rotations[1]);
	}
}
