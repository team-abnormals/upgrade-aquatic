package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.MathUtil;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

public class SearocketFeature extends Feature<NoFeatureConfig> {

	private static final Supplier<BlockState> SEAROCKET(boolean pink) {
		return pink ? () -> UABlocks.PINK_SEAROCKET.get().defaultBlockState() : () -> UABlocks.WHITE_SEAROCKET.get().defaultBlockState();
	}

	public SearocketFeature(Codec<NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean colorType;
		if (worldIn.getBiome(pos).getBaseTemperature() < 0.2D) {
			colorType = rand.nextFloat() <= 0.25F;
			if (SEAROCKET(colorType).get().canSurvive(worldIn, pos)) {
				this.generateSearocketPatch(worldIn, pos, colorType, rand.nextInt(8));
				return true;
			}
		} else {
			colorType = rand.nextFloat() <= 0.75F;
			if (SEAROCKET(colorType).get().canSurvive(worldIn, pos)) {
				this.generateSearocketPatch(worldIn, pos, colorType, rand.nextInt(8));
				return true;
			}
		}
		return false;
	}

	public void generateSearocketPatch(IWorld world, BlockPos pos, boolean pink, int randomDesign) {
		// 0 - a, 1 - b, 2 - c
		int[] patterns = new int[3];
		switch (randomDesign) {
			default:
			case 0:
				patterns[0] = 3;
				patterns[1] = 4;
				patterns[2] = 9;
			case 1:
				patterns[0] = 2;
				patterns[1] = 8;
				patterns[2] = 5;
			case 2:
				patterns[0] = 3;
				patterns[1] = 4;
				patterns[2] = 7;
			case 3:
				patterns[0] = 3;
				patterns[1] = 13;
				patterns[2] = 12;
			case 4:
				patterns[0] = 3;
				patterns[1] = 12;
				patterns[2] = 12;
			case 5:
				patterns[0] = 2;
				patterns[1] = 12;
				patterns[2] = 12;
			case 6:
				patterns[0] = 3;
				patterns[1] = 5;
				patterns[2] = 3;
			case 7:
				patterns[0] = 3;
				patterns[1] = 3;
				patterns[2] = 6;
		}
		BlockPos startPos = pos;
		MathUtil.Equation r = (theta) -> {
			return (Math.cos(patterns[1] * theta) / patterns[2] + 1) * patterns[0];
		};
		if (!world.isEmptyBlock(startPos.below()) && !world.isEmptyBlock(startPos.below(2)) && !world.isEmptyBlock(startPos.below(3))) {
			int repeatsDown = world.getRandom().nextInt(2) + 2;
			for (int repeats = 0; repeats < repeatsDown; repeats++) {
				pos = pos.offset(0, -repeats, 0);
				for (int i = -(patterns[0] / patterns[2] + patterns[0]); i < patterns[0] / patterns[2] + patterns[0]; i++) {
					for (int j = -(patterns[0] / patterns[2] + patterns[0]); j < patterns[0] / patterns[2] + patterns[0]; j++) {
						double radius = r.compute(Math.atan2(j, i));
						BlockPos placingPos = pos.offset(i, 0, j);
						if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius) {
							if (SEAROCKET(pink).get().canSurvive(world, placingPos) && world.getFluidState(placingPos).isEmpty()) {
								world.setBlock(placingPos, SEAROCKET(pink).get(), 2);
							}
						}
					}
				}
			}
		}
	}
}
