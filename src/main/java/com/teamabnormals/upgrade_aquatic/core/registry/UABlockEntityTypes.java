package com.teamabnormals.upgrade_aquatic.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.upgrade_aquatic.common.block.entity.BedrollBlockEntity;
import com.teamabnormals.upgrade_aquatic.common.block.entity.ElderEyeBlockEntity;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class UABlockEntityTypes {
	public static final BlockEntitySubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getBlockEntitySubHelper();

	public static final RegistryObject<BlockEntityType<ElderEyeBlockEntity>> ELDER_EYE = HELPER.createBlockEntity("elder_eye", ElderEyeBlockEntity::new, () -> Set.of(UABlocks.ELDER_EYE.get()));
	public static final RegistryObject<BlockEntityType<BedrollBlockEntity>> BEDROLL = HELPER.createBlockEntity("bedroll", BedrollBlockEntity::new, () -> Set.of(UABlocks.BEDROLL.get(), UABlocks.GRAY_BEDROLL.get(), UABlocks.LIGHT_GRAY_BEDROLL.get(), UABlocks.BROWN_BEDROLL.get(), UABlocks.WHITE_BEDROLL.get(), UABlocks.BLACK_BEDROLL.get(), UABlocks.PINK_BEDROLL.get(), UABlocks.RED_BEDROLL.get(), UABlocks.ORANGE_BEDROLL.get(), UABlocks.YELLOW_BEDROLL.get(), UABlocks.LIME_BEDROLL.get(), UABlocks.GREEN_BEDROLL.get(), UABlocks.LIGHT_BLUE_BEDROLL.get(), UABlocks.BLUE_BEDROLL.get(), UABlocks.CYAN_BEDROLL.get(), UABlocks.MAGENTA_BEDROLL.get(), UABlocks.PURPLE_BEDROLL.get()));
}