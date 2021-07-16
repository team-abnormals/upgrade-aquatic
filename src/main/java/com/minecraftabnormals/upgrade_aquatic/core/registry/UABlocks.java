package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.google.common.collect.Maps;
import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchSlabBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchStairsBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.thatch.ThatchVerticalSlabBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.*;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.coralstone.*;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.trees.RiverTree;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.other.UAProperties;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BedPart;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UABlocks {
	public static final BlockSubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> DEAD_ACAN_CORAL_BLOCK 		= HELPER.createBlock("dead_acan_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_FINGER_CORAL_BLOCK 		= HELPER.createBlock("dead_finger_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_STAR_CORAL_BLOCK 		= HELPER.createBlock("dead_star_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_MOSS_CORAL_BLOCK 		= HELPER.createBlock("dead_moss_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_PETAL_CORAL_BLOCK 		= HELPER.createBlock("dead_petal_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL_BLOCK 		= HELPER.createBlock("dead_branch_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_ROCK_CORAL_BLOCK 		= HELPER.createBlock("dead_rock_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL_BLOCK 		= HELPER.createBlock("dead_pillow_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_SILK_CORAL_BLOCK 		= HELPER.createBlock("dead_silk_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_CHROME_CORAL_BLOCK 		= HELPER.createBlock("dead_chrome_coral_block", () -> new InjectedBlock(Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_BLOCK 	= HELPER.createInjectedBlock("elder_prismarine_coral_block", Blocks.DEAD_HORN_CORAL_BLOCK.asItem(), () -> new ConduitSupportingBlock(UAProperties.createPrismarineCoralBlock(true)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> ACAN_CORAL_BLOCK 			= HELPER.createInjectedBlock("acan_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_ACAN_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.CYAN)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FINGER_CORAL_BLOCK 		= HELPER.createInjectedBlock("finger_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_FINGER_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.ORANGE_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STAR_CORAL_BLOCK 			= HELPER.createInjectedBlock("star_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_STAR_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.LIME)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MOSS_CORAL_BLOCK 			= HELPER.createInjectedBlock("moss_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_MOSS_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.GREEN)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETAL_CORAL_BLOCK 		= HELPER.createInjectedBlock("petal_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_PETAL_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.LIGHT_BLUE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRANCH_CORAL_BLOCK 		= HELPER.createInjectedBlock("branch_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_BRANCH_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROCK_CORAL_BLOCK 			= HELPER.createInjectedBlock("rock_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_ROCK_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.BROWN)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PILLOW_CORAL_BLOCK 		= HELPER.createInjectedBlock("pillow_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_PILLOW_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.WHITE_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SILK_CORAL_BLOCK 			= HELPER.createInjectedBlock("silk_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_SILK_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.PURPLE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHROME_CORAL_BLOCK 		= HELPER.createInjectedBlock("chrome_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new CoralBlock(DEAD_CHROME_CORAL_BLOCK.get(), UAProperties.createCoralBlock(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PRISMARINE_CORAL_BLOCK 	= HELPER.createInjectedBlock("prismarine_coral_block", Blocks.HORN_CORAL_BLOCK.asItem(), () -> new PrismarineCoralBlock(ELDER_PRISMARINE_CORAL_BLOCK.get(), UAProperties.createPrismarineCoralBlock(false)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> DEAD_ACAN_CORAL 			= HELPER.createInjectedBlock("dead_acan_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_FINGER_CORAL 		= HELPER.createInjectedBlock("dead_finger_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_STAR_CORAL 			= HELPER.createInjectedBlock("dead_star_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_MOSS_CORAL 			= HELPER.createInjectedBlock("dead_moss_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_PETAL_CORAL 			= HELPER.createInjectedBlock("dead_petal_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL 		= HELPER.createInjectedBlock("dead_branch_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_ROCK_CORAL 			= HELPER.createInjectedBlock("dead_rock_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL 		= HELPER.createInjectedBlock("dead_pillow_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_SILK_CORAL 			= HELPER.createInjectedBlock("dead_silk_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_CHROME_CORAL 		= HELPER.createInjectedBlock("dead_chrome_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL 	= HELPER.createInjectedBlock("elder_prismarine_coral", Blocks.DEAD_HORN_CORAL.asItem(), () -> new UACoralDeadBlock(UAProperties.createPrismarineCoral(true)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> ACAN_CORAL 		= HELPER.createInjectedBlock("acan_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_ACAN_CORAL.get(), UAProperties.createCoral(MaterialColor.CYAN)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FINGER_CORAL 		= HELPER.createInjectedBlock("finger_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_FINGER_CORAL.get(), UAProperties.createCoral(MaterialColor.ORANGE_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> STAR_CORAL 		= HELPER.createInjectedBlock("star_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_STAR_CORAL.get(), UAProperties.createCoral(MaterialColor.LIME)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MOSS_CORAL 		= HELPER.createInjectedBlock("moss_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_MOSS_CORAL.get(), UAProperties.createCoral(MaterialColor.GREEN)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PETAL_CORAL 		= HELPER.createInjectedBlock("petal_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_PETAL_CORAL.get(), UAProperties.createCoral(MaterialColor.LIGHT_BLUE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BRANCH_CORAL 		= HELPER.createInjectedBlock("branch_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_BRANCH_CORAL.get(), UAProperties.createCoral(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROCK_CORAL 		= HELPER.createInjectedBlock("rock_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_ROCK_CORAL.get(), UAProperties.createCoral(MaterialColor.BROWN_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PILLOW_CORAL 		= HELPER.createInjectedBlock("pillow_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_PILLOW_CORAL.get(), UAProperties.createCoral(MaterialColor.WHITE_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SILK_CORAL 		= HELPER.createInjectedBlock("silk_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_SILK_CORAL.get(), UAProperties.createCoral(MaterialColor.PURPLE_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHROME_CORAL 		= HELPER.createInjectedBlock("chrome_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(DEAD_CHROME_CORAL.get(), UAProperties.createCoral(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PRISMARINE_CORAL 	= HELPER.createInjectedBlock("prismarine_coral", Blocks.HORN_CORAL.asItem(), () -> new UACoralBlock(ELDER_PRISMARINE_CORAL.get(), UAProperties.createPrismarineCoral(false)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> DEAD_ACAN_CORAL_WALL_FAN 			= HELPER.createBlockNoItem("dead_acan_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_FINGER_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("dead_finger_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_STAR_CORAL_WALL_FAN 			= HELPER.createBlockNoItem("dead_star_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_MOSS_CORAL_WALL_FAN 			= HELPER.createBlockNoItem("dead_moss_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_PETAL_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("dead_petal_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("dead_branch_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_ROCK_CORAL_WALL_FAN 			= HELPER.createBlockNoItem("dead_rock_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("dead_pillow_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_SILK_CORAL_WALL_FAN 			= HELPER.createBlockNoItem("dead_silk_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> DEAD_CHROME_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("dead_chrome_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.DEAD_CORAL));
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_WALL_FAN 	= HELPER.createBlockNoItem("elder_prismarine_coral_wall_fan", () -> new UACoralWallFanDeadBlock(UAProperties.createPrismarineCoral(true)));

	public static final RegistryObject<Block> ACAN_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("acan_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_ACAN_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.CYAN)));
	public static final RegistryObject<Block> FINGER_CORAL_WALL_FAN 	= HELPER.createBlockNoItem("finger_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_FINGER_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.ORANGE_TERRACOTTA)));
	public static final RegistryObject<Block> STAR_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("star_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_STAR_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.LIME)));
	public static final RegistryObject<Block> MOSS_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("moss_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_MOSS_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.GREEN)));
	public static final RegistryObject<Block> PETAL_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("petal_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_PETAL_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.LIGHT_BLUE)));
	public static final RegistryObject<Block> BRANCH_CORAL_WALL_FAN 	= HELPER.createBlockNoItem("branch_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_BRANCH_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.GRAY_TERRACOTTA)));
	public static final RegistryObject<Block> ROCK_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("rock_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_ROCK_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.BROWN_TERRACOTTA)));
	public static final RegistryObject<Block> PILLOW_CORAL_WALL_FAN 	= HELPER.createBlockNoItem("pillow_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_PILLOW_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.WHITE_TERRACOTTA)));
	public static final RegistryObject<Block> SILK_CORAL_WALL_FAN 		= HELPER.createBlockNoItem("silk_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_SILK_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.PURPLE_TERRACOTTA)));
	public static final RegistryObject<Block> CHROME_CORAL_WALL_FAN 	= HELPER.createBlockNoItem("chrome_coral_wall_fan", () -> new UACoralWallFanBlock(DEAD_CHROME_CORAL_WALL_FAN.get(), UAProperties.createCoralFan(MaterialColor.GRAY_TERRACOTTA)));
	public static final RegistryObject<Block> PRISMARINE_CORAL_WALL_FAN = HELPER.createBlockNoItem("prismarine_coral_wall_fan", () -> new UACoralWallFanBlock(ELDER_PRISMARINE_CORAL_WALL_FAN.get(), UAProperties.createPrismarineCoral(false)));

	public static final RegistryObject<Block> DEAD_ACAN_CORAL_FAN 			= HELPER.createWallOrFloorBlock("dead_acan_coral_fan", UACoralFanDeadBlock::new, DEAD_ACAN_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_FINGER_CORAL_FAN 		= HELPER.createWallOrFloorBlock("dead_finger_coral_fan", UACoralFanDeadBlock::new, DEAD_FINGER_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_STAR_CORAL_FAN 			= HELPER.createWallOrFloorBlock("dead_star_coral_fan", UACoralFanDeadBlock::new, DEAD_STAR_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_MOSS_CORAL_FAN 			= HELPER.createWallOrFloorBlock("dead_moss_coral_fan", UACoralFanDeadBlock::new, DEAD_MOSS_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_PETAL_CORAL_FAN 			= HELPER.createWallOrFloorBlock("dead_petal_coral_fan", UACoralFanDeadBlock::new, DEAD_PETAL_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_BRANCH_CORAL_FAN 		= HELPER.createWallOrFloorBlock("dead_branch_coral_fan", UACoralFanDeadBlock::new, DEAD_BRANCH_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_ROCK_CORAL_FAN 			= HELPER.createWallOrFloorBlock("dead_rock_coral_fan", UACoralFanDeadBlock::new, DEAD_ROCK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_PILLOW_CORAL_FAN 		= HELPER.createWallOrFloorBlock("dead_pillow_coral_fan", UACoralFanDeadBlock::new, DEAD_PILLOW_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_SILK_CORAL_FAN 			= HELPER.createWallOrFloorBlock("dead_silk_coral_fan", UACoralFanDeadBlock::new, DEAD_SILK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_CHROME_CORAL_FAN 		= HELPER.createWallOrFloorBlock("dead_chrome_coral_fan", UACoralFanDeadBlock::new, DEAD_CHROME_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_FAN 	= HELPER.createWallOrFloorBlock("elder_prismarine_coral_fan", () -> new UACoralFanDeadBlock(UAProperties.createPrismarineCoral(true)), ELDER_PRISMARINE_CORAL_WALL_FAN, ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> ACAN_CORAL_FAN 		= HELPER.createWallOrFloorBlock("acan_coral_fan", () -> new UACoralFanBlock(DEAD_ACAN_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.CYAN)), ACAN_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FINGER_CORAL_FAN 		= HELPER.createWallOrFloorBlock("finger_coral_fan", () -> new UACoralFanBlock(DEAD_FINGER_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.ORANGE_TERRACOTTA)), FINGER_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> STAR_CORAL_FAN 		= HELPER.createWallOrFloorBlock("star_coral_fan", () -> new UACoralFanBlock(DEAD_STAR_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.LIME)), STAR_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MOSS_CORAL_FAN 		= HELPER.createWallOrFloorBlock("moss_coral_fan", () -> new UACoralFanBlock(DEAD_MOSS_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.GREEN)), MOSS_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PETAL_CORAL_FAN 		= HELPER.createWallOrFloorBlock("petal_coral_fan", () -> new UACoralFanBlock(DEAD_PETAL_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.LIGHT_BLUE)), PETAL_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BRANCH_CORAL_FAN 		= HELPER.createWallOrFloorBlock("branch_coral_fan", () -> new UACoralFanBlock(DEAD_BRANCH_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.GRAY_TERRACOTTA)), BRANCH_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROCK_CORAL_FAN 		= HELPER.createWallOrFloorBlock("rock_coral_fan", () -> new UACoralFanBlock(DEAD_ROCK_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.BROWN_TERRACOTTA)), ROCK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PILLOW_CORAL_FAN 		= HELPER.createWallOrFloorBlock("pillow_coral_fan", () -> new UACoralFanBlock(DEAD_PILLOW_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.WHITE_TERRACOTTA)), PILLOW_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SILK_CORAL_FAN 		= HELPER.createWallOrFloorBlock("silk_coral_fan", () -> new UACoralFanBlock(DEAD_SILK_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.PURPLE_TERRACOTTA)), SILK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHROME_CORAL_FAN 		= HELPER.createWallOrFloorBlock("chrome_coral_fan", () -> new UACoralFanBlock(DEAD_CHROME_CORAL_FAN.get(), UAProperties.createCoralFan(MaterialColor.GRAY_TERRACOTTA)), CHROME_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PRISMARINE_CORAL_FAN 	= HELPER.createWallOrFloorBlock("prismarine_coral_fan", () -> new UACoralFanBlock(ELDER_PRISMARINE_CORAL_FAN.get(), UAProperties.createPrismarineCoral(false)), PRISMARINE_CORAL_WALL_FAN, ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> ELDER_PRISMARINE_CORAL_SHOWER = HELPER.createBlock("elder_prismarine_coral_shower", () -> new DeadCoralShowerBlock(UAProperties.createPrismarineCoral(true)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PRISMARINE_CORAL_SHOWER 		= HELPER.createBlock("prismarine_coral_shower", () -> new CoralShowerBlock(ELDER_PRISMARINE_CORAL_SHOWER.get(), UAProperties.createPrismarineCoral(false)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> ELDER_GUARDIAN_SPINE 	= HELPER.createBlock("elder_guardian_spine", () -> new GuardianSpineBlock(UAProperties.SPINES, true), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> GUARDIAN_SPINE 		= HELPER.createBlock("guardian_spine", () -> new GuardianSpineBlock(UAProperties.SPINES, false), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> ELDER_EYE 			= HELPER.createRareBlock("elder_eye", () -> new ElderEyeBlock(UAProperties.ELDER_EYE), Rarity.RARE, ItemGroup.REDSTONE);

	public static final RegistryObject<Block> PINK_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("pink_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PINK));
	public static final RegistryObject<Block> PURPLE_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("purple_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PURPLE));
	public static final RegistryObject<Block> BLUE_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("blue_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.BLUE));
	public static final RegistryObject<Block> GREEN_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("green_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.GREEN));
	public static final RegistryObject<Block> YELLOW_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("yellow_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.YELLOW));
	public static final RegistryObject<Block> ORANGE_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("orange_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.ORANGE));
	public static final RegistryObject<Block> RED_JELLY_WALL_TORCH 		= HELPER.createBlockNoItem("red_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.RED));
	public static final RegistryObject<Block> WHITE_JELLY_WALL_TORCH 	= HELPER.createBlockNoItem("white_jelly_wall_torch", () -> new JellyWallTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.WHITE));

	public static final RegistryObject<Block> PINK_JELLY_TORCH 		= HELPER.createWallOrFloorBlock("pink_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PINK), PINK_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_JELLY_TORCH 	= HELPER.createWallOrFloorBlock("purple_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.PURPLE), PURPLE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_JELLY_TORCH 		= HELPER.createWallOrFloorBlock("blue_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.BLUE), BLUE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GREEN_JELLY_TORCH 	= HELPER.createWallOrFloorBlock("green_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.GREEN), GREEN_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_JELLY_TORCH 	= HELPER.createWallOrFloorBlock("yellow_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.YELLOW), YELLOW_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_JELLY_TORCH 	= HELPER.createWallOrFloorBlock("orange_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.ORANGE), ORANGE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_JELLY_TORCH 		= HELPER.createWallOrFloorBlock("red_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.RED), RED_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_JELLY_TORCH 	= HELPER.createWallOrFloorBlock("white_jelly_torch", () -> new JellyTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchBlock.JellyTorchType.WHITE), WHITE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> EMBEDDED_AMMONITE 	= HELPER.createBlock("embedded_ammonite", () -> new EmbeddedAmmoniteBlock(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> BEDROLL 				= HELPER.createBlock("bedroll", createBedroll(DyeColor.BROWN), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> WHITE_BEDROLL 		= HELPER.createBlock("white_bedroll", createBedroll(DyeColor.WHITE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ORANGE_BEDROLL 		= HELPER.createBlock("orange_bedroll", createBedroll(DyeColor.ORANGE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MAGENTA_BEDROLL 		= HELPER.createBlock("magenta_bedroll", createBedroll(DyeColor.MAGENTA), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_BLUE_BEDROLL 	= HELPER.createBlock("light_blue_bedroll", createBedroll(DyeColor.LIGHT_BLUE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> YELLOW_BEDROLL 		= HELPER.createBlock("yellow_bedroll", createBedroll(DyeColor.YELLOW), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIME_BEDROLL 			= HELPER.createBlock("lime_bedroll", createBedroll(DyeColor.LIME), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_BEDROLL 			= HELPER.createBlock("pink_bedroll", createBedroll(DyeColor.PINK), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GRAY_BEDROLL 			= HELPER.createBlock("gray_bedroll", createBedroll(DyeColor.GRAY), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> LIGHT_GRAY_BEDROLL 	= HELPER.createBlock("light_gray_bedroll", createBedroll(DyeColor.LIGHT_GRAY), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CYAN_BEDROLL 			= HELPER.createBlock("cyan_bedroll", createBedroll(DyeColor.CYAN), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PURPLE_BEDROLL 		= HELPER.createBlock("purple_bedroll", createBedroll(DyeColor.PURPLE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLUE_BEDROLL 			= HELPER.createBlock("blue_bedroll", createBedroll(DyeColor.BLUE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BROWN_BEDROLL 		= HELPER.createBlock("brown_bedroll", createBedroll(DyeColor.BROWN), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> GREEN_BEDROLL 		= HELPER.createBlock("green_bedroll", createBedroll(DyeColor.GREEN), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RED_BEDROLL 			= HELPER.createBlock("red_bedroll", createBedroll(DyeColor.RED), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BLACK_BEDROLL 		= HELPER.createBlock("black_bedroll", createBedroll(DyeColor.BLACK), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> BLUE_PICKERELWEED 		= HELPER.createBlock("blue_pickerelweed", () -> new PickerelweedPlantBlock(UAProperties.PICKERELWEED), ItemGroup.MISC);
	public static final RegistryObject<Block> TALL_BLUE_PICKERELWEED 	= HELPER.createBlockNoItem("tall_blue_pickerelweed", () -> new PickerelweedDoublePlantBlock(UAProperties.PICKERELWEED));
	public static final RegistryObject<Block> PURPLE_PICKERELWEED 		= HELPER.createBlock("purple_pickerelweed", () -> new PickerelweedPlantBlock(UAProperties.PICKERELWEED), ItemGroup.MISC);
	public static final RegistryObject<Block> TALL_PURPLE_PICKERELWEED 	= HELPER.createBlockNoItem("tall_purple_pickerelweed", () -> new PickerelweedDoublePlantBlock(UAProperties.PICKERELWEED));

	public static final RegistryObject<Block> WHITE_SEAROCKET 	= HELPER.createBlock("white_searocket", () -> new SearocketBlock(Effects.WATER_BREATHING, 9, UAProperties.createSearocket(false)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PINK_SEAROCKET 	= HELPER.createBlock("pink_searocket", () -> new SearocketBlock(Effects.WATER_BREATHING, 9, UAProperties.createSearocket(true)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FLOWERING_RUSH 	= HELPER.createBlock("flowering_rush", () -> new FloweringRushBlock(Properties.from(Blocks.PEONY).sound(SoundType.WET_GRASS)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> MULBERRY_VINE 		= HELPER.createBlockNoItem("mulberry_vine", () -> new MulberryVineBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH).tickRandomly().notSolid()));
	public static final RegistryObject<Block> MULBERRY_JAM_BLOCK 	= HELPER.createBlock("mulberry_jam_block", () -> new MulberryJamBlock(Block.Properties.from(Blocks.SLIME_BLOCK)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> MULBERRY_PUNNET       = HELPER.createCompatBlock("quark", "mulberry_punnet", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(1.5F).sound(SoundType.WOOD)), ItemGroup.DECORATIONS);

    public static final RegistryObject<Block> POTTED_BLUE_PICKERELWEED 		= HELPER.createBlockNoItem("potted_blue_pickerelweed", () -> new FlowerPotBlock(BLUE_PICKERELWEED.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
	public static final RegistryObject<Block> POTTED_PURPLE_PICKERELWEED 	= HELPER.createBlockNoItem("potted_purple_pickerelweed", () -> new FlowerPotBlock(PURPLE_PICKERELWEED.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
	public static final RegistryObject<Block> POTTED_WHITE_SEAROCKET 		= HELPER.createBlockNoItem("potted_white_searocket", () -> new FlowerPotBlock(WHITE_SEAROCKET.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
	public static final RegistryObject<Block> POTTED_PINK_SEAROCKET 		= HELPER.createBlockNoItem("potted_pink_searocket", () -> new FlowerPotBlock(PINK_SEAROCKET.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));

	public static final RegistryObject<Block> TOOTH_BLOCK 				= HELPER.createBlock("tooth_block", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_TILES 				= HELPER.createBlock("tooth_tiles", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_STAIRS 				= HELPER.createBlock("tooth_stairs", () -> new AbnormalsStairsBlock(TOOTH_BLOCK.get().getDefaultState(), Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_SLAB 				= HELPER.createBlock("tooth_slab", () -> new SlabBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_WALL 				= HELPER.createBlock("tooth_wall", () -> new WallBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> TOOTH_VERTICAL_SLAB 		= HELPER.createCompatBlock("quark", "tooth_vertical_slab", () -> new VerticalSlabBlock(Properties.from(TOOTH_TILES.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_BRICKS 				= HELPER.createBlock("tooth_bricks", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_TOOTH_BRICKS 	= HELPER.createBlock("chiseled_tooth_bricks", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_BRICK_STAIRS 		= HELPER.createBlock("tooth_brick_stairs", () -> new AbnormalsStairsBlock(TOOTH_BLOCK.get().getDefaultState(), Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_BRICK_SLAB 			= HELPER.createBlock("tooth_brick_slab", () -> new SlabBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_BRICK_WALL 			= HELPER.createBlock("tooth_brick_wall", () -> new WallBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> TOOTH_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "tooth_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.from(TOOTH_BRICKS.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TOOTH_TRAPDOOR 			= HELPER.createBlock("tooth_trapdoor", () -> new ToothTrapdoorBlock(Properties.from(Blocks.END_STONE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> TOOTH_DOOR 				= HELPER.createBlock("tooth_door", () -> new ToothDoorBlock(Properties.from(Blocks.END_STONE)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> TOOTH_LANTERN 			= HELPER.createBlock("tooth_lantern", () -> new ToothLanternBlock(Properties.from(Blocks.END_STONE).sound(UASounds.TOOTH_LANTERN).notSolid().setLightLevel((unknown) -> 15)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> SCUTE_BLOCK 					= HELPER.createBlock("scute_block", () -> new ScuteBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_SHINGLES 				= HELPER.createBlock("scute_shingles", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_SHINGLE_STAIRS 			= HELPER.createBlock("scute_shingle_stairs", () -> new AbnormalsStairsBlock(SCUTE_BLOCK.get().getDefaultState(), Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_SHINGLE_SLAB 			= HELPER.createBlock("scute_shingle_slab", () -> new SlabBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_SHINGLE_WALL 			= HELPER.createBlock("scute_shingle_wall", () -> new WallBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SCUTE_SHINGLE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "scute_shingle_vertical_slab", () -> new VerticalSlabBlock(Properties.from(SCUTE_SHINGLES.get())), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_SCUTE_SHINGLES 		= HELPER.createBlock("chiseled_scute_shingles", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_PAVEMENT 				= HELPER.createBlock("scute_pavement", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_PAVEMENT_STAIRS 		= HELPER.createBlock("scute_pavement_stairs", () -> new AbnormalsStairsBlock(SCUTE_BLOCK.get().getDefaultState(), Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_PAVEMENT_SLAB 			= HELPER.createBlock("scute_pavement_slab", () -> new SlabBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SCUTE_PAVEMENT_WALL 			= HELPER.createBlock("scute_pavement_wall", () -> new WallBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SCUTE_PAVEMENT_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "scute_pavement_vertical_slab", () -> new VerticalSlabBlock(Properties.from(SCUTE_PAVEMENT.get())), ItemGroup.BUILDING_BLOCKS);
	
	public static final RegistryObject<Block> GLASS_TRAPDOOR 			= HELPER.createBlock("glass_trapdoor", () -> new TrapDoorBlock(Properties.from(Blocks.GLASS)), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> GLASS_DOOR 				= HELPER.createBlock("glass_door", () -> new DoorBlock(Properties.from(Blocks.GLASS)), ItemGroup.REDSTONE);
	
	public static final RegistryObject<Block> CORALSTONE 					= HELPER.createBlock("coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BUBBLE_CORALSTONE 			= HELPER.createBlock("bubble_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HORN_CORALSTONE 				= HELPER.createBlock("horn_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TUBE_CORALSTONE 				= HELPER.createBlock("tube_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRAIN_CORALSTONE 				= HELPER.createBlock("brain_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FIRE_CORALSTONE 				= HELPER.createBlock("fire_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACAN_CORALSTONE 				= HELPER.createBlock("acan_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FINGER_CORALSTONE 			= HELPER.createBlock("finger_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STAR_CORALSTONE 				= HELPER.createBlock("star_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MOSS_CORALSTONE 				= HELPER.createBlock("moss_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETAL_CORALSTONE 				= HELPER.createBlock("petal_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRANCH_CORALSTONE 			= HELPER.createBlock("branch_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROCK_CORALSTONE 				= HELPER.createBlock("rock_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PILLOW_CORALSTONE 			= HELPER.createBlock("pillow_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SILK_CORALSTONE 				= HELPER.createBlock("silk_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHROME_CORALSTONE 			= HELPER.createBlock("chrome_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE 		= HELPER.createBlock("prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false, new Block[] { PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE 	= HELPER.createBlock("elder_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_CORALSTONE 				= HELPER.createBlock("dead_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, false), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CHISELED_CORALSTONE 					= HELPER.createBlock("chiseled_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BUBBLE_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_bubble_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HORN_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_horn_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TUBE_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_tube_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRAIN_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_brain_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FIRE_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_fire_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACAN_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_acan_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FINGER_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_finger_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STAR_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_star_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MOSS_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_moss_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETAL_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_petal_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRANCH_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_branch_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROCK_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_rock_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PILLOW_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_pillow_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SILK_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_silk_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHROME_CHISELED_CORALSTONE 			= HELPER.createBlock("chiseled_chrome_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PRISMARINE_CHISELED_CORALSTONE 		= HELPER.createBlock("chiseled_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true, new Block[] { PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHISELED_ELDER_PRISMARINE_CORALSTONE 	= HELPER.createBlock("chiseled_elder_prismarine_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_CHISELED_CORALSTONE 				= HELPER.createBlock("chiseled_dead_coralstone", () -> new CoralstoneBlock(UAProperties.CORALSTONE, true), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CORALSTONE_SLAB 					= HELPER.createBlock("coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_SLAB 			= HELPER.createBlock("bubble_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HORN_CORALSTONE_SLAB 				= HELPER.createBlock("horn_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TUBE_CORALSTONE_SLAB 				= HELPER.createBlock("tube_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRAIN_CORALSTONE_SLAB 			= HELPER.createBlock("brain_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FIRE_CORALSTONE_SLAB 				= HELPER.createBlock("fire_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACAN_CORALSTONE_SLAB 				= HELPER.createBlock("acan_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FINGER_CORALSTONE_SLAB 			= HELPER.createBlock("finger_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STAR_CORALSTONE_SLAB 				= HELPER.createBlock("star_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MOSS_CORALSTONE_SLAB 				= HELPER.createBlock("moss_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETAL_CORALSTONE_SLAB 			= HELPER.createBlock("petal_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRANCH_CORALSTONE_SLAB 			= HELPER.createBlock("branch_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROCK_CORALSTONE_SLAB 				= HELPER.createBlock("rock_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PILLOW_CORALSTONE_SLAB 			= HELPER.createBlock("pillow_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SILK_CORALSTONE_SLAB 				= HELPER.createBlock("silk_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHROME_CORALSTONE_SLAB 			= HELPER.createBlock("chrome_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_SLAB 		= HELPER.createBlock("prismarine_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, new Block[] { PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_SLAB 	= HELPER.createBlock("elder_prismarine_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_CORALSTONE_SLAB 				= HELPER.createBlock("dead_coralstone_slab", () -> new CoralstoneSlabBlock(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CORALSTONE_STAIRS 					= HELPER.createBlock("coralstone_stairs", () -> new CoralstoneStairsBlock(() -> CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_STAIRS 				= HELPER.createBlock("bubble_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BUBBLE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HORN_CORALSTONE_STAIRS 				= HELPER.createBlock("horn_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> HORN_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TUBE_CORALSTONE_STAIRS 				= HELPER.createBlock("tube_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> TUBE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRAIN_CORALSTONE_STAIRS 				= HELPER.createBlock("brain_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BRAIN_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FIRE_CORALSTONE_STAIRS 				= HELPER.createBlock("fire_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> FIRE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACAN_CORALSTONE_STAIRS 				= HELPER.createBlock("acan_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ACAN_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FINGER_CORALSTONE_STAIRS 				= HELPER.createBlock("finger_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> FINGER_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STAR_CORALSTONE_STAIRS 				= HELPER.createBlock("star_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> STAR_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MOSS_CORALSTONE_STAIRS 				= HELPER.createBlock("moss_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> MOSS_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETAL_CORALSTONE_STAIRS 				= HELPER.createBlock("petal_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PETAL_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRANCH_CORALSTONE_STAIRS 				= HELPER.createBlock("branch_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> BRANCH_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROCK_CORALSTONE_STAIRS 				= HELPER.createBlock("rock_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ROCK_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PILLOW_CORALSTONE_STAIRS 				= HELPER.createBlock("pillow_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PILLOW_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SILK_CORALSTONE_STAIRS 				= HELPER.createBlock("silk_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> SILK_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHROME_CORALSTONE_STAIRS 				= HELPER.createBlock("chrome_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> CHROME_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_STAIRS 			= HELPER.createBlock("prismarine_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> PRISMARINE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] { PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_STAIRS 	= HELPER.createBlock("elder_prismarine_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> ELDER_PRISMARINE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_CORALSTONE_STAIRS 				= HELPER.createBlock("dead_coralstone_stairs", () -> new CoralstoneStairsBlock(() -> DEAD_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> CORALSTONE_WALL 					= HELPER.createBlock("coralstone_wall", () -> new CoralstoneWallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_WALL 			= HELPER.createBlock("bubble_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> HORN_CORALSTONE_WALL 				= HELPER.createBlock("horn_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> TUBE_CORALSTONE_WALL 				= HELPER.createBlock("tube_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BRAIN_CORALSTONE_WALL 			= HELPER.createBlock("brain_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FIRE_CORALSTONE_WALL 				= HELPER.createBlock("fire_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ACAN_CORALSTONE_WALL 				= HELPER.createBlock("acan_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> FINGER_CORALSTONE_WALL 			= HELPER.createBlock("finger_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> STAR_CORALSTONE_WALL 				= HELPER.createBlock("star_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> MOSS_CORALSTONE_WALL 				= HELPER.createBlock("moss_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PETAL_CORALSTONE_WALL 			= HELPER.createBlock("petal_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BRANCH_CORALSTONE_WALL 			= HELPER.createBlock("branch_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ROCK_CORALSTONE_WALL 				= HELPER.createBlock("rock_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PILLOW_CORALSTONE_WALL 			= HELPER.createBlock("pillow_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> SILK_CORALSTONE_WALL 				= HELPER.createBlock("silk_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> CHROME_CORALSTONE_WALL 			= HELPER.createBlock("chrome_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_WALL 		= HELPER.createBlock("prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_WALL 	= HELPER.createBlock("elder_prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DEAD_CORALSTONE_WALL 				= HELPER.createBlock("dead_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> CORALSTONE_VERTICAL_SLAB 					= HELPER.createCompatBlock("quark", "coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BUBBLE_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "bubble_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> HORN_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "horn_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TUBE_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "tube_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRAIN_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "brain_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FIRE_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "fire_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ACAN_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "acan_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> FINGER_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "finger_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STAR_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "star_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> MOSS_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "moss_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PETAL_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "petal_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BRANCH_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "branch_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ROCK_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "rock_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PILLOW_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "pillow_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> SILK_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "silk_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> CHROME_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "chrome_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PRISMARINE_CORALSTONE_VERTICAL_SLAB 		= HELPER.createCompatBlock("quark", "prismarine_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, new Block[] { PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get() }), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "elder_prismarine_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DEAD_CORALSTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "dead_coralstone_vertical_slab", () -> new CoralstoneVerticalSlabBlock(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> TONGUE_KELP 		= HELPER.createBlock("tongue_kelp", () -> new UAKelpTopBlock(KelpType.TONGUE, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static final RegistryObject<Block> THORNY_KELP 		= HELPER.createBlock("thorny_kelp", () -> new UAKelpTopBlock(KelpType.THORNY, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static final RegistryObject<Block> OCHRE_KELP 		= HELPER.createBlock("ochre_kelp", () -> new UAKelpTopBlock(KelpType.OCHRE, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static final RegistryObject<Block> POLAR_KELP 		= HELPER.createBlock("polar_kelp", () -> new UAKelpTopBlock(KelpType.POLAR, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static final RegistryObject<Block> TONGUE_KELP_PLANT = HELPER.createBlockNoItem("tongue_kelp_plant", () -> new UAKelpBlock(KelpType.TONGUE, TONGUE_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	public static final RegistryObject<Block> THORNY_KELP_PLANT = HELPER.createBlockNoItem("thorny_kelp_plant", () -> new UAKelpBlock(KelpType.THORNY, THORNY_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	public static final RegistryObject<Block> OCHRE_KELP_PLANT 	= HELPER.createBlockNoItem("ochre_kelp_plant", () -> new UAKelpBlock(KelpType.OCHRE, OCHRE_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	public static final RegistryObject<Block> POLAR_KELP_PLANT 	= HELPER.createBlockNoItem("polar_kelp_plant", () -> new UAKelpBlock(KelpType.POLAR, POLAR_KELP.get(), Properties.from(Blocks.KELP_PLANT)));

	public static final RegistryObject<Block> KELP_BLOCK 		= HELPER.createBlock("kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELP_BLOCK = HELPER.createBlock("tongue_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELP_BLOCK = HELPER.createBlock("thorny_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELP_BLOCK 	= HELPER.createBlock("ochre_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELP_BLOCK 	= HELPER.createBlock("polar_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_COBBLESTONE 		= HELPER.createBlock("kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_COBBLESTONE 	= HELPER.createBlock("tongue_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_COBBLESTONE 	= HELPER.createBlock("thorny_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_COBBLESTONE 	= HELPER.createBlock("ochre_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_COBBLESTONE 	= HELPER.createBlock("polar_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_COBBLESTONE_STAIRS 			= HELPER.createBlock("kelpy_cobblestone_stairs", () -> new AbnormalsStairsBlock(KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_STAIRS 	= HELPER.createBlock("tongue_kelpy_cobblestone_stairs", () -> new AbnormalsStairsBlock(TONGUE_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_COBBLESTONE_STAIRS 	= HELPER.createBlock("thorny_kelpy_cobblestone_stairs", () -> new AbnormalsStairsBlock(THORNY_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_STAIRS 	= HELPER.createBlock("ochre_kelpy_cobblestone_stairs", () -> new AbnormalsStairsBlock(OCHRE_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_COBBLESTONE_STAIRS 	= HELPER.createBlock("polar_kelpy_cobblestone_stairs", () -> new AbnormalsStairsBlock(POLAR_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_COBBLESTONE_SLAB 		= HELPER.createBlock("kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_SLAB = HELPER.createBlock("tongue_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_COBBLESTONE_SLAB = HELPER.createBlock("thorny_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_SLAB 	= HELPER.createBlock("ochre_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_COBBLESTONE_SLAB 	= HELPER.createBlock("polar_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_COBBLESTONE_WALL 		= HELPER.createBlock("kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_WALL = HELPER.createBlock("tongue_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> THORNY_KELPY_COBBLESTONE_WALL = HELPER.createBlock("thorny_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_WALL 	= HELPER.createBlock("ochre_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POLAR_KELPY_COBBLESTONE_WALL 	= HELPER.createBlock("polar_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> KELPY_STONE_BRICKS 		= HELPER.createBlock("kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_STONE_BRICKS = HELPER.createBlock("tongue_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_STONE_BRICKS = HELPER.createBlock("thorny_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_STONE_BRICKS 	= HELPER.createBlock("ochre_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_STONE_BRICKS 	= HELPER.createBlock("polar_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_STONE_BRICK_STAIRS 			= HELPER.createBlock("kelpy_stone_brick_stairs", () -> new AbnormalsStairsBlock(KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_STAIRS 	= HELPER.createBlock("tongue_kelpy_stone_brick_stairs", () -> new AbnormalsStairsBlock(TONGUE_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_STONE_BRICK_STAIRS 	= HELPER.createBlock("thorny_kelpy_stone_brick_stairs", () -> new AbnormalsStairsBlock(THORNY_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_STAIRS 	= HELPER.createBlock("ochre_kelpy_stone_brick_stairs", () -> new AbnormalsStairsBlock(OCHRE_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_STONE_BRICK_STAIRS 	= HELPER.createBlock("polar_kelpy_stone_brick_stairs", () -> new AbnormalsStairsBlock(POLAR_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_STONE_BRICK_SLAB 		= HELPER.createBlock("kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_SLAB = HELPER.createBlock("tongue_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_STONE_BRICK_SLAB = HELPER.createBlock("thorny_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_SLAB 	= HELPER.createBlock("ochre_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_STONE_BRICK_SLAB 	= HELPER.createBlock("polar_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_STONE_BRICK_WALL 		= HELPER.createBlock("kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_WALL = HELPER.createBlock("tongue_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> THORNY_KELPY_STONE_BRICK_WALL = HELPER.createBlock("thorny_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_WALL 	= HELPER.createBlock("ochre_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POLAR_KELPY_STONE_BRICK_WALL 	= HELPER.createBlock("polar_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);

	public static final RegistryObject<Block> KELPY_COBBLESTONE_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "kelpy_cobblestone_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "tongue_kelpy_cobblestone_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_COBBLESTONE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "thorny_kelpy_cobblestone_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "ochre_kelpy_cobblestone_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_COBBLESTONE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "polar_kelpy_cobblestone_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> KELPY_STONE_BRICK_VERTICAL_SLAB 			= HELPER.createCompatBlock("quark", "kelpy_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "tongue_kelpy_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> THORNY_KELPY_STONE_BRICK_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "thorny_kelpy_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "ochre_kelpy_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> POLAR_KELPY_STONE_BRICK_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "polar_kelpy_stone_brick_vertical_slab", () -> new VerticalSlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> BLUE_PICKERELWEED_BLOCK 			= HELPER.createBlock("blue_pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(false), false), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> PURPLE_PICKERELWEED_BLOCK 		= HELPER.createBlock("purple_pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(false), false), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BOILED_BLUE_PICKERELWEED_BLOCK 	= HELPER.createBlock("boiled_blue_pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(true), true), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BOILED_PURPLE_PICKERELWEED_BLOCK 	= HELPER.createBlock("boiled_purple_pickerelweed_block", () -> new PickerelweedBlock(UAProperties.createPickerelweedBlock(true), true), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> PRISMARINE_ROD_BUNDLE 	= HELPER.createBlock("prismarine_rod_bundle", () -> new PrismarineRodBlock(Properties.from(Blocks.PRISMARINE_BRICKS).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> STRIPPED_DRIFTWOOD_LOG 	= HELPER.createBlock("stripped_driftwood_log", () -> new StrippedLogBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_DRIFTWOOD 		= HELPER.createBlock("stripped_driftwood", () -> new StrippedWoodBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_LOG 			= HELPER.createBlock("driftwood_log", () -> new AbnormalsLogBlock(STRIPPED_DRIFTWOOD_LOG, UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD 				= HELPER.createBlock("driftwood", () -> new WoodBlock(STRIPPED_DRIFTWOOD_LOG, UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_PLANKS 			= HELPER.createBlock("driftwood_planks", () -> new PlanksBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_DOOR 			= HELPER.createBlock("driftwood_door", () -> new WoodDoorBlock(UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> DRIFTWOOD_SLAB 			= HELPER.createBlock("driftwood_slab", () -> new WoodSlabBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_STAIRS 			= HELPER.createBlock("driftwood_stairs", () -> new WoodStairsBlock(DRIFTWOOD_PLANKS.get().getDefaultState(), UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_FENCE 			= HELPER.createFuelBlock("driftwood_fence", () -> new WoodFenceBlock(UAProperties.DRIFTWOOD), 300, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> DRIFTWOOD_FENCE_GATE 		= HELPER.createFuelBlock("driftwood_fence_gate", () -> new WoodFenceGateBlock(UAProperties.DRIFTWOOD),  300,ItemGroup.REDSTONE);
	public static final RegistryObject<Block> DRIFTWOOD_PRESSURE_PLATE 	= HELPER.createBlock("driftwood_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> DRIFTWOOD_BUTTON 			= HELPER.createBlock("driftwood_button", () -> new AbnormalsWoodButtonBlock(Properties.from(UABlocks.DRIFTWOOD.get()).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> DRIFTWOOD_TRAPDOOR 		= HELPER.createBlock("driftwood_trapdoor", () -> new WoodTrapDoorBlock(UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> VERTICAL_DRIFTWOOD_PLANKS = HELPER.createCompatBlock("quark", "vertical_driftwood_planks", () -> new Block(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_VERTICAL_SLAB 	= HELPER.createCompatFuelBlock("quark", "driftwood_vertical_slab", () -> new VerticalSlabBlock(UAProperties.DRIFTWOOD), 150, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_BOOKSHELF 		= HELPER.createCompatFuelBlock("quark", "driftwood_bookshelf", () -> new BookshelfBlock(Properties.from(Blocks.BOOKSHELF)), 300, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_LADDER 			= HELPER.createCompatFuelBlock("quark", "driftwood_ladder", () -> new AbnormalsLadderBlock(Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_DRIFTWOOD_POST 	= HELPER.createCompatBlock("quark", "stripped_driftwood_post", () -> new WoodPostBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_POST 			= HELPER.createCompatBlock("quark", "driftwood_post", () -> new WoodPostBlock(STRIPPED_DRIFTWOOD_POST, UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> DRIFTWOOD_BEEHIVE			= HELPER.createCompatBlock("buzzier_bees", "driftwood_beehive", () -> new AbnormalsBeehiveBlock(Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> DRIFTWOOD_SIGN = HELPER.createSignBlock("driftwood", MaterialColor.GRAY);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> DRIFTWOOD_CHEST 	= HELPER.createCompatChestBlocks("quark" ,"driftwood", MaterialColor.GRAY);

	public static final RegistryObject<Block> STRIPPED_RIVER_LOG 	= HELPER.createBlock("stripped_river_log", () -> new StrippedLogBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> STRIPPED_RIVER_WOOD 	= HELPER.createBlock("stripped_river_wood", () -> new StrippedWoodBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_LOG 			= HELPER.createBlock("river_log", () -> new AbnormalsLogBlock(STRIPPED_RIVER_LOG, UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_WOOD 			= HELPER.createBlock("river_wood", () -> new WoodBlock(STRIPPED_RIVER_WOOD, UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_LEAVES 			= HELPER.createBlock("river_leaves", () -> new AbnormalsLeavesBlock(UAProperties.LEAVES), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RIVER_SAPLING 		= HELPER.createBlock("river_sapling", () -> new AbnormalsSaplingBlock(new RiverTree(), Block.Properties.from(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> POTTED_RIVER_SAPLING 	= HELPER.createBlockNoItem("potted_river_sapling", () -> new FlowerPotBlock(RIVER_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));
	public static final RegistryObject<Block> RIVER_PLANKS 			= HELPER.createBlock("river_planks", () -> new PlanksBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_DOOR 			= HELPER.createBlock("river_door", () -> new WoodDoorBlock(UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> RIVER_SLAB 			= HELPER.createBlock("river_slab", () -> new WoodSlabBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_STAIRS 			= HELPER.createBlock("river_stairs", () -> new WoodStairsBlock(RIVER_PLANKS.get().getDefaultState(), UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_FENCE 			= HELPER.createFuelBlock("river_fence", () -> new WoodFenceBlock(UAProperties.RIVER_WOOD), 300, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RIVER_FENCE_GATE 		= HELPER.createFuelBlock("river_fence_gate", () -> new WoodFenceGateBlock(UAProperties.RIVER_WOOD), 300, ItemGroup.REDSTONE);
	public static final RegistryObject<Block> RIVER_PRESSURE_PLATE 	= HELPER.createBlock("river_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> RIVER_BUTTON 			= HELPER.createBlock("river_button", () -> new AbnormalsWoodButtonBlock(Properties.from(UABlocks.RIVER_WOOD.get()).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> RIVER_TRAPDOOR 		= HELPER.createBlock("river_trapdoor", () -> new WoodTrapDoorBlock(UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static final RegistryObject<Block> RIVER_LEAF_CARPET 	= HELPER.createCompatBlock("quark", "river_leaf_carpet", () -> new LeafCarpetBlock(UAProperties.LEAF_CARPET), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> VERTICAL_RIVER_PLANKS = HELPER.createCompatBlock("quark", "vertical_river_planks", () -> new Block(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_VERTICAL_SLAB 	= HELPER.createCompatFuelBlock("quark", "river_vertical_slab", () -> new VerticalSlabBlock(UAProperties.RIVER_WOOD), 150, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_BOOKSHELF 		= HELPER.createCompatFuelBlock("quark", "river_bookshelf", () -> new BookshelfBlock(Properties.from(Blocks.BOOKSHELF)), 300, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_LADDER 			= HELPER.createCompatFuelBlock("quark", "river_ladder", () -> new AbnormalsLadderBlock(Properties.from(Blocks.LADDER).harvestTool(ToolType.AXE)), 300, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> STRIPPED_RIVER_POST 	= HELPER.createCompatFuelBlock("quark", "stripped_river_post", () -> new WoodPostBlock(UAProperties.RIVER_WOOD), 300, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_POST 			= HELPER.createCompatFuelBlock("quark", "river_post", () -> new WoodPostBlock(STRIPPED_RIVER_POST, UAProperties.RIVER_WOOD), 300, ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> RIVER_HEDGE 			= HELPER.createCompatFuelBlock("quark", "river_hedge", () -> new HedgeBlock(UAProperties.RIVER_WOOD), 300, ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> RIVER_BEEHIVE			= HELPER.createCompatBlock("buzzier_bees", "river_beehive", () -> new AbnormalsBeehiveBlock(Properties.from(Blocks.BEEHIVE)), ItemGroup.DECORATIONS);
	public static final Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> RIVER_SIGN = HELPER.createSignBlock("river", MaterialColor.GRAY);
	public static final Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> RIVER_CHEST 	= HELPER.createCompatChestBlocks("quark" ,"river", MaterialColor.GRAY);

	public static final RegistryObject<Block> BEACHGRASS 						= HELPER.createBlock("beachgrass", () -> new BeachgrassBlock(Properties.from(Blocks.GRASS).harvestTool(ToolType.HOE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> TALL_BEACHGRASS 					= HELPER.createBlock("tall_beachgrass", () -> new TallBeachgrassBlock(Properties.from(Blocks.GRASS).harvestTool(ToolType.HOE)), ItemGroup.DECORATIONS);
	public static final RegistryObject<Block> BEACHGRASS_THATCH 				= HELPER.createBlock("beachgrass_thatch", () -> new ThatchBlock(UAProperties.BEACHGRASS_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEACHGRASS_THATCH_SLAB 			= HELPER.createBlock("beachgrass_thatch_slab", () -> new ThatchSlabBlock(UAProperties.BEACHGRASS_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEACHGRASS_THATCH_STAIRS 			= HELPER.createBlock("beachgrass_thatch_stairs", () -> new ThatchStairsBlock(BEACHGRASS_THATCH.get().getDefaultState(), UAProperties.BEACHGRASS_THATCH), ItemGroup.BUILDING_BLOCKS);
	public static final RegistryObject<Block> BEACHGRASS_THATCH_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "beachgrass_thatch_vertical_slab", () -> new ThatchVerticalSlabBlock(UAProperties.BEACHGRASS_THATCH), ItemGroup.BUILDING_BLOCKS);

	public static final RegistryObject<Block> LUMINOUS_PRISMARINE				= HELPER.createBlock("luminous_prismarine", () -> new ConduitSupportingBlock(UAProperties.LUMINOUS_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LUMINOUS_PRISMARINE_STAIRS 		= HELPER.createBlock("luminous_prismarine_stairs", () -> new AbnormalsStairsBlock(LUMINOUS_PRISMARINE.get().getDefaultState(), UAProperties.LUMINOUS_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LUMINOUS_PRISMARINE_SLAB 			= HELPER.createBlock("luminous_prismarine_slab", () -> new SlabBlock(UAProperties.LUMINOUS_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LUMINOUS_PRISMARINE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "luminous_prismarine_vertical_slab", () -> new VerticalSlabBlock(UAProperties.LUMINOUS_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
	
    public static final RegistryObject<Block> LUMINOUS_ELDER_PRISMARINE					= HELPER.createCompatBlock("quark", "luminous_elder_prismarine", () -> new ConduitSupportingBlock(UAProperties.LUMINOUS_ELDER_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LUMINOUS_ELDER_PRISMARINE_STAIRS 			= HELPER.createCompatBlock("quark", "luminous_elder_prismarine_stairs", () -> new AbnormalsStairsBlock(LUMINOUS_ELDER_PRISMARINE.get().getDefaultState(), UAProperties.LUMINOUS_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LUMINOUS_ELDER_PRISMARINE_SLAB 			= HELPER.createCompatBlock("quark", "luminous_elder_prismarine_slab", () -> new SlabBlock(UAProperties.LUMINOUS_ELDER_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LUMINOUS_ELDER_PRISMARINE_VERTICAL_SLAB 	= HELPER.createCompatBlock("quark", "luminous_elder_prismarine_vertical_slab", () -> new VerticalSlabBlock(UAProperties.LUMINOUS_ELDER_PRISMARINE), ItemGroup.BUILDING_BLOCKS);
	
	private static Supplier<BedrollBlock> createBedroll(DyeColor color) {
		return () -> new BedrollBlock(color, AbstractBlock.Properties.create(Material.WOOL, (state) -> {
			return state.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MaterialColor.WOOL;
		}).sound(SoundType.CLOTH).hardnessAndResistance(0.2F, 0.3F).notSolid());
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

	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_VERTICAL_SLAB);
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_VERTICAL_SLAB);
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_VERTICAL_SLAB);
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_VERTICAL_SLAB);
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_VERTICAL_SLAB);
		conversions.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_VERTICAL_SLAB);
		conversions.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_VERTICAL_SLAB);
		conversions.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_VERTICAL_SLAB);
		conversions.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_VERTICAL_SLAB);
		conversions.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_VERTICAL_SLAB);
		conversions.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_VERTICAL_SLAB);
		conversions.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_VERTICAL_SLAB);
		conversions.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_VERTICAL_SLAB);
		conversions.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_VERTICAL_SLAB);
		conversions.put(CHROME_CORAL_BLOCK, CHROME_CORALSTONE_VERTICAL_SLAB);
		conversions.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_VERTICAL_SLAB);
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> FALLABLES = Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> Blocks.SANDSTONE, () -> Blocks.SAND);
		fallables.put(() -> Blocks.RED_SANDSTONE, () -> Blocks.RED_SAND);
		fallables.put(() -> Blocks.COBBLESTONE, () -> Blocks.GRAVEL);
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> ATMOSHPERIC_FALLABLES = ModList.get().isLoaded("atmospheric") ? Util.make(Maps.newHashMap(), (fallables) -> {
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:arid_sand")));
		fallables.put(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:red_arid_sandstone")), () -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric:red_arid_sand")));
	}) : null;
	

	public static enum KelpType {
		TONGUE(0.14D), THORNY(0.14D), OCHRE(0.14D), POLAR(0.14D);

		private double growChance;

		KelpType(double growChance) {
			this.growChance = growChance;
		}

		public double getGrowChance() {
			return this.growChance;
		}
	}
}