package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teamabnormals.upgrade_aquatic.client.model.ModelThrasher;
import com.teamabnormals.upgrade_aquatic.client.render.overlay.RenderLayerThrasher;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityGreatThrasher;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreatThrasherRenderer extends MobRenderer<EntityGreatThrasher, ModelThrasher<EntityGreatThrasher>>{

	public GreatThrasherRenderer(EntityRendererManager renderer) {
		super(renderer, new ModelThrasher<>(), 1.575F);
		this.addLayer(new RenderLayerThrasher<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(EntityGreatThrasher entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/thrasher/great_thrasher.png");
	}
	
	@Override
	protected void preRenderCallback(EntityGreatThrasher thrasher, MatrixStack matrixStack, float partialTickTime) {
		float scale = 1.75F;
		matrixStack.scale(scale, scale, scale);
	}
	
}