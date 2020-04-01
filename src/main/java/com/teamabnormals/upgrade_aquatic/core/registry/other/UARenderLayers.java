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
		RenderTypeLookup.setRenderLayer(UABlocks.DRIFTWOOD_LADDER.get(), CUTOUT);
		
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
		
		RenderTypeLookup.setRenderLayer(UABlocks.ACAN_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.FINGER_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.STAR_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.MOSS_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PETAL_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.BRANCH_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ROCK_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PILLOW_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.SILK_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.CHROME_CORAL.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.ACAN_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.FINGER_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.STAR_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.MOSS_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PETAL_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.BRANCH_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ROCK_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PILLOW_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.SILK_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.CHROME_CORAL_FAN.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.ACAN_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.FINGER_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.STAR_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.MOSS_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PETAL_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.BRANCH_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ROCK_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PILLOW_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.SILK_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.CHROME_CORAL_WALL_FAN.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_ACAN_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_FINGER_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_STAR_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_MOSS_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_PETAL_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_BRANCH_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_ROCK_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_PILLOW_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_SILK_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_CHROME_CORAL.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_ACAN_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_FINGER_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_STAR_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_MOSS_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_PETAL_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_BRANCH_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_ROCK_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_PILLOW_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_SILK_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_CHROME_CORAL_FAN.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_ACAN_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_FINGER_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_STAR_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_MOSS_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_PETAL_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_BRANCH_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_ROCK_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_PILLOW_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_SILK_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DEAD_CHROME_CORAL_WALL_FAN.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.PRISMARINE_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PRISMARINE_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PRISMARINE_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PRISMARINE_CORAL_SHOWER.get(), TRANSLUSCENT);

		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get(), TRANSLUSCENT);

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
				
		RenderTypeLookup.setRenderLayer(UABlocks.OCHRE_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.THORNY_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POLAR_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TONGUE_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.OCHRE_KELP_PLANT.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.THORNY_KELP_PLANT.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POLAR_KELP_PLANT.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TONGUE_KELP_PLANT.get(), CUTOUT);
		
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_PICKERELWEED_BLUE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_PICKERELWEED_PURPLE.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_SEAROCKET_PINK.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_SEAROCKET_WHITE.get(), CUTOUT);
	}
}