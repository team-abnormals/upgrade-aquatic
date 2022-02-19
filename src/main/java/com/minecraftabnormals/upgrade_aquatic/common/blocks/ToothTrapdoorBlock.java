package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;

import java.util.Random;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ToothTrapdoorBlock extends TrapDoorBlock {

	public ToothTrapdoorBlock(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (state.getValue(POWERED)) {
			return InteractionResult.SUCCESS;
		} else {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 2);
			if (state.getValue(WATERLOGGED)) {
				worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}

			this.playSound(player, worldIn, pos, state.getValue(OPEN));
			worldIn.scheduleTick(pos, this, 20);
			return InteractionResult.SUCCESS;
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
		if (!worldIn.isClientSide) {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 2);
			if (state.getValue(WATERLOGGED)) {
				worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}
			this.playSound(null, worldIn, pos, state.getValue(OPEN));
		}
	}

}