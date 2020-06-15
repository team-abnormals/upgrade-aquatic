package com.teamabnormals.upgrade_aquatic.core.registry.other;

import java.util.Arrays;

import com.teamabnormals.abnormals_core.core.utils.DataUtils;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;

public class UAColors {
	
	public static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		DataUtils.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(
				UABlocks.RIVER_LEAVES, UABlocks.RIVER_LEAF_CARPET, UABlocks.MULBERRY_VINE));

		ItemColors itemColors = Minecraft.getInstance().getItemColors();
		DataUtils.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAVES, UABlocks.RIVER_LEAF_CARPET));
	}	
}