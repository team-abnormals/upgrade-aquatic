package com.minecraftabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowSquidInkParticle extends SimpleAnimatedParticle {
	private GlowSquidInkParticle(ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, IAnimatedSprite animatedSprite) {
		super(world, posX, posY, posZ, animatedSprite, 0.0F);
		this.quadSize = 0.5F;
		this.setAlpha(1.0F);
		this.setColor(66F / 256F, 215F / 256F, 165F / 256F);
		this.lifetime = (int) ((double) (this.quadSize * 12.0F) / (Math.random() * (double) 0.8F + (double) 0.2F));
		this.setSpriteFromAge(animatedSprite);
		this.hasPhysics = false;
		this.xd = motionX;
		this.yd = motionY;
		this.zd = motionZ;
		this.setBaseAirFriction(0.0F);
	}

	@Override
	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.age++ >= this.lifetime) {
			this.remove();
		} else {
			this.setSpriteFromAge(this.sprites);
			if (this.age > this.lifetime / 2) {
				this.setAlpha(1.0F - ((float) this.age - (float) (this.lifetime / 2)) / (float) this.lifetime);
			}

			this.move(this.xd, this.yd, this.zd);
			if (this.level.getBlockState(new BlockPos(this.x, this.y, this.z)).isAir()) {
				this.yd -= (double) 0.008F;
			}

			this.xd *= (double) 0.92F;
			this.yd *= (double) 0.92F;
			this.zd *= (double) 0.92F;
			if (this.onGround) {
				this.xd *= (double) 0.7F;
				this.zd *= (double) 0.7F;
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

		public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GlowSquidInkParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}
}