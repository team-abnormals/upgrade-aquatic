package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.GlowSquidModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.GlowSquidRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.GlowSquid;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class GlowSquidRenderer extends SquidRenderer<GlowSquid> {

	public GlowSquidRenderer(EntityRendererProvider.Context context) {
		super(context, new GlowSquidModel<>(context.bakeLayer(GlowSquidModel.LOCATION), false));
		this.addLayer(new GlowSquidRenderLayer(new GlowSquidModel<>(context.bakeLayer(GlowSquidModel.LOCATION), true), this));
	}

	@Override
	public ResourceLocation getTextureLocation(GlowSquid entity) {
		return MissingTextureAtlasSprite.getLocation();
	}
}