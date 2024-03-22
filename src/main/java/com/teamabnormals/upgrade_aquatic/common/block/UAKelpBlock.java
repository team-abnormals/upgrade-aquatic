package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.KelpType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.KelpBlock;

public class UAKelpBlock extends KelpBlock {
	private final KelpType kelpType;

	public UAKelpBlock(KelpType kelpType, Properties props) {
		super(props);
		this.kelpType = kelpType;
	}

	@Override
	public Block getBodyBlock() {
		return switch (this.kelpType) {
			case TONGUE -> UABlocks.TONGUE_KELP_PLANT.get();
			case THORNY -> UABlocks.THORNY_KELP_PLANT.get();
			case OCHRE -> UABlocks.OCHRE_KELP_PLANT.get();
			case POLAR -> UABlocks.POLAR_KELP_PLANT.get();
		};
	}
}
