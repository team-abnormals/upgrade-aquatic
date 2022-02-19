package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.teamabnormals.blueprint.core.api.AdvancedRandomPositionGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

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

		Vec3 vec3d = this.getPosition();
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
	protected Vec3 getPosition() {
		//Tries to go deep when it has an entity in its mouth
		Vec3 vec3d = AdvancedRandomPositionGenerator.findRandomTarget(this.mob, 15, 8, !this.thrasher.getPassengers().isEmpty());

		for (int i = 0; vec3d != null && !this.mob.level.getBlockState(new BlockPos(vec3d)).isPathfindable(this.mob.level, new BlockPos(vec3d), PathComputationType.WATER) && i++ < 10; vec3d = AdvancedRandomPositionGenerator.findRandomTarget(this.mob, 10, 8, !this.thrasher.getPassengers().isEmpty())) {
		}

		return vec3d;
	}
}