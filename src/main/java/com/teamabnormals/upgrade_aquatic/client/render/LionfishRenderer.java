package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teamabnormals.upgrade_aquatic.client.model.ModelLionfish;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityLionfish;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionfishRenderer extends MobRenderer<EntityLionfish, ModelLionfish<EntityLionfish>> {

	public LionfishRenderer(EntityRendererManager manager) {
		super(manager, new ModelLionfish<>(), 0.45F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityLionfish entity) {
		return new ResourceLocation(Reference.MODID, "textures/entity/lionfish.png");
	}
	
	@Override
	protected void applyRotations(EntityLionfish entityLiving, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.0F * MathHelper.sin(0.6F * ageInTicks);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStack.translate(0.1F, 0.1F, -0.1F);
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

}