package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.thrasher;

import com.teamabnormals.blueprint.core.util.EntityUtil;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entity.monster.Thrasher;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.phys.HitResult.Type;

public class ThrasherGrabGoal extends MeleeAttackGoal {
	private final Thrasher thrasher;

	public ThrasherGrabGoal(Thrasher thrasher, double speedIn, boolean useLongMemory) {
		super(thrasher, speedIn, useLongMemory);
		this.thrasher = thrasher;
	}

	@Override
	public boolean canUse() {
		LivingEntity attackTarget = this.thrasher.getTarget();
		if (attackTarget != null && attackTarget.isPassenger()) {
			if (attackTarget.getVehicle() instanceof Thrasher) {
				return false;
			}
		}
		return !this.thrasher.isStunned() && super.canUse() && this.thrasher.getPassengers().isEmpty();
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity attackTarget = this.thrasher.getTarget();
		if (attackTarget != null && attackTarget.isPassenger()) {
			if (attackTarget.getVehicle() instanceof Thrasher) {
				return false;
			}
		}
		return !this.thrasher.isStunned() && super.canContinueToUse() && this.thrasher.getPassengers().isEmpty();
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double attackReachSqr = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= attackReachSqr + 0.75F && this.getTicksUntilNextAttack() <= 0) {
			if (this.thrasher.isNoEndimationPlaying()) {
				NetworkUtil.setPlayingAnimationMessage(this.thrasher, Thrasher.SNAP_AT_PRAY_ANIMATION);
			}
		}

		boolean isGrabBlocked = EntityUtil.rayTrace(this.thrasher, enemy.position().distanceTo(this.thrasher.position()), 1.0F).getType() == Type.BLOCK;

		if (distToEnemySqr <= attackReachSqr && !isGrabBlocked && this.getTicksUntilNextAttack() <= 0) {
			enemy.startRiding(this.thrasher, true);
			this.thrasher.setTarget(null);
		}
	}

	@Override
	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return super.getAttackReachSqr(attackTarget) * 0.55F;
	}
}