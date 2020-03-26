package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockFloweringRush;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureFloweringRush extends Feature<NoFeatureConfig> {
	private static final BlockState FLOWERING_RUSH = UABlocks.FLOWERING_RUSH.get().getDefaultState();

	public FeatureFloweringRush(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean flag = false;

		for(int i = 0; i < 64; ++i) {
			BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(4) - rand.nextInt(4));
			if(blockpos.getY() < worldIn.getWorld().getDimension().getHeight() - 2 && worldIn.getBlockState(blockpos.down()).getBlock().isIn(BlockTags.BAMBOO_PLANTABLE_ON)) {
	            if(worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.isAirBlock(blockpos.up())) {
	            	this.placeFloweringRush(worldIn, blockpos);
	            	flag = true;
	            }
			}
		}

		return flag;
	}
	
	private void placeFloweringRush(IWorld world, BlockPos pos) {
		world.setBlockState(pos, FLOWERING_RUSH.with(BlockFloweringRush.WATERLOGGED, true), 2);
		world.setBlockState(pos.up(), FLOWERING_RUSH.with(BlockFloweringRush.HALF, DoubleBlockHalf.UPPER), 2);
	}
	
	public static void addFloweringRush() {
		ForgeRegistries.BIOMES.getValues().stream().forEach(FeatureFloweringRush::process);
	}
	
	private static void process(Biome biome) {
		if(biome.getCategory() == Category.RIVER) {
			biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(Biome.createDecoratedFeature(UAFeatures.FLOWERING_RUSH.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(15)));
		}
	}
}