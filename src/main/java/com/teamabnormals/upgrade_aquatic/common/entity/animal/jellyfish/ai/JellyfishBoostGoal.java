package com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ai;

import com.teamabnormals.blueprint.core.endimator.Endimation;
import com.teamabnormals.blueprint.core.util.EntityUtil;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfishEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.HitResult.Type;

import java.util.EnumSet;

public class JellyfishBoostGoal extends Goal {
	private final AbstractJellyfishEntity jellyfish;
	private final Endimation boostAnimation;

	public JellyfishBoostGoal(AbstractJellyfishEntity jellyfish, Endimation boostAnimation) {
		this.jellyfish = jellyfish;
		this.boostAnimation = boostAnimation;
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
		NetworkUtil.setPlayingAnimationMessage(this.jellyfish, this.boostAnimation);
	}
}