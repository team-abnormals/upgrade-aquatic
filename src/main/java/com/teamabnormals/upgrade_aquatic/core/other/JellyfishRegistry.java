package com.teamabnormals.upgrade_aquatic.core.other;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfishEntity;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Registry class for the jellyfish
 *
 * @author SmellyModder(Luke Tonon)
 */
public class JellyfishRegistry {
	public static final List<JellyfishEntry<?>> JELLYFISHES = Lists.newArrayList();
	public static final Map<Class<? extends AbstractJellyfishEntity>, Integer> IDS = Maps.newHashMap();

	static {
		registerJellyfish(UAEntities.BOX_JELLYFISH, BoxJellyfishEntity.class, Rarity.COMMON);
		registerJellyfish(UAEntities.CASSIOPEA_JELLYFISH, CassiopeaJellyfishEntity.class, Rarity.COMMON);
		registerJellyfish(UAEntities.IMMORTAL_JELLYFISH, ImmortalJellyfishEntity.class, Rarity.COMMON);
	}

	public static <J extends AbstractJellyfishEntity> void registerJellyfish(Supplier<EntityType<J>> jellyfish, Class<J> jellyfishClass, Rarity rarity) {
		JELLYFISHES.add(new JellyfishEntry<J>(jellyfish, rarity));
		IDS.putIfAbsent(jellyfishClass, getNextId());
	}

	public static List<JellyfishEntry<?>> collectJelliesMatchingRarity(Rarity rarity) {
		List<JellyfishEntry<?>> jellies = JELLYFISHES;
		jellies.removeIf(jellyfish -> jellyfish.rarity != rarity);
		return jellies;
	}

	public static JellyfishEntry<?> getRandomJellyfish(Random rand) {
		float chance = rand.nextFloat();
		if (chance > 50.0F) {
			List<JellyfishEntry<?>> commonJellies = collectJelliesMatchingRarity(Rarity.COMMON);
			return commonJellies.get(rand.nextInt(commonJellies.size()));
		} else if (chance < 50.0F && chance > 15.0F) {
			List<JellyfishEntry<?>> uncommonJellies = collectJelliesMatchingRarity(Rarity.UNCOMMON);
			return uncommonJellies.get(rand.nextInt(uncommonJellies.size()));
		} else if (chance < 15.0F && chance > 0.5F) {
			List<JellyfishEntry<?>> rarerJellies = collectJelliesMatchingRarity(Rarity.RARE);
			return rarerJellies.get(rand.nextInt(rarerJellies.size()));
		}
		List<JellyfishEntry<?>> epicJellies = collectJelliesMatchingRarity(Rarity.EPIC);
		return epicJellies.get(rand.nextInt(epicJellies.size()));
	}

	public static int getNextId() {
		int id = 0;
		if (!IDS.isEmpty()) {
			for (Map.Entry<Class<? extends AbstractJellyfishEntity>, Integer> entries : IDS.entrySet()) {
				int ids = entries.getValue();
				if (ids > id) {
					id = ids;
				}
			}
		}
		return id + 1;
	}

	public static class JellyfishEntry<J extends AbstractJellyfishEntity> {
		public final Supplier<EntityType<J>> jellyfish;
		public final Rarity rarity;

		public JellyfishEntry(Supplier<EntityType<J>> jellyfish, Rarity rarity) {
			this.jellyfish = jellyfish;
			this.rarity = rarity;
		}
	}
}