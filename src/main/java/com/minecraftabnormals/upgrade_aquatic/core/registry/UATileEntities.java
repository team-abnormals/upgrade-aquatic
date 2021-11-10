package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.TileEntitySubRegistryHelper;
import com.minecraftabnormals.upgrade_aquatic.client.tileentity.ElderEyeTileEntityRenderer;
import com.minecraftabnormals.upgrade_aquatic.common.tileentities.BedrollTileEntity;
import com.minecraftabnormals.upgrade_aquatic.common.tileentities.ElderEyeTileEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UATileEntities {
	public static final TileEntitySubRegistryHelper HELPER = UpgradeAquatic.REGISTRY_HELPER.getTileEntitySubHelper();

	public static final RegistryObject<TileEntityType<ElderEyeTileEntity>> ELDER_EYE = HELPER.createTileEntity("elder_eye", ElderEyeTileEntity::new, () -> new Block[]{UABlocks.ELDER_EYE.get()});
	public static final RegistryObject<TileEntityType<BedrollTileEntity>> BEDROLL = HELPER.createTileEntity("bedroll", BedrollTileEntity::new, () -> new Block[]{UABlocks.BEDROLL.get(), UABlocks.GRAY_BEDROLL.get(), UABlocks.LIGHT_GRAY_BEDROLL.get(), UABlocks.BROWN_BEDROLL.get(), UABlocks.WHITE_BEDROLL.get(), UABlocks.BLACK_BEDROLL.get(), UABlocks.PINK_BEDROLL.get(), UABlocks.RED_BEDROLL.get(), UABlocks.ORANGE_BEDROLL.get(), UABlocks.YELLOW_BEDROLL.get(), UABlocks.LIME_BEDROLL.get(), UABlocks.GREEN_BEDROLL.get(), UABlocks.LIGHT_BLUE_BEDROLL.get(), UABlocks.BLUE_BEDROLL.get(), UABlocks.CYAN_BEDROLL.get(), UABlocks.MAGENTA_BEDROLL.get(), UABlocks.PURPLE_BEDROLL.get()});

	public static void registerRenderers() {
		ClientRegistry.bindTileEntityRenderer(UATileEntities.ELDER_EYE.get(), ElderEyeTileEntityRenderer::new);
	}
}