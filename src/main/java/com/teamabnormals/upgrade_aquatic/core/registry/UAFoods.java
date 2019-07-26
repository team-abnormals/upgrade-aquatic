package com.teamabnormals.upgrade_aquatic.core.registry;

import net.minecraft.item.Food;

public class UAFoods {

	public static final Food PICKERELWEED(boolean purple) {
		return purple ? new Food.Builder().hunger(3).saturation(0.0F).setAlwaysEdible().build() : new Food.Builder().hunger(2).saturation(0.0F).setAlwaysEdible().build();
	}
	
	public static final Food PICKEREL(boolean cooked) {
		return cooked ? new Food.Builder().hunger(6).saturation(9.6F).build() : new Food.Builder().hunger(2).saturation(0.2F).build();
	}
	
}
