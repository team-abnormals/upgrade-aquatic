package com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import java.util.EnumSet;

import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;

public class ThrasherSonarGoal extends Goal {
	private final EntityThrasher thrasher;
	
	public ThrasherSonarGoal(EntityThrasher thrasher) {
		this.thrasher = thrasher;
		this.setMutexFlags(EnumSet.of(Goal.Flag.TARGET));
	}

	@Override
	public boolean shouldExecute() {
		return this.thrasher.isSonarActive();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.thrasher.isSonarActive();
	}
	
	@Override
	public void tick() {
		AxisAlignedBB detectionBox = new AxisAlignedBB(this.thrasher.getPosition());
		if (this.thrasher.isAlive()) {
			double expansionAmount = this.thrasher.sonarTicks <= 80 ? (this.thrasher.sonarTicks / 20) * 4 : (this.thrasher.sonarTicks / 20) / 2 * 4;
			for(LivingEntity livingEntity : this.thrasher.world.getEntitiesWithinAABB(LivingEntity.class, detectionBox.grow(expansionAmount), EntityThrasher.ENEMY_MATCHER)) {
				if(livingEntity != null && livingEntity.isAlive()) {
					this.thrasher.setAttackTarget(livingEntity);
					this.thrasher.setSonarActive(false);
					this.thrasher.setTicksTillSonar((this.thrasher.getRNG().nextInt(140) + 80) * 20);
				}
			}
		}
	}
}