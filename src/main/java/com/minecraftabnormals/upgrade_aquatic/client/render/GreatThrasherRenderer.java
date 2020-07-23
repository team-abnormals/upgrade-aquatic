package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.ThrasherRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityGreatThrasher;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreatThrasherRenderer extends MobRenderer<EntityGreatThrasher, ThrasherModel<EntityGreatThrasher>>{

	public GreatThrasherRenderer(EntityRendererManager renderer) {
		super(renderer, new ThrasherModel<>(), 1.575F);
		this.addLayer(new ThrasherRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(EntityGreatThrasher entity) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/thrasher/great_thrasher.png");
	}
	
	@Override
	protected void preRenderCallback(EntityGreatThrasher thrasher, MatrixStack matrixStack, float partialTickTime) {
		float scale = 1.75F;
		matrixStack.scale(scale, scale, scale);
	}
	
}