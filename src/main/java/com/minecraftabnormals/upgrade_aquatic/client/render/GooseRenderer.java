package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.GooseModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.GooseEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GooseRenderer extends MobRenderer<GooseEntity, GooseModel<GooseEntity>> {

	public GooseRenderer(EntityRenderDispatcher manager) {
		super(manager, new GooseModel<>(), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(GooseEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/goose.png");
	}

	@Override
	protected float getBob(GooseEntity livingBase, float partialTicks) {
		float f = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
		float f1 = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
		return (Mth.sin(f) + 1.0F) * f1;
	}
}