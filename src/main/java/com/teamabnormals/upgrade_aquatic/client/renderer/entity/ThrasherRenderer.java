package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.ThrasherRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrasherRenderer extends MobRenderer<Thrasher, ThrasherModel<Thrasher>> {

	public ThrasherRenderer(EntityRenderDispatcher renderer) {
		super(renderer, new ThrasherModel<>(), 0.9F);
		this.addLayer(new ThrasherRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Thrasher entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/thrasher.png");
	}

}