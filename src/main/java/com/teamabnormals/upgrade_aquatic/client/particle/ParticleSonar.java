package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ParticleSonar extends UAParticle {
	private final int MAX_FRAME = 8;
    protected int currentFrame = 0;

	public ParticleSonar(World world, double x, double y, double z) {
		super(world, x, y, z, 0, 0, 0);
		this.particleScale = 1F;
		this.maxAge = 81;
		this.particleRed = 1f;
    	this.particleGreen = 1f;
    	this.particleBlue = 1f;
	}
	
	@Override
	protected void onPreRender(BufferBuilder buffer, ActiveRenderInfo activeInfo, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		super.onPreRender(buffer, activeInfo, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
		this.currentFrame = 0;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.world.getGameTime() % 10 == 0) {
			if(this.currentFrame < MAX_FRAME) {
				this.currentFrame++;
			}
		}
		this.particleScale = (this.age / 20) * 4; 
	}
	
	@Override
    public int getBrightnessForRender(float partialTick) {
		float f = ((float) this.age + partialTick) / (float) this.maxAge;
		f = MathHelper.clamp(f, 0f, 1f);
		int i = super.getBrightnessForRender(partialTick);
		int j = i & 255;
		int k = i >> 16 & 255;
		j = j + (int) (f * 15f * 16f);
		if (j > 240) {
			j = 240;
		}
		return j | k << 16;
	}

	@Override
	ResourceLocation getTexture() {
		return UAParticleSprites.SONAR_FRAMES[currentFrame];
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticle {
    	
		@Override
		public Particle makeParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... params) {
			return new ParticleSonar(world, x, y, z);
		}
        
	}

}
