package com.teamabnormals.upgrade_aquatic.core.other;

import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAItems;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UAClientCompat {
	private static final RenderType CUTOUT = RenderType.cutout();
	private static final RenderType CUTOUT_MIPPED = RenderType.cutoutMipped();
	private static final RenderType TRANSLUSCENT = RenderType.translucent();

	public static void register() {
		UABlocks.setupTabEditors();
		UAItems.setupTabEditors();
		registerRenderLayers();
		registerItemProperties();
	}

	public static void registerRenderLayers() {
		// TODO: Update block model json to include "render_type" instead.
		ItemBlockRenderTypes.setRenderLayer(UABlocks.GLASS_DOOR.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.GLASS_TRAPDOOR.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.MULBERRY_JAM_BLOCK.get(), TRANSLUSCENT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.BEACHGRASS.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.TALL_BEACHGRASS.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.MULBERRY_VINE.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.DRIFTWOOD_LADDER.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.RIVER_LADDER.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.RIVER_SAPLING.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.RIVER_LEAVES.get(), CUTOUT_MIPPED);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.RIVER_LEAF_PILE.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.PICKERELWEED.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.TALL_PICKERELWEED.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PINK_SEAROCKET.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.WHITE_SEAROCKET.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.FLOWERING_RUSH.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.BEACHGRASS_THATCH.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.BEACHGRASS_THATCH_SLAB.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.BEACHGRASS_THATCH_STAIRS.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.ACAN_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.FINGER_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.STAR_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.MOSS_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PETAL_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.BRANCH_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ROCK_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PILLOW_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.SILK_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.CHROME_CORAL.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.ACAN_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.FINGER_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.STAR_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.MOSS_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PETAL_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.BRANCH_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ROCK_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PILLOW_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.SILK_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.CHROME_CORAL_FAN.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.ACAN_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.FINGER_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.STAR_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.MOSS_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PETAL_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.BRANCH_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ROCK_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PILLOW_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.SILK_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.CHROME_CORAL_WALL_FAN.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_ACAN_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_FINGER_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_STAR_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_MOSS_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_PETAL_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_BRANCH_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_ROCK_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_PILLOW_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_SILK_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_CHROME_CORAL.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_ACAN_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_FINGER_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_STAR_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_MOSS_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_PETAL_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_BRANCH_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_ROCK_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_PILLOW_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_SILK_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_CHROME_CORAL_FAN.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_ACAN_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_FINGER_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_STAR_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_MOSS_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_PETAL_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_BRANCH_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_ROCK_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_PILLOW_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_SILK_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.DEAD_CHROME_CORAL_WALL_FAN.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.PRISMARINE_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PRISMARINE_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PRISMARINE_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PRISMARINE_CORAL_SHOWER.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.ELDER_EYE.get(), CUTOUT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.BLUE_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.GREEN_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.RED_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ORANGE_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.YELLOW_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PINK_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PURPLE_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.WHITE_JELLY_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.BLUE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.GREEN_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.RED_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.ORANGE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.YELLOW_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PINK_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.PURPLE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.WHITE_JELLY_WALL_TORCH.get(), TRANSLUSCENT);

		ItemBlockRenderTypes.setRenderLayer(UABlocks.POTTED_PICKERELWEED.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.POTTED_PINK_SEAROCKET.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.POTTED_WHITE_SEAROCKET.get(), CUTOUT);
		ItemBlockRenderTypes.setRenderLayer(UABlocks.POTTED_RIVER_SAPLING.get(), CUTOUT);
	}

	public static void registerItemProperties() {
		ItemProperties.register(UAItems.JELLYFISH_BUCKET.get(), ResourceLocation.withDefaultNamespace("variant"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag();
			if (tag.contains("JellyfishDisplayTag")) {
				return AbstractJellyfish.BucketDisplayInfo.readVariant(tag.getCompound("JellyfishDisplayTag"));
			}
			return 0.0F;
		});

		ItemProperties.register(UAItems.PIKE_BUCKET.get(), ResourceLocation.withDefaultNamespace("variant"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag();
			if (tag.contains("BucketVariantTag", 3)) {
				return tag.getInt("BucketVariantTag");
			}
			return 2;
		});

		ItemProperties.register(Items.AXOLOTL_BUCKET, ResourceLocation.withDefaultNamespace("variant"), (stack, world, entity, num) -> {
			CompoundTag tag = stack.get(DataComponents.BUCKET_ENTITY_DATA).copyTag();
			if (tag.contains("Variant")) {
				return tag.getInt("Variant");
			}
			return 0;
		});
	}

	@SubscribeEvent
	public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.get(0.5D, 1.0D), UABlocks.RIVER_LEAVES.get(), UABlocks.RIVER_LEAF_PILE.get(), UABlocks.MULBERRY_VINE.get());
	}

	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((color, items) -> FoliageColor.get(0.5D, 1.0D), UABlocks.RIVER_LEAVES.get(), UABlocks.RIVER_LEAF_PILE.get());
	}
}