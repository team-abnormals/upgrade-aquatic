package com.minecraftabnormals.upgrade_aquatic.common.blocks.coralstone;

import java.util.Random;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.minecraftabnormals.abnormals_core.core.utils.BlockUtils;

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

public class CoralstoneWallBlock extends WallBlock {

	public CoralstoneWallBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(!worldIn.isAreaLoaded(pos, 3)) return;
		
		for(int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			UABlocks.CORALSTONE_WALL_CONVERSION_MAP.forEach((input, output) -> {
			    if(input.get() == worldIn.getBlockState(blockpos).getBlock()) {
					worldIn.setBlockState(pos, BlockUtils.transferAllBlockStates(state, output.get().getDefaultState()), 2);
			    }
			});
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_WALL.get()) {
			world.playSound(null, pos, SoundEvents.ENTITY_MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
			stack.damageItem(1, player, (entity) -> entity.sendBreakAnimation(hand));
			world.setBlockState(pos, BlockUtils.transferAllBlockStates(state, UABlocks.CORALSTONE_WALL.get().getDefaultState()), 2);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

}
