package com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;

import java.util.EnumSet;

import net.minecraft.entity.ai.goal.Goal.Flag;

public final class PikeTemptGoal extends Goal {
	private static final EntityPredicate CAN_FOLLOW = new EntityPredicate().range(10.0D).allowSameTeam().allowInvulnerable();
	private final PikeEntity pike;
	private PlayerEntity tempter;
	private int cooldown;

	public PikeTemptGoal(PikeEntity pike) {
		this.pike = pike;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean canUse() {
		if (this.cooldown > 0) {
			this.cooldown--;
            return false;
		} else {
			this.tempter = this.pike.level.getNearestPlayer(CAN_FOLLOW, this.pike);
			if (this.tempter == null) {
				return false;
			} else {
				return this.isTemptedBy(this.tempter.getMainHandItem()) || this.isTemptedBy(this.tempter.getOffhandItem());
			}
		}
	}

	public void stop() {
		this.tempter = null;
		this.pike.getNavigation().stop();
		this.cooldown = 100;
	}

	public void tick() {
		this.pike.getLookControl().setLookAt(this.tempter, this.pike.getMaxHeadYRot() + 20.0F, this.pike.getMaxHeadXRot());
		if (this.pike.distanceToSqr(this.tempter) < 6.25D) {
			this.pike.getNavigation().stop();
		} else {
			this.pike.getNavigation().moveTo(this.tempter, 1.0F);
		}
	}
      
	private boolean isTemptedBy(ItemStack stack) {
		return stack.getItem().is(ItemTags.FISHES);
	}
}