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
	
	public static final RegistryObject<TileEntityType<TileEntityElderEye>> ELDER_EYE = TILE_ENTITY_TYPES.register("elder_eye", () -> new TileEntityType<>(TileEntityElderEye::new, Sets.newHashSet(UABlocks.ELDER_EYE), null));
	public static final RegistryObject<TileEntityType<TileEntityBedroll>> BEDROLL = TILE_ENTITY_TYPES.register("bedroll",
		() -> new TileEntityType<>(TileEntityBedroll::new, Sets.newHashSet(UABlocks.BEDROLL_LEATHER, UABlocks.BEDROLL_GRAY, UABlocks.BEDROLL_LIGHT_GRAY, UABlocks.BEDROLL_BROWN, UABlocks.BEDROLL_WHITE, UABlocks.BEDROLL_BLACK, UABlocks.BEDROLL_PINK, UABlocks.BEDROLL_RED, UABlocks.BEDROLL_ORANGE, UABlocks.BEDROLL_YELLOW, UABlocks.BEDROLL_LIME, UABlocks.BEDROLL_GREEN, UABlocks.BEDROLL_LIGHT_BLUE, UABlocks.BEDROLL_BLUE, UABlocks.BEDROLL_CYAN, UABlocks.BEDROLL_MAGENTA, UABlocks.BEDROLL_PURPLE), null));

}
