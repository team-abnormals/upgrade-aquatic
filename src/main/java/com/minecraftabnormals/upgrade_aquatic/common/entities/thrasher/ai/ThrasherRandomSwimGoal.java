package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import javax.annotation.Nullable;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.teamabnormals.abnormals_core.core.library.api.AdvancedRandomPositionGenerator;

import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class ThrasherRandomSwimGoal extends RandomSwimmingGoal {
	private final ThrasherEntity thrasher;

	public ThrasherRandomSwimGoal(ThrasherEntity thrasher, double speed, int chance) {
		super(thrasher, speed, chance);
		this.thrasher = thrasher;
	}
	
	public boolean shouldExecute() {
		if(!this.mustUpdate) {
			if(this.thrasher.getIdleTime() >= 100) {
				return false;
			}

			if(this.thrasher.getRNG().nextInt(this.executionChance) != 0) {
				return false;
			}
		}

		Vector3d vec3d = this.getPosition();
		if(vec3d == null) {
			return false;
		} else {
			this.x = vec3d.x;
			this.y = vec3d.y;
			this.z = vec3d.z;
			this.mustUpdate = false;
			return true;
	    }
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return super.shouldContinueExecuting();
	}
	
	@Nullable
	@Override
	protected Vector3d getPosition() {
		//Tries to go deep when it has an entity in its mouth
		Vector3d vec3d = AdvancedRandomPositionGenerator.findRandomTarget(this.creature, 15, 8, !this.thrasher.getPassengers().isEmpty());
		
		for(int i = 0; vec3d != null && !this.creature.world.getBlockState(new BlockPos(vec3d)).allowsMovement(this.creature.world, new BlockPos(vec3d), PathType.WATER) && i++ < 10; vec3d = AdvancedRandomPositionGenerator.findRandomTarget(this.creature, 10, 8, !this.thrasher.getPassengers().isEmpty())) {
			;
		}
		
		return vec3d;
	}
}