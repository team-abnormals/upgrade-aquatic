package com.teamabnormals.upgrade_aquatic.core.registry.other;

import java.util.Arrays;
import java.util.List;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.RegistryObject;

public class UAColors {
	
	public static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAVES));
		registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAF_CARPET));
		registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.MULBERRY_VINE));
        
		ItemColors itemColors = Minecraft.getInstance().getItemColors();
		registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAVES));
		registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAF_CARPET));
	}
	
	private static void registerBlockColor(BlockColors blockColors, IBlockColor color, List<RegistryObject<Block>> blocksIn) {
		List<RegistryObject<Block>> registryObjects = blocksIn;
		registryObjects.removeIf(block -> !block.isPresent());
		if(registryObjects.size() > 0) {
			Block[] blocks = new Block[registryObjects.size()];
			for(int i = 0; i < registryObjects.size(); i++) {
				blocks[i] = registryObjects.get(i).get();
			}
			blockColors.register(color, blocks);
		}
	}
	
	private static void registerBlockItemColor(ItemColors blockColors, IItemColor color, List<RegistryObject<Block>> blocksIn) {
		List<RegistryObject<Block>> registryObjects = blocksIn;
		registryObjects.removeIf(block -> !block.isPresent());
		if(registryObjects.size() > 0) {
			Block[] blocks = new Block[registryObjects.size()];
			for(int i = 0; i < registryObjects.size(); i++) {
				blocks[i] = registryObjects.get(i).get();
			}
			blockColors.register(color, blocks);
		}
	}
	
}