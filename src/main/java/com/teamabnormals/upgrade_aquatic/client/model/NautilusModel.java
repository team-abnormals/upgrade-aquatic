package com.teamabnormals.upgrade_aquatic.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Nautilus;
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
 * ModelNautilus - Anomalocaris101
 * Created using Tabula 7.0.0
 */
@OnlyIn(Dist.CLIENT)
public class NautilusModel<T extends Nautilus> extends EntityModel<T> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(UpgradeAquatic.MOD_ID, "nautilus"), "main");
	public static final float SCALE = 0.6F;

	public ModelPart shell;
	public ModelPart head;
	public ModelPart Shell2;
	public ModelPart tents_top;
	public ModelPart hood;
	public ModelPart tents_bottom;
	public ModelPart tents_right;
	public ModelPart tents_left;

	public NautilusModel(ModelPart root) {
		this.shell = root.getChild("shell");
		this.Shell2 = this.shell.getChild("Shell2");
		this.head = this.shell.getChild("head");
		this.hood = this.head.getChild("hood");
		this.tents_top = this.head.getChild("tents_top");
		this.tents_left = this.head.getChild("tents_left");
		this.tents_right = this.head.getChild("tents_right");
		this.tents_bottom = this.head.getChild("tents_bottom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition root = meshdefinition.getRoot();
		PartDefinition shell = root.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(28, 14).addBox(-3.0F, -6.0F, -6.0F, 6.0F, 6.0F, 12.0F, false), PartPose.offsetAndRotation(0.0F, 30.0F, 0.0F, 0.08726646F, 0.0F, 0.0F));
		PartDefinition Shell2 = shell.addOrReplaceChild("Shell2", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 8.0F, false), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.0F));
		PartDefinition head = shell.addOrReplaceChild("head", CubeListBuilder.create().texOffs(15, 0).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, false), PartPose.offsetAndRotation(0.0F, 3.1F, 4.0F, -0.1308997F, 0.0F, 0.0F));
		PartDefinition hood = head.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(35, 0).addBox(-3.5F, -0.5F, 0.0F, 7.0F, 1.0F, 7.0F, false), PartPose.offsetAndRotation(0.0F, -3.0F, -3.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition tents_top = head.addOrReplaceChild("tents_top", CubeListBuilder.create().texOffs(16, 11).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, -2.5F, 2.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition tents_left = head.addOrReplaceChild("tents_left", CubeListBuilder.create().texOffs(26, 11).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 4.0F, false), PartPose.offsetAndRotation(-2.5F, 0.0F, 2.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition tents_right = head.addOrReplaceChild("tents_right", CubeListBuilder.create().texOffs(21, 11).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 4.0F, false), PartPose.offsetAndRotation(2.5F, 0.0F, 2.5F, 0.0F, 0.0F, 0.0F));
		PartDefinition tents_bottom = head.addOrReplaceChild("tents_bottom", CubeListBuilder.create().texOffs(26, 11).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, false), PartPose.offsetAndRotation(0.0F, 2.5F, 2.5F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		matrixStack.pushPose();
		matrixStack.scale(SCALE, SCALE, SCALE);
		this.shell.render(matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		matrixStack.popPose();
	}

	@Override
	public void setupAnim(T nautilus, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean moving = nautilus.isMoving();
		boolean inWater = nautilus.isInWater();
		float shellSway = !inWater || moving ? 1.5F : 1.0F;

		this.tents_left.yRot = -shellSway * 0.10F * Mth.cos(0.2F * ageInTicks);
		this.tents_right.yRot = -shellSway * 0.10F * -Mth.cos(0.2F * ageInTicks);
		this.tents_top.xRot = -shellSway * 0.10F * Mth.sin(0.2F * ageInTicks);
		this.tents_bottom.xRot = -shellSway * 0.10F * -Mth.sin(0.2F * ageInTicks);

		if (moving && nautilus.isInWater()) {
			this.shell.xRot = -1.0F * 0.12F * Mth.sin(0.2F * ageInTicks);
		}
	}
}
