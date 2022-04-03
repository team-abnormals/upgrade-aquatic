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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/**
 * ModelSonar - SmellyModder
 * Created using Tabula 7.0.0
 */
public class SonarWaveModel extends EntityModel<SonarWave> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "sonar_wave"), "main");

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
	public void renderToBuffer(PoseStack stack, VertexConsumer consumer, int p_103113_, int p_103114_, float r, float g, float b, float a) {
		stack.pushPose();
		float progress = this.sonarWave.getGrowProgress();
		float scale = 0.6F + progress;
		stack.scale(scale, scale, scale);
		this.wave.render(stack, consumer, p_103113_, p_103114_, r, g, b, a - (0.25F * progress));
		stack.popPose();
	}

	@Override
	public void setupAnim(SonarWave sonar, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.sonarWave = sonar;
		this.wave.xRot = (float) -Math.toRadians(Mth.lerp(ClientInfo.getPartialTicks(), sonar.xRotO, sonar.getXRot()));
	}
}