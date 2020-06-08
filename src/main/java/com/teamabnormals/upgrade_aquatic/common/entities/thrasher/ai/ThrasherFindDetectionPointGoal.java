package com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import java.util.EnumSet;
import java.util.Random;

import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ThrasherFindDetectionPointGoal extends Goal {
	public EntityThrasher thrasher;
	private LivingEntity foundTarget;
	private BlockPos foundPos;
	private int ticksPassed;

	public ThrasherFindDetectionPointGoal(EntityThrasher thrasher) {
		this.thrasher = thrasher;
		this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
	}
	
	@Override
	public boolean shouldExecute() {
		boolean flag = !this.thrasher.isStunned() && this.thrasher.getRNG().nextFloat() < 0.05F;
		if(flag) {
			this.findNearestTarget();
			return this.foundTarget != null && this.thrasher.getTicksSinceLastSonarFire() > 55 && EntityThrasher.ENEMY_MATCHER.test(this.foundTarget);
		}
		return false;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return !this.thrasher.isStunned() && this.thrasher.getPossibleDetectionPoint() == null && this.ticksPassed < 10;
	}
	
	@Override
	public void resetTask() {
		if(this.foundPos != null && !this.thrasher.isStunned()) {
			this.thrasher.setPossibleDetectionPoint(this.foundPos);
		}
	}
	
	public void tick() {
		this.ticksPassed++;
		Random rand = this.thrasher.getRNG();
		this.foundPos = this.foundTarget.getPosition().add(rand.nextInt(2), rand.nextInt(2), rand.nextInt(2));
	}
	
	private void findNearestTarget() {
		this.foundTarget = this.thrasher.world.getClosestEntity(this.thrasher.world.getEntitiesWithinAABB(LivingEntity.class, this.getTargetableArea(32), EntityThrasher.ENEMY_MATCHER), new EntityPredicate().setDistance(this.getTargetDistance()).setCustomPredicate(null), this.thrasher, this.thrasher.getPosX(), this.thrasher.getPosY() + this.thrasher.getEyeHeight(), this.thrasher.getPosZ());
	}
	
	private double getTargetDistance() {
		IAttributeInstance iattributeinstance = this.thrasher.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
		return iattributeinstance == null ? 16.0D : iattributeinstance.getValue();
	}
	
	private AxisAlignedBB getTargetableArea(double targetDistance) {
		return this.thrasher.getBoundingBox().grow(targetDistance, 4.0D, targetDistance);
	}
}