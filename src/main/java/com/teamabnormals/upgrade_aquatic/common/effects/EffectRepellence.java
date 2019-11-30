package com.teamabnormals.upgrade_aquatic.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class EffectRepellence extends Effect {

	public EffectRepellence() {
		super(EffectType.NEUTRAL, 11663081);
	}
	
	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		for(LivingEntity livingEntities : entity.world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(0.3D))) {
			if (livingEntities.isAlive() && livingEntities != entity) {
				if(livingEntities instanceof PlayerEntity && ((PlayerEntity)livingEntities).isCreative()) {
					
				} else {
					livingEntities.addPotionEffect(new EffectInstance(Effects.POISON, 70, 1 * (amplifier + 1)));
					livingEntities.setRevengeTarget(entity);
				}
			}
		}
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

}
