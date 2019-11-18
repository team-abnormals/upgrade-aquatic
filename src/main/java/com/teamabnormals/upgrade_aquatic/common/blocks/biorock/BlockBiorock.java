package com.teamabnormals.upgrade_aquatic.common.blocks.biorock;

import java.util.Random;

import javax.annotation.Nullable;

import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralWallFanBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockBiorock extends Block {
	@Nullable
	private Block[] growableCoralBlocks;
	
	private boolean chiseled;
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public BlockBiorock(Properties properties, boolean chiseled) {
		this(properties, chiseled, null);
	}
	
	public BlockBiorock(Properties properties, boolean chiseled, @Nullable Block[] growableCoralBlocks) {
		super(properties);
		this.chiseled = chiseled;
		this.growableCoralBlocks = growableCoralBlocks;
		this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, false));
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(world.isRemote) {
			return true;
		} else {
			ItemStack stack = player.getHeldItem(hand);
			BlockState newState = this.chiseled ? UABlocks.CHISELED_BIOROCK.getDefaultState() : UABlocks.BIOROCK.getDefaultState();
			if(stack.getItem() == Items.SHEARS && state.getBlock() != UABlocks.CHISELED_BIOROCK && state.getBlock() != UABlocks.BIOROCK) {
				world.playSound(null, pos, SoundEvents.ENTITY_MOOSHROOM_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.8F);
				stack.damageItem(1, player, (entity) -> entity.sendBreakAnimation(hand));
				world.setBlockState(pos, newState, 2);
				return true;
			}
			return false;
		}
	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if(!worldIn.isAreaLoaded(pos, 3)) return;
		Block block = state.getBlock();
		
		if(this.growableCoralBlocks == null && block != UABlocks.DEAD_BIOROCK && block != UABlocks.DEAD_CHISELED_BIOROCK) {
			for(int i = 0; i < 4; i++) {
				BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if(chiseled) {
					if(UABlocks.CHISELED_BIOROCK_CONVERSION_MAP.containsKey(worldIn.getBlockState(blockpos).getBlock())) {
						worldIn.setBlockState(pos, UABlocks.CHISELED_BIOROCK_CONVERSION_MAP.get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState());
					}
				} else {
					if(UABlocks.BIOROCK_CONVERSION_MAP.containsKey(worldIn.getBlockState(blockpos).getBlock())) {
						worldIn.setBlockState(pos, UABlocks.BIOROCK_CONVERSION_MAP.get(worldIn.getBlockState(blockpos).getBlock()).getDefaultState());
					}
				}
			}
		}
		
		if(this.growableCoralBlocks != null && random.nextFloat() < 0.12F) {
			Direction randDirection = this.growableCoralBlocks.length > 3 ? Direction.random(random) : Direction.byIndex(random.nextInt(5) + 1);
			BlockPos growPos = pos.offset(randDirection);
			IFluidState fluidState = worldIn.getBlockState(growPos).getFluidState();
			boolean isValidPosToGrow = worldIn.getBlockState(growPos).getMaterial().isReplaceable() && fluidState.getLevel() >= 8 && fluidState.isTagged(FluidTags.WATER);
			
			if(isValidPosToGrow && state.get(POWERED)) {
				if(randDirection.getIndex() > 1) {
					worldIn.setBlockState(growPos, growableCoralBlocks[2].getDefaultState().with(CoralWallFanBlock.FACING, randDirection), 2);
				} else if(randDirection.getIndex() == 1) {
					if(random.nextBoolean()) {
						worldIn.setBlockState(growPos, growableCoralBlocks[1].getDefaultState(), 2);
					} else {
						worldIn.setBlockState(growPos, growableCoralBlocks[0].getDefaultState(), 2);
					}
				} else {
					worldIn.setBlockState(growPos, growableCoralBlocks[3].getDefaultState(), 2);
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
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(POWERED, context.getWorld().isBlockPowered(context.getPos()));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}
}