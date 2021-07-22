package com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public final class HideInPickerelweedGoal extends RandomWalkingGoal {
	
	public HideInPickerelweedGoal(PikeEntity pike) {
		super(pike, 1.1D, 25);
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
			if(((PikeEntity) this.mob).isPickerelweedNearby()) {
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
		return ((PikeEntity) this.mob).isPickerelweedNearby() && super.canContinueToUse();
	}

	@Nullable
	protected Vector3d getPosition() {
		PikeEntity pike = (PikeEntity) this.mob;
		if (pike.isPickerelweedNearby()) {
			int pickedWeed = pike.getRandom().nextInt(pike.getNearbyPickerelweeds().size());
			BlockPos pos = pike.getNearbyPickerelweeds().get(pickedWeed);
			return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
		}
		return null;
	}
	
}