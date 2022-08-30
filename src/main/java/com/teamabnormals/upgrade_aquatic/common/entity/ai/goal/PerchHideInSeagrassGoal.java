package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Perch;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class PerchHideInSeagrassGoal extends RandomStrollGoal {

	public PerchHideInSeagrassGoal(Perch creature) {
		super(creature, 1.1D, 25);
	}

	@Override
	public boolean canUse() {
		if (!this.forceTrigger && (this.mob.getNoActionTime() >= 100 || this.mob.getRandom().nextInt(this.interval) != 0)) {
			return false;
		}

		Vec3 vec3d = this.getPosition();
		if (vec3d == null) {
			return false;
		} else {
			if (((Perch) this.mob).isSeagrassNearby()) {
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
		return ((Perch) this.mob).isSeagrassNearby() && super.canContinueToUse();
	}

	@Nullable
	protected Vec3 getPosition() {
		Perch perch = (Perch) this.mob;
		if (perch.isSeagrassNearby()) {
			int seagrass = perch.getRandom().nextInt(perch.getNearbySeagrass().size());
			BlockPos pos = perch.getNearbySeagrass().get(seagrass);
			return new Vec3(pos.getX(), pos.getY(), pos.getZ());
		}
		return null;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

}