package com.teamabnormals.upgrade_aquatic.core.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

	/**
     * Client specific configuration
     */
	public static class Client {
		public final ConfigValue<Integer> daysTillRenderInsomniaOverlay;
		
		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client only settings for Upgrade Aquatic")
            .push("client");
			
			daysTillRenderInsomniaOverlay = builder
				.comment("The amount of days till the insomnia overlay is rendered. Default: Zero(No Insomnia at all)")
				.translation("upgrade_aquatic.configgui.daysTillRenderInsomniaOverlay")
				.define("daysTillRenderInsomniaOverlay", 0);
			
			builder.pop();
		}
		
	}
	
	public static final ForgeConfigSpec CLIENTSPEC;
	public static final Client CLIENT;
	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENTSPEC = specPair.getRight();
		CLIENT = specPair.getLeft();
	}
    
    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    	
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.ConfigReloading configEvent) {
    	
    }

    
}
