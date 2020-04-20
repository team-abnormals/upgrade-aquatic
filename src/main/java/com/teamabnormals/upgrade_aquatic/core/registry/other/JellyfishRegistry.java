package com.teamabnormals.upgrade_aquatic.core.registry.other;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.*;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Rarity;

/**
 * Registry class for the jellyfish
 * @author SmellyModder(Luke Tonon)
 */
public class JellyfishRegistry {
	public static final List<JellyfishEntry<?>> JELLYFISHES = Lists.newArrayList();
	public static final Map<Class<? extends AbstractEntityJellyfish>, Float> IDS = Maps.newHashMap();
	
	static {
		registerJellyfish(() -> UAEntities.BOX_JELLYFISH.get(), EntityBoxJellyfish.class, Rarity.COMMON);
	}

	public static <J extends AbstractEntityJellyfish> void registerJellyfish(Supplier<EntityType<J>> jellyfish, Class<J> jellyfishClass, Rarity rarity) {
		JELLYFISHES.add(new JellyfishEntry<J>(jellyfish, rarity));
		IDS.putIfAbsent(jellyfishClass, getHighestId());
	}
	
	public static List<JellyfishEntry<?>> collectJelliesMatchingRarity(Rarity rarity) {
		List<JellyfishEntry<?>> jellies = JELLYFISHES;
		jellies.removeIf(jellyfish -> jellyfish.rarity != rarity);
		return jellies;
	}
	
	public static float getHighestId() {
		float id = 1.0F;
		if(!IDS.isEmpty()) {
			for(Map.Entry<Class<? extends AbstractEntityJellyfish>, Float> entries : IDS.entrySet()) {
				float ids = entries.getValue();
				if(ids > id) {
					id = ids;
				}
			}
		}
		return id;
	}
	
	public static class JellyfishEntry<J extends AbstractEntityJellyfish> {
		public final Supplier<EntityType<J>> jellyfish;
		public final Rarity rarity;
		
		public JellyfishEntry(Supplier<EntityType<J>> jellyfish, Rarity rarity) {
			this.jellyfish = jellyfish;
			this.rarity = rarity;
		}
	}
}