package com.teamabnormals.upgrade_aquatic.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class VibingMobEffect extends MobEffect {

	public VibingMobEffect() {
		super(MobEffectCategory.NEUTRAL, 0xffb5f4);
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		for (LivingEntity living : entity.level().getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(0.3D))) {
			if (living.isAlive() && living != entity && !living.hasEffect(MobEffects.REGENERATION)) {
				living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 70, amplifier + 1));
			}
		}
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
