package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBeachgrass;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBeachgrassTall;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBedroll;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockCoralShower;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockCoralShowerDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockEmbeddedAmmonite;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockFloweringRush;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorchWall;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockLeafCarpet;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweedBlock;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweedDouble;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockSapling;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockSearocket;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockSpine;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockThatch;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockToothDoor;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockToothLantern;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockToothTrapdoor;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUABookshelf;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoral;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralFan;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralFanDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralWallFan;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralWallFanDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUAKelp;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUAKelpTop;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockVerticalSlab;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstone;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneSlab;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneStairs;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneVerticalSlab;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneWall;
import com.teamabnormals.upgrade_aquatic.core.registry.other.UAProperties;
import com.teamabnormals.upgrade_aquatic.core.registry.util.RegistryUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.CoralBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.OakTree;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Util;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UABlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MODID);
	
	public static RegistryObject<Block> DEAD_ACAN_CORAL_BLOCK         = RegistryUtils.createBlock("dead_acan_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_FINGER_CORAL_BLOCK       = RegistryUtils.createBlock("dead_finger_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_STAR_CORAL_BLOCK         = RegistryUtils.createBlock("dead_star_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_MOSS_CORAL_BLOCK         = RegistryUtils.createBlock("dead_moss_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_PETAL_CORAL_BLOCK        = RegistryUtils.createBlock("dead_petal_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_BRANCH_CORAL_BLOCK       = RegistryUtils.createBlock("dead_branch_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_ROCK_CORAL_BLOCK         = RegistryUtils.createBlock("dead_rock_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_PILLOW_CORAL_BLOCK       = RegistryUtils.createBlock("dead_pillow_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_SILK_CORAL_BLOCK         = RegistryUtils.createBlock("dead_silk_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_CHROME_CORAL_BLOCK       = RegistryUtils.createBlock("dead_chrome_coral_block", () -> new Block(UAProperties.DEAD_CORAL_BLOCK), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORAL_BLOCK  = RegistryUtils.createBlock("elder_prismarine_coral_block", () -> new Block(UAProperties.PRISMARINE_CORAL_BLOCK_BASE(true)), ItemGroup.BUILDING_BLOCKS);

	public static RegistryObject<Block> ACAN_CORAL_BLOCK              = RegistryUtils.createBlock("acan_coral_block", () -> new CoralBlock(DEAD_ACAN_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.CYAN)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FINGER_CORAL_BLOCK            = RegistryUtils.createBlock("finger_coral_block", () -> new CoralBlock(DEAD_FINGER_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.ORANGE_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STAR_CORAL_BLOCK              = RegistryUtils.createBlock("star_coral_block", () -> new CoralBlock(DEAD_STAR_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.LIME)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> MOSS_CORAL_BLOCK              = RegistryUtils.createBlock("moss_coral_block", () -> new CoralBlock(DEAD_MOSS_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.GREEN)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PETAL_CORAL_BLOCK             = RegistryUtils.createBlock("petal_coral_block", () -> new CoralBlock(DEAD_PETAL_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.LIGHT_BLUE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRANCH_CORAL_BLOCK            = RegistryUtils.createBlock("branch_coral_block", () -> new CoralBlock(DEAD_BRANCH_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ROCK_CORAL_BLOCK              = RegistryUtils.createBlock("rock_coral_block", () -> new CoralBlock(DEAD_ROCK_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.BROWN)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PILLOW_CORAL_BLOCK            = RegistryUtils.createBlock("pillow_coral_block", () -> new CoralBlock(DEAD_PILLOW_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.WHITE_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> SILK_CORAL_BLOCK              = RegistryUtils.createBlock("silk_coral_block", () -> new CoralBlock(DEAD_SILK_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.PURPLE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> CHROME_CORAL_BLOCK            = RegistryUtils.createBlock("chrome_coral_block", () -> new CoralBlock(DEAD_CHROME_CORAL_BLOCK.get(), UAProperties.CORAL_BLOCK_BASE(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PRISMARINE_CORAL_BLOCK        = RegistryUtils.createBlock("prismarine_coral_block", () -> new CoralBlock(ELDER_PRISMARINE_CORAL_BLOCK.get(), UAProperties.PRISMARINE_CORAL_BLOCK_BASE(false)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> DEAD_ACAN_CORAL               = RegistryUtils.createBlock("dead_acan_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_FINGER_CORAL             = RegistryUtils.createBlock("dead_finger_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_STAR_CORAL               = RegistryUtils.createBlock("dead_star_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_MOSS_CORAL               = RegistryUtils.createBlock("dead_moss_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_PETAL_CORAL              = RegistryUtils.createBlock("dead_petal_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_BRANCH_CORAL             = RegistryUtils.createBlock("dead_branch_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_ROCK_CORAL               = RegistryUtils.createBlock("dead_rock_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_PILLOW_CORAL             = RegistryUtils.createBlock("dead_pillow_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_SILK_CORAL               = RegistryUtils.createBlock("dead_silk_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_CHROME_CORAL             = RegistryUtils.createBlock("dead_chrome_coral", () -> new BlockUACoralDead(UAProperties.DEAD_CORAL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORAL        = RegistryUtils.createBlock("elder_prismarine_coral", () -> new BlockUACoralDead(UAProperties.PRISMARINE_CORAL_BASE(true)), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> ACAN_CORAL               	  = RegistryUtils.createBlock("acan_coral", () -> new BlockUACoral(DEAD_ACAN_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.CYAN)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> FINGER_CORAL             	  = RegistryUtils.createBlock("finger_coral", () -> new BlockUACoral(DEAD_FINGER_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> STAR_CORAL                    = RegistryUtils.createBlock("star_coral", () -> new BlockUACoral(DEAD_STAR_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.LIME)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> MOSS_CORAL                    = RegistryUtils.createBlock("moss_coral", () -> new BlockUACoral(DEAD_MOSS_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.GREEN)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PETAL_CORAL                   = RegistryUtils.createBlock("petal_coral", () -> new BlockUACoral(DEAD_PETAL_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BRANCH_CORAL                  = RegistryUtils.createBlock("branch_coral", () -> new BlockUACoral(DEAD_BRANCH_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ROCK_CORAL                    = RegistryUtils.createBlock("rock_coral", () -> new BlockUACoral(DEAD_ROCK_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.BROWN_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PILLOW_CORAL                  = RegistryUtils.createBlock("pillow_coral", () -> new BlockUACoral(DEAD_PILLOW_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.WHITE_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> SILK_CORAL                    = RegistryUtils.createBlock("silk_coral", () -> new BlockUACoral(DEAD_SILK_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.PURPLE_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> CHROME_CORAL                  = RegistryUtils.createBlock("chrome_coral", () -> new BlockUACoral(DEAD_CHROME_CORAL.get(), UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PRISMARINE_CORAL              = RegistryUtils.createBlock("prismarine_coral", () -> new BlockUACoral(ELDER_PRISMARINE_CORAL.get(), UAProperties.PRISMARINE_CORAL_BASE(false)), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> DEAD_ACAN_CORAL_WALL_FAN      = RegistryUtils.createBlockNoItem("dead_acan_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_FINGER_CORAL_WALL_FAN    = RegistryUtils.createBlockNoItem("dead_finger_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_STAR_CORAL_WALL_FAN      = RegistryUtils.createBlockNoItem("dead_star_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_MOSS_CORAL_WALL_FAN      = RegistryUtils.createBlockNoItem("dead_moss_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_PETAL_CORAL_WALL_FAN     = RegistryUtils.createBlockNoItem("dead_petal_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_BRANCH_CORAL_WALL_FAN    = RegistryUtils.createBlockNoItem("dead_branch_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_ROCK_CORAL_WALL_FAN      = RegistryUtils.createBlockNoItem("dead_rock_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_PILLOW_CORAL_WALL_FAN    = RegistryUtils.createBlockNoItem("dead_pillow_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_SILK_CORAL_WALL_FAN      = RegistryUtils.createBlockNoItem("dead_silk_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> DEAD_CHROME_CORAL_WALL_FAN    = RegistryUtils.createBlockNoItem("dead_chrome_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL));
	public static RegistryObject<Block> ELDER_PRISMARINE_CORAL_WALL_FAN = RegistryUtils.createBlockNoItem("elder_prismarine_coral_wall_fan", () -> new BlockUACoralWallFanDead(UAProperties.PRISMARINE_CORAL_BASE(true)));
	
	public static RegistryObject<Block> ACAN_CORAL_WALL_FAN           = RegistryUtils.createBlockNoItem("acan_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_ACAN_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.CYAN)));
	public static RegistryObject<Block> FINGER_CORAL_WALL_FAN         = RegistryUtils.createBlockNoItem("finger_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_FINGER_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.ORANGE_TERRACOTTA)));
	public static RegistryObject<Block> STAR_CORAL_WALL_FAN           = RegistryUtils.createBlockNoItem("star_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_STAR_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.LIME)));
	public static RegistryObject<Block> MOSS_CORAL_WALL_FAN           = RegistryUtils.createBlockNoItem("moss_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_MOSS_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.GREEN)));
	public static RegistryObject<Block> PETAL_CORAL_WALL_FAN          = RegistryUtils.createBlockNoItem("petal_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_PETAL_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.LIGHT_BLUE)));
	public static RegistryObject<Block> BRANCH_CORAL_WALL_FAN         = RegistryUtils.createBlockNoItem("branch_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_BRANCH_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.GRAY_TERRACOTTA)));
	public static RegistryObject<Block> ROCK_CORAL_WALL_FAN           = RegistryUtils.createBlockNoItem("rock_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_ROCK_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.BROWN_TERRACOTTA)));
	public static RegistryObject<Block> PILLOW_CORAL_WALL_FAN         = RegistryUtils.createBlockNoItem("pillow_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_PILLOW_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.WHITE_TERRACOTTA)));
	public static RegistryObject<Block> SILK_CORAL_WALL_FAN           = RegistryUtils.createBlockNoItem("silk_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_SILK_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.PURPLE_TERRACOTTA)));
	public static RegistryObject<Block> CHROME_CORAL_WALL_FAN         = RegistryUtils.createBlockNoItem("chrome_coral_wall_fan", () -> new BlockUACoralWallFan(DEAD_CHROME_CORAL_WALL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.GRAY_TERRACOTTA)));
	public static RegistryObject<Block> PRISMARINE_CORAL_WALL_FAN     = RegistryUtils.createBlockNoItem("prismarine_coral_wall_fan", () -> new BlockUACoralWallFan(ELDER_PRISMARINE_CORAL_WALL_FAN.get(), UAProperties.PRISMARINE_CORAL_BASE(false)));
	
	public static RegistryObject<Block> DEAD_ACAN_CORAL_FAN           = RegistryUtils.createWallOrFloorBlock("dead_acan_coral_fan", () -> new BlockUACoralFanDead(), DEAD_ACAN_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_FINGER_CORAL_FAN         = RegistryUtils.createWallOrFloorBlock("dead_finger_coral_fan", () -> new BlockUACoralFanDead(), DEAD_FINGER_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_STAR_CORAL_FAN           = RegistryUtils.createWallOrFloorBlock("dead_star_coral_fan", () -> new BlockUACoralFanDead(), DEAD_STAR_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_MOSS_CORAL_FAN           = RegistryUtils.createWallOrFloorBlock("dead_moss_coral_fan", () -> new BlockUACoralFanDead(), DEAD_MOSS_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_PETAL_CORAL_FAN          = RegistryUtils.createWallOrFloorBlock("dead_petal_coral_fan", () -> new BlockUACoralFanDead(), DEAD_PETAL_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_BRANCH_CORAL_FAN         = RegistryUtils.createWallOrFloorBlock("dead_branch_coral_fan", () -> new BlockUACoralFanDead(), DEAD_BRANCH_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_ROCK_CORAL_FAN           = RegistryUtils.createWallOrFloorBlock("dead_rock_coral_fan", () -> new BlockUACoralFanDead(), DEAD_ROCK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_PILLOW_CORAL_FAN         = RegistryUtils.createWallOrFloorBlock("dead_pillow_coral_fan", () -> new BlockUACoralFanDead(), DEAD_PILLOW_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_SILK_CORAL_FAN           = RegistryUtils.createWallOrFloorBlock("dead_silk_coral_fan", () -> new BlockUACoralFanDead(), DEAD_SILK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_CHROME_CORAL_FAN         = RegistryUtils.createWallOrFloorBlock("dead_chrome_coral_fan", () -> new BlockUACoralFanDead(), DEAD_CHROME_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORAL_FAN    = RegistryUtils.createWallOrFloorBlock("elder_prismarine_coral_fan", () -> new BlockUACoralFanDead(UAProperties.PRISMARINE_CORAL_BASE(true)), ELDER_PRISMARINE_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> ACAN_CORAL_FAN           	  = RegistryUtils.createWallOrFloorBlock("acan_coral_fan", () -> new BlockUACoralFan(DEAD_ACAN_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.CYAN)), ACAN_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> FINGER_CORAL_FAN              = RegistryUtils.createWallOrFloorBlock("finger_coral_fan", () -> new BlockUACoralFan(DEAD_FINGER_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.ORANGE_TERRACOTTA)), FINGER_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> STAR_CORAL_FAN                = RegistryUtils.createWallOrFloorBlock("star_coral_fan", () -> new BlockUACoralFan(DEAD_STAR_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.LIME)), STAR_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> MOSS_CORAL_FAN                = RegistryUtils.createWallOrFloorBlock("moss_coral_fan", () -> new BlockUACoralFan(DEAD_MOSS_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.GREEN)), MOSS_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PETAL_CORAL_FAN               = RegistryUtils.createWallOrFloorBlock("petal_coral_fan", () -> new BlockUACoralFan(DEAD_PETAL_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.LIGHT_BLUE)), PETAL_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BRANCH_CORAL_FAN              = RegistryUtils.createWallOrFloorBlock("branch_coral_fan", () -> new BlockUACoralFan(DEAD_BRANCH_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.GRAY_TERRACOTTA)), BRANCH_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ROCK_CORAL_FAN                = RegistryUtils.createWallOrFloorBlock("rock_coral_fan", () -> new BlockUACoralFan(DEAD_ROCK_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.BROWN_TERRACOTTA)), ROCK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PILLOW_CORAL_FAN              = RegistryUtils.createWallOrFloorBlock("pillow_coral_fan", () -> new BlockUACoralFan(DEAD_PILLOW_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.WHITE_TERRACOTTA)), PILLOW_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> SILK_CORAL_FAN                = RegistryUtils.createWallOrFloorBlock("silk_coral_fan", () -> new BlockUACoralFan(DEAD_SILK_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.PURPLE_TERRACOTTA)), SILK_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> CHROME_CORAL_FAN              = RegistryUtils.createWallOrFloorBlock("chrome_coral_fan", () -> new BlockUACoralFan(DEAD_CHROME_CORAL_FAN.get(), UAProperties.CORAL_FAN_BASE(MaterialColor.GRAY_TERRACOTTA)), CHROME_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PRISMARINE_CORAL_FAN          = RegistryUtils.createWallOrFloorBlock("prismarine_coral_fan", () -> new BlockUACoralFan(ELDER_PRISMARINE_CORAL_FAN.get(), UAProperties.PRISMARINE_CORAL_BASE(false)), PRISMARINE_CORAL_WALL_FAN, ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> ELDER_PRISMARINE_CORAL_SHOWER = RegistryUtils.createBlock("elder_prismarine_coral_shower", () -> new BlockCoralShowerDead(UAProperties.PRISMARINE_CORAL_BASE(true)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PRISMARINE_CORAL_SHOWER       = RegistryUtils.createBlock("prismarine_coral_shower", () -> new BlockCoralShower(ELDER_PRISMARINE_CORAL_SHOWER.get(), UAProperties.PRISMARINE_CORAL_BASE(false)), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> ELDER_GUARDIAN_SPINE          = RegistryUtils.createBlock("elder_guardian_spine", () -> new BlockSpine(UAProperties.SPINES, true), ItemGroup.REDSTONE);
	public static RegistryObject<Block> GUARDIAN_SPINE                = RegistryUtils.createBlock("guardian_spine", () -> new BlockSpine(UAProperties.SPINES, false), ItemGroup.REDSTONE);
	public static RegistryObject<Block> ELDER_EYE                     = RegistryUtils.createRareBlock("elder_eye", () -> new BlockElderEye(UAProperties.ELDER_EYE), Rarity.RARE, ItemGroup.REDSTONE);
	
	public static RegistryObject<Block> PINK_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_pink", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PINK));
	public static RegistryObject<Block> PURPLE_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_purple", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PURPLE));
	public static RegistryObject<Block> BLUE_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_blue", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.BLUE));
	public static RegistryObject<Block> GREEN_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_green", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.GREEN));
	public static RegistryObject<Block> YELLOW_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_yellow", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.YELLOW));
	public static RegistryObject<Block> ORANGE_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_orange", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.ORANGE));
	public static RegistryObject<Block> RED_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_red", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.RED));
	public static RegistryObject<Block> WHITE_JELLY_WALL_TORCH	= RegistryUtils.createBlockNoItem("jelly_torch_wall_white", () -> new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.WHITE));
	
	public static RegistryObject<Block> PINK_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_pink", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PINK), PINK_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PURPLE_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_purple", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PURPLE), PURPLE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BLUE_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_blue", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.BLUE), BLUE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> GREEN_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_green", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.GREEN), GREEN_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> YELLOW_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_yellow", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.YELLOW), YELLOW_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ORANGE_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_orange", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.ORANGE), ORANGE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
	public static RegistryObject<Block> RED_JELLY_TORCH		= RegistryUtils.createWallOrFloorBlock("jelly_torch_red", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.RED), RED_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);
    public static RegistryObject<Block> WHITE_JELLY_TORCH	= RegistryUtils.createWallOrFloorBlock("jelly_torch_white", () -> new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.WHITE), WHITE_JELLY_WALL_TORCH, ItemGroup.DECORATIONS);

	public static RegistryObject<Block> EMBEDDED_AMMONITE	= RegistryUtils.createBlock("embedded_ammonite", () -> new BlockEmbeddedAmmonite(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> BEDROLL				= RegistryUtils.createBlock("bedroll_leather", () -> new BlockBedroll(DyeColor.BROWN, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> WHITE_BEDROLL		= RegistryUtils.createBlock("bedroll_white", () -> new BlockBedroll(DyeColor.WHITE, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ORANGE_BEDROLL		= RegistryUtils.createBlock("bedroll_orange", () -> new BlockBedroll(DyeColor.ORANGE, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> MAGENTA_BEDROLL		= RegistryUtils.createBlock("bedroll_magenta", () -> new BlockBedroll(DyeColor.MAGENTA, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> LIGHT_BLUE_BEDROLL	= RegistryUtils.createBlock("bedroll_light_blue", () -> new BlockBedroll(DyeColor.LIGHT_BLUE, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> YELLOW_BEDROLL		= RegistryUtils.createBlock("bedroll_yellow", () -> new BlockBedroll(DyeColor.YELLOW, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> LIME_BEDROLL		= RegistryUtils.createBlock("bedroll_lime", () -> new BlockBedroll(DyeColor.LIME, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> PINK_BEDROLL		= RegistryUtils.createBlock("bedroll_pink", () -> new BlockBedroll(DyeColor.PINK, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> GRAY_BEDROLL		= RegistryUtils.createBlock("bedroll_gray", () -> new BlockBedroll(DyeColor.GRAY, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> LIGHT_GRAY_BEDROLL	= RegistryUtils.createBlock("bedroll_light_gray", () -> new BlockBedroll(DyeColor.LIGHT_GRAY, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> CYAN_BEDROLL		= RegistryUtils.createBlock("bedroll_cyan", () -> new BlockBedroll(DyeColor.CYAN, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> PURPLE_BEDROLL		= RegistryUtils.createBlock("bedroll_purple", () -> new BlockBedroll(DyeColor.PURPLE, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> BLUE_BEDROLL		= RegistryUtils.createBlock("bedroll_blue", () -> new BlockBedroll(DyeColor.BLUE, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> BROWN_BEDROLL		= RegistryUtils.createBlock("bedroll_brown", () -> new BlockBedroll(DyeColor.BROWN, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> GREEN_BEDROLL		= RegistryUtils.createBlock("bedroll_green", () -> new BlockBedroll(DyeColor.GREEN, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> RED_BEDROLL			= RegistryUtils.createBlock("bedroll_red", () -> new BlockBedroll(DyeColor.RED, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> BLACK_BEDROLL		= RegistryUtils.createBlock("bedroll_black", () -> new BlockBedroll(DyeColor.BLACK, UAProperties.BEDROLL), ItemGroup.DECORATIONS);
	
    public static RegistryObject<Block> BLUE_PICKERELWEED			= RegistryUtils.createBlock("pickerel_weed_blue", () -> new BlockPickerelweed(UAProperties.PICKERELWEED), ItemGroup.MISC);
    public static RegistryObject<Block> TALL_BLUE_PICKERELWEED		= RegistryUtils.createBlockNoItem("pickerel_weed_tall_blue", () -> new BlockPickerelweedDouble(UAProperties.PICKERELWEED));
    public static RegistryObject<Block> PURPLE_PICKERELWEED			= RegistryUtils.createBlock("pickerel_weed_purple", () -> new BlockPickerelweed(UAProperties.PICKERELWEED), ItemGroup.MISC);
    public static RegistryObject<Block> TALL_PURPLE_PICKERELWEED	= RegistryUtils.createBlockNoItem("pickerel_weed_tall_purple", () -> new BlockPickerelweedDouble(UAProperties.PICKERELWEED));
    
    public static RegistryObject<Block> WHITE_SEAROCKET	= RegistryUtils.createBlock("searocket_white", () -> new BlockSearocket(Effects.WATER_BREATHING, 9, UAProperties.SEAROCKET(false)), ItemGroup.DECORATIONS);
    public static RegistryObject<Block> PINK_SEAROCKET	= RegistryUtils.createBlock("searocket_pink", () -> new BlockSearocket(Effects.WATER_BREATHING, 9, UAProperties.SEAROCKET(true)), ItemGroup.DECORATIONS);
    
    public static RegistryObject<Block> POTTED_BLUE_PICKERELWEED 	= RegistryUtils.createBlockNoItem("potted_pickerelweed_blue", () -> new FlowerPotBlock(BLUE_PICKERELWEED.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
	public static RegistryObject<Block> POTTED_PURPLE_PICKERELWEED 	= RegistryUtils.createBlockNoItem("potted_pickerelweed_purple", () -> new FlowerPotBlock(PURPLE_PICKERELWEED.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
    public static RegistryObject<Block> POTTED_WHITE_SEAROCKET 		= RegistryUtils.createBlockNoItem("potted_searocket_white", () -> new FlowerPotBlock(WHITE_SEAROCKET.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
    public static RegistryObject<Block> POTTED_PINK_SEAROCKET 		= RegistryUtils.createBlockNoItem("potted_searocket_pink", () -> new FlowerPotBlock(PINK_SEAROCKET.get(), Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
	
	public static RegistryObject<Block> TOOTH_BLOCK					= RegistryUtils.createBlock("tooth_block", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> TOOTH_TILES					= RegistryUtils.createBlock("tooth_tiles", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> TOOTH_STAIRS				= RegistryUtils.createBlock("tooth_stairs", () -> new StairsBlock(TOOTH_BLOCK.get().getDefaultState(), Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> TOOTH_SLAB					= RegistryUtils.createBlock("tooth_slab", () -> new SlabBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> TOOTH_WALL					= RegistryUtils.createBlock("tooth_wall", () -> new WallBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> TOOTH_VERTICAL_SLAB			= RegistryUtils.createCompatBlock("tooth_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(TOOTH_TILES.get())), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> TOOTH_BRICKS				= RegistryUtils.createBlock("tooth_bricks", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> CHISELED_TOOTH_BRICKS		= RegistryUtils.createBlock("chiseled_tooth_bricks", () -> new Block(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> TOOTH_BRICK_STAIRS			= RegistryUtils.createBlock("tooth_brick_stairs", () -> new StairsBlock(TOOTH_BLOCK.get().getDefaultState(), Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> TOOTH_BRICK_SLAB			= RegistryUtils.createBlock("tooth_brick_slab", () -> new SlabBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> TOOTH_BRICK_WALL			= RegistryUtils.createBlock("tooth_brick_wall", () -> new WallBlock(Properties.from(Blocks.END_STONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> TOOTH_BRICK_VERTICAL_SLAB	= RegistryUtils.createCompatBlock("tooth_brick_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(TOOTH_BRICKS.get())), ItemGroup.BUILDING_BLOCKS);
    public static RegistryObject<Block> TOOTH_TRAPDOOR				= RegistryUtils.createBlock("tooth_trapdoor", () -> new BlockToothTrapdoor(Properties.from(Blocks.END_STONE)), ItemGroup.REDSTONE);
	public static RegistryObject<Block> TOOTH_DOOR					= RegistryUtils.createBlock("tooth_door", () -> new BlockToothDoor(Properties.from(Blocks.END_STONE)), ItemGroup.REDSTONE);
	public static RegistryObject<Block> TOOTH_LANTERN				= RegistryUtils.createBlock("tooth_lantern", () -> new BlockToothLantern(Properties.from(Blocks.END_STONE).sound(UASounds.TOOTH_LANTERN).notSolid().lightValue(15)), ItemGroup.DECORATIONS);

	public static RegistryObject<Block> CORALSTONE                       = RegistryUtils.createBlock("coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BUBBLE_CORALSTONE                = RegistryUtils.createBlock("bubble_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> HORN_CORALSTONE             	 = RegistryUtils.createBlock("horn_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TUBE_CORALSTONE                  = RegistryUtils.createBlock("tube_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRAIN_CORALSTONE                 = RegistryUtils.createBlock("brain_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FIRE_CORALSTONE                  = RegistryUtils.createBlock("fire_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> ACAN_CORALSTONE               	 = RegistryUtils.createBlock("acan_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FINGER_CORALSTONE           	 = RegistryUtils.createBlock("finger_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STAR_CORALSTONE                  = RegistryUtils.createBlock("star_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> MOSS_CORALSTONE                  = RegistryUtils.createBlock("moss_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PETAL_CORALSTONE                 = RegistryUtils.createBlock("petal_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRANCH_CORALSTONE                = RegistryUtils.createBlock("branch_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ROCK_CORALSTONE                  = RegistryUtils.createBlock("rock_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PILLOW_CORALSTONE                = RegistryUtils.createBlock("pillow_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> SILK_CORALSTONE                  = RegistryUtils.createBlock("silk_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> CHROME_CORALSTONE                = RegistryUtils.createBlock("chrome_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PRISMARINE_CORALSTONE            = RegistryUtils.createBlock("prismarine_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE      = RegistryUtils.createBlock("elder_prismarine_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_CORALSTONE                  = RegistryUtils.createBlock("dead_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, false), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> CHISELED_CORALSTONE              = RegistryUtils.createBlock("chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BUBBLE_CHISELED_CORALSTONE       = RegistryUtils.createBlock("bubble_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> HORN_CHISELED_CORALSTONE         = RegistryUtils.createBlock("horn_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TUBE_CHISELED_CORALSTONE         = RegistryUtils.createBlock("tube_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRAIN_CHISELED_CORALSTONE        = RegistryUtils.createBlock("brain_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FIRE_CHISELED_CORALSTONE         = RegistryUtils.createBlock("fire_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> ACAN_CHISELED_CORALSTONE         = RegistryUtils.createBlock("acan_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FINGER_CHISELED_CORALSTONE       = RegistryUtils.createBlock("finger_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STAR_CHISELED_CORALSTONE         = RegistryUtils.createBlock("star_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> MOSS_CHISELED_CORALSTONE         = RegistryUtils.createBlock("moss_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PETAL_CHISELED_CORALSTONE        = RegistryUtils.createBlock("petal_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRANCH_CHISELED_CORALSTONE       = RegistryUtils.createBlock("branch_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ROCK_CHISELED_CORALSTONE         = RegistryUtils.createBlock("rock_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PILLOW_CHISELED_CORALSTONE       = RegistryUtils.createBlock("pillow_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> SILK_CHISELED_CORALSTONE         = RegistryUtils.createBlock("silk_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> CHROME_CHISELED_CORALSTONE       = RegistryUtils.createBlock("chrome_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PRISMARINE_CHISELED_CORALSTONE   = RegistryUtils.createBlock("prismarine_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CHISELED_CORALSTONE = RegistryUtils.createBlock("elder_prismarine_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_CHISELED_CORALSTONE         = RegistryUtils.createBlock("dead_chiseled_coralstone", () -> new BlockCoralstone(UAProperties.CORALSTONE, true), ItemGroup.BUILDING_BLOCKS);
    
	public static RegistryObject<Block> CORALSTONE_SLAB                  = RegistryUtils.createBlock("coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BUBBLE_CORALSTONE_SLAB           = RegistryUtils.createBlock("bubble_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> HORN_CORALSTONE_SLAB             = RegistryUtils.createBlock("horn_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TUBE_CORALSTONE_SLAB             = RegistryUtils.createBlock("tube_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRAIN_CORALSTONE_SLAB            = RegistryUtils.createBlock("brain_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FIRE_CORALSTONE_SLAB             = RegistryUtils.createBlock("fire_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> ACAN_CORALSTONE_SLAB             = RegistryUtils.createBlock("acan_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FINGER_CORALSTONE_SLAB           = RegistryUtils.createBlock("finger_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STAR_CORALSTONE_SLAB             = RegistryUtils.createBlock("star_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> MOSS_CORALSTONE_SLAB             = RegistryUtils.createBlock("moss_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PETAL_CORALSTONE_SLAB            = RegistryUtils.createBlock("petal_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRANCH_CORALSTONE_SLAB           = RegistryUtils.createBlock("branch_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ROCK_CORALSTONE_SLAB             = RegistryUtils.createBlock("rock_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PILLOW_CORALSTONE_SLAB           = RegistryUtils.createBlock("pillow_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> SILK_CORALSTONE_SLAB             = RegistryUtils.createBlock("silk_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> CHROME_CORALSTONE_SLAB           = RegistryUtils.createBlock("chrome_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PRISMARINE_CORALSTONE_SLAB       = RegistryUtils.createBlock("prismarine_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, new Block[] {PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_SLAB = RegistryUtils.createBlock("elder_prismarine_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_CORALSTONE_SLAB             = RegistryUtils.createBlock("dead_coralstone_slab", () -> new BlockCoralstoneSlab(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);	
	
	public static RegistryObject<Block> CORALSTONE_STAIRS                = RegistryUtils.createBlock("coralstone_stairs", () -> new BlockCoralstoneStairs(() -> CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BUBBLE_CORALSTONE_STAIRS         = RegistryUtils.createBlock("bubble_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> BUBBLE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> HORN_CORALSTONE_STAIRS           = RegistryUtils.createBlock("horn_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> HORN_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TUBE_CORALSTONE_STAIRS           = RegistryUtils.createBlock("tube_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> TUBE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRAIN_CORALSTONE_STAIRS          = RegistryUtils.createBlock("brain_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> BRAIN_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FIRE_CORALSTONE_STAIRS           = RegistryUtils.createBlock("fire_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> FIRE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ACAN_CORALSTONE_STAIRS           = RegistryUtils.createBlock("acan_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> ACAN_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FINGER_CORALSTONE_STAIRS         = RegistryUtils.createBlock("finger_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> FINGER_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STAR_CORALSTONE_STAIRS           = RegistryUtils.createBlock("star_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> STAR_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> MOSS_CORALSTONE_STAIRS           = RegistryUtils.createBlock("moss_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> MOSS_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PETAL_CORALSTONE_STAIRS          = RegistryUtils.createBlock("petal_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> PETAL_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRANCH_CORALSTONE_STAIRS         = RegistryUtils.createBlock("branch_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> BRANCH_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ROCK_CORALSTONE_STAIRS           = RegistryUtils.createBlock("rock_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> ROCK_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PILLOW_CORALSTONE_STAIRS         = RegistryUtils.createBlock("pillow_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> PILLOW_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> SILK_CORALSTONE_STAIRS           = RegistryUtils.createBlock("silk_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> SILK_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> CHROME_CORALSTONE_STAIRS         = RegistryUtils.createBlock("chrome_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> CHROME_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PRISMARINE_CORALSTONE_STAIRS     = RegistryUtils.createBlock("prismarine_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> PRISMARINE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, new Block[] {PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_STAIRS = RegistryUtils.createBlock("elder_prismarine_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> ELDER_PRISMARINE_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_CORALSTONE_STAIRS           = RegistryUtils.createBlock("dead_coralstone_stairs", () -> new BlockCoralstoneStairs(() -> DEAD_CORALSTONE.get().getDefaultState(), UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> CORALSTONE_WALL                  = RegistryUtils.createBlock("coralstone_wall", () -> new BlockCoralstoneWall(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BUBBLE_CORALSTONE_WALL           = RegistryUtils.createBlock("bubble_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> HORN_CORALSTONE_WALL             = RegistryUtils.createBlock("horn_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> TUBE_CORALSTONE_WALL             = RegistryUtils.createBlock("tube_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BRAIN_CORALSTONE_WALL            = RegistryUtils.createBlock("brain_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> FIRE_CORALSTONE_WALL             = RegistryUtils.createBlock("fire_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);	
	public static RegistryObject<Block> ACAN_CORALSTONE_WALL             = RegistryUtils.createBlock("acan_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> FINGER_CORALSTONE_WALL           = RegistryUtils.createBlock("finger_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> STAR_CORALSTONE_WALL             = RegistryUtils.createBlock("star_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> MOSS_CORALSTONE_WALL             = RegistryUtils.createBlock("moss_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PETAL_CORALSTONE_WALL            = RegistryUtils.createBlock("petal_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BRANCH_CORALSTONE_WALL           = RegistryUtils.createBlock("branch_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ROCK_CORALSTONE_WALL             = RegistryUtils.createBlock("rock_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PILLOW_CORALSTONE_WALL           = RegistryUtils.createBlock("pillow_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> SILK_CORALSTONE_WALL             = RegistryUtils.createBlock("silk_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> CHROME_CORALSTONE_WALL           = RegistryUtils.createBlock("chrome_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> PRISMARINE_CORALSTONE_WALL       = RegistryUtils.createBlock("prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_WALL = RegistryUtils.createBlock("elder_prismarine_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DEAD_CORALSTONE_WALL             = RegistryUtils.createBlock("dead_coralstone_wall", () -> new WallBlock(UAProperties.CORALSTONE), ItemGroup.DECORATIONS);	

	public static RegistryObject<Block> CORALSTONE_VERTICAL_SLAB                  = RegistryUtils.createCompatBlock("coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BUBBLE_CORALSTONE_VERTICAL_SLAB           = RegistryUtils.createCompatBlock("bubble_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> HORN_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("horn_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TUBE_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("tube_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRAIN_CORALSTONE_VERTICAL_SLAB            = RegistryUtils.createCompatBlock("brain_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FIRE_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("fire_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}), ItemGroup.BUILDING_BLOCKS);	
	public static RegistryObject<Block> ACAN_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("acan_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {ACAN_CORAL.get(), ACAN_CORAL_FAN.get(), ACAN_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> FINGER_CORALSTONE_VERTICAL_SLAB           = RegistryUtils.createCompatBlock("finger_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {FINGER_CORAL.get(), FINGER_CORAL_FAN.get(), FINGER_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STAR_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("star_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {STAR_CORAL.get(), STAR_CORAL_FAN.get(), STAR_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> MOSS_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("moss_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {MOSS_CORAL.get(), MOSS_CORAL_FAN.get(), MOSS_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PETAL_CORALSTONE_VERTICAL_SLAB            = RegistryUtils.createCompatBlock("petal_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PETAL_CORAL.get(), PETAL_CORAL_FAN.get(), PETAL_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BRANCH_CORALSTONE_VERTICAL_SLAB           = RegistryUtils.createCompatBlock("branch_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {BRANCH_CORAL.get(), BRANCH_CORAL_FAN.get(), BRANCH_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ROCK_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("rock_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {ROCK_CORAL.get(), ROCK_CORAL_FAN.get(), ROCK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PILLOW_CORALSTONE_VERTICAL_SLAB           = RegistryUtils.createCompatBlock("pillow_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PILLOW_CORAL.get(), PILLOW_CORAL_FAN.get(), PILLOW_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> SILK_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("silk_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {SILK_CORAL.get(), SILK_CORAL_FAN.get(), SILK_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> CHROME_CORALSTONE_VERTICAL_SLAB           = RegistryUtils.createCompatBlock("chrome_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {CHROME_CORAL.get(), CHROME_CORAL_FAN.get(), CHROME_CORAL_WALL_FAN.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PRISMARINE_CORALSTONE_VERTICAL_SLAB       = RegistryUtils.createCompatBlock("prismarine_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PRISMARINE_CORAL.get(), PRISMARINE_CORAL_FAN.get(), PRISMARINE_CORAL_WALL_FAN.get(), PRISMARINE_CORAL_SHOWER.get()}), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> ELDER_PRISMARINE_CORALSTONE_VERTICAL_SLAB = RegistryUtils.createCompatBlock("elder_prismarine_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DEAD_CORALSTONE_VERTICAL_SLAB             = RegistryUtils.createCompatBlock("dead_coralstone_vertical_slab", "quark", () -> new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null), ItemGroup.BUILDING_BLOCKS);	
	
	public static RegistryObject<Block> TONGUE_KELP                   = RegistryUtils.createBlock("tongue_kelp", () -> new BlockUAKelpTop(KelpType.TONGUE, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static RegistryObject<Block> THORNY_KELP                   = RegistryUtils.createBlock("thorny_kelp", () -> new BlockUAKelpTop(KelpType.THORNY, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static RegistryObject<Block> OCHRE_KELP                    = RegistryUtils.createBlock("ochre_kelp", () -> new BlockUAKelpTop(KelpType.OCHRE, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static RegistryObject<Block> POLAR_KELP                    = RegistryUtils.createBlock("polar_kelp", () -> new BlockUAKelpTop(KelpType.POLAR, Properties.from(Blocks.KELP)), ItemGroup.MISC);
	public static RegistryObject<Block> TONGUE_KELP_PLANT             = RegistryUtils.createBlockNoItem("tongue_kelp_plant", () -> new BlockUAKelp(KelpType.TONGUE, TONGUE_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	public static RegistryObject<Block> THORNY_KELP_PLANT             = RegistryUtils.createBlockNoItem("thorny_kelp_plant", () -> new BlockUAKelp(KelpType.THORNY, THORNY_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	public static RegistryObject<Block> OCHRE_KELP_PLANT              = RegistryUtils.createBlockNoItem("ochre_kelp_plant", () -> new BlockUAKelp(KelpType.OCHRE, OCHRE_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	public static RegistryObject<Block> POLAR_KELP_PLANT              = RegistryUtils.createBlockNoItem("polar_kelp_plant", () -> new BlockUAKelp(KelpType.POLAR, POLAR_KELP.get(), Properties.from(Blocks.KELP_PLANT)));
	
	public static RegistryObject<Block> KELP_BLOCK                    = RegistryUtils.createBlock("kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELP_BLOCK             = RegistryUtils.createBlock("tongue_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELP_BLOCK             = RegistryUtils.createBlock("thorny_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELP_BLOCK              = RegistryUtils.createBlock("ochre_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELP_BLOCK              = RegistryUtils.createBlock("polar_kelp_block", () -> new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_COBBLESTONE             = RegistryUtils.createBlock("kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_COBBLESTONE      = RegistryUtils.createBlock("tongue_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_COBBLESTONE      = RegistryUtils.createBlock("thorny_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_COBBLESTONE       = RegistryUtils.createBlock("ochre_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_COBBLESTONE       = RegistryUtils.createBlock("polar_kelpy_cobblestone", () -> new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_COBBLESTONE_STAIRS         = RegistryUtils.createBlock("kelpy_cobblestone_stairs", () -> new StairsBlock(KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_STAIRS  = RegistryUtils.createBlock("tongue_kelpy_cobblestone_stairs", () -> new StairsBlock(TONGUE_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_COBBLESTONE_STAIRS  = RegistryUtils.createBlock("thorny_kelpy_cobblestone_stairs", () -> new StairsBlock(THORNY_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_STAIRS   = RegistryUtils.createBlock("ochre_kelpy_cobblestone_stairs", () -> new StairsBlock(OCHRE_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_COBBLESTONE_STAIRS   = RegistryUtils.createBlock("polar_kelpy_cobblestone_stairs", () -> new StairsBlock(POLAR_KELPY_COBBLESTONE.get().getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_COBBLESTONE_SLAB         = RegistryUtils.createBlock("kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_SLAB  = RegistryUtils.createBlock("tongue_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_COBBLESTONE_SLAB  = RegistryUtils.createBlock("thorny_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_SLAB   = RegistryUtils.createBlock("ochre_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_COBBLESTONE_SLAB   = RegistryUtils.createBlock("polar_kelpy_cobblestone_slab", () -> new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_COBBLESTONE_WALL         = RegistryUtils.createBlock("kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_WALL  = RegistryUtils.createBlock("tongue_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> THORNY_KELPY_COBBLESTONE_WALL  = RegistryUtils.createBlock("thorny_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_WALL   = RegistryUtils.createBlock("ochre_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> POLAR_KELPY_COBBLESTONE_WALL   = RegistryUtils.createBlock("polar_kelpy_cobblestone_wall", () -> new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> KELPY_STONE_BRICKS             = RegistryUtils.createBlock("kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_STONE_BRICKS      = RegistryUtils.createBlock("tongue_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_STONE_BRICKS      = RegistryUtils.createBlock("thorny_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_STONE_BRICKS       = RegistryUtils.createBlock("ochre_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_STONE_BRICKS       = RegistryUtils.createBlock("polar_kelpy_stone_bricks", () -> new Block(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_STONE_BRICK_STAIRS         = RegistryUtils.createBlock("kelpy_stone_brick_stairs", () -> new StairsBlock(KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_STAIRS  = RegistryUtils.createBlock("tongue_kelpy_stone_brick_stairs", () -> new StairsBlock(TONGUE_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_STONE_BRICK_STAIRS  = RegistryUtils.createBlock("thorny_kelpy_stone_brick_stairs", () -> new StairsBlock(THORNY_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_STAIRS   = RegistryUtils.createBlock("ochre_kelpy_stone_brick_stairs", () -> new StairsBlock(OCHRE_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_STONE_BRICK_STAIRS   = RegistryUtils.createBlock("polar_kelpy_stone_brick_stairs", () -> new StairsBlock(POLAR_KELPY_STONE_BRICKS.get().getDefaultState(), Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_STONE_BRICK_SLAB         = RegistryUtils.createBlock("kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_SLAB  = RegistryUtils.createBlock("tongue_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_STONE_BRICK_SLAB  = RegistryUtils.createBlock("thorny_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_SLAB   = RegistryUtils.createBlock("ochre_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_STONE_BRICK_SLAB   = RegistryUtils.createBlock("polar_kelpy_stone_brick_slab", () -> new SlabBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_STONE_BRICK_WALL         = RegistryUtils.createBlock("kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_WALL  = RegistryUtils.createBlock("tongue_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> THORNY_KELPY_STONE_BRICK_WALL  = RegistryUtils.createBlock("thorny_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_WALL   = RegistryUtils.createBlock("ochre_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> POLAR_KELPY_STONE_BRICK_WALL   = RegistryUtils.createBlock("polar_kelpy_stone_brick_wall", () -> new WallBlock(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> KELPY_COBBLESTONE_VERTICAL_SLAB         = RegistryUtils.createCompatBlock("kelpy_cobblestone_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_COBBLESTONE_VERTICAL_SLAB  = RegistryUtils.createCompatBlock("tongue_kelpy_cobblestone_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_COBBLESTONE_VERTICAL_SLAB  = RegistryUtils.createCompatBlock("thorny_kelpy_cobblestone_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_COBBLESTONE_VERTICAL_SLAB   = RegistryUtils.createCompatBlock("ochre_kelpy_cobblestone_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_COBBLESTONE_VERTICAL_SLAB   = RegistryUtils.createCompatBlock("polar_kelpy_cobblestone_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> KELPY_STONE_BRICK_VERTICAL_SLAB         = RegistryUtils.createCompatBlock("kelpy_stone_brick_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_VERTICAL_SLAB  = RegistryUtils.createCompatBlock("tongue_kelpy_stone_brick_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> THORNY_KELPY_STONE_BRICK_VERTICAL_SLAB  = RegistryUtils.createCompatBlock("thorny_kelpy_stone_brick_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_VERTICAL_SLAB   = RegistryUtils.createCompatBlock("ochre_kelpy_stone_brick_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> POLAR_KELPY_STONE_BRICK_VERTICAL_SLAB   = RegistryUtils.createCompatBlock("polar_kelpy_stone_brick_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(Blocks.STONE_BRICKS).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static RegistryObject<Block> BLUE_PICKERELWEED_BLOCK          = RegistryUtils.createBlock("pickerelweed_blue_block", () -> new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(false), false), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> PURPLE_PICKERELWEED_BLOCK        = RegistryUtils.createBlock("pickerelweed_purple_block", () -> new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(false), false), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BOILED_BLUE_PICKERELWEED_BLOCK   = RegistryUtils.createBlock("boiled_pickerelweed_blue_block", () -> new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(true), true), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BOILED_PURPLE_PICKERELWEED_BLOCK = RegistryUtils.createBlock("boiled_pickerelweed_purple_block", () -> new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(true), true), ItemGroup.BUILDING_BLOCKS);
	
	public static RegistryObject<Block> FLOWERING_RUSH           = RegistryUtils.createBlock("flowering_rush", () -> new BlockFloweringRush(Properties.from(Blocks.PEONY).sound(SoundType.WET_GRASS)), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> PRISMARINE_ROD_BUNDLE    = RegistryUtils.createBlock("prismarine_rod_bundle", () -> new RotatedPillarBlock(Properties.from(Blocks.PRISMARINE_BRICKS).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE)), ItemGroup.BUILDING_BLOCKS);

	public static RegistryObject<Block> DRIFTWOOD_LOG            = RegistryUtils.createBlock("driftwood_log", () -> new LogBlock(MaterialColor.GRAY, UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD                = RegistryUtils.createBlock("driftwood", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STRIPPED_DRIFTWOOD_LOG   = RegistryUtils.createBlock("driftwood_log_stripped", () -> new LogBlock(MaterialColor.GRAY, UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STRIPPED_DRIFTWOOD       = RegistryUtils.createBlock("driftwood_stripped", () -> new RotatedPillarBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_PLANKS         = RegistryUtils.createBlock("driftwood_planks", () -> new Block(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_DOOR           = RegistryUtils.createBlock("driftwood_door", () -> new DoorBlock(UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> DRIFTWOOD_SLAB           = RegistryUtils.createBlock("driftwood_slab", () -> new SlabBlock(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_STAIRS         = RegistryUtils.createBlock("driftwood_stairs", () -> new StairsBlock(DRIFTWOOD_PLANKS.get().getDefaultState(), UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_FENCE          = RegistryUtils.createBlock("driftwood_fence", () -> new FenceBlock(UAProperties.DRIFTWOOD), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> DRIFTWOOD_FENCE_GATE     = RegistryUtils.createBlock("driftwood_fence_gate", () -> new FenceGateBlock(UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> DRIFTWOOD_PRESSURE_PLATE = RegistryUtils.createBlock("driftwood_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> DRIFTWOOD_BUTTON         = RegistryUtils.createBlock("driftwood_button", () -> new WoodButtonBlock(Properties.from(UABlocks.DRIFTWOOD.get()).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static RegistryObject<Block> DRIFTWOOD_TRAPDOOR       = RegistryUtils.createBlock("driftwood_trapdoor", () -> new TrapDoorBlock(UAProperties.DRIFTWOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> VERTICAL_DRIFTWOOD_PLANKS= RegistryUtils.createCompatBlock("driftwood_vertical_planks", "quark", () -> new Block(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_VERTICAL_SLAB  = RegistryUtils.createCompatBlock("driftwood_vertical_slab", "quark", () -> new BlockVerticalSlab(UAProperties.DRIFTWOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_BOOKSHELF      = RegistryUtils.createCompatBlock("driftwood_bookshelf", "quark", () -> new BlockUABookshelf(Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> DRIFTWOOD_LADDER         = RegistryUtils.createCompatBlock("driftwood_ladder", "quark", () -> new LadderBlock(Properties.from(Blocks.LADDER).notSolid()), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> RIVER_LOG				= RegistryUtils.createBlock("river_log", () -> new LogBlock(MaterialColor.GRAY, UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_WOOD				= RegistryUtils.createBlock("river_wood", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STRIPPED_RIVER_LOG		= RegistryUtils.createBlock("stripped_river_log", () -> new LogBlock(MaterialColor.GRAY, UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> STRIPPED_RIVER_WOOD		= RegistryUtils.createBlock("stripped_river_wood", () -> new RotatedPillarBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_LEAVES			= RegistryUtils.createBlock("river_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES).notSolid()), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> RIVER_SAPLING			= RegistryUtils.createBlock("river_sapling", () -> new BlockSapling(new OakTree(), Block.Properties.from(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> POTTED_RIVER_SAPLING	= RegistryUtils.createBlockNoItem("potted_river_sapling", () -> new FlowerPotBlock(RIVER_SAPLING.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));
	public static RegistryObject<Block> RIVER_PLANKS			= RegistryUtils.createBlock("river_planks", () -> new Block(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_DOOR				= RegistryUtils.createBlock("river_door", () -> new DoorBlock(UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> RIVER_SLAB				= RegistryUtils.createBlock("river_slab", () -> new SlabBlock(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_STAIRS			= RegistryUtils.createBlock("river_stairs", () -> new StairsBlock(RIVER_PLANKS.get().getDefaultState(), UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_FENCE				= RegistryUtils.createBlock("river_fence", () -> new FenceBlock(UAProperties.RIVER_WOOD), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> RIVER_FENCE_GATE		= RegistryUtils.createBlock("river_fence_gate", () -> new FenceGateBlock(UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> RIVER_PRESSURE_PLATE	= RegistryUtils.createBlock("river_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> RIVER_BUTTON			= RegistryUtils.createBlock("river_button", () -> new WoodButtonBlock(Properties.from(UABlocks.RIVER_WOOD.get()).doesNotBlockMovement()), ItemGroup.REDSTONE);
	public static RegistryObject<Block> RIVER_TRAPDOOR			= RegistryUtils.createBlock("river_trapdoor", () -> new TrapDoorBlock(UAProperties.RIVER_WOOD), ItemGroup.REDSTONE);
	public static RegistryObject<Block> RIVER_LEAF_CARPET		= RegistryUtils.createCompatBlock("river_leaf_carpet", "quark", () -> new BlockLeafCarpet(Block.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> VERTICAL_RIVER_PLANKS	= RegistryUtils.createCompatBlock("vertical_river_planks", "quark", () -> new Block(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_VERTICAL_SLAB		= RegistryUtils.createCompatBlock("river_vertical_slab", "quark", () -> new BlockVerticalSlab(UAProperties.RIVER_WOOD), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_BOOKSHELF			= RegistryUtils.createCompatBlock("river_bookshelf", "quark", () -> new BlockUABookshelf(Properties.from(Blocks.BOOKSHELF)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> RIVER_LADDER			= RegistryUtils.createCompatBlock("river_ladder", "quark", () -> new LadderBlock(Properties.from(Blocks.LADDER).notSolid()), ItemGroup.DECORATIONS);
	
	public static RegistryObject<Block> BEACHGRASS                     	= RegistryUtils.createBlock("beachgrass", () -> new BlockBeachgrass(Properties.from(Blocks.GRASS).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> TALL_BEACHGRASS                	= RegistryUtils.createBlock("tall_beachgrass", () -> new BlockBeachgrassTall(Properties.from(Blocks.GRASS).harvestTool(ToolType.AXE)), ItemGroup.DECORATIONS);
	public static RegistryObject<Block> BEACHGRASS_THATCH              	= RegistryUtils.createBlock("beachgrass_thatch", () -> new BlockThatch(Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.AXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BEACHGRASS_THATCH_SLAB         	= RegistryUtils.createBlock("beachgrass_thatch_slab", () -> new SlabBlock(Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.AXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BEACHGRASS_THATCH_STAIRS       	= RegistryUtils.createBlock("beachgrass_thatch_stairs", () -> new StairsBlock(BEACHGRASS_THATCH.get().getDefaultState(), Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.AXE)), ItemGroup.BUILDING_BLOCKS);
	public static RegistryObject<Block> BEACHGRASS_THATCH_VERTICAL_SLAB	= RegistryUtils.createCompatBlock("beachgrass_thatch_vertical_slab", "quark", () -> new BlockVerticalSlab(Properties.from(BEACHGRASS_THATCH.get())), ItemGroup.BUILDING_BLOCKS);

	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, () -> BUBBLE_CORALSTONE.get());
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, () -> HORN_CORALSTONE.get());
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, () -> TUBE_CORALSTONE.get());
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, () -> BRAIN_CORALSTONE.get());
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, () -> FIRE_CORALSTONE.get());
		conversions.put(() -> ACAN_CORAL_BLOCK.get(), () -> ACAN_CORALSTONE.get());
		conversions.put(() -> FINGER_CORAL_BLOCK.get(), () -> FINGER_CORALSTONE.get());
		conversions.put(() -> STAR_CORAL_BLOCK.get(), () -> STAR_CORALSTONE.get());
		conversions.put(() -> MOSS_CORAL_BLOCK.get(), () -> MOSS_CORALSTONE.get());
		conversions.put(() -> PETAL_CORAL_BLOCK.get(), () -> PETAL_CORALSTONE.get());
		conversions.put(() -> BRANCH_CORAL_BLOCK.get(), () -> BRANCH_CORALSTONE.get());
		conversions.put(() -> ROCK_CORAL_BLOCK.get(), () -> ROCK_CORALSTONE.get());
		conversions.put(() -> PILLOW_CORAL_BLOCK.get(), () -> PILLOW_CORALSTONE.get());
		conversions.put(() -> SILK_CORAL_BLOCK.get(), () -> SILK_CORALSTONE.get());
		conversions.put(() -> CHROME_CORAL_BLOCK.get(), () -> CHROME_CORALSTONE.get());
		conversions.put(() -> PRISMARINE_CORAL_BLOCK.get(), () -> PRISMARINE_CORALSTONE.get());
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> CHISELED_CORALSTONE_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, () -> BUBBLE_CHISELED_CORALSTONE.get());
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, () -> HORN_CHISELED_CORALSTONE.get());
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, () -> TUBE_CHISELED_CORALSTONE.get());
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, () -> BRAIN_CHISELED_CORALSTONE.get());
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, () -> FIRE_CHISELED_CORALSTONE.get());
		conversions.put(() -> ACAN_CORAL_BLOCK.get(), () -> ACAN_CHISELED_CORALSTONE.get());
		conversions.put(() -> FINGER_CORAL_BLOCK.get(), () -> FINGER_CHISELED_CORALSTONE.get());
		conversions.put(() -> STAR_CORAL_BLOCK.get(), () -> STAR_CHISELED_CORALSTONE.get());
		conversions.put(() -> MOSS_CORAL_BLOCK.get(), () -> MOSS_CHISELED_CORALSTONE.get());
		conversions.put(() -> PETAL_CORAL_BLOCK.get(), () -> PETAL_CHISELED_CORALSTONE.get());
		conversions.put(() -> BRANCH_CORAL_BLOCK.get(), () -> BRANCH_CHISELED_CORALSTONE.get());
		conversions.put(() -> ROCK_CORAL_BLOCK.get(), () -> ROCK_CHISELED_CORALSTONE.get());
		conversions.put(() -> PILLOW_CORAL_BLOCK.get(), () -> PILLOW_CHISELED_CORALSTONE.get());
		conversions.put(() -> SILK_CORAL_BLOCK.get(), () -> SILK_CHISELED_CORALSTONE.get());
		conversions.put(() -> CHROME_CORAL_BLOCK.get(), () -> CHROME_CHISELED_CORALSTONE.get());
		conversions.put(() -> PRISMARINE_CORAL_BLOCK.get(), () -> PRISMARINE_CHISELED_CORALSTONE.get());
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_SLAB_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, () -> BUBBLE_CORALSTONE_SLAB.get());
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, () -> HORN_CORALSTONE_SLAB.get());
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, () -> TUBE_CORALSTONE_SLAB.get());
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, () -> BRAIN_CORALSTONE_SLAB.get());
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, () -> FIRE_CORALSTONE_SLAB.get());
		conversions.put(() -> ACAN_CORAL_BLOCK.get(), () -> ACAN_CORALSTONE_SLAB.get());
		conversions.put(() -> FINGER_CORAL_BLOCK.get(), () -> FINGER_CORALSTONE_SLAB.get());
		conversions.put(() -> STAR_CORAL_BLOCK.get(), () -> STAR_CORALSTONE_SLAB.get());
		conversions.put(() -> MOSS_CORAL_BLOCK.get(), () -> MOSS_CORALSTONE_SLAB.get());
		conversions.put(() -> PETAL_CORAL_BLOCK.get(), () -> PETAL_CORALSTONE_SLAB.get());
		conversions.put(() -> BRANCH_CORAL_BLOCK.get(), () -> BRANCH_CORALSTONE_SLAB.get());
		conversions.put(() -> ROCK_CORAL_BLOCK.get(), () -> ROCK_CORALSTONE_SLAB.get());
		conversions.put(() -> PILLOW_CORAL_BLOCK.get(), () -> PILLOW_CORALSTONE_SLAB.get());
		conversions.put(() -> SILK_CORAL_BLOCK.get(), () -> SILK_CORALSTONE_SLAB.get());
		conversions.put(() -> CHROME_CORAL_BLOCK.get(), () -> CHROME_CORALSTONE_SLAB.get());
		conversions.put(() -> PRISMARINE_CORAL_BLOCK.get(), () -> PRISMARINE_CORALSTONE_SLAB.get());
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_STAIRS_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, () -> BUBBLE_CORALSTONE_STAIRS.get());
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, () -> HORN_CORALSTONE_STAIRS.get());
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, () -> TUBE_CORALSTONE_STAIRS.get());
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, () -> BRAIN_CORALSTONE_STAIRS.get());
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, () -> FIRE_CORALSTONE_STAIRS.get());
		conversions.put(() -> ACAN_CORAL_BLOCK.get(), () -> ACAN_CORALSTONE_STAIRS.get());
		conversions.put(() -> FINGER_CORAL_BLOCK.get(), () -> FINGER_CORALSTONE_STAIRS.get());
		conversions.put(() -> STAR_CORAL_BLOCK.get(), () -> STAR_CORALSTONE_STAIRS.get());
		conversions.put(() -> MOSS_CORAL_BLOCK.get(), () -> MOSS_CORALSTONE_STAIRS.get());
		conversions.put(() -> PETAL_CORAL_BLOCK.get(), () -> PETAL_CORALSTONE_STAIRS.get());
		conversions.put(() -> BRANCH_CORAL_BLOCK.get(), () -> BRANCH_CORALSTONE_STAIRS.get());
		conversions.put(() -> ROCK_CORAL_BLOCK.get(), () -> ROCK_CORALSTONE_STAIRS.get());
		conversions.put(() -> PILLOW_CORAL_BLOCK.get(), () -> PILLOW_CORALSTONE_STAIRS.get());
		conversions.put(() -> SILK_CORAL_BLOCK.get(), () -> SILK_CORALSTONE_STAIRS.get());
		conversions.put(() -> CHROME_CORAL_BLOCK.get(), () -> CHROME_CORALSTONE_STAIRS.get());
		conversions.put(() -> PRISMARINE_CORAL_BLOCK.get(), () -> PRISMARINE_CORALSTONE_STAIRS.get());
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_WALL_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, () -> BUBBLE_CORALSTONE_WALL.get());
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, () -> HORN_CORALSTONE_WALL.get());
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, () -> TUBE_CORALSTONE_WALL.get());
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, () -> BRAIN_CORALSTONE_WALL.get());
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, () -> FIRE_CORALSTONE_WALL.get());
		conversions.put(() -> ACAN_CORAL_BLOCK.get(), () -> ACAN_CORALSTONE_WALL.get());
		conversions.put(() -> FINGER_CORAL_BLOCK.get(), () -> FINGER_CORALSTONE_WALL.get());
		conversions.put(() -> STAR_CORAL_BLOCK.get(), () -> STAR_CORALSTONE_WALL.get());
		conversions.put(() -> MOSS_CORAL_BLOCK.get(), () -> MOSS_CORALSTONE_WALL.get());
		conversions.put(() -> PETAL_CORAL_BLOCK.get(), () -> PETAL_CORALSTONE_WALL.get());
		conversions.put(() -> BRANCH_CORAL_BLOCK.get(), () -> BRANCH_CORALSTONE_WALL.get());
		conversions.put(() -> ROCK_CORAL_BLOCK.get(), () -> ROCK_CORALSTONE_WALL.get());
		conversions.put(() -> PILLOW_CORAL_BLOCK.get(), () -> PILLOW_CORALSTONE_WALL.get());
		conversions.put(() -> SILK_CORAL_BLOCK.get(), () -> SILK_CORALSTONE_WALL.get());
		conversions.put(() -> CHROME_CORAL_BLOCK.get(), () -> CHROME_CORALSTONE_WALL.get());
		conversions.put(() -> PRISMARINE_CORAL_BLOCK.get(), () -> PRISMARINE_CORALSTONE_WALL.get());
	});
	
	public static final Map<Supplier<Block>, Supplier<Block>> CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP = Util.make(Maps.newHashMap(), (conversions) -> {
		conversions.put(() -> Blocks.BUBBLE_CORAL_BLOCK, () -> BUBBLE_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> Blocks.HORN_CORAL_BLOCK, () -> HORN_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> Blocks.TUBE_CORAL_BLOCK, () -> TUBE_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> Blocks.BRAIN_CORAL_BLOCK, () -> BRAIN_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> Blocks.FIRE_CORAL_BLOCK, () -> FIRE_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> ACAN_CORAL_BLOCK.get(), () -> ACAN_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> FINGER_CORAL_BLOCK.get(), () -> FINGER_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> STAR_CORAL_BLOCK.get(), () -> STAR_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> MOSS_CORAL_BLOCK.get(), () -> MOSS_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> PETAL_CORAL_BLOCK.get(), () -> PETAL_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> BRANCH_CORAL_BLOCK.get(), () -> BRANCH_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> ROCK_CORAL_BLOCK.get(), () -> ROCK_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> PILLOW_CORAL_BLOCK.get(), () -> PILLOW_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> SILK_CORAL_BLOCK.get(), () -> SILK_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> CHROME_CORAL_BLOCK.get(), () -> CHROME_CORALSTONE_VERTICAL_SLAB.get());
		conversions.put(() -> PRISMARINE_CORAL_BLOCK.get(), () -> PRISMARINE_CORALSTONE_VERTICAL_SLAB.get());
	});
	
	public static enum KelpType {
		TONGUE(0.14D),
		THORNY(0.14D),
		OCHRE(0.14D),
		POLAR(0.14D);
		
		private double growChance;
		
		KelpType(double growChance) {
			this.growChance = growChance;
		}
		
		public double getGrowChance() {
			return this.growChance;
		}
	}
}