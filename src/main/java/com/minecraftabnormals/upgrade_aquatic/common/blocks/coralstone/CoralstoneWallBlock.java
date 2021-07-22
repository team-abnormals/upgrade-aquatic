package com.minecraftabnormals.upgrade_aquatic.common.blocks.coralstone;

import com.minecraftabnormals.abnormals_core.core.util.BlockUtil;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

@SuppressWarnings("deprecation")
public class CoralstoneWallBlock extends WallBlock {

	public CoralstoneWallBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
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
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_WALL.get()) {
			world.playSound(null, pos, SoundEvents.MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
			stack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(hand));
			world.setBlock(pos, BlockUtil.transferAllBlockStates(state, UABlocks.CORALSTONE_WALL.get().defaultBlockState()), 2);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

}
