package com.teamabnormals.upgrade_aquatic.common.world.gen;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePickerelweed;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoral;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoralShelf;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineStalactite;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class UAFeatures {
	
	public static final Feature<NoFeatureConfig> PRISMARINE_CORAL_SHELF      = new FeaturePrismarineCoralShelf(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PRISMARINE_CORAL_STALACTITE = new FeaturePrismarineStalactite(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PRISMARINE_CORAL            = new FeaturePrismarineCoral(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PICKERELWEED                = new FeaturePickerelweed(NoFeatureConfig::deserialize);
	
	@SubscribeEvent
    public static void registerFeatures(IForgeRegistry<Feature<?>> event) {
		generic(event).add("prismarine_coral_shelf", PRISMARINE_CORAL_SHELF);
		generic(event).add("prismarine_coral_stalactite", PRISMARINE_CORAL_STALACTITE);
		generic(event).add("prismarine_coral", PRISMARINE_CORAL);
		generic(event).add("pickerelweed", PICKERELWEED);
	}
	
	public static <T extends IForgeRegistryEntry<T>> Generic<T> generic(IForgeRegistry<T> registry) {
		return new Generic<>(registry);
	}
	
	public static class Generic<T extends IForgeRegistryEntry<T>> {
		private final IForgeRegistry<T> registry;

		private Generic(IForgeRegistry<T> registry) {
			this.registry = registry;
		}

		public Generic<T> add(String name, T entry) {
			ResourceLocation registryName = GameData.checkPrefix(name, false);
			entry.setRegistryName(registryName);
			this.registry.register(entry);
			return this;
        }
    }
	
}
