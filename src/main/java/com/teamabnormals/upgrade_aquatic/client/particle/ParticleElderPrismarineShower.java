package com.teamabnormals.upgrade_aquatic.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticleElderPrismarineShower extends ParticlePrismarineShower {

	public ParticleElderPrismarineShower(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(world, posX, posY, posZ, motionX, motionY, motionZ);
	}
	
	@Override
    ResourceLocation getTexture() {
        return UAParticleSprites.ELDER_PRISMARINE_FRAMES[currentFrame];
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticle {
    	
        @Override
        public Particle makeParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... params) {
            return new ParticleElderPrismarineShower(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
        
    }

}
