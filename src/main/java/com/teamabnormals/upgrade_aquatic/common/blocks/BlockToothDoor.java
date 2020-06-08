package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlockToothDoor extends DoorBlock {

	public BlockToothDoor(Properties builder) {
		super(builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (state.get(POWERED)) {
			return ActionResultType.SUCCESS;
		} else {
			state = state.cycle(OPEN);
			worldIn.setBlockState(pos, state, 10);
			worldIn.playEvent(player, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
			return ActionResultType.SUCCESS;
		}
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
	      if (!worldIn.isRemote) {
	            state = state.cycle(OPEN);
	            worldIn.setBlockState(pos, state, 10);
				worldIn.playEvent((PlayerEntity)null, state.get(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);

	      }
	   }

	private int getCloseSound() {
		return this.material == Material.IRON ? 1011 : 1012;
	}

	private int getOpenSound() {
		return this.material == Material.IRON ? 1005 : 1006;
	}

}
