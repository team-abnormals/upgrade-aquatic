package com.teamabnormals.upgrade_aquatic.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabnormals.blueprint.client.EntitySkinHelper;
import com.teamabnormals.upgrade_aquatic.client.model.NautilusModel;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.Nautilus;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NautilusRenderer extends MobRenderer<Nautilus, NautilusModel<Nautilus>> {
	private static final EntitySkinHelper<Nautilus> SKIN_HELPER = EntitySkinHelper.create(UpgradeAquatic.MOD_ID, "textures/entity/nautilus", "nautilus", helper -> {
		helper.putSkins("smelly", "smelly", "thefaceofgaming");
		helper.putSkins("mca", "abnormal", "abnautilus", "abnortilus", "mca");
		helper.putSkins("five", "five", "epic");
		helper.putSkins("cell", "neon membrane", "cell membrane", "cell");
		helper.putSkins("tb", "tb");
		helper.putSkins("bagel", "bagel", "shy guy", "legobagel");
		helper.putSkins("sadcat", "sadcat");
		helper.putSkins("cameron", "cameron", "cam", "cringe");
		helper.putSkins("snake_block", "snake", "snautilus", "snakeblock", "snake block");
		helper.putSkins("snail", "snail", "snail nautilus");
	});

	public NautilusRenderer(EntityRendererProvider.Context context) {
		super(context, new NautilusModel<>(context.bakeLayer(NautilusModel.LOCATION)), 0.25F);
	}

	@Override
	public ResourceLocation getTextureLocation(Nautilus nautilus) {
		return SKIN_HELPER.getSkinForEntityOrElse(nautilus, new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/nautilus/nautilus.png"));
	}

	@Override
	protected void setupRotations(Nautilus nautilus, PoseStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.setupRotations(nautilus, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 0.3F * Mth.sin(0.6F * ageInTicks);
		matrixStack.mulPose(Axis.YP.rotationDegrees(f));
		if (!nautilus.isInWater() && !nautilus.isEyeInFluid(FluidTags.WATER)) {
			matrixStack.translate(0.2F, 0.14F, 0.0F);
			matrixStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
		}
	}
}
