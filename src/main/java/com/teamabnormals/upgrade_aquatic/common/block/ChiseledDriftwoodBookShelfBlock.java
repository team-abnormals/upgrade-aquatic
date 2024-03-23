package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

public class ChiseledDriftwoodBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledDriftwoodBookShelfBlock(Properties properties) {
		super(properties);
	}

	public int getHitSlot(Vec2 vec2) {
		int i = vec2.y >= 0.5F ? 0 : 1;
		int j = getSection(i, vec2.x);
		return j + i;
	}

	public static int getSection(int i, float x) {
		if (i == 0) {
			return x < 0.5F ? 0 : 1;
		} else {
			return x < 0.25F ? 1 : x < 0.5F ? 2 : x < 0.75 ? 3 : 4;
		}
	}
}
