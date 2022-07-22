package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.UAGlowSquidModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.GlowSquidRenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.GlowSquid;

public class UAGlowSquidRenderer extends SquidRenderer<GlowSquid> {

	public UAGlowSquidRenderer(EntityRendererProvider.Context context) {
		super(context, new UAGlowSquidModel<>(context.bakeLayer(UAGlowSquidModel.LOCATION), false));
		this.addLayer(new GlowSquidRenderLayer(new UAGlowSquidModel<>(context.bakeLayer(UAGlowSquidModel.LOCATION), true), this));
	}

	@Override
	public ResourceLocation getTextureLocation(GlowSquid entity) {
		return MissingTextureAtlasSprite.getLocation();
	}

}