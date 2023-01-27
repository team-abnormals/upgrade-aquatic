package com.teamabnormals.upgrade_aquatic.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class ToothDoorBlock extends DoorBlock {

	public ToothDoorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!state.getValue(POWERED)) {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 10);
			worldIn.levelEvent(player, state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
			worldIn.scheduleTick(pos, this, 20);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isClientSide) {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 10);
			worldIn.levelEvent(null, state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
		}
	}

	private int getCloseSound() {
		return this.material == Material.METAL ? 1011 : 1012;
	}

	private int getOpenSound() {
		return this.material == Material.METAL ? 1005 : 1006;
	}

}