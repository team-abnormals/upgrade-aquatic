package com.minecraftabnormals.upgrade_aquatic.core.config;

import static com.minecraftabnormals.upgrade_aquatic.core.config.ConfigHolder.daysTillRenderInsomniaOverlay;

import net.minecraftforge.fml.config.ModConfig;

public class ConfigHelper {
	
	public static ModConfig clientConfig;

	public static void updateClientConfig(ModConfig config) {
		clientConfig = config;
		
		daysTillRenderInsomniaOverlay = Config.CLIENT.daysTillRenderInsomniaOverlay.get();
	}
	
}
