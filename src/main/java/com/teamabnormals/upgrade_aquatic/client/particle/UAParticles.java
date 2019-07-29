package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum UAParticles {
	PRISMARINE_SHOWER, ELDER_PRISMARINE_SHOWER, JELLY_TORCH, SPECTRAL_CONSUME;
	
	UAParticles() {}

    @OnlyIn(Dist.CLIENT)
    public Particle create(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, int... params) {
        return getFactory().makeParticle(world, x, y, z, velocityX, velocityY, velocityZ, params);
    }
    
    @OnlyIn(Dist.CLIENT)
    public IParticle getFactory() {
    	switch (this) {
    		case PRISMARINE_SHOWER:
    			return new ParticlePrismarineShower.Factory();
    		case ELDER_PRISMARINE_SHOWER:
    			return new ParticleElderPrismarineShower.Factory();
    		case JELLY_TORCH:
    			return new ParticleJellyTorch.Factory();
    		case SPECTRAL_CONSUME:
    			return new ParticleSpectralConsume.Factory();
    	}
		return this.getDefaultParticle().getFactory();
    }

    public UAParticles getDefaultParticle() {
        return PRISMARINE_SHOWER;
    }

    public void spawn(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, int... parameters) {
        if (world.isRemote) {
            spawn(create(world, x, y, z, velocityX, velocityY, velocityZ, parameters));
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawn(Particle particle) {
        Minecraft.getInstance().particles.addEffect(particle);
    }
}
