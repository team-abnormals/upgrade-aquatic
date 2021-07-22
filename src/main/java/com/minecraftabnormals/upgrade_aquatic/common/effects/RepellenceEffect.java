package com.minecraftabnormals.upgrade_aquatic.common.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class RepellenceEffect extends Effect {

	public RepellenceEffect() {
		super(EffectType.NEUTRAL, 11663081);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		for (LivingEntity livingEntity : entity.level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(0.3D))) {
			if (livingEntity.isAlive() && livingEntity != entity) {
				if (!(livingEntity instanceof PlayerEntity && ((PlayerEntity) livingEntity).isCreative())) {
					livingEntity.addEffect(new EffectInstance(Effects.POISON, 70, amplifier + 1));
					livingEntity.setLastHurtByMob(entity);
				}
			}
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

}
