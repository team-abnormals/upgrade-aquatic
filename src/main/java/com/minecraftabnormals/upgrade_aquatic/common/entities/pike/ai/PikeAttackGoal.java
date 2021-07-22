package com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;

public final class PikeAttackGoal extends MeleeAttackGoal {
	
	public PikeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}
	
	@Override
	public boolean canUse() {
		PikeEntity pike = (PikeEntity) this.mob;
		return pike.getAttackCooldown() <= 0 && pike.getTarget() != null && !pike.getItemBySlot(EquipmentSlotType.MAINHAND).getItem().is(ItemTags.FISHES) && pike.isInWater() && pike.getCaughtEntity() == null && !(pike.getTarget() instanceof PufferfishEntity) && super.canUse();
	}
	
	@Override
	public boolean canContinueToUse() {
		return !this.mob.getItemBySlot(EquipmentSlotType.MAINHAND).getItem().is(ItemTags.FISHES) && this.mob.isInWater() && super.canContinueToUse();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		PikeEntity pike = (PikeEntity) this.mob;
		double attackReach = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= attackReach && this.isTimeToAttack()) {
			this.resetAttackCooldown();
			if (pike.getTarget() != null) {
				if (enemy instanceof AbstractFishEntity || enemy instanceof AnimalEntity) {
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
		if (pike.getItemBySlot(EquipmentSlotType.MAINHAND) != null && pike.getTarget() instanceof AbstractFishEntity || pike.getItemBySlot(EquipmentSlotType.MAINHAND) != null && pike.getTarget() instanceof TurtleEntity) {
			pike.spitOutItem(pike.getItemBySlot(EquipmentSlotType.MAINHAND));
		}
		super.start();
	}

}