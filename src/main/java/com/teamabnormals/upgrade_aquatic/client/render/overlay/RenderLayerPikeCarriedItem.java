package com.teamabnormals.upgrade_aquatic.client.render.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.client.model.ModelPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class RenderLayerPikeCarriedItem extends LayerRenderer<EntityPike, ModelPike<EntityPike>> {
	private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

	public RenderLayerPikeCarriedItem(IEntityRenderer<EntityPike, ModelPike<EntityPike>> p_i50944_1_) {
		super(p_i50944_1_);
	}

	@Override
	public void render(EntityPike entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float f, float f1, float p_212842_8_) {
		ItemStack itemstack = entityIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
		if (!itemstack.isEmpty()) {
			this.renderItem(entityIn, itemstack, f, f1);
		}
	}
	
	private void renderItem(LivingEntity entity, ItemStack stack, float f, float f1) {
		if (!stack.isEmpty()) {
			Item item = stack.getItem();
			Block block = Block.getBlockFromItem(item);
			
			GlStateManager.pushMatrix();
			
			boolean flag = this.itemRenderer.shouldRenderItemIn3D(stack) && block.getRenderLayer() == BlockRenderLayer.TRANSLUCENT;
			if (flag) {
				GlStateManager.depthMask(false);
			}

			GlStateManager.translatef((this.getEntityModel()).nose.rotationPointX / 16.0F, (this.getEntityModel()).nose.rotationPointY / 16.0F + 1.3F, (this.getEntityModel()).nose.rotationPointZ / 16.0F - 0.5F);
			GlStateManager.rotatef(90F, 1.0F, 0.0F, 0.0F);
			
			this.itemRenderer.renderItem(stack, entity, ItemCameraTransforms.TransformType.GROUND, false);
			
			if (flag) {
				GlStateManager.depthMask(true);
			}

			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
