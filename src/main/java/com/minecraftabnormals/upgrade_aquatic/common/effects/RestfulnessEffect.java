package com.minecraftabnormals.upgrade_aquatic.common.effects;

import com.minecraftabnormals.upgrade_aquatic.common.entities.FlareEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Phantom;

public class RestfulnessEffect extends InstantenousMobEffect {

	public RestfulnessEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xb48675);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof ServerPlayer playerMP) {
			StatsCounter statisticsManager = playerMP.getStats();
			statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), -(24000 * (amplifier + 1)));
		} else if (entity instanceof Phantom) {
			entity.hurt(DamageSource.MAGIC, Float.MAX_VALUE);
		} else if (entity instanceof FlareEntity) {
			Phantom phantom = EntityType.PHANTOM.create(entity.level);
			phantom.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
			phantom.setNoAi(((Mob) entity).isNoAi());
			if (entity.hasCustomName()) {
				phantom.setCustomName(entity.getCustomName());
				phantom.setCustomNameVisible(entity.isCustomNameVisible());
			}
			phantom.setHealth(entity.getHealth());
			if (phantom.getHealth() > 0) {
				entity.level.addFreshEntity(phantom);
				entity.discard();
			}
			entity.discard();
		}
	}

}
