package com.teamabnormals.upgrade_aquatic.common.world.gen;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.*;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, Reference.MODID);
		
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_SHELF      = FEATURES.register("prismarine_coral_shelf", () -> new FeaturePrismarineCoralShelf(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_STALACTITE = FEATURES.register("prismarine_coral_stalactite", () -> new FeaturePrismarineStalactite(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL            = FEATURES.register("prismarine_coral", () -> new FeaturePrismarineCoral(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> PICKERELWEED                = FEATURES.register("pickerelweed", () -> new FeaturePickerelweed(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> SEAROCKET                   = FEATURES.register("searocket", () -> new FeatureSearocket(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> FLOWERING_RUSH              = FEATURES.register("flowering_rush", () -> new FeatureFloweringRush(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> DRIFTWOOD                   = FEATURES.register("driftwood", () -> new FeatureDriftwood(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<NoFeatureConfig>> DUNES                       = FEATURES.register("dunes", () -> new FeatureDunes(NoFeatureConfig::deserialize));
	public static final RegistryObject<Feature<TreeFeatureConfig>> RIVER_TREE 				 = FEATURES.register("river_tree", () -> new FeatureRiverTree(TreeFeatureConfig::func_227338_a_, false));
	public static final RegistryObject<Feature<TreeFeatureConfig>> MULBERRY_RIVER_TREE 		 = FEATURES.register("mulberry_river_tree", () -> new FeatureRiverTree(TreeFeatureConfig::func_227338_a_, true));
}