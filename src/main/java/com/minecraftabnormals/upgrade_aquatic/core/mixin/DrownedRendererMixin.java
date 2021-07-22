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
	
	/**
	 * @author Upgrade Aquatic
	 * @reason Replace the Drowned swimming animation
	 */
	@Overwrite
	protected void setupRotations(DrownedEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		if (entityLiving.isVisuallySwimming()) {
			float swimAnimationTicks = entityLiving.getSwimAmount(partialTicks);
			if (entityLiving.isInWater()) {
				super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
				float rotationPitchChange = entityLiving.isInWater() ? -90.0F - entityLiving.xRot : -90.0F;
				float rotationModifier = MathHelper.lerp(swimAnimationTicks, 0.0F, rotationPitchChange);
				matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(rotationModifier));
				matrixStackIn.translate(0.0D, -1.0D, 0.3F);
			} else {
				super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
			}
		} else {
			super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
			float f = entityLiving.getSwimAmount(partialTicks);
			if (f > 0.0F) {
				matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(MathHelper.lerp(f, entityLiving.xRot, -10.0F - entityLiving.xRot)));
			}
		}
	}
}