package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.ModelThrasher;
import com.minecraftabnormals.upgrade_aquatic.client.render.overlay.RenderLayerThrasher;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrasherRenderer extends MobRenderer<EntityThrasher, ModelThrasher<EntityThrasher>> {

	public ThrasherRenderer(EntityRendererManager renderer) {
		super(renderer, new ModelThrasher<>(), 0.9F);
		this.addLayer(new RenderLayerThrasher<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(EntityThrasher entity) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/thrasher/thrasher.png");
	}

}