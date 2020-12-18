package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.minecraftabnormals.upgrade_aquatic.client.model.LionfishModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.LionfishEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionfishRenderer extends MobRenderer<LionfishEntity, LionfishModel<LionfishEntity>> {

	public LionfishRenderer(EntityRendererManager manager) {
		super(manager, new LionfishModel<>(), 0.45F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(LionfishEntity entity) {
		return new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/lionfish.png");
	}
	
	@Override
	protected void applyRotations(LionfishEntity entityLiving, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.0F * MathHelper.sin(0.6F * ageInTicks);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStack.translate(0.1F, 0.1F, -0.1F);
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

}