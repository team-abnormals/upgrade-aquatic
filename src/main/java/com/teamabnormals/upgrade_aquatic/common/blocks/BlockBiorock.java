package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBiorock extends Block {
	private boolean chiseled;
	public static final Map<Block, Block> BIOROCK_CONVERSION_MAP(boolean chiseled) {
		return chiseled ? Maps.newHashMap() : Maps.newHashMap();
	}

	public BlockBiorock(Properties properties, boolean chiseled) {
		super(properties);
		this.chiseled = chiseled;
		this.registerMap();
	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;
		
		for(int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			if(BIOROCK_CONVERSION_MAP(this.chiseled).containsKey(worldIn.getBlockState(blockpos).getBlock())) {
				worldIn.setBlockState(pos, BIOROCK_CONVERSION_MAP(this.chiseled).get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState());
			}
		}
	}
	
	private void registerMap() {
		BIOROCK_CONVERSION_MAP(false).put(Blocks.BUBBLE_CORAL_BLOCK, UABlocks.BUBBLE_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(Blocks.HORN_CORAL_BLOCK, UABlocks.HORN_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(Blocks.TUBE_CORAL_BLOCK, UABlocks.TUBE_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(Blocks.BRAIN_CORAL_BLOCK, UABlocks.BRAIN_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(Blocks.FIRE_CORAL_BLOCK, UABlocks.FIRE_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.ACAN_CORAL_BLOCK, UABlocks.ACAN_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.FINGER_CORAL_BLOCK, UABlocks.FINGER_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.STAR_CORAL_BLOCK, UABlocks.STAR_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.MOSS_CORAL_BLOCK, UABlocks.MOSS_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.PETAL_CORAL_BLOCK, UABlocks.PETAL_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.BRANCH_CORAL_BLOCK, UABlocks.BRANCH_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.ROCK_CORAL_BLOCK, UABlocks.ROCK_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.PILLOW_CORAL_BLOCK, UABlocks.PILLOW_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.SILK_CORAL_BLOCK, UABlocks.SILK_BIOROCK);
		BIOROCK_CONVERSION_MAP(false).put(UABlocks.PRISMARINE_CORAL_BLOCK, UABlocks.PRISMARINE_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(Blocks.BUBBLE_CORAL_BLOCK, UABlocks.BUBBLE_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(Blocks.HORN_CORAL_BLOCK, UABlocks.HORN_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(Blocks.TUBE_CORAL_BLOCK, UABlocks.TUBE_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(Blocks.BRAIN_CORAL_BLOCK, UABlocks.BRAIN_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(Blocks.FIRE_CORAL_BLOCK, UABlocks.FIRE_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.ACAN_CORAL_BLOCK, UABlocks.ACAN_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.FINGER_CORAL_BLOCK, UABlocks.FINGER_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.STAR_CORAL_BLOCK, UABlocks.STAR_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.MOSS_CORAL_BLOCK, UABlocks.MOSS_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.PETAL_CORAL_BLOCK, UABlocks.PETAL_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.BRANCH_CORAL_BLOCK, UABlocks.BRANCH_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.ROCK_CORAL_BLOCK, UABlocks.ROCK_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.PILLOW_CORAL_BLOCK, UABlocks.PILLOW_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.SILK_CORAL_BLOCK, UABlocks.SILK_CHISELED_BIOROCK);
		BIOROCK_CONVERSION_MAP(true).put(UABlocks.PRISMARINE_CORAL_BLOCK, UABlocks.PRISMARINE_CHISELED_BIOROCK);
	}
}
