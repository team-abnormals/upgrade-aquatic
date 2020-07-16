package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Consumer;

import com.mojang.serialization.Codec;
import com.teamabnormals.abnormals_core.core.library.api.IAddToBiomes;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockMulberryVine;
import com.teamabnormals.upgrade_aquatic.common.world.gen.UAFeatures;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.IPlantable;

@SuppressWarnings("deprecation")
public class FeatureRiverTree extends Feature<BaseTreeFeatureConfig> implements IAddToBiomes {
	public static final BaseTreeFeatureConfig RIVER_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(UABlocks.RIVER_LOG.get().getDefaultState()), new SimpleBlockStateProvider(UABlocks.RIVER_LEAVES.get().getDefaultState()), null, null, null)).build();

	public FeatureRiverTree(Codec<BaseTreeFeatureConfig> config) {
		super(config);
	}

	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
		int height = 3 + rand.nextInt(2) + rand.nextInt(2);
		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + height + 1 <= worldIn.getHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; ++j) {
				int k = 1;
				if (j == position.getY())
					k = 0;
				if (j >= position.getY() + 1 + height - 2)
					k = 2;
				BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < worldIn.getHeight()) {
							if (!isAirOrLeaves(worldIn, blockpos$mutableblockpos.setPos(l, j, i1)))
								flag = false;
						} else
							flag = false;
					}
				}
			}

			if (!flag) {
				return false;
			} else if (isValidGround(worldIn, position.down()) && position.getY() < worldIn.getHeight() - height - 1) {
				setDirtAt(worldIn, position.down());

				int logX = position.getX();
				int logZ = position.getZ();
				int logY = position.getY();

				for (int k1 = 0; k1 < height; ++k1) {
					logY = position.getY() + k1;
					BlockPos blockpos = new BlockPos(logX, logY, logZ);
					if (isAirOrLeaves(worldIn, blockpos)) {
						this.placeLogAt(worldIn, blockpos, rand, config);
					}
				}

				logY = position.getY() + height - rand.nextInt(2);

				position = new BlockPos(logX, logY, logZ);

				this.createLeaves(worldIn, position.down(), rand, false, config);
				this.createLeaves(worldIn, position, rand, false, config);
				this.createLeaves(worldIn, position.up(), rand, true, config);
				this.placeLeafAt(worldIn, position.up(), rand, config);
				this.placeLeafAt(worldIn, position.up(2), rand, config);

				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private void createLeaves(IWorldGenerationReader worldIn, BlockPos newPos, Random rand, boolean small, BaseTreeFeatureConfig config) {
		int leafSize = 1;
		for (int k3 = -leafSize; k3 <= leafSize; ++k3) {
			for (int j4 = -leafSize; j4 <= leafSize; ++j4) {
				if (small) {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						if (rand.nextInt(3) != 0)
							this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, null);
					}
				} else {
					if ((Math.abs(k3) != leafSize || Math.abs(j4) != leafSize)) {
						this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
					} else if (rand.nextInt(4) == 0) {
						this.placeLeafAt(worldIn, newPos.add(k3, 0, j4), rand, config);
					}
				}
			}
		}
	}

	private void placeLogAt(IWorldWriter worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
		this.setLogState(worldIn, pos, UABlocks.RIVER_LOG.get().getDefaultState());
	}

	private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config) {
		if (isAirOrLeaves(world, pos)) {
			this.setLogState(world, pos, UABlocks.RIVER_LEAVES.get().getDefaultState().with(LeavesBlock.DISTANCE, 1));
		}
		if (isAir(world, pos.down()) && rand.nextInt(3) == 0 && rand.nextBoolean()) {
			BlockState state = UABlocks.MULBERRY_VINE.get().getDefaultState().with(BlockMulberryVine.AGE, 4).with(BlockMulberryVine.DOUBLE, rand.nextBoolean());
			if (state.isValidPosition((IWorldReader) world, pos.down()))
				this.setLogState(world, pos.down(), state);
		}
	}

	protected final void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
		worldIn.setBlockState(pos, state, 18);
	}

	public static boolean isAir(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		if (!(worldIn instanceof net.minecraft.world.IBlockReader)) // FORGE: Redirect to state method when possible
			return worldIn.hasBlockState(pos, BlockState::isAir);
		else
			return worldIn.hasBlockState(pos, state -> state.isAir((net.minecraft.world.IBlockReader) worldIn, pos));
	}

	public static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		if (worldIn instanceof net.minecraft.world.IWorldReader) // FORGE: Redirect to state method when possible
			return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((net.minecraft.world.IWorldReader) worldIn, pos));
		return worldIn.hasBlockState(pos, (state) -> {
			return state.isAir() || state.isIn(BlockTags.LEAVES);
		});
	}

	public static void setDirtAt(IWorld worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND) {
			worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 18);
		}
	}
	
	public static boolean isValidGround(IWorld world, BlockPos pos) {
		return world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (IPlantable)UABlocks.RIVER_SAPLING.get());
	}

	@Override
	public Consumer<Biome> processBiomeAddition() {
		return biome -> {
			if (biome.getCategory() == Category.RIVER) {
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, UAFeatures.RIVER_TREE.get().withConfiguration(RIVER_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(1, 0.75F, 2))));
			}
		};
	}
}
