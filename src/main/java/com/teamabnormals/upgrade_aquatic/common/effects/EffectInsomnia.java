package com.teamabnormals.upgrade_aquatic.common.effects;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityFlare;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;

public class EffectInsomnia extends InstantEffect {
	
    public EffectInsomnia() {
        super(EffectType.HARMFUL, 0xa075b4);
    }
    
    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
    	if(entity instanceof ServerPlayerEntity) {
    		ServerPlayerEntity playerMP = (ServerPlayerEntity) entity;
    		StatisticsManager statisticsManager = playerMP.getStats();
    		statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), (24000 * (amplifier + 1)));
    	} else if(entity instanceof PhantomEntity) {
    		EntityFlare flare = UAEntities.FLARE.create(entity.world);
    		flare.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
    		entity.world.addEntity(flare);
    		entity.remove(true);
    	} else if(entity instanceof EntityFlare) {
    		entity.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
    	}
    }
    
}