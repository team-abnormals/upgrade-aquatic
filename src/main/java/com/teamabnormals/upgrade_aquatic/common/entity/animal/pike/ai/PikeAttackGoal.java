package com.teamabnormals.upgrade_aquatic.common.entity.animal.pike.ai;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.pike.PikeEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;

public final class PikeAttackGoal extends MeleeAttackGoal {

	public PikeAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

	@Override
	public boolean canUse() {
		PikeEntity pike = (PikeEntity) this.mob;
		return pike.getAttackCooldown() <= 0 && pike.getTarget() != null && !pike.getItemBySlot(EquipmentSlot.MAINHAND).is(ItemTags.FISHES) && pike.isInWater() && pike.getCaughtEntity() == null && !(pike.getTarget() instanceof Pufferfish) && super.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		return !this.mob.getItemBySlot(EquipmentSlot.MAINHAND).is(ItemTags.FISHES) && this.mob.isInWater() && super.canContinueToUse();
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		PikeEntity pike = (PikeEntity) this.mob;
		double attackReach = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= attackReach && this.isTimeToAttack()) {
			this.resetAttackCooldown();
			if (pike.getTarget() != null) {
				if (enemy instanceof AbstractFish || enemy instanceof Animal) {
					pike.setAttackCooldown(pike.getRandom().nextInt(550) + 50);
					enemy.startRiding(pike, true);
					pike.setTarget(null);
				} else {
					enemy.hurt(DamageSource.mobAttack(pike), 1.5F);
				}
			}
		}
	}

	@Override
	public void start() {
		PikeEntity pike = (PikeEntity) this.mob;
		if (pike.getItemBySlot(EquipmentSlot.MAINHAND) != null && pike.getTarget() instanceof AbstractFish || pike.getItemBySlot(EquipmentSlot.MAINHAND) != null && pike.getTarget() instanceof Turtle) {
			pike.spitOutItem(pike.getItemBySlot(EquipmentSlot.MAINHAND));
		}
		super.start();
	}

}