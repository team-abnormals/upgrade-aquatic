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
	public boolean canUse() {
		if (!this.forceTrigger && (this.mob.getNoActionTime() >= 100 || this.mob.getRandom().nextInt(this.interval) != 0)) {
			return false;
		}

		Vector3d vec3d = this.getPosition();
		if (vec3d == null) {
			return false;
		} else {
			if (((PerchEntity) this.mob).isSeagrassNearby()) {
				this.wantedX = vec3d.x;
				this.wantedY = vec3d.y;
				this.wantedZ = vec3d.z;
				this.forceTrigger = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return ((PerchEntity) this.mob).isSeagrassNearby() && super.canContinueToUse();
	}


	@Nullable
	protected Vector3d getPosition() {
		PerchEntity perch = (PerchEntity) this.mob;
		if (perch.isSeagrassNearby()) {
			int seagrass = perch.getRandom().nextInt(perch.getNearbySeagrass().size());
			BlockPos pos = perch.getNearbySeagrass().get(seagrass);
			return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
		}
		return null;
	}
}