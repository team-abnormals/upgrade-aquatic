package com.minecraftabnormals.upgrade_aquatic.common.items;

import java.util.List;
import java.util.Random;

import com.minecraftabnormals.upgrade_aquatic.core.registry.other.JellyfishRegistry;
import com.minecraftabnormals.upgrade_aquatic.core.registry.other.JellyfishRegistry.JellyfishEntry;
import com.teamabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;

public class ItemJellyfishSpawnEgg extends AbnormalsSpawnEggItem {

	public ItemJellyfishSpawnEgg(int primaryColor, int secondaryColor, Properties properties) {
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