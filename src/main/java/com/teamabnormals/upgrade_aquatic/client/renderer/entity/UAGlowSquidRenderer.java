package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.UAGlowSquidModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.GlowSquidRenderLayer;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.GlowSquid;

public class UAGlowSquidRenderer extends SquidRenderer<GlowSquid> {
	private static final ResourceLocation GLOW_SQUID_LOCATION = new ResourceLocation("textures/entity/squid/glow_squid.png");
	public final boolean useReplacementRenderer;

	public UAGlowSquidRenderer(EntityRendererProvider.Context context) {
		super(context, UAConfig.CLIENT.replaceGlowSquidRenderer.get() ? new UAGlowSquidModel<>(context.bakeLayer(UAGlowSquidModel.LOCATION), false) : new SquidModel<>(context.bakeLayer(ModelLayers.GLOW_SQUID)));
		this.useReplacementRenderer = UAConfig.CLIENT.replaceGlowSquidRenderer.get();
		if (this.useReplacementRenderer) {
			this.addLayer(new GlowSquidRenderLayer(new UAGlowSquidModel<>(context.bakeLayer(UAGlowSquidModel.LOCATION), true), this));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(GlowSquid entity) {
		return this.useReplacementRenderer ? MissingTextureAtlasSprite.getLocation() : GLOW_SQUID_LOCATION;
	}

	@Override
	protected int getBlockLightLevel(GlowSquid entity, BlockPos pos) {
		if (!this.useReplacementRenderer) {
			int i = (int) Mth.clampedLerp(0.0F, 15.0F, 1.0F - (float) entity.getDarkTicksRemaining() / 10.0F);
			return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(entity, pos));
		}
		return super.getBlockLightLevel(entity, pos);
	}
}