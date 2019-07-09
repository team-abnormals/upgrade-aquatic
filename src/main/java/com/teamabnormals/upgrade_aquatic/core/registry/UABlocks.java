package com.teamabnormals.upgrade_aquatic.core.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import com.teamabnormals.upgrade_aquatic.common.UAProperties;
import com.teamabnormals.upgrade_aquatic.common.blocks.*;
import com.teamabnormals.upgrade_aquatic.core.registry.util.RegistryUtils;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

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
	public static Block ELDER_PRISMARINE_CORAL_BLOCK   = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "elder_prismarine_coral_block");

	public static Block ACAN_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_ACAN_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.CYAN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "acan_coral_block");
	public static Block FINGER_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_FINGER_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "finger_coral_block");
	public static Block STAR_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_STAR_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.LIME).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "star_coral_block");
	public static Block MOSS_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_MOSS_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.GREEN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "moss_coral_block");
	public static Block PETAL_CORAL_BLOCK             = new BlockUACoralBlock(DEAD_PETAL_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.LIGHT_BLUE).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "petal_coral_block");
	public static Block BRANCH_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_BRANCH_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "branch_coral_block");
	public static Block ROCK_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_ROCK_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "rock_coral_block");
	public static Block PILLOW_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_PILLOW_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "pillow_coral_block");
	public static Block SILK_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_SILK_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "silk_coral_block");
	public static Block PRISMARINE_CORAL_BLOCK        = new BlockUACoralBlock(ELDER_PRISMARINE_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.DIAMOND).hardnessAndResistance(1.5F, 6.0F).lightValue(3).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "prismarine_coral_block");
	
	public static Block DEAD_ACAN_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_acan_coral");
	public static Block DEAD_FINGER_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_finger_coral");
	public static Block DEAD_STAR_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_star_coral");
	public static Block DEAD_MOSS_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_moss_coral");
	public static Block DEAD_PETAL_CORAL              = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_petal_coral");
	public static Block DEAD_BRANCH_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_branch_coral");
	public static Block DEAD_ROCK_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_rock_coral");
	public static Block DEAD_PILLOW_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_pillow_coral");
	public static Block DEAD_SILK_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_silk_coral");
	public static Block ELDER_PRISMARINE_CORAL         = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "elder_prismarine_coral");
	
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
	
	public static Block ACAN_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_ACAN_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.CYAN)).setRegistryName("acan_coral_wall_fan");
	public static Block FINGER_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_FINGER_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName("finger_coral_wall_fan");
	public static Block STAR_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_STAR_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIME)).setRegistryName("star_coral_wall_fan");
	public static Block MOSS_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_MOSS_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.GREEN)).setRegistryName("moss_coral_wall_fan");
	public static Block PETAL_CORAL_WALL_FAN          = new BlockUACoralWallFan(DEAD_PETAL_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName("petal_coral_wall_fan");
	public static Block BRANCH_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_BRANCH_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName("branch_coral_wall_fan");
	public static Block ROCK_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_ROCK_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.BROWN_TERRACOTTA)).setRegistryName("rock_coral_wall_fan");
	public static Block PILLOW_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_PILLOW_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.WHITE_TERRACOTTA)).setRegistryName("pillow_coral_wall_fan");
	public static Block SILK_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_SILK_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.PURPLE_TERRACOTTA)).setRegistryName("silk_coral_wall_fan");
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
	public static Block ELDER_PRISMARINE_CORAL_FAN     = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "elder_prismarine_coral_fan");
	
	public static Block ACAN_CORAL_FAN           	  = new BlockUACoralFan(DEAD_ACAN_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.CYAN)).setRegistryName(Reference.MODID, "acan_coral_fan");
	public static Block FINGER_CORAL_FAN              = new BlockUACoralFan(DEAD_FINGER_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName(Reference.MODID, "finger_coral_fan");
	public static Block STAR_CORAL_FAN                = new BlockUACoralFan(DEAD_STAR_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIME)).setRegistryName(Reference.MODID, "star_coral_fan");
	public static Block MOSS_CORAL_FAN                = new BlockUACoralFan(DEAD_MOSS_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.GREEN)).setRegistryName(Reference.MODID, "moss_coral_fan");
	public static Block PETAL_CORAL_FAN               = new BlockUACoralFan(DEAD_PETAL_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName(Reference.MODID, "petal_coral_fan");
	public static Block BRANCH_CORAL_FAN              = new BlockUACoralFan(DEAD_BRANCH_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName(Reference.MODID, "branch_coral_fan");
	public static Block ROCK_CORAL_FAN                = new BlockUACoralFan(DEAD_ROCK_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.BROWN_TERRACOTTA)).setRegistryName(Reference.MODID, "rock_coral_fan");
	public static Block PILLOW_CORAL_FAN              = new BlockUACoralFan(DEAD_PILLOW_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.WHITE_TERRACOTTA)).setRegistryName(Reference.MODID, "pillow_coral_fan");
	public static Block SILK_CORAL_FAN                = new BlockUACoralFan(DEAD_SILK_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.PURPLE_TERRACOTTA)).setRegistryName(Reference.MODID, "silk_coral_fan");
	public static Block PRISMARINE_CORAL_FAN          = new BlockUACoralFan(ELDER_PRISMARINE_CORAL_FAN, UAProperties.PRISMARINE_CORAL_BASE()).setRegistryName(Reference.MODID, "prismarine_coral_fan");
	
	public static Block ELDER_PRISMARINE_CORAL_SHOWER = new BlockCoralShowerDead().setRegistryName(Reference.MODID, "elder_prismarine_coral_shower");
	public static Block PRISMARINE_CORAL_SHOWER       = new BlockCoralShower(ELDER_PRISMARINE_CORAL_SHOWER, UAProperties.CORAL_BASE(MaterialColor.DIAMOND)).setRegistryName(Reference.MODID, "prismarine_coral_shower");
	
	public static Block GUARDIAN_SPINE                = new BlockSpine(UAProperties.SPINES).setRegistryName(Reference.MODID, "guardian_spine");
	public static Block ELDER_EYE                     = new BlockElderEye(UAProperties.ELDER_PRISMARINE).setRegistryName(Reference.MODID, "elder_eye");
	
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
			GUARDIAN_SPINE, ELDER_EYE
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
		
		registry.register(RegistryUtils.createSimpleItemBlock(GUARDIAN_SPINE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createItemBlockWithRarity(ELDER_EYE, ItemGroup.REDSTONE, Rarity.RARE));
	}
}
