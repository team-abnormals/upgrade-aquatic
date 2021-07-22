package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class TallBeachgrassBlock extends Block implements IGrowable {
	private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.LARGE_FERN);

	public TallBeachgrassBlock(Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
	}
	
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		return SHAPE;
	}
	
	@Override
	public boolean canBeReplaced(BlockState state, BlockItemUseContext useContext) {
		return false;
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return block.is(BlockTags.SAND);
	}
	   
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		DoubleBlockHalf doubleblockhalf = stateIn.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (facing == Direction.UP) || facingState.getBlock() == this && facingState.getValue(HALF) != doubleblockhalf) {
			return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		} else {
			return Blocks.AIR.defaultBlockState();
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos blockpos = context.getClickedPos();
		return blockpos.getY() < context.getLevel().getMaxBuildHeight() - 1 && context.getLevel().getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
	}

	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
	}

	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			return this.isValidGround(worldIn.getBlockState(pos.below()), worldIn, pos);
		} else {
			BlockState blockstate = worldIn.getBlockState(pos.below());
			if (state.getBlock() != this) this.isValidGround(worldIn.getBlockState(pos.below()), worldIn, pos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
			return blockstate.getBlock() == this && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
		}
	}

	public void placeAt(IWorld p_196390_1_, BlockPos p_196390_2_, int flags) {
		p_196390_1_.setBlock(p_196390_2_, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER), flags);
		p_196390_1_.setBlock(p_196390_2_.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), flags);
	}

	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
		BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (blockstate.getBlock() == this && blockstate.getValue(HALF) != doubleblockhalf) {
			worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
			worldIn.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
			if(!worldIn.isClientSide && !player.isCreative() && player.getMainHandItem().getItem() instanceof ShearsItem) {
				popResource(worldIn, pos, new ItemStack(UABlocks.BEACHGRASS.get()));
				popResource(worldIn, pos.above(), new ItemStack(UABlocks.BEACHGRASS.get()));
			} else if(!worldIn.isClientSide && !player.isCreative() && !(player.getMainHandItem().getItem() instanceof ShearsItem)) {
				Random rand = new Random();
				if(rand.nextFloat() < 0.125F) {
					popResource(worldIn, pos, new ItemStack(Items.BEETROOT_SEEDS));
				}
			}
		}

		super.playerWillDestroy(worldIn, pos, state, player);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HALF);
	}

	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	public Block.OffsetType getOffsetType() {
		return Block.OffsetType.XZ;
	}

	@OnlyIn(Dist.CLIENT)
	public long getSeed(BlockState state, BlockPos pos) {
		return MathHelper.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}
	
	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
	
	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		if(!worldIn.isClientSide) {
			BlockState blockstate = UABlocks.BEACHGRASS.get().defaultBlockState();
			cont:
			for(int i = 0; i < 128; ++i) {
				BlockPos blockpos = pos;
				
				for(int j = 0; j < i / 16; j++) {
					blockpos = blockpos.offset(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
					if(Block.isShapeFullBlock(worldIn.getBlockState(blockpos).getCollisionShape(worldIn, blockpos))) {
						continue cont;
					}
				}
				
				if(blockstate.canSurvive(worldIn, blockpos) && worldIn.isEmptyBlock(blockpos) && rand.nextFloat() <= 0.10F) {
					worldIn.setBlockAndUpdate(blockpos, blockstate);
				}
			}
		}
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this.asItem(), group, items);
	}
}