package com.minecraftabnormals.upgrade_aquatic.client.render;

import com.google.common.collect.Maps;
import com.minecraftabnormals.upgrade_aquatic.client.model.NautilusModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.NautilusEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class NautilusRenderer extends MobRenderer<NautilusEntity, NautilusModel<NautilusEntity>> {
	private static final Map<List<String>, String> SKINS = Util.make(Maps.newHashMap(), (skins) -> {
		skins.put(Arrays.asList("smelly", "thefaceofgaming"), "smelly");
		skins.put(Arrays.asList("abnormal", "abnautilus", "abnortilus", "mca"), "mca");
		skins.put(Arrays.asList("five", "epic"), "five");
		skins.put(Arrays.asList("neon membrane", "cell membrane", "cell"), "cell");
		skins.put(Arrays.asList("tb"), "tb");
		skins.put(Arrays.asList("bagel", "shy guy", "legobagel"), "bagel");
		skins.put(Arrays.asList("sadcat"), "sadcat");
		skins.put(Arrays.asList("cameron", "cam", "cringe"), "cameron");
		skins.put(Arrays.asList("snake", "snautilus", "snakeblock", "snake block"), "snake_block");
		skins.put(Arrays.asList("snail", "snail nautilus"), "snail");
	});

	public NautilusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new NautilusModel<>(), 0.25F);
	}

	@Override
	public ResourceLocation getEntityTexture(NautilusEntity nautilus) {
		String textureSuffix = "";
		
		if(nautilus.hasCustomName()) {
			String name = nautilus.getName().getString().toLowerCase().trim();
			for(Map.Entry<List<String>, String> entries : SKINS.entrySet()) {
				if(entries.getKey().contains(name)) {
					textureSuffix = "_" + entries.getValue();
				}
			}
		}
		return new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/entity/nautilus/nautilus" + textureSuffix + ".png");
	}
	
	@Override
	protected void applyRotations(NautilusEntity nautilus, MatrixStack matrixStack, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(nautilus, matrixStack, ageInTicks, rotationYaw, partialTicks);
		float f = 0.3F * MathHelper.sin(0.6F * ageInTicks);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(f));
		if(!nautilus.isInWater() && !nautilus.areEyesInFluid(FluidTags.WATER)) {
			matrixStack.translate(0.2F, 0.14F, 0.0F);
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0F));
		}
	}
}
