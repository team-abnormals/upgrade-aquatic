package com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.CassiopeaJellyfishEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;

import java.util.EnumSet;

import net.minecraft.entity.ai.goal.Goal.Flag;

/**
 * @author SmellyModder(Luke Tonon)
 */
public class CassiopeaHideInSeagrassGoal extends Goal {
	private final CassiopeaJellyfishEntity jellyfish;
	private int ticksPassed;
	private int ticksTillEnd;

	public CassiopeaHideInSeagrassGoal(CassiopeaJellyfishEntity jellyfish) {
		this.jellyfish = jellyfish;
		this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
	}

	@Override
	public boolean canUse() {
		return this.jellyfish.isEyeInFluid(FluidTags.WATER) && this.isInSeagrass() && !this.jellyfish.hasHideCooldown();
	}

	@Override
	public void start() {
		this.ticksPassed = 0;
		this.ticksTillEnd = this.jellyfish.getRandom().nextInt(40) + 80;
		this.jellyfish.hideCooldown = this.jellyfish.getRandom().nextInt(200) + 200;
	}

	@Override
	public void tick() {
		this.ticksPassed++;
	}

	@Override
	public boolean canContinueToUse() {
		return this.jellyfish.isEyeInFluid(FluidTags.WATER) && this.isInSeagrass() && this.ticksPassed < this.ticksTillEnd;
	}

	private boolean isInSeagrass() {
		return this.jellyfish.getFeetBlockState().getBlock() == Blocks.SEAGRASS || this.jellyfish.getFeetBlockState().getBlock() == Blocks.TALL_SEAGRASS;
	}
}