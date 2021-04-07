package com.minecraftabnormals.upgrade_aquatic.common.items;

import com.minecraftabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;
import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.other.JellyfishRegistry.JellyfishEntry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;

import java.util.List;
import java.util.Random;

public class JellyfishSpawnEggItem extends AbnormalsSpawnEggItem {

	public JellyfishSpawnEggItem(int primaryColor, int secondaryColor, Properties properties) {
		super(null, primaryColor, secondaryColor, properties);
	}

	@Override
	public EntityType<?> getType(CompoundNBT compound){
		if(compound != null && compound.contains("EntityTag", 10)) {
			CompoundNBT entityTag = compound.getCompound("EntityTag");

			if(entityTag.contains("id", 8)) {
				return EntityType.byKey(entityTag.getString("id")).orElse(getRandomJellyfish());
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