package com.teamabnormals.upgrade_aquatic.common;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class UAProperties {
	
	public static final Block.Properties DEAD_CORAL_BLOCK = Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F);
	public static final Block.Properties DEAD_CORAL = Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0F, 0F);
	
	public static Block.Properties CORAL_BASE(MaterialColor color) {
		return Block.Properties.create(Material.CORAL, color).doesNotBlockMovement().sound(SoundType.WET_GRASS);
	}
	
	public static Block.Properties CORAL_BLOCK_BASE(MaterialColor color) {
		return Block.Properties.create(Material.ROCK, color).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL);
	}
	
	public static Block.Properties CORAL_FAN_BASE(MaterialColor color) {
		return Block.Properties.create(Material.OCEAN_PLANT, color).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.WET_GRASS);
	}
	
	public static Block.Properties PRISMARINE_CORAL_BASE() {
		return Block.Properties.create(Material.CORAL, MaterialColor.DIAMOND).doesNotBlockMovement().lightValue(3).sound(SoundType.WET_GRASS);
	}
	
	public static final Block.Properties SEAROCKET(boolean pink) {
		return pink ? Block.Properties.create(Material.PLANTS, MaterialColor.PINK).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT) : Block.Properties.create(Material.PLANTS, MaterialColor.WHITE_TERRACOTTA).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT);
	}
	
	public static final Block.Properties CORALSTONE = Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE).tickRandomly();
	
	public static final Block.Properties DRIFTWOOD    = Block.Properties.create(Material.WOOD, MaterialColor.STONE).sound(SoundType.WOOD).hardnessAndResistance(2, 10);
	public static final Block.Properties SPINES       = Block.Properties.create(Material.ORGANIC).doesNotBlockMovement().hardnessAndResistance(1.5F);
	public static final Block.Properties ELDER_EYE    = Block.Properties.create(Material.ORGANIC, MaterialColor.WHITE_TERRACOTTA).sound(SoundType.WET_GRASS).hardnessAndResistance(1.0F);
	public static final Block.Properties BEDROLL      = Block.Properties.create(Material.WOOL).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.CLOTH);
	public static final Block.Properties PICKERELWEED = Block.Properties.create(Material.PLANTS, MaterialColor.GRASS).tickRandomly().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT);
	
	public static final Block.Properties PICKERELWEED_BLOCK(boolean isBoiled){
		return isBoiled ? Block.Properties.create(Material.PLANTS, MaterialColor.FOLIAGE).hardnessAndResistance(0.5F, 5).sound(SoundType.PLANT) : Block.Properties.create(Material.PLANTS, MaterialColor.GRASS).hardnessAndResistance(0.5F, 5).sound(SoundType.WET_GRASS);
	}
}