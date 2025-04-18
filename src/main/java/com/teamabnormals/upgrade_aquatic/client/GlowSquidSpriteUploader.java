package com.teamabnormals.upgrade_aquatic.client;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

/**
 * @author Ocelot
 */
@EventBusSubscriber(modid = UpgradeAquatic.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GlowSquidSpriteUploader extends TextureAtlasHolder {
	public static final ResourceLocation ATLAS_LOCATION = UpgradeAquatic.location("textures/atlas/glow_squid.png");
	public static final ResourceLocation SQUID_SPRITE = UpgradeAquatic.location("glow_squid");
	public static final ResourceLocation GLOW_SPRITE = UpgradeAquatic.location("glow_squid_emissive");

	private static GlowSquidSpriteUploader uploader;

	public GlowSquidSpriteUploader(TextureManager textureManagerIn) {
		super(textureManagerIn, ATLAS_LOCATION, SQUID_SPRITE);
	}

	/**
	 * Registers client reload listeners
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(uploader = new GlowSquidSpriteUploader(Minecraft.getInstance().getTextureManager()));
	}

	/**
	 * @return The sprite for the glow squid
	 */
	public static TextureAtlasSprite getSprite() {
		return uploader.getSprite(SQUID_SPRITE);
	}

	/**
	 * @return The sprite for the glow squid outer layer
	 */
	public static TextureAtlasSprite getGlowSprite() {
		return uploader.getSprite(GLOW_SPRITE);
	}
}
