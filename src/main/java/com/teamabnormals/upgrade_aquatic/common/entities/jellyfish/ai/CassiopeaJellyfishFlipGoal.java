package com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import java.util.EnumSet;

import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.EntityCassiopeaJellyfish;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CassiopeaJellyfishFlipGoal extends Goal {
	private final EntityCassiopeaJellyfish jellyfish;
	private World world;
	private int ticksPassed;
	
	public CassiopeaJellyfishFlipGoal(EntityCassiopeaJellyfish jellyfish) {
		this.jellyfish = jellyfish;
		this.world = jellyfish.world;
		this.setMutexFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		if(this.jellyfish.getRNG().nextFloat() < 0.025F && this.jellyfish.areEyesInFluid(FluidTags.WATER) && this.world.isDaytime()) {
			BlockPos pos = this.jellyfish.func_233580_cy_();
			if(pos.getY() >= this.world.getSeaLevel() - 2) {
				if(this.world.func_230315_m_().hasSkyLight() && this.world.canBlockSeeSky(pos)) {
					return !this.jellyfish.hasUpsideDownCooldown() && !this.jellyfish.func_233570_aj_();
				}
			}
		}
		return false;
	}
	
	@Override
	public void startExecuting() {
		this.ticksPassed = 0;
		this.jellyfish.upsideDownCooldown = this.jellyfish.getRNG().nextInt(1200) + 1600;
		this.jellyfish.lockedRotations[1] = 180.0F;
	}
	
	@Override
	public void tick() {
		this.ticksPassed++;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.ticksPassed < 40 && !this.jellyfish.func_233570_aj_() && this.jellyfish.areEyesInFluid(FluidTags.WATER) && this.world.isDaytime() && this.jellyfish.func_233580_cy_().getY() >= this.world.getSeaLevel() - 4;
	}
}