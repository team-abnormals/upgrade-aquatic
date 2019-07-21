package com.teamabnormals.upgrade_aquatic.core.registry;

import net.minecraft.item.Food;

public class UAFoods {

	public static final Food PICKERELWEED(boolean purple) {
		return purple ? new Food.Builder().hunger(3).saturation(0.4F).build() : new Food.Builder().hunger(2).saturation(0.6F).build();
	}
	
}
