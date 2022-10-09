package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import com.teamabnormals.upgrade_aquatic.common.block.MulberryVineBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class RiverTreeFeature extends Feature<TreeConfiguration> {
	public RiverTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
		WorldGenLevel level = context.level();
		Random random = context.random();
		BlockPos origin = context.origin();
		TreeConfiguration config = context.config();
		int height = config.trunkPlacer.getTreeHeight(random);
		boolean flag = true;

		if (origin.getY() > level.getMinBuildHeight() && origin.getY() + height + 1 <= level.getMaxBuildHeight()) {
			for (int j = origin.getY(); j <= origin.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == origin.getY())
					k = 0;
				if (j >= origin.getY() + 1 + height - 2)
					k = 2;
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
				for (int l = origin.getX() - k; l <= origin.getX() + k && flag; ++l) {
					for (int i1 = origin.getZ() - k; i1 <= origin.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < level.getMaxBuildHeight()) {
							if (!TreeFeature.isAirOrLeaves(level, blockpos$mutableblockpos.set(l, j, i1)))
								flag = false;
						} else
							flag = false;
					}
				}
			}

			if (!flag) {
				return false;
			} else if (TreeUtil.isValidGround(level, origin.below(), (SaplingBlock) UABlocks.RIVER_SAPLING.get()) && origin.getY() < level.getMaxBuildHeight() - height - 1) {
				Set<BlockPos> logPositions = Sets.newHashSet();
				BiConsumer<BlockPos, BlockState> consumer = (pos, state) -> {
					logPositions.add(pos.immutable());
					TreeUtil.setForcedState(level, pos, state);
				};

				config.trunkPlacer.placeTrunk(level, consumer, random, height, origin, config);

				origin = new BlockPos(origin.getX(), origin.getY() + height - random.nextInt(2), origin.getZ());
				this.createLeaves(level, origin.below(), random, config, false);
				this.createLeaves(level, origin, random, config, false);
				this.createLeaves(level, origin.above(), random, config, true);
				this.placeLeavesAt(level, origin.above(), random, config);
				this.placeLeavesAt(level, origin.above(2), random, config);

				TreeUtil.updateLeaves(level, logPositions);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void createLeaves(LevelSimulatedRW worldIn, BlockPos newPos, Random rand, TreeConfiguration config, boolean small) {
		int leafSize = 1;
		for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (small) {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						if (rand.nextInt(3) != 0)
							this.placeLeavesAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					}
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeavesAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					} else if (rand.nextInt(4) == 0) {
						this.placeLeavesAt(worldIn, newPos.offset(k3, 0, j4), rand, config);
					}
				}
			}
		}
	}

	private void placeLeavesAt(LevelSimulatedRW world, BlockPos pos, Random rand, TreeConfiguration config) {
		TreeUtil.placeLeafAt(world, pos, rand, config);
		if (isAir(world, pos.below()) && rand.nextInt(3) == 0 && rand.nextBoolean()) {
			BlockState state = UABlocks.MULBERRY_VINE.get().defaultBlockState().setValue(MulberryVineBlock.AGE, 4).setValue(MulberryVineBlock.DOUBLE, rand.nextBoolean());
			if (state.canSurvive((LevelReader) world, pos.below()))
				TreeUtil.setForcedState(world, pos.below(), state);
		}
	}
}
