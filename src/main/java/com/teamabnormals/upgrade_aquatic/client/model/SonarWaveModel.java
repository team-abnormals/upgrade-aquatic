package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.upgrade_aquatic.common.entity.projectile.SonarWave;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.FastColor.ARGB32;
import net.minecraft.util.Mth;

/**
 * ModelSonar - SmellyModder
 * Created using Tabula 7.0.0
 */
public class SonarWaveModel extends EntityModel<SonarWave> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(UpgradeAquatic.location("sonar_wave"), "main");

	public ModelPart wave;
	private SonarWave sonarWave;

	public SonarWaveModel(ModelPart root) {
		this.wave = root.getChild("wave");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition wave = root.addOrReplaceChild("wave", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, false), PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int p_103113_, int p_103114_, int color) {
		stack.pushPose();
		float progress = this.sonarWave.getGrowProgress();
		float scale = 0.6F + progress;
		stack.scale(scale, scale, scale);
		int i = ARGB32.color(Mth.floor(ARGB32.alpha(color) - (0.25F * progress)), 255, 255, 255);
		this.wave.render(stack, consumer, p_103113_, p_103114_, i);
		stack.popPose();
	}

	@Override
	public void setupAnim(SonarWave sonar, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.sonarWave = sonar;
		this.wave.xRot = (float) -Math.toRadians(Mth.lerp(ClientInfo.getPartialTicks(), sonar.xRotO, sonar.getXRot()));
	}
}