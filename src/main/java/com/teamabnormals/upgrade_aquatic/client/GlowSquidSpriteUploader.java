package com.teamabnormals.upgrade_aquatic.client;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * @author Ocelot
 */
public class GlowSquidSpriteUploader extends TextureAtlasHolder {
	public static final ResourceLocation ATLAS_LOCATION = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/atlas/glow_squid.png");
	public static final ResourceLocation SQUID_SPRITE = new ResourceLocation(UpgradeAquatic.MOD_ID, "glow_squid");
	public static final ResourceLocation GLOW_SPRITE = new ResourceLocation(UpgradeAquatic.MOD_ID, "glow_squid_emissive");

	private static GlowSquidSpriteUploader uploader;

	public GlowSquidSpriteUploader(TextureManager textureManagerIn) {
		super(textureManagerIn, ATLAS_LOCATION, SQUID_SPRITE);
	}

	/**
	 * Initializes this uploader under the mod bus.
	 *
	 * @param bus The bus to register to
	 */
	public static void init(IEventBus bus) {
		bus.addListener(EventPriority.NORMAL, false, RegisterColorHandlersEvent.class, event -> {
			Minecraft minecraft = Minecraft.getInstance();
			ResourceManager resourceManager = minecraft.getResourceManager();
			if (resourceManager instanceof ReloadableResourceManager) {
				((ReloadableResourceManager) resourceManager).registerReloadListener(uploader = new GlowSquidSpriteUploader(minecraft.textureManager));
			}
		});
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
