package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.blueprint.core.endimator.Endimator;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
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
 * ModelThrasher - SmellyModder
 * Created using Tabula 7.0.0
 */
public class ThrasherModel<E extends Thrasher> extends EndimatorEntityModel<E> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "thrasher"), "main");

	public ModelPart neck;
	public ModelPart body;
	public ModelPart top_jaw;
	public ModelPart bottom_jaw;
	public ModelPart left_fin;
	public ModelPart right_fin;
	public ModelPart fin;
	public ModelPart tail_holder;
	public ModelPart right_fin_2;
	public ModelPart left_fin_2;
	public ModelPart tail_holder_2;
	public ModelPart tail;
	public ModelPart tail_linear;

	public ThrasherModel(ModelPart root) {
		this.neck = root.getChild("neck");
		this.body = this.neck.getChild("body");
		this.top_jaw = this.neck.getChild("top_jaw");
		this.bottom_jaw = this.neck.getChild("bottom_jaw");
		this.left_fin = this.neck.getChild("left_fin");
		this.right_fin = this.neck.getChild("right_fin");
		this.fin = this.neck.getChild("fin");
		this.tail_holder = this.body.getChild("tail_holder");
		this.right_fin_2 = this.body.getChild("right_fin_2");
		this.left_fin_2 = this.body.getChild("left_fin_2");
		this.tail_holder_2 = this.tail_holder.getChild("tail_holder_2");
		this.tail = this.tail_holder_2.getChild("tail");
		this.tail_linear = this.tail.getChild("tail_linear");
		this.endimator = Endimator.compile(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 24).addBox(-6.0F, -5.0F, -4.0F, 12, 11, 8), PartPose.offsetAndRotation(0.0F, 17.0F, -10.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition body = neck.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 43).addBox(-6.0F, 0.0F, 0.0F, 12, 10, 13), PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition top_jaw = neck.addOrReplaceChild("top_jaw", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -2.5F, -10.0F, 12, 5, 10), PartPose.offsetAndRotation(0.0F, -2.5F, -4.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition bottom_jaw = neck.addOrReplaceChild("bottom_jaw", CubeListBuilder.create().texOffs(44, 0).addBox(-6.0F, -2.0F, -10.0F, 12, 4, 10), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition left_fin = neck.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0.0F, 0.0F, -2.5F, 10, 1, 6), PartPose.offsetAndRotation(6.0F, 0.5F, -0.5F, 0.0F, -0.17453292F, 0.20943952F));
		PartDefinition right_fin = neck.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(0, 16).addBox(-10.0F, 0.0F, -2.5F, 10, 1, 6), PartPose.offsetAndRotation(-6.0F, 0.5F, -0.5F, 0.0F, 0.17453292F, -0.20943952F));
		PartDefinition fin = neck.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(50, 50).addBox(0.0F, -7.0F, 0.0F, 0, 7, 12), PartPose.offsetAndRotation(0.0F, -5.0F, -2.5F, -0.17453292F, 0.0F, 0.0F));
		PartDefinition tail_holder = body.addOrReplaceChild("tail_holder", CubeListBuilder.create().texOffs(0, 66).addBox(-4.0F, -1.0F, 0.0F, 8, 7, 8), PartPose.offsetAndRotation(0.0F, 2.5F, 13.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition right_fin_2 = body.addOrReplaceChild("right_fin_2", CubeListBuilder.create().texOffs(33, 16).addBox(-13.0F, 0.0F, -4.0F, 13, 1, 8), PartPose.offsetAndRotation(-6.0F, 5.0F, 6.0F, 0.0F, 0.17453292F, -0.20943952F));
		PartDefinition left_fin_2 = body.addOrReplaceChild("left_fin_2", CubeListBuilder.create().texOffs(33, 16).mirror().addBox(-0.0F, 0.0F, -4.0F, 13, 1, 8), PartPose.offsetAndRotation(6.0F, 5.0F, 6.0F, 0.0F, -0.17453292F, 0.20943952F));
		PartDefinition tail_holder_2 = tail_holder.addOrReplaceChild("tail_holder_2", CubeListBuilder.create().texOffs(0, 81).addBox(-3.0F, -3.0F, 0.0F, 6, 6, 8), PartPose.offsetAndRotation(0.0F, 2.5F, 8.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition tail = tail_holder_2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(30, 70).addBox(0.0F, -3.0F, 0.0F, 0, 6, 18), PartPose.offsetAndRotation(0.0F, 0.1F, 8.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition tail_linear = tail.addOrReplaceChild("tail_linear", CubeListBuilder.create().texOffs(30, 70).addBox(0.0F, 0.0F, 0.0F, 6, 0, 18), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 100, 100);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.neck.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(E thrasher, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(thrasher, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float tailAnimation = thrasher.getTailAnimation(ageInTicks - thrasher.tickCount);
		float finWaveAnimation = thrasher.getFinAnimation(ageInTicks - thrasher.tickCount);

		this.neck.xRot = headPitch * (float) (Math.PI / 180F);
		this.neck.yRot = netHeadYaw * (float) (Math.PI / 180F);

		ModelPart[] tailComponents = new ModelPart[]{this.body, this.tail_holder, this.tail_holder_2};
		for (int i = 1; i < tailComponents.length + 1; i++) {
			tailComponents[i - 1].yRot = Mth.sin(tailAnimation) * (float) Math.PI * (0.045F * i);
		}

		if (thrasher.isMoving() && thrasher.isInWater()) {
			this.tail.zRot = Mth.sin((float) ((thrasher.tickCount + ageInTicks) * 2 * Math.PI * 0.8125D));
		}

		this.right_fin.zRot = (float) (-Mth.cos(finWaveAnimation) * (float) Math.PI * 0.13);
		this.right_fin_2.zRot = (float) (-Mth.cos(finWaveAnimation - 1.5F) * (float) Math.PI * 0.1);
		this.left_fin.zRot = (float) (Mth.cos(finWaveAnimation) * (float) Math.PI * 0.13);
		this.left_fin_2.zRot = (float) (Mth.cos(finWaveAnimation - 1.5F) * (float) Math.PI * 0.1);
	}
}