package com.teamabnormals.upgrade_aquatic.client.render.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teamabnormals.upgrade_aquatic.client.model.ModelPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class RenderLayerPikeCarriedItem extends LayerRenderer<EntityPike, ModelPike<EntityPike>> {
	
	public RenderLayerPikeCarriedItem(IEntityRenderer<EntityPike, ModelPike<EntityPike>> renderer) {
		super(renderer);
	}
	
	@Override
	public void render(MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int packedLightIn, EntityPike pike, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = pike.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		if(!itemstack.isEmpty()) {
			if (!itemstack.isEmpty()) {
				matrixStack.push();
				
				matrixStack.translate((this.getEntityModel()).nose.rotationPointX / 16.0F, (this.getEntityModel()).nose.rotationPointY / 16.0F + 1.3F, (this.getEntityModel()).nose.rotationPointZ / 16.0F - 0.5F);
				
				matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
				
				Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(pike, itemstack, ItemCameraTransforms.TransformType.GROUND, false, matrixStack, bufferIn, packedLightIn);
				matrixStack.pop();
			}
		}
	}

}