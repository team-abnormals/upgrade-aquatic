package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowSquidInkParticle extends SimpleAnimatedParticle {

	private GlowSquidInkParticle(ClientLevel world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, SpriteSet animatedSprite) {
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
				this.yd -= 0.008F;
			}

			this.xd *= 0.92F;
			this.yd *= 0.92F;
			this.zd *= 0.92F;
			if (this.onGround) {
				this.xd *= 0.7F;
				this.zd *= 0.7F;
			}

		}
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public Factory(SpriteSet p_i50599_1_) {
			this.spriteSet = p_i50599_1_;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GlowSquidInkParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}
}