package com.teamabnormals.upgrade_aquatic.common.effects;

import com.teamabnormals.upgrade_aquatic.common.entities.EntityFlare;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
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
    		EntityFlare flare = UAEntities.FLARE.get().create(entity.world);
    		flare.setLocationAndAngles(entity.getPosX(), entity.getPosY(), entity.getPosZ(), entity.rotationYaw, entity.rotationPitch);
    		flare.setNoAI(((MobEntity) entity).isAIDisabled());
    		if(entity.hasCustomName()) {
    			flare.setCustomName(entity.getCustomName());
    			flare.setCustomNameVisible(entity.isCustomNameVisible());
    		}
    		flare.setHealth(entity.getHealth());
    		if(flare.getHealth() > 0) {
    			entity.world.addEntity(flare);
    			entity.remove(true);
    		}
    	} else if(entity instanceof EntityFlare) {
    		entity.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
    	}
    }
    
}