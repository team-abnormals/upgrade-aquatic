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
	private static final Supplier<BlockState> PICKERELWEED = () -> UABlocks.PICKERELWEED.get().defaultBlockState();

	public PickerelweedFeature(Codec<NoneFeatureConfiguration> configFactory) {
		super(configFactory);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel worldIn = context.level();
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		Holder<Biome> biome = worldIn.getBiome(pos);
		if (isValidBlock(worldIn, pos) && this.shouldPlace(worldIn, pos) && PICKERELWEED.get().canSurvive(worldIn, pos.below())) {
			if (biome.is(BiomeTags.IS_RIVER) || biome.is(Tags.Biomes.IS_SWAMP) || biome.is(Biomes.FLOWER_FOREST)) {
				if (rand.nextDouble() <= 0.90D) {
					this.generatePickerelweedPatch(worldIn, pos, rand.nextInt(8));
				}
			} else {
				if (rand.nextDouble() <= 0.35D) {
					this.generatePickerelweedPatch(worldIn, pos, rand.nextInt(8));
				}
			}
			return true;
		}
		return false;
	}

	public void generatePickerelweedPatch(LevelAccessor world, BlockPos pos, int randomDesign) {
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
		PickerelweedDoublePlantBlock doubleplantblock = (PickerelweedDoublePlantBlock) UABlocks.TALL_PICKERELWEED.get();
		MathUtil.Equation r = (theta) -> (Math.cos(patterns[1] * theta) / patterns[2] + 1) * patterns[0];
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
								if (PICKERELWEED.get().canSurvive(world, placingPos) && world.getBlockState(placingPos.above()).canBeReplaced() && world.getRandom().nextDouble() <= 0.85D) {
									world.setBlock(placingPos, PICKERELWEED.get().setValue(PickerelweedPlantBlock.WATERLOGGED, ifluidstate.is(FluidTags.WATER)), 2);
								} else if (PICKERELWEED.get().canSurvive(world, placingPos)) {
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
