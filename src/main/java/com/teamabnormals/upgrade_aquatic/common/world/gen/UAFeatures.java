package com.teamabnormals.upgrade_aquatic.common.world.gen;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureDriftwood;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureFloweringRush;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePickerelweed;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoral;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoralShelf;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineStalactite;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureSearocket;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class UAFeatures {
	
	public static final Feature<NoFeatureConfig> PRISMARINE_CORAL_SHELF      = new FeaturePrismarineCoralShelf(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PRISMARINE_CORAL_STALACTITE = new FeaturePrismarineStalactite(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PRISMARINE_CORAL            = new FeaturePrismarineCoral(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PICKERELWEED                = new FeaturePickerelweed(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SEAROCKET                   = new FeatureSearocket(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> FLOWERING_RUSH              = new FeatureFloweringRush(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> DRIFTWOOD                   = new FeatureDriftwood(NoFeatureConfig::deserialize);
	
	@SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
			PRISMARINE_CORAL, PRISMARINE_CORAL_SHELF, PRISMARINE_CORAL_STALACTITE, PICKERELWEED, SEAROCKET, DRIFTWOOD
		);
	}
	
}
