package com.teamabnormals.upgrade_aquatic.core;

import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(value = Reference.MODID)
public class UpgradeAquatic {
	
	public static UpgradeAquatic instance;
	
	public UpgradeAquatic() {
		instance = this;
	}
	
	@SuppressWarnings("unused")
	private void preInit(final FMLCommonSetupEvent event) {}

	@SuppressWarnings("unused")
	private void Init(final FMLCommonSetupEvent event) {}
}
