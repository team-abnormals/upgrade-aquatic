package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.other.UABiomeModifiers;
import com.teamabnormals.upgrade_aquatic.core.other.UADamageTypes;
import com.teamabnormals.upgrade_aquatic.core.other.UAStructureRepaletters;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAConfiguredFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAPlacedFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAWorldCarvers.UAConfiguredWorldCarvers;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UADatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.DAMAGE_TYPE, UADamageTypes::bootstrap)
			.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, UAStructureRepaletters::bootstrap)
			.add(Registries.CONFIGURED_FEATURE, UAConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, UAPlacedFeatures::bootstrap)
			.add(Registries.CONFIGURED_CARVER, UAConfiguredWorldCarvers::bootstrap)
			.add(ForgeRegistries.Keys.BIOME_MODIFIERS, UABiomeModifiers::bootstrap);

	public UADatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, Set.of(UpgradeAquatic.MOD_ID));
	}
}