package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

public class ChiseledRiverBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledRiverBookShelfBlock(Properties properties) {
		super(properties);
	}

	public int getHitSlot(Vec2 vec2) {
		return vec2.x < 0.5F ? (vec2.y < 0.375F ? 3 : vec2.x < 0.25F ? 0 : 1) : (vec2.y >= 0.625F ? 2 : vec2.x < 0.75F ? 4 : 5);
	}
}
