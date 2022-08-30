package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.EndimatorModelPart;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorLayerDefinition;
import com.teamabnormals.blueprint.core.endimator.model.EndimatorPartDefinition;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfish;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * ModelCassiopeaJellyfish - SnakeBlock
 * Created using Tabula 7.0.0
 */
public class CassiopeaJellyfishModel<E extends CassiopeaJellyfish> extends EndimatorEntityModel<E> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "cassiopea_jellyfish"), "main");

	public EndimatorModelPart cap;
	public EndimatorModelPart cross1;
	public EndimatorModelPart cross2;
	public EndimatorModelPart thing;
	public EndimatorModelPart tentacleT;
	public EndimatorModelPart tentacleE;
	public EndimatorModelPart tentacleN;
	public EndimatorModelPart tentacleW;

	@SuppressWarnings("all")
	public CassiopeaJellyfishModel(ModelPart root) {
		this.cap = (EndimatorModelPart) root.getChild("cap");
		this.cross1 = (EndimatorModelPart) this.cap.getChild("cross1");
		this.cross2 = (EndimatorModelPart) this.cap.getChild("cross2");
		this.thing = (EndimatorModelPart) this.cap.getChild("thing");
		this.tentacleT = (EndimatorModelPart) this.thing.getChild("tentacleT");
		this.tentacleE = (EndimatorModelPart) this.thing.getChild("tentacleE");
		this.tentacleN = (EndimatorModelPart) this.thing.getChild("tentacleN");
		this.tentacleW = (EndimatorModelPart) this.thing.getChild("tentacleW");
		this.cap.setShouldScaleChildren(false);
		this.endimator = Endimator.compile(root);
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
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		//TODO: Use Endimator in 1.19
		float amplifier = Math.min(limbSwingAmount, 0.25F) * 4.0F;
		float tentacleAngle = (0.45F * Mth.abs(Mth.sin(0.8F * limbSwing + 0.384F)) - 0.2F) * amplifier;
		this.tentacleN.xRot = tentacleAngle;
		this.tentacleE.xRot = tentacleAngle;
		this.tentacleT.xRot = tentacleAngle;
		this.tentacleW.xRot = tentacleAngle;
		float xzScale = 1.0F + (0.15F * Mth.sin(0.8F * limbSwing - 0.34F) + 0.05F) * amplifier;
		this.cap.setScale(xzScale, 1.0F + (-0.125F * Mth.sin(0.8F * limbSwing + 0.201F) + 0.025F) * amplifier, xzScale);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.cap.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
}
