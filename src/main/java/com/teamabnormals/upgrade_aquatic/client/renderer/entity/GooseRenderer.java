package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.teamabnormals.upgrade_aquatic.client.model.GooseModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Goose;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GooseRenderer extends MobRenderer<Goose, GooseModel<Goose>> {

	public GooseRenderer(EntityRenderDispatcher manager) {
		super(manager, new GooseModel<>(), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(Goose entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/goose.png");
	}

	@Override
	protected float getBob(Goose livingBase, float partialTicks) {
		float f = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
		float f1 = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}
}