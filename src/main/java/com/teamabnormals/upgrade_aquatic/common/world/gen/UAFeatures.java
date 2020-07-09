package com.teamabnormals.upgrade_aquatic.common.world.gen;

import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureDriftwood;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureDunes;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureFloweringRush;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePickerelweed;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoral;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineCoralShelf;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeaturePrismarineStalactite;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureRiverTree;
import com.teamabnormals.upgrade_aquatic.common.world.gen.feature.FeatureSearocket;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Reference.MODID);
		
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_SHELF      = FEATURES.register("prismarine_coral_shelf", () -> new FeaturePrismarineCoralShelf(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_STALACTITE = FEATURES.register("prismarine_coral_stalactite", () -> new FeaturePrismarineStalactite(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL            = FEATURES.register("prismarine_coral", () -> new FeaturePrismarineCoral(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PICKERELWEED                = FEATURES.register("pickerelweed", () -> new FeaturePickerelweed(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SEAROCKET                   = FEATURES.register("searocket", () -> new FeatureSearocket(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> FLOWERING_RUSH              = FEATURES.register("flowering_rush", () -> new FeatureFloweringRush(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DRIFTWOOD                   = FEATURES.register("driftwood", () -> new FeatureDriftwood(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DUNES                       = FEATURES.register("dunes", () -> new FeatureDunes(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> RIVER_TREE 				 = FEATURES.register("river_tree", () -> new FeatureRiverTree(BaseTreeFeatureConfig.field_236676_a_));
}