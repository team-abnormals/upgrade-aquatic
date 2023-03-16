package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.google.common.collect.Maps;
import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterProvider;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.eventbus.api.EventPriority;

public final class UAStructureRepaletterProvider extends StructureRepaletterProvider {

	public UAStructureRepaletterProvider(DataGenerator dataGenerator) {
		super(dataGenerator, UpgradeAquatic.MOD_ID);
	}

	@Override
	protected void registerRepaletters() {
		this.simpleRepaletter("kelpy_cobblestone_in_ocean_ruins", selector(BuiltinStructures.OCEAN_RUIN_WARM.location(), BuiltinStructures.OCEAN_RUIN_COLD.location()), Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get());
		this.simpleRepaletter("kelpy_stone_bricks_in_ocean_ruins", selector(BuiltinStructures.OCEAN_RUIN_WARM.location(), BuiltinStructures.OCEAN_RUIN_COLD.location()), Blocks.MOSSY_STONE_BRICKS, UABlocks.KELPY_STONE_BRICKS.get());
	}

	private static ConditionedResourceSelector selector(ResourceLocation... structures) {
		return new ConditionedResourceSelector(new NamesResourceSelector(structures), new ConfigValueCondition(new ResourceLocation(UpgradeAquatic.MOD_ID, "config"), UAConfig.COMMON.kelpyOceanRuins, "kelpy_ocean_ruins", Maps.newHashMap(), false));
	}

	public void simpleRepaletter(String name, ConditionedResourceSelector selector, Block toReplace, Block replacesWith) {
		this.registerRepaletter(name, selector, EventPriority.NORMAL, new SimpleStructureRepaletter(toReplace, replacesWith));
	}
}
