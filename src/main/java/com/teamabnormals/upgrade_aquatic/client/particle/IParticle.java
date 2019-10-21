package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public interface IParticle extends IParticleFactory<IParticleData> {
	
	Particle makeParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... params);

	@Nullable
	@Override
	default Particle makeParticle(IParticleData type, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		return null;
	}

}
