package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.abnormals_core.core.util.EntityUtil;
import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractJellyfishEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.RayTraceResult.Type;

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