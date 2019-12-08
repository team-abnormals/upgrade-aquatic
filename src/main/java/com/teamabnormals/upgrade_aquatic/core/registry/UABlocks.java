package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.Map;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.common.UAProperties;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBeachgrass;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBeachgrassTall;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockBedroll;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockButtonBase;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockCoralShower;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockCoralShowerDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockDriftwood;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockDriftwoodDoor;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockDriftwoodLog;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockDriftwoodPlanks;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockFenceBase;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockFenceGateBase;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockFloweringRush;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorchWall;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweedBlock;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweedDouble;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPressurePlateBase;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockSearocket;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockSpine;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockTrapdoorBase;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUABookshelf;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoral;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralBlock;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralFan;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralFanDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralWallFan;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoralWallFanDead;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUAKelp;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUAKelpTop;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUALadder;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockVerticalSlab;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstone;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneVerticalSlab;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneStairs;
import com.teamabnormals.upgrade_aquatic.common.blocks.coralstone.BlockCoralstoneWall;
import com.teamabnormals.upgrade_aquatic.core.registry.util.RegistryUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UABlocks {
	/* Blocks */
	public static Block DEAD_ACAN_CORAL_BLOCK         = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_acan_coral_block");
	public static Block DEAD_FINGER_CORAL_BLOCK       = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_finger_coral_block");
	public static Block DEAD_STAR_CORAL_BLOCK         = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_star_coral_block");
	public static Block DEAD_MOSS_CORAL_BLOCK         = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_moss_coral_block");
	public static Block DEAD_PETAL_CORAL_BLOCK        = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_petal_coral_block");
	public static Block DEAD_BRANCH_CORAL_BLOCK       = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_branch_coral_block");
	public static Block DEAD_ROCK_CORAL_BLOCK         = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_rock_coral_block");
	public static Block DEAD_PILLOW_CORAL_BLOCK       = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_pillow_coral_block");
	public static Block DEAD_SILK_CORAL_BLOCK         = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_silk_coral_block");
	public static Block ELDER_PRISMARINE_CORAL_BLOCK  = new Block(UAProperties.DEAD_CORAL_BLOCK.lightValue(3)).setRegistryName(Reference.MODID, "elder_prismarine_coral_block");

	public static Block ACAN_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_ACAN_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.CYAN)).setRegistryName(Reference.MODID, "acan_coral_block");
	public static Block FINGER_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_FINGER_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName(Reference.MODID, "finger_coral_block");
	public static Block STAR_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_STAR_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.LIME)).setRegistryName(Reference.MODID, "star_coral_block");
	public static Block MOSS_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_MOSS_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.GREEN)).setRegistryName(Reference.MODID, "moss_coral_block");
	public static Block PETAL_CORAL_BLOCK             = new BlockUACoralBlock(DEAD_PETAL_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName(Reference.MODID, "petal_coral_block");
	public static Block BRANCH_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_BRANCH_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName(Reference.MODID, "branch_coral_block");
	public static Block ROCK_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_ROCK_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.BROWN)).setRegistryName(Reference.MODID, "rock_coral_block");
	public static Block PILLOW_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_PILLOW_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.WHITE_TERRACOTTA)).setRegistryName(Reference.MODID, "pillow_coral_block");
	public static Block SILK_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_SILK_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.PURPLE)).setRegistryName(Reference.MODID, "silk_coral_block");
	public static Block PRISMARINE_CORAL_BLOCK        = new BlockUACoralBlock(ELDER_PRISMARINE_CORAL_BLOCK, UAProperties.CORAL_BLOCK_BASE(MaterialColor.DIAMOND).lightValue(3)).setRegistryName(Reference.MODID, "prismarine_coral_block");
	
	public static Block DEAD_ACAN_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_acan_coral");
	public static Block DEAD_FINGER_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_finger_coral");
	public static Block DEAD_STAR_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_star_coral");
	public static Block DEAD_MOSS_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_moss_coral");
	public static Block DEAD_PETAL_CORAL              = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_petal_coral");
	public static Block DEAD_BRANCH_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_branch_coral");
	public static Block DEAD_ROCK_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_rock_coral");
	public static Block DEAD_PILLOW_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_pillow_coral");
	public static Block DEAD_SILK_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_silk_coral");
	public static Block ELDER_PRISMARINE_CORAL        = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "elder_prismarine_coral");
	
	public static Block ACAN_CORAL               	  = new BlockUACoral(DEAD_ACAN_CORAL, UAProperties.CORAL_BASE(MaterialColor.CYAN)).setRegistryName(Reference.MODID, "acan_coral");
	public static Block FINGER_CORAL             	  = new BlockUACoral(DEAD_FINGER_CORAL, UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName(Reference.MODID, "finger_coral");
	public static Block STAR_CORAL                    = new BlockUACoral(DEAD_STAR_CORAL, UAProperties.CORAL_BASE(MaterialColor.LIME)).setRegistryName(Reference.MODID, "star_coral");
	public static Block MOSS_CORAL                    = new BlockUACoral(DEAD_MOSS_CORAL, UAProperties.CORAL_BASE(MaterialColor.GREEN)).setRegistryName(Reference.MODID, "moss_coral");
	public static Block PETAL_CORAL                   = new BlockUACoral(DEAD_PETAL_CORAL, UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName(Reference.MODID, "petal_coral");
	public static Block BRANCH_CORAL                  = new BlockUACoral(DEAD_BRANCH_CORAL, UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName(Reference.MODID, "branch_coral");
	public static Block ROCK_CORAL                    = new BlockUACoral(DEAD_ROCK_CORAL, UAProperties.CORAL_BASE(MaterialColor.BROWN_TERRACOTTA)).setRegistryName(Reference.MODID, "rock_coral");
	public static Block PILLOW_CORAL                  = new BlockUACoral(DEAD_PILLOW_CORAL, UAProperties.CORAL_BASE(MaterialColor.WHITE_TERRACOTTA)).setRegistryName(Reference.MODID, "pillow_coral");
	public static Block SILK_CORAL                    = new BlockUACoral(DEAD_SILK_CORAL, UAProperties.CORAL_BASE(MaterialColor.PURPLE_TERRACOTTA)).setRegistryName(Reference.MODID, "silk_coral");
	public static Block PRISMARINE_CORAL              = new BlockUACoral(ELDER_PRISMARINE_CORAL, UAProperties.PRISMARINE_CORAL_BASE()).setRegistryName(Reference.MODID, "prismarine_coral");
	
	public static Block DEAD_ACAN_CORAL_WALL_FAN      = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_acan_coral_wall_fan");
	public static Block DEAD_FINGER_CORAL_WALL_FAN    = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_finger_coral_wall_fan");
	public static Block DEAD_STAR_CORAL_WALL_FAN      = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_star_coral_wall_fan");
	public static Block DEAD_MOSS_CORAL_WALL_FAN      = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_moss_coral_wall_fan");
	public static Block DEAD_PETAL_CORAL_WALL_FAN     = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_petal_coral_wall_fan");
	public static Block DEAD_BRANCH_CORAL_WALL_FAN    = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_branch_coral_wall_fan");
	public static Block DEAD_ROCK_CORAL_WALL_FAN      = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_rock_coral_wall_fan");
	public static Block DEAD_PILLOW_CORAL_WALL_FAN    = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_pillow_coral_wall_fan");
	public static Block DEAD_SILK_CORAL_WALL_FAN      = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_silk_coral_wall_fan");
	public static Block ELDER_PRISMARINE_CORAL_WALL_FAN = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("elder_prismarine_coral_wall_fan");
	
	public static Block ACAN_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_ACAN_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.CYAN)).setRegistryName("acan_coral_wall_fan");
	public static Block FINGER_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_FINGER_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName("finger_coral_wall_fan");
	public static Block STAR_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_STAR_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.LIME)).setRegistryName("star_coral_wall_fan");
	public static Block MOSS_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_MOSS_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.GREEN)).setRegistryName("moss_coral_wall_fan");
	public static Block PETAL_CORAL_WALL_FAN          = new BlockUACoralWallFan(DEAD_PETAL_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName("petal_coral_wall_fan");
	public static Block BRANCH_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_BRANCH_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName("branch_coral_wall_fan");
	public static Block ROCK_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_ROCK_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.BROWN_TERRACOTTA)).setRegistryName("rock_coral_wall_fan");
	public static Block PILLOW_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_PILLOW_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.WHITE_TERRACOTTA)).setRegistryName("pillow_coral_wall_fan");
	public static Block SILK_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_SILK_CORAL_WALL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.PURPLE_TERRACOTTA)).setRegistryName("silk_coral_wall_fan");
	public static Block PRISMARINE_CORAL_WALL_FAN     = new BlockUACoralWallFan(ELDER_PRISMARINE_CORAL_WALL_FAN, UAProperties.PRISMARINE_CORAL_BASE()).setRegistryName("prismarine_coral_wall_fan");
	
	public static Block DEAD_ACAN_CORAL_FAN           = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_acan_coral_fan");
	public static Block DEAD_FINGER_CORAL_FAN         = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_finger_coral_fan");
	public static Block DEAD_STAR_CORAL_FAN           = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_star_coral_fan");
	public static Block DEAD_MOSS_CORAL_FAN           = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_moss_coral_fan");
	public static Block DEAD_PETAL_CORAL_FAN          = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_petal_coral_fan");
	public static Block DEAD_BRANCH_CORAL_FAN         = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_branch_coral_fan");
	public static Block DEAD_ROCK_CORAL_FAN           = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_rock_coral_fan");
	public static Block DEAD_PILLOW_CORAL_FAN         = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_pillow_coral_fan");
	public static Block DEAD_SILK_CORAL_FAN           = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_silk_coral_fan");
	public static Block ELDER_PRISMARINE_CORAL_FAN    = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "elder_prismarine_coral_fan");
	
	public static Block ACAN_CORAL_FAN           	  = new BlockUACoralFan(DEAD_ACAN_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.CYAN)).setRegistryName(Reference.MODID, "acan_coral_fan");
	public static Block FINGER_CORAL_FAN              = new BlockUACoralFan(DEAD_FINGER_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName(Reference.MODID, "finger_coral_fan");
	public static Block STAR_CORAL_FAN                = new BlockUACoralFan(DEAD_STAR_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.LIME)).setRegistryName(Reference.MODID, "star_coral_fan");
	public static Block MOSS_CORAL_FAN                = new BlockUACoralFan(DEAD_MOSS_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.GREEN)).setRegistryName(Reference.MODID, "moss_coral_fan");
	public static Block PETAL_CORAL_FAN               = new BlockUACoralFan(DEAD_PETAL_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName(Reference.MODID, "petal_coral_fan");
	public static Block BRANCH_CORAL_FAN              = new BlockUACoralFan(DEAD_BRANCH_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName(Reference.MODID, "branch_coral_fan");
	public static Block ROCK_CORAL_FAN                = new BlockUACoralFan(DEAD_ROCK_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.BROWN_TERRACOTTA)).setRegistryName(Reference.MODID, "rock_coral_fan");
	public static Block PILLOW_CORAL_FAN              = new BlockUACoralFan(DEAD_PILLOW_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.WHITE_TERRACOTTA)).setRegistryName(Reference.MODID, "pillow_coral_fan");
	public static Block SILK_CORAL_FAN                = new BlockUACoralFan(DEAD_SILK_CORAL_FAN, UAProperties.CORAL_FAN_BASE(MaterialColor.PURPLE_TERRACOTTA)).setRegistryName(Reference.MODID, "silk_coral_fan");
	public static Block PRISMARINE_CORAL_FAN          = new BlockUACoralFan(ELDER_PRISMARINE_CORAL_FAN, UAProperties.PRISMARINE_CORAL_BASE()).setRegistryName(Reference.MODID, "prismarine_coral_fan");
	
	public static Block ELDER_PRISMARINE_CORAL_SHOWER = new BlockCoralShowerDead().setRegistryName(Reference.MODID, "elder_prismarine_coral_shower");
	public static Block PRISMARINE_CORAL_SHOWER       = new BlockCoralShower(ELDER_PRISMARINE_CORAL_SHOWER, UAProperties.CORAL_BASE(MaterialColor.DIAMOND)).setRegistryName(Reference.MODID, "prismarine_coral_shower");
	
	public static Block ELDER_GUARDIAN_SPINE          = new BlockSpine(UAProperties.SPINES, true).setRegistryName(Reference.MODID, "elder_guardian_spine");
	public static Block GUARDIAN_SPINE                = new BlockSpine(UAProperties.SPINES, false).setRegistryName(Reference.MODID, "guardian_spine");
	public static Block ELDER_EYE                     = new BlockElderEye(UAProperties.ELDER_EYE).setRegistryName(Reference.MODID, "elder_eye");
	public static Block JELLY_TORCH_PINK              = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PINK).setRegistryName(Reference.MODID, "jelly_torch_pink");
	public static Block JELLY_TORCH_WALL_PINK         = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PINK).setRegistryName(Reference.MODID, "jelly_torch_wall_pink");
	public static Block JELLY_TORCH_PURPLE            = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PURPLE).setRegistryName(Reference.MODID, "jelly_torch_purple");
	public static Block JELLY_TORCH_WALL_PURPLE       = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.PURPLE).setRegistryName(Reference.MODID, "jelly_torch_wall_purple");
	public static Block JELLY_TORCH_BLUE              = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.BLUE).setRegistryName(Reference.MODID, "jelly_torch_blue");
	public static Block JELLY_TORCH_WALL_BLUE         = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.BLUE).setRegistryName(Reference.MODID, "jelly_torch_wall_blue");
	public static Block JELLY_TORCH_GREEN             = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.GREEN).setRegistryName(Reference.MODID, "jelly_torch_green");
	public static Block JELLY_TORCH_WALL_GREEN        = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.GREEN).setRegistryName(Reference.MODID, "jelly_torch_wall_green");
	public static Block JELLY_TORCH_YELLOW            = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.YELLOW).setRegistryName(Reference.MODID, "jelly_torch_yellow");
	public static Block JELLY_TORCH_WALL_YELLOW       = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.YELLOW).setRegistryName(Reference.MODID, "jelly_torch_wall_yellow");
	public static Block JELLY_TORCH_ORANGE            = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.ORANGE).setRegistryName(Reference.MODID, "jelly_torch_orange");
	public static Block JELLY_TORCH_WALL_ORANGE       = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.ORANGE).setRegistryName(Reference.MODID, "jelly_torch_wall_orange");
	public static Block JELLY_TORCH_RED            	  = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.RED).setRegistryName(Reference.MODID, "jelly_torch_red");
	public static Block JELLY_TORCH_WALL_RED          = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.RED).setRegistryName(Reference.MODID, "jelly_torch_wall_red");
    public static Block JELLY_TORCH_WHITE             = new BlockJellyTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.WHITE).setRegistryName(Reference.MODID, "jelly_torch_white");
	public static Block JELLY_TORCH_WALL_WHITE        = new BlockJellyTorchWall(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorch.JellyTorchType.WHITE).setRegistryName(Reference.MODID, "jelly_torch_wall_white");
	public static Block EMBEDDED_AMMONITE             = new Block(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "embedded_ammonite");
	
	public static Block BEDROLL_LEATHER               = new BlockBedroll(DyeColor.BROWN, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_leather");
	public static Block BEDROLL_WHITE                 = new BlockBedroll(DyeColor.WHITE, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_white");
	public static Block BEDROLL_ORANGE 				  = new BlockBedroll(DyeColor.ORANGE, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_orange");
    public static Block BEDROLL_MAGENTA               = new BlockBedroll(DyeColor.MAGENTA, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_magenta");
    public static Block BEDROLL_LIGHT_BLUE            = new BlockBedroll(DyeColor.LIGHT_BLUE, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_light_blue");
    public static Block BEDROLL_YELLOW                = new BlockBedroll(DyeColor.YELLOW, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_yellow");
    public static Block BEDROLL_LIME                  = new BlockBedroll(DyeColor.LIME, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_lime");
    public static Block BEDROLL_PINK                  = new BlockBedroll(DyeColor.PINK, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_pink");
    public static Block BEDROLL_GRAY                  = new BlockBedroll(DyeColor.GRAY, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_gray");
    public static Block BEDROLL_LIGHT_GRAY            = new BlockBedroll(DyeColor.LIGHT_GRAY, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_light_gray");
    public static Block BEDROLL_CYAN                  = new BlockBedroll(DyeColor.CYAN, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_cyan");
    public static Block BEDROLL_PURPLE                = new BlockBedroll(DyeColor.PURPLE, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_purple");
    public static Block BEDROLL_BLUE                  = new BlockBedroll(DyeColor.BLUE, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_blue");
    public static Block BEDROLL_BROWN                 = new BlockBedroll(DyeColor.BROWN, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_brown");
    public static Block BEDROLL_GREEN                 = new BlockBedroll(DyeColor.GREEN, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_green");
    public static Block BEDROLL_RED                   = new BlockBedroll(DyeColor.RED, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_red");
    public static Block BEDROLL_BLACK                 = new BlockBedroll(DyeColor.BLACK, UAProperties.BEDROLL).setRegistryName(Reference.MODID, "bedroll_black");
	
    public static Block PICKERELWEED_BLUE 	 	      = new BlockPickerelweed(UAProperties.PICKERELWEED).setRegistryName(Reference.MODID, "pickerel_weed_blue");
    public static Block PICKERELWEED_TALL_BLUE 	 	  = new BlockPickerelweedDouble(UAProperties.PICKERELWEED).setRegistryName(Reference.MODID, "pickerel_weed_tall_blue");
    public static Block PICKERELWEED_PURPLE 	 	  = new BlockPickerelweed(UAProperties.PICKERELWEED).setRegistryName(Reference.MODID, "pickerel_weed_purple");
    public static Block PICKERELWEED_TALL_PURPLE      = new BlockPickerelweedDouble(UAProperties.PICKERELWEED).setRegistryName(Reference.MODID, "pickerel_weed_tall_purple");
    
    public static Block SEAROCKET_WHITE               = new BlockSearocket(UAProperties.SEAROCKET(false)).setRegistryName(Reference.MODID, "searocket_white");
    public static Block SEAROCKET_PINK				  = new BlockSearocket(UAProperties.SEAROCKET(true)).setRegistryName(Reference.MODID, "searocket_pink");
    
    public static Block POTTED_PICKERELWEED_BLUE 	  = new FlowerPotBlock(PICKERELWEED_BLUE, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)).setRegistryName(Reference.MODID, "potted_pickerelweed_blue");
	public static Block POTTED_PICKERELWEED_PURPLE 	  = new FlowerPotBlock(PICKERELWEED_PURPLE, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)).setRegistryName(Reference.MODID, "potted_pickerelweed_purple");
    public static Block POTTED_SEAROCKET_WHITE 	      = new FlowerPotBlock(SEAROCKET_WHITE, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)).setRegistryName(Reference.MODID, "potted_searocket_white");
    public static Block POTTED_SEAROCKET_PINK 	      = new FlowerPotBlock(SEAROCKET_PINK, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)).setRegistryName(Reference.MODID, "potted_searocket_pink");
	
	public static Block TOOTH_BLOCK                   = new Block(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tooth_block");	
	public static Block TOOTH_STAIRS                  = new StairsBlock(TOOTH_BLOCK.getDefaultState(), UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "tooth_stairs");
    public static Block TOOTH_SLAB                    = new SlabBlock(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tooth_slab");
    public static Block TOOTH_WALL                    = new WallBlock(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tooth_wall");
	
	public static Block CORALSTONE                       = new BlockCoralstone(UAProperties.CORALSTONE, false).setRegistryName(Reference.MODID, "coralstone");
	public static Block BUBBLE_CORALSTONE                = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_coralstone");
	public static Block HORN_CORALSTONE             	  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_coralstone");
	public static Block TUBE_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_coralstone");
	public static Block BRAIN_CORALSTONE                 = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_coralstone");
	public static Block FIRE_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_coralstone");	
	public static Block ACAN_CORALSTONE               	  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_coralstone");
	public static Block FINGER_CORALSTONE           	  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_coralstone");
	public static Block STAR_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_coralstone");
	public static Block MOSS_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_coralstone");
	public static Block PETAL_CORALSTONE                 = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_coralstone");
	public static Block BRANCH_CORALSTONE                = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_coralstone");
	public static Block ROCK_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_coralstone");
	public static Block PILLOW_CORALSTONE                = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_coralstone");
	public static Block SILK_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_coralstone");
	public static Block PRISMARINE_CORALSTONE            = new BlockCoralstone(UAProperties.CORALSTONE, false, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_coralstone");
	public static Block ELDER_CORALSTONE                 = new BlockCoralstone(UAProperties.CORALSTONE, false).setRegistryName(Reference.MODID, "elder_coralstone");
	public static Block DEAD_CORALSTONE                  = new BlockCoralstone(UAProperties.CORALSTONE, false).setRegistryName(Reference.MODID, "dead_coralstone");
	
	public static Block CHISELED_CORALSTONE              = new BlockCoralstone(UAProperties.CORALSTONE, true).setRegistryName(Reference.MODID, "chiseled_coralstone");
	public static Block BUBBLE_CHISELED_CORALSTONE       = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_chiseled_coralstone");
	public static Block HORN_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_chiseled_coralstone");
	public static Block TUBE_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_chiseled_coralstone");
	public static Block BRAIN_CHISELED_CORALSTONE        = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_chiseled_coralstone");
	public static Block FIRE_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_chiseled_coralstone");	
	public static Block ACAN_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_chiseled_coralstone");
	public static Block FINGER_CHISELED_CORALSTONE       = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_chiseled_coralstone");
	public static Block STAR_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_chiseled_coralstone");
	public static Block MOSS_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_chiseled_coralstone");
	public static Block PETAL_CHISELED_CORALSTONE        = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_chiseled_coralstone");
	public static Block BRANCH_CHISELED_CORALSTONE       = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_chiseled_coralstone");
	public static Block ROCK_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_chiseled_coralstone");
	public static Block PILLOW_CHISELED_CORALSTONE       = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_chiseled_coralstone");
	public static Block SILK_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_chiseled_coralstone");
	public static Block PRISMARINE_CHISELED_CORALSTONE   = new BlockCoralstone(UAProperties.CORALSTONE, true, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_chiseled_coralstone");
	public static Block ELDER_CHISELED_CORALSTONE        = new BlockCoralstone(UAProperties.CORALSTONE, true).setRegistryName(Reference.MODID, "elder_chiseled_coralstone");
	public static Block DEAD_CHISELED_CORALSTONE         = new BlockCoralstone(UAProperties.CORALSTONE, true).setRegistryName(Reference.MODID, "dead_chiseled_coralstone");
    
	public static Block CORALSTONE_SLAB                  = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "coralstone_slab");
	public static Block BUBBLE_CORALSTONE_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_coralstone_slab");
	public static Block HORN_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_coralstone_slab");
	public static Block TUBE_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_coralstone_slab");
	public static Block BRAIN_CORALSTONE_SLAB            = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_coralstone_slab");
	public static Block FIRE_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_coralstone_slab");	
	public static Block ACAN_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_coralstone_slab");
	public static Block FINGER_CORALSTONE_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_coralstone_slab");
	public static Block STAR_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_coralstone_slab");
	public static Block MOSS_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_coralstone_slab");
	public static Block PETAL_CORALSTONE_SLAB            = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_coralstone_slab");
	public static Block BRANCH_CORALSTONE_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_coralstone_slab");
	public static Block ROCK_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_coralstone_slab");
	public static Block PILLOW_CORALSTONE_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_coralstone_slab");
	public static Block SILK_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_coralstone_slab");
	public static Block PRISMARINE_CORALSTONE_SLAB       = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_coralstone_slab");
	public static Block ELDER_CORALSTONE_SLAB            = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "elder_coralstone_slab");
	public static Block DEAD_CORALSTONE_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "dead_coralstone_slab");	
	
	public static Block CORALSTONE_STAIRS                = new BlockCoralstoneStairs(() -> CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "coralstone_stairs");
	public static Block BUBBLE_CORALSTONE_STAIRS         = new BlockCoralstoneStairs(() -> BUBBLE_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_coralstone_stairs");
	public static Block HORN_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> HORN_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_coralstone_stairs");
	public static Block TUBE_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> TUBE_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_coralstone_stairs");
	public static Block BRAIN_CORALSTONE_STAIRS          = new BlockCoralstoneStairs(() -> BRAIN_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_coralstone_stairs");
	public static Block FIRE_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> FIRE_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_coralstone_stairs");
	public static Block ACAN_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> ACAN_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_coralstone_stairs");
	public static Block FINGER_CORALSTONE_STAIRS         = new BlockCoralstoneStairs(() -> FINGER_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_coralstone_stairs");
	public static Block STAR_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> STAR_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_coralstone_stairs");
	public static Block MOSS_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> MOSS_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_coralstone_stairs");
	public static Block PETAL_CORALSTONE_STAIRS          = new BlockCoralstoneStairs(() -> PETAL_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_coralstone_stairs");
	public static Block BRANCH_CORALSTONE_STAIRS         = new BlockCoralstoneStairs(() -> BRANCH_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_coralstone_stairs");
	public static Block ROCK_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> ROCK_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_coralstone_stairs");
	public static Block PILLOW_CORALSTONE_STAIRS         = new BlockCoralstoneStairs(() -> PILLOW_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_coralstone_stairs");
	public static Block SILK_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> SILK_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_coralstone_stairs");
	public static Block PRISMARINE_CORALSTONE_STAIRS     = new BlockCoralstoneStairs(() -> PRISMARINE_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_coralstone_stairs");
	public static Block ELDER_CORALSTONE_STAIRS          = new BlockCoralstoneStairs(() -> ELDER_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "elder_coralstone_stairs");
	public static Block DEAD_CORALSTONE_STAIRS           = new BlockCoralstoneStairs(() -> DEAD_CORALSTONE.getDefaultState(), UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "dead_coralstone_stairs");
	
	public static Block CORALSTONE_WALL                  = new BlockCoralstoneWall(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "coralstone_wall");
	public static Block BUBBLE_CORALSTONE_WALL           = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "bubble_coralstone_wall");
	public static Block HORN_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "horn_coralstone_wall");
	public static Block TUBE_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "tube_coralstone_wall");
	public static Block BRAIN_CORALSTONE_WALL            = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "brain_coralstone_wall");
	public static Block FIRE_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "fire_coralstone_wall");	
	public static Block ACAN_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "acan_coralstone_wall");
	public static Block FINGER_CORALSTONE_WALL           = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "finger_coralstone_wall");
	public static Block STAR_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "star_coralstone_wall");
	public static Block MOSS_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "moss_coralstone_wall");
	public static Block PETAL_CORALSTONE_WALL            = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "petal_coralstone_wall");
	public static Block BRANCH_CORALSTONE_WALL           = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "branch_coralstone_wall");
	public static Block ROCK_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "rock_coralstone_wall");
	public static Block PILLOW_CORALSTONE_WALL           = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "pillow_coralstone_wall");
	public static Block SILK_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "silk_coralstone_wall");
	public static Block PRISMARINE_CORALSTONE_WALL       = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "prismarine_coralstone_wall");
	public static Block ELDER_CORALSTONE_WALL            = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "elder_coralstone_wall");
	public static Block DEAD_CORALSTONE_WALL             = new WallBlock(UAProperties.CORALSTONE).setRegistryName(Reference.MODID, "dead_coralstone_wall");	

	public static Block TONGUE_KELP                   = new BlockUAKelpTop(KelpType.TONGUE, Properties.from(Blocks.KELP)).setRegistryName(Reference.MODID, "tongue_kelp");
	public static Block THORNY_KELP                   = new BlockUAKelpTop(KelpType.THORNY, Properties.from(Blocks.KELP)).setRegistryName(Reference.MODID, "thorny_kelp");
	public static Block OCHRE_KELP                    = new BlockUAKelpTop(KelpType.OCHRE, Properties.from(Blocks.KELP)).setRegistryName(Reference.MODID, "ochre_kelp");
	public static Block POLAR_KELP                    = new BlockUAKelpTop(KelpType.POLAR, Properties.from(Blocks.KELP)).setRegistryName(Reference.MODID, "polar_kelp");
	public static Block TONGUE_KELP_PLANT             = new BlockUAKelp(KelpType.TONGUE, TONGUE_KELP, Properties.from(Blocks.KELP_PLANT)).setRegistryName(Reference.MODID, "tongue_kelp_plant");
	public static Block THORNY_KELP_PLANT             = new BlockUAKelp(KelpType.THORNY, THORNY_KELP, Properties.from(Blocks.KELP_PLANT)).setRegistryName(Reference.MODID, "thorny_kelp_plant");
	public static Block OCHRE_KELP_PLANT              = new BlockUAKelp(KelpType.OCHRE, OCHRE_KELP, Properties.from(Blocks.KELP_PLANT)).setRegistryName(Reference.MODID, "ochre_kelp_plant");
	public static Block POLAR_KELP_PLANT              = new BlockUAKelp(KelpType.POLAR, POLAR_KELP, Properties.from(Blocks.KELP_PLANT)).setRegistryName(Reference.MODID, "polar_kelp_plant");
	
	public static Block KELP_BLOCK                    = new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)).setRegistryName(Reference.MODID, "kelp_block");
	public static Block TONGUE_KELP_BLOCK             = new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)).setRegistryName(Reference.MODID, "tongue_kelp_block");
	public static Block THORNY_KELP_BLOCK             = new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)).setRegistryName(Reference.MODID, "thorny_kelp_block");
	public static Block OCHRE_KELP_BLOCK              = new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)).setRegistryName(Reference.MODID, "ochre_kelp_block");
	public static Block POLAR_KELP_BLOCK              = new Block(Properties.from(Blocks.DRIED_KELP_BLOCK).sound(SoundType.WET_GRASS)).setRegistryName(Reference.MODID, "polar_kelp_block");
	
	public static Block KELPY_COBBLESTONE             = new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "kelpy_cobblestone");
	public static Block TONGUE_KELPY_COBBLESTONE      = new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tongue_kelpy_cobblestone");
	public static Block THORNY_KELPY_COBBLESTONE      = new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "thorny_kelpy_cobblestone");
	public static Block OCHRE_KELPY_COBBLESTONE       = new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "ochre_kelpy_cobblestone");
	public static Block POLAR_KELPY_COBBLESTONE       = new Block(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "polar_kelpy_cobblestone");
	
	public static Block KELPY_COBBLESTONE_STAIRS         = new StairsBlock(KELPY_COBBLESTONE.getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "kelpy_cobblestone_stairs");
	public static Block TONGUE_KELPY_COBBLESTONE_STAIRS  = new StairsBlock(TONGUE_KELPY_COBBLESTONE.getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tongue_kelpy_cobblestone_stairs");
	public static Block THORNY_KELPY_COBBLESTONE_STAIRS  = new StairsBlock(THORNY_KELPY_COBBLESTONE.getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "thorny_kelpy_cobblestone_stairs");
	public static Block OCHRE_KELPY_COBBLESTONE_STAIRS   = new StairsBlock(OCHRE_KELPY_COBBLESTONE.getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "ochre_kelpy_cobblestone_stairs");
	public static Block POLAR_KELPY_COBBLESTONE_STAIRS   = new StairsBlock(POLAR_KELPY_COBBLESTONE.getDefaultState(), Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "polar_kelpy_cobblestone_stairs");
	
	public static Block KELPY_COBBLESTONE_SLAB         = new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "kelpy_cobblestone_slab");
	public static Block TONGUE_KELPY_COBBLESTONE_SLAB  = new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tongue_kelpy_cobblestone_slab");
	public static Block THORNY_KELPY_COBBLESTONE_SLAB  = new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "thorny_kelpy_cobblestone_slab");
	public static Block OCHRE_KELPY_COBBLESTONE_SLAB   = new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "ochre_kelpy_cobblestone_slab");
	public static Block POLAR_KELPY_COBBLESTONE_SLAB   = new SlabBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "polar_kelpy_cobblestone_slab");
	
	public static Block KELPY_COBBLESTONE_WALL         = new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "kelpy_cobblestone_wall");
	public static Block TONGUE_KELPY_COBBLESTONE_WALL  = new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tongue_kelpy_cobblestone_wall");
	public static Block THORNY_KELPY_COBBLESTONE_WALL  = new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "thorny_kelpy_cobblestone_wall");
	public static Block OCHRE_KELPY_COBBLESTONE_WALL   = new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "ochre_kelpy_cobblestone_wall");
	public static Block POLAR_KELPY_COBBLESTONE_WALL   = new WallBlock(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "polar_kelpy_cobblestone_wall");
	
	public static Block BLUE_PICKERELWEED_BLOCK          = new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(false), false).setRegistryName(Reference.MODID, "pickerelweed_blue_block");
	public static Block PURPLE_PICKERELWEED_BLOCK        = new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(false), false).setRegistryName(Reference.MODID, "pickerelweed_purple_block");
	public static Block BOILED_BLUE_PICKERELWEED_BLOCK   = new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(true), true).setRegistryName(Reference.MODID, "boiled_pickerelweed_blue_block");
	public static Block BOILED_PURPLE_PICKERELWEED_BLOCK = new BlockPickerelweedBlock(UAProperties.PICKERELWEED_BLOCK(true), true).setRegistryName(Reference.MODID, "boiled_pickerelweed_purple_block");
	
	public static Block FLOWERING_RUSH                   = new BlockFloweringRush(Properties.from(Blocks.PEONY)).setRegistryName(Reference.MODID, "flowering_rush");
	
	public static Block PRISMARINE_ROD_BUNDLE            = new RotatedPillarBlock(Properties.from(Blocks.PRISMARINE_BRICKS).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "prismarine_rod_bundle");

	public static Block DRIFTWOOD_LOG            = new BlockDriftwoodLog(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_log");
	public static Block DRIFTWOOD                = new BlockDriftwood(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood");
	public static Block DRIFTWOOD_LOG_STRIPPED   = new BlockDriftwoodLog(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_log_stripped");
	public static Block DRIFTWOOD_STRIPPED       = new BlockDriftwood(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_stripped");
	public static Block DRIFTWOOD_PLANKS         = new BlockDriftwoodPlanks(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_planks");
	public static Block DRIFTWOOD_DOOR           = new BlockDriftwoodDoor(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_door");
	public static Block DRIFTWOOD_SLAB           = new SlabBlock(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_slab");
	public static Block DRIFTWOOD_STAIRS         = new StairsBlock(DRIFTWOOD_PLANKS.getDefaultState(), UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_stairs");
	public static Block DRIFTWOOD_FENCE          = new BlockFenceBase(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_fence");
	public static Block DRIFTWOOD_FENCE_GATE     = new BlockFenceGateBase(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_fence_gate");
	public static Block DRIFTWOOD_PRESSURE_PLATE = new BlockPressurePlateBase(PressurePlateBlock.Sensitivity.EVERYTHING, UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_pressure_plate");
	public static Block DRIFTWOOD_BUTTON         = new BlockButtonBase(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_button");
	public static Block DRIFTWOOD_TRAPDOOR       = new BlockTrapdoorBase(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_trapdoor");

	public static Block BEACHGRASS_THATCH              = new Block(Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "beachgrass_thatch");
	public static Block BEACHGRASS_THATCH_SLAB         = new SlabBlock(Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "beachgrass_thatch_slab");
	public static Block BEACHGRASS_THATCH_STAIRS       = new StairsBlock(BEACHGRASS_THATCH.getDefaultState(), Properties.from(Blocks.HAY_BLOCK).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "beachgrass_thatch_stairs");	
	public static Block BEACHGRASS                     = new BlockBeachgrass(Properties.from(Blocks.GRASS)).setRegistryName(Reference.MODID, "beachgrass");
	public static Block TALL_BEACHGRASS                = new BlockBeachgrassTall(Properties.from(Blocks.GRASS)).setRegistryName(Reference.MODID, "tall_beachgrass");
	
	/*
	 * Quark Compat
	 */
	public static Block DRIFTWOOD_VERTICAL_PLANKS               = new BlockDriftwoodPlanks(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_vertical_planks");
	public static Block DRIFTWOOD_VERTICAL_SLAB                 = new BlockVerticalSlab(UAProperties.DRIFTWOOD).setRegistryName(Reference.MODID, "driftwood_vertical_slab");
	public static Block DRIFTWOOD_BOOKSHELF                     = new BlockUABookshelf(Properties.from(Blocks.BOOKSHELF)).setRegistryName(Reference.MODID, "driftwood_bookshelf");
	public static Block DRIFTWOOD_LADDER                        = new BlockUALadder(Properties.from(Blocks.LADDER)).setRegistryName(Reference.MODID, "driftwood_ladder");
	public static Block BEACHGRASS_THATCH_VERTICAL_SLAB         = new BlockVerticalSlab(Properties.from(BEACHGRASS_THATCH)).setRegistryName(Reference.MODID, "beachgrass_thatch_vertical_slab");
	public static Block TOOTH_VERTICAL_SLAB                     = new BlockVerticalSlab(Properties.from(TOOTH_BLOCK)).setRegistryName(Reference.MODID, "tooth_vertical_slab");
	public static Block KELPY_COBBLESTONE_VERTICAL_SLAB         = new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "kelpy_cobblestone_vertical_slab");
	public static Block TONGUE_KELPY_COBBLESTONE_VERTICAL_SLAB  = new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tongue_kelpy_cobblestone_vertical_slab");
	public static Block THORNY_KELPY_COBBLESTONE_VERTICAL_SLAB  = new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "thorny_kelpy_cobblestone_vertical_slab");
	public static Block OCHRE_KELPY_COBBLESTONE_VERTICAL_SLAB   = new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "ochre_kelpy_cobblestone_vertical_slab");
	public static Block POLAR_KELPY_COBBLESTONE_VERTICAL_SLAB   = new BlockVerticalSlab(Properties.from(Blocks.COBBLESTONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "polar_kelpy_cobblestone_vertical_slab");
	public static Block CORALSTONE_VERTICAL_SLAB                  = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "coralstone_vertical_slab");
	public static Block BUBBLE_CORALSTONE_VERTICAL_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_coralstone_vertical_slab");
	public static Block HORN_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_coralstone_vertical_slab");
	public static Block TUBE_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_coralstone_vertical_slab");
	public static Block BRAIN_CORALSTONE_VERTICAL_SLAB            = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_coralstone_vertical_slab");
	public static Block FIRE_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_coralstone_vertical_slab");	
	public static Block ACAN_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_coralstone_vertical_slab");
	public static Block FINGER_CORALSTONE_VERTICAL_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_coralstone_vertical_slab");
	public static Block STAR_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_coralstone_vertical_slab");
	public static Block MOSS_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_coralstone_vertical_slab");
	public static Block PETAL_CORALSTONE_VERTICAL_SLAB            = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_coralstone_vertical_slab");
	public static Block BRANCH_CORALSTONE_VERTICAL_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_coralstone_vertical_slab");
	public static Block ROCK_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_coralstone_vertical_slab");
	public static Block PILLOW_CORALSTONE_VERTICAL_SLAB           = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_coralstone_vertical_slab");
	public static Block SILK_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_coralstone_vertical_slab");
	public static Block PRISMARINE_CORALSTONE_VERTICAL_SLAB       = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_coralstone_vertical_slab");
	public static Block ELDER_CORALSTONE_VERTICAL_SLAB            = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "elder_coralstone_vertical_slab");
	public static Block DEAD_CORALSTONE_VERTICAL_SLAB             = new BlockCoralstoneVerticalSlab(UAProperties.CORALSTONE, null).setRegistryName(Reference.MODID, "dead_coralstone_vertical_slab");	
	
	public static final Map<Block, Block> CORALSTONE_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> CHISELED_CORALSTONE_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> CORALSTONE_SLAB_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> CORALSTONE_STAIRS_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> CORALSTONE_WALL_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP = Maps.newHashMap();
	
	static {
		CORALSTONE_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CORALSTONE);
		CORALSTONE_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CHISELED_CORALSTONE);
		CHISELED_CORALSTONE_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CHISELED_CORALSTONE);
		
		CORALSTONE_SLAB_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_SLAB);
		CORALSTONE_SLAB_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_SLAB);
		
		CORALSTONE_STAIRS_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_STAIRS);
		CORALSTONE_STAIRS_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_STAIRS);
		
		CORALSTONE_WALL_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_WALL);
		CORALSTONE_WALL_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_WALL);
		
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CORALSTONE_VERTICAL_SLAB);
		CORALSTONE_VERTICAL_SLAB_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CORALSTONE_VERTICAL_SLAB);
	}
	
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
	
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		final Block blocks[] = {
			DEAD_ACAN_CORAL_BLOCK, DEAD_FINGER_CORAL_BLOCK, DEAD_STAR_CORAL_BLOCK, DEAD_MOSS_CORAL_BLOCK, DEAD_PETAL_CORAL_BLOCK, DEAD_BRANCH_CORAL_BLOCK, DEAD_ROCK_CORAL_BLOCK, DEAD_PILLOW_CORAL_BLOCK, DEAD_SILK_CORAL_BLOCK, ELDER_PRISMARINE_CORAL_BLOCK,
			ACAN_CORAL_BLOCK, FINGER_CORAL_BLOCK, STAR_CORAL_BLOCK, MOSS_CORAL_BLOCK, PETAL_CORAL_BLOCK, BRANCH_CORAL_BLOCK, ROCK_CORAL_BLOCK, PILLOW_CORAL_BLOCK, SILK_CORAL_BLOCK, PRISMARINE_CORAL_BLOCK,
			DEAD_ACAN_CORAL, DEAD_FINGER_CORAL, DEAD_STAR_CORAL, DEAD_MOSS_CORAL, DEAD_PETAL_CORAL, DEAD_BRANCH_CORAL, DEAD_ROCK_CORAL, DEAD_PILLOW_CORAL, DEAD_SILK_CORAL, ELDER_PRISMARINE_CORAL,
			ACAN_CORAL, FINGER_CORAL, STAR_CORAL, MOSS_CORAL, PETAL_CORAL, BRANCH_CORAL, ROCK_CORAL, PILLOW_CORAL, SILK_CORAL, PRISMARINE_CORAL,
			DEAD_ACAN_CORAL_WALL_FAN, DEAD_FINGER_CORAL_WALL_FAN, DEAD_STAR_CORAL_WALL_FAN, DEAD_MOSS_CORAL_WALL_FAN, DEAD_PETAL_CORAL_WALL_FAN, DEAD_BRANCH_CORAL_WALL_FAN, DEAD_ROCK_CORAL_WALL_FAN, DEAD_PILLOW_CORAL_WALL_FAN, DEAD_SILK_CORAL_WALL_FAN, ELDER_PRISMARINE_CORAL_WALL_FAN,
			ACAN_CORAL_WALL_FAN, FINGER_CORAL_WALL_FAN, STAR_CORAL_WALL_FAN, MOSS_CORAL_WALL_FAN, PETAL_CORAL_WALL_FAN, BRANCH_CORAL_WALL_FAN, ROCK_CORAL_WALL_FAN, PILLOW_CORAL_WALL_FAN, SILK_CORAL_WALL_FAN, PRISMARINE_CORAL_WALL_FAN,
			DEAD_ACAN_CORAL_FAN, DEAD_FINGER_CORAL_FAN, DEAD_STAR_CORAL_FAN, DEAD_MOSS_CORAL_FAN, DEAD_PETAL_CORAL_FAN, DEAD_BRANCH_CORAL_FAN, DEAD_ROCK_CORAL_FAN, DEAD_PILLOW_CORAL_FAN, DEAD_SILK_CORAL_FAN, ELDER_PRISMARINE_CORAL_SHOWER, ELDER_PRISMARINE_CORAL_FAN,
			ACAN_CORAL_FAN, FINGER_CORAL_FAN, STAR_CORAL_FAN, MOSS_CORAL_FAN, PETAL_CORAL_FAN, BRANCH_CORAL_FAN,  ROCK_CORAL_FAN, PILLOW_CORAL_FAN, SILK_CORAL_FAN, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_SHOWER,
			GUARDIAN_SPINE, ELDER_GUARDIAN_SPINE, ELDER_EYE,
			JELLY_TORCH_PINK, JELLY_TORCH_WALL_PINK, JELLY_TORCH_PURPLE, JELLY_TORCH_WALL_PURPLE, JELLY_TORCH_BLUE, JELLY_TORCH_WALL_BLUE,
			JELLY_TORCH_GREEN, JELLY_TORCH_WALL_GREEN, JELLY_TORCH_YELLOW, JELLY_TORCH_WALL_YELLOW, JELLY_TORCH_ORANGE, JELLY_TORCH_WALL_ORANGE, JELLY_TORCH_RED, JELLY_TORCH_WALL_RED, JELLY_TORCH_WHITE, JELLY_TORCH_WALL_WHITE,
			EMBEDDED_AMMONITE, BEDROLL_LEATHER, BEDROLL_WHITE, BEDROLL_BLACK, BEDROLL_GRAY, BEDROLL_LIGHT_GRAY, BEDROLL_PINK, BEDROLL_MAGENTA, BEDROLL_PURPLE, BEDROLL_BLUE, BEDROLL_CYAN, BEDROLL_LIGHT_BLUE, BEDROLL_GREEN, BEDROLL_LIME, BEDROLL_ORANGE, BEDROLL_RED, BEDROLL_YELLOW, BEDROLL_BROWN,
			PICKERELWEED_BLUE, PICKERELWEED_TALL_BLUE, PICKERELWEED_PURPLE, PICKERELWEED_TALL_PURPLE,
			SEAROCKET_WHITE, SEAROCKET_PINK,
			POTTED_PICKERELWEED_BLUE, POTTED_PICKERELWEED_PURPLE, POTTED_SEAROCKET_WHITE, POTTED_SEAROCKET_PINK,
			TOOTH_BLOCK, TOOTH_SLAB, TOOTH_STAIRS, TOOTH_WALL,
			CORALSTONE_SLAB, BUBBLE_CORALSTONE_SLAB, HORN_CORALSTONE_SLAB, TUBE_CORALSTONE_SLAB, BRAIN_CORALSTONE_SLAB, FIRE_CORALSTONE_SLAB, ACAN_CORALSTONE_SLAB, FINGER_CORALSTONE_SLAB, STAR_CORALSTONE_SLAB, MOSS_CORALSTONE_SLAB, PETAL_CORALSTONE_SLAB, BRANCH_CORALSTONE_SLAB, ROCK_CORALSTONE_SLAB, PILLOW_CORALSTONE_SLAB, SILK_CORALSTONE_SLAB, PRISMARINE_CORALSTONE_SLAB, ELDER_CORALSTONE_SLAB, DEAD_CORALSTONE_SLAB,
		    CORALSTONE_STAIRS, BUBBLE_CORALSTONE_STAIRS, HORN_CORALSTONE_STAIRS, TUBE_CORALSTONE_STAIRS, BRAIN_CORALSTONE_STAIRS, FIRE_CORALSTONE_STAIRS, ACAN_CORALSTONE_STAIRS, FINGER_CORALSTONE_STAIRS, STAR_CORALSTONE_STAIRS, MOSS_CORALSTONE_STAIRS, PETAL_CORALSTONE_STAIRS, BRANCH_CORALSTONE_STAIRS, ROCK_CORALSTONE_STAIRS, PILLOW_CORALSTONE_STAIRS, SILK_CORALSTONE_STAIRS, PRISMARINE_CORALSTONE_STAIRS, ELDER_CORALSTONE_STAIRS, DEAD_CORALSTONE_STAIRS,
		    CORALSTONE_WALL, BUBBLE_CORALSTONE_WALL, HORN_CORALSTONE_WALL, TUBE_CORALSTONE_WALL, BRAIN_CORALSTONE_WALL, FIRE_CORALSTONE_WALL, ACAN_CORALSTONE_WALL, FINGER_CORALSTONE_WALL, STAR_CORALSTONE_WALL, MOSS_CORALSTONE_WALL, PETAL_CORALSTONE_WALL, BRANCH_CORALSTONE_WALL, ROCK_CORALSTONE_WALL, PILLOW_CORALSTONE_WALL, SILK_CORALSTONE_WALL, PRISMARINE_CORALSTONE_WALL, ELDER_CORALSTONE_WALL, DEAD_CORALSTONE_WALL,
		    CORALSTONE, BUBBLE_CORALSTONE, HORN_CORALSTONE, TUBE_CORALSTONE, BRAIN_CORALSTONE, FIRE_CORALSTONE, ACAN_CORALSTONE, FINGER_CORALSTONE, STAR_CORALSTONE, MOSS_CORALSTONE, PETAL_CORALSTONE, BRANCH_CORALSTONE, ROCK_CORALSTONE, PILLOW_CORALSTONE, SILK_CORALSTONE, PRISMARINE_CORALSTONE, ELDER_CORALSTONE, DEAD_CORALSTONE,
		    CHISELED_CORALSTONE, BUBBLE_CHISELED_CORALSTONE, HORN_CHISELED_CORALSTONE, TUBE_CHISELED_CORALSTONE, BRAIN_CHISELED_CORALSTONE, FIRE_CHISELED_CORALSTONE, ACAN_CHISELED_CORALSTONE, FINGER_CHISELED_CORALSTONE, STAR_CHISELED_CORALSTONE, MOSS_CHISELED_CORALSTONE, PETAL_CHISELED_CORALSTONE, BRANCH_CHISELED_CORALSTONE, ROCK_CHISELED_CORALSTONE, PILLOW_CHISELED_CORALSTONE, SILK_CHISELED_CORALSTONE, PRISMARINE_CHISELED_CORALSTONE, ELDER_CHISELED_CORALSTONE, DEAD_CHISELED_CORALSTONE,
		    TONGUE_KELP, THORNY_KELP, OCHRE_KELP, POLAR_KELP, TONGUE_KELP_PLANT, THORNY_KELP_PLANT, OCHRE_KELP_PLANT, POLAR_KELP_PLANT,
		    KELP_BLOCK, TONGUE_KELP_BLOCK, OCHRE_KELP_BLOCK, THORNY_KELP_BLOCK, POLAR_KELP_BLOCK,
		    KELPY_COBBLESTONE, TONGUE_KELPY_COBBLESTONE, OCHRE_KELPY_COBBLESTONE, THORNY_KELPY_COBBLESTONE, POLAR_KELPY_COBBLESTONE,
		    KELPY_COBBLESTONE_SLAB, TONGUE_KELPY_COBBLESTONE_SLAB, OCHRE_KELPY_COBBLESTONE_SLAB, THORNY_KELPY_COBBLESTONE_SLAB, POLAR_KELPY_COBBLESTONE_SLAB,
		    KELPY_COBBLESTONE_STAIRS, TONGUE_KELPY_COBBLESTONE_STAIRS, OCHRE_KELPY_COBBLESTONE_STAIRS, THORNY_KELPY_COBBLESTONE_STAIRS, POLAR_KELPY_COBBLESTONE_STAIRS,
		    KELPY_COBBLESTONE_WALL, TONGUE_KELPY_COBBLESTONE_WALL, OCHRE_KELPY_COBBLESTONE_WALL, THORNY_KELPY_COBBLESTONE_WALL, POLAR_KELPY_COBBLESTONE_WALL,
		    BLUE_PICKERELWEED_BLOCK, PURPLE_PICKERELWEED_BLOCK, BOILED_BLUE_PICKERELWEED_BLOCK, BOILED_PURPLE_PICKERELWEED_BLOCK,
		    DRIFTWOOD_LOG, DRIFTWOOD, DRIFTWOOD_LOG_STRIPPED, DRIFTWOOD_STRIPPED, DRIFTWOOD_PLANKS, DRIFTWOOD_DOOR, DRIFTWOOD_TRAPDOOR, DRIFTWOOD_FENCE, DRIFTWOOD_FENCE_GATE, DRIFTWOOD_SLAB, DRIFTWOOD_STAIRS, DRIFTWOOD_BUTTON, DRIFTWOOD_PRESSURE_PLATE,
			PRISMARINE_ROD_BUNDLE, FLOWERING_RUSH,
			BEACHGRASS, TALL_BEACHGRASS,
			BEACHGRASS_THATCH, BEACHGRASS_THATCH_SLAB, BEACHGRASS_THATCH_STAIRS
		};
		event.getRegistry().registerAll(blocks);
		
		/*
		 * Compat
		 */
		//if(ModList.get().isLoaded("Quark")) {
			event.getRegistry().registerAll(
				DRIFTWOOD_VERTICAL_SLAB, DRIFTWOOD_BOOKSHELF, DRIFTWOOD_LADDER, DRIFTWOOD_VERTICAL_SLAB, DRIFTWOOD_VERTICAL_PLANKS,
				BEACHGRASS_THATCH_VERTICAL_SLAB,
				TOOTH_VERTICAL_SLAB,
				KELPY_COBBLESTONE_VERTICAL_SLAB, TONGUE_KELPY_COBBLESTONE_VERTICAL_SLAB, OCHRE_KELPY_COBBLESTONE_VERTICAL_SLAB, THORNY_KELPY_COBBLESTONE_VERTICAL_SLAB, POLAR_KELPY_COBBLESTONE_VERTICAL_SLAB,
				CORALSTONE_VERTICAL_SLAB, BUBBLE_CORALSTONE_VERTICAL_SLAB, HORN_CORALSTONE_VERTICAL_SLAB, TUBE_CORALSTONE_VERTICAL_SLAB, BRAIN_CORALSTONE_VERTICAL_SLAB, FIRE_CORALSTONE_VERTICAL_SLAB, ACAN_CORALSTONE_VERTICAL_SLAB, FINGER_CORALSTONE_VERTICAL_SLAB, STAR_CORALSTONE_VERTICAL_SLAB, MOSS_CORALSTONE_VERTICAL_SLAB, PETAL_CORALSTONE_VERTICAL_SLAB, BRANCH_CORALSTONE_VERTICAL_SLAB, ROCK_CORALSTONE_VERTICAL_SLAB, PILLOW_CORALSTONE_VERTICAL_SLAB, SILK_CORALSTONE_VERTICAL_SLAB, PRISMARINE_CORALSTONE_VERTICAL_SLAB, ELDER_CORALSTONE_VERTICAL_SLAB, DEAD_CORALSTONE_VERTICAL_SLAB
			);
		//}
	}
	
	@SubscribeEvent
	public static void onRegisterItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ACAN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_FINGER_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_STAR_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_MOSS_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_PETAL_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_BRANCH_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ROCK_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_PILLOW_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_SILK_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_PRISMARINE_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ACAN_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_FINGER_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_STAR_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_MOSS_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_PETAL_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_BRANCH_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ROCK_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_PILLOW_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_SILK_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_PRISMARINE_CORAL, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CORAL, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CORAL, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_ACAN_CORAL_FAN, DEAD_ACAN_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_FINGER_CORAL_FAN, DEAD_FINGER_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_STAR_CORAL_FAN, DEAD_STAR_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_MOSS_CORAL_FAN, DEAD_MOSS_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_PETAL_CORAL_FAN, DEAD_PETAL_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_BRANCH_CORAL_FAN, DEAD_BRANCH_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_ROCK_CORAL_FAN, DEAD_ROCK_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_PILLOW_CORAL_FAN, DEAD_PILLOW_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_SILK_CORAL_FAN, DEAD_SILK_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(ELDER_PRISMARINE_CORAL_FAN, ELDER_PRISMARINE_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createWallOrFloorItem(ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(STAR_CORAL_FAN, STAR_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(SILK_CORAL_FAN, SILK_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createUpsideDownBlockItem(ELDER_PRISMARINE_CORAL_SHOWER, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createUpsideDownBlockItem(PRISMARINE_CORAL_SHOWER, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_GUARDIAN_SPINE, ItemGroup.REDSTONE));
		registry.register(RegistryUtils.createSimpleItemBlock(GUARDIAN_SPINE, ItemGroup.REDSTONE));
		registry.register(RegistryUtils.createItemBlockWithRarity(ELDER_EYE, ItemGroup.REDSTONE, Rarity.RARE));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_PINK, JELLY_TORCH_WALL_PINK, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_PURPLE, JELLY_TORCH_WALL_PURPLE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_BLUE, JELLY_TORCH_WALL_BLUE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_GREEN, JELLY_TORCH_WALL_GREEN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_YELLOW, JELLY_TORCH_WALL_YELLOW, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_ORANGE, JELLY_TORCH_WALL_ORANGE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_RED, JELLY_TORCH_WALL_RED, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createWallOrFloorItem(JELLY_TORCH_WHITE, JELLY_TORCH_WALL_WHITE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(EMBEDDED_AMMONITE, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_LEATHER, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_WHITE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_BLACK, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_RED, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_LIGHT_BLUE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_BLUE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_PINK, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_PURPLE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_MAGENTA, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_GREEN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_LIME, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_GRAY, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_LIGHT_GRAY, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_ORANGE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_YELLOW, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_CYAN, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEDROLL_BROWN, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(PICKERELWEED_BLUE, ItemGroup.MISC));
		registry.register(RegistryUtils.createSimpleItemBlock(PICKERELWEED_PURPLE, ItemGroup.MISC));
		registry.register(RegistryUtils.createSimpleItemBlock(SEAROCKET_WHITE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(SEAROCKET_PINK, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(TOOTH_BLOCK, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(TOOTH_SLAB, ItemGroup.BUILDING_BLOCKS));	
		registry.register(RegistryUtils.createSimpleItemBlock(TOOTH_STAIRS, ItemGroup.BUILDING_BLOCKS));	
		registry.register(RegistryUtils.createSimpleItemBlock(TOOTH_WALL, ItemGroup.BUILDING_BLOCKS));	
		
		registry.register(RegistryUtils.createSimpleItemBlock(CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_CORALSTONE, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_CHISELED_CORALSTONE, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_CORALSTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_CORALSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_CORALSTONE_WALL, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(TONGUE_KELP, ItemGroup.MISC));
		registry.register(RegistryUtils.createSimpleItemBlock(THORNY_KELP, ItemGroup.MISC));
		registry.register(RegistryUtils.createSimpleItemBlock(OCHRE_KELP, ItemGroup.MISC));
		registry.register(RegistryUtils.createSimpleItemBlock(POLAR_KELP, ItemGroup.MISC));
		
		registry.register(RegistryUtils.createSimpleItemBlock(TONGUE_KELP_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(OCHRE_KELP_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(THORNY_KELP_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(POLAR_KELP_BLOCK, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(TONGUE_KELPY_COBBLESTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(OCHRE_KELPY_COBBLESTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(THORNY_KELPY_COBBLESTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(POLAR_KELPY_COBBLESTONE, ItemGroup.BUILDING_BLOCKS));

		registry.register(RegistryUtils.createSimpleItemBlock(TONGUE_KELPY_COBBLESTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(OCHRE_KELPY_COBBLESTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(THORNY_KELPY_COBBLESTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(POLAR_KELPY_COBBLESTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(TONGUE_KELPY_COBBLESTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(OCHRE_KELPY_COBBLESTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(THORNY_KELPY_COBBLESTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(POLAR_KELPY_COBBLESTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(TONGUE_KELPY_COBBLESTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(OCHRE_KELPY_COBBLESTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(THORNY_KELPY_COBBLESTONE_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(POLAR_KELPY_COBBLESTONE_WALL, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(KELP_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(KELPY_COBBLESTONE, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(KELPY_COBBLESTONE_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(KELPY_COBBLESTONE_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(KELPY_COBBLESTONE_WALL, ItemGroup.BUILDING_BLOCKS));

		registry.register(RegistryUtils.createSimpleItemBlock(BLUE_PICKERELWEED_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PURPLE_PICKERELWEED_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BOILED_BLUE_PICKERELWEED_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BOILED_PURPLE_PICKERELWEED_BLOCK, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(FLOWERING_RUSH, ItemGroup.DECORATIONS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_LOG, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_STRIPPED, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_LOG_STRIPPED, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_PLANKS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_FENCE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_FENCE_GATE, ItemGroup.REDSTONE));
		registry.register(RegistryUtils.createTallItemBlock(DRIFTWOOD_DOOR, ItemGroup.REDSTONE));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_PRESSURE_PLATE, ItemGroup.REDSTONE));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_BUTTON, ItemGroup.REDSTONE));
		registry.register(RegistryUtils.createSimpleItemBlock(DRIFTWOOD_TRAPDOOR, ItemGroup.REDSTONE));
		
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_ROD_BUNDLE, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(BEACHGRASS, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(TALL_BEACHGRASS, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEACHGRASS_THATCH, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEACHGRASS_THATCH_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BEACHGRASS_THATCH_STAIRS, ItemGroup.BUILDING_BLOCKS));
		
		/*
		 * Compat
		 */
		//if(ModList.get().isLoaded("Quark")) {
			event.getRegistry().registerAll(
				RegistryUtils.createSimpleItemBlock(DRIFTWOOD_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS), 
				RegistryUtils.createSimpleItemBlock(DRIFTWOOD_BOOKSHELF, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(DRIFTWOOD_VERTICAL_PLANKS, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(DRIFTWOOD_LADDER, ItemGroup.DECORATIONS),
				RegistryUtils.createSimpleItemBlock(BEACHGRASS_THATCH_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(TOOTH_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),		
				RegistryUtils.createSimpleItemBlock(KELPY_COBBLESTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS), 
				RegistryUtils.createSimpleItemBlock(OCHRE_KELPY_COBBLESTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS), 	
				RegistryUtils.createSimpleItemBlock(POLAR_KELPY_COBBLESTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS), 
				RegistryUtils.createSimpleItemBlock(TONGUE_KELPY_COBBLESTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS), 	
				RegistryUtils.createSimpleItemBlock(THORNY_KELPY_COBBLESTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS), 	
				RegistryUtils.createSimpleItemBlock(BUBBLE_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(HORN_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(TUBE_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(BRAIN_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(FIRE_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),	
				RegistryUtils.createSimpleItemBlock(ACAN_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(FINGER_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(STAR_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(MOSS_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(PETAL_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(BRANCH_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(ROCK_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(PILLOW_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(SILK_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(PRISMARINE_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(ELDER_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS),
				RegistryUtils.createSimpleItemBlock(DEAD_CORALSTONE_VERTICAL_SLAB, ItemGroup.BUILDING_BLOCKS)
			);
		//}
	}
}