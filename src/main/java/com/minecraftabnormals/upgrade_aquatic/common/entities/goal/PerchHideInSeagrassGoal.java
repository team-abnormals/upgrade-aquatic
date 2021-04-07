package com.minecraftabnormals.upgrade_aquatic.common.entities.goal;

import com.minecraftabnormals.upgrade_aquatic.common.entities.PerchEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class PerchHideInSeagrassGoal extends RandomWalkingGoal {

	public PerchHideInSeagrassGoal(PerchEntity creature) {
		super(creature, 1.1D, 25);
	}

	@Override
	public boolean shouldExecute() {
		if (!this.mustUpdate && (this.creature.getIdleTime() >= 100 || this.creature.getRNG().nextInt(this.executionChance) != 0)) {
			return false;
		}

		Vector3d vec3d = this.getPosition();
		if (vec3d == null) {
			return false;
		} else {
			if (((PerchEntity) this.creature).isSeagrassNearby()) {
				this.x = vec3d.x;
				this.y = vec3d.y;
				this.z = vec3d.z;
				this.mustUpdate = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return ((PerchEntity) this.creature).isSeagrassNearby() && super.shouldContinueExecuting();
	}


	@Nullable
	protected Vector3d getPosition() {
		PerchEntity perch = (PerchEntity) this.creature;
		if (perch.isSeagrassNearby()) {
			int seagrass = perch.getRNG().nextInt(perch.getNearbySeagrass().size());
			BlockPos pos = perch.getNearbySeagrass().get(seagrass);
			return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
		}
		return null;
	}
}