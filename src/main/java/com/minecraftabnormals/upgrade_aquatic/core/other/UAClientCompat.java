package com.minecraftabnormals.upgrade_aquatic.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class UAClientCompat {
	private static final RenderType CUTOUT = RenderType.getCutout();
	private static final RenderType CUTOUT_MIPPED = RenderType.getCutoutMipped();
	private static final RenderType TRANSLUSCENT = RenderType.getTranslucent();

	public static void registerClientCompat() {
		registerBlockColors();
		registerRenderLayers();
	}

	public static void registerRenderLayers() {
		RenderTypeLookup.setRenderLayer(UABlocks.GLASS_DOOR.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.GLASS_TRAPDOOR.get(), CUTOUT);

		RenderTypeLookup.setRenderLayer(UABlocks.MULBERRY_JAM_BLOCK.get(), TRANSLUSCENT);

		RenderTypeLookup.setRenderLayer(UABlocks.BEACHGRASS.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TALL_BEACHGRASS.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.MULBERRY_VINE.get(), CUTOUT);

		RenderTypeLookup.setRenderLayer(UABlocks.DRIFTWOOD_LADDER.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.DRIFTWOOD_POST.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.STRIPPED_DRIFTWOOD_POST.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.RIVER_LADDER.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.RIVER_POST.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.STRIPPED_RIVER_POST.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.RIVER_SAPLING.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.RIVER_LEAVES.get(), CUTOUT_MIPPED);
		RenderTypeLookup.setRenderLayer(UABlocks.RIVER_LEAF_CARPET.get(), CUTOUT_MIPPED);
		RenderTypeLookup.setRenderLayer(UABlocks.RIVER_HEDGE.get(), CUTOUT_MIPPED);

		RenderTypeLookup.setRenderLayer(UABlocks.BLUE_PICKERELWEED.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PURPLE_PICKERELWEED.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TALL_BLUE_PICKERELWEED.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TALL_PURPLE_PICKERELWEED.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.PINK_SEAROCKET.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.WHITE_SEAROCKET.get(), CUTOUT);
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
		RenderTypeLookup.setRenderLayer(UABlocks.PRISMARINE_CORAL_SHOWER.get(), CUTOUT);

		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get(), CUTOUT);

		RenderTypeLookup.setRenderLayer(UABlocks.BLUE_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.GREEN_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.RED_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.ORANGE_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.YELLOW_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.PINK_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.PURPLE_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.WHITE_JELLY_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.BLUE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.GREEN_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.RED_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.ORANGE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.YELLOW_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.PINK_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.PURPLE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		RenderTypeLookup.setRenderLayer(UABlocks.WHITE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);

		RenderTypeLookup.setRenderLayer(UABlocks.OCHRE_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.THORNY_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POLAR_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TONGUE_KELP.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.OCHRE_KELP_PLANT.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.THORNY_KELP_PLANT.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POLAR_KELP_PLANT.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.TONGUE_KELP_PLANT.get(), CUTOUT);

		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_PURPLE_PICKERELWEED.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_BLUE_PICKERELWEED.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_PINK_SEAROCKET.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_WHITE_SEAROCKET.get(), CUTOUT);
		RenderTypeLookup.setRenderLayer(UABlocks.POTTED_RIVER_SAPLING.get(), CUTOUT);
	}

	public static void registerBlockColors() {
		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		ItemColors itemColors = Minecraft.getInstance().getItemColors();

		DataUtil.registerBlockColor(blockColors, (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAVES, UABlocks.RIVER_LEAF_CARPET, UABlocks.RIVER_HEDGE, UABlocks.MULBERRY_VINE));
		DataUtil.registerBlockItemColor(itemColors, (color, items) -> FoliageColors.get(0.5D, 1.0D), Arrays.asList(UABlocks.RIVER_LEAVES, UABlocks.RIVER_LEAF_CARPET, UABlocks.RIVER_HEDGE));
	}
}