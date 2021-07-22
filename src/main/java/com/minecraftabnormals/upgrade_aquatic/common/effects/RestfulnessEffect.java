package com.minecraftabnormals.upgrade_aquatic.common.effects;

import com.minecraftabnormals.upgrade_aquatic.common.entities.FlareEntity;
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

public class RestfulnessEffect extends InstantEffect {
	
    public RestfulnessEffect() {
        super(EffectType.BENEFICIAL, 0xb48675);
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
    	if (entity instanceof ServerPlayerEntity) {
    		ServerPlayerEntity playerMP = (ServerPlayerEntity) entity;
            StatisticsManager statisticsManager = playerMP.getStats();
            statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), -(24000 * (amplifier + 1)));
        } else if(entity instanceof PhantomEntity) {
        	entity.hurt(DamageSource.MAGIC, Float.MAX_VALUE);
        } else if(entity instanceof FlareEntity) {
        	PhantomEntity phantom = EntityType.PHANTOM.create(entity.level);
    		phantom.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.yRot, entity.xRot);
    		phantom.setNoAi(((MobEntity) entity).isNoAi());
    		if(entity.hasCustomName()) {
    			phantom.setCustomName(entity.getCustomName());
    			phantom.setCustomNameVisible(entity.isCustomNameVisible());
    		}
    		phantom.setHealth(entity.getHealth());
    		if(phantom.getHealth() > 0) {
    			entity.level.addFreshEntity(phantom);
    			entity.remove(true);
    		}
    		entity.remove(true);
        }
    }
    
}
