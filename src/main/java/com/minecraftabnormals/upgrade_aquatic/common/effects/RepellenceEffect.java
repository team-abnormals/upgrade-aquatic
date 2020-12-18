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
	public void performEffect(LivingEntity entity, int amplifier) {
		for (LivingEntity livingEntity : entity.world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(0.3D))) {
			if (livingEntity.isAlive() && livingEntity != entity) {
				if (!(livingEntity instanceof PlayerEntity && ((PlayerEntity) livingEntity).isCreative())) {
					livingEntity.addPotionEffect(new EffectInstance(Effects.POISON, 70, amplifier + 1));
					livingEntity.setRevengeTarget(entity);
				}
			}
		}
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

}
