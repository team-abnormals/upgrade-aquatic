package com.teamabnormals.upgrade_aquatic.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.particle.*;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PrismarineShowerParticle extends TextureSheetParticle {
	protected final SpriteSet animatedSprite;
	private final float rotSpeed;
	private final float angle;
	private final float scale;
	private final int MAX_FRAME_ID = 5;
	protected int currentFrame = 0;
	private boolean directionRight = true;
	private int lastTick = 0;

	public PrismarineShowerParticle(SpriteSet animatedSprite, ClientLevel world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(world, posX, posY, posZ, motionX, motionY, motionZ);
		this.xd = this.xd * 0.009999999776482582d + motionX;
		this.yd = this.yd * 0.009999999776482582d + motionY;
		this.zd = this.zd * 0.009999999776482582d + motionZ;
		this.scale = this.quadSize = 0.5f;
		this.rotSpeed = ((float) Math.random() - 0.5F) * 0.1F;
		this.roll = (float) Math.random() * ((float) Math.PI * 2F);
		this.angle = (float) Math.random() * ((float) Math.PI * 2F);
		this.gravity = 0.035F;
		this.rCol = 1f;
		this.gCol = 1f;
		this.bCol = 1f;
		this.lifetime = (int) (15d / (Math.random() * 0.8d + 0.2d)) + 4;
		this.animatedSprite = animatedSprite;
		this.setSpriteFromAge(animatedSprite);
	}

	@Override
	public void render(VertexConsumer p_225606_1_, Camera activeInfo, float partialTicks) {
		Entity entity = activeInfo.getEntity();
		if (entity.tickCount >= this.lastTick + 5) {
			if (this.currentFrame == MAX_FRAME_ID) {
				this.directionRight = false;
			} else if (currentFrame == 0) {
				this.directionRight = true;
			}
			this.currentFrame = this.currentFrame + (directionRight ? 1 : -1);
			this.lastTick = entity.tickCount;
		}
		float f = ((float) this.age + partialTicks) / (float) this.lifetime;
		this.quadSize = this.scale * (1f - f * f * 0.5f);
		super.render(p_225606_1_, activeInfo, partialTicks);
	}

	@Override
	public void tick() {
		super.tick();
		this.oRoll = this.roll;
		this.roll += (float) Math.PI * this.rotSpeed * 2.0F;

		if (this.onGround) {
			this.oRoll = this.roll = 0.0F;
		}

		this.xd += Math.cos(angle) * 0.0025;
		this.yd *= 1.06D;
		this.zd += Math.sin(angle) * 0.0025;

		if (this.isAlive()) {
			this.setSpriteFromAge(this.animatedSprite);
		}
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public int getLightColor(float partialTick) {
		float f = ((float) this.age + partialTick) / (float) this.lifetime;
		f = Mth.clamp(f, 0f, 1f);
		int i = super.getLightColor(partialTick);
		int j = i & 255;
		int k = i >> 16 & 255;
		j = j + (int) (f * 15f * 16f);
		if (j > 240) {
			j = 240;
		}
		return j | k << 16;
	}

	public static class Factory implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet animatedSprite;

		public Factory(SpriteSet animatedSprite) {
			this.animatedSprite = animatedSprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new PrismarineShowerParticle(this.animatedSprite, world, x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}
}
