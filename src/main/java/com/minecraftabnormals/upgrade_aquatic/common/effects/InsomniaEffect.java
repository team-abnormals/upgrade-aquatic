package com.minecraftabnormals.upgrade_aquatic.common.effects;

import com.minecraftabnormals.upgrade_aquatic.common.advancement.UACriteriaTriggers;
import com.minecraftabnormals.upgrade_aquatic.common.entities.FlareEntity;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.stats.StatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;

public class InsomniaEffect extends InstantenousMobEffect {

	public InsomniaEffect() {
		super(MobEffectCategory.HARMFUL, 0xa075b4);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity instanceof ServerPlayer playerMP) {
			StatsCounter statisticsManager = playerMP.getStats();
			statisticsManager.increment(playerMP, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), (24000 * (amplifier + 1)));
		} else if (entity instanceof Phantom) {
			FlareEntity flare = UAEntities.FLARE.get().create(entity.level);
			flare.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
			flare.setNoAi(((Mob) entity).isNoAi());
			if (entity.hasCustomName()) {
				flare.setCustomName(entity.getCustomName());
				flare.setCustomNameVisible(entity.isCustomNameVisible());
			}
			flare.setHealth(entity.getHealth());
			if (flare.getHealth() > 0) {
				entity.level.addFreshEntity(flare);
				entity.discard();
			}
			Player player = entity.getCommandSenderWorld().getNearestPlayer(entity, 11);
			if (player instanceof ServerPlayer serverPlayer && player.isAlive()) {
				if (!entity.level.isClientSide()) {
					UACriteriaTriggers.CONVERT_PHANTOM.trigger(serverPlayer);
				}
			}
		} else if (entity instanceof FlareEntity) {
			entity.hurt(DamageSource.MAGIC, Float.MAX_VALUE);
		}
	}

}