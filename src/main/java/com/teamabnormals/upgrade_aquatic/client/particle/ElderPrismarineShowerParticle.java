package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElderPrismarineShowerParticle extends PrismarineShowerParticle {

	public ElderPrismarineShowerParticle(SpriteSet animatedSprite, ClientLevel world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(animatedSprite, world, posX, posY, posZ, motionX, motionY, motionZ);
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet animatedSprite;

		public Factory(SpriteSet animatedSprite) {
			this.animatedSprite = animatedSprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ElderPrismarineShowerParticle(this.animatedSprite, world, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}

}
