package com.minecraftabnormals.upgrade_aquatic.client;

import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.stream.Stream;

/**
 * @author Ocelot
 */
public class GlowSquidSpriteUploader extends TextureAtlasHolder {
	public static final ResourceLocation ATLAS_LOCATION = new ResourceLocation(UpgradeAquatic.MOD_ID, "textures/atlas/glow_squid.png");
	public static final ResourceLocation SQUID_SPRITE = new ResourceLocation(UpgradeAquatic.MOD_ID, "glow_squid");
	public static final ResourceLocation GLOW_SPRITE = new ResourceLocation(UpgradeAquatic.MOD_ID, "glow_squid_emissive");

	private static GlowSquidSpriteUploader uploader;

	public GlowSquidSpriteUploader(TextureManager textureManagerIn, ResourceLocation atlasTextureLocation, String prefixIn) {
		super(textureManagerIn, atlasTextureLocation, prefixIn);
	}

	@Override
	protected Stream<ResourceLocation> getResourcesToLoad() {
		return Stream.of(SQUID_SPRITE, GLOW_SPRITE);
	}

	/**
	 * Initializes this uploader under the mod bus.
	 *
	 * @param bus The bus to register to
	 */
	public static void init(IEventBus bus) {
		bus.addListener(EventPriority.NORMAL, false, ColorHandlerEvent.Block.class, event -> {
			Minecraft minecraft = Minecraft.getInstance();
			ResourceManager resourceManager = minecraft.getResourceManager();
			if (resourceManager instanceof ReloadableResourceManager) {
				((ReloadableResourceManager) resourceManager).registerReloadListener(uploader = new GlowSquidSpriteUploader(minecraft.textureManager, ATLAS_LOCATION, "entity/glow_squid"));
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
