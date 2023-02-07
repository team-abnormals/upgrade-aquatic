package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.KelpPlantBlock;

public class UAKelpPlantBlock extends KelpPlantBlock {
	private final KelpType kelpType;

	public UAKelpPlantBlock(KelpType kelpType, Properties props) {
		super(props);
		this.kelpType = kelpType;
	}

	@Override
	protected GrowingPlantHeadBlock getHeadBlock() {
		return switch (this.kelpType) {
			case TONGUE -> (GrowingPlantHeadBlock) UABlocks.TONGUE_KELP.get();
			case THORNY -> (GrowingPlantHeadBlock) UABlocks.THORNY_KELP.get();
			case OCHRE -> (GrowingPlantHeadBlock) UABlocks.OCHRE_KELP.get();
			case POLAR -> (GrowingPlantHeadBlock) UABlocks.POLAR_KELP.get();
		};
	}
}
