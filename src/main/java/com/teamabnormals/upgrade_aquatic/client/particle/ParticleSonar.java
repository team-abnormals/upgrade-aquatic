package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ParticleSonar extends SpriteTexturedParticle {
	protected final IAnimatedSprite animatedSprite;
	protected final Vec3d firedDirection;
	
	public ParticleSonar(IAnimatedSprite animatedSprite, World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(world, posX, posY, posZ, motionX, motionY, motionZ);
		this.motionX = motionX;
		this.motionY = motionX;
		this.motionZ = motionX;
		this.particleScale = (rand.nextFloat() / 2) + 1;
		this.firedDirection = new Vec3d(motionX, motionY, motionZ).normalize();
		this.maxAge = 81;
		this.particleRed = 1f;
		this.particleGreen = 1f;
		this.particleBlue = 1f;
    	this.animatedSprite = animatedSprite;
    	this.selectSpriteWithAge(animatedSprite);
	}
	
	@Override
	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if(this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			this.move(this.motionX, this.motionY, this.motionZ);
		}
		this.selectSpriteWithAge(this.animatedSprite);
		this.particleScale += (this.age / 60);
		this.particleAlpha -= (this.age / 60);
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	@Override
	public int getBrightnessForRender(float partialTick) {
		float f = ((float) this.age + partialTick) / (float) this.maxAge;
		f = MathHelper.clamp(f, 0f, 1f);
		int i = super.getBrightnessForRender(partialTick);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (f * 15f * 16f);
		if(j > 240) {
			j = 240;
		}
		return j | k << 16;
	}
	
	@Override
	public void renderParticle(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float scale = this.getScale(partialTicks);
		
		float minU = this.getMinU();
		float maxU = this.getMaxU();
		float minV = this.getMinV();
		float maxV = this.getMaxV();
		
		float f5 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosX, this.posX) - interpPosX);
		float f6 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosY, this.posY) - interpPosY);
		float f7 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosZ, this.posZ) - interpPosZ);
		
		int i = this.getBrightnessForRender(partialTicks);
		int j = i >> 16 & '\uffff';
		int k = i & '\uffff';
		
		Vec3d direction = this.firedDirection;
		Vec3d up = new Vec3d(0, 1, 0);
		Vec3d perpediuclarDirection = up.crossProduct(direction);
		
		double yOffset = 0.125D;
		
		Vec3d[] vertices = new Vec3d[] {up.add(perpediuclarDirection.scale(-1)).add(up.scale(yOffset)).scale(scale), up.scale(-1).add(perpediuclarDirection.scale(-1)).add(up.scale(yOffset)).scale(scale), up.scale(-1).add(perpediuclarDirection).add(up.scale(yOffset)).scale(scale), up.add(perpediuclarDirection).add(up.scale(yOffset)).scale(scale)};
	
		if(this.particleAngle != 0.0F) {
			float f8 = MathHelper.lerp(partialTicks, this.prevParticleAngle, this.particleAngle);
			float f9 = MathHelper.cos(f8 * 0.5F);
			float f10 = (float)((double)MathHelper.sin(f8 * 0.5F) * entityIn.getLookDirection().x);
			float f11 = (float)((double)MathHelper.sin(f8 * 0.5F) * entityIn.getLookDirection().y);
			float f12 = (float)((double)MathHelper.sin(f8 * 0.5F) * entityIn.getLookDirection().z);
			Vec3d vec3d = new Vec3d((double)f10, (double)f11, (double)f12);

			for(int l = 0; l < 4; ++l) {
				vertices[l] = vec3d.scale(2.0D * vertices[l].dotProduct(vec3d)).add(vertices[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(vertices[l]).scale((double)(2.0F * f9)));
			}
		}
		
		buffer.pos((double)f5 + vertices[0].x, (double)f6 + vertices[0].y, (double)f7 + vertices[0].z).tex((double)maxU, (double)maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + vertices[1].x, (double)f6 + vertices[1].y, (double)f7 + vertices[1].z).tex((double)maxU, (double)minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + vertices[2].x, (double)f6 + vertices[2].y, (double)f7 + vertices[2].z).tex((double)minU, (double)minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + vertices[3].x, (double)f6 + vertices[3].y, (double)f7 + vertices[3].z).tex((double)minU, (double)maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();

		buffer.pos((double)f5 + vertices[1].x, (double)f6 + vertices[1].y, (double)f7 + vertices[1].z).tex((double)maxU, (double)minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + vertices[0].x, (double)f6 + vertices[0].y, (double)f7 + vertices[0].z).tex((double)maxU, (double)maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + vertices[3].x, (double)f6 + vertices[3].y, (double)f7 + vertices[3].z).tex((double)minU, (double)maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
		buffer.pos((double)f5 + vertices[2].x, (double)f6 + vertices[2].y, (double)f7 + vertices[2].z).tex((double)minU, (double)minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	}
	
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private IAnimatedSprite animatedSprite;
		
		public Factory(IAnimatedSprite animatedSprite) {
			this.animatedSprite = animatedSprite;
		}
    	
		@Override
		public Particle makeParticle(BasicParticleType type, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ParticleSonar(this.animatedSprite, world, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
