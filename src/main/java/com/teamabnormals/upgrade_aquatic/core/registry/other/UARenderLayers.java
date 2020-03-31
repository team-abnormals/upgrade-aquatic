package com.teamabnormals.upgrade_aquatic.core.registry.other;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class UARenderLayers {
	private static final RenderType CUTOUT = RenderType.getCutout();
	private static final RenderType TRANSLUSCENT = RenderType.getTranslucent();
	
	public static void setBlockRenderLayers() {
		RenderTypeLookup.setRenderLayer(UABlocks.BEACHGRASS.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TALL_BEACHGRASS.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.PICKERELWEED_BLUE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PICKERELWEED_PURPLE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PICKERELWEED_TALL_BLUE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PICKERELWEED_TALL_PURPLE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.SEAROCKET_PINK.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.SEAROCKET_WHITE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.FLOWERING_RUSH.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.BEACHGRASS_THATCH.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.BEACHGRASS_THATCH_SLAB.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.BEACHGRASS_THATCH_STAIRS.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.BEACHGRASS_THATCH_VERTICAL_SLAB.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_BLUE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_GREEN.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_RED.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_ORANGE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_YELLOW.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_PINK.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_PURPLE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WHITE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_BLUE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_GREEN.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_RED.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_ORANGE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_YELLOW.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_PINK.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_PURPLE.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.JELLY_TORCH_WALL_WHITE.get(), TRANSLUSCENT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.PRISMARINE_CORAL_SHOWER.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get(), TRANSLUSCENT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_PICKERELWEED_BLUE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_PICKERELWEED_PURPLE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_SEAROCKET_PINK.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_SEAROCKET_WHITE.get(), CUTOUT);
	}
}