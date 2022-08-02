package com.teamabnormals.upgrade_aquatic.common.item;

import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry.JellyfishEntry;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.List;
import java.util.Random;

public class JellyfishSpawnEggItem extends ForgeSpawnEggItem {

	public JellyfishSpawnEggItem(int primaryColor, int secondaryColor, Properties properties) {
		super(UAEntityTypes.BOX_JELLYFISH, primaryColor, secondaryColor, properties);
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
		List<JellyfishEntry<?>> jellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
		return jellies.get(new Random().nextInt(jellies.size())).jellyfish().get();
	}
}