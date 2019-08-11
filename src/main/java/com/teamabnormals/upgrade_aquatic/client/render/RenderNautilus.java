package com.teamabnormals.upgrade_aquatic.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.client.model.ModelNautilus;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityNautilus;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderNautilus extends MobRenderer<EntityNautilus, ModelNautilus<EntityNautilus>> {
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] { 
		new ResourceLocation(Reference.MODID, "textures/entity/nautilus/nautilus.png"),
		new ResourceLocation(Reference.MODID, "textures/entity/nautilus/nautilus_smelly.png"),
		new ResourceLocation(Reference.MODID, "textures/entity/nautilus/nautilus_mca.png"),
		new ResourceLocation(Reference.MODID, "textures/entity/nautilus/nautilus_five.png"),
		new ResourceLocation(Reference.MODID, "textures/entity/nautilus/nautilus_cell.png"),
		new ResourceLocation(Reference.MODID, "textures/entity/nautilus/nautilus_tb.png"),
	};

	public RenderNautilus(EntityRendererManager renderManager) {
        super(renderManager, new ModelNautilus<>(), 0.5F);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityNautilus entity) {
		String name = entity.getName().getString().toLowerCase().trim();
		if (name.equals("smelly") || name.equals("thefaceofgaming")) {
			return TEXTURES[1];
		} else if (name.equals("abnormal") || name.equals("cameron")) {
			return TEXTURES[2];
		} else if(name.equals("five") || name.equals("epic")) {
			return TEXTURES[3];
		} else if(name.equals("neonmembrane") || name.equals("cellmembrane")) {
			return TEXTURES[4];
		} else if(name.equals("tb")) {
			return TEXTURES[5];
		}
		return TEXTURES[0];
	}
	
	@Override
	protected void applyRotations(EntityNautilus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
		float f = 0.3F * MathHelper.sin(0.6F * ageInTicks);
		GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
		if (!entityLiving.isInWater() && !entityLiving.areEyesInFluid(FluidTags.WATER)) {
			GlStateManager.translatef(0.2F, 0.14F, 0.0F);
			GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
		}
	}
}
