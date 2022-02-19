package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry.JellyfishEntry;
import com.teamabnormals.blueprint.common.item.BlueprintSpawnEggItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Random;

public class JellyfishSpawnEggItem extends BlueprintSpawnEggItem {

	public JellyfishSpawnEggItem(int primaryColor, int secondaryColor, Properties properties) {
		super(null, primaryColor, secondaryColor, properties);
	}

	@Override
	public EntityType<?> getType(CompoundTag compound) {
		if (compound != null && compound.contains("EntityTag", 10)) {
			CompoundTag entityTag = compound.getCompound("EntityTag");

			if (entityTag.contains("id", 8)) {
				return EntityType.byString(entityTag.getString("id")).orElse(getRandomJellyfish());
			}
		}
		return getRandomJellyfish();
	}

	private EntityType<?> getRandomJellyfish() {
		Random rand = new Random();
		List<JellyfishEntry<?>> jellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
		return jellies.get(rand.nextInt(jellies.size())).jellyfish.get();
	}
}