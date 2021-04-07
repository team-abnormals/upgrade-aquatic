package com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;

import java.util.EnumSet;

public final class PikeTemptGoal extends Goal {
	private static final EntityPredicate CAN_FOLLOW = new EntityPredicate().setDistance(10.0D).allowFriendlyFire().allowInvulnerable();
	private final PikeEntity pike;
	private PlayerEntity tempter;
	private int cooldown;

	public PikeTemptGoal(PikeEntity pike) {
		this.pike = pike;
		this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
	}

	public boolean shouldExecute() {
		if (this.cooldown > 0) {
			this.cooldown--;
            return false;
		} else {
			this.tempter = this.pike.world.getClosestPlayer(CAN_FOLLOW, this.pike);
			if (this.tempter == null) {
				return false;
			} else {
				return this.isTemptedBy(this.tempter.getHeldItemMainhand()) || this.isTemptedBy(this.tempter.getHeldItemOffhand());
			}
		}
	}

	public void resetTask() {
		this.tempter = null;
		this.pike.getNavigator().clearPath();
		this.cooldown = 100;
	}

	public void tick() {
		this.pike.getLookController().setLookPositionWithEntity(this.tempter, this.pike.getHorizontalFaceSpeed() + 20.0F, this.pike.getVerticalFaceSpeed());
		if (this.pike.getDistanceSq(this.tempter) < 6.25D) {
			this.pike.getNavigator().clearPath();
		} else {
			this.pike.getNavigator().tryMoveToEntityLiving(this.tempter, 1.0F);
		}
	}
      
	private boolean isTemptedBy(ItemStack stack) {
		return stack.getItem().isIn(ItemTags.FISHES);
	}
}