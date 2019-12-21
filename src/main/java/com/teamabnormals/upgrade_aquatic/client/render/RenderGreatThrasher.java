package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
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
public class RenderGreatThrasher extends MobRenderer<EntityGreatThrasher, ModelThrasher<EntityGreatThrasher>>{

	public RenderGreatThrasher(EntityRendererManager renderer) {
		super(renderer, new ModelThrasher<>(), 1.575F);
		this.addLayer(new RenderLayerThrasher<>(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGreatThrasher entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/thrasher/great_thrasher.png");
	}
	
	@Override
	protected void preRenderCallback(EntityGreatThrasher thrasher, float partialTickTime) {
		GlStateManager.scalef(1.75F, 1.75F, 1.75F);
	}
	
}