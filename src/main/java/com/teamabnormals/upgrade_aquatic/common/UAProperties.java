package com.teamabnormals.upgrade_aquatic.common;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class UAProperties {
	
	public static final Block.Properties DEAD_CORAL_BLOCK = Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties DEAD_CORAL = Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0F, 0F);
	
	public static Block.Properties CORAL_BASE(MaterialColor color) {
		return Block.Properties.create(Material.CORAL, color).doesNotBlockMovement().sound(SoundType.WET_GRASS);
	}
	
	public static Block.Properties PRISMARINE_CORAL_BASE() {
		return Block.Properties.create(Material.CORAL, MaterialColor.DIAMOND).doesNotBlockMovement().lightValue(3).sound(SoundType.WET_GRASS);
	}
	
	public static final Block.Properties SPINES = Block.Properties.create(Material.ORGANIC).doesNotBlockMovement().hardnessAndResistance(1.5F);
	public static final Block.Properties ELDER_EYE = Block.Properties.create(Material.ORGANIC, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.WET_GRASS).hardnessAndResistance(1.0F);
	
}