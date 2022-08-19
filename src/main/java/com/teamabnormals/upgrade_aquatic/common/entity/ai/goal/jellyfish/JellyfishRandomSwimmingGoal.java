package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class JellyfishRandomSwimmingGoal extends RandomSwimmingGoal {

	public JellyfishRandomSwimmingGoal(AbstractJellyfish jellyfish) {
		super(jellyfish, 1.0F, 60);
	}

	@Nullable
	@Override
	protected Vec3 getPosition() {
		return BehaviorUtils.getRandomSwimmablePos(this.mob, 8, 8);
	}

}