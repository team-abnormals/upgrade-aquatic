package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.proxy.*;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = Reference.MODID)
public class UpgradeAquatic {
	
	public static UpgradeAquatic instance;
	public static TileEntityType<TileEntityElderEye> ELDER_EYE;
	public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	
	public UpgradeAquatic() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(TileEntityType.class, this::registerTileEntities);
	}
	
	private void preInit(final FMLCommonSetupEvent event) {
		proxy.preInit();
	}

	@SuppressWarnings("unused")
	private void Init(final FMLCommonSetupEvent event) {}
	
	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register(ELDER_EYE = (TileEntityType<TileEntityElderEye>) TileEntityType.Builder.create(TileEntityElderEye::new, UABlocks.ELDER_EYE).build(null).setRegistryName(Reference.MODID, "elder_eye"));
	}
}
