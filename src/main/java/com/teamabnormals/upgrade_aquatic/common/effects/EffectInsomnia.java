package com.teamabnormals.upgrade_aquatic.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;

public class EffectInsomnia extends InstantEffect {
	
    public EffectInsomnia() {
        super(EffectType.HARMFUL, 0xa075b4);
    }
    
    @Override
    public void performEffect(LivingEntity entityLivingBase, int amplifier) {
    	if(entityLivingBase instanceof ServerPlayerEntity) {
    		ServerPlayerEntity playerMP = (ServerPlayerEntity) entityLivingBase;
    		StatisticsManager statisticsManager = playerMP.getStats();
    		statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), (24000 * (amplifier + 1)));
    	}
    }
    
}