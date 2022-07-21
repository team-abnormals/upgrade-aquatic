package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish;

import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.util.EntityUtil;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.HitResult.Type;

import java.util.EnumSet;
import java.util.Random;

public class JellyfishSwimIntoDirectionGoal extends Goal {
	private final AbstractJellyfish jellyfish;
	private final PlayableEndimation swimEndimation;
	private float yaw, pitch;
	private int ticksAtRotation;

	public JellyfishSwimIntoDirectionGoal(AbstractJellyfish jellyfish, PlayableEndimation swimEndimation) {
		this.jellyfish = jellyfish;
		this.swimEndimation = swimEndimation;
		this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		Random rand = this.jellyfish.getRandom();
		float chance = EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, this.jellyfish.lockedRotations[1], this.jellyfish.lockedRotations[0], 2.0F, 1.0F).getType() != Type.BLOCK ? 0.1F : 0.025F;
		if (this.jellyfish.isNoEndimationPlaying() && rand.nextFloat() < chance && this.jellyfish.isEyeInFluid(FluidTags.WATER)) {
			float[] generatedRotations = this.generateDirection(rand);
			this.yaw = generatedRotations[0];
			this.pitch = generatedRotations[1];

			if (EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, this.pitch, this.yaw, 1.0F, 1.0F).getType() != Type.BLOCK) {
				return !this.jellyfish.willBeBoostedOutOfWater(this.yaw, this.pitch);
			}
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return this.jellyfish.isNoEndimationPlaying() && this.jellyfish.isEyeInFluid(FluidTags.WATER);
	}

	@Override
	public void start() {
		this.jellyfish.lockedRotations[0] = this.yaw;
		this.jellyfish.lockedRotations[1] = this.pitch;
	}

	@Override
	public void tick() {
		float[] jellyfishRotations = this.jellyfish.getRotationController().getRotations(1.0F);
		if (this.yaw == jellyfishRotations[0] && this.pitch == jellyfishRotations[1]) {
			this.ticksAtRotation++;
		}

		if (this.ticksAtRotation >= 5) {
			NetworkUtil.setPlayingAnimation(this.jellyfish, this.swimEndimation);
		}
	}

	@Override
	public void stop() {
		this.ticksAtRotation = 0;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	private float[] generateDirection(Random rand) {
		float[] rotations = this.jellyfish.getRotationController().getRotations(1.0F);
		float upperChance = this.jellyfish.position().y() < this.jellyfish.level.getSeaLevel() - 6 ? 0.5F : 0.2F;
		if (this.jellyfish.isOnGround() || rotations[0] == 0.0F && rotations[1] == 0.0F || EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, rotations[1], rotations[0], 1.0F, 1.0F).getType() == Type.BLOCK) {
			return new float[]{
					(float) Mth.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 180.0F, rand)),
					(float) Mth.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 180.0F, rand))
			};
		}
		return new float[]{
				rotations[0] + (float) Mth.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 100.0F, rand)),
				rand.nextFloat() < upperChance ? (float) MathUtil.makeNegativeRandomly(rand.nextFloat() * 50.0F, rand) : rotations[1] + (float) Mth.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 100.0F, rand))
		};
	}


}