package com.teamabnormals.upgrade_aquatic.core.data.server;

import com.teamabnormals.blueprint.common.world.modification.structure.SimpleStructureRepaletter;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterProvider;
import com.teamabnormals.blueprint.core.util.modification.selection.ConditionedResourceSelector;
import com.teamabnormals.blueprint.core.util.modification.selection.selectors.NamesResourceSelector;
import com.teamabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraftforge.eventbus.api.EventPriority;

public final class UAStructureRepaletterProvider extends StructureRepaletterProvider {

	public UAStructureRepaletterProvider(DataGenerator dataGenerator) {
		super(dataGenerator, UpgradeAquatic.MOD_ID);
	}

	@Override
	protected void registerRepaletters() {
		this.registerRepaletter("kelpy_cobblestone_in_ocean_ruins", new ConditionedResourceSelector(new NamesResourceSelector(BuiltinStructures.OCEAN_RUIN_WARM.location(), BuiltinStructures.OCEAN_RUIN_COLD.location())), EventPriority.NORMAL, new SimpleStructureRepaletter(Blocks.MOSSY_COBBLESTONE, UABlocks.KELPY_COBBLESTONE.get()));
		this.registerRepaletter("kelpy_stone_bricks_in_ocean_ruins", new ConditionedResourceSelector(new NamesResourceSelector(BuiltinStructures.OCEAN_RUIN_WARM.location(), BuiltinStructures.OCEAN_RUIN_COLD.location())), EventPriority.NORMAL, new SimpleStructureRepaletter(Blocks.MOSSY_STONE_BRICKS, UABlocks.KELPY_STONE_BRICKS.get()));
	}

}
