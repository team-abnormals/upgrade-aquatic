package com.teamabnormals.upgrade_aquatic.core.registry;

import com.google.common.collect.Sets;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityBedroll;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UATileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MODID);
	
	public static final RegistryObject<TileEntityType<TileEntityElderEye>> ELDER_EYE = TILE_ENTITY_TYPES.register("elder_eye", () -> new TileEntityType<>(TileEntityElderEye::new, Sets.newHashSet(UABlocks.ELDER_EYE.get()), null));
	public static final RegistryObject<TileEntityType<TileEntityBedroll>> BEDROLL = TILE_ENTITY_TYPES.register("bedroll",
		() -> new TileEntityType<>(TileEntityBedroll::new, Sets.newHashSet(UABlocks.BEDROLL_LEATHER.get(), UABlocks.BEDROLL_GRAY.get(), UABlocks.BEDROLL_LIGHT_GRAY.get(), UABlocks.BEDROLL_BROWN.get(), UABlocks.BEDROLL_WHITE.get(), UABlocks.BEDROLL_BLACK.get(), UABlocks.BEDROLL_PINK.get(), UABlocks.BEDROLL_RED.get(), UABlocks.BEDROLL_ORANGE.get(), UABlocks.BEDROLL_YELLOW.get(), UABlocks.BEDROLL_LIME.get(), UABlocks.BEDROLL_GREEN.get(), UABlocks.BEDROLL_LIGHT_BLUE.get(), UABlocks.BEDROLL_BLUE.get(), UABlocks.BEDROLL_CYAN.get(), UABlocks.BEDROLL_MAGENTA.get(), UABlocks.BEDROLL_PURPLE.get()), null));

}
