package com.minecraftabnormals.upgrade_aquatic.common.world.gen.feature;

import java.util.Random;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.serialization.Codec;
import com.minecraftabnormals.abnormals_core.core.utils.MathUtils;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class PrismarineStalactiteFeature extends PrismarineCoralFeature {

	public PrismarineStalactiteFeature(Codec<NoFeatureConfig> config) {
		super(config);
	}
	
	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		return false;
	}
	
	public static boolean placeFeature(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		Direction randDirection = Direction.func_239631_a_(rand);
		if(randDirection == Direction.UP || randDirection == Direction.DOWN) {
			randDirection = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
		if(isInRavine(world, pos, randDirection, rand) && pos.getY() >= 25) {
			for(int y = 0; y <= 8; y++) {
				BlockPos checkPos = pos.up(y);
				if(world.getBlockState(checkPos).getBlock() == Blocks.STONE) {
					int a = rand.nextInt(3) + 3;
					int b = 4;
					int c = rand.nextInt(5) + 4;
					@SuppressWarnings("unused")
					MathUtils.Equation r = (theta) -> {
						return (Math.cos(b * theta) / c + 1) * a;
					};
					for (int i = -(a / c + a); i < a / c + a; i++) {
						for (int j = -(a / c + a); j < a / c + a; j++) {
							BlockPos placingPos = pos.add(i, 0, j);
							if (world.getBlockState(placingPos).getMaterial().isReplaceable()) {
								return false;
							}
						}
					}
					createStalactiteLayer(world, checkPos.down(), rand, a, b, c);
					return true;
				}
			}
		}
		return false;
	}
	
	public static void createStalactiteLayer(IWorld world, BlockPos pos, Random rand, int a, int b, int c) {
		MathUtils.Equation r = (theta) -> {
			return (Math.cos(b * theta) / c + 1) * a;
		};
		for (int i = -(a / c + a); i < a / c + a; i++) {
			for (int j = -(a / c + a); j < a / c + a; j++) {
				double radius = r.compute(Math.atan2(j, i));
				BlockPos placingPos = pos.add(i, 0, j);
				if (world.getBlockState(placingPos).getMaterial().isReplaceable() && (i * i + j * j) < radius * radius || world.getBlockState(placingPos).getBlock() == UABlocks.PRISMARINE_CORAL_WALL_FAN.get() && (i * i + j * j) < radius * radius) {
					world.setBlockState(placingPos, CORAL_BLOCK_BLOCK(true), 2);
				}
			}
		}
	}
	
	public static boolean isInRavine(IWorld world, BlockPos pos, Direction randDirection, Random rand) {
		for(int i = 0; i < 19; i++) {
			BlockPos checkPos = pos.offset(randDirection, rand.nextInt(2) + 1).down(i);
			if(world.getBlockState(checkPos).getBlock() == Blocks.MAGMA_BLOCK || world.getBlockState(checkPos).getBlock() == Blocks.OBSIDIAN) {
				return true;
			} else if(!world.getBlockState(checkPos).getMaterial().isReplaceable()) {
				return false;
			}
		}
		return false;
	}

}
