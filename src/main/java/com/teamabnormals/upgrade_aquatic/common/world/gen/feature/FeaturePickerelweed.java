package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.api.util.MathUtil;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweed;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockPickerelweedDouble;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.biome.FlowerForestBiome;
import net.minecraft.world.biome.SwampBiome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class FeaturePickerelweed extends Feature<NoFeatureConfig> {
	private static final BlockState BLUE_PICKERELWEED = UABlocks.PICKERELWEED_BLUE.getDefaultState();
	private static final BlockState PURPLE_PICKERELWEED = UABlocks.PICKERELWEED_PURPLE.getDefaultState();
	
	public FeaturePickerelweed(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory) {
		super(configFactory);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		Biome biome = worldIn.getBiome(pos);
		if (isValidBlock(worldIn, pos) && this.shouldPlace(worldIn, pos) && BLUE_PICKERELWEED.isValidPosition(worldIn, pos.down())) {
			if(biome.getCategory() == Category.RIVER || biome.getCategory() == Category.SWAMP || biome instanceof FlowerForestBiome) {
				boolean purpleGen;
				if(biome instanceof SwampBiome) {
					purpleGen = rand.nextFloat() >= 0.60D ? true : false;
				} else {
					purpleGen = rand.nextFloat() >= 0.60D ? false : true;
				}
				if(rand.nextInt() <= 0.90D) {
					this.generatePickerelweedPatch(worldIn, pos, purpleGen, rand.nextInt(8));
				}
			} else {
				boolean purpleGen;
				if(biome.getTempCategory() == TempCategory.COLD) {
					purpleGen = rand.nextFloat() >= 0.75D ? true : false;
				} else if(biome.getTempCategory() == TempCategory.MEDIUM) {
					purpleGen = rand.nextBoolean();
				} else {
					purpleGen = rand.nextFloat() >= 0.75D ? false : true;
				}
				
				if(rand.nextInt() <= 0.35D) {
					this.generatePickerelweedPatch(worldIn, pos, purpleGen, rand.nextInt(8));
				}
			}
			return true;
		}
		return false;
	}
	
	public void generatePickerelweedPatch(IWorld world, BlockPos pos, boolean purple, int randomDesign) {
		// 0 - a, 1 - b, 2 - c
		int[] patterns = new int[3];
		switch(randomDesign) {
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
		BlockPickerelweedDouble doubleplantblock = (BlockPickerelweedDouble) (!purple ? UABlocks.PICKERELWEED_TALL_BLUE : UABlocks.PICKERELWEED_TALL_PURPLE);
		MathUtil.Equation r = (theta) -> {
			return (Math.cos(patterns[1] * theta) / patterns[2] + 1) * patterns[0];
		};
		if(!world.isAirBlock(startPos.down()) && !world.isAirBlock(startPos.down(2)) && !world.isAirBlock(startPos.down(3))) {
			int repeatsDown = world.getRandom().nextInt(2) + 2;
			for(int repeats = 0; repeats < repeatsDown; repeats++) {
				pos = pos.add(0, -repeats, 0);
				for (int i = -(patterns[0] / patterns[2] + patterns[0]); i < patterns[0] / patterns[2] + patterns[0]; i++) {
					for (int j = -(patterns[0] / patterns[2] + patterns[0]); j < patterns[0] / patterns[2] + patterns[0]; j++) {
						double radius = r.compute(Math.atan2(j, i));
						BlockPos placingPos = pos.add(i, 0, j);
						if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius) {
							if(i * i + j * j > (radius - 1) * (radius - 1)) {
								IFluidState ifluidstate = world.getFluidState(placingPos);
								if(PURPLE_PICKERELWEED.isValidPosition(world, placingPos) && world.getBlockState(placingPos.up()).getMaterial().isReplaceable() && world.getRandom().nextDouble() <= 0.85D) {
									if(purple) {
										world.setBlockState(placingPos, PURPLE_PICKERELWEED.with(BlockPickerelweed.WATERLOGGED, Boolean.valueOf(ifluidstate.isTagged(FluidTags.WATER))), 2);
									} else {
										world.setBlockState(placingPos, BLUE_PICKERELWEED.with(BlockPickerelweed.WATERLOGGED, Boolean.valueOf(ifluidstate.isTagged(FluidTags.WATER))), 2);
									}
								} else if(PURPLE_PICKERELWEED.isValidPosition(world, placingPos)) {
									doubleplantblock.placeAt(world, placingPos, 2);
								}
							} else {
								if(doubleplantblock.getDefaultState().isValidPosition(world, placingPos)) {
									doubleplantblock.placeAt(world, placingPos, 2);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public boolean isValidBlock(IWorld world, BlockPos pos) {
		if(world.isAirBlock(pos) || world.getBlockState(pos).getFluidState().isTagged(FluidTags.WATER)) {
			return true;
		}
		return false;
	}
	
	public boolean shouldPlace(IWorld world, BlockPos pos) {
		if(world.getFluidState(pos.down().west()).isTagged(FluidTags.WATER) || world.getFluidState(pos.down().east()).isTagged(FluidTags.WATER) || world.getFluidState(pos.down().north()).isTagged(FluidTags.WATER) || world.getFluidState(pos.down().south()).isTagged(FluidTags.WATER)) {
			return true;
		}
		return false;
	}
	
	public static void addPickerelweed() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(FeaturePickerelweed::process);
	}
	
	private static void process(Biome biome) {
		if(biome == Biomes.FLOWER_FOREST) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.PICKERELWEED.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(90)));
		} else {
			if(biome.getCategory() != Category.OCEAN && biome.getCategory() != Category.BEACH && biome.getCategory() != Category.DESERT && biome.getCategory() != Category.ICY) {
				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.PICKERELWEED.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(28)));
			}
		}
	}
	
}
