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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class UAStructureRepaletters {

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
		ConfigValueCondition config = config(UAConfig.COMMON.kelpyOceanRuins, "kelpy_ocean_ruins");

		basicRepaletter(context, structures, config, "kelpy_cobblestone_in_ocean_ruins", Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get(), BuiltinStructures.OCEAN_RUIN_WARM, BuiltinStructures.OCEAN_RUIN_COLD);
		basicRepaletter(context, structures, config, "kelpy_stone_bricks_in_ocean_ruins", Blocks.MOSSY_STONE_BRICKS, UABlocks.KELPY_STONE_BRICKS.get(), BuiltinStructures.OCEAN_RUIN_WARM, BuiltinStructures.OCEAN_RUIN_COLD);
	}

	@SafeVarargs
	private static void basicRepaletter(BootstrapContext<StructureRepaletterEntry> context, HolderGetter<Structure> structures, ICondition condition, String name, Block replacesBlock, Block replacesWith, ResourceKey<Structure>... selector) {
		context.register(
				repaletterKey(name),
				new StructureRepaletterEntry(
						// TODO: conditional holder set?
						HolderSet.direct(Stream.of(selector).map(structures::getOrThrow).collect(Collectors.toList())),
						Optional.empty(), false,
						new SimpleStructureRepaletter(replacesBlock, replacesWith))
		);
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
		return new ConfigValueCondition(UAConditions.CONFIG.get(), value, key, Maps.newHashMap(), inverted);
	}

	public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
		return config(value, key, false);
	}

	private static ResourceKey<StructureRepaletterEntry> repaletterKey(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, UpgradeAquatic.location(name));
	}

}
