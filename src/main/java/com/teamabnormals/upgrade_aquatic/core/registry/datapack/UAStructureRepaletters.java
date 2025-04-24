package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAConditions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class UAStructureRepaletters {
	public static final ResourceKey<StructureRepaletterEntry> KELPY_OCEAN_RUINS = create("kelpy_ocean_ruins");

	public static final ConfigValueCondition CONFIG = config(UAConfig.COMMON.kelpyOceanRuins, "kelpy_ocean_ruins");

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		context.register(KELPY_OCEAN_RUINS, StructureRepaletterEntry.repalette().repaletters(
				new SimpleStructureRepaletter(Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get()),
				new SimpleStructureRepaletter(Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get())
		).select(HolderSet.direct(structures.getOrThrow(BuiltinStructures.OCEAN_RUIN_WARM))));
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(UAConditions.CONFIG.get(), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	private static ResourceKey<StructureRepaletterEntry> create(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, UpgradeAquatic.location(name));
	}
}
