package com.teamabnormals.upgrade_aquatic.core.other;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.AbstractJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.CassiopeaJellyfish;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.ImmortalJellyfish;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Registry class for the jellyfish.
 *
 * @author SmellyModder (Luke Tonon)
 */
public class JellyfishRegistry {
	public static final List<JellyfishEntry<?>> JELLYFISHES = Lists.newArrayList();
	public static final Map<Class<? extends AbstractJellyfish>, Integer> IDS = Maps.newHashMap();

	static {
		registerJellyfish(UAEntityTypes.BOX_JELLYFISH, BoxJellyfish.class, Rarity.COMMON);
		registerJellyfish(UAEntityTypes.CASSIOPEA_JELLYFISH, CassiopeaJellyfish.class, Rarity.COMMON);
		registerJellyfish(UAEntityTypes.IMMORTAL_JELLYFISH, ImmortalJellyfish.class, Rarity.COMMON);
	}

	public static <J extends AbstractJellyfish> void registerJellyfish(Supplier<EntityType<J>> jellyfish, Class<J> jellyfishClass, Rarity rarity) {
		JELLYFISHES.add(new JellyfishEntry<>(jellyfish, rarity));
		IDS.putIfAbsent(jellyfishClass, IDS.size() + 1);
	}

	public static List<JellyfishEntry<?>> collectJelliesMatchingRarity(Rarity rarity) {
		return JELLYFISHES.stream().filter(jellyfish -> jellyfish.rarity == rarity).toList();
	}

	public static JellyfishEntry<?> getRandomJellyfish(Random random) {
		float chance = random.nextFloat();
		if (chance > 0.5F) {
			List<JellyfishEntry<?>> commonJellies = collectJelliesMatchingRarity(Rarity.COMMON);
			return commonJellies.get(random.nextInt(commonJellies.size()));
		} else if (chance > 0.15F) {
			List<JellyfishEntry<?>> uncommonJellies = collectJelliesMatchingRarity(Rarity.UNCOMMON);
			return uncommonJellies.get(random.nextInt(uncommonJellies.size()));
		} else if (chance > 0.05F) {
			List<JellyfishEntry<?>> rareJellies = collectJelliesMatchingRarity(Rarity.RARE);
			return rareJellies.get(random.nextInt(rareJellies.size()));
		}
		List<JellyfishEntry<?>> epicJellies = collectJelliesMatchingRarity(Rarity.EPIC);
		return epicJellies.get(random.nextInt(epicJellies.size()));
	}

	public record JellyfishEntry<J extends AbstractJellyfish>(Supplier<EntityType<J>> jellyfish, Rarity rarity) {
	}
}