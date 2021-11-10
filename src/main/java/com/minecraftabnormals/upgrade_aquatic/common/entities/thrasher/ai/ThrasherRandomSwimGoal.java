package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import com.minecraftabnormals.abnormals_core.core.api.AdvancedRandomPositionGenerator;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class ThrasherRandomSwimGoal extends RandomSwimmingGoal {
	private final ThrasherEntity thrasher;

	public ThrasherRandomSwimGoal(ThrasherEntity thrasher, double speed, int chance) {
		super(thrasher, speed, chance);
		this.thrasher = thrasher;
	}

	public boolean canUse() {
		if (!this.forceTrigger) {
			if (this.thrasher.getNoActionTime() >= 100) {
				return false;
			}

			if (this.thrasher.getRandom().nextInt(this.interval) != 0) {
				return false;
			}
		}

		Vector3d vec3d = this.getPosition();
		if (vec3d == null) {
			return false;
		} else {
			this.wantedX = vec3d.x;
			this.wantedY = vec3d.y;
			this.wantedZ = vec3d.z;
			this.forceTrigger = false;
			return true;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse();
	}

	@Nullable
	@Override
	protected Vector3d getPosition() {
		//Tries to go deep when it has an entity in its mouth
		Vector3d vec3d = AdvancedRandomPositionGenerator.findRandomTarget(this.mob, 15, 8, !this.thrasher.getPassengers().isEmpty());

		for (int i = 0; vec3d != null && !this.mob.level.getBlockState(new BlockPos(vec3d)).isPathfindable(this.mob.level, new BlockPos(vec3d), PathType.WATER) && i++ < 10; vec3d = AdvancedRandomPositionGenerator.findRandomTarget(this.mob, 10, 8, !this.thrasher.getPassengers().isEmpty())) {
		}

		return vec3d;
	}
}