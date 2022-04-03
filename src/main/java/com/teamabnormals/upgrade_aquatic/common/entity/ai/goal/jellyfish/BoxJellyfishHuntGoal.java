package com.teamabnormals.upgrade_aquatic.common.entity.ai.goal.jellyfish;

import com.teamabnormals.blueprint.core.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfish;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEndimations;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class BoxJellyfishHuntGoal extends Goal {
	private final BoxJellyfish hunter;
	private int noSightTicks;

	public BoxJellyfishHuntGoal(BoxJellyfish hunter) {
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
		Vec3 distance = new Vec3(target.getX() - this.hunter.getX(), target.getY() - this.hunter.getY(), target.getZ() - this.hunter.getZ());

		float pitch = -((float) (Mth.atan2(distance.y(), Mth.sqrt((float) (distance.x() * distance.x() + distance.z() * distance.z()))) * (double) (180F / (float) Math.PI)));
		float yaw = (float) (Mth.atan2(distance.z(), distance.x()) * (double) (180F / (float) Math.PI)) - 90.0F;

		this.hunter.lockedRotations[0] = Mth.wrapDegrees(yaw);
		this.hunter.lockedRotations[1] = Mth.wrapDegrees(pitch + 90.0F);

		float[] rotations = this.hunter.getRotationController().getRotations(1.0F);

		if (!this.hunter.hasLineOfSight(target)) {
			this.noSightTicks++;
		}

		if (this.hunter.isNoEndimationPlaying() && Math.abs(rotations[0] - this.hunter.lockedRotations[0]) < 7.5F) {
			NetworkUtil.setPlayingAnimation(this.hunter, UAEndimations.JELLYFISH_SWIM);
		}
	}
}