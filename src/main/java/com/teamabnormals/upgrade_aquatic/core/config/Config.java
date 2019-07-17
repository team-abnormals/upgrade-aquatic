package com.teamabnormals.upgrade_aquatic.core.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class Config {

	/**
     * Client specific configuration
     */
	public static class Client {
		public final BooleanValue renderInsomniaOverlay;
		
		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client only settings for Upgrade Aquatic")
            .push("client");
			
			renderInsomniaOverlay = builder
				.comment("Toggle off to make the insomnia overlay not render")
				.translation("upgrade_aquatic.configgui.renderInsomniaOverlay")
				.define("renderInsomniaOverlay", true);
			
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
