package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import java.util.EnumSet;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.CassiopeaJellyfishEntity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class CassiopeaHideInSeagrassGoal extends Goal {
	private final CassiopeaJellyfishEntity jellyfish;
	private int ticksPassed;
	private int ticksTillEnd;
	
	public CassiopeaHideInSeagrassGoal(CassiopeaJellyfishEntity jellyfish) {
		this.jellyfish = jellyfish;
		this.setMutexFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean shouldExecute() {
		return this.jellyfish.areEyesInFluid(FluidTags.WATER) && this.isInSeagrass() && !this.jellyfish.hasHideCooldown();
	}
	
	@Override
	public void startExecuting() {
		this.ticksPassed = 0;
		this.ticksTillEnd = this.jellyfish.getRNG().nextInt(40) + 80;
		this.jellyfish.hideCooldown = this.jellyfish.getRNG().nextInt(200) + 200;
	}
	
	@Override
	public void tick() {
		this.ticksPassed++;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		return this.jellyfish.areEyesInFluid(FluidTags.WATER) && this.isInSeagrass() && this.ticksPassed < this.ticksTillEnd;
	}
	
	private boolean isInSeagrass() {
		return this.jellyfish.getBlockState().getBlock() == Blocks.SEAGRASS || this.jellyfish.getBlockState().getBlock() == Blocks.TALL_SEAGRASS;
	}
}