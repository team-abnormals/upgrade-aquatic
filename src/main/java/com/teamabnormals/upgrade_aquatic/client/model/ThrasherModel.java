package com.teamabnormals.upgrade_aquatic.client.model;

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
		this.body = root.getChild("body");
		this.top_jaw = root.getChild("top_jaw");
		this.bottom_jaw = root.getChild("bottom_jaw");
		this.left_fin = root.getChild("left_fin");
		this.right_fin = root.getChild("right_fin");
		this.fin = root.getChild("fin");
		this.tail_holder = root.getChild("tail_holder");
		this.right_fin_2 = root.getChild("right_fin_2");
		this.left_fin_2 = root.getChild("left_fin_2");
		this.tail_holder_2 = root.getChild("tail_holder_2");
		this.tail = root.getChild("tail");
		this.tail_linear = root.getChild("tail_linear");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition neck = root.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 17.0F, -10.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition top_jaw = root.addOrReplaceChild("top_jaw", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -2.5F, -4.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition bottom_jaw = root.addOrReplaceChild("bottom_jaw", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 4.0F, -4.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition left_fin = root.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 0.5F, -0.5F, 0.0F, -0.17453292F, 0.20943952F));
		PartDefinition right_fin = root.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 0.5F, -0.5F, 0.0F, 0.17453292F, -0.20943952F));
		PartDefinition fin = root.addOrReplaceChild("fin", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -5.0F, -2.5F, -0.17453292F, 0.0F, 0.0F));
		PartDefinition tail_holder = root.addOrReplaceChild("tail_holder", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.5F, 13.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition right_fin_2 = root.addOrReplaceChild("right_fin_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 5.0F, 6.0F, 0.0F, 0.17453292F, -0.20943952F));
		PartDefinition left_fin_2 = root.addOrReplaceChild("left_fin_2", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, 5.0F, 6.0F, 0.0F, -0.17453292F, 0.20943952F));
		PartDefinition tail_holder_2 = root.addOrReplaceChild("tail_holder_2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 2.5F, 8.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition tail = root.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.1F, 8.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition tail_linear = root.addOrReplaceChild("tail_linear", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
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