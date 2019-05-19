package com.teamabnormals.upgrade_aquatic.core.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import com.teamabnormals.upgrade_aquatic.common.UAProperties;
import com.teamabnormals.upgrade_aquatic.common.blocks.*;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UABlocks {
	
	/* Blocks */
	public static Block DEAD_ACAN_CORAL_BLOCK         = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_acan_coral_block");
	public static Block DEAD_PILLAR_CORAL_BLOCK       = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_pillar_coral_block");
	public static Block DEAD_LIME_BRAIN_CORAL_BLOCK   = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_lime_brain_coral_block");
	public static Block DEAD_GREEN_BUBBLE_CORAL_BLOCK = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_green_bubble_coral_block");
	public static Block DEAD_ANTIPATHES_CORAL_BLOCK   = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_antipathes_coral_block");
	public static Block DEAD_STAGHORN_CORAL_BLOCK     = new Block(UAProperties.DEAD_CORAL_BLOCK).setRegistryName(Reference.MODID, "dead_staghorn_coral_block");
	public static Block ACAN_CORAL_BLOCK              = new BlockUACoralBlock(DEAD_ACAN_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.CYAN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "acan_coral_block");
	public static Block PILLAR_CORAL_BLOCK            = new BlockUACoralBlock(DEAD_PILLAR_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "pillar_coral_block");
	public static Block LIME_BRAIN_CORAL_BLOCK        = new BlockUACoralBlock(DEAD_LIME_BRAIN_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.LIME).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "lime_brain_coral_block");
	public static Block GREEN_BUBBLE_CORAL_BLOCK      = new BlockUACoralBlock(DEAD_GREEN_BUBBLE_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.GREEN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "green_bubble_coral_block");
	public static Block ANTIPATHES_CORAL_BLOCK        = new BlockUACoralBlock(DEAD_ANTIPATHES_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.LIGHT_BLUE).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "antipathes_coral_block");
	public static Block STAGHORN_CORAL_BLOCK          = new BlockUACoralBlock(DEAD_STAGHORN_CORAL_BLOCK, Block.Properties.create(Material.CORAL, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "staghorn_coral_block");
	public static Block DEAD_ACAN_CORAL               = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_acan_coral");
	public static Block DEAD_PILLAR_CORAL             = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_pillar_coral");
	public static Block DEAD_LIME_BRAIN_CORAL         = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_lime_brain_coral");
	public static Block DEAD_GREEN_BUBBLE_CORAL       = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_green_bubble_coral");
	public static Block DEAD_ANTIPATHES_CORAL         = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_antipathes_coral");
	public static Block DEAD_STAGHORN_CORAL           = new BlockUACoralDead(UAProperties.DEAD_CORAL).setRegistryName(Reference.MODID, "dead_staghorn_coral");
	public static Block ACAN_CORAL               	  = new BlockUACoral(DEAD_ACAN_CORAL, UAProperties.CORAL_BASE(MaterialColor.CYAN)).setRegistryName(Reference.MODID, "acan_coral");
	public static Block PILLAR_CORAL             	  = new BlockUACoral(DEAD_PILLAR_CORAL, UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName(Reference.MODID, "pillar_coral");
	public static Block LIME_BRAIN_CORAL              = new BlockUACoral(DEAD_LIME_BRAIN_CORAL, UAProperties.CORAL_BASE(MaterialColor.LIME)).setRegistryName(Reference.MODID, "lime_brain_coral");
	public static Block GREEN_BUBBLE_CORAL            = new BlockUACoral(DEAD_GREEN_BUBBLE_CORAL, UAProperties.CORAL_BASE(MaterialColor.GREEN)).setRegistryName(Reference.MODID, "green_bubble_coral");
	public static Block ANTIPATHES_CORAL              = new BlockUACoral(DEAD_ANTIPATHES_CORAL, UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName(Reference.MODID, "antipathes_coral");
	public static Block STAGHORN_CORAL                = new BlockUACoral(DEAD_STAGHORN_CORAL, UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName(Reference.MODID, "staghorn_coral");
	public static Block DEAD_ACAN_CORAL_WALL_FAN      = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_acan_coral_wall_fan");
	public static Block DEAD_PILLAR_CORAL_WALL_FAN    = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_pillar_coral_wall_fan");
	public static Block DEAD_LIME_BRAIN_CORAL_WALL_FAN = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_lime_brain_coral_wall_fan");
	public static Block DEAD_GREEN_BUBBLE_CORAL_WALL_FAN = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_green_bubble_coral_wall_fan");
	public static Block DEAD_ANTIPATHES_CORAL_WALL_FAN = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_antipathes_coral_wall_fan");
	public static Block DEAD_STAGHORN_CORAL_WALL_FAN  = new BlockUACoralWallFanDead(UAProperties.DEAD_CORAL).setRegistryName("dead_staghorn_coral_wall_fan");
	public static Block ACAN_CORAL_WALL_FAN           = new BlockUACoralWallFan(DEAD_ACAN_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.CYAN)).setRegistryName("acan_coral_wall_fan");
	public static Block PILLAR_CORAL_WALL_FAN         = new BlockUACoralWallFan(DEAD_PILLAR_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName("pillar_coral_wall_fan");
	public static Block LIME_BRAIN_CORAL_WALL_FAN     = new BlockUACoralWallFan(DEAD_LIME_BRAIN_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIME)).setRegistryName("lime_brain_coral_wall_fan");
	public static Block GREEN_BUBBLE_CORAL_WALL_FAN   = new BlockUACoralWallFan(DEAD_GREEN_BUBBLE_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.GREEN)).setRegistryName("green_bubble_coral_wall_fan");
	public static Block ANTIPATHES_CORAL_WALL_FAN     = new BlockUACoralWallFan(DEAD_ANTIPATHES_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName("antipathes_coral_wall_fan");
	public static Block STAGHORN_CORAL_WALL_FAN       = new BlockUACoralWallFan(DEAD_STAGHORN_CORAL_WALL_FAN, UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName("staghorn_coral_wall_fan");
	public static Block DEAD_ACAN_CORAL_FAN           = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_acan_coral_fan");
	public static Block DEAD_PILLAR_CORAL_FAN         = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_pillar_coral_fan");
	public static Block DEAD_LIME_BRAIN_CORAL_FAN     = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_lime_brain_coral_fan");
	public static Block DEAD_GREEN_BUBBLE_CORAL_FAN   = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_green_bubble_coral_fan");
	public static Block DEAD_ANTIPATHES_CORAL_FAN     = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_antipathes_coral_fan");
	public static Block DEAD_STAGHORN_CORAL_FAN       = new BlockUACoralFanDead().setRegistryName(Reference.MODID, "dead_staghorn_coral_fan");
	public static Block ACAN_CORAL_FAN           	  = new BlockUACoralFan(DEAD_ACAN_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.CYAN)).setRegistryName(Reference.MODID, "acan_coral_fan");
	public static Block PILLAR_CORAL_FAN              = new BlockUACoralFan(DEAD_PILLAR_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.ORANGE_TERRACOTTA)).setRegistryName(Reference.MODID, "pillar_coral_fan");
	public static Block LIME_BRAIN_CORAL_FAN          = new BlockUACoralFan(DEAD_LIME_BRAIN_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIME)).setRegistryName(Reference.MODID, "lime_brain_coral_fan");
	public static Block GREEN_BUBBLE_CORAL_FAN        = new BlockUACoralFan(DEAD_GREEN_BUBBLE_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.GREEN)).setRegistryName(Reference.MODID, "green_bubble_coral_fan");
	public static Block ANTIPATHES_CORAL_FAN          = new BlockUACoralFan(DEAD_ANTIPATHES_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.LIGHT_BLUE)).setRegistryName(Reference.MODID, "antipathes_coral_fan");
	public static Block STAGHORN_CORAL_FAN            = new BlockUACoralFan(DEAD_STAGHORN_CORAL_FAN, UAProperties.CORAL_BASE(MaterialColor.GRAY_TERRACOTTA)).setRegistryName(Reference.MODID, "staghorn_coral_fan");
	
	public static Block GUARDIAN_SPINE                = new BlockSpine(UAProperties.SPINES).setRegistryName(Reference.MODID, "guardian_spine");
	public static Block ELDER_EYE                     = new BlockElderEye(UAProperties.ELDER_PRISMARINE).setRegistryName(Reference.MODID, "elder_eye");
	
	@SubscribeEvent
	public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		final Block blocks[] = {
			DEAD_ACAN_CORAL_BLOCK, DEAD_PILLAR_CORAL_BLOCK, DEAD_LIME_BRAIN_CORAL_BLOCK, DEAD_GREEN_BUBBLE_CORAL_BLOCK, DEAD_ANTIPATHES_CORAL_BLOCK, DEAD_STAGHORN_CORAL_BLOCK, 
			ACAN_CORAL_BLOCK, PILLAR_CORAL_BLOCK, LIME_BRAIN_CORAL_BLOCK, GREEN_BUBBLE_CORAL_BLOCK, ANTIPATHES_CORAL_BLOCK, STAGHORN_CORAL_BLOCK,
			DEAD_ACAN_CORAL, DEAD_PILLAR_CORAL, DEAD_LIME_BRAIN_CORAL, DEAD_GREEN_BUBBLE_CORAL, DEAD_ANTIPATHES_CORAL, DEAD_STAGHORN_CORAL,
			ACAN_CORAL, PILLAR_CORAL, LIME_BRAIN_CORAL, GREEN_BUBBLE_CORAL, ANTIPATHES_CORAL, STAGHORN_CORAL,
			DEAD_ACAN_CORAL_WALL_FAN, DEAD_PILLAR_CORAL_WALL_FAN, DEAD_LIME_BRAIN_CORAL_WALL_FAN, DEAD_GREEN_BUBBLE_CORAL_WALL_FAN, DEAD_ANTIPATHES_CORAL_WALL_FAN, DEAD_STAGHORN_CORAL_WALL_FAN,
			ACAN_CORAL_WALL_FAN, PILLAR_CORAL_WALL_FAN, LIME_BRAIN_CORAL_WALL_FAN, GREEN_BUBBLE_CORAL_WALL_FAN, ANTIPATHES_CORAL_WALL_FAN, STAGHORN_CORAL_WALL_FAN,
			DEAD_ACAN_CORAL_FAN, DEAD_PILLAR_CORAL_FAN, DEAD_LIME_BRAIN_CORAL_FAN, DEAD_GREEN_BUBBLE_CORAL_FAN, DEAD_ANTIPATHES_CORAL_FAN, DEAD_STAGHORN_CORAL_FAN,
			ACAN_CORAL_FAN, PILLAR_CORAL_FAN, LIME_BRAIN_CORAL_FAN, GREEN_BUBBLE_CORAL_FAN, ANTIPATHES_CORAL_FAN, STAGHORN_CORAL_FAN, 
			GUARDIAN_SPINE, ELDER_EYE
		};
		event.getRegistry().registerAll(blocks);
	}
	
	@SubscribeEvent
	public static void onRegisterItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ACAN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_PILLAR_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_LIME_BRAIN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_GREEN_BUBBLE_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ANTIPATHES_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_STAGHORN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLAR_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(LIME_BRAIN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(GREEN_BUBBLE_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ANTIPATHES_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAGHORN_CORAL_BLOCK, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ACAN_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_PILLAR_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_LIME_BRAIN_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_GREEN_BUBBLE_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_ANTIPATHES_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(DEAD_STAGHORN_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ACAN_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(PILLAR_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(LIME_BRAIN_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(GREEN_BUBBLE_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(ANTIPATHES_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createSimpleItemBlock(STAGHORN_CORAL, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_ACAN_CORAL_FAN, DEAD_ACAN_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_PILLAR_CORAL_FAN, DEAD_PILLAR_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_LIME_BRAIN_CORAL_FAN, DEAD_LIME_BRAIN_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_GREEN_BUBBLE_CORAL_FAN, DEAD_GREEN_BUBBLE_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_ANTIPATHES_CORAL_FAN, DEAD_ANTIPATHES_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(DEAD_STAGHORN_CORAL_FAN, DEAD_STAGHORN_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(ACAN_CORAL_FAN, ACAN_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(PILLAR_CORAL_FAN, PILLAR_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(LIME_BRAIN_CORAL_FAN, LIME_BRAIN_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(GREEN_BUBBLE_CORAL_FAN, GREEN_BUBBLE_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(ANTIPATHES_CORAL_FAN, ANTIPATHES_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
		registry.register(RegistryUtils.createWallOrFloorItem(STAGHORN_CORAL_FAN, STAGHORN_CORAL_WALL_FAN, ItemGroup.BUILDING_BLOCKS));
	
		registry.register(RegistryUtils.createSimpleItemBlock(GUARDIAN_SPINE, ItemGroup.DECORATIONS));
		registry.register(RegistryUtils.createItemBlockWithRarity(ELDER_EYE, ItemGroup.REDSTONE, EnumRarity.RARE));
	}
}
