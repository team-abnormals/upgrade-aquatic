package com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.upgrade_aquatic.client.model.PikeModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PikeCarriedItemRenderLayer extends RenderLayer<Pike, PikeModel<Pike>> {
	private final ItemInHandRenderer itemInHandRenderer;

	public PikeCarriedItemRenderLayer(RenderLayerParent<Pike, PikeModel<Pike>> renderer, ItemInHandRenderer itemInHandRenderer) {
		super(renderer);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn, Pike pike, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = pike.getItemBySlot(EquipmentSlot.MAINHAND);
		if (!itemstack.isEmpty()) {
			if (!itemstack.isEmpty()) {
				matrixStack.pushPose();
				matrixStack.translate((this.getParentModel()).nose.x / 16.0F, (this.getParentModel()).nose.y / 16.0F + 1.3F, (this.getParentModel()).nose.z / 16.0F - 0.5F);
				matrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				this.itemInHandRenderer.renderItem(pike, itemstack, ItemDisplayContext.GROUND, false, matrixStack, bufferIn, packedLightIn);
				matrixStack.popPose();
			}
		}
	}

}