package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.abnormals_core.core.library.api.IAddToBiomes;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockMulberryVine;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class FeatureRiverTree extends TreeFeature implements IAddToBiomes {
	public static final TreeFeatureConfig RIVER_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(UABlocks.RIVER_LOG.get().getDefaultState()), new SimpleBlockStateProvider(UABlocks.RIVER_LEAVES.get().getDefaultState()), new BlobFoliagePlacer(0, 0))).setSapling((net.minecraftforge.common.IPlantable)UABlocks.RIVER_SAPLING.get()).build();
	
	public FeatureRiverTree(Function<Dynamic<?>, ? extends TreeFeatureConfig> config) {
		super(config);
	}

	public boolean func_225557_a_(IWorldGenerationReader worldIn, Random rand, BlockPos position, Set<BlockPos> logsPlaced, Set<BlockPos> leavesPlaced, MutableBoundingBox boundsIn, TreeFeatureConfig config) {
		
		int height = 3 + rand.nextInt(2) + rand.nextInt(2);
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getMaxHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY()) k = 0;
				if (j >= position.getY() + 1 + height - 2) k = 2;
				BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getMaxHeight()) {
							if (!canBeReplacedByLogs(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) flag = false;
						} else flag = false;
					}
				}
			}

			if (!flag) {
				return false;
			} else if (isSoil(worldIn, position.down(), config.getSapling()) && position.getY() < worldIn.getMaxHeight()) {
				this.setDirtAt(worldIn, position.down(), position);
				
				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (isAirOrLeaves(worldIn, blockpos)) {
						this.placeLogAt(logsPlaced, worldIn, blockpos, boundsIn);
					}
				}
				
				logY = position.getY() + height - rand.nextInt(2);
				
				position = new BlockPos(logX, logY, logZ);

				this.createLeaves(leavesPlaced, worldIn, position.down(), boundsIn, rand, false);
				this.createLeaves(leavesPlaced, worldIn, position, boundsIn, rand, false);
				this.createLeaves(leavesPlaced, worldIn, position.up(), boundsIn, rand, true);
				this.placeLeafAt(leavesPlaced, worldIn, position.up(), boundsIn, rand);
				this.placeLeafAt(leavesPlaced, worldIn, position.up(2), boundsIn, rand);
				
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void createLeaves(Set<BlockPos> leavesPlaced, IWorldGenerationReader worldIn, BlockPos newPos, MutableBoundingBox boundsIn, Random rand, boolean small) {
		int leafSize = 1;
		for(int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for(int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (small) {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						if (rand.nextInt(3) != 0) this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn, rand);
					}
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn, rand);
					} else if (rand.nextInt(4) == 0) { 
						this.placeLeafAt(leavesPlaced, worldIn, newPos.add(k3, 0, j4), boundsIn, rand); 
					}
				}
			}
		}
	}

	private void placeLogAt(Set<BlockPos> changedBlocks, IWorldWriter worldIn, BlockPos pos, MutableBoundingBox boundsIn) {
		this.setLogState(changedBlocks, worldIn, pos, UABlocks.RIVER_LOG.get().getDefaultState(), boundsIn);
	}

	private void placeLeafAt(Set<BlockPos> changedBlocks, IWorldGenerationReader world, BlockPos pos, MutableBoundingBox boundsIn, Random rand) {
		if (isAirOrLeaves(world, pos)) { 
			this.setLogState(changedBlocks, world, pos, UABlocks.RIVER_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1), boundsIn);
		}
		if (isAir(world, pos.down()) && rand.nextInt(3) == 0 && rand.nextBoolean()) {
			BlockState state = UABlocks.MULBERRY_VINE.get().getDefaultState().with(BlockMulberryVine.AGE, 4).with(BlockMulberryVine.DOUBLE, rand.nextBoolean());
			if (state.isValidPosition((IWorldReader) world, pos.down())) this.setLogState(changedBlocks, world, pos.down(), state, boundsIn);
		}
	}

	protected final void setLogState(Set<BlockPos> changedBlocks, IWorldWriter worldIn, BlockPos pos, BlockState state, MutableBoundingBox boundsIn) {
		worldIn.setBlockState(pos, state, 18);
		boundsIn.expandTo(new MutableBoundingBox(pos, pos));
		if (BlockTags.LOGS.contains(state.getBlock())) {
			changedBlocks.add(pos.toImmutable());
		}
	}
	
	@Override
	public Consumer<Biome> processBiomeAddition() {
		return biome -> {
			if(biome.getCategory() == Category.RIVER) {
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UAFeatures.RIVER_TREE.get().withConfiguration(RIVER_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.75F, 2))));

			}
		};
	}
}
