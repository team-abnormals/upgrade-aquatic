package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class PrismarineCoralShelfFeature extends PrismarineCoralFeature {

	public PrismarineCoralShelfFeature(Codec<NoneFeatureConfiguration> config) {
		super(config);
	}

	@Override
	public boolean place(WorldGenLevel worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
		return false;
	}

	public static boolean placeFeature(WorldGenLevel world, ChunkGenerator generator, Random rand, BlockPos pos, NoneFeatureConfiguration config) {
		Direction direction = Direction.getRandom(rand);
		if (direction == Direction.UP || direction == Direction.DOWN) {
			direction = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
		if (shouldPlace(world, pos, direction, rand)) {
			int a = rand.nextInt(4) + 2;
			int c = rand.nextInt(5) + 3;
			int b = 4;
			boolean[] elders = {
					rand.nextDouble() <= 0.35D,
					rand.nextDouble() <= 0.35D,
					rand.nextDouble() <= 0.35D
			};
			if (rand.nextInt(6) < 2 && pos.getY() > 11) {
				pos = pos.relative(direction.getOpposite());
				addShelf(world, pos, rand, a, b, c, elders[0]);
				if (rand.nextBoolean()) {
					addShelf(world, pos.relative(direction.getOpposite()).above(rand.nextInt(2) + 2), rand, 3, 3, c + 1, elders[1]);
					if (rand.nextBoolean()) {
						addShelf(world, pos.relative(direction.getOpposite()).below(rand.nextInt(2) + 2), rand, 3, 4, c + 1, elders[2]);
					}
				}
			}
			return true;
		}
		return false;
	}

	private static boolean shouldPlace(LevelAccessor world, BlockPos pos, Direction randDirection, Random rand) {
		for (int i = 0; i < 13; i++) {
			BlockPos checkPos = pos.relative(randDirection, rand.nextInt(2) + 1).below(i);
			if (world.getBlockState(checkPos).getBlock() == Blocks.MAGMA_BLOCK || world.getBlockState(checkPos).getBlock() == Blocks.OBSIDIAN) {
				if (world.getBlockState(pos.relative(randDirection.getOpposite())).getBlock() == Blocks.STONE) {
					return true;
				}
			} else if (!world.getBlockState(checkPos).getMaterial().isReplaceable()) {
				return false;
			}
		}
		return false;
	}

	private static void addShelf(LevelAccessor world, BlockPos pos, Random rand, int a, int b, int c, boolean isElder) {
		MathUtil.Equation r = (theta) -> {
			return (Math.cos(b * theta) / c + 1) * a;
		};
		for (int i = -(a / c + a); i < a / c + a; i++) {
			for (int j = -(a / c + a); j < a / c + a; j++) {
				double radius = r.compute(Math.atan2(j, i));
				BlockPos placingPos = pos.offset(i, 0, j);
				if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius) {
					world.setBlock(placingPos, CORAL_BLOCK_BLOCK(isElder), 2);
					if (rand.nextBoolean()) {
						boolean gen = rand.nextBoolean();
						if (gen && world.getBlockState(placingPos.above()).getMaterial().isReplaceable()) {
							world.setBlock(placingPos.above(), CORAL_BLOCK(isElder), 2);
						} else if (!gen && world.getBlockState(placingPos.above()).getMaterial().isReplaceable()) {
							world.setBlock(placingPos.above(), CORAL_FAN(isElder), 2);
						}
						if (world.getBlockState(placingPos.below()).getMaterial().isReplaceable()) {
							world.setBlock(placingPos.below(), CORAL_SHOWER(isElder), 2);
						}
						for (Direction direction : Direction.Plane.HORIZONTAL) {
							if (rand.nextFloat() < 0.85F) {
								BlockPos blockpos1 = placingPos.relative(direction);
								if (world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
									BlockState blockstate1 = CORAL_WALL_FAN(isElder).setValue(BaseCoralWallFanBlock.FACING, direction);
									world.setBlock(blockpos1, blockstate1, 2);
								}
							}
						}
					}
				}
			}
		}
	}

}
