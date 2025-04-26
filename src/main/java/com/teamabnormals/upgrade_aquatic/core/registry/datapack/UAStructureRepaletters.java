package com.teamabnormals.upgrade_aquatic.core.registry.datapack;

import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UAConditionSerializers.UAConditions;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.function.BiConsumer;

import static com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry.*;

public final class UAStructureRepaletters {
	public static final ResourceKey<StructureRepaletterEntry> KELPY_OCEAN_RUINS = create("kelpy_ocean_ruins");

	public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		context.register(KELPY_OCEAN_RUINS, repalette().repaletters(
				simple(Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get()),
				simple(Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get())
		).select(holder(structures, BuiltinStructures.OCEAN_RUIN_WARM)));
	}

	public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
		builder.accept(KELPY_OCEAN_RUINS, UAConditions.KELPY_OCEAN_RUINS);
	}

	private static ResourceKey<StructureRepaletterEntry> create(String name) {
		return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, UpgradeAquatic.location(name));
	}
}
