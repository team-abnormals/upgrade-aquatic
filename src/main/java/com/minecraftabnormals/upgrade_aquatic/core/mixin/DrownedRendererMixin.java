package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Drowned;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DrownedRenderer.class)
public class DrownedRendererMixin extends AbstractZombieRenderer<Drowned, DrownedModel<Drowned>> {

	protected DrownedRendererMixin(Context p_173910_, DrownedModel<Drowned> p_173911_, DrownedModel<Drowned> p_173912_, DrownedModel<Drowned> p_173913_) {
		super(p_173910_, p_173911_, p_173912_, p_173913_);
	}

	/**
	 * @author Upgrade Aquatic
	 * @reason Replace the Drowned swimming animation
	 */
	@Overwrite
	protected void setupRotations(Drowned entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		if (entityLiving.isVisuallySwimming()) {
			float swimAnimationTicks = entityLiving.getSwimAmount(partialTicks);
			if (entityLiving.isInWater()) {
				super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
				float rotationPitchChange = entityLiving.isInWater() ? -90.0F - entityLiving.getXRot() : -90.0F;
				float rotationModifier = Mth.lerp(swimAnimationTicks, 0.0F, rotationPitchChange);
				matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(rotationModifier));
				matrixStackIn.translate(0.0D, -1.0D, 0.3F);
			} else {
				super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
			}
		} else {
			super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
			float f = entityLiving.getSwimAmount(partialTicks);
			if (f > 0.0F) {
				matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(f, entityLiving.getXRot(), -10.0F - entityLiving.getXRot())));
			}
		}
	}
}