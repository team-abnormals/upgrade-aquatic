package com.minecraftabnormals.upgrade_aquatic.common.effects;

import com.minecraftabnormals.upgrade_aquatic.common.entities.EntityFlare;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
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
    public void performEffect(LivingEntity entity, int amplifier) {
    	if (entity instanceof ServerPlayerEntity) {
    		ServerPlayerEntity playerMP = (ServerPlayerEntity) entity;
            StatisticsManager statisticsManager = playerMP.getStats();
            statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), -(24000 * (amplifier + 1)));
        } else if(entity instanceof PhantomEntity) {
        	entity.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
        } else if(entity instanceof EntityFlare) {
        	PhantomEntity phantom = EntityType.PHANTOM.create(entity.world);
    		phantom.setLocationAndAngles(entity.getPosX(), entity.getPosY(), entity.getPosZ(), entity.rotationYaw, entity.rotationPitch);
    		phantom.setNoAI(((MobEntity) entity).isAIDisabled());
    		if(entity.hasCustomName()) {
    			phantom.setCustomName(entity.getCustomName());
    			phantom.setCustomNameVisible(entity.isCustomNameVisible());
    		}
    		phantom.setHealth(entity.getHealth());
    		if(phantom.getHealth() > 0) {
    			entity.world.addEntity(phantom);
    			entity.remove(true);
    		}
    		entity.remove(true);
        }
    }
    
}
