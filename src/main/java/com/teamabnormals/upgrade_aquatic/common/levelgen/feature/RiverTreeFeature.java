package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.TreeUtil;
import com.teamabnormals.upgrade_aquatic.common.block.MulberryVineBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.Random;

public class RiverTreeFeature extends Feature<TreeConfiguration> {
	public RiverTreeFeature(Codec<TreeConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
		WorldGenLevel worldIn = context.level();
		Random rand = context.random();
		BlockPos position = context.origin();
		int height = 3 + rand.nextInt(2) + rand.nextInt(2);
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getMaxBuildHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY())
					k = 0;
				if (j >= position.getY() + 1 + height - 2)
					k = 2;
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getMaxBuildHeight()) {
							if (!TreeFeature.isAirOrLeaves(worldIn, blockpos$mutableblockpos.set(l, j, i1)))
								flag = false;
						} else
							flag = false;
					}
				}
			}

			if (!flag) {
				return false;
			} else if (TreeUtil.isValidGround(worldIn, position.below(), (SaplingBlock) UABlocks.RIVER_SAPLING.get()) && position.getY() < worldIn.getMaxBuildHeight() - height - 1) {
				TreeUtil.setDirtAt(worldIn, position.below());

				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (TreeFeature.isAirOrLeaves(worldIn, blockpos)) {
						this.placeLogAt(worldIn, blockpos);
					}
				}

				logY = position.getY() + height - rand.nextInt(2);

				position = new BlockPos(logX, logY, logZ);

				this.createLeaves(worldIn, position.below(), rand, false);
				this.createLeaves(worldIn, position, rand, false);
				this.createLeaves(worldIn, position.above(), rand, true);
				this.placeLeafAt(worldIn, position.above(), rand);
				this.placeLeafAt(worldIn, position.above(2), rand);

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void createLeaves(LevelSimulatedRW worldIn, BlockPos newPos, Random rand, boolean small) {
		int leafSize = 1;
		for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (small) {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						if (rand.nextInt(3) != 0)
							this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand);
					}
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand);
					} else if (rand.nextInt(4) == 0) {
						this.placeLeafAt(worldIn, newPos.offset(k3, 0, j4), rand);
					}
				}
			}
		}
	}

	private void placeLogAt(LevelWriter worldIn, BlockPos pos) {
		TreeUtil.setForcedState(worldIn, pos, UABlocks.RIVER_LOG.get().defaultBlockState());
	}

	private void placeLeafAt(LevelSimulatedRW world, BlockPos pos, Random rand) {
		if (TreeFeature.isAirOrLeaves(world, pos)) {
			TreeUtil.setForcedState(world, pos, UABlocks.RIVER_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1));
		}
		if (isAir(world, pos.below()) && rand.nextInt(3) == 0 && rand.nextBoolean()) {
			BlockState state = UABlocks.MULBERRY_VINE.get().defaultBlockState().setValue(MulberryVineBlock.AGE, 4).setValue(MulberryVineBlock.DOUBLE, rand.nextBoolean());
			if (state.canSurvive((LevelReader) world, pos.below()))
				TreeUtil.setForcedState(world, pos.below(), state);
		}
	}
}
