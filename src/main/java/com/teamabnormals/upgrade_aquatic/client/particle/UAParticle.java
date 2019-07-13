package com.teamabnormals.upgrade_aquatic.client.particle;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.TexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class UAParticle extends TexturedParticle implements IParticleRenderType {
	
    protected UAParticle(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    protected UAParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
    }

    abstract ResourceLocation getTexture();

    @Override
    public void renderParticle(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        TextureManager textureManager = Minecraft.getInstance().textureManager;
        beginRender(buffer, textureManager);
        onPreRender(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
        finishRender(Tessellator.getInstance());
    }

    protected void onPreRender(BufferBuilder buffer, ActiveRenderInfo activeInfo, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {}

    @Override
    protected float getMinU() {
        return 0f;
    }

    @Override
    protected float getMaxU() {
        return 1f;
    }

    @Override
    protected float getMinV() {
        return 0f;
    }

    @Override
    protected float getMaxV() {
        return 1f;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.CUSTOM;
    }

    @Override
    public void beginRender(BufferBuilder buffer, TextureManager textureManager) {
        RenderHelper.disableStandardItemLighting();
        GlStateManager.depthMask(true);
        textureManager.bindTexture(getTexture());
        GlStateManager.disableBlend();
        buffer.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
    }

    @Override
    public void finishRender(Tessellator tess) {
        tess.draw();
    }
	
}
