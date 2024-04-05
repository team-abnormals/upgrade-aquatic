package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.common.levelgen.feature.BlueprintTreeFeature;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class RiverTreeFeature extends BlueprintTreeFeature {

	public RiverTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public void doPlace(FeaturePlaceContext<TreeConfiguration> context) {
		RandomSource random = context.random();
		BlockPos origin = context.origin();
		TreeConfiguration config = context.config();

		int trunkHeight = config.trunkPlacer.getTreeHeight(random);
		for (int y = 0; y < trunkHeight; y++) {
			this.addLog(origin.above(y));
		}

		origin = new BlockPos(origin.getX(), origin.getY() + trunkHeight - random.nextInt(2), origin.getZ());

		this.createLeaves(origin.below(), random, false);
		this.createLeaves(origin, random, false);
		this.createLeaves(origin.above(), random, true);
		this.addFoliage(origin.above());
		this.addFoliage(origin.above(2));
	}

	private void createLeaves(BlockPos newPos, RandomSource rand, boolean small) {
		int leafSize = 1;
		for (int i = -leafSize; i <= leafSize; ++i) {
			for (int j = -leafSize; j <= leafSize; ++j) {
				if (((Math.abs(i) != leafSize || Math.abs(j) != leafSize) && (!small || rand.nextInt(3) != 0)) || (!small && rand.nextInt(4) == 0)) {
					this.addFoliage(newPos.offset(i, 0, j));
				}
			}
		}
	}

	@Override
	public BlockState getSapling() {
		return UABlocks.RIVER_SAPLING.get().defaultBlockState();
	}
}
