package com.teamabnormals.upgrade_aquatic.common.blocks.coralstone;

import java.util.Random;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralWallFanBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlockCoralstoneStairs extends StairsBlock {
	@Nullable
	private Block[] growableCoralBlocks;
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public BlockCoralstoneStairs(Supplier<BlockState> state, Properties properties, @Nullable Block[] growableCoralBlocks) {
		super(state, properties);
		this.growableCoralBlocks = growableCoralBlocks;
		this.setDefaultState(this.stateContainer.getBaseState()
			.with(FACING, Direction.NORTH)
			.with(HALF, Half.BOTTOM)
			.with(SHAPE, StairsShape.STRAIGHT)
			.with(WATERLOGGED, false)
			.with(POWERED, false)
		);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED, FACING, HALF, SHAPE, WATERLOGGED);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(!worldIn.isAreaLoaded(pos, 3)) return;
		
		for(int i = 0; i < 4; i++) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			UABlocks.CORALSTONE_STAIRS_CONVERSION_MAP.forEach((input, output) -> {
			    if(input.get() == worldIn.getBlockState(blockpos).getBlock()) {
			    	BlockState newState = output.get().getDefaultState()
							.with(FACING, state.get(FACING))
							.with(HALF, state.get(HALF))
							.with(SHAPE, state.get(SHAPE))
							.with(WATERLOGGED, state.get(WATERLOGGED));
			    	worldIn.setBlockState(pos, newState);
			    }
			});
		}
		
		if(this.growableCoralBlocks != null && random.nextFloat() < 0.24F && state.get(POWERED)) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.random(random) : Direction.byIndex(random.nextInt(5) + 1);
			BlockPos growPos = pos.offset(randDirection);
			IFluidState fluidState =worldIn.getBlockState(growPos).getFluidState();
			BlockState coralState;
			
			if(randDirection.getIndex() > 1) {
				coralState = growableCoralBlocks[2].getDefaultState().with(CoralWallFanBlock.FACING, randDirection);
				if(coralState.isValidPosition(worldIn, growPos) && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
					worldIn.setBlockState(growPos, coralState, 2);
				}
			} else if(randDirection.getIndex() == 1) {
				coralState = random.nextBoolean() ? growableCoralBlocks[1].getDefaultState() : growableCoralBlocks[0].getDefaultState();
				if(coralState.isValidPosition(worldIn, growPos) && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
					worldIn.setBlockState(growPos, coralState, 2);
				}
			} else {
				coralState = growableCoralBlocks[3].getDefaultState();
				if(coralState.isValidPosition(worldIn, growPos) && this.isValidPosToGrow(worldIn, growPos, fluidState)) {
					worldIn.setBlockState(growPos, coralState, 2);
				}
			}
		}
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if(!worldIn.isRemote) {
			boolean flag = state.get(POWERED);
			if(flag != worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, state.cycle(POWERED), 2);
			}
		}
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CORALSTONE_STAIRS.get()) {
			BlockState newState = UABlocks.CORALSTONE_STAIRS.get().getDefaultState()
				.with(FACING, state.get(FACING))
				.with(HALF, state.get(HALF))
				.with(SHAPE, state.get(SHAPE))
				.with(WATERLOGGED, state.get(WATERLOGGED)
			);
			world.playSound(null, pos, SoundEvents.ENTITY_MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
			stack.damageItem(1, player, (entity) -> entity.sendBreakAnimation(hand));
			world.setBlockState(pos, newState, 2);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(POWERED, context.getWorld().isBlockPowered(context.getPos()));
	}
	
	private boolean isValidPosToGrow(World world, BlockPos pos, IFluidState fluidState) {
		return world.getBlockState(pos).getMaterial().isReplaceable() && fluidState.getLevel() >= 8 && fluidState.isTagged(FluidTags.WATER);
	}
}
