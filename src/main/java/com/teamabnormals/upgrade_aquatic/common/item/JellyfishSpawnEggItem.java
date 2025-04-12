package com.teamabnormals.upgrade_aquatic.common.item;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry;
import com.teamabnormals.upgrade_aquatic.core.other.JellyfishRegistry.JellyfishEntry;
import com.teamabnormals.upgrade_aquatic.core.registry.UAEntityTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.util.List;
import java.util.Random;

public class JellyfishSpawnEggItem extends DeferredSpawnEggItem {
	
	private static final MapCodec<EntityType<?>> ENTITY_TYPE_FIELD_CODEC = BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("id");
	
	public JellyfishSpawnEggItem(int primaryColor, int secondaryColor, Properties properties) {
		super(UAEntityTypes.BOX_JELLYFISH, primaryColor, secondaryColor, properties);
	}
	
	@Override
	public EntityType<?> getType(ItemStack stack) {
		CustomData customdata = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
		return !customdata.isEmpty() ? customdata.read(ENTITY_TYPE_FIELD_CODEC).result().orElse(getRandomJellyfish()) : getRandomJellyfish();
	}

	private EntityType<?> getRandomJellyfish() {
		List<JellyfishEntry<?>> jellies = JellyfishRegistry.collectJelliesMatchingRarity(Rarity.COMMON);
		return jellies.get(new Random().nextInt(jellies.size())).jellyfish().get();
	}
}