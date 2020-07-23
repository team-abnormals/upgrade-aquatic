package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Consumer;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.FloweringRushBlock;
import com.minecraftabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.core.library.api.IAddToBiomes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

public class FloweringRushFeature extends Feature<NoFeatureConfig> implements IAddToBiomes {
	private static final BlockState FLOWERING_RUSH = UABlocks.FLOWERING_RUSH.get().getDefaultState();

	public FloweringRushFeature(Codec<NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		boolean flag = false;

		for(int i = 0; i < 64; ++i) {
			BlockPos blockpos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(4) - rand.nextInt(4));
			if(blockpos.getY() < worldIn.getWorld().getHeight() - 2 && worldIn.getBlockState(blockpos.down()).getBlock().isIn(BlockTags.BAMBOO_PLANTABLE_ON)) {
	            if(worldIn.getBlockState(blockpos).getBlock() == Blocks.WATER && worldIn.isAirBlock(blockpos.up())) {
	            	this.placeFloweringRush(worldIn, blockpos);
	            	flag = true;
	            }
			}
		}

		return flag;
	}
	
	private void placeFloweringRush(IWorld world, BlockPos pos) {
		world.setBlockState(pos, FLOWERING_RUSH.with(FloweringRushBlock.WATERLOGGED, true), 2);
		world.setBlockState(pos.up(), FLOWERING_RUSH.with(FloweringRushBlock.HALF, DoubleBlockHalf.UPPER), 2);
	}

	@Override
	public Consumer<Biome> processBiomeAddition() {
		return biome -> {
			if(biome.getCategory() == Category.RIVER) {
				biome.getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(UAFeatures.FLOWERING_RUSH.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(15))));
			}
		};
	}
}