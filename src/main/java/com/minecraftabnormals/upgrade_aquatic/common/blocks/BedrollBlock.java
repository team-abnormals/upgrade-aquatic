package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import javax.annotation.Nullable;

import com.minecraftabnormals.upgrade_aquatic.common.tileentities.BedrollTileEntity;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BedrollBlock extends BedBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;
	public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	private final DyeColor color;

	public BedrollBlock(DyeColor colorIn, Block.Properties builder) {
		super(colorIn, builder);
		this.color = colorIn;
		this.setDefaultState(this.stateContainer.getBaseState().with(PART, BedPart.FOOT).with(OCCUPIED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.2F);
	}
	
	public void onLanded(IBlockReader worldIn, Entity entityIn) {
		if (entityIn.isCrouching()) {
			super.onLanded(worldIn, entityIn);
		} else if (entityIn.getMotion().y < 0.0D) {
			entityIn.setMotion(entityIn.getMotion().x, -entityIn.getMotion().y * (double)0.66F, entityIn.getMotion().z);
			
			if (!(entityIn instanceof LivingEntity)) {
				entityIn.setMotion(entityIn.getMotion().x, entityIn.getMotion().y * 0.3F, entityIn.getMotion().z);
			}
			
		}
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		
		if (facing == func_208070_a(stateIn.get(PART), stateIn.get(HORIZONTAL_FACING))) {
			return facingState.getBlock() == this && facingState.get(PART) != stateIn.get(PART) ? stateIn.with(OCCUPIED, facingState.get(OCCUPIED)) : Blocks.AIR.getDefaultState();
		} else {
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		}
	}
	
	private static Direction func_208070_a(BedPart p_208070_0_, Direction p_208070_1_) {
		return p_208070_0_ == BedPart.FOOT ? p_208070_1_ : p_208070_1_.getOpposite();
	}
	
	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			super.onReplaced(state, worldIn, pos, newState, isMoving);
			worldIn.removeTileEntity(pos);
		}
	}
	
	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		BedPart bedpart = state.get(PART);
		BlockPos blockpos = pos.offset(getDirectionToOther(bedpart, state.get(HORIZONTAL_FACING)));
		BlockState blockstate = worldIn.getBlockState(blockpos);
		if (blockstate.getBlock() == this && blockstate.get(PART) != bedpart) {
			worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
			worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
			if (!worldIn.isRemote && !player.isCreative()) {
				ItemStack itemstack = player.getHeldItemMainhand();
				spawnDrops(state, worldIn, pos, (TileEntity)null, player, itemstack);
			}

			player.addStat(Stats.BLOCK_MINED.get(this));
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	   
	private static Direction getDirectionToOther(BedPart p_208070_0_, Direction p_208070_1_) {
		return p_208070_0_ == BedPart.FOOT ? p_208070_1_ : p_208070_1_.getOpposite();
	}
	
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction enumfacing = context.getPlacementHorizontalFacing();
		BlockPos blockpos = context.getPos();
		BlockPos blockpos1 = blockpos.offset(enumfacing);
		
		if (context.getWorld().getBlockState(blockpos).getBlock() == Blocks.WATER) {
			return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, enumfacing).with(WATERLOGGED, Boolean.valueOf(true)) : null;
		} else {
			return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, enumfacing).with(WATERLOGGED, Boolean.valueOf(false)) : null;
		}
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean hasCustomBreakingProgress(BlockState state) {
		return true;
	}

	@Nullable
	public static BlockPos getSafeExitLocation(IBlockReader worldIn, BlockPos pos, int tries) {
		Direction enumfacing = worldIn.getBlockState(pos).get(HORIZONTAL_FACING);
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		
		for(int l = 0; l <= 1; ++l) {
			int i1 = i - enumfacing.getXOffset() * l - 1;
			int j1 = k - enumfacing.getZOffset() * l - 1;
			int k1 = i1 + 2;
			int l1 = j1 + 2;
			
			for(int i2 = i1; i2 <= k1; ++i2) {
				for(int j2 = j1; j2 <= l1; ++j2) {
					BlockPos blockpos = new BlockPos(i2, j, j2);
					if (hasRoomForPlayer(worldIn, blockpos)) {
						if (tries <= 0) {
							return blockpos;
						}
						--tries;
					}
				}
			}
		}
		return null;
	}
	
	protected static boolean hasRoomForPlayer(IBlockReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).isSolid() && !worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getMaterial().isSolid();
	}
	
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}
	
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, PART, OCCUPIED, WATERLOGGED);
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new BedrollTileEntity(this.color);
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (!worldIn.isRemote) {
			BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING));
			worldIn.setBlockState(blockpos, state.with(PART, BedPart.HEAD), 3);
			worldIn.notifyNeighborsOfStateChange(pos, Blocks.AIR);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public DyeColor getColor() {
		return this.color;
	}
	
	@OnlyIn(Dist.CLIENT)
	public long getPositionRandom(BlockState state, BlockPos pos) {
		BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING), state.get(PART) == BedPart.HEAD ? 0 : 1);
		return MathHelper.getCoordinateRandom(blockpos.getX(), pos.getY(), blockpos.getZ());
	}
}
