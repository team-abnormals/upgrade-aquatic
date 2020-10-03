package com.minecraftabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowSquidInkParticle extends SimpleAnimatedParticle {
	private GlowSquidInkParticle(ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, IAnimatedSprite animatedSprite) {
		super(world, posX, posY, posZ, animatedSprite, 0.0F);
		this.particleScale = 0.5F;
		this.setAlphaF(1.0F);
		this.setColor(0.40F, 0.86F, 0.81F);
		this.maxAge = (int) ((double) (this.particleScale * 12.0F) / (Math.random() * (double) 0.8F + (double) 0.2F));
		this.selectSpriteWithAge(animatedSprite);
		this.canCollide = false;
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		this.setBaseAirFriction(0.0F);
	}

	@Override
	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			this.selectSpriteWithAge(this.field_217584_C);
			if (this.age > this.maxAge / 2) {
				this.setAlphaF(1.0F - ((float) this.age - (float) (this.maxAge / 2)) / (float) this.maxAge);
			}

			this.move(this.motionX, this.motionY, this.motionZ);
			if (this.world.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).isAir()) {
				this.motionY -= (double) 0.008F;
			}

			this.motionX *= (double) 0.92F;
			this.motionY *= (double) 0.92F;
			this.motionZ *= (double) 0.92F;
			if (this.onGround) {
				this.motionX *= (double) 0.7F;
				this.motionZ *= (double) 0.7F;
			}

		}
	}
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite p_i50599_1_) {
			this.spriteSet = p_i50599_1_;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GlowSquidInkParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}
}