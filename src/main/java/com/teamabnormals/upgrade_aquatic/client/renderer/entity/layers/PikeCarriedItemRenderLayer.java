package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.upgrade_aquatic.client.model.PikeModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PikeCarriedItemRenderLayer extends RenderLayer<Pike, PikeModel<Pike>> {

	public PikeCarriedItemRenderLayer(RenderLayerParent<Pike, PikeModel<Pike>> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn, Pike pike, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = pike.getItemBySlot(EquipmentSlot.MAINHAND);
		if (!itemstack.isEmpty()) {
			if (!itemstack.isEmpty()) {
				matrixStack.pushPose();
				matrixStack.translate((this.getParentModel()).nose.x / 16.0F, (this.getParentModel()).nose.y / 16.0F + 1.3F, (this.getParentModel()).nose.z / 16.0F - 0.5F);
				matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
				Minecraft.getInstance().getItemInHandRenderer().renderItem(pike, itemstack, ItemTransforms.TransformType.GROUND, false, matrixStack, bufferIn, packedLightIn);
				matrixStack.popPose();
			}
		}
	}

}