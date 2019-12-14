package com.teamabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import java.util.EnumSet;

import com.teamabnormals.upgrade_aquatic.api.util.EntityUtil;
import com.teamabnormals.upgrade_aquatic.api.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.RayTraceResult;

public class ThrasherFireSonarGoal extends Goal {
	public EntityThrasher thrasher;
	private int sonarTicks;
	private int sonarFireDuration;
	private float originalYaw, originalPitch;
	
	public ThrasherFireSonarGoal(EntityThrasher thrasher) {
		this.thrasher = thrasher;
		this.setMutexFlags(EnumSet.of(Flag.LOOK, Flag.TARGET));
	}

	@Override
	public boolean shouldExecute() {
		boolean flag = !this.thrasher.isStunned() && this.thrasher.getPassengers().isEmpty() && this.thrasher.isAnimationPlaying(EntityThrasher.BLANK_ANIMATION) && this.thrasher.getAttackTarget() == null && this.thrasher.getRNG().nextFloat() < 0.05F;
		return flag && this.thrasher.isInWater() && this.thrasher.world.getBlockState(this.thrasher.getPosition().down()).getBlock() == Blocks.WATER && EntityUtil.rayTrace(this.thrasher, 32.0D, 1.0F).getType() == RayTraceResult.Type.MISS;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		boolean flag = !this.thrasher.isStunned() && this.thrasher.isInWater() && this.thrasher.getPassengers().isEmpty() && (this.thrasher.getAttackTarget() == null && (this.sonarTicks == 0 || this.sonarTicks == this.sonarFireDuration) || this.sonarTicks < this.sonarFireDuration);
		return flag && this.thrasher.world.getBlockState(this.thrasher.getPosition().down()).getBlock() == Blocks.WATER && EntityUtil.rayTrace(this.thrasher, 32.0D, 1.0F).getType() == RayTraceResult.Type.MISS;
	}
	
	@Override
	public void startExecuting() {
		this.sonarFireDuration = this.thrasher.getRNG().nextInt(3) * 5 + 30;
		this.originalYaw = this.thrasher.rotationYaw;
		this.originalPitch = this.thrasher.rotationPitch;
		NetworkUtil.setPlayingAnimationMessage(this.thrasher, EntityThrasher.SONAR_FIRE_ANIMATION);
	}
	
	@Override
	public void resetTask() {
		this.sonarFireDuration = 0;
		this.sonarTicks = 0;
	}
	
	@Override
	public void tick() {
		this.sonarTicks++;
		
		this.thrasher.getNavigator().clearPath();
		
		/*
		 * Stops thrasher from 'aimlessly' shooting
		 */
		this.thrasher.prevRotationYaw = this.originalYaw;
		this.thrasher.prevRotationPitch = this.originalPitch;
		this.thrasher.rotationYaw = this.originalYaw;
		this.thrasher.rotationPitch = this.originalPitch;
		
		if(this.sonarTicks % 5 == 0 && this.sonarTicks != 0 && this.sonarTicks < this.sonarFireDuration) {
			EntitySonarWave sonarWave = UAEntities.SONAR_WAVE.create(this.thrasher.world);
			sonarWave.fireSonarWave(this.thrasher);
			this.thrasher.world.addEntity(sonarWave);
		}
	}
}