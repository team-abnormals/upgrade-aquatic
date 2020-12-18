package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DrownedRenderer.class)
public class DrownedRendererMixin extends AbstractZombieRenderer<DrownedEntity, DrownedModel<DrownedEntity>> {
	private DrownedRendererMixin(EntityRendererManager manager, DrownedModel<DrownedEntity> model, DrownedModel<DrownedEntity> model2, DrownedModel<DrownedEntity> model3) {
		super(manager, model, model2, model3);
	}
	
	@Overwrite
	protected void applyRotations(DrownedEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		float swimAnimationTicks = entityLiving.getSwimAnimation(partialTicks);
		if (entityLiving.isInWater()) {
			super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
			float rotationPitchChange = entityLiving.isInWater() ? -90.0F - entityLiving.rotationPitch : -90.0F;
			float rotationModifier = MathHelper.lerp(swimAnimationTicks, 0.0F, rotationPitchChange);
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(rotationModifier));
			if (entityLiving.isActualySwimming()) {
				matrixStackIn.translate(0.0D, -1.0D, (double)0.3F);
			}
		} else {
			super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		}
	}
}