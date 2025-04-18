package com.teamabnormals.upgrade_aquatic.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class RepellenceMobEffect extends MobEffect {

	public RepellenceMobEffect() {
		super(MobEffectCategory.NEUTRAL, 11663081);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		for (LivingEntity living : entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(0.3D))) {
			if (living.isAlive() && living != entity && !living.hasEffect(MobEffects.POISON)) {
				living.addEffect(new MobEffectInstance(MobEffects.POISON, 70, amplifier + 1));
				living.setLastHurtByMob(entity);
			}
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
