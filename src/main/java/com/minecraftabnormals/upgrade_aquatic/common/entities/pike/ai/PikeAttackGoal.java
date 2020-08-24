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
	public boolean shouldExecute() {
		PikeEntity pike = (PikeEntity) this.attacker;
		return pike.getAttackCooldown() <= 0 && pike.getAttackTarget() != null && !pike.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem().isIn(ItemTags.FISHES) && pike.isInWater() && pike.getCaughtEntity() == null && !(pike.getAttackTarget() instanceof PufferfishEntity) && super.shouldExecute();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return !this.attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem().isIn(ItemTags.FISHES) && this.attacker.isInWater() && super.shouldContinueExecuting();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		PikeEntity pike = (PikeEntity) this.attacker;
		double attackReach = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= attackReach && this.func_234040_h_()) {
			this.func_234039_g_();
			if (pike.getAttackTarget() != null) {
				if (enemy instanceof AbstractFishEntity || enemy instanceof AnimalEntity) {
					pike.setAttackCooldown(pike.getRNG().nextInt(550) + 50);
					enemy.startRiding(pike, true);
					pike.setAttackTarget(null);
				} else {
					enemy.attackEntityFrom(DamageSource.causeMobDamage(pike), 1.5F);
				}
			}
		}
	}
	
	@Override
	public void startExecuting() {
		PikeEntity pike = (PikeEntity) this.attacker;
		if (pike.getItemStackFromSlot(EquipmentSlotType.MAINHAND) != null && pike.getAttackTarget() instanceof AbstractFishEntity || pike.getItemStackFromSlot(EquipmentSlotType.MAINHAND) != null && pike.getAttackTarget() instanceof TurtleEntity) {
			pike.spitOutItem(pike.getItemStackFromSlot(EquipmentSlotType.MAINHAND));
		}
		super.startExecuting();
	}

}