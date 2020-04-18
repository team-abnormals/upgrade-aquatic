package com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import java.util.EnumSet;
import java.util.Random;

import com.teamabnormals.upgrade_aquatic.api.util.EntityUtil;
import com.teamabnormals.upgrade_aquatic.api.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.api.util.NetworkUtil;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.AbstractEntityJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.EntityBoxJellyfish;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult.Type;

public class JellyfishSwinIntoDirectionGoal extends Goal {
	private final AbstractEntityJellyfish jellyfish;
	private float yaw, pitch;
	private int ticksAtRotation;
	
	public JellyfishSwinIntoDirectionGoal(AbstractEntityJellyfish jellyfish) {
		this.jellyfish = jellyfish;
		this.setMutexFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		Random rand = this.jellyfish.getRNG();
		float chance = EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, this.jellyfish.lockedRotations[1], this.jellyfish.lockedRotations[0], 2.0F, 1.0F).getType() != Type.BLOCK ? 0.1F : 0.025F;
		if(this.jellyfish.isNoEndimationPlaying() && rand.nextFloat() < chance && this.jellyfish.areEyesInFluid(FluidTags.WATER)) {
			float[] generatedRotations = this.generateDirection(rand);
			this.yaw = generatedRotations[0];
			this.pitch = generatedRotations[1];
			
			if(EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, this.pitch, this.yaw, 1.0F, 1.0F).getType() != Type.BLOCK) {
				return !this.jellyfish.willBeBoostedOutOfWater(this.yaw, this.pitch);
			}
		}
		return false;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.jellyfish.isNoEndimationPlaying() && this.jellyfish.areEyesInFluid(FluidTags.WATER);
	}
	
	@Override
	public void startExecuting() {
		this.jellyfish.lockedRotations[0] = this.yaw;
		this.jellyfish.lockedRotations[1] = this.pitch;
	}
	
	@Override
	public void tick() {
		float[] jellyfishRotations = this.jellyfish.getRotationController().getRotations(1.0F);
		if(this.yaw == jellyfishRotations[0] && this.pitch == jellyfishRotations[1]) {
			this.ticksAtRotation++;
		}
		
		if(this.ticksAtRotation >= 5) {
			NetworkUtil.setPlayingAnimationMessage(this.jellyfish, EntityBoxJellyfish.SWIM_ANIMATION);
		}
	}
	
	@Override
	public void resetTask() {
		this.ticksAtRotation = 0;
	}
	
	private float[] generateDirection(Random rand) {
		float[] rotations = this.jellyfish.getRotationController().getRotations(1.0F);
		float negativeChance = this.jellyfish.getPosition().getY() <= this.jellyfish.world.getSeaLevel() - 2 ? 0.75F : 0.25F;
		if(this.jellyfish.onGround || rotations[0] == 0.0F && rotations[1] == 0.0F || EntityUtil.rayTraceUpWithCustomDirection(this.jellyfish, rotations[1], rotations[0], 1.0F, 1.0F).getType() == Type.BLOCK) {
			return new float[] {
				(float) MathHelper.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 360.0F, rand)),
				(float) MathHelper.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 360.0F, rand))
			};
		}
		return new float[] {rotations[0] + (float) MathHelper.wrapDegrees(MathUtil.makeNegativeRandomly(rand.nextFloat() * 100.0F, rand)), rotations[1] + (float) MathHelper.wrapDegrees(MathUtil.makeNegativeRandomlyWithFavoritism(rand.nextFloat() * 100.0F, rand, negativeChance))};
	}
}