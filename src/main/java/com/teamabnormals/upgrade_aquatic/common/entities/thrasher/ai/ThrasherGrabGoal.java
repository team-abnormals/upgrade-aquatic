package com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatedEntity;
import com.teamabnormals.upgrade_aquatic.api.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class ThrasherGrabGoal extends MeleeAttackGoal {
	private EntityThrasher thrasher;

	public ThrasherGrabGoal(EntityThrasher thrasher, double speedIn, boolean useLongMemory) {
		super(thrasher, speedIn, useLongMemory);
		this.thrasher = thrasher;
	}
	
	@Override
	public boolean shouldExecute() {
		return super.shouldExecute() && thrasher.getPassengers().isEmpty();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return super.shouldContinueExecuting() && thrasher.getPassengers().isEmpty();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double attackReachSqr = this.getAttackReachSqr(enemy);
		if(distToEnemySqr <= attackReachSqr + 0.75F && this.attackTick <= 0) {
			if(this.thrasher.getPlayingAnimation() == EndimatedEntity.BLANK_ANIMATION) {
				NetworkUtil.setPlayingAnimationMessage(this.thrasher, EntityThrasher.SNAP_AT_PRAY_ANIMATION);
			}
		}
		
		if(distToEnemySqr <= attackReachSqr && this.attackTick <= 0) {
			enemy.startRiding(this.thrasher, true);
			this.thrasher.setAttackTarget(null);
		}
	}
}