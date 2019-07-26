package com.teamabnormals.upgrade_aquatic.client.render.overlay;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.client.model.ModelPike;
import com.teamabnormals.upgrade_aquatic.common.entities.EntityPike;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderLayerSpectralPike<T extends EntityPike, M extends ModelPike<T>> extends LayerRenderer<T, M> {
	private static final ResourceLocation RES_PIKE_EYES = new ResourceLocation(Reference.MODID, "textures/entity/pike/pike_7_glow.png");

	public RenderLayerSpectralPike(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(T entity, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
		if(entity.getPikeType() != 7) return;
		this.bindTexture(RES_PIKE_EYES);
		GlStateManager.depthMask(true);
        GlStateManager.enableBlend();

        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240.0F, 240.0F);
        GlStateManager.color4f(1F, 1F, 1F, 1F);
        GlStateManager.disableLighting();

        this.getEntityModel().render(entity, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);

        GlStateManager.enableLighting();
        int i = entity.getBrightnessForRender();
        int j = i % 65536;
        int k = i / 65536;
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float)j, (float)k);
        func_215334_a(entity);

        GlStateManager.disableBlend();
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}