package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.ThrasherRenderLayer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.GreatThrasherEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreatThrasherRenderer extends MobRenderer<GreatThrasherEntity, ThrasherModel<GreatThrasherEntity>>{

	public GreatThrasherRenderer(EntityRendererManager renderer) {
		super(renderer, new ThrasherModel<>(), 1.575F);
		this.addLayer(new ThrasherRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(GreatThrasherEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/great_thrasher.png");
	}
	
	@Override
	protected void preRenderCallback(GreatThrasherEntity thrasher, MatrixStack matrixStack, float partialTickTime) {
		float scale = 1.75F;
		matrixStack.scale(scale, scale, scale);
	}
	
}