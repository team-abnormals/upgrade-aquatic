package com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import java.util.EnumSet;

import com.teamabnormals.upgrade_aquatic.api.util.EntityUtil;
import com.teamabnormals.upgrade_aquatic.api.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.EntityBoxJellyfish;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.RayTraceResult.Type;

public class JellyfishBoostGoal extends Goal {
	private final AbstractEntityJellyfish jellyfish;
	
	public JellyfishBoostGoal(AbstractEntityJellyfish jellyfish) {
		this.jellyfish = jellyfish;
		this.setMutexFlags(EnumSet.of(Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		float[] jellyfishRotations = this.jellyfish.getRotationController().getRotations(1.0F);
		float chance = jellyfishRotations[1] < 70.0F && jellyfishRotations[1] > -70.0F ? 0.05F : 0.03F;
		if(this.jellyfish.isInWater() && this.jellyfish.getRNG().nextFloat() < chance && this.jellyfish.isNoEndimationPlaying() && EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, jellyfishRotations[1], jellyfishRotations[0], 2.0F, 1.0F).getType() != Type.BLOCK) {
			return !this.jellyfish.willBeBoostedOutOfWater(jellyfishRotations[0], jellyfishRotations[1]);
		}
		return false;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.jellyfish.isInWater() && this.jellyfish.isNoEndimationPlaying();
	}
	
	@Override
	public void startExecuting() {
		NetworkUtil.setPlayingAnimationMessage(this.jellyfish, EntityBoxJellyfish.BOOST_ANIMATION);
	}
}