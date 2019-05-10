package com.teamabnormals.upgrade_aquatic.core.registry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import com.teamabnormals.upgrade_aquatic.common.blocks.BlockUACoral;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UABlocks {
	
	/* Blocks */
	public static Block DEAD_ACAN_CORAL = new Block(Block.Properties.create(Material.CORAL, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(Reference.MODID, "dead_acan_coral_block");
	public static Block DEAD_PILLAR_CORAL_CORAL = new Block(Block.Properties.create(Material.CORAL, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(Reference.MODID, "dead_pillar_coral_block");
	public static Block DEAD_LIME_BRAIN_CORAL = new Block(Block.Properties.create(Material.CORAL, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(Reference.MODID, "dead_lime_brain_coral_block");
	public static Block DEAD_GREEN_BUBBLE_CORAL = new Block(Block.Properties.create(Material.CORAL, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(Reference.MODID, "dead_green_bubble_coral_block");
	public static Block DEAD_ANTIPATHES_CORAL = new Block(Block.Properties.create(Material.CORAL, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(Reference.MODID, "dead_antipathes_coral_block");
	public static Block DEAD_STAGHORN_CORAL = new Block(Block.Properties.create(Material.CORAL, MaterialColor.GRAY).hardnessAndResistance(1.5F, 6.0F)).setRegistryName(Reference.MODID, "dead_staghorn_coral_block");
	public static Block ACAN_CORAL = new BlockUACoral(DEAD_ACAN_CORAL, Block.Properties.create(Material.CORAL, MaterialColor.CYAN).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)).setRegistryName(Reference.MODID, "acan_coral_block");
	
	@SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
		final Block blocks[] = {
			DEAD_ACAN_CORAL, DEAD_PILLAR_CORAL_CORAL, DEAD_LIME_BRAIN_CORAL, DEAD_GREEN_BUBBLE_CORAL, DEAD_ANTIPATHES_CORAL, DEAD_STAGHORN_CORAL, ACAN_CORAL
		};
		event.getRegistry().registerAll(blocks);
	}
	
	@SubscribeEvent
    public static void onRegisterItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(new ItemBlock(DEAD_ACAN_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(DEAD_ACAN_CORAL.getRegistryName()));
		registry.register(new ItemBlock(DEAD_PILLAR_CORAL_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(DEAD_PILLAR_CORAL_CORAL.getRegistryName()));
		registry.register(new ItemBlock(DEAD_LIME_BRAIN_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(DEAD_LIME_BRAIN_CORAL.getRegistryName()));
		registry.register(new ItemBlock(DEAD_GREEN_BUBBLE_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(DEAD_GREEN_BUBBLE_CORAL.getRegistryName()));
		registry.register(new ItemBlock(DEAD_ANTIPATHES_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(DEAD_ANTIPATHES_CORAL.getRegistryName()));
		registry.register(new ItemBlock(DEAD_STAGHORN_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(DEAD_STAGHORN_CORAL.getRegistryName()));
		registry.register(new ItemBlock(ACAN_CORAL, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(Reference.MODID, "acan_coral_block"));
	}
}
