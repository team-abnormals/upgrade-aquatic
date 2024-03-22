package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks.UAProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ToothDoorBlock extends DoorBlock {

	public ToothDoorBlock(Properties builder) {
		super(builder, UAProperties.TOOTH_BLOCK_SET);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
		if (!state.getValue(POWERED)) {
			state = state.cycle(OPEN);
			level.setBlock(pos, state, 10);
			this.playSound(player, level, pos, state.getValue(OPEN));
			level.gameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
			level.scheduleTick(pos, this, 20);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!level.isClientSide) {
			state = state.cycle(OPEN);
			level.setBlock(pos, state, 10);
			this.playSound(null, level, pos, state.getValue(OPEN));
			level.gameEvent(null, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
		}
	}
}