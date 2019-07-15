package com.teamabnormals.upgrade_aquatic.common.effects;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.InstantEffect;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;

public class EffectInsomnia extends InstantEffect {
    public EffectInsomnia(String name) {
        super(EffectType.HARMFUL, 0xa075b4);
        this.setRegistryName(new ResourceLocation(Reference.MODID, name));
    }
    
    @Override
    public void performEffect(LivingEntity entityLivingBase, int amplifier) {
    	if (entityLivingBase instanceof ServerPlayerEntity) {
    		ServerPlayerEntity playerMP = (ServerPlayerEntity) entityLivingBase;
            StatisticsManager statisticsManager = playerMP.getStats();
            statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), -(24000 * (amplifier + 1)));
        }
    }
}
