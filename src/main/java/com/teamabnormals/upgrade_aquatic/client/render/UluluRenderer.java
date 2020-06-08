package com.teamabnormals.upgrade_aquatic.client.render;

import com.teamabnormals.upgrade_aquatic.client.model.ModelUlulu;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityUlulu;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class UluluRenderer extends MobRenderer<EntityUlulu, ModelUlulu<EntityUlulu>> {

	public UluluRenderer(EntityRendererManager manager) {
		super(manager, new ModelUlulu<>(), 0.9F);
	}
	
	public UluluRenderer(EntityRendererManager p_i50961_1_, ModelUlulu<EntityUlulu> p_i50961_2_, float p_i50961_3_) {
		super(p_i50961_1_, p_i50961_2_, p_i50961_3_);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityUlulu entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/ululu/ululu_dry.png");
	}

}
