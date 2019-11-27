package com.teamabnormals.upgrade_aquatic.client.render;

import com.teamabnormals.upgrade_aquatic.client.model.ModelThrasher;
import com.teamabnormals.upgrade_aquatic.client.render.overlay.RenderLayerThrasher;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderThrasher extends MobRenderer<EntityThrasher, ModelThrasher<EntityThrasher>> {

	public RenderThrasher(EntityRendererManager renderer) {
		super(renderer, new ModelThrasher<>(), 0.9F);
		this.addLayer(new RenderLayerThrasher<>(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityThrasher entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/thrasher/thrasher.png");
	}

}
