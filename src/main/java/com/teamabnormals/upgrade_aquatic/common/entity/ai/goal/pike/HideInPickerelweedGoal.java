package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.pike;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.Pike;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public final class HideInPickerelweedGoal extends RandomStrollGoal {

	public HideInPickerelweedGoal(Pike pike) {
		super(pike, 1.1D, 25);
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
			if (((Pike) this.mob).isPickerelweedNearby()) {
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
		return ((Pike) this.mob).isPickerelweedNearby() && super.canContinueToUse();
	}

	@Nullable
	protected Vec3 getPosition() {
		Pike pike = (Pike) this.mob;
		if (pike.isPickerelweedNearby()) {
			int pickedWeed = pike.getRandom().nextInt(pike.getNearbyPickerelweeds().size());
			BlockPos pos = pike.getNearbyPickerelweeds().get(pickedWeed);
			return new Vec3(pos.getX(), pos.getY(), pos.getZ());
		}
		return null;
	}

}