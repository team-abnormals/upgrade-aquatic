package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class PrismarineCoralFeature extends Feature<NoneFeatureConfiguration> {

	public PrismarineCoralFeature(Codec<NoneFeatureConfiguration> configFactoryIn) {
		super(configFactoryIn);
	}

	protected static BlockState coralBlock(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_BLOCK.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_BLOCK.get().defaultBlockState();
	}

	protected static BlockState coral(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL.get().defaultBlockState();
	}

	protected static BlockState coralFan(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_FAN.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_FAN.get().defaultBlockState();
	}

	protected static BlockState coralWallFan(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_WALL_FAN.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get().defaultBlockState();
	}

	protected static BlockState coralShower(boolean elder) {
		return !elder ? UABlocks.PRISMARINE_CORAL_SHOWER.get().defaultBlockState() : UABlocks.ELDER_PRISMARINE_CORAL_SHOWER.get().defaultBlockState();
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		if (shouldPlace(world, pos)) {
			int a = rand.nextInt(4) + 2;
			int c = rand.nextInt(5) + 3;
			int b = 4;
			if (pos.getY() > 11) {
				addShelf(world, pos, rand, a, b, c, rand.nextDouble() <= 0.35D);
				if (rand.nextBoolean()) {
					BlockPos pairPos = pos.relative(Direction.getRandom(rand)).above(rand.nextInt(2) + 2);
					if (isInWaterNextToStone(world, pairPos)) addShelf(world, pairPos, rand, 3, 3, c + 1, rand.nextDouble() <= 0.35D);
					if (rand.nextBoolean()) {
						pairPos = pos.relative(Direction.getRandom(rand)).below(rand.nextInt(2) + 2);
						if (isInWaterNextToStone(world, pairPos)) addShelf(world, pairPos, rand, 3, 4, c + 1, rand.nextDouble() <= 0.35D);
					}
				}
			}
			return true;
		}
		return false;
	}

	private static boolean isInWaterNextToStone(LevelAccessor level, BlockPos pos) {
		if (!level.isWaterAt(pos)) return false;
		BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
		for (Direction direction : Direction.Plane.HORIZONTAL) {
			mutableBlockPos.setWithOffset(pos, direction);
			if (level.getBlockState(mutableBlockPos).is(BlockTags.BASE_STONE_OVERWORLD)) {
				return true;
			}
		}
		return false;
	}

	private static boolean shouldPlace(LevelAccessor level, BlockPos pos) {
		if (!isInWaterNextToStone(level, pos)) return false;
		for (int i = 0; i < 16; i++) {
			BlockPos checkPos = pos.below(i);
			BlockState state = level.getBlockState(checkPos);
			if (state.getBlock() == Blocks.MAGMA_BLOCK || state.getBlock() == Blocks.OBSIDIAN) {
				return true;
			}
		}
		return false;
	}

	private static void addShelf(LevelAccessor world, BlockPos pos, RandomSource rand, int a, int b, int c, boolean isElder) {
		MathUtil.Equation r = (theta) -> (Math.cos(b * theta) / c + 1) * a;
		for (int i = -(a / c + a); i < a / c + a; i++) {
			for (int j = -(a / c + a); j < a / c + a; j++) {
				double radius = r.compute(Math.atan2(j, i));
				BlockPos placingPos = pos.offset(i, 0, j);
				if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius) {
					world.setBlock(placingPos, coralBlock(isElder), 2);
					if (rand.nextBoolean()) {
						BlockPos above = placingPos.above();
						if (world.isWaterAt(above)) {
							world.setBlock(above, rand.nextBoolean() ? coral(isElder) : coralFan(isElder), 2);
						}
						BlockPos below = placingPos.below();
						if (world.isWaterAt(below)) {
							world.setBlock(below, coralShower(isElder), 2);
						}
						for (Direction direction : Direction.Plane.HORIZONTAL) {
							if (rand.nextFloat() < 0.85F) {
								BlockPos blockpos1 = placingPos.relative(direction);
								if (world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
									BlockState blockstate1 = coralWallFan(isElder).setValue(BaseCoralWallFanBlock.FACING, direction);
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
