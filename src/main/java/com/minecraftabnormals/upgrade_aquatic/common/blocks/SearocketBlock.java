package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import net.minecraft.block.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class SearocketBlock extends FlowerBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.WITHER_ROSE);

	public SearocketBlock(Effect effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}

	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.below();
		return this.mayPlaceOn(worldIn.getBlockState(blockpos), worldIn, blockpos);
	}

	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block.is(BlockTags.BAMBOO_PLANTABLE_ON);
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader arg0, BlockPos arg1, BlockState arg2, boolean arg3) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World arg0, Random arg1, BlockPos arg2, BlockState arg3) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		popResource(world, pos, new ItemStack(this));
	}


	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}
}