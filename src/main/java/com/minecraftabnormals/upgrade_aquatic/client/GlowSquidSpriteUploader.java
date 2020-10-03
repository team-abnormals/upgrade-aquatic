package com.minecraftabnormals.upgrade_aquatic.client;

import java.util.stream.Stream;

import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SpriteUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * @author Ocelot
 */
public class GlowSquidSpriteUploader extends SpriteUploader {
	public static final ResourceLocation ATLAS_LOCATION = new ResourceLocation(UpgradeAquatic.MODID, "textures/atlas/glow_squid.png");
	public static final ResourceLocation SQUID_SPRITE = new ResourceLocation(UpgradeAquatic.MODID, "glow_squid");

	private static GlowSquidSpriteUploader uploader;

	public GlowSquidSpriteUploader(TextureManager textureManagerIn, ResourceLocation atlasTextureLocation, String prefixIn) {
		super(textureManagerIn, atlasTextureLocation, prefixIn);
	}

	@Override
	protected Stream<ResourceLocation> getResourceLocations() {
		return Stream.of(SQUID_SPRITE);
	}

	/**
	 * Initializes this uploader under the mod bus.
	 *
	 * @param bus The bus to register to
	 */
	public static void init(IEventBus bus) {
		bus.addListener(EventPriority.NORMAL, false, ColorHandlerEvent.Block.class, event -> {
			Minecraft minecraft = Minecraft.getInstance();
			IResourceManager resourceManager = minecraft.getResourceManager();
			if (resourceManager instanceof IReloadableResourceManager) {
				((IReloadableResourceManager) resourceManager).addReloadListener(uploader = new GlowSquidSpriteUploader(minecraft.textureManager, ATLAS_LOCATION, "entity"));
			}
		});
	}

	/**
	 * @return The sprite for the glow squid
	 */
	public static TextureAtlasSprite getSprite() {
		return uploader.getSprite(SQUID_SPRITE);
	}
}
