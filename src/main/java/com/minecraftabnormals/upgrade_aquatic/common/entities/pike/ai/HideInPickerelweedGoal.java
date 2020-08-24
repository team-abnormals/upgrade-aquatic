package com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai;

import javax.annotation.Nullable;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;

import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public final class HideInPickerelweedGoal extends RandomWalkingGoal {
	
	public HideInPickerelweedGoal(PikeEntity pike) {
		super(pike, 1.1D, 25);
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
			if(((PikeEntity) this.creature).isPickerelweedNearby()) {
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
		return ((PikeEntity) this.creature).isPickerelweedNearby() && super.shouldContinueExecuting();
	}

	@Nullable
	protected Vector3d getPosition() {
		PikeEntity pike = (PikeEntity) this.creature;
		if (pike.isPickerelweedNearby()) {
			int pickedWeed = pike.getRNG().nextInt(pike.getNearbyPickerelweeds().size());
			BlockPos pos = pike.getNearbyPickerelweeds().get(pickedWeed);
			return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
		}
		return null;
	}
	
}