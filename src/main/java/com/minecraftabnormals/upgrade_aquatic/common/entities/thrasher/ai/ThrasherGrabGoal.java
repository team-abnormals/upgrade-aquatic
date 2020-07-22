package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.abnormals_core.core.utils.EntityUtils;
import com.teamabnormals.abnormals_core.core.utils.NetworkUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.math.RayTraceResult.Type;

public class ThrasherGrabGoal extends MeleeAttackGoal {
	private EntityThrasher thrasher;

	public ThrasherGrabGoal(EntityThrasher thrasher, double speedIn, boolean useLongMemory) {
		super(thrasher, speedIn, useLongMemory);
		this.thrasher = thrasher;
	}
	
	@Override
	public boolean shouldExecute() {
		LivingEntity attackTarget = this.thrasher.getAttackTarget();
		if(attackTarget != null && attackTarget.isPassenger()) {
			if(attackTarget.getRidingEntity() instanceof EntityThrasher) {
				return false;
			}
		}
		return !this.thrasher.isStunned() && super.shouldExecute() && this.thrasher.getPassengers().isEmpty();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		LivingEntity attackTarget = this.thrasher.getAttackTarget();
		if(attackTarget != null && attackTarget.isPassenger()) {
			if(attackTarget.getRidingEntity() instanceof EntityThrasher) {
				return false;
			}
		}
		return !this.thrasher.isStunned() && super.shouldContinueExecuting() && this.thrasher.getPassengers().isEmpty();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double attackReachSqr = this.getAttackReachSqr(enemy);
		if(distToEnemySqr <= attackReachSqr + 0.75F && this.func_234041_j_() <= 0) {
			if(this.thrasher.isNoEndimationPlaying()) {
				NetworkUtil.setPlayingAnimationMessage(this.thrasher, EntityThrasher.SNAP_AT_PRAY_ANIMATION);
			}
		}
		
		boolean isGrabBlocked = EntityUtils.rayTrace(this.thrasher, enemy.getPositionVec().distanceTo(this.thrasher.getPositionVec()), 1.0F).getType() == Type.BLOCK;
		
		if(distToEnemySqr <= attackReachSqr && !isGrabBlocked && this.func_234041_j_() <= 0) {
			enemy.startRiding(this.thrasher, true);
			this.thrasher.setAttackTarget(null);
		}
	}
	
	@Override
	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return super.getAttackReachSqr(attackTarget) * 0.55F;
	}
}