package com.teamabnormals.upgrade_aquatic.client.renderer.entity.jellyfish;

import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractJellyfishRenderer<J extends AbstractJellyfish> extends MobRenderer<J, EndimatorEntityModel<J>> {

	public AbstractJellyfishRenderer(EntityRendererProvider.Context context, EndimatorEntityModel<J> entityModelIn, float shadowSizeIn) {
		super(context, entityModelIn, shadowSizeIn);
	}

	public abstract ResourceLocation getOverlayTexture(J jellyfish);

}