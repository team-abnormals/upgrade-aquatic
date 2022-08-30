package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.teamabnormals.upgrade_aquatic.client.model.LionfishModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Lionfish;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionfishRenderer extends MobRenderer<Lionfish, LionfishModel<Lionfish>> {

	public LionfishRenderer(EntityRendererProvider.Context context) {
		super(context, new LionfishModel<>(context.bakeLayer(LionfishModel.LOCATION)), 0.45F);
	}

	@Override
	public ResourceLocation getTextureLocation(Lionfish entity) {
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/lionfish.png");
	}

	@Override
	protected void setupRotations(Lionfish entityLiving, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 4.0F * Mth.sin(0.6F * ageInTicks);
		matrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
		if (!entityLiving.isInWater()) {
			matrixStack.translate(0.1F, 0.1F, -0.1F);
			matrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}

}