package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class ToothTrapdoorBlock extends TrapDoorBlock {

	public ToothTrapdoorBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (state.getValue(POWERED)) {
			return ActionResultType.SUCCESS;
		} else {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 2);
	         if (state.getValue(WATERLOGGED)) {
	            worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
	         }

	         this.playSound(player, worldIn, pos, state.getValue(OPEN));
			worldIn.getBlockTicks().scheduleTick(pos, this, 20);
			return ActionResultType.SUCCESS;
		}
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isClientSide) {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 2);
			if (state.getValue(WATERLOGGED)) {
				worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
			}
			this.playSound((PlayerEntity)null, worldIn, pos, state.getValue(OPEN));
		}
	}

}