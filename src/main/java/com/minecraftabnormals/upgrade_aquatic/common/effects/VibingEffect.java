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
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		for (LivingEntity livingEntities : entity.level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(0.3D))) {
			if (livingEntities.isAlive() && livingEntities != entity) {
				if (livingEntities instanceof PlayerEntity && ((PlayerEntity) livingEntities).isCreative()) {

				} else {
					livingEntities.addEffect(new EffectInstance(Effects.REGENERATION, 70, 1 * (amplifier + 1)));
				}
			}
		}
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}

}
