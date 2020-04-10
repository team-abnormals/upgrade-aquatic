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
		() -> new TileEntityType<>(TileEntityBedroll::new, Sets.newHashSet(UABlocks.BEDROLL.get(), UABlocks.GRAY_BEDROLL.get(), UABlocks.LIGHT_GRAY_BEDROLL.get(), UABlocks.BROWN_BEDROLL.get(), UABlocks.WHITE_BEDROLL.get(), UABlocks.BLACK_BEDROLL.get(), UABlocks.PINK_BEDROLL.get(), UABlocks.RED_BEDROLL.get(), UABlocks.ORANGE_BEDROLL.get(), UABlocks.YELLOW_BEDROLL.get(), UABlocks.LIME_BEDROLL.get(), UABlocks.GREEN_BEDROLL.get(), UABlocks.LIGHT_BLUE_BEDROLL.get(), UABlocks.BLUE_BEDROLL.get(), UABlocks.CYAN_BEDROLL.get(), UABlocks.MAGENTA_BEDROLL.get(), UABlocks.PURPLE_BEDROLL.get()), null));

}
