package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import java.util.EnumSet;
import java.util.function.BiPredicate;

import javax.annotation.Nullable;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntitySonarWave;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UAEntities;
import com.teamabnormals.abnormals_core.core.utils.EntityUtils;
import com.teamabnormals.abnormals_core.core.utils.NetworkUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class ThrasherFireSonarGoal extends Goal {
	public EntityThrasher thrasher;
	private int turnTicks;
	private int sonarTicks;
	private int sonarFireDuration;
	private float originalYaw, originalPitch;
	@Nullable
	private SonarPhase sonarPhase;
	
	public ThrasherFireSonarGoal(EntityThrasher thrasher) {
		this.thrasher = thrasher;
		this.setMutexFlags(EnumSet.of(Flag.LOOK, Flag.TARGET));
	}

	@Override
	public boolean shouldExecute() {
		return SonarPhase.shouldContinueExecutingPhase(null, this.thrasher, this.sonarTicks) && this.thrasher.getTicksSinceLastSonarFire() > 55 && this.thrasher.isEndimationPlaying(EntityThrasher.BLANK_ANIMATION);
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		boolean shouldContinue = SonarPhase.shouldContinueExecutingPhase(this.sonarPhase, this.thrasher, this.sonarTicks);
		return shouldContinue && (this.sonarPhase == SonarPhase.FIRE ? (this.thrasher.getAttackTarget() == null && (this.sonarTicks == 0 || this.sonarTicks == this.sonarFireDuration) || this.sonarTicks < this.sonarFireDuration) : true);
	}
	
	@Override
	public void startExecuting() {
		this.sonarPhase = SonarPhase.TURN;
		this.sonarFireDuration = this.thrasher.getRNG().nextInt(3) * 5 + 30;
	}
	
	@Override
	public void resetTask() {
		this.sonarFireDuration = 0;
		this.sonarTicks = 0;
		this.turnTicks = 0;
		this.sonarPhase = null;
		this.thrasher.setPossibleDetectionPoint(null);
		((EntityThrasher.ThrasherLookController) this.thrasher.getLookController()).setTurningForSonar(false);
	}
	
	@Override
	public void tick() {
		this.thrasher.getNavigator().clearPath();
		
		if(this.sonarPhase == SonarPhase.TURN) {
			this.turnTicks++;
			BlockPos pos = this.thrasher.getPossibleDetectionPoint();
			((EntityThrasher.ThrasherLookController) this.thrasher.getLookController()).setTurningForSonar(true);
			this.thrasher.getLookController().setLookPosition(pos.getX(), pos.getY(), pos.getZ(), 60.0F, 60.0F);
			
			if(this.turnTicks > 50) {
				this.sonarPhase = SonarPhase.FIRE;
			}
		} else {
			if(this.sonarTicks == 0 && SonarPhase.shouldContinueExecutingPhase(SonarPhase.FIRE, this.thrasher, this.sonarTicks)) {
				this.originalYaw = this.thrasher.rotationYaw;
				this.originalPitch = this.thrasher.rotationPitch;
				NetworkUtil.setPlayingAnimationMessage(this.thrasher, EntityThrasher.SONAR_FIRE_ANIMATION);
				this.thrasher.playSound(this.thrasher.getSonarFireSound(), 3.5F, 1.0F);
			}
			
			this.sonarTicks++;
			
			this.stablilizeDirection();
			
			if(this.sonarTicks % 5 == 0 && this.sonarTicks < this.sonarFireDuration) {
				EntitySonarWave sonarWave = UAEntities.SONAR_WAVE.get().create(this.thrasher.world);
				sonarWave.fireSonarWave(this.thrasher);
				this.thrasher.world.addEntity(sonarWave);
			}
		}
	}
	
	private void stablilizeDirection() {
		this.thrasher.prevRotationYaw = this.originalYaw;
		this.thrasher.prevRotationPitch = this.originalPitch;
		this.thrasher.rotationYaw = this.originalYaw;
		this.thrasher.rotationPitch = this.originalPitch;
	}
	
	static enum SonarPhase {
		TURN(null),
		FIRE((thrasher, sonarTicks) -> sonarTicks > 15 ? EntityUtils.rayTrace(thrasher, 32.0D, 1.0F).getType() == RayTraceResult.Type.MISS : true);
		
		@Nullable
		private BiPredicate<EntityThrasher, Integer> phaseCondition;
		
		SonarPhase(@Nullable BiPredicate<EntityThrasher, Integer> phaseCondition) {
			this.phaseCondition = phaseCondition;
		}
		
		public static boolean shouldContinueExecutingPhase(@Nullable SonarPhase phase, EntityThrasher thrasher, int sonarTicks) {
			boolean defaultCondition = !thrasher.isStunned() && thrasher.isInWater() && thrasher.getPassengers().isEmpty() && thrasher.getAttackTarget() == null && thrasher.getPossibleDetectionPoint() != null && thrasher.world.getBlockState(thrasher.func_233580_cy_().down()).getBlock() == Blocks.WATER;
			if(phase == null) {
				return defaultCondition;
			}
			return defaultCondition && (phase.phaseCondition != null && phase.phaseCondition.test(thrasher, sonarTicks) || phase.phaseCondition == null);
		}
	}
}