package com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;
import com.teamabnormals.abnormals_core.core.library.endimator.EndimatorEntityModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractJellyfishRenderer<J extends AbstractEntityJellyfish> extends MobRenderer<J, EndimatorEntityModel<J>> {

	public AbstractJellyfishRenderer(EntityRendererManager renderManagerIn, EndimatorEntityModel<J> entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}
	
	public abstract ResourceLocation getOverlayTexture(J jellyfish);

}