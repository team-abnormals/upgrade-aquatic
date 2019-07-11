package com.teamabnormals.upgrade_aquatic.client.render;

import com.teamabnormals.upgrade_aquatic.client.model.ModelNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderNautilus extends MobRenderer<EntityNautilus, ModelNautilus<EntityNautilus>> {
	private static final ResourceLocation NAUTILUS_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/nautilus.png");

	public RenderNautilus(EntityRendererManager renderManager) {
        super(renderManager, new ModelNautilus<>(), 0.5F);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityNautilus entity) {
		return NAUTILUS_TEXTURE;
	}
	
}
