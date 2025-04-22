package com.teamabnormals.upgrade_aquatic.core.registry;

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
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
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
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.UAConstants;
import com.teamabnormals.upgrade_aquatic.core.other.UATreeGrowers;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockSetType.PressurePlateSensitivity;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class UABlocks {
	public static final BlockSubRegistryHelper BLOCKS = UpgradeAquatic.REGISTRY_HELPER.getBlockSubHelper();

	public static final DeferredBlock<Block> EMBEDDED_AMMONITE = BLOCKS.createBlock("embedded_ammonite", () -> new EmbeddedAmmoniteBlock(Properties.ofFullCopy(Blocks.STONE)));

	public static final DeferredBlock<Block> WHITE_SEAROCKET = BLOCKS.createBlock("white_searocket", () -> new SearocketBlock(MobEffects.WATER_BREATHING, 9, PropertyUtil.flower()));
	public static final DeferredBlock<Block> PINK_SEAROCKET = BLOCKS.createBlock("pink_searocket", () -> new SearocketBlock(MobEffects.WATER_BREATHING, 9, PropertyUtil.flower()));
	public static final DeferredBlock<Block> FLOWERING_RUSH = BLOCKS.createBlock("flowering_rush", () -> new FloweringRushBlock(Properties.ofFullCopy(Blocks.PEONY).sound(SoundType.WET_GRASS)));

	public static final DeferredBlock<Block> PICKERELWEED = BLOCKS.createBlock("pickerelweed", () -> new PickerelweedPlantBlock(UAProperties.PICKERELWEED));
	public static final DeferredBlock<Block> TALL_PICKERELWEED = BLOCKS.createBlockNoItem("tall_pickerelweed", () -> new PickerelweedDoublePlantBlock(UAProperties.PICKERELWEED));
	public static final DeferredBlock<Block> PICKERELWEED_BLOCK = BLOCKS.createBlock("pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(false), false));
	public static final DeferredBlock<Block> BOILED_PICKERELWEED_BLOCK = BLOCKS.createBlock("boiled_pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(true), true));

	public static final DeferredBlock<Block> POTTED_WHITE_SEAROCKET = BLOCKS.createBlockNoItem("potted_white_searocket", () -> new FlowerPotBlock(WHITE_SEAROCKET.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_PINK_SEAROCKET = BLOCKS.createBlockNoItem("potted_pink_searocket", () -> new FlowerPotBlock(PINK_SEAROCKET.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> POTTED_PICKERELWEED = BLOCKS.createBlockNoItem("potted_pickerelweed", () -> new FlowerPotBlock(PICKERELWEED.get(), PropertyUtil.flowerPot()));

	public static final DeferredBlock<Block> BEACHGRASS = BLOCKS.createBlock("beachgrass", () -> new BeachgrassBlock(Properties.ofFullCopy(Blocks.FERN).mapColor(MapColor.COLOR_YELLOW)));
	public static final DeferredBlock<Block> TALL_BEACHGRASS = BLOCKS.createBlock("tall_beachgrass", () -> new TallBeachgrassBlock(Properties.ofFullCopy(Blocks.LARGE_FERN).mapColor(MapColor.COLOR_YELLOW)));
	public static final DeferredBlock<Block> BEACHGRASS_THATCH = BLOCKS.createBlock("beachgrass_thatch", () -> new ThatchBlock(UAProperties.BEACHGRASS_THATCH));
	public static final DeferredBlock<Block> BEACHGRASS_THATCH_SLAB = BLOCKS.createBlock("beachgrass_thatch_slab", () -> new ThatchSlabBlock(UAProperties.BEACHGRASS_THATCH));
	public static final DeferredBlock<Block> BEACHGRASS_THATCH_STAIRS = BLOCKS.createBlock("beachgrass_thatch_stairs", () -> new ThatchStairBlock(BEACHGRASS_THATCH.get().defaultBlockState(), UAProperties.BEACHGRASS_THATCH));

	public static final DeferredBlock<Block> MULBERRY_VINE = BLOCKS.createBlockNoItem("mulberry_vine", () -> new MulberryVineBlock(Block.Properties.of().randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ)));
	public static final DeferredBlock<Block> MULBERRY_JAM_BLOCK = BLOCKS.createBlock("mulberry_jam_block", () -> new MulberryJamBlock(Block.Properties.ofFullCopy(Blocks.SLIME_BLOCK)));
	public static final DeferredBlock<Block> MULBERRY_PUNNET = BLOCKS.createBlock("mulberry_punnet", () -> new BlueprintDirectionalBlock(Block.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD)));

	public static final DeferredBlock<Block> PRISMARINE_ROD_BUNDLE = BLOCKS.createBlock("prismarine_rod_bundle", () -> new PrismarineRodBlock(Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> LUMINOUS_PRISMARINE = BLOCKS.createBlock("luminous_prismarine", () -> new ConduitFrameBlock(UAProperties.LUMINOUS_PRISMARINE));
	public static final DeferredBlock<Block> LUMINOUS_PRISMARINE_STAIRS = BLOCKS.createBlock("luminous_prismarine_stairs", () -> new StairBlock(LUMINOUS_PRISMARINE.get().defaultBlockState(), UAProperties.LUMINOUS_PRISMARINE));
	public static final DeferredBlock<Block> LUMINOUS_PRISMARINE_SLAB = BLOCKS.createBlock("luminous_prismarine_slab", () -> new SlabBlock(UAProperties.LUMINOUS_PRISMARINE));

	public static final DeferredBlock<Block> BEDROLL = BLOCKS.createBlock("bedroll", () -> new BedrollBlock(BlockBehaviour.Properties.of().mapColor((state) -> state.getValue(BedBlock.PART) == BedPart.FOOT ? MapColor.COLOR_BROWN : MapColor.WOOL).sound(SoundType.WOOL).strength(0.2F, 0.3F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));

	public static final DeferredBlock<Block> TOOTH_BLOCK = BLOCKS.createBlock("tooth_block", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_TILES = BLOCKS.createBlock("tooth_tiles", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_TILE_STAIRS = BLOCKS.createBlock("tooth_stairs", () -> new StairBlock(TOOTH_BLOCK.get().defaultBlockState(), Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_TILE_SLAB = BLOCKS.createBlock("tooth_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_TILE_WALL = BLOCKS.createBlock("tooth_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_BRICKS = BLOCKS.createBlock("tooth_bricks", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> CHISELED_TOOTH_BRICKS = BLOCKS.createBlock("chiseled_tooth_bricks", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_BRICK_STAIRS = BLOCKS.createBlock("tooth_brick_stairs", () -> new StairBlock(TOOTH_BLOCK.get().defaultBlockState(), Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_BRICK_SLAB = BLOCKS.createBlock("tooth_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_BRICK_WALL = BLOCKS.createBlock("tooth_brick_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_TRAPDOOR = BLOCKS.createBlock("tooth_trapdoor", () -> new ToothTrapdoorBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_DOOR = BLOCKS.createBlock("tooth_door", () -> new ToothDoorBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> TOOTH_LANTERN = BLOCKS.createBlock("tooth_lantern", () -> new ToothLanternBlock(Properties.ofFullCopy(Blocks.END_STONE).sound(UASoundEvents.TOOTH_LANTERN).noOcclusion().lightLevel((unknown) -> 15)));

	public static final DeferredBlock<Block> SCUTE_BLOCK = BLOCKS.createBlock("scute_block", () -> new ScuteBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_SHINGLES = BLOCKS.createBlock("scute_shingles", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_SHINGLE_STAIRS = BLOCKS.createBlock("scute_shingle_stairs", () -> new StairBlock(SCUTE_BLOCK.get().defaultBlockState(), Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_SHINGLE_SLAB = BLOCKS.createBlock("scute_shingle_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_SHINGLE_WALL = BLOCKS.createBlock("scute_shingle_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> CHISELED_SCUTE_SHINGLES = BLOCKS.createBlock("chiseled_scute_shingles", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_PAVEMENT = BLOCKS.createBlock("scute_pavement", () -> new Block(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_PAVEMENT_STAIRS = BLOCKS.createBlock("scute_pavement_stairs", () -> new StairBlock(SCUTE_BLOCK.get().defaultBlockState(), Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_PAVEMENT_SLAB = BLOCKS.createBlock("scute_pavement_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.END_STONE)));
	public static final DeferredBlock<Block> SCUTE_PAVEMENT_WALL = BLOCKS.createBlock("scute_pavement_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.END_STONE)));

	public static final DeferredBlock<Block> DEAD_ACAN_CORAL_BLOCK = BLOCKS.createBlock("dead_acan_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_FINGER_CORAL_BLOCK = BLOCKS.createBlock("dead_finger_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_STAR_CORAL_BLOCK = BLOCKS.createBlock("dead_star_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_MOSS_CORAL_BLOCK = BLOCKS.createBlock("dead_moss_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_PETAL_CORAL_BLOCK = BLOCKS.createBlock("dead_petal_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_BRANCH_CORAL_BLOCK = BLOCKS.createBlock("dead_branch_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_ROCK_CORAL_BLOCK = BLOCKS.createBlock("dead_rock_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_PILLOW_CORAL_BLOCK = BLOCKS.createBlock("dead_pillow_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_SILK_CORAL_BLOCK = BLOCKS.createBlock("dead_silk_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> DEAD_CHROME_CORAL_BLOCK = BLOCKS.createBlock("dead_chrome_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORAL_BLOCK = BLOCKS.createBlock("elder_prismarine_coral_block", () -> new ConduitFrameBlock(UAProperties.createPrismarineCoralBlock(true)));

	public static final DeferredBlock<Block> ACAN_CORAL_BLOCK = BLOCKS.createBlock("acan_coral_block", () -> new CoralBlock(DEAD_ACAN_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_CYAN)));
	public static final DeferredBlock<Block> FINGER_CORAL_BLOCK = BLOCKS.createBlock("finger_coral_block", () -> new CoralBlock(DEAD_FINGER_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_ORANGE)));
	public static final DeferredBlock<Block> STAR_CORAL_BLOCK = BLOCKS.createBlock("star_coral_block", () -> new CoralBlock(DEAD_STAR_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_LIGHT_GREEN)));
	public static final DeferredBlock<Block> MOSS_CORAL_BLOCK = BLOCKS.createBlock("moss_coral_block", () -> new CoralBlock(DEAD_MOSS_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_GREEN)));
	public static final DeferredBlock<Block> PETAL_CORAL_BLOCK = BLOCKS.createBlock("petal_coral_block", () -> new CoralBlock(DEAD_PETAL_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_LIGHT_BLUE)));
	public static final DeferredBlock<Block> BRANCH_CORAL_BLOCK = BLOCKS.createBlock("branch_coral_block", () -> new CoralBlock(DEAD_BRANCH_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_GRAY)));
	public static final DeferredBlock<Block> ROCK_CORAL_BLOCK = BLOCKS.createBlock("rock_coral_block", () -> new CoralBlock(DEAD_ROCK_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> PILLOW_CORAL_BLOCK = BLOCKS.createBlock("pillow_coral_block", () -> new CoralBlock(DEAD_PILLOW_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_WHITE)));
	public static final DeferredBlock<Block> SILK_CORAL_BLOCK = BLOCKS.createBlock("silk_coral_block", () -> new CoralBlock(DEAD_SILK_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.COLOR_PURPLE)));
	public static final DeferredBlock<Block> CHROME_CORAL_BLOCK = BLOCKS.createBlock("chrome_coral_block", () -> new CoralBlock(DEAD_CHROME_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MapColor.TERRACOTTA_GRAY)));
	public static final DeferredBlock<Block> PRISMARINE_CORAL_BLOCK = BLOCKS.createBlock("prismarine_coral_block", () -> new PrismarineCoralBlock(ELDER_PRISMARINE_CORAL_BLOCK.get(), UAProperties.createPrismarineCoralBlock(false)));

	public static final DeferredBlock<Block> DEAD_ACAN_CORAL = BLOCKS.createBlock("dead_acan_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_FINGER_CORAL = BLOCKS.createBlock("dead_finger_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_STAR_CORAL = BLOCKS.createBlock("dead_star_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_MOSS_CORAL = BLOCKS.createBlock("dead_moss_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_PETAL_CORAL = BLOCKS.createBlock("dead_petal_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_BRANCH_CORAL = BLOCKS.createBlock("dead_branch_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_ROCK_CORAL = BLOCKS.createBlock("dead_rock_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_PILLOW_CORAL = BLOCKS.createBlock("dead_pillow_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_SILK_CORAL = BLOCKS.createBlock("dead_silk_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_CHROME_CORAL = BLOCKS.createBlock("dead_chrome_coral", () -> new BaseCoralPlantBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORAL = BLOCKS.createBlock("elder_prismarine_coral", () -> new BaseCoralPlantBlock(UAProperties.createPrismarineCoral(true)));

	public static final DeferredBlock<Block> ACAN_CORAL = BLOCKS.createBlock("acan_coral", () -> new CoralPlantBlock(DEAD_ACAN_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_CYAN)));
	public static final DeferredBlock<Block> FINGER_CORAL = BLOCKS.createBlock("finger_coral", () -> new CoralPlantBlock(DEAD_FINGER_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_ORANGE)));
	public static final DeferredBlock<Block> STAR_CORAL = BLOCKS.createBlock("star_coral", () -> new CoralPlantBlock(DEAD_STAR_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_GREEN)));
	public static final DeferredBlock<Block> MOSS_CORAL = BLOCKS.createBlock("moss_coral", () -> new CoralPlantBlock(DEAD_MOSS_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_GREEN)));
	public static final DeferredBlock<Block> PETAL_CORAL = BLOCKS.createBlock("petal_coral", () -> new CoralPlantBlock(DEAD_PETAL_CORAL.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_BLUE)));
	public static final DeferredBlock<Block> BRANCH_CORAL = BLOCKS.createBlock("branch_coral", () -> new CoralPlantBlock(DEAD_BRANCH_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final DeferredBlock<Block> ROCK_CORAL = BLOCKS.createBlock("rock_coral", () -> new CoralPlantBlock(DEAD_ROCK_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_BROWN)));
	public static final DeferredBlock<Block> PILLOW_CORAL = BLOCKS.createBlock("pillow_coral", () -> new CoralPlantBlock(DEAD_PILLOW_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_WHITE)));
	public static final DeferredBlock<Block> SILK_CORAL = BLOCKS.createBlock("silk_coral", () -> new CoralPlantBlock(DEAD_SILK_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_PURPLE)));
	public static final DeferredBlock<Block> CHROME_CORAL = BLOCKS.createBlock("chrome_coral", () -> new CoralPlantBlock(DEAD_CHROME_CORAL.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final DeferredBlock<Block> PRISMARINE_CORAL = BLOCKS.createBlock("prismarine_coral", () -> new CoralPlantBlock(ELDER_PRISMARINE_CORAL.get(), UAProperties.createPrismarineCoral(false)));

	public static final DeferredBlock<Block> DEAD_ACAN_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_acan_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_FINGER_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_finger_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_STAR_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_star_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_MOSS_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_moss_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_PETAL_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_petal_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_BRANCH_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_branch_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_ROCK_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_rock_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_PILLOW_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_pillow_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_SILK_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_silk_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> DEAD_CHROME_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("dead_chrome_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.DEAD_CORAL));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("elder_prismarine_coral_wall_fan", () -> new BaseCoralWallFanBlock(UAProperties.createPrismarineCoral(true)));

	public static final DeferredBlock<Block> ACAN_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("acan_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_ACAN_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_CYAN)));
	public static final DeferredBlock<Block> FINGER_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("finger_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_FINGER_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_ORANGE)));
	public static final DeferredBlock<Block> STAR_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("star_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_STAR_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_GREEN)));
	public static final DeferredBlock<Block> MOSS_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("moss_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_MOSS_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_GREEN)));
	public static final DeferredBlock<Block> PETAL_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("petal_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_PETAL_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_BLUE)));
	public static final DeferredBlock<Block> BRANCH_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("branch_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_BRANCH_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final DeferredBlock<Block> ROCK_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("rock_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_ROCK_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_BROWN)));
	public static final DeferredBlock<Block> PILLOW_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("pillow_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_PILLOW_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_WHITE)));
	public static final DeferredBlock<Block> SILK_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("silk_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_SILK_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_PURPLE)));
	public static final DeferredBlock<Block> CHROME_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("chrome_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_CHROME_CORAL_WALL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)));
	public static final DeferredBlock<Block> PRISMARINE_CORAL_WALL_FAN = BLOCKS.createBlockNoItem("prismarine_coral_wall_fan", () -> new CoralWallFanBlock(ELDER_PRISMARINE_CORAL_WALL_FAN.get(), UAProperties.createPrismarineCoral(false)));

	public static final DeferredBlock<Block> DEAD_ACAN_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_acan_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_ACAN_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_FINGER_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_finger_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_FINGER_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_STAR_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_star_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_STAR_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_MOSS_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_moss_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_MOSS_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_PETAL_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_petal_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_PETAL_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_BRANCH_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_branch_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_BRANCH_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_ROCK_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_rock_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_ROCK_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_PILLOW_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_pillow_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_PILLOW_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_SILK_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_silk_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_SILK_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> DEAD_CHROME_CORAL_FAN = BLOCKS.createStandingAndWallBlock("dead_chrome_coral_fan", () -> new BaseCoralFanBlock(UAProperties.DEAD_CORAL_FAN), DEAD_CHROME_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORAL_FAN = BLOCKS.createStandingAndWallBlock("elder_prismarine_coral_fan", () -> new BaseCoralFanBlock(UAProperties.createPrismarineCoral(true)), ELDER_PRISMARINE_CORAL_WALL_FAN, Direction.DOWN);

	public static final DeferredBlock<Block> ACAN_CORAL_FAN = BLOCKS.createStandingAndWallBlock("acan_coral_fan", () -> new CoralFanBlock(DEAD_ACAN_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_CYAN)), ACAN_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> FINGER_CORAL_FAN = BLOCKS.createStandingAndWallBlock("finger_coral_fan", () -> new CoralFanBlock(DEAD_FINGER_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_ORANGE)), FINGER_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> STAR_CORAL_FAN = BLOCKS.createStandingAndWallBlock("star_coral_fan", () -> new CoralFanBlock(DEAD_STAR_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_GREEN)), STAR_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> MOSS_CORAL_FAN = BLOCKS.createStandingAndWallBlock("moss_coral_fan", () -> new CoralFanBlock(DEAD_MOSS_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_GREEN)), MOSS_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> PETAL_CORAL_FAN = BLOCKS.createStandingAndWallBlock("petal_coral_fan", () -> new CoralFanBlock(DEAD_PETAL_CORAL_FAN.get(), UAProperties.createCoral(MapColor.COLOR_LIGHT_BLUE)), PETAL_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> BRANCH_CORAL_FAN = BLOCKS.createStandingAndWallBlock("branch_coral_fan", () -> new CoralFanBlock(DEAD_BRANCH_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)), BRANCH_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> ROCK_CORAL_FAN = BLOCKS.createStandingAndWallBlock("rock_coral_fan", () -> new CoralFanBlock(DEAD_ROCK_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_BROWN)), ROCK_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> PILLOW_CORAL_FAN = BLOCKS.createStandingAndWallBlock("pillow_coral_fan", () -> new CoralFanBlock(DEAD_PILLOW_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_WHITE)), PILLOW_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> SILK_CORAL_FAN = BLOCKS.createStandingAndWallBlock("silk_coral_fan", () -> new CoralFanBlock(DEAD_SILK_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_PURPLE)), SILK_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> CHROME_CORAL_FAN = BLOCKS.createStandingAndWallBlock("chrome_coral_fan", () -> new CoralFanBlock(DEAD_CHROME_CORAL_FAN.get(), UAProperties.createCoral(MapColor.TERRACOTTA_GRAY)), CHROME_CORAL_WALL_FAN, Direction.DOWN);
	public static final DeferredBlock<Block> PRISMARINE_CORAL_FAN = BLOCKS.createStandingAndWallBlock("prismarine_coral_fan", () -> new CoralFanBlock(ELDER_PRISMARINE_CORAL_FAN.get(), UAProperties.createPrismarineCoral(false)), PRISMARINE_CORAL_WALL_FAN, Direction.DOWN);

	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORAL_SHOWER = BLOCKS.createBlock("elder_prismarine_coral_shower", () -> new DeadCoralShowerBlock(UAProperties.createPrismarineCoral(true)));
	public static final DeferredBlock<Block> PRISMARINE_CORAL_SHOWER = BLOCKS.createBlock("prismarine_coral_shower", () -> new CoralShowerBlock(ELDER_PRISMARINE_CORAL_SHOWER.get(), UAProperties.createPrismarineCoral(false)));

	public static final DeferredBlock<Block> ELDER_EYE = BLOCKS.createBlockNoItem("elder_eye", () -> new ElderEyeBlock(UAProperties.ELDER_EYE));

	public static final DeferredBlock<Block> PINK_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("pink_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PINK));
	public static final DeferredBlock<Block> PURPLE_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("purple_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PURPLE));
	public static final DeferredBlock<Block> BLUE_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("blue_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.BLUE));
	public static final DeferredBlock<Block> GREEN_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("green_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.GREEN));
	public static final DeferredBlock<Block> YELLOW_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("yellow_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.YELLOW));
	public static final DeferredBlock<Block> ORANGE_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("orange_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.ORANGE));
	public static final DeferredBlock<Block> RED_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("red_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.RED));
	public static final DeferredBlock<Block> WHITE_JELLY_WALL_TORCH = BLOCKS.createBlockNoItem("white_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.WHITE));

	public static final DeferredBlock<Block> PINK_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("pink_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PINK), PINK_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> PURPLE_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("purple_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PURPLE), PURPLE_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> BLUE_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("blue_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.BLUE), BLUE_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> GREEN_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("green_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.GREEN), GREEN_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> YELLOW_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("yellow_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.YELLOW), YELLOW_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> ORANGE_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("orange_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.ORANGE), ORANGE_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> RED_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("red_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.RED), RED_JELLY_WALL_TORCH, Direction.DOWN);
	public static final DeferredBlock<Block> WHITE_JELLY_TORCH = BLOCKS.createStandingAndWallBlock("white_jelly_torch", () -> new JellyTorchBlock(Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.WHITE), WHITE_JELLY_WALL_TORCH, Direction.DOWN);

	public static final DeferredBlock<Block> CORALSTONE = BLOCKS.createBlock("coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false));
	public static final DeferredBlock<Block> BUBBLE_CORALSTONE = BLOCKS.createBlock("bubble_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> HORN_CORALSTONE = BLOCKS.createBlock("horn_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> TUBE_CORALSTONE = BLOCKS.createBlock("tube_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> BRAIN_CORALSTONE = BLOCKS.createBlock("brain_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> FIRE_CORALSTONE = BLOCKS.createBlock("fire_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> ACAN_CORALSTONE = BLOCKS.createBlock("acan_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> FINGER_CORALSTONE = BLOCKS.createBlock("finger_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> STAR_CORALSTONE = BLOCKS.createBlock("star_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> MOSS_CORALSTONE = BLOCKS.createBlock("moss_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PETAL_CORALSTONE = BLOCKS.createBlock("petal_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> BRANCH_CORALSTONE = BLOCKS.createBlock("branch_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> ROCK_CORALSTONE = BLOCKS.createBlock("rock_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PILLOW_CORALSTONE = BLOCKS.createBlock("pillow_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> SILK_CORALSTONE = BLOCKS.createBlock("silk_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> CHROME_CORALSTONE = BLOCKS.createBlock("chrome_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PRISMARINE_CORALSTONE = BLOCKS.createBlock("prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORALSTONE = BLOCKS.createBlock("elder_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false));
	public static final DeferredBlock<Block> DEAD_CORALSTONE = BLOCKS.createBlock("dead_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false));

	public static final DeferredBlock<Block> CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true));
	public static final DeferredBlock<Block> BUBBLE_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_bubble_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> HORN_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_horn_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> TUBE_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_tube_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> BRAIN_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_brain_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> FIRE_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_fire_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> ACAN_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_acan_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> FINGER_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_finger_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> STAR_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_star_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> MOSS_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_moss_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PETAL_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_petal_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> BRANCH_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_branch_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> ROCK_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_rock_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PILLOW_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_pillow_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> SILK_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_silk_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> CHROME_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_chrome_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PRISMARINE_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final DeferredBlock<Block> CHISELED_ELDER_PRISMARINE_CORALSTONE = BLOCKS.createBlock("chiseled_elder_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true));
	public static final DeferredBlock<Block> DEAD_CHISELED_CORALSTONE = BLOCKS.createBlock("chiseled_dead_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true));

	public static final DeferredBlock<Block> CORALSTONE_SLAB = BLOCKS.createBlock("coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null));
	public static final DeferredBlock<Block> BUBBLE_CORALSTONE_SLAB = BLOCKS.createBlock("bubble_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> HORN_CORALSTONE_SLAB = BLOCKS.createBlock("horn_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> TUBE_CORALSTONE_SLAB = BLOCKS.createBlock("tube_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> BRAIN_CORALSTONE_SLAB = BLOCKS.createBlock("brain_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> FIRE_CORALSTONE_SLAB = BLOCKS.createBlock("fire_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> ACAN_CORALSTONE_SLAB = BLOCKS.createBlock("acan_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> FINGER_CORALSTONE_SLAB = BLOCKS.createBlock("finger_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> STAR_CORALSTONE_SLAB = BLOCKS.createBlock("star_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> MOSS_CORALSTONE_SLAB = BLOCKS.createBlock("moss_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PETAL_CORALSTONE_SLAB = BLOCKS.createBlock("petal_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> BRANCH_CORALSTONE_SLAB = BLOCKS.createBlock("branch_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> ROCK_CORALSTONE_SLAB = BLOCKS.createBlock("rock_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PILLOW_CORALSTONE_SLAB = BLOCKS.createBlock("pillow_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> SILK_CORALSTONE_SLAB = BLOCKS.createBlock("silk_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> CHROME_CORALSTONE_SLAB = BLOCKS.createBlock("chrome_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PRISMARINE_CORALSTONE_SLAB = BLOCKS.createBlock("prismarine_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORALSTONE_SLAB = BLOCKS.createBlock("elder_prismarine_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null));
	public static final DeferredBlock<Block> DEAD_CORALSTONE_SLAB = BLOCKS.createBlock("dead_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null));

	public static final DeferredBlock<Block> CORALSTONE_STAIRS = BLOCKS.createBlock("coralstone_stairs", () -> new CoralstoneStairsBlock(() -> CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, null));
	public static final DeferredBlock<Block> BUBBLE_CORALSTONE_STAIRS = BLOCKS.createBlock("bubble_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BUBBLE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> HORN_CORALSTONE_STAIRS = BLOCKS.createBlock("horn_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> HORN_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> TUBE_CORALSTONE_STAIRS = BLOCKS.createBlock("tube_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> TUBE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> BRAIN_CORALSTONE_STAIRS = BLOCKS.createBlock("brain_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BRAIN_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> FIRE_CORALSTONE_STAIRS = BLOCKS.createBlock("fire_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> FIRE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}));
	public static final DeferredBlock<Block> ACAN_CORALSTONE_STAIRS = BLOCKS.createBlock("acan_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ACAN_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> FINGER_CORALSTONE_STAIRS = BLOCKS.createBlock("finger_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> FINGER_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> STAR_CORALSTONE_STAIRS = BLOCKS.createBlock("star_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> STAR_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> MOSS_CORALSTONE_STAIRS = BLOCKS.createBlock("moss_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> MOSS_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PETAL_CORALSTONE_STAIRS = BLOCKS.createBlock("petal_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PETAL_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> BRANCH_CORALSTONE_STAIRS = BLOCKS.createBlock("branch_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BRANCH_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> ROCK_CORALSTONE_STAIRS = BLOCKS.createBlock("rock_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ROCK_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PILLOW_CORALSTONE_STAIRS = BLOCKS.createBlock("pillow_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PILLOW_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> SILK_CORALSTONE_STAIRS = BLOCKS.createBlock("silk_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> SILK_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> CHROME_CORALSTONE_STAIRS = BLOCKS.createBlock("chrome_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> CHROME_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}));
	public static final DeferredBlock<Block> PRISMARINE_CORALSTONE_STAIRS = BLOCKS.createBlock("prismarine_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PRISMARINE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, new Block[]{PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORALSTONE_STAIRS = BLOCKS.createBlock("elder_prismarine_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ELDER_PRISMARINE_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, null));
	public static final DeferredBlock<Block> DEAD_CORALSTONE_STAIRS = BLOCKS.createBlock("dead_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> DEAD_CORALSTONE.get().defaultBlockState(), UAProperties.CORALSTONE, null));

	public static final DeferredBlock<Block> CORALSTONE_WALL = BLOCKS.createBlock("coralstone_wall", () -> new CoralstoneWallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> BUBBLE_CORALSTONE_WALL = BLOCKS.createBlock("bubble_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> HORN_CORALSTONE_WALL = BLOCKS.createBlock("horn_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> TUBE_CORALSTONE_WALL = BLOCKS.createBlock("tube_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> BRAIN_CORALSTONE_WALL = BLOCKS.createBlock("brain_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> FIRE_CORALSTONE_WALL = BLOCKS.createBlock("fire_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> ACAN_CORALSTONE_WALL = BLOCKS.createBlock("acan_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> FINGER_CORALSTONE_WALL = BLOCKS.createBlock("finger_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> STAR_CORALSTONE_WALL = BLOCKS.createBlock("star_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> MOSS_CORALSTONE_WALL = BLOCKS.createBlock("moss_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> PETAL_CORALSTONE_WALL = BLOCKS.createBlock("petal_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> BRANCH_CORALSTONE_WALL = BLOCKS.createBlock("branch_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> ROCK_CORALSTONE_WALL = BLOCKS.createBlock("rock_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> PILLOW_CORALSTONE_WALL = BLOCKS.createBlock("pillow_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> SILK_CORALSTONE_WALL = BLOCKS.createBlock("silk_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> CHROME_CORALSTONE_WALL = BLOCKS.createBlock("chrome_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> PRISMARINE_CORALSTONE_WALL = BLOCKS.createBlock("prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> ELDER_PRISMARINE_CORALSTONE_WALL = BLOCKS.createBlock("elder_prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));
	public static final DeferredBlock<Block> DEAD_CORALSTONE_WALL = BLOCKS.createBlock("dead_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE));

	public static final DeferredBlock<Block> KELP_BLOCK = BLOCKS.createBlock("kelp_block", () -> new Block(Properties.ofFullCopy(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)));

	public static final DeferredBlock<Block> KELPY_COBBLESTONE = BLOCKS.createBlock("kelpy_cobblestone", () -> new Block(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_STAIRS = BLOCKS.createBlock("kelpy_cobblestone_stairs", () -> new StairBlock(KELPY_COBBLESTONE.get().defaultBlockState(), Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_SLAB = BLOCKS.createBlock("kelpy_cobblestone_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_WALL = BLOCKS.createBlock("kelpy_cobblestone_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_STONE_BRICKS = BLOCKS.createBlock("kelpy_stone_bricks", () -> new Block(Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<Block> KELPY_STONE_BRICK_STAIRS = BLOCKS.createBlock("kelpy_stone_brick_stairs", () -> new StairBlock(KELPY_STONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<Block> KELPY_STONE_BRICK_SLAB = BLOCKS.createBlock("kelpy_stone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.STONE_BRICKS)));
	public static final DeferredBlock<Block> KELPY_STONE_BRICK_WALL = BLOCKS.createBlock("kelpy_stone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.STONE_BRICKS)));

	public static final DeferredBlock<Block> KELPY_COBBLESTONE_BRICKS = BLOCKS.createBlock("kelpy_cobblestone_bricks", () -> new Block(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_BRICK_STAIRS = BLOCKS.createBlock("kelpy_cobblestone_brick_stairs", () -> new StairBlock(KELPY_COBBLESTONE_BRICKS.get().defaultBlockState(), Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_BRICK_SLAB = BLOCKS.createBlock("kelpy_cobblestone_brick_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_BRICK_WALL = BLOCKS.createBlock("kelpy_cobblestone_brick_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_TILES = BLOCKS.createBlock("kelpy_cobblestone_tiles", () -> new Block(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_TILE_STAIRS = BLOCKS.createBlock("kelpy_cobblestone_tile_stairs", () -> new StairBlock(KELPY_COBBLESTONE_TILES.get().defaultBlockState(), Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_TILE_SLAB = BLOCKS.createBlock("kelpy_cobblestone_tile_slab", () -> new SlabBlock(Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> KELPY_COBBLESTONE_TILE_WALL = BLOCKS.createBlock("kelpy_cobblestone_tile_wall", () -> new WallBlock(Properties.ofFullCopy(Blocks.COBBLESTONE)));

	public static final DeferredBlock<Block> STRIPPED_DRIFTWOOD_LOG = BLOCKS.createBlock("stripped_driftwood_log", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD.log()));
	public static final DeferredBlock<Block> STRIPPED_DRIFTWOOD = BLOCKS.createBlock("stripped_driftwood", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD.log()));
	public static final DeferredBlock<Block> DRIFTWOOD_LOG = BLOCKS.createBlock("driftwood_log", () -> new LogBlock(STRIPPED_DRIFTWOOD_LOG, UAProperties.DRIFTWOOD.log()));
	public static final DeferredBlock<Block> DRIFTWOOD = BLOCKS.createBlock("driftwood", () -> new LogBlock(STRIPPED_DRIFTWOOD, UAProperties.DRIFTWOOD.log()));
	public static final DeferredBlock<Block> DRIFTWOOD_PLANKS = BLOCKS.createBlock("driftwood_planks", () -> new Block(UAProperties.DRIFTWOOD.planks()));
	public static final DeferredBlock<Block> DRIFTWOOD_STAIRS = BLOCKS.createBlock("driftwood_stairs", () -> new StairBlock(DRIFTWOOD_PLANKS.get().defaultBlockState(), UAProperties.DRIFTWOOD.planks()));
	public static final DeferredBlock<Block> DRIFTWOOD_SLAB = BLOCKS.createBlock("driftwood_slab", () -> new SlabBlock(UAProperties.DRIFTWOOD.planks()));
	public static final DeferredBlock<Block> DRIFTWOOD_PRESSURE_PLATE = BLOCKS.createBlock("driftwood_pressure_plate", () -> new PressurePlateBlock(UAProperties.DRIFTWOOD_BLOCK_SET, UAProperties.DRIFTWOOD.pressurePlate()));
	public static final DeferredBlock<Block> DRIFTWOOD_BUTTON = BLOCKS.createBlock("driftwood_button", () -> new ButtonBlock(UAProperties.DRIFTWOOD_BLOCK_SET, 30, UAProperties.DRIFTWOOD.button()));
	public static final DeferredBlock<Block> DRIFTWOOD_FENCE = BLOCKS.createBlock("driftwood_fence", () -> new FenceBlock(UAProperties.DRIFTWOOD.planks()));
	public static final DeferredBlock<Block> DRIFTWOOD_FENCE_GATE = BLOCKS.createBlock("driftwood_fence_gate", () -> new FenceGateBlock(UAProperties.DRIFTWOOD_WOOD_TYPE, UAProperties.DRIFTWOOD.planks()));
	public static final DeferredBlock<Block> DRIFTWOOD_DOOR = BLOCKS.createBlock("driftwood_door", () -> new DoorBlock(UAProperties.DRIFTWOOD_BLOCK_SET, UAProperties.DRIFTWOOD.door()));
	public static final DeferredBlock<Block> DRIFTWOOD_TRAPDOOR = BLOCKS.createBlock("driftwood_trapdoor", () -> new TrapDoorBlock(UAProperties.DRIFTWOOD_BLOCK_SET, UAProperties.DRIFTWOOD.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> DRIFTWOOD_SIGNS = BLOCKS.createSignBlock("driftwood", UAProperties.DRIFTWOOD_WOOD_TYPE, UAProperties.DRIFTWOOD.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> DRIFTWOOD_HANGING_SIGNS = BLOCKS.createHangingSignBlock("driftwood", UAProperties.DRIFTWOOD_WOOD_TYPE, UAProperties.DRIFTWOOD.hangingSign());
	public static final DeferredBlock<Block> DRIFTWOOD_BOARDS = BLOCKS.createBlock("driftwood_boards", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD.planks()));
	public static final DeferredBlock<Block> DRIFTWOOD_BOOKSHELF = BLOCKS.createBlock("driftwood_bookshelf", () -> new Block(UAProperties.DRIFTWOOD.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_DRIFTWOOD_BOOKSHELF = BLOCKS.createBlock("chiseled_driftwood_bookshelf", () -> new ChiseledDriftwoodBookShelfBlock(UAProperties.DRIFTWOOD.chiseledBookshelf()));
	public static final DeferredBlock<Block> DRIFTWOOD_LADDER = BLOCKS.createBlock("driftwood_ladder", () -> new LadderBlock(UAProperties.DRIFTWOOD.ladder()));
	public static final DeferredBlock<Block> DRIFTWOOD_BEEHIVE = BLOCKS.createBlock("driftwood_beehive", () -> new BlueprintBeehiveBlock(UAProperties.DRIFTWOOD.beehive()));
	public static final DeferredBlock<BlueprintChestBlock> DRIFTWOOD_CHEST = BLOCKS.createChestBlock("driftwood", UAProperties.DRIFTWOOD.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_DRIFTWOOD_CHEST = BLOCKS.createTrappedChestBlock("driftwood", UAProperties.DRIFTWOOD.chest());

	public static final DeferredBlock<Block> STRIPPED_RIVER_LOG = BLOCKS.createBlock("stripped_river_log", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD.log()));
	public static final DeferredBlock<Block> STRIPPED_RIVER_WOOD = BLOCKS.createBlock("stripped_river_wood", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD.log()));
	public static final DeferredBlock<Block> RIVER_LOG = BLOCKS.createBlock("river_log", () -> new LogBlock(STRIPPED_RIVER_LOG, UAProperties.RIVER_WOOD.log()));
	public static final DeferredBlock<Block> RIVER_WOOD = BLOCKS.createBlock("river_wood", () -> new LogBlock(STRIPPED_RIVER_WOOD, UAProperties.RIVER_WOOD.log()));
	public static final DeferredBlock<Block> RIVER_LEAVES = BLOCKS.createBlock("river_leaves", () -> new LeavesBlock(UAProperties.RIVER_WOOD.leaves()));
	public static final DeferredBlock<Block> RIVER_SAPLING = BLOCKS.createBlock("river_sapling", () -> new SaplingBlock(UATreeGrowers.RIVER, UAProperties.RIVER_WOOD.sapling()));
	public static final DeferredBlock<Block> POTTED_RIVER_SAPLING = BLOCKS.createBlockNoItem("potted_river_sapling", () -> new FlowerPotBlock(RIVER_SAPLING.get(), PropertyUtil.flowerPot()));
	public static final DeferredBlock<Block> RIVER_PLANKS = BLOCKS.createBlock("river_planks", () -> new Block(UAProperties.RIVER_WOOD.planks()));
	public static final DeferredBlock<Block> RIVER_STAIRS = BLOCKS.createBlock("river_stairs", () -> new StairBlock(RIVER_PLANKS.get().defaultBlockState(), UAProperties.RIVER_WOOD.planks()));
	public static final DeferredBlock<Block> RIVER_SLAB = BLOCKS.createBlock("river_slab", () -> new SlabBlock(UAProperties.RIVER_WOOD.planks()));
	public static final DeferredBlock<Block> RIVER_PRESSURE_PLATE = BLOCKS.createBlock("river_pressure_plate", () -> new PressurePlateBlock(UAProperties.RIVER_BLOCK_SET, UAProperties.RIVER_WOOD.pressurePlate()));
	public static final DeferredBlock<Block> RIVER_BUTTON = BLOCKS.createBlock("river_button", () -> new ButtonBlock(UAProperties.RIVER_BLOCK_SET, 30, UAProperties.RIVER_WOOD.button()));
	public static final DeferredBlock<Block> RIVER_FENCE = BLOCKS.createBlock("river_fence", () -> new FenceBlock(UAProperties.RIVER_WOOD.planks()));
	public static final DeferredBlock<Block> RIVER_FENCE_GATE = BLOCKS.createBlock("river_fence_gate", () -> new FenceGateBlock(UAProperties.RIVER_WOOD_TYPE, UAProperties.RIVER_WOOD.planks()));
	public static final DeferredBlock<Block> RIVER_DOOR = BLOCKS.createBlock("river_door", () -> new DoorBlock(UAProperties.RIVER_BLOCK_SET, UAProperties.RIVER_WOOD.door()));
	public static final DeferredBlock<Block> RIVER_TRAPDOOR = BLOCKS.createBlock("river_trapdoor", () -> new TrapDoorBlock(UAProperties.RIVER_BLOCK_SET, UAProperties.RIVER_WOOD.trapdoor()));
	public static final Pair<DeferredBlock<BlueprintStandingSignBlock>, DeferredBlock<BlueprintWallSignBlock>> RIVER_SIGNS = BLOCKS.createSignBlock("river", UAProperties.RIVER_WOOD_TYPE, UAProperties.RIVER_WOOD.sign());
	public static final Pair<DeferredBlock<BlueprintCeilingHangingSignBlock>, DeferredBlock<BlueprintWallHangingSignBlock>> RIVER_HANGING_SIGNS = BLOCKS.createHangingSignBlock("river", UAProperties.RIVER_WOOD_TYPE, UAProperties.RIVER_WOOD.hangingSign());
	public static final DeferredBlock<Block> RIVER_BOARDS = BLOCKS.createBlock("river_boards", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD.planks()));
	public static final DeferredBlock<Block> RIVER_BOOKSHELF = BLOCKS.createBlock("river_bookshelf", () -> new Block(UAProperties.RIVER_WOOD.bookshelf()));
	public static final DeferredBlock<Block> CHISELED_RIVER_BOOKSHELF = BLOCKS.createBlock("chiseled_river_bookshelf", () -> new ChiseledRiverBookShelfBlock(UAProperties.RIVER_WOOD.chiseledBookshelf()));
	public static final DeferredBlock<Block> RIVER_LADDER = BLOCKS.createBlock("river_ladder", () -> new LadderBlock(UAProperties.RIVER_WOOD.ladder()));
	public static final DeferredBlock<Block> RIVER_BEEHIVE = BLOCKS.createBlock("river_beehive", () -> new BlueprintBeehiveBlock(UAProperties.RIVER_WOOD.beehive()));
	public static final DeferredBlock<Block> RIVER_LEAF_PILE = BLOCKS.createBlock("river_leaf_pile", () -> new LeafPileBlock(UAProperties.RIVER_WOOD.leafPile()));
	public static final DeferredBlock<BlueprintChestBlock> RIVER_CHEST = BLOCKS.createChestBlock("river", UAProperties.RIVER_WOOD.chest());
	public static final DeferredBlock<BlueprintTrappedChestBlock> TRAPPED_RIVER_CHEST = BLOCKS.createTrappedChestBlock("river", UAProperties.RIVER_WOOD.chest());

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
				.addItemsBefore(of(Blocks.SANDSTONE), BEACHGRASS_THATCH, BEACHGRASS_THATCH_STAIRS, BEACHGRASS_THATCH_SLAB)
				.addItemsBefore(of(Blocks.SMOOTH_STONE), KELPY_COBBLESTONE, KELPY_COBBLESTONE_STAIRS, KELPY_COBBLESTONE_SLAB, KELPY_COBBLESTONE_WALL)
				.addItemsBefore(modLoaded(Blocks.SMOOTH_STONE, UAConstants.CAVERNS_AND_CHASMS),
						KELPY_COBBLESTONE_BRICKS, KELPY_COBBLESTONE_BRICK_STAIRS, KELPY_COBBLESTONE_BRICK_SLAB, KELPY_COBBLESTONE_BRICK_WALL,
						KELPY_COBBLESTONE_TILES, KELPY_COBBLESTONE_TILE_STAIRS, KELPY_COBBLESTONE_TILE_SLAB, KELPY_COBBLESTONE_TILE_WALL
				)
				.addItemsBefore(of(Blocks.GRANITE), KELPY_STONE_BRICKS, KELPY_STONE_BRICK_STAIRS, KELPY_STONE_BRICK_SLAB, KELPY_STONE_BRICK_WALL)
				.addItemsBefore(of(Blocks.NETHERRACK),
						LUMINOUS_PRISMARINE, LUMINOUS_PRISMARINE_STAIRS, LUMINOUS_PRISMARINE_SLAB, PRISMARINE_ROD_BUNDLE,
						SCUTE_BLOCK, SCUTE_SHINGLES, SCUTE_SHINGLE_STAIRS, SCUTE_SHINGLE_SLAB, SCUTE_SHINGLE_WALL, CHISELED_SCUTE_SHINGLES, SCUTE_PAVEMENT, SCUTE_PAVEMENT_STAIRS, SCUTE_PAVEMENT_SLAB, SCUTE_PAVEMENT_WALL,
						TOOTH_BLOCK, TOOTH_BRICKS, TOOTH_BRICK_STAIRS, TOOTH_BRICK_SLAB, TOOTH_BRICK_WALL, CHISELED_TOOTH_BRICKS, TOOTH_TILES, TOOTH_TILE_STAIRS, TOOTH_TILE_SLAB, TOOTH_TILE_WALL, TOOTH_DOOR, TOOTH_TRAPDOOR,
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
				.tab(FUNCTIONAL_BLOCKS)
				.addItemsBefore(of(Blocks.SEA_LANTERN), TOOTH_LANTERN)
				.addItemsBefore(of(Blocks.BAMBOO_SIGN), DRIFTWOOD_SIGNS.getFirst(), DRIFTWOOD_HANGING_SIGNS.getFirst(), RIVER_SIGNS.getFirst(), RIVER_HANGING_SIGNS.getFirst())
				.addItemsBefore(of(Blocks.CANDLE), BEDROLL)
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

		CreativeModeTabContentsPopulator.mod("berry_good_" + UpgradeAquatic.MOD_ID)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(ofID(UAConstants.GLOW_BERRY_BASKET), MULBERRY_PUNNET);

		CreativeModeTabContentsPopulator.mod("woodworks_" + UpgradeAquatic.MOD_ID)
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
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) ? of(BuiltInRegistries.ITEM.get(location)) : of(fallback)).test(stack);
	}

	public static Predicate<ItemStack> ofID(ResourceLocation location, String... modids) {
		return stack -> (BlockSubRegistryHelper.areModsLoaded(modids) && of(BuiltInRegistries.ITEM.get(location)).test(stack));
	}

	public static final class UAProperties {
		public static final BlockSetType DRIFTWOOD_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":driftwood"));
		public static final BlockSetType RIVER_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":river"));
		public static final BlockSetType TOOTH_BLOCK_SET = BlockSetTypeRegistryHelper.register(new BlockSetType(UpgradeAquatic.MOD_ID + ":tooth", true, true, true, PressurePlateSensitivity.EVERYTHING, SoundType.STONE, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF, SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON));

		public static final WoodType DRIFTWOOD_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(UpgradeAquatic.MOD_ID + ":driftwood", DRIFTWOOD_BLOCK_SET));
		public static final WoodType RIVER_WOOD_TYPE = WoodTypeRegistryHelper.registerWoodType(new WoodType(UpgradeAquatic.MOD_ID + ":river", RIVER_BLOCK_SET));

		public static final WoodSetProperties DRIFTWOOD = WoodSetProperties.builder(MapColor.STONE).build();
		public static final WoodSetProperties RIVER_WOOD = WoodSetProperties.builder(MapColor.COLOR_BROWN).build();

		public static final BlockBehaviour.Properties DEAD_CORAL_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
		public static final BlockBehaviour.Properties DEAD_CORAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().instabreak();
		public static final BlockBehaviour.Properties DEAD_CORAL_FAN = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().instabreak();

		public static final BlockBehaviour.Properties LUMINOUS_PRISMARINE = BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(1.5F, 6.0F).lightLevel((unknown) -> (8)).hasPostProcess(PropertyUtil::always).emissiveRendering(PropertyUtil::always);

		public static final BlockBehaviour.Properties CORALSTONE = Properties.ofFullCopy(Blocks.STONE).randomTicks();

		public static final BlockBehaviour.Properties ELDER_EYE = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.METAL).strength(1.0F);
		public static final BlockBehaviour.Properties PICKERELWEED = BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).randomTicks().noCollission().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY).offsetType(Block.OffsetType.XZ);
		public static final BlockBehaviour.Properties BEACHGRASS_THATCH = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.GRASS).noOcclusion();

		public static BlockBehaviour.Properties createCoral(MapColor color) {
			return BlockBehaviour.Properties.of().mapColor(color).noCollission().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY);
		}

		public static BlockBehaviour.Properties createCoralBlock(MapColor color) {
			return BlockBehaviour.Properties.of().mapColor(color).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);
		}

		public static BlockBehaviour.Properties createPrismarineCoral(boolean elder) {
			MapColor color = !elder ? MapColor.DIAMOND : MapColor.TERRACOTTA_WHITE;
			return BlockBehaviour.Properties.of().mapColor(color).noCollission().instabreak().lightLevel((unknown) -> (5)).sound(SoundType.GLASS).pushReaction(PushReaction.DESTROY);
		}

		public static BlockBehaviour.Properties createPrismarineCoralBlock(boolean elder) {
			MapColor color = !elder ? MapColor.DIAMOND : MapColor.TERRACOTTA_WHITE;
			return BlockBehaviour.Properties.of().mapColor(color).requiresCorrectToolForDrops().strength(1.5F, 6.0F).lightLevel((unknown) -> (6)).sound(SoundType.GLASS);
		}

		public static BlockBehaviour.Properties createPickerelweedBlock(boolean isBoiled) {
			return isBoiled ? BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.5F, 5).noOcclusion().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY) : BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).noOcclusion().strength(0.5F, 5).sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY);
		}
	}
}
