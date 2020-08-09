package com.minecraftabnormals.upgrade_aquatic.core.registry;

import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.DriftwoodFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.AmmoniteFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.BeachgrassDunesFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.FloweringRushFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.PickerelweedFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.PrismarineCoralFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.PrismarineCoralShelfFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.PrismarineStalactiteFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.RiverTreeFeature;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature.SearocketFeature;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;

import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class UAFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UpgradeAquatic.MODID);
		
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_SHELF      = FEATURES.register("prismarine_coral_shelf", () -> new PrismarineCoralShelfFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL_STALACTITE = FEATURES.register("prismarine_coral_stalactite", () -> new PrismarineStalactiteFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PRISMARINE_CORAL            = FEATURES.register("prismarine_coral", () -> new PrismarineCoralFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> PICKERELWEED                = FEATURES.register("pickerelweed", () -> new PickerelweedFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> SEAROCKET                   = FEATURES.register("searocket", () -> new SearocketFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> FLOWERING_RUSH              = FEATURES.register("flowering_rush", () -> new FloweringRushFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DRIFTWOOD                   = FEATURES.register("driftwood", () -> new DriftwoodFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<NoFeatureConfig>> DUNES                       = FEATURES.register("dunes", () -> new BeachgrassDunesFeature(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Feature<BaseTreeFeatureConfig>> RIVER_TREE 			 = FEATURES.register("river_tree", () -> new RiverTreeFeature(BaseTreeFeatureConfig.CODEC_BASE_TREE_FEATURE_CONFIG));
	
	public static void setupGeneration() {
	    AmmoniteFeature.setupGeneration();
	}
}