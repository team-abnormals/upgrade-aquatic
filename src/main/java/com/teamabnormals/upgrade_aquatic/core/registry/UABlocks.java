package com.teamabnormals.upgrade_aquatic.core.registry;

import java.util.Map;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.common.UAProperties;
import com.teamabnormals.upgrade_aquatic.common.blocks.*;
import com.teamabnormals.upgrade_aquatic.common.blocks.biorock.BlockBiorock;
import com.teamabnormals.upgrade_aquatic.common.blocks.biorock.BlockBiorockSlab;
import com.teamabnormals.upgrade_aquatic.common.blocks.biorock.BlockBiorockStairs;
import com.teamabnormals.upgrade_aquatic.common.blocks.biorock.BlockBiorockWall;
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
	public static Block TOOTH_STAIRS                  = new StairsBlock(TOOTH_BLOCK.getDefaultState(), UAProperties.BIOROCK).setRegistryName(Reference.MODID, "tooth_stairs");
    public static Block TOOTH_SLAB                    = new SlabBlock(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tooth_slab");
    public static Block TOOTH_WALL                    = new WallBlock(Properties.from(Blocks.STONE).harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "tooth_wall");
	
	public static Block BIOROCK                       = new BlockBiorock(UAProperties.BIOROCK, false).setRegistryName(Reference.MODID, "biorock");
	public static Block BUBBLE_BIOROCK                = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_biorock");
	public static Block HORN_BIOROCK             	  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_biorock");
	public static Block TUBE_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_biorock");
	public static Block BRAIN_BIOROCK                 = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_biorock");
	public static Block FIRE_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_biorock");	
	public static Block ACAN_BIOROCK               	  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_biorock");
	public static Block FINGER_BIOROCK           	  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_biorock");
	public static Block STAR_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_biorock");
	public static Block MOSS_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_biorock");
	public static Block PETAL_BIOROCK                 = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_biorock");
	public static Block BRANCH_BIOROCK                = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_biorock");
	public static Block ROCK_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_biorock");
	public static Block PILLOW_BIOROCK                = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_biorock");
	public static Block SILK_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_biorock");
	public static Block PRISMARINE_BIOROCK            = new BlockBiorock(UAProperties.BIOROCK, false, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_biorock");
	public static Block ELDER_BIOROCK                 = new BlockBiorock(UAProperties.BIOROCK, false).setRegistryName(Reference.MODID, "elder_biorock");
	public static Block DEAD_BIOROCK                  = new BlockBiorock(UAProperties.BIOROCK, false).setRegistryName(Reference.MODID, "dead_biorock");
	
	public static Block CHISELED_BIOROCK              = new BlockBiorock(UAProperties.BIOROCK, true).setRegistryName(Reference.MODID, "chiseled_biorock");
	public static Block BUBBLE_CHISELED_BIOROCK       = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_chiseled_biorock");
	public static Block HORN_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_chiseled_biorock");
	public static Block TUBE_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_chiseled_biorock");
	public static Block BRAIN_CHISELED_BIOROCK        = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_chiseled_biorock");
	public static Block FIRE_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_chiseled_biorock");	
	public static Block ACAN_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_chiseled_biorock");
	public static Block FINGER_CHISELED_BIOROCK       = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_chiseled_biorock");
	public static Block STAR_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_chiseled_biorock");
	public static Block MOSS_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_chiseled_biorock");
	public static Block PETAL_CHISELED_BIOROCK        = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_chiseled_biorock");
	public static Block BRANCH_CHISELED_BIOROCK       = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_chiseled_biorock");
	public static Block ROCK_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_chiseled_biorock");
	public static Block PILLOW_CHISELED_BIOROCK       = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_chiseled_biorock");
	public static Block SILK_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_chiseled_biorock");
	public static Block PRISMARINE_CHISELED_BIOROCK   = new BlockBiorock(UAProperties.BIOROCK, true, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_chiseled_biorock");
	public static Block ELDER_CHISELED_BIOROCK        = new BlockBiorock(UAProperties.BIOROCK, true).setRegistryName(Reference.MODID, "elder_chiseled_biorock");
	public static Block DEAD_CHISELED_BIOROCK         = new BlockBiorock(UAProperties.BIOROCK, true).setRegistryName(Reference.MODID, "dead_chiseled_biorock");
    
	public static Block BIOROCK_SLAB                  = new BlockBiorockSlab(UAProperties.BIOROCK, null).setRegistryName(Reference.MODID, "biorock_slab");
	public static Block BUBBLE_BIOROCK_SLAB           = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_biorock_slab");
	public static Block HORN_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_biorock_slab");
	public static Block TUBE_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_biorock_slab");
	public static Block BRAIN_BIOROCK_SLAB            = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_biorock_slab");
	public static Block FIRE_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_biorock_slab");	
	public static Block ACAN_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_biorock_slab");
	public static Block FINGER_BIOROCK_SLAB           = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_biorock_slab");
	public static Block STAR_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_biorock_slab");
	public static Block MOSS_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_biorock_slab");
	public static Block PETAL_BIOROCK_SLAB            = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_biorock_slab");
	public static Block BRANCH_BIOROCK_SLAB           = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_biorock_slab");
	public static Block ROCK_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_biorock_slab");
	public static Block PILLOW_BIOROCK_SLAB           = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_biorock_slab");
	public static Block SILK_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_biorock_slab");
	public static Block PRISMARINE_BIOROCK_SLAB       = new BlockBiorockSlab(UAProperties.BIOROCK, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_biorock_slab");
	public static Block ELDER_BIOROCK_SLAB            = new BlockBiorockSlab(UAProperties.BIOROCK, null).setRegistryName(Reference.MODID, "elder_biorock_slab");
	public static Block DEAD_BIOROCK_SLAB             = new BlockBiorockSlab(UAProperties.BIOROCK, null).setRegistryName(Reference.MODID, "dead_biorock_slab");	
	
	public static Block BIOROCK_STAIRS                = new BlockBiorockStairs(() -> BIOROCK.getDefaultState(), UAProperties.BIOROCK, null).setRegistryName(Reference.MODID, "biorock_stairs");
	public static Block BUBBLE_BIOROCK_STAIRS         = new BlockBiorockStairs(() -> BUBBLE_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "bubble_biorock_stairs");
	public static Block HORN_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> HORN_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "horn_biorock_stairs");
	public static Block TUBE_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> TUBE_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "tube_biorock_stairs");
	public static Block BRAIN_BIOROCK_STAIRS          = new BlockBiorockStairs(() -> BRAIN_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "brain_biorock_stairs");
	public static Block FIRE_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> FIRE_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "fire_biorock_stairs");
	public static Block ACAN_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> ACAN_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {ACAN_CORAL, ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "acan_biorock_stairs");
	public static Block FINGER_BIOROCK_STAIRS         = new BlockBiorockStairs(() -> FINGER_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {FINGER_CORAL, FINGER_CORAL_FAN, FINGER_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "finger_biorock_stairs");
	public static Block STAR_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> STAR_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {STAR_CORAL, STAR_CORAL_FAN, STAR_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "star_biorock_stairs");
	public static Block MOSS_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> MOSS_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {MOSS_CORAL, MOSS_CORAL_FAN, MOSS_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "moss_biorock_stairs");
	public static Block PETAL_BIOROCK_STAIRS          = new BlockBiorockStairs(() -> PETAL_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {PETAL_CORAL, PETAL_CORAL_FAN, PETAL_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "petal_biorock_stairs");
	public static Block BRANCH_BIOROCK_STAIRS         = new BlockBiorockStairs(() -> BRANCH_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {BRANCH_CORAL, BRANCH_CORAL_FAN, BRANCH_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "branch_biorock_stairs");
	public static Block ROCK_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> ROCK_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {ROCK_CORAL, ROCK_CORAL_FAN, ROCK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "rock_biorock_stairs");
	public static Block PILLOW_BIOROCK_STAIRS         = new BlockBiorockStairs(() -> PILLOW_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {PILLOW_CORAL, PILLOW_CORAL_FAN, PILLOW_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "pillow_biorock_stairs");
	public static Block SILK_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> SILK_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {SILK_CORAL, SILK_CORAL_FAN, SILK_CORAL_WALL_FAN}).setRegistryName(Reference.MODID, "silk_biorock_stairs");
	public static Block PRISMARINE_BIOROCK_STAIRS     = new BlockBiorockStairs(() -> PRISMARINE_BIOROCK.getDefaultState(), UAProperties.BIOROCK, new Block[] {PRISMARINE_CORAL, PRISMARINE_CORAL_FAN, PRISMARINE_CORAL_WALL_FAN, PRISMARINE_CORAL_SHOWER}).setRegistryName(Reference.MODID, "prismarine_biorock_stairs");
	public static Block ELDER_BIOROCK_STAIRS          = new BlockBiorockStairs(() -> ELDER_BIOROCK.getDefaultState(), UAProperties.BIOROCK, null).setRegistryName(Reference.MODID, "elder_biorock_stairs");
	public static Block DEAD_BIOROCK_STAIRS           = new BlockBiorockStairs(() -> DEAD_BIOROCK.getDefaultState(), UAProperties.BIOROCK, null).setRegistryName(Reference.MODID, "dead_biorock_stairs");
	
	public static Block BIOROCK_WALL                  = new BlockBiorockWall(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "biorock_wall");
	public static Block BUBBLE_BIOROCK_WALL           = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "bubble_biorock_wall");
	public static Block HORN_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "horn_biorock_wall");
	public static Block TUBE_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "tube_biorock_wall");
	public static Block BRAIN_BIOROCK_WALL            = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "brain_biorock_wall");
	public static Block FIRE_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "fire_biorock_wall");	
	public static Block ACAN_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "acan_biorock_wall");
	public static Block FINGER_BIOROCK_WALL           = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "finger_biorock_wall");
	public static Block STAR_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "star_biorock_wall");
	public static Block MOSS_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "moss_biorock_wall");
	public static Block PETAL_BIOROCK_WALL            = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "petal_biorock_wall");
	public static Block BRANCH_BIOROCK_WALL           = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "branch_biorock_wall");
	public static Block ROCK_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "rock_biorock_wall");
	public static Block PILLOW_BIOROCK_WALL           = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "pillow_biorock_wall");
	public static Block SILK_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "silk_biorock_wall");
	public static Block PRISMARINE_BIOROCK_WALL       = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "prismarine_biorock_wall");
	public static Block ELDER_BIOROCK_WALL            = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "elder_biorock_wall");
	public static Block DEAD_BIOROCK_WALL             = new WallBlock(UAProperties.BIOROCK).setRegistryName(Reference.MODID, "dead_biorock_wall");	

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
	
	public static final Map<Block, Block> BIOROCK_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> CHISELED_BIOROCK_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> BIOROCK_SLAB_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> BIOROCK_STAIRS_CONVERSION_MAP = Maps.newHashMap();
	public static final Map<Block, Block> BIOROCK_WALL_CONVERSION_MAP = Maps.newHashMap();
	
	static {
		BIOROCK_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_BIOROCK);
		BIOROCK_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_CHISELED_BIOROCK);
		CHISELED_BIOROCK_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_CHISELED_BIOROCK);
		
		BIOROCK_SLAB_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_BIOROCK_SLAB);
		BIOROCK_SLAB_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_BIOROCK_SLAB);
		
		BIOROCK_STAIRS_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_BIOROCK_STAIRS);
		BIOROCK_STAIRS_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_BIOROCK_STAIRS);
		
		BIOROCK_WALL_CONVERSION_MAP.put(Blocks.BUBBLE_CORAL_BLOCK, BUBBLE_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(Blocks.HORN_CORAL_BLOCK, HORN_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(Blocks.TUBE_CORAL_BLOCK, TUBE_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(Blocks.BRAIN_CORAL_BLOCK, BRAIN_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(Blocks.FIRE_CORAL_BLOCK, FIRE_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(ACAN_CORAL_BLOCK, ACAN_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(FINGER_CORAL_BLOCK, FINGER_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(STAR_CORAL_BLOCK, STAR_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(MOSS_CORAL_BLOCK, MOSS_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(PETAL_CORAL_BLOCK, PETAL_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(BRANCH_CORAL_BLOCK, BRANCH_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(ROCK_CORAL_BLOCK, ROCK_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(PILLOW_CORAL_BLOCK, PILLOW_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(SILK_CORAL_BLOCK, SILK_BIOROCK_WALL);
		BIOROCK_WALL_CONVERSION_MAP.put(PRISMARINE_CORAL_BLOCK, PRISMARINE_BIOROCK_WALL);
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
			BIOROCK_SLAB, BUBBLE_BIOROCK_SLAB, HORN_BIOROCK_SLAB, TUBE_BIOROCK_SLAB, BRAIN_BIOROCK_SLAB, FIRE_BIOROCK_SLAB, ACAN_BIOROCK_SLAB, FINGER_BIOROCK_SLAB, STAR_BIOROCK_SLAB, MOSS_BIOROCK_SLAB, PETAL_BIOROCK_SLAB, BRANCH_BIOROCK_SLAB, ROCK_BIOROCK_SLAB, PILLOW_BIOROCK_SLAB, SILK_BIOROCK_SLAB, PRISMARINE_BIOROCK_SLAB, ELDER_BIOROCK_SLAB, DEAD_BIOROCK_SLAB,
		    BIOROCK_STAIRS, BUBBLE_BIOROCK_STAIRS, HORN_BIOROCK_STAIRS, TUBE_BIOROCK_STAIRS, BRAIN_BIOROCK_STAIRS, FIRE_BIOROCK_STAIRS, ACAN_BIOROCK_STAIRS, FINGER_BIOROCK_STAIRS, STAR_BIOROCK_STAIRS, MOSS_BIOROCK_STAIRS, PETAL_BIOROCK_STAIRS, BRANCH_BIOROCK_STAIRS, ROCK_BIOROCK_STAIRS, PILLOW_BIOROCK_STAIRS, SILK_BIOROCK_STAIRS, PRISMARINE_BIOROCK_STAIRS, ELDER_BIOROCK_STAIRS, DEAD_BIOROCK_STAIRS,
		    BIOROCK_WALL, BUBBLE_BIOROCK_WALL, HORN_BIOROCK_WALL, TUBE_BIOROCK_WALL, BRAIN_BIOROCK_WALL, FIRE_BIOROCK_WALL, ACAN_BIOROCK_WALL, FINGER_BIOROCK_WALL, STAR_BIOROCK_WALL, MOSS_BIOROCK_WALL, PETAL_BIOROCK_WALL, BRANCH_BIOROCK_WALL, ROCK_BIOROCK_WALL, PILLOW_BIOROCK_WALL, SILK_BIOROCK_WALL, PRISMARINE_BIOROCK_WALL, ELDER_BIOROCK_WALL, DEAD_BIOROCK_WALL,
		    BIOROCK, BUBBLE_BIOROCK, HORN_BIOROCK, TUBE_BIOROCK, BRAIN_BIOROCK, FIRE_BIOROCK, ACAN_BIOROCK, FINGER_BIOROCK, STAR_BIOROCK, MOSS_BIOROCK, PETAL_BIOROCK, BRANCH_BIOROCK, ROCK_BIOROCK, PILLOW_BIOROCK, SILK_BIOROCK, PRISMARINE_BIOROCK, ELDER_BIOROCK, DEAD_BIOROCK,
		    CHISELED_BIOROCK, BUBBLE_CHISELED_BIOROCK, HORN_CHISELED_BIOROCK, TUBE_CHISELED_BIOROCK, BRAIN_CHISELED_BIOROCK, FIRE_CHISELED_BIOROCK, ACAN_CHISELED_BIOROCK, FINGER_CHISELED_BIOROCK, STAR_CHISELED_BIOROCK, MOSS_CHISELED_BIOROCK, PETAL_CHISELED_BIOROCK, BRANCH_CHISELED_BIOROCK, ROCK_CHISELED_BIOROCK, PILLOW_CHISELED_BIOROCK, SILK_CHISELED_BIOROCK, PRISMARINE_CHISELED_BIOROCK, ELDER_CHISELED_BIOROCK, DEAD_CHISELED_BIOROCK,
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
		
		registry.register(RegistryUtils.createSimpleItemBlock(BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_BIOROCK, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_CHISELED_BIOROCK, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_BIOROCK_SLAB, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_BIOROCK_STAIRS, ItemGroup.BUILDING_BLOCKS));
		
		registry.register(RegistryUtils.createSimpleItemBlock(BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BUBBLE_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(HORN_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(TUBE_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRAIN_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FIRE_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));		
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(FINGER_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAR_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(MOSS_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PETAL_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(BRANCH_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ROCK_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLOW_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(SILK_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PRISMARINE_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ELDER_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_BIOROCK_WALL, ItemGroup.BUILDING_BLOCKS));
	
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
	}
}