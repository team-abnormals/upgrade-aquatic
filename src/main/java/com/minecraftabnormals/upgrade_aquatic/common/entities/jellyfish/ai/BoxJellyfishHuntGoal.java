package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.BoxJellyfishEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.EnumSet;

public class BoxJellyfishHuntGoal extends Goal {
	private final BoxJellyfishEntity hunter;
	private int noSightTicks;

	public BoxJellyfishHuntGoal(BoxJellyfishEntity hunter) {
		this.hunter = hunter;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.hunter.getTarget();
		if (this.hunter.isInWater() && target != null && target.isInWater() && target.isAlive() && target.distanceTo(this.hunter) <= 10) {
			return true;
		} else if (target != null) {
			this.hunter.setTarget(null);
		}
		return false;
	}

	@Override
	public boolean canContinueToUse() {
		return super.canContinueToUse() && this.noSightTicks < 20;
	}

	@Override
	public void start() {
		this.hunter.setHuntingCooldown();
		this.noSightTicks = 0;
	}

	@Override
	public void tick() {
		LivingEntity target = this.hunter.getTarget();
		Vector3d distance = new Vector3d(target.getX() - this.hunter.getX(), target.getY() - this.hunter.getY(), target.getZ() - this.hunter.getZ());

		float pitch = -((float) (MathHelper.atan2(distance.y(), MathHelper.sqrt(distance.x() * distance.x() + distance.z() * distance.z())) * (double) (180F / (float) Math.PI)));
		float yaw = (float) (MathHelper.atan2(distance.z(), distance.x()) * (double) (180F / (float) Math.PI)) - 90.0F;

		this.hunter.lockedRotations[0] = MathHelper.wrapDegrees(yaw);
		this.hunter.lockedRotations[1] = MathHelper.wrapDegrees(pitch + 90.0F);

		float[] rotations = this.hunter.getRotationController().getRotations(1.0F);

		if (!this.hunter.canSee(target)) {
			this.noSightTicks++;
		}

		if (this.hunter.isNoEndimationPlaying() && Math.abs(rotations[0] - this.hunter.lockedRotations[0]) < 7.5F) {
			NetworkUtil.setPlayingAnimationMessage(this.hunter, BoxJellyfishEntity.SWIM_ANIMATION);
		}
	}
}