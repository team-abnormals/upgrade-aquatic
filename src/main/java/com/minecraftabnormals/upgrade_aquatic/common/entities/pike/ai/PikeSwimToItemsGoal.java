package com.minecraftabnormals.upgrade_aquatic.common.entities.pike.ai;

import com.minecraftabnormals.upgrade_aquatic.common.entities.pike.PikeEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.List;

public final class PikeSwimToItemsGoal extends Goal {
	private final PikeEntity pike;

	public PikeSwimToItemsGoal(PikeEntity pike) {
		this.pike = pike;
	}

	@Override
	public boolean canUse() {
		List<ItemEntity> list = this.pike.level.getEntitiesOfClass(ItemEntity.class, this.pike.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), PikeEntity.ITEM_SELECTOR);
		return !list.isEmpty() || !this.pike.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
	}

	@Override
	public void start() {
		this.moveTowardsNearestItem();
	}

	@Override
	public void tick() {
		if (this.pike.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) this.moveTowardsNearestItem();
	}

	private void moveTowardsNearestItem() {
		List<ItemEntity> list = this.pike.level.getEntitiesOfClass(ItemEntity.class, this.pike.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), PikeEntity.ITEM_SELECTOR);
		if (!list.isEmpty()) this.pike.getNavigation().moveTo(list.get(0), 1.2F);
	}
}