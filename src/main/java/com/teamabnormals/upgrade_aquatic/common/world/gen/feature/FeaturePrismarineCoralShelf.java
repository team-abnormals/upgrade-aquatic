package com.teamabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.teamabnormals.abnormals_core.core.utils.MathUtils;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DeadCoralWallFanBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * @author - SmellyModder(Luke Tonon)
 */
public class FeaturePrismarineCoralShelf extends FeaturePrismarineCoral {

	public FeaturePrismarineCoralShelf(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config);
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		return false;
	}
	
	public static boolean placeFeature(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		Direction direction = Direction.random(rand);
		if(direction == Direction.UP || direction == Direction.DOWN) {
			direction = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
		if(shouldPlace(world, pos, direction, rand)) {
			int a = rand.nextInt(4) + 2;
		    int c = rand.nextInt(5) + 3;
		    int b = 4;
		    boolean elders[] = {
		    	rand.nextDouble() <= 0.35D ? true : false,
		    	rand.nextDouble() <= 0.35D ? true : false,
		    	rand.nextDouble() <= 0.35D ? true : false
		    };
			if(rand.nextInt(6) < 2 && pos.getY() > 11) {
				pos = pos.offset(direction.getOpposite());
				addShelf(world, pos, rand, a, b, c, elders[0]);
				if(rand.nextBoolean()) {
					addShelf(world, pos.offset(direction.getOpposite()).up(rand.nextInt(2) + 2), rand, 3, 3, c + 1, elders[1]);
					if(rand.nextBoolean()) {
						addShelf(world, pos.offset(direction.getOpposite()).down(rand.nextInt(2) + 2), rand, 3, 4, c + 1, elders[2]);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	private static boolean shouldPlace(IWorld world, BlockPos pos, Direction randDirection, Random rand) {
		for(int i = 0; i < 13; i++) {
			BlockPos checkPos = pos.offset(randDirection, rand.nextInt(2) + 1).down(i);
			if(world.getBlockState(checkPos).getBlock() == Blocks.MAGMA_BLOCK || world.getBlockState(checkPos).getBlock() == Blocks.OBSIDIAN) {
				if(world.getBlockState(pos.offset(randDirection.getOpposite())).getBlock() == Blocks.STONE) {
					return true;
				}
			} else if(!world.getBlockState(checkPos).getMaterial().isReplaceable()) {
				return false;
			}
		}
		return false;
	}
	
	private static void addShelf(IWorld world, BlockPos pos, Random rand, int a, int b, int c, boolean isElder) {
		MathUtils.Equation r = (theta) -> {
			return (Math.cos(b * theta) / c + 1) * a;
		};
		for (int i = -(a / c + a); i < a / c + a; i++) {
			for (int j = -(a / c + a); j < a / c + a; j++) {
				double radius = r.compute(Math.atan2(j, i));
				BlockPos placingPos = pos.add(i, 0, j);
				if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.ELDER_PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius) {
					world.setBlockState(placingPos, CORAL_BLOCK_BLOCK(isElder), 2);
					if(rand.nextBoolean()) {
						boolean gen = rand.nextBoolean();
						if(gen && world.getBlockState(placingPos.up()).getMaterial().isReplaceable()) {
							world.setBlockState(placingPos.up(), CORAL_BLOCK(isElder), 2);
						} else if(!gen && world.getBlockState(placingPos.up()).getMaterial().isReplaceable()) {
							world.setBlockState(placingPos.up(), CORAL_FAN(isElder), 2);
						}
						if(world.getBlockState(placingPos.down()).getMaterial().isReplaceable()) {
							world.setBlockState(placingPos.down(), CORAL_SHOWER(isElder), 2);
						}
						for(Direction direction : Direction.Plane.HORIZONTAL) {
							if (rand.nextFloat() < 0.85F) {
				            	BlockPos blockpos1 = placingPos.offset(direction);
				            	if (world.getBlockState(blockpos1).getBlock() == Blocks.WATER) {
				            		BlockState blockstate1 = CORAL_WALL_FAN(isElder).with(DeadCoralWallFanBlock.FACING, direction);
				            		world.setBlockState(blockpos1, blockstate1, 2);
				            	}
							}
				        }
					}
				}
			}
		}
	}
	
}
