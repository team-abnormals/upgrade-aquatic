package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelPickerel - Five & SmellyModder
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class PikeModel<T extends Pike> extends EntityModel<T> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "pike"), "main");

	public ModelPart body_front;
	public ModelPart neck;
	public ModelPart body_back;
	public ModelPart left_fin_1;
	public ModelPart left_fin_2;
	public ModelPart right_fin_1;
	public ModelPart right_fin_2;
	public ModelPart nose;
	public ModelPart tailfin;
	public ModelPart top_dorsal;
	public ModelPart left_dorsal;
	public ModelPart right_dorsal;

	public PikeModel(ModelPart root) {
		this.body_front = root.getChild("body_front");
		this.left_fin_2 = this.body_front.getChild("left_fin_2");
		this.left_fin_1 = this.body_front.getChild("left_fin_1");
		this.right_fin_2 = this.body_front.getChild("right_fin_2");
		this.right_fin_1 = this.body_front.getChild("right_fin_1");
		this.neck = this.body_front.getChild("neck");
		this.nose = this.neck.getChild("nose");
		this.body_back = this.body_front.getChild("body_back");
		this.tailfin = this.body_back.getChild("tailfin");
		this.top_dorsal = this.body_back.getChild("top_dorsal");
		this.left_dorsal = this.body_back.getChild("left_dorsal");
		this.right_dorsal = this.body_back.getChild("right_dorsal");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition body_front = root.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 5.0F, 7.0F, false), PartPose.offsetAndRotation(0.0F, 19.5F, 1.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition left_fin_2 = body_front.addOrReplaceChild("left_fin_2", CubeListBuilder.create().texOffs(10, 27).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 3.0F, false), PartPose.offsetAndRotation(1.1F, 3.0F, 1.5F, 0.0F, 0.0F, -0.87266463F));
		PartDefinition left_fin_1 = body_front.addOrReplaceChild("left_fin_1", CubeListBuilder.create().texOffs(18, 28).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, false), PartPose.offsetAndRotation(1.1F, 3.0F, -3.0F, 0.0F, 0.0F, -0.87266463F));
		PartDefinition right_fin_2 = body_front.addOrReplaceChild("right_fin_2", CubeListBuilder.create().texOffs(10, 27).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 3.0F, false), PartPose.offsetAndRotation(-1.0F, 3.0F, 2.5F, 0.0F, 0.0F, 0.87266463F));
		PartDefinition right_fin_1 = body_front.addOrReplaceChild("right_fin_1", CubeListBuilder.create().texOffs(18, 28).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, false), PartPose.offsetAndRotation(-1.1F, 3.0F, -3.0F, 0.0F, 0.0F, 0.87266463F));
		PartDefinition neck = body_front.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(20, 12).addBox(0.0F, 0.0F, 0.0F, 3.0F, 4.0F, 3.0F, false), PartPose.offsetAndRotation(-1.5F, -0.5F, -6.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition nose = neck.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 28).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F, false), PartPose.offsetAndRotation(0.5F, 1.0F, -2.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition body_back = body_front.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, 0.5F, 0.0F, 3.0F, 5.0F, 7.0F, false), PartPose.offsetAndRotation(0.0F, -2.0F, 3.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition tailfin = body_back.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(22, 15).addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(0.0F, 0.5F, 7.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition top_dorsal = body_back.addOrReplaceChild("top_dorsal", CubeListBuilder.create().texOffs(24, 24).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, -1.5F, 5.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition left_dorsal = body_back.addOrReplaceChild("left_dorsal", CubeListBuilder.create().texOffs(24, 26).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(1.1F, 5.0F, 5.0F, 0.0F, 0.0F, -0.87266463F));
		PartDefinition right_dorsal = body_back.addOrReplaceChild("right_dorsal", CubeListBuilder.create().texOffs(24, 26).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, false), PartPose.offsetAndRotation(-1.1F, 5.0F, 5.0F, 0.0F, 0.0F, 0.87266463F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.body_front.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(T pike, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean outOfWater = !pike.isInWater();

		float multiplier = outOfWater ? -1.35F : -1.1F;
		float thetaModifier = outOfWater ? 1.7F : 1.0F;

		this.body_back.yRot = multiplier * 0.25F * Mth.sin(thetaModifier * 0.6F * ageInTicks);
	}
}
