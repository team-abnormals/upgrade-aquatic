package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UASoundEvents;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;

import java.util.function.UnaryOperator;

public class UAJukeboxSongs {
	public static final ResourceKey<JukeboxSong> JUKEBOX_SONG_ATLANTIS = createKey("atlantis");

	public static void bootstrap(BootstrapContext<JukeboxSong> context) {
		register(context, JUKEBOX_SONG_ATLANTIS, UASoundEvents.MUSIC_DISC_ATLANTIS, Rarity.RARE.getStyleModifier(), 114, 14);
	}

	private static ResourceKey<JukeboxSong> createKey(String name) {
		return ResourceKey.create(Registries.JUKEBOX_SONG, UpgradeAquatic.location(name));
	}

	private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, UnaryOperator<Style> styleModifier, int lengthInSeconds, int comparatorOutput) {
		context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())).withStyle(styleModifier), lengthInSeconds, comparatorOutput));
	}
}
