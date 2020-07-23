package com.minecraftabnormals.upgrade_aquatic.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class VibingEffect extends Effect {

	public VibingEffect() {
		super(EffectType.NEUTRAL, 0xffb5f4);
	}
	
	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		for(LivingEntity livingEntities : entity.world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(0.3D))) {
			if (livingEntities.isAlive() && livingEntities != entity) {
				if(livingEntities instanceof PlayerEntity && ((PlayerEntity)livingEntities).isCreative()) {
					
				} else {
					livingEntities.addPotionEffect(new EffectInstance(Effects.REGENERATION, 70, 1 * (amplifier + 1)));
				}
			}
		}
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

}
