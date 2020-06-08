package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

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

public class BlockToothTrapdoor extends TrapDoorBlock {

	public BlockToothTrapdoor(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (state.get(POWERED)) {
			return ActionResultType.SUCCESS;
		} else {
			state = state.cycle(OPEN);
			worldIn.setBlockState(pos, state, 2);
	         if (state.get(WATERLOGGED)) {
	            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	         }

	         this.playSound(player, worldIn, pos, state.get(OPEN));
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
			return ActionResultType.SUCCESS;
		}
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
	      if (!worldIn.isRemote) {
	            state = state.cycle(OPEN);
	            worldIn.setBlockState(pos, state, 2);
	            if (state.get(WATERLOGGED)) {
	               worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	            }

	            this.playSound((PlayerEntity)null, worldIn, pos, state.get(OPEN));

	      }
	   }

}
