package com.teamabnormals.upgrade_aquatic.common.levelgen.feature;

import com.mojang.serialization.Codec;
import com.teamabnormals.blueprint.core.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.common.block.PickerelweedDoublePlantBlock;
import com.teamabnormals.upgrade_aquatic.common.block.PickerelweedPlantBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.Tags;

import java.util.function.Supplier;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class PickerelweedFeature extends Feature<NoneFeatureConfiguration> {
	private static final Supplier<BlockState> BLUE_PICKERELWEED = () -> UABlocks.BLUE_PICKERELWEED.get().defaultBlockState();
	private static final Supplier<BlockState> PURPLE_PICKERELWEED = () -> UABlocks.PURPLE_PICKERELWEED.get().defaultBlockState();

	public PickerelweedFeature(Codec<NoneFeatureConfiguration> configFactory) {
		super(configFactory);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel worldIn = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		Holder<Biome> biome = worldIn.getBiome(pos);
		if (isValidBlock(worldIn, pos) && this.shouldPlace(worldIn, pos) && BLUE_PICKERELWEED.get().canSurvive(worldIn, pos.below())) {
			if (biome.is(BiomeTags.IS_RIVER) || biome.is(Tags.Biomes.IS_SWAMP) || biome.is(Biomes.FLOWER_FOREST)) {
				boolean purpleGen;
				if (biome.is(Tags.Biomes.IS_SWAMP)) {
					purpleGen = rand.nextFloat() >= 0.60D;
				} else {
					purpleGen = !(rand.nextFloat() >= 0.60D);
				}
				if (rand.nextDouble() <= 0.90D) {
					this.generatePickerelweedPatch(worldIn, pos, purpleGen, rand.nextInt(8));
				}
			} else {
				boolean purpleGen;
				if (biome.value().getBaseTemperature() < 0.2D) {
					purpleGen = rand.nextFloat() >= 0.75D;
				} else if (biome.value().getBaseTemperature() < 1.0D) {
					purpleGen = rand.nextBoolean();
				} else {
					purpleGen = !(rand.nextFloat() >= 0.75D);
				}

				if (rand.nextDouble() <= 0.35D) {
					this.generatePickerelweedPatch(worldIn, pos, purpleGen, rand.nextInt(8));
				}
			}
			return true;
		}
		return false;
	}

	public void generatePickerelweedPatch(LevelAccessor world, BlockPos pos, boolean purple, int randomDesign) {
		// 0 - a, 1 - b, 2 - c
		int[] patterns = new int[3];
		switch (randomDesign) {
			default:
			case 0:
				patterns[0] = 4;
				patterns[1] = 4;
				patterns[2] = 9;
			case 1:
				patterns[0] = 3;
				patterns[1] = 4;
				patterns[2] = 9;
			case 2:
				patterns[0] = 3;
				patterns[1] = 4;
				patterns[2] = 12;
			case 3:
				patterns[0] = 3;
				patterns[1] = 13;
				patterns[2] = 12;
			case 4:
				patterns[0] = 4;
				patterns[1] = 3;
				patterns[2] = 6;
			case 5:
				patterns[0] = 3;
				patterns[1] = 4;
				patterns[2] = 6;
			case 6:
				patterns[0] = 5;
				patterns[1] = 4;
				patterns[2] = 6;
			case 7:
				patterns[0] = 5;
				patterns[1] = 4;
				patterns[2] = 6;
		}
		BlockPos startPos = pos;
		PickerelweedDoublePlantBlock doubleplantblock = (PickerelweedDoublePlantBlock) (!purple ? UABlocks.TALL_BLUE_PICKERELWEED.get() : UABlocks.TALL_PURPLE_PICKERELWEED.get());
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
						if (world.getBlockState(placingPos).canBeReplaced() && (i * i + j * j) < radius * radius) {
							if (i * i + j * j > (radius - 1) * (radius - 1)) {
								FluidState ifluidstate = world.getFluidState(placingPos);
								if (PURPLE_PICKERELWEED.get().canSurvive(world, placingPos) && world.getBlockState(placingPos.above()).canBeReplaced() && world.getRandom().nextDouble() <= 0.85D) {
									if (purple) {
										world.setBlock(placingPos, PURPLE_PICKERELWEED.get().setValue(PickerelweedPlantBlock.WATERLOGGED, ifluidstate.is(FluidTags.WATER)), 2);
									} else {
										world.setBlock(placingPos, BLUE_PICKERELWEED.get().setValue(PickerelweedPlantBlock.WATERLOGGED, ifluidstate.is(FluidTags.WATER)), 2);
									}
								} else if (PURPLE_PICKERELWEED.get().canSurvive(world, placingPos)) {
									doubleplantblock.placeAt(world, placingPos, 2);
								}
							} else {
								if (doubleplantblock.defaultBlockState().canSurvive(world, placingPos)) {
									doubleplantblock.placeAt(world, placingPos, 2);
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean isValidBlock(LevelAccessor world, BlockPos pos) {
		return world.isEmptyBlock(pos) || world.getBlockState(pos).getFluidState().is(FluidTags.WATER);
	}

	public boolean shouldPlace(LevelAccessor world, BlockPos pos) {
		return world.getFluidState(pos.below().west()).is(FluidTags.WATER) || world.getFluidState(pos.below().east()).is(FluidTags.WATER) || world.getFluidState(pos.below().north()).is(FluidTags.WATER) || world.getFluidState(pos.below().south()).is(FluidTags.WATER);
	}

}
