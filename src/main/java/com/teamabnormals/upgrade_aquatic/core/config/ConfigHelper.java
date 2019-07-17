package com.teamabnormals.upgrade_aquatic.core.config;

import net.minecraftforge.fml.config.ModConfig;
import static com.teamabnormals.upgrade_aquatic.core.config.ConfigHolder.daysTillRenderInsomniaOverlay;

public class ConfigHelper {
	
	public static ModConfig clientConfig;

	public static void updateClientConfig(ModConfig config) {
		clientConfig = config;
		
		daysTillRenderInsomniaOverlay = Config.CLIENT.daysTillRenderInsomniaOverlay.get();
	}
	
}
