package com.teamabnormals.upgrade_aquatic.common.block.coralstone;

import com.teamabnormals.blueprint.core.util.BlockUtil;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

@SuppressWarnings("deprecation")
public class CoralstoneWallBlock extends WallBlock {

	public CoralstoneWallBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;

		if (state.getBlock() != UABlocks.DEAD_CORALSTONE_WALL.get()) {
			CoralstoneBlock.tickConversion(UABlocks.CORALSTONE_WALL_CONVERSION_MAP, state, worldIn, pos, random);
		}

		for (int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			UABlocks.CORALSTONE_WALL_CONVERSION_MAP.forEach((input, output) -> {
				if (input.get() == worldIn.getBlockState(blockpos).getBlock()) {
					worldIn.setBlock(pos, BlockUtil.transferAllBlockStates(state, output.get().defaultBlockState()), 2);
				}
			});
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_WALL.get()) {
			world.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundSource.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
			world.setBlock(pos, BlockUtil.transferAllBlockStates(state, UABlocks.CORALSTONE_WALL.get().defaultBlockState()), 2);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

}
