package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.DrownedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

@Mixin(DrownedRenderer.class)
public class DrownedRendererMixin extends AbstractZombieRenderer<DrownedEntity, DrownedModel<DrownedEntity>> 
{
	protected DrownedRendererMixin(EntityRendererManager p_i50974_1_, DrownedModel<DrownedEntity> p_i50974_2_, DrownedModel<DrownedEntity> p_i50974_3_, DrownedModel<DrownedEntity> p_i50974_4_) {
		super(p_i50974_1_, p_i50974_2_, p_i50974_3_, p_i50974_4_);
	}
	
	/**
	 * @reason when i tried to use inject instead of overwrite it made the entity walk backwards. hence why this is an overwrite
	 * @author tallest red
	 */
	@Overwrite
    protected void applyRotations(DrownedEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) 
    {
	 float f = entityLiving.getSwimAnimation(partialTicks);
	 if (entityLiving.isInWater()) {
		 super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		 float f3 = entityLiving.isInWater() ? -90.0F - entityLiving.rotationPitch : -90.0F;
		 float f4 = MathHelper.lerp(f, 0.0F, f3);
		 matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f4));
         if (entityLiving.isActualySwimming()) {
		   matrixStackIn.translate(0.0D, -1.0D, (double)0.3F);
		  }
		} else {
		  super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
       }
    }
}

