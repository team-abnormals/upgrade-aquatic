package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import com.minecraftabnormals.abnormals_core.core.util.MathUtil;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class PrismarineStalactiteFeature extends PrismarineCoralFeature {

	public PrismarineStalactiteFeature(Codec<NoFeatureConfig> config) {
		super(config);
	}

	@Override
	public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		return false;
	}

	public static boolean placeFeature(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		Direction randDirection = Direction.getRandom(rand);
		if (randDirection == Direction.UP || randDirection == Direction.DOWN) {
			randDirection = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
		if (isInRavine(world, pos, randDirection, rand) && pos.getY() >= 25) {
			for (int y = 0; y <= 8; y++) {
				BlockPos checkPos = pos.above(y);
				if (world.getBlockState(checkPos).getBlock() == Blocks.STONE) {
					int a = rand.nextInt(3) + 3;
					int b = 4;
					int c = rand.nextInt(5) + 4;
					@SuppressWarnings("unused")
					MathUtil.Equation r = (theta) -> {
						return (Math.cos(b * theta) / c + 1) * a;
					};
					for (int i = -(a / c + a); i < a / c + a; i++) {
						for (int j = -(a / c + a); j < a / c + a; j++) {
							BlockPos placingPos = pos.offset(i, 0, j);
							if (world.getBlockState(placingPos).getMaterial().isReplaceable()) {
								return false;
							}
						}
					}
					createStalactiteLayer(world, checkPos.below(), rand, a, b, c);
					return true;
				}
			}
		}
		return false;
	}

	public static void createStalactiteLayer(IWorld world, BlockPos pos, Random rand, int a, int b, int c) {
		MathUtil.Equation r = (theta) -> (Math.cos(b * theta) / c + 1) * a;
		for (int i = -(a / c + a); i < a / c + a; i++) {
			for (int j = -(a / c + a); j < a / c + a; j++) {
				double radius = r.compute(Math.atan2(j, i));
				BlockPos placingPos = pos.offset(i, 0, j);
				if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius) {
					world.setBlock(placingPos, CORAL_BLOCK_BLOCK(true), 2);
				}
			}
		}
	}

	public static boolean isInRavine(IWorld world, BlockPos pos, Direction randDirection, Random rand) {
		for (int i = 0; i < 19; i++) {
			BlockPos checkPos = pos.relative(randDirection, rand.nextInt(2) + 1).below(i);
			if (world.getBlockState(checkPos).getBlock() == Blocks.MAGMA_BLOCK || world.getBlockState(checkPos).getBlock() == Blocks.OBSIDIAN) {
				return true;
			} else if (!world.getBlockState(checkPos).getMaterial().isReplaceable()) {
				return false;
			}
		}
		return false;
	}

}
