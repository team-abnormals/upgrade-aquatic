package com.teamabnormals.upgrade_aquatic.core.registry;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.common.block.BlueprintBeehiveBlock;
import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.common.block.LeafPileBlock;
import com.teamabnormals.blueprint.common.block.LogBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintCeilingHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallHangingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchSlabBlock;
import com.teamabnormals.blueprint.common.block.thatch.ThatchStairBlock;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.block.*;
import com.teamabnormals.upgrade_aquatic.common.block.coralstone.CoralstoneBlock;
import com.teamabnormals.upgrade_aquatic.common.block.coralstone.CoralstoneSlabBlock;
import com.teamabnormals.upgrade_aquatic.common.block.coralstone.CoralstoneStairsBlock;
import com.teamabnormals.upgrade_aquatic.common.block.coralstone.CoralstoneWallBlock;
import com.teamabnormals.upgrade_aquatic.common.block.grower.RiverTreeGrower;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.UAConstants;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UABlocks {
	public static final BlockSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> EMBEDDED_AMMONITE = HELPER.createBlock("embedded_ammonite", () -> new EmbeddedAmmoniteBlock(Properties.copy(Blocks.STONE)));

	public static final RegistryObject<Block> WHITE_SEAROCKET = HELPER.createBlock("white_searocket", () -> new SearocketBlock(() -> MobEffects.WATER_BREATHING, 9, PropertyUtil.flower()));
	public static final RegistryObject<Block> PINK_SEAROCKET = HELPER.createBlock("pink_searocket", () -> new SearocketBlock(() -> MobEffects.WATER_BREATHING, 9, PropertyUtil.flower()));
	public static final RegistryObject<Block> FLOWERING_RUSH = HELPER.createBlock("flowering_rush", () -> new FloweringRushBlock(Properties.copy(Blocks.PEONY).sound(SoundType.WET_GRASS)));

	public static final RegistryObject<Block> PICKERELWEED = HELPER.createBlock("pickerelweed", () -> new PickerelweedPlantBlock(UAProperties.PICKERELWEED));
	public static final RegistryObject<Block> TALL_PICKERELWEED = HELPER.createBlockNoItem("tall_pickerelweed", () -> new PickerelweedDoublePlantBlock(UAProperties.PICKERELWEED));
	public static final RegistryObject<Block> PICKERELWEED_BLOCK = HELPER.createBlock("pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(false), false));
	public static final RegistryObject<Block> BOILED_PICKERELWEED_BLOCK = HELPER.createBlock("boiled_pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(true), true));

	public static final RegistryObject<Block> POTTED_WHITE_SEAROCKET = HELPER.createBlockNoItem("potted_white_searocket", () -> new FlowerPotBlock(WHITE_SEAROCKET.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_PINK_SEAROCKET = HELPER.createBlockNoItem("potted_pink_searocket", () -> new FlowerPotBlock(PINK_SEAROCKET.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> POTTED_PICKERELWEED = HELPER.createBlockNoItem("potted_pickerelweed", () -> new FlowerPotBlock(PICKERELWEED.get(), PropertyUtil.flowerPot()));

	public static final RegistryObject<Block> BEACHGRASS = HELPER.createBlock("beachgrass", () -> new BeachgrassBlock(Properties.copy(Blocks.FERN)));
	public static final RegistryObject<Block> TALL_BEACHGRASS = HELPER.createBlock("tall_beachgrass", () -> new TallBeachgrassBlock(Properties.copy(Blocks.LARGE_FERN)));
	public static final RegistryObject<Block> BEACHGRASS_THATCH = HELPER.createBlock("beachgrass_thatch", () -> new ThatchBlock(UAProperties.BEACHGRASS_THATCH));
	public static final RegistryObject<Block> BEACHGRASS_THATCH_SLAB = HELPER.createBlock("beachgrass_thatch_slab", () -> new ThatchSlabBlock(UAProperties.BEACHGRASS_THATCH));
	public static final RegistryObject<Block> BEACHGRASS_THATCH_STAIRS = HELPER.createBlock("beachgrass_thatch_stairs", () -> new ThatchStairBlock(BEACHGRASS_THATCH.get().defaultBlockState(), UAProperties.BEACHGRASS_THATCH));

	public static final RegistryObject<Block> MULBERRY_VINE = HELPER.createBlockNoItem("mulberry_vine", () -> new MulberryVineBlock(Block.Properties.of().randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final RegistryObject<Block> MULBERRY_JAM_BLOCK = HELPER.createBlock("mulberry_jam_block", () -> new MulberryJamBlock(Block.Properties.copy(Blocks.SLIME_BLOCK)));
	public static final RegistryObject<Block> MULBERRY_PUNNET = HELPER.createBlock("mulberry_punnet", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD)));

	public static final RegistryObject<Block> PRISMARINE_ROD_BUNDLE = HELPER.createBlock("prismarine_rod_bundle", () -> new PrismarineRodBlock(Properties.copy(Blocks.PRISMARINE_BRICKS).sound(SoundType.METAL)));
	public static final RegistryObject<Block> LUMINOUS_PRISMARINE = HELPER.createBlock("luminous_prismarine", () -> new ConduitFrameBlock(UAProperties.LUMINOUS_PRISMARINE));
	public static final RegistryObject<Block> LUMINOUS_PRISMARINE_STAIRS = HELPER.createBlock("luminous_prismarine_stairs", () -> new StairBlock(() -> LUMINOUS_PRISMARINE.get().defaultBlockState(), UAProperties.LUMINOUS_PRISMARINE));
	public static final RegistryObject<Block> LUMINOUS_PRISMARINE_SLAB = HELPER.createBlock("luminous_prismarine_slab", () -> new SlabBlock(UAProperties.LUMINOUS_PRISMARINE));

	public static final RegistryObject<Block> GLASS_TRAPDOOR = HELPER.createBlock("glass_trapdoor", () -> new TrapDoorBlock(Properties.copy(Blocks.GLASS), UAProperties.GLASS_BLOCK_SET));
	public static final RegistryObject<Block> GLASS_DOOR = HELPER.createBlock("glass_door", () -> new DoorBlock(Properties.copy(Blocks.GLASS), UAProperties.GLASS_BLOCK_SET));

	public static final RegistryObject<Block> BEDROLL = HELPER.createBlock("bedroll", createBedroll(DyeColor.BROWN));
	public static final RegistryObject<Block> WHITE_BEDROLL = HELPER.createBlock("white_bedroll", createBedroll(DyeColor.WHITE));
	public static final RegistryObject<Block> ORANGE_BEDROLL = HELPER.createBlock("orange_bedroll", createBedroll(DyeColor.ORANGE));
	public static final RegistryObject<Block> MAGENTA_BEDROLL = HELPER.createBlock("magenta_bedroll", createBedroll(DyeColor.MAGENTA));
	public static final RegistryObject<Block> LIGHT_BLUE_BEDROLL = HELPER.createBlock("light_blue_bedroll", createBedroll(DyeColor.LIGHT_BLUE));
	public static final RegistryObject<Block> YELLOW_BEDROLL = HELPER.createBlock("yellow_bedroll", createBedroll(DyeColor.YELLOW));
	public static final RegistryObject<Block> LIME_BEDROLL = HELPER.createBlock("lime_bedroll", createBedroll(DyeColor.LIME));
	public static final RegistryObject<Block> PINK_BEDROLL = HELPER.createBlock("pink_bedroll", createBedroll(DyeColor.PINK));
	public static final RegistryObject<Block> GRAY_BEDROLL = HELPER.createBlock("gray_bedroll", createBedroll(DyeColor.GRAY));
	public static final RegistryObject<Block> LIGHT_GRAY_BEDROLL = HELPER.createBlock("light_gray_bedroll", createBedroll(DyeColor.LIGHT_GRAY));
	public static final RegistryObject<Block> CYAN_BEDROLL = HELPER.createBlock("cyan_bedroll", createBedroll(DyeColor.CYAN));
	public static final RegistryObject<Block> PURPLE_BEDROLL = HELPER.createBlock("purple_bedroll", createBedroll(DyeColor.PURPLE));
	public static final RegistryObject<Block> BLUE_BEDROLL = HELPER.createBlock("blue_bedroll", createBedroll(DyeColor.BLUE));
	public static final RegistryObject<Block> BROWN_BEDROLL = HELPER.createBlock("brown_bedroll", createBedroll(DyeColor.BROWN));
	public static final RegistryObject<Block> GREEN_BEDROLL = HELPER.createBlock("green_bedroll", createBedroll(DyeColor.GREEN));
	public static final RegistryObject<Block> RED_BEDROLL = HELPER.createBlock("red_bedroll", createBedroll(DyeColor.RED));
	public static final RegistryObject<Block> BLACK_BEDROLL = HELPER.createBlock("black_bedroll", createBedroll(DyeColor.BLACK));

	public static final RegistryObject<Block> TOOTH_BLOCK = HELPER.createBlock("tooth_block", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_TILES = HELPER.createBlock("tooth_tiles", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_STAIRS = HELPER.createBlock("tooth_stairs", () -> new StairBlock(() -> TOOTH_BLOCK.get().defaultBlockState(), Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_SLAB = HELPER.createBlock("tooth_slab", () -> new SlabBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_WALL = HELPER.createBlock("tooth_wall", () -> new WallBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_BRICKS = HELPER.createBlock("tooth_bricks", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> CHISELED_TOOTH_BRICKS = HELPER.createBlock("chiseled_tooth_bricks", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_BRICK_STAIRS = HELPER.createBlock("tooth_brick_stairs", () -> new StairBlock(() -> TOOTH_BLOCK.get().defaultBlockState(), Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_BRICK_SLAB = HELPER.createBlock("tooth_brick_slab", () -> new SlabBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_BRICK_WALL = HELPER.createBlock("tooth_brick_wall", () -> new WallBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_TRAPDOOR = HELPER.createBlock("tooth_trapdoor", () -> new ToothTrapdoorBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_DOOR = HELPER.createBlock("tooth_door", () -> new ToothDoorBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> TOOTH_LANTERN = HELPER.createBlock("tooth_lantern", () -> new ToothLanternBlock(Properties.copy(Blocks.END_STONE).sound(UASoundEvents.TOOTH_LANTERN).noOcclusion().lightLevel((unknown) -> 15)));

	public static final RegistryObject<Block> SCUTE_BLOCK = HELPER.createBlock("scute_block", () -> new ScuteBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_SHINGLES = HELPER.createBlock("scute_shingles", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_SHINGLE_STAIRS = HELPER.createBlock("scute_shingle_stairs", () -> new StairBlock(() -> SCUTE_BLOCK.get().defaultBlockState(), Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_SHINGLE_SLAB = HELPER.createBlock("scute_shingle_slab", () -> new SlabBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_SHINGLE_WALL = HELPER.createBlock("scute_shingle_wall", () -> new WallBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> CHISELED_SCUTE_SHINGLES = HELPER.createBlock("chiseled_scute_shingles", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_PAVEMENT = HELPER.createBlock("scute_pavement", () -> new Block(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_PAVEMENT_STAIRS = HELPER.createBlock("scute_pavement_stairs", () -> new StairBlock(() -> SCUTE_BLOCK.get().defaultBlockState(), Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_PAVEMENT_SLAB = HELPER.createBlock("scute_pavement_slab", () -> new SlabBlock(Properties.copy(Blocks.END_STONE)));
	public static final RegistryObject<Block> SCUTE_PAVEMENT_WALL = HELPER.createBlock("scute_pavement_wall", () -> new WallBlock(Properties.copy(Blocks.END_STONE)));

	public static final RegistryObject<Block> DEAD_ACAN_CORAL_BLOCK = HELPER.createBlock("dead_acan_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_FINGER_CORAL_BLOCK = HELPER.createBlock("dead_finger_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_STAR_CORAL_BLOCK = HELPER.createBlock("dead_star_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_MOSS_CORAL_BLOCK = HELPER.createBlock("dead_moss_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_PETAL_CORAL_BLOCK = HELPER.createBlock("dead_petal_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL_BLOCK = HELPER.createBlock("dead_branch_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_ROCK_CORAL_BLOCK = HELPER.createBlock("dead_rock_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL_BLOCK = HELPER.createBlock("dead_pillow_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_SILK_CORAL_BLOCK = HELPER.createBlock("dead_silk_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> DEAD_CHROME_CORAL_BLOCK = HELPER.createBlock("dead_chrome_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_BLOCK = HELPER.createBlock("elder_prismarine_coral_block", () -> new ConduitFrameBlock(UAProperties.createPrismarineCoralBlock(true)));

	public static final RegistryObject<Block> ACAN_CORAL_BLOCK = HELPER.createBlock("acan_coral_block", () -> new CoralBlock(DEAD_ACAN_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_CYAN)));
	public static final RegistryObject<Block> FINGER_CORAL_BLOCK = HELPER.createBlock("finger_coral_block", () -> new CoralBlock(DEAD_FINGER_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_ORANGE)));
	public static final RegistryObject<Block> STAR_CORAL_BLOCK = HELPER.createBlock("star_coral_block", () -> new CoralBlock(DEAD_STAR_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> MOSS_CORAL_BLOCK = HELPER.createBlock("moss_coral_block", () -> new CoralBlock(DEAD_MOSS_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_GREEN)));
	public static final RegistryObject<Block> PETAL_CORAL_BLOCK = HELPER.createBlock("petal_coral_block", () -> new CoralBlock(DEAD_PETAL_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_LIGHT_BLUE)));
	public static final RegistryObject<Block> BRANCH_CORAL_BLOCK = HELPER.createBlock("branch_coral_block", () -> new CoralBlock(DEAD_BRANCH_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_GRAY)));
	public static final RegistryObject<Block> ROCK_CORAL_BLOCK = HELPER.createBlock("rock_coral_block", () -> new CoralBlock(DEAD_ROCK_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_BROWN)));
	public static final RegistryObject<Block> PILLOW_CORAL_BLOCK = HELPER.createBlock("pillow_coral_block", () -> new CoralBlock(DEAD_PILLOW_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_WHITE)));
	public static final RegistryObject<Block> SILK_CORAL_BLOCK = HELPER.createBlock("silk_coral_block", () -> new CoralBlock(DEAD_SILK_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_PURPLE)));
	public static final RegistryObject<Block> CHROME_CORAL_BLOCK = HELPER.createBlock("chrome_coral_block", () -> new CoralBlock(DEAD_CHROME_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_GRAY)));
	public static final RegistryObject<Block> PRISMARINE_CORAL_BLOCK = HELPER.createBlock("prismarine_coral_block", () -> new PrismarineCoralBlock(ELDER_PRISMARINE_CORAL_BLOCK.get(), UAProperties.createPrismarineCoralBlock(false)));

	public static final RegistryObject<Block> DEAD_ACAN_CORAL = HELPER.createBlock("dead_acan_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_FINGER_CORAL = HELPER.createBlock("dead_finger_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_STAR_CORAL = HELPER.createBlock("dead_star_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_MOSS_CORAL = HELPER.createBlock("dead_moss_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_PETAL_CORAL = HELPER.createBlock("dead_petal_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL = HELPER.createBlock("dead_branch_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_ROCK_CORAL = HELPER.createBlock("dead_rock_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL = HELPER.createBlock("dead_pillow_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_SILK_CORAL = HELPER.createBlock("dead_silk_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_CHROME_CORAL = HELPER.createBlock("dead_chrome_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL = HELPER.createBlock("elder_prismarine_coral", () -> new BaseCoralPlantBlock(UAProperties.createPrismarineCoral(true)));

	public static final RegistryObject<Block> ACAN_CORAL = HELPER.createBlock("acan_coral", () -> new CoralPlantBlock(DEAD_ACAN_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_CYAN)));
	public static final RegistryObject<Block> FINGER_CORAL = HELPER.createBlock("finger_coral", () -> new CoralPlantBlock(DEAD_FINGER_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_ORANGE)));
	public static final RegistryObject<Block> STAR_CORAL = HELPER.createBlock("star_coral", () -> new CoralPlantBlock(DEAD_STAR_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> MOSS_CORAL = HELPER.createBlock("moss_coral", () -> new CoralPlantBlock(DEAD_MOSS_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_GREEN)));
	public static final RegistryObject<Block> PETAL_CORAL = HELPER.createBlock("petal_coral", () -> new CoralPlantBlock(DEAD_PETAL_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_BLUE)));
	public static final RegistryObject<Block> BRANCH_CORAL = HELPER.createBlock("branch_coral", () -> new CoralPlantBlock(DEAD_BRANCH_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final RegistryObject<Block> ROCK_CORAL = HELPER.createBlock("rock_coral", () -> new CoralPlantBlock(DEAD_ROCK_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_BROWN)));
	public static final RegistryObject<Block> PILLOW_CORAL = HELPER.createBlock("pillow_coral", () -> new CoralPlantBlock(DEAD_PILLOW_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_WHITE)));
	public static final RegistryObject<Block> SILK_CORAL = HELPER.createBlock("silk_coral", () -> new CoralPlantBlock(DEAD_SILK_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_PURPLE)));
	public static final RegistryObject<Block> CHROME_CORAL = HELPER.createBlock("chrome_coral", () -> new CoralPlantBlock(DEAD_CHROME_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final RegistryObject<Block> PRISMARINE_CORAL = HELPER.createBlock("prismarine_coral", () -> new CoralPlantBlock(ELDER_PRISMARINE_CORAL.get(), UAProperties.createPrismarineCoral(false)));

	public static final RegistryObject<Block> DEAD_ACAN_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_acan_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_FINGER_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_finger_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_STAR_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_star_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_MOSS_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_moss_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_PETAL_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_petal_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_branch_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_ROCK_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_rock_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_pillow_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_SILK_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_silk_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_CHROME_CORAL_WALL_FAN = HELPER.createBlockNoItem("dead_chrome_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_WALL_FAN = HELPER.createBlockNoItem("elder_prismarine_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.createPrismarineCoral(true)));

	public static final RegistryObject<Block> ACAN_CORAL_WALL_FAN = HELPER.createBlockNoItem("acan_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_ACAN_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_CYAN)));
	public static final RegistryObject<Block> FINGER_CORAL_WALL_FAN = HELPER.createBlockNoItem("finger_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_FINGER_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_ORANGE)));
	public static final RegistryObject<Block> STAR_CORAL_WALL_FAN = HELPER.createBlockNoItem("star_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_STAR_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_GREEN)));
	public static final RegistryObject<Block> MOSS_CORAL_WALL_FAN = HELPER.createBlockNoItem("moss_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_MOSS_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_GREEN)));
	public static final RegistryObject<Block> PETAL_CORAL_WALL_FAN = HELPER.createBlockNoItem("petal_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_PETAL_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_BLUE)));
	public static final RegistryObject<Block> BRANCH_CORAL_WALL_FAN = HELPER.createBlockNoItem("branch_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_BRANCH_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final RegistryObject<Block> ROCK_CORAL_WALL_FAN = HELPER.createBlockNoItem("rock_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_ROCK_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_BROWN)));
	public static final RegistryObject<Block> PILLOW_CORAL_WALL_FAN = HELPER.createBlockNoItem("pillow_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_PILLOW_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_WHITE)));
	public static final RegistryObject<Block> SILK_CORAL_WALL_FAN = HELPER.createBlockNoItem("silk_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_SILK_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_PURPLE)));
	public static final RegistryObject<Block> CHROME_CORAL_WALL_FAN = HELPER.createBlockNoItem("chrome_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_CHROME_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final RegistryObject<Block> PRISMARINE_CORAL_WALL_FAN = HELPER.createBlockNoItem("prismarine_coral_wall_fan", () -> new CoralWallFanBlock(ELDER_PRISMARINE_CORAL_WALL_FAN.get(), UAProperties.createPrismarineCoral(false)));

	public static final RegistryObject<Block> DEAD_ACAN_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_acan_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_ACAN_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_FINGER_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_finger_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_FINGER_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_STAR_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_star_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_STAR_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_MOSS_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_moss_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_MOSS_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_PETAL_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_petal_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_PETAL_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_branch_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_BRANCH_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_ROCK_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_rock_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_ROCK_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_pillow_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_PILLOW_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_SILK_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_silk_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_SILK_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> DEAD_CHROME_CORAL_FAN = HELPER.createStandingAndWallBlock("dead_chrome_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_CHROME_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_FAN = HELPER.createStandingAndWallBlock("elder_prismarine_coral_fan", () -> new BaseCoralFanBlock(UAProperties.createPrismarineCoral(true)), ELDER_PRISMARINE_CORAL_WALL_FAN, Direction.DOWN);

	public static final RegistryObject<Block> ACAN_CORAL_FAN = HELPER.createStandingAndWallBlock("acan_coral_fan", () -> new CoralFanBlock(DEAD_ACAN_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_CYAN)), ACAN_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> FINGER_CORAL_FAN = HELPER.createStandingAndWallBlock("finger_coral_fan", () -> new CoralFanBlock(DEAD_FINGER_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_ORANGE)), FINGER_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> STAR_CORAL_FAN = HELPER.createStandingAndWallBlock("star_coral_fan", () -> new CoralFanBlock(DEAD_STAR_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_GREEN)), STAR_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> MOSS_CORAL_FAN = HELPER.createStandingAndWallBlock("moss_coral_fan", () -> new CoralFanBlock(DEAD_MOSS_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_GREEN)), MOSS_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> PETAL_CORAL_FAN = HELPER.createStandingAndWallBlock("petal_coral_fan", () -> new CoralFanBlock(DEAD_PETAL_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_BLUE)), PETAL_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> BRANCH_CORAL_FAN = HELPER.createStandingAndWallBlock("branch_coral_fan", () -> new CoralFanBlock(DEAD_BRANCH_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)), BRANCH_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> ROCK_CORAL_FAN = HELPER.createStandingAndWallBlock("rock_coral_fan", () -> new CoralFanBlock(DEAD_ROCK_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_BROWN)), ROCK_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> PILLOW_CORAL_FAN = HELPER.createStandingAndWallBlock("pillow_coral_fan", () -> new CoralFanBlock(DEAD_PILLOW_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_WHITE)), PILLOW_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> SILK_CORAL_FAN = HELPER.createStandingAndWallBlock("silk_coral_fan", () -> new CoralFanBlock(DEAD_SILK_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_PURPLE)), SILK_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> CHROME_CORAL_FAN = HELPER.createStandingAndWallBlock("chrome_coral_fan", () -> new CoralFanBlock(DEAD_CHROME_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)), CHROME_CORAL_WALL_FAN, Direction.DOWN);
	public static final RegistryObject<Block> PRISMARINE_CORAL_FAN = HELPER.createStandingAndWallBlock("prismarine_coral_fan", () -> new CoralFanBlock(ELDER_PRISMARINE_CORAL_FAN.get(), UAProperties.createPrismarineCoral(false)), PRISMARINE_CORAL_WALL_FAN, Direction.DOWN);

	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_SHOWER = HELPER.createBlock("elder_prismarine_coral_shower", () -> new DeadCoralShowerBlock(UAProperties.createPrismarineCoral(true)));
	public static final RegistryObject<Block> PRISMARINE_CORAL_SHOWER = HELPER.createBlock("prismarine_coral_shower", () -> new CoralShowerBlock(ELDER_PRISMARINE_CORAL_SHOWER.get(), UAProperties.createPrismarineCoral(false)));

	public static final RegistryObject<Block> ELDER_EYE = HELPER.createRareBlock("elder_eye", () -> new ElderEyeBlock(UAProperties.ELDER_EYE), Rarity.RARE);

	public static final RegistryObject<Block> PINK_JELLY_WALL_TORCH = HELPER.createBlockNoItem("pink_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PINK));
	public static final RegistryObject<Block> PURPLE_JELLY_WALL_TORCH = HELPER.createBlockNoItem("purple_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PURPLE));
	public static final RegistryObject<Block> BLUE_JELLY_WALL_TORCH = HELPER.createBlockNoItem("blue_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.BLUE));
	public static final RegistryObject<Block> GREEN_JELLY_WALL_TORCH = HELPER.createBlockNoItem("green_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.GREEN));
	public static final RegistryObject<Block> YELLOW_JELLY_WALL_TORCH = HELPER.createBlockNoItem("yellow_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.YELLOW));
	public static final RegistryObject<Block> ORANGE_JELLY_WALL_TORCH = HELPER.createBlockNoItem("orange_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.ORANGE));
	public static final RegistryObject<Block> RED_JELLY_WALL_TORCH = HELPER.createBlockNoItem("red_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.RED));
	public static final RegistryObject<Block> WHITE_JELLY_WALL_TORCH = HELPER.createBlockNoItem("white_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.WHITE));

	public static final RegistryObject<Block> PINK_JELLY_TORCH = HELPER.createStandingAndWallBlock("pink_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PINK), PINK_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> PURPLE_JELLY_TORCH = HELPER.createStandingAndWallBlock("purple_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PURPLE), PURPLE_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> BLUE_JELLY_TORCH = HELPER.createStandingAndWallBlock("blue_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.BLUE), BLUE_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> GREEN_JELLY_TORCH = HELPER.createStandingAndWallBlock("green_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.GREEN), GREEN_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> YELLOW_JELLY_TORCH = HELPER.createStandingAndWallBlock("yellow_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.YELLOW), YELLOW_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> ORANGE_JELLY_TORCH = HELPER.createStandingAndWallBlock("orange_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.ORANGE), ORANGE_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> RED_JELLY_TORCH = HELPER.createStandingAndWallBlock("red_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.RED), RED_JELLY_WALL_TORCH, Direction.DOWN);
	public static final RegistryObject<Block> WHITE_JELLY_TORCH = HELPER.createStandingAndWallBlock("white_jelly_torch", () -> new JellyTorchBlock(Properties.copy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.WHITE), WHITE_JELLY_WALL_TORCH, Direction.DOWN);

	public static final RegistryObject<Block> CORALSTONE = HELPER.createBlock("coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false));
	public static final RegistryObject<Block> BUBBLE_CORALSTONE = HELPER.createBlock("bubble_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> HORN_CORALSTONE = HELPER.createBlock("horn_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> TUBE_CORALSTONE = HELPER.createBlock("tube_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> BRAIN_CORALSTONE = HELPER.createBlock("brain_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> FIRE_CORALSTONE = HELPER.createBlock("fire_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> ACAN_CORALSTONE = HELPER.createBlock("acan_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> FINGER_CORALSTONE = HELPER.createBlock("finger_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> STAR_CORALSTONE = HELPER.createBlock("star_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> MOSS_CORALSTONE = HELPER.createBlock("moss_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PETAL_CORALSTONE = HELPER.createBlock("petal_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> BRANCH_CORALSTONE = HELPER.createBlock("branch_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> ROCK_CORALSTONE = HELPER.createBlock("rock_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PILLOW_CORALSTONE = HELPER.createBlock("pillow_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> SILK_CORALSTONE = HELPER.createBlock("silk_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> CHROME_CORALSTONE = HELPER.createBlock("chrome_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE = HELPER.createBlock("prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE = HELPER.createBlock("elder_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false));
	public static final RegistryObject<Block> DEAD_CORALSTONE = HELPER.createBlock("dead_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false));

	public static final RegistryObject<Block> CHISELED_CORALSTONE = HELPER.createBlock("chiseled_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true));
	public static final RegistryObject<Block> BUBBLE_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_bubble_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> HORN_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_horn_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> TUBE_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_tube_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> BRAIN_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_brain_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> FIRE_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_fire_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> ACAN_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_acan_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> FINGER_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_finger_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> STAR_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_star_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> MOSS_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_moss_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PETAL_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_petal_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> BRANCH_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_branch_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> ROCK_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_rock_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PILLOW_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_pillow_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> SILK_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_silk_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> CHROME_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_chrome_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PRISMARINE_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final RegistryObject<Block> CHISELED_ELDER_PRISMARINE_CORALSTONE = HELPER.createBlock("chiseled_elder_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true));
	public static final RegistryObject<Block> DEAD_CHISELED_CORALSTONE = HELPER.createBlock("chiseled_dead_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true));

	public static final RegistryObject<Block> CORALSTONE_SLAB = HELPER.createBlock("coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null));
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_SLAB = HELPER.createBlock("bubble_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> HORN_CORALSTONE_SLAB = HELPER.createBlock("horn_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> TUBE_CORALSTONE_SLAB = HELPER.createBlock("tube_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> BRAIN_CORALSTONE_SLAB = HELPER.createBlock("brain_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> FIRE_CORALSTONE_SLAB = HELPER.createBlock("fire_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> ACAN_CORALSTONE_SLAB = HELPER.createBlock("acan_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> FINGER_CORALSTONE_SLAB = HELPER.createBlock("finger_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> STAR_CORALSTONE_SLAB = HELPER.createBlock("star_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> MOSS_CORALSTONE_SLAB = HELPER.createBlock("moss_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PETAL_CORALSTONE_SLAB = HELPER.createBlock("petal_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> BRANCH_CORALSTONE_SLAB = HELPER.createBlock("branch_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> ROCK_CORALSTONE_SLAB = HELPER.createBlock("rock_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PILLOW_CORALSTONE_SLAB = HELPER.createBlock("pillow_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> SILK_CORALSTONE_SLAB = HELPER.createBlock("silk_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> CHROME_CORALSTONE_SLAB = HELPER.createBlock("chrome_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_SLAB = HELPER.createBlock("prismarine_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_SLAB = HELPER.createBlock("elder_prismarine_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null));
	public static final RegistryObject<Block> DEAD_CORALSTONE_SLAB = HELPER.createBlock("dead_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null));

	public static final RegistryObject<Block> CORALSTONE_STAIRS = HELPER.createBlock("coralstone_stairs", () -> new CoralstoneStairsBlock(() -> CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, null));
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_STAIRS = HELPER.createBlock("bubble_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BUBBLE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> HORN_CORALSTONE_STAIRS = HELPER.createBlock("horn_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> HORN_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> TUBE_CORALSTONE_STAIRS = HELPER.createBlock("tube_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> TUBE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> BRAIN_CORALSTONE_STAIRS = HELPER.createBlock("brain_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BRAIN_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> FIRE_CORALSTONE_STAIRS = HELPER.createBlock("fire_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> FIRE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final RegistryObject<Block> ACAN_CORALSTONE_STAIRS = HELPER.createBlock("acan_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ACAN_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> FINGER_CORALSTONE_STAIRS = HELPER.createBlock("finger_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> FINGER_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> STAR_CORALSTONE_STAIRS = HELPER.createBlock("star_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> STAR_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> MOSS_CORALSTONE_STAIRS = HELPER.createBlock("moss_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> MOSS_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PETAL_CORALSTONE_STAIRS = HELPER.createBlock("petal_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PETAL_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> BRANCH_CORALSTONE_STAIRS = HELPER.createBlock("branch_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BRANCH_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> ROCK_CORALSTONE_STAIRS = HELPER.createBlock("rock_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ROCK_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PILLOW_CORALSTONE_STAIRS = HELPER.createBlock("pillow_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PILLOW_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> SILK_CORALSTONE_STAIRS = HELPER.createBlock("silk_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> SILK_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> CHROME_CORALSTONE_STAIRS = HELPER.createBlock("chrome_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> CHROME_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_STAIRS = HELPER.createBlock("prismarine_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PRISMARINE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_STAIRS = HELPER.createBlock("elder_prismarine_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ELDER_PRISMARINE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, null));
	public static final RegistryObject<Block> DEAD_CORALSTONE_STAIRS = HELPER.createBlock("dead_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> DEAD_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, null));

	public static final RegistryObject<Block> CORALSTONE_WALL = HELPER.createBlock("coralstone_wall", () -> new CoralstoneWallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_WALL = HELPER.createBlock("bubble_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> HORN_CORALSTONE_WALL = HELPER.createBlock("horn_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> TUBE_CORALSTONE_WALL = HELPER.createBlock("tube_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> BRAIN_CORALSTONE_WALL = HELPER.createBlock("brain_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> FIRE_CORALSTONE_WALL = HELPER.createBlock("fire_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> ACAN_CORALSTONE_WALL = HELPER.createBlock("acan_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> FINGER_CORALSTONE_WALL = HELPER.createBlock("finger_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> STAR_CORALSTONE_WALL = HELPER.createBlock("star_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> MOSS_CORALSTONE_WALL = HELPER.createBlock("moss_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> PETAL_CORALSTONE_WALL = HELPER.createBlock("petal_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> BRANCH_CORALSTONE_WALL = HELPER.createBlock("branch_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> ROCK_CORALSTONE_WALL = HELPER.createBlock("rock_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> PILLOW_CORALSTONE_WALL = HELPER.createBlock("pillow_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> SILK_CORALSTONE_WALL = HELPER.createBlock("silk_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> CHROME_CORALSTONE_WALL = HELPER.createBlock("chrome_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_WALL = HELPER.createBlock("prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_WALL = HELPER.createBlock("elder_prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final RegistryObject<Block> DEAD_CORALSTONE_WALL = HELPER.createBlock("dead_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));

	public static final RegistryObject<Block> KELP_BLOCK = HELPER.createBlock("kelp_block", () -> new Block(Properties.copy(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)));

	public static final RegistryObject<Block> KELPY_COBBLESTONE = HELPER.createBlock("kelpy_cobblestone", () -> new Block(Properties.copy(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> KELPY_COBBLESTONE_STAIRS = HELPER.createBlock("kelpy_cobblestone_stairs", () -> new StairBlock(() -> KELPY_COBBLESTONE.get().defaultBlockState(), Properties.copy(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> KELPY_COBBLESTONE_SLAB = HELPER.createBlock("kelpy_cobblestone_slab", () -> new SlabBlock(Properties.copy(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> KELPY_COBBLESTONE_WALL = HELPER.createBlock("kelpy_cobblestone_wall", () -> new WallBlock(Properties.copy(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> KELPY_STONE_BRICKS = HELPER.createBlock("kelpy_stone_bricks", () -> new Block(Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> KELPY_STONE_BRICK_STAIRS = HELPER.createBlock("kelpy_stone_brick_stairs", () -> new StairBlock(() -> KELPY_STONE_BRICKS.get().defaultBlockState(), Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> KELPY_STONE_BRICK_SLAB = HELPER.createBlock("kelpy_stone_brick_slab", () -> new SlabBlock(Properties.copy(Blocks.STONE_BRICKS)));
	public static final RegistryObject<Block> KELPY_STONE_BRICK_WALL = HELPER.createBlock("kelpy_stone_brick_wall", () -> new WallBlock(Properties.copy(Blocks.STONE_BRICKS)));

	public static final RegistryObject<Block> STRIPPED_DRIFTWOOD_LOG = HELPER.createBlock("stripped_driftwood_log", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD.log()));
	public static final RegistryObject<Block> STRIPPED_DRIFTWOOD = HELPER.createBlock("stripped_driftwood", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD.log()));
	public static final RegistryObject<Block> DRIFTWOOD_LOG = HELPER.createBlock("driftwood_log", () -> new LogBlock(STRIPPED_DRIFTWOOD_LOG, UAProperties.DRIFTWOOD.log()));
	public static final RegistryObject<Block> DRIFTWOOD = HELPER.createBlock("driftwood", () -> new LogBlock(STRIPPED_DRIFTWOOD, UAProperties.DRIFTWOOD.log()));
	public static final RegistryObject<Block> DRIFTWOOD_PLANKS = HELPER.createBlock("driftwood_planks", () -> new Block(UAProperties.DRIFTWOOD.planks()));
	public static final RegistryObject<Block> DRIFTWOOD_STAIRS = HELPER.createBlock("driftwood_stairs", () -> new StairBlock(() -> DRIFTWOOD_PLANKS.get().defaultBlockState(), UAProperties.DRIFTWOOD.planks()));
	public static final RegistryObject<Block> DRIFTWOOD_SLAB = HELPER.createBlock("driftwood_slab", () -> new SlabBlock(UAProperties.DRIFTWOOD.planks()));
	public static final RegistryObject<Block> DRIFTWOOD_PRESSURE_PLATE = HELPER.createBlock("driftwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.DRIFTWOOD.pressurePlate(), UAProperties.DRIFTWOOD_BLOCK_SET));
	public static final RegistryObject<Block> DRIFTWOOD_BUTTON = HELPER.createBlock("driftwood_button", () -> new ButtonBlock(UAProperties.DRIFTWOOD.button(), UAProperties.DRIFTWOOD_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> DRIFTWOOD_FENCE = HELPER.createFuelBlock("driftwood_fence", () -> new FenceBlock(UAProperties.DRIFTWOOD.planks()), 300);
	public static final RegistryObject<Block> DRIFTWOOD_FENCE_GATE = HELPER.createFuelBlock("driftwood_fence_gate", () -> new FenceGateBlock(UAProperties.DRIFTWOOD.planks(), UAProperties.DRIFTWOOD_WOOD_TYPE), 300);
	public static final RegistryObject<Block> DRIFTWOOD_DOOR = HELPER.createBlock("driftwood_door", () -> new DoorBlock(UAProperties.DRIFTWOOD.door(), UAProperties.DRIFTWOOD_BLOCK_SET));
	public static final RegistryObject<Block> DRIFTWOOD_TRAPDOOR = HELPER.createBlock("driftwood_trapdoor", () -> new TrapDoorBlock(UAProperties.DRIFTWOOD.trapdoor(), UAProperties.DRIFTWOOD_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> DRIFTWOOD_SIGNS = HELPER.createSignBlock("driftwood", UAProperties.DRIFTWOOD_WOOD_TYPE, UAProperties.DRIFTWOOD.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> DRIFTWOOD_HANGING_SIGNS = HELPER.createHangingSignBlock("driftwood", UAProperties.DRIFTWOOD_WOOD_TYPE, UAProperties.DRIFTWOOD.hangingSign());
	public static final RegistryObject<Block> DRIFTWOOD_BOARDS = HELPER.createFuelBlock("driftwood_boards", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD.planks()), 300);
	public static final RegistryObject<Block> DRIFTWOOD_BOOKSHELF = HELPER.createFuelBlock("driftwood_bookshelf", () -> new Block(UAProperties.DRIFTWOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_DRIFTWOOD_BOOKSHELF = HELPER.createFuelBlock("chiseled_driftwood_bookshelf", () -> new ChiseledDriftwoodBookShelfBlock(UAProperties.DRIFTWOOD.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> DRIFTWOOD_LADDER = HELPER.createFuelBlock("driftwood_ladder", () -> new LadderBlock(UAProperties.DRIFTWOOD.ladder()), 300);
	public static final RegistryObject<Block> DRIFTWOOD_BEEHIVE = HELPER.createBlock("driftwood_beehive", () -> new BlueprintBeehiveBlock(UAProperties.DRIFTWOOD.beehive()));
	public static final RegistryObject<BlueprintChestBlock> DRIFTWOOD_CHEST = HELPER.createChestBlock("driftwood", UAProperties.DRIFTWOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_DRIFTWOOD_CHEST = HELPER.createTrappedChestBlockNamed("driftwood", UAProperties.DRIFTWOOD.chest());

	public static final RegistryObject<Block> STRIPPED_RIVER_LOG = HELPER.createBlock("stripped_river_log", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD.log()));
	public static final RegistryObject<Block> STRIPPED_RIVER_WOOD = HELPER.createBlock("stripped_river_wood", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD.log()));
	public static final RegistryObject<Block> RIVER_LOG = HELPER.createBlock("river_log", () -> new LogBlock(STRIPPED_RIVER_LOG, UAProperties.RIVER_WOOD.log()));
	public static final RegistryObject<Block> RIVER_WOOD = HELPER.createBlock("river_wood", () -> new LogBlock(STRIPPED_RIVER_WOOD, UAProperties.RIVER_WOOD.log()));
	public static final RegistryObject<Block> RIVER_LEAVES = HELPER.createBlock("river_leaves", () -> new LeavesBlock(UAProperties.RIVER_WOOD.leaves()));
	public static final RegistryObject<Block> RIVER_SAPLING = HELPER.createBlock("river_sapling", () -> new SaplingBlock(new RiverTreeGrower(), UAProperties.RIVER_WOOD.sapling()));
	public static final RegistryObject<Block> POTTED_RIVER_SAPLING = HELPER.createBlockNoItem("potted_river_sapling", () -> new FlowerPotBlock(RIVER_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final RegistryObject<Block> RIVER_PLANKS = HELPER.createBlock("river_planks", () -> new Block(UAProperties.RIVER_WOOD.planks()));
	public static final RegistryObject<Block> RIVER_STAIRS = HELPER.createBlock("river_stairs", () -> new StairBlock(() -> RIVER_PLANKS.get().defaultBlockState(), UAProperties.RIVER_WOOD.planks()));
	public static final RegistryObject<Block> RIVER_SLAB = HELPER.createBlock("river_slab", () -> new SlabBlock(UAProperties.RIVER_WOOD.planks()));
	public static final RegistryObject<Block> RIVER_PRESSURE_PLATE = HELPER.createBlock("river_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.RIVER_WOOD.pressurePlate(), UAProperties.RIVER_BLOCK_SET));
	public static final RegistryObject<Block> RIVER_BUTTON = HELPER.createBlock("river_button", () -> new ButtonBlock(UAProperties.RIVER_WOOD.button(), UAProperties.RIVER_BLOCK_SET, 30, true));
	public static final RegistryObject<Block> RIVER_FENCE = HELPER.createFuelBlock("river_fence", () -> new FenceBlock(UAProperties.RIVER_WOOD.planks()), 300);
	public static final RegistryObject<Block> RIVER_FENCE_GATE = HELPER.createFuelBlock("river_fence_gate", () -> new FenceGateBlock(UAProperties.RIVER_WOOD.planks(), UAProperties.RIVER_WOOD_TYPE), 300);
	public static final RegistryObject<Block> RIVER_DOOR = HELPER.createBlock("river_door", () -> new DoorBlock(UAProperties.RIVER_WOOD.door(), UAProperties.RIVER_BLOCK_SET));
	public static final RegistryObject<Block> RIVER_TRAPDOOR = HELPER.createBlock("river_trapdoor", () -> new TrapDoorBlock(UAProperties.RIVER_WOOD.trapdoor(), UAProperties.RIVER_BLOCK_SET));
	public static final Pair<RegistryObject<BlueprintStandingSignBlock>, RegistryObject<BlueprintWallSignBlock>> RIVER_SIGNS = HELPER.createSignBlock("river", UAProperties.RIVER_WOOD_TYPE, UAProperties.RIVER_WOOD.sign());
	public static final Pair<RegistryObject<BlueprintCeilingHangingSignBlock>, RegistryObject<BlueprintWallHangingSignBlock>> RIVER_HANGING_SIGNS = HELPER.createHangingSignBlock("river", UAProperties.RIVER_WOOD_TYPE, UAProperties.RIVER_WOOD.hangingSign());
	public static final RegistryObject<Block> RIVER_BOARDS = HELPER.createFuelBlock("river_boards", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD.planks()), 300);
	public static final RegistryObject<Block> RIVER_BOOKSHELF = HELPER.createFuelBlock("river_bookshelf", () -> new Block(UAProperties.RIVER_WOOD.bookshelf()), 300);
	public static final RegistryObject<Block> CHISELED_RIVER_BOOKSHELF = HELPER.createFuelBlock("chiseled_river_bookshelf", () -> new ChiseledRiverBookShelfBlock(UAProperties.RIVER_WOOD.chiseledBookshelf()), 300);
	public static final RegistryObject<Block> RIVER_LADDER = HELPER.createFuelBlock("river_ladder", () -> new LadderBlock(UAProperties.RIVER_WOOD.ladder()), 300);
	public static final RegistryObject<Block> RIVER_BEEHIVE = HELPER.createBlock("river_beehive", () -> new BlueprintBeehiveBlock(UAProperties.RIVER_WOOD.beehive()));
	public static final RegistryObject<Block> RIVER_LEAF_PILE = HELPER.createBlock("river_leaf_pile", () -> new LeafPileBlock(UAProperties.RIVER_WOOD.leafPile()));
	public static final RegistryObject<BlueprintChestBlock> RIVER_CHEST = HELPER.createChestBlock("river", UAProperties.RIVER_WOOD.chest());
	public static final RegistryObject<BlueprintTrappedChestBlock> TRAPPED_RIVER_CHEST = HELPER.createTrappedChestBlockNamed("river", UAProperties.RIVER_WOOD.chest());

	private static Supplier<BedrollBlock> createBedroll(DyeColor color) {
		return () -> new BedrollBlock(color, BlockBehaviour.Properties.of().mapColor((state) -> state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WOOL).sound(SoundType.WOOL).strength(0.2F, 0.3F).noOcclusion());
	}

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(UpgradeAquatic.MOD_ID)
				.tab(BUILDING_BLOCKS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK), DRIFTWOOD_LOG, DRIFTWOOD, STRIPPED_DRIFTWOOD_LOG, STRIPPED_DRIFTWOOD, DRIFTWOOD_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), DRIFTWOOD_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK),
						DRIFTWOOD_STAIRS, DRIFTWOOD_SLAB, DRIFTWOOD_FENCE, DRIFTWOOD_FENCE_GATE, DRIFTWOOD_DOOR, DRIFTWOOD_TRAPDOOR, DRIFTWOOD_PRESSURE_PLATE, DRIFTWOOD_BUTTON,
						RIVER_LOG, RIVER_WOOD, STRIPPED_RIVER_LOG, STRIPPED_RIVER_WOOD, RIVER_PLANKS)
				.addItemsBefore(modLoaded(Blocks.BAMBOO_BLOCK, "woodworks"), RIVER_BOARDS)
				.addItemsBefore(of(Blocks.BAMBOO_BLOCK), RIVER_STAIRS, RIVER_SLAB, RIVER_FENCE, RIVER_FENCE_GATE, RIVER_DOOR, RIVER_TRAPDOOR, RIVER_PRESSURE_PLATE, RIVER_BUTTON)
				.addItemsBefore(of(Blocks.STONE), BEACHGRASS_THATCH, BEACHGRASS_THATCH_STAIRS, BEACHGRASS_THATCH_SLAB)
				.addItemsBefore(of(Blocks.SMOOTH_STONE), KELPY_COBBLESTONE, KELPY_COBBLESTONE_STAIRS, KELPY_COBBLESTONE_SLAB, KELPY_COBBLESTONE_WALL)
				.addItemsBefore(of(Blocks.GRANITE), KELPY_STONE_BRICKS, KELPY_STONE_BRICK_STAIRS, KELPY_STONE_BRICK_SLAB, KELPY_STONE_BRICK_WALL)
				.addItemsBefore(of(Blocks.NETHERRACK),
						LUMINOUS_PRISMARINE, LUMINOUS_PRISMARINE_STAIRS, LUMINOUS_PRISMARINE_SLAB, PRISMARINE_ROD_BUNDLE,
						SCUTE_BLOCK, SCUTE_SHINGLES, SCUTE_SHINGLE_STAIRS, SCUTE_SHINGLE_SLAB, SCUTE_SHINGLE_WALL, CHISELED_SCUTE_SHINGLES, SCUTE_PAVEMENT, SCUTE_PAVEMENT_STAIRS, SCUTE_PAVEMENT_SLAB, SCUTE_PAVEMENT_WALL,
						TOOTH_BLOCK, TOOTH_BRICKS, TOOTH_BRICK_STAIRS, TOOTH_BRICK_SLAB, TOOTH_BRICK_WALL, CHISELED_TOOTH_BRICKS, TOOTH_TILES, TOOTH_STAIRS, TOOTH_SLAB, TOOTH_WALL, TOOTH_DOOR, TOOTH_TRAPDOOR,
						CORALSTONE, CORALSTONE_STAIRS, CORALSTONE_SLAB, CORALSTONE_WALL, CHISELED_CORALSTONE,
						TUBE_CORALSTONE, TUBE_CORALSTONE_STAIRS, TUBE_CORALSTONE_SLAB, TUBE_CORALSTONE_WALL, TUBE_CHISELED_CORALSTONE,
						BRAIN_CORALSTONE, BRAIN_CORALSTONE_STAIRS, BRAIN_CORALSTONE_SLAB, BRAIN_CORALSTONE_WALL, BRAIN_CHISELED_CORALSTONE,
						BUBBLE_CORALSTONE, BUBBLE_CORALSTONE_STAIRS, BUBBLE_CORALSTONE_SLAB, BUBBLE_CORALSTONE_WALL, BUBBLE_CHISELED_CORALSTONE,
						FIRE_CORALSTONE, FIRE_CORALSTONE_STAIRS, FIRE_CORALSTONE_SLAB, FIRE_CORALSTONE_WALL, FIRE_CHISELED_CORALSTONE,
						HORN_CORALSTONE, HORN_CORALSTONE_STAIRS, HORN_CORALSTONE_SLAB, HORN_CORALSTONE_WALL, HORN_CHISELED_CORALSTONE,
						ACAN_CORALSTONE, ACAN_CORALSTONE_STAIRS, ACAN_CORALSTONE_SLAB, ACAN_CORALSTONE_WALL, ACAN_CHISELED_CORALSTONE,
						FINGER_CORALSTONE, FINGER_CORALSTONE_STAIRS, FINGER_CORALSTONE_SLAB, FINGER_CORALSTONE_WALL, FINGER_CHISELED_CORALSTONE,
						STAR_CORALSTONE, STAR_CORALSTONE_STAIRS, STAR_CORALSTONE_SLAB, STAR_CORALSTONE_WALL, STAR_CHISELED_CORALSTONE,
						MOSS_CORALSTONE, MOSS_CORALSTONE_STAIRS, MOSS_CORALSTONE_SLAB, MOSS_CORALSTONE_WALL, MOSS_CHISELED_CORALSTONE,
						PETAL_CORALSTONE, PETAL_CORALSTONE_STAIRS, PETAL_CORALSTONE_SLAB, PETAL_CORALSTONE_WALL, PETAL_CHISELED_CORALSTONE,
						BRANCH_CORALSTONE, BRANCH_CORALSTONE_STAIRS, BRANCH_CORALSTONE_SLAB, BRANCH_CORALSTONE_WALL, BRANCH_CHISELED_CORALSTONE,
						ROCK_CORALSTONE, ROCK_CORALSTONE_STAIRS, ROCK_CORALSTONE_SLAB, ROCK_CORALSTONE_WALL, ROCK_CHISELED_CORALSTONE,
						PILLOW_CORALSTONE, PILLOW_CORALSTONE_STAIRS, PILLOW_CORALSTONE_SLAB, PILLOW_CORALSTONE_WALL, PILLOW_CHISELED_CORALSTONE,
						SILK_CORALSTONE, SILK_CORALSTONE_STAIRS, SILK_CORALSTONE_SLAB, SILK_CORALSTONE_WALL, SILK_CHISELED_CORALSTONE,
						CHROME_CORALSTONE, CHROME_CORALSTONE_STAIRS, CHROME_CORALSTONE_SLAB, CHROME_CORALSTONE_WALL, CHROME_CHISELED_CORALSTONE,
						PRISMARINE_CORALSTONE, PRISMARINE_CORALSTONE_STAIRS, PRISMARINE_CORALSTONE_SLAB, PRISMARINE_CORALSTONE_WALL, PRISMARINE_CHISELED_CORALSTONE,
						DEAD_CORALSTONE, DEAD_CORALSTONE_STAIRS, DEAD_CORALSTONE_SLAB, DEAD_CORALSTONE_WALL, DEAD_CHISELED_CORALSTONE,
						ELDER_PRISMARINE_CORALSTONE, ELDER_PRISMARINE_CORALSTONE_STAIRS, ELDER_PRISMARINE_CORALSTONE_SLAB, ELDER_PRISMARINE_CORALSTONE_WALL, CHISELED_ELDER_PRISMARINE_CORALSTONE
				)
				.tab(COLORED_BLOCKS)
				.addItemsBefore(of(Blocks.SHULKER_BOX), GLASS_DOOR, GLASS_TRAPDOOR)
				.addItemsBefore(of(Blocks.CANDLE), BEDROLL, WHITE_BEDROLL, LIGHT_GRAY_BEDROLL, GRAY_BEDROLL, BLACK_BEDROLL, BROWN_BEDROLL, RED_BEDROLL, ORANGE_BEDROLL, YELLOW_BEDROLL, LIME_BEDROLL, GREEN_BEDROLL, CYAN_BEDROLL, LIGHT_BLUE_BEDROLL, BLUE_BEDROLL, PURPLE_BEDROLL, MAGENTA_BEDROLL, PINK_BEDROLL)
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(of(Blocks.SEA_LANTERN), TOOTH_LANTERN)
				.addItemsBefore(of(Blocks.BAMBOO_SIGN), DRIFTWOOD_SIGNS.getFirst(), DRIFTWOOD_HANGING_SIGNS.getFirst(), RIVER_SIGNS.getFirst(), RIVER_HANGING_SIGNS.getFirst())
				.addItemsBefore(of(Blocks.CANDLE), BEDROLL, WHITE_BEDROLL, LIGHT_GRAY_BEDROLL, GRAY_BEDROLL, BLACK_BEDROLL, BROWN_BEDROLL, RED_BEDROLL, ORANGE_BEDROLL, YELLOW_BEDROLL, LIME_BEDROLL, GREEN_BEDROLL, CYAN_BEDROLL, LIGHT_BLUE_BEDROLL, BLUE_BEDROLL, PURPLE_BEDROLL, MAGENTA_BEDROLL, PINK_BEDROLL)
				.tab(NATURAL_BLOCKS)
				.addItemsBefore(of(Blocks.NETHER_GOLD_ORE), EMBEDDED_AMMONITE)
				.addItemsBefore(of(Blocks.DEAD_BUSH), BEACHGRASS)
				.addItemsBefore(of(Blocks.SUNFLOWER), TALL_BEACHGRASS)
				.addItemsBefore(of(Blocks.KELP), PICKERELWEED)
				.addItemsBefore(of(Blocks.DRIED_KELP_BLOCK), PICKERELWEED_BLOCK, BOILED_PICKERELWEED_BLOCK, KELP_BLOCK)
				.addItemsBefore(of(Blocks.MUSHROOM_STEM), DRIFTWOOD_LOG, RIVER_LOG)
				.addItemsBefore(of(Blocks.AZALEA_LEAVES), RIVER_LEAVES)
				.addItemsBefore(modLoaded(Blocks.AZALEA_LEAVES, "woodworks"), RIVER_LEAF_PILE)
				.addItemsBefore(of(Blocks.AZALEA), RIVER_SAPLING)
				.addItemsBefore(of(Blocks.TORCHFLOWER), WHITE_SEAROCKET, PINK_SEAROCKET)
				.addItemsBefore(of(Blocks.PITCHER_PLANT), FLOWERING_RUSH)
				.addItemsAfter(of(Blocks.HONEY_BLOCK), MULBERRY_JAM_BLOCK)
				.addItemsAfter(of(Blocks.HORN_CORAL_BLOCK), ACAN_CORAL_BLOCK, FINGER_CORAL_BLOCK, STAR_CORAL_BLOCK, MOSS_CORAL_BLOCK, PETAL_CORAL_BLOCK, BRANCH_CORAL_BLOCK, ROCK_CORAL_BLOCK, PILLOW_CORAL_BLOCK, SILK_CORAL_BLOCK, CHROME_CORAL_BLOCK, PRISMARINE_CORAL_BLOCK)
				.addItemsAfter(of(Blocks.DEAD_HORN_CORAL_BLOCK), DEAD_ACAN_CORAL_BLOCK, DEAD_FINGER_CORAL_BLOCK, DEAD_STAR_CORAL_BLOCK, DEAD_MOSS_CORAL_BLOCK, DEAD_PETAL_CORAL_BLOCK, DEAD_BRANCH_CORAL_BLOCK, DEAD_ROCK_CORAL_BLOCK, DEAD_PILLOW_CORAL_BLOCK, DEAD_SILK_CORAL_BLOCK, DEAD_CHROME_CORAL_BLOCK, ELDER_PRISMARINE_CORAL_BLOCK)
				.addItemsAfter(of(Blocks.HORN_CORAL), ACAN_CORAL, FINGER_CORAL, STAR_CORAL, MOSS_CORAL, PETAL_CORAL, BRANCH_CORAL, ROCK_CORAL, PILLOW_CORAL, SILK_CORAL, CHROME_CORAL, PRISMARINE_CORAL)
				.addItemsAfter(of(Blocks.DEAD_HORN_CORAL), DEAD_ACAN_CORAL, DEAD_FINGER_CORAL, DEAD_STAR_CORAL, DEAD_MOSS_CORAL, DEAD_PETAL_CORAL, DEAD_BRANCH_CORAL, DEAD_ROCK_CORAL, DEAD_PILLOW_CORAL, DEAD_SILK_CORAL, DEAD_CHROME_CORAL, ELDER_PRISMARINE_CORAL)
				.addItemsAfter(of(Blocks.HORN_CORAL_FAN), ACAN_CORAL_FAN, FINGER_CORAL_FAN, STAR_CORAL_FAN, MOSS_CORAL_FAN, PETAL_CORAL_FAN, BRANCH_CORAL_FAN, ROCK_CORAL_FAN, PILLOW_CORAL_FAN, SILK_CORAL_FAN, CHROME_CORAL_FAN, PRISMARINE_CORAL_FAN)
				.addItemsAfter(of(Blocks.DEAD_HORN_CORAL_FAN), DEAD_ACAN_CORAL_FAN, DEAD_FINGER_CORAL_FAN, DEAD_STAR_CORAL_FAN, DEAD_MOSS_CORAL_FAN, DEAD_PETAL_CORAL_FAN, DEAD_BRANCH_CORAL_FAN, DEAD_ROCK_CORAL_FAN, DEAD_PILLOW_CORAL_FAN, DEAD_SILK_CORAL_FAN, DEAD_CHROME_CORAL_FAN, ELDER_PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_SHOWER, ELDER_PRISMARINE_CORAL_SHOWER)
				.tab(REDSTONE_BLOCKS)
				.addItemsAfter(of(Blocks.HONEY_BLOCK), MULBERRY_JAM_BLOCK)
				.addItemsBefore(of(Blocks.SCULK_SENSOR), ELDER_EYE);

		CreativeModeTabContentsPopulator.mod("berry_good_1")
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(ofID(UAConstants.GLOW_BERRY_BASKET), MULBERRY_PUNNET);

		CreativeModeTabContentsPopulator.mod("woodworks_1")
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(ofID(UAConstants.BAMBOO_LADDER), DRIFTWOOD_LADDER, RIVER_LADDER)
				.addItemsBefore(ofID(UAConstants.BAMBOO_BEEHIVE), DRIFTWOOD_BEEHIVE, RIVER_BEEHIVE)
				.addItemsBefore(ofID(UAConstants.BAMBOO_BOOKSHELF), DRIFTWOOD_BOOKSHELF, CHISELED_DRIFTWOOD_BOOKSHELF, RIVER_BOOKSHELF, CHISELED_RIVER_BOOKSHELF)
				.addItemsBefore(ofID(UAConstants.BAMBOO_CLOSET), DRIFTWOOD_CHEST, RIVER_CHEST)
				.tab(REDSTONE_BLOCKS)
				.addItemsBefore(ofID(UAConstants.TRAPPED_BAMBOO_CLOSET), TRAPPED_DRIFTWOOD_CHEST, TRAPPED_RIVER_CHEST);
	}

	public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
		return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, ItemLike fallback, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(ForgeRegistries.ITEMS.getValue(location)) : of(fallback)).test(stack);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(ForgeRegistries.ITEMS.getValue(location)).test(stack));
	}


	public static final class UAProperties {
		public static final BlockSetType DRIFTWOOD_BLOCK_SET = BlockSetType.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":driftwood"));
		public static final BlockSetType RIVER_BLOCK_SET = BlockSetType.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":river"));
		public static final BlockSetType TOOTH_BLOCK_SET = BlockSetType.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":tooth"));
		public static final BlockSetType GLASS_BLOCK_SET = BlockSetType.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":glass"));

		public static final WoodType DRIFTWOOD_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(UpgradeAquatic.MOD_ID + ":driftwood", DRIFTWOOD_BLOCK_SET));
		public static final WoodType RIVER_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(UpgradeAquatic.MOD_ID + ":river", RIVER_BLOCK_SET));

		public static final WoodSetProperties DRIFTWOOD = WoodSetProperties.builder(MapColor.STONE).build();
		public static final WoodSetProperties RIVER_WOOD = WoodSetProperties.builder(MapColor.COLOR_BROWN).build();

		public static final BlockBehaviour.Properties DEAD_CORAL_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
		public static final BlockBehaviour.Properties DEAD_CORAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().instabreak();
		public static final BlockBehaviour.Properties DEAD_CORAL_FAN = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().instabreak();

		public static final BlockBehaviour.Properties LUMINOUS_PRISMARINE = BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(1.5F, 6.0F).lightLevel((unknown) -> (8)).hasPostProcess(PropertyUtil::always).emissiveRendering(PropertyUtil::always);

		public static final BlockBehaviour.Properties CORALSTONE = Properties.copy(Blocks.STONE).randomTicks();

		public static final BlockBehaviour.Properties ELDER_EYE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.0F);
		public static final BlockBehaviour.Properties PICKERELWEED = BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).randomTicks().noCollission().strength(0.0F).sound(SoundType.WET_GRASS);
		public static final BlockBehaviour.Properties BEACHGRASS_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.GRASS).noOcclusion();

		public static BlockBehaviour.Properties createCoral(MapColor color) {
			return BlockBehaviour.Properties.of().mapColor(color).noCollission().instabreak().sound(SoundType.WET_GRASS);
		}

		public static BlockBehaviour.Properties createCoralBlock(MapColor color) {
			return BlockBehaviour.Properties.of().mapColor(color).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);
		}

		public static BlockBehaviour.Properties createPrismarineCoral(boolean elder) {
			MapColor color = !elder ? MapColor.DIAMOND : MapColor.TERRACOTTA_WHITE;
			return BlockBehaviour.Properties.of().mapColor(color).noCollission().instabreak().lightLevel((unknown) -> (5)).sound(SoundType.GLASS);
		}

		public static BlockBehaviour.Properties createPrismarineCoralBlock(boolean elder) {
			MapColor color = !elder ? MapColor.DIAMOND : MapColor.TERRACOTTA_WHITE;
			return BlockBehaviour.Properties.of().mapColor(color).requiresCorrectToolForDrops().strength(1.5F, 6.0F).lightLevel((unknown) -> (6)).sound(SoundType.GLASS);
		}

		public static BlockBehaviour.Properties createPickerelweedBlock(boolean isBoiled) {
			return isBoiled ? BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.5F, 5).noOcclusion().sound(SoundType.GRASS) : BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).noOcclusion().strength(0.5F, 5).sound(SoundType.WET_GRASS);
		}
	}

	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE);
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE);
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE);
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE);
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE);
		conversions.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE);
		conversions.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE);
		conversions.put(STAR_CORAL_BLOCK, STAR_CORALSTONE);
		conversions.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE);
		conversions.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE);
		conversions.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE);
		conversions.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE);
		conversions.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE);
		conversions.put(SILK_CORAL_BLOCK, SILK_CORALSTONE);
		conversions.put(CHROME_CORAL_BLOCK, CHROME_CORALSTONE);
		conversions.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> CHISELED_CORALSTONE_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CHISELED_CORALSTONE);
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, HORN_CHISELED_CORALSTONE);
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, TUBE_CHISELED_CORALSTONE);
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, BRAIN_CHISELED_CORALSTONE);
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, FIRE_CHISELED_CORALSTONE);
		conversions.put(ACAN_CORAL_BLOCK, ACAN_CHISELED_CORALSTONE);
		conversions.put(FINGER_CORAL_BLOCK, FINGER_CHISELED_CORALSTONE);
		conversions.put(STAR_CORAL_BLOCK, STAR_CHISELED_CORALSTONE);
		conversions.put(MOSS_CORAL_BLOCK, MOSS_CHISELED_CORALSTONE);
		conversions.put(PETAL_CORAL_BLOCK, PETAL_CHISELED_CORALSTONE);
		conversions.put(BRANCH_CORAL_BLOCK, BRANCH_CHISELED_CORALSTONE);
		conversions.put(ROCK_CORAL_BLOCK, ROCK_CHISELED_CORALSTONE);
		conversions.put(PILLOW_CORAL_BLOCK, PILLOW_CHISELED_CORALSTONE);
		conversions.put(SILK_CORAL_BLOCK, SILK_CHISELED_CORALSTONE);
		conversions.put(CHROME_CORAL_BLOCK, CHROME_CHISELED_CORALSTONE);
		conversions.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CHISELED_CORALSTONE);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_SLAB_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_SLAB);
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_SLAB);
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_SLAB);
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_SLAB);
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_SLAB);
		conversions.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_SLAB);
		conversions.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_SLAB);
		conversions.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_SLAB);
		conversions.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_SLAB);
		conversions.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_SLAB);
		conversions.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_SLAB);
		conversions.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_SLAB);
		conversions.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_SLAB);
		conversions.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_SLAB);
		conversions.put(CHROME_CORAL_BLOCK, CHROME_CORALSTONE_SLAB);
		conversions.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_SLAB);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_STAIRS_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_STAIRS);
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_STAIRS);
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_STAIRS);
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_STAIRS);
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_STAIRS);
		conversions.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_STAIRS);
		conversions.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_STAIRS);
		conversions.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_STAIRS);
		conversions.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_STAIRS);
		conversions.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_STAIRS);
		conversions.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_STAIRS);
		conversions.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_STAIRS);
		conversions.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_STAIRS);
		conversions.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_STAIRS);
		conversions.put(CHROME_CORAL_BLOCK, CHROME_CORALSTONE_STAIRS);
		conversions.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_STAIRS);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_WALL_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_WALL);
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_WALL);
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_WALL);
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_WALL);
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_WALL);
		conversions.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_WALL);
		conversions.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_WALL);
		conversions.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_WALL);
		conversions.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_WALL);
		conversions.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_WALL);
		conversions.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_WALL);
		conversions.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_WALL);
		conversions.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_WALL);
		conversions.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_WALL);
		conversions.put(CHROME_CORAL_BLOCK, CHROME_CORALSTONE_WALL);
		conversions.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_WALL);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> SAND_FALLABLES = Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> Blocks.SANDSTONE, () -> Blocks.SAND);
		fallables.put(() -> Blocks.RED_SANDSTONE, () -> Blocks.RED_SAND);
	});

	public static final Map<Supplier<Block>, Supplier<Block>> ATMOSPHERIC_SAND_FALLABLES = ModList.get().isLoaded("atmospheric") ? Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "arid_sand")));
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "red_arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", "red_arid_sand")));
	}) : null;

	public static final Map<Supplier<Block>, Supplier<Block>> GRAVEL_FALLABLES = Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> Blocks.COBBLESTONE, () -> Blocks.GRAVEL);
	});

	public enum KelpType {
		TONGUE, THORNY, OCHRE, POLAR
	}
}
