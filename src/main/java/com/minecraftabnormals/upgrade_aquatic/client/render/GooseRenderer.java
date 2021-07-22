package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.GooseModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.GooseEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GooseRenderer extends MobRenderer<GooseEntity, GooseModel<GooseEntity>> {

	public GooseRenderer(EntityRendererManager manager) {
		super(manager, new GooseModel<>(), 0.25F);
	}
	
	@Override
	public ResourceLocation getTextureLocation(GooseEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/goose.png");
	}
	
	@Override
	protected float getBob(GooseEntity livingBase, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
		float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}