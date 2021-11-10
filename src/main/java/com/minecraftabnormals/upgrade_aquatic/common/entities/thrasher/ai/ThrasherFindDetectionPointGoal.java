package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;
import java.util.Random;

public class ThrasherFindDetectionPointGoal extends Goal {
	public ThrasherEntity thrasher;
	private LivingEntity foundTarget;
	private BlockPos foundPos;
	private int ticksPassed;

	public ThrasherFindDetectionPointGoal(ThrasherEntity thrasher) {
		this.thrasher = thrasher;
		this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	@Override
	public boolean canUse() {
		boolean flag = !this.thrasher.isStunned() && this.thrasher.getRandom().nextFloat() < 0.05F;
		if (flag) {
			this.findNearestTarget();
			return this.foundTarget != null && this.thrasher.getTicksSinceLastSonarFire() > 55 && ThrasherEntity.ENEMY_MATCHER.test(this.foundTarget);
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.thrasher.isStunned() && this.thrasher.getPossibleDetectionPoint() == null && this.ticksPassed < 10;
	}

	@Override
	public void stop() {
		if (this.foundPos != null && !this.thrasher.isStunned()) {
			this.thrasher.setPossibleDetectionPoint(this.foundPos);
		}
	}

	public void tick() {
		this.ticksPassed++;
		Random rand = this.thrasher.getRandom();
		this.foundPos = this.foundTarget.blockPosition().offset(rand.nextInt(2), rand.nextInt(2), rand.nextInt(2));
	}

	private void findNearestTarget() {
		this.foundTarget = this.thrasher.level.getNearestEntity(this.thrasher.level.getEntitiesOfClass(LivingEntity.class, this.getTargetableArea(32), ThrasherEntity.ENEMY_MATCHER), new EntityPredicate().range(this.getTargetDistance()).selector(null), this.thrasher, this.thrasher.getX(), this.thrasher.getY() + this.thrasher.getEyeHeight(), this.thrasher.getZ());
	}

	private double getTargetDistance() {
		ModifiableAttributeInstance iattributeinstance = this.thrasher.getAttribute(Attributes.FOLLOW_RANGE);
		return iattributeinstance == null ? 16.0D : iattributeinstance.getValue();
	}

	private AxisAlignedBB getTargetableArea(double targetDistance) {
		return this.thrasher.getBoundingBox().inflate(targetDistance, 4.0D, targetDistance);
	}
}