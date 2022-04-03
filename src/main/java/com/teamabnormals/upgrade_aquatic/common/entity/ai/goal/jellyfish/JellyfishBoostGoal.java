package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish;

import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.util.EntityUtil;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.HitResult.Type;

import java.util.EnumSet;

public class JellyfishBoostGoal extends Goal {
	private final AbstractJellyfish jellyfish;
	private final PlayableEndimation boostEndimation;

	public JellyfishBoostGoal(AbstractJellyfish jellyfish, PlayableEndimation boostEndimation) {
		this.jellyfish = jellyfish;
		this.boostEndimation = boostEndimation;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		float[] jellyfishRotations = this.jellyfish.getRotationController().getRotations(1.0F);
		float chance = jellyfishRotations[1] < 70.0F && jellyfishRotations[1] > -70.0F ? 0.05F : 0.03F;
		if (this.jellyfish.isInWater() && this.jellyfish.getRandom().nextFloat() < chance && this.jellyfish.isNoEndimationPlaying() && EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, jellyfishRotations[1], jellyfishRotations[0], 2.0F, 1.0F).getType() != Type.BLOCK) {
			return !this.jellyfish.willBeBoostedOutOfWater(jellyfishRotations[0], jellyfishRotations[1]);
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return this.jellyfish.isInWater() && this.jellyfish.isNoEndimationPlaying();
	}

	@Override
	public void start() {
		NetworkUtil.setPlayingAnimation(this.jellyfish, this.boostEndimation);
	}
}