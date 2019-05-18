package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.core.proxy.*;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = Reference.MODID)
public class UpgradeAquatic {
	
	public static UpgradeAquatic instance;
	public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	
	public UpgradeAquatic() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
	}
	
	private void preInit(final FMLCommonSetupEvent event) {
		proxy.preInit();
	}

	@SuppressWarnings("unused")
	private void Init(final FMLCommonSetupEvent event) {}
}
