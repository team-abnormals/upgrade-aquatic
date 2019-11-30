package com.teamabnormals.upgrade_aquatic.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;

public class EffectRestfulness extends InstantEffect {
	
    public EffectRestfulness() {
        super(EffectType.BENEFICIAL, 0xb48675);
    }
    
    @Override
    public void performEffect(LivingEntity entityLivingBase, int amplifier) {
    	if (entityLivingBase instanceof ServerPlayerEntity) {
    		ServerPlayerEntity playerMP = (ServerPlayerEntity) entityLivingBase;
            StatisticsManager statisticsManager = playerMP.getStats();
            statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), -(24000 * (amplifier + 1)));
        } else if(entityLivingBase instanceof PhantomEntity) {
        	entityLivingBase.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
        }
    }
    
}
