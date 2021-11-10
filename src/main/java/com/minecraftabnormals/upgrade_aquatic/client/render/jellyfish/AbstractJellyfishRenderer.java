package com.minecraftabnormals.upgrade_aquatic.client.render.jellyfish;

import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatorEntityModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractJellyfishRenderer<J extends AbstractJellyfishEntity> extends MobRenderer<J, EndimatorEntityModel<J>> {

	public AbstractJellyfishRenderer(EntityRendererManager renderManagerIn, EndimatorEntityModel<J> entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}

	public abstract ResourceLocation getOverlayTexture(J jellyfish);

}