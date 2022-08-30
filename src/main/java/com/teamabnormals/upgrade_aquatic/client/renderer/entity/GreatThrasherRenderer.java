package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.teamabnormals.upgrade_aquatic.client.renderer.entity.layers.ThrasherRenderLayer;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.GreatThrasher;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GreatThrasherRenderer extends MobRenderer<GreatThrasher, ThrasherModel<GreatThrasher>> {

	public GreatThrasherRenderer(EntityRendererProvider.Context context) {
		super(context, new ThrasherModel<>(context.bakeLayer(ThrasherModel.LOCATION)), 1.575F);
		this.addLayer(new ThrasherRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(GreatThrasher entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/thrasher/great_thrasher.png");
	}

	@Override
	protected void scale(GreatThrasher thrasher, PoseStack matrixStack, float partialTickTime) {
		matrixStack.scale(1.75F, 1.75F, 1.75F);
	}

}