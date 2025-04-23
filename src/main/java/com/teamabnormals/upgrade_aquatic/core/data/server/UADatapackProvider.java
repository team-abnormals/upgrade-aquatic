package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAConfiguredFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UAFeatures.UAPlacedFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UARegistries;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.*;
import com.teamabnormals.upgrade_aquatic.core.registry.datapack.UAWorldCarvers.UAConfiguredWorldCarvers;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UADatapackProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(UARegistries.PIKE_VARIANT, UAPikeVariants::bootstrap)
			.add(Registries.DAMAGE_TYPE, UADamageTypes::bootstrap)
			.add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, UAStructureRepaletters::bootstrap)
			.add(Registries.CONFIGURED_FEATURE, UAConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, UAPlacedFeatures::bootstrap)
			.add(Registries.CONFIGURED_CARVER, UAConfiguredWorldCarvers::bootstrap)
			.add(Registries.TRIM_MATERIAL, UATrimMaterials::bootstrap)
			.add(Registries.JUKEBOX_SONG, UAJukeboxSongs::bootstrap)
			.add(Registries.PAINTING_VARIANT, UAPaintingVariants::bootstrap)
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, UABiomeModifiers::bootstrap);

	public UADatapackProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider, BUILDER, builder -> {
			builder.accept(UAStructureRepaletters.KELPY_OCEAN_RUINS, UAStructureRepaletters.CONFIG);
		}, Set.of(UpgradeAquatic.MOD_ID));
	}
}