package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;

public class UAColors
{
	public static void registerBlockColors() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), UABlocks.RIVER_LEAVES.get());
        
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), UABlocks.RIVER_LEAVES.get());
        
        blockColors.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), UABlocks.RIVER_LEAF_CARPET.get());
        itemColors.register((color, items) -> FoliageColors.get(0.5D, 1.0D), UABlocks.RIVER_LEAF_CARPET.get());
    }
}
