package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityBedroll;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.util.UARegistryHelper;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UATileEntities {
	public static final UARegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER;
	
	public static final RegistryObject<TileEntityType<TileEntityElderEye>> ELDER_EYE = HELPER.createTileEntity("elder_eye", TileEntityElderEye::new, () -> new Block[] {UABlocks.ELDER_EYE.get()});
	public static final RegistryObject<TileEntityType<TileEntityBedroll>> BEDROLL = HELPER.createTileEntity("bedroll", TileEntityBedroll::new, () -> new Block[] {UABlocks.BEDROLL.get(), UABlocks.GRAY_BEDROLL.get(), UABlocks.LIGHT_GRAY_BEDROLL.get(), UABlocks.BROWN_BEDROLL.get(), UABlocks.WHITE_BEDROLL.get(), UABlocks.BLACK_BEDROLL.get(), UABlocks.PINK_BEDROLL.get(), UABlocks.RED_BEDROLL.get(), UABlocks.ORANGE_BEDROLL.get(), UABlocks.YELLOW_BEDROLL.get(), UABlocks.LIME_BEDROLL.get(), UABlocks.GREEN_BEDROLL.get(), UABlocks.LIGHT_BLUE_BEDROLL.get(), UABlocks.BLUE_BEDROLL.get(), UABlocks.CYAN_BEDROLL.get(), UABlocks.MAGENTA_BEDROLL.get(), UABlocks.PURPLE_BEDROLL.get()});
}