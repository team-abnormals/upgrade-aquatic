package com.teamabnormals.upgrade_aquatic.core.config;

import net.minecraftforge.fml.config.ModConfig;
import static com.teamabnormals.upgrade_aquatic.core.config.ConfigHolder.renderInsomniaOverlay;

public class ConfigHelper {
	
	public static ModConfig clientConfig;

	public static void updateClientConfig(ModConfig config) {
		clientConfig = config;
		
		renderInsomniaOverlay = Config.CLIENT.renderInsomniaOverlay.get();
	}
	
}
