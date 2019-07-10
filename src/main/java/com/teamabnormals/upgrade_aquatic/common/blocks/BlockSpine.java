package com.teamabnormals.upgrade_aquatic.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Direction.AxisDirection;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockSpine extends DirectionalBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final BooleanProperty DRAWN = BooleanProperty.create("drawn");
	public static final BooleanProperty ELDER = BooleanProperty.create("elder");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape[] SHAPES = { 
		Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), 
		Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D),
		Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D),
		//Elder
		Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
		Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
		Block.makeCuboidShape(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D),
	};
	protected static final VoxelShape[] SHAPES_RETRACTED = { 
		Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 1.0D, 10.0D), 
		Block.makeCuboidShape(16.0D, 6.0D, 6.0D, 15.0D, 10.0D, 10.0D),
		Block.makeCuboidShape(6.0D, 6.0D, 16.0D, 10.0D, 10.0D, 15.0D),
		Block.makeCuboidShape(6.0D, 16.0D, 6.0D, 10.0D, 15.0D, 10.0D),
		//Elder
		Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 1.0D, 12.0D),
		Block.makeCuboidShape(16.0D, 4.0D, 4.0D, 15.0D, 12.0D, 12.0D),
		Block.makeCuboidShape(4.0D, 4.0D, 16.0D, 12.0D, 12.0D, 15.0D),
		Block.makeCuboidShape(4.0D, 16.0D, 4.0D, 12.0D, 15.0D, 12.0D),
		
		//Fixers Elder
		Block.makeCuboidShape(0.0D, 4.0D, 4.0D, 1.0D, 12.0D, 12.0D),
		Block.makeCuboidShape(4.0D, 4.0D, 1.0D, 12.0D, 12.0D, 0.0D),
		//Fixes
		Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 1.0D, 10.0D, 10.0D),
		Block.makeCuboidShape(6.0D, 6.0D, 1.0D, 10.0D, 10.0D, 0.0D),
	};
	
	public BlockSpine(Properties props, boolean elder) {
		super(props);
		this.setDefaultState(this.stateContainer.getBaseState()
			.with(WATERLOGGED, false)
			.with(DRAWN, false)
			.with(ELDER, elder)
			.with(FACING, Direction.SOUTH)
		);
	}
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		if(!state.get(ELDER)) {
			if(state.get(DRAWN)) {
				if(state.get(FACING).getAxis() == Axis.Y) {
					return SHAPES[0];
				} else if(state.get(FACING).getAxis() == Axis.X) {
					return SHAPES[1];
				} else if(state.get(FACING).getAxis() == Axis.Z) {
					return SHAPES[2];
				}
			} else {
				if(state.get(FACING).getAxis() == Axis.Y) {
					if(state.get(FACING).getAxisDirection() == AxisDirection.POSITIVE) {
						return SHAPES_RETRACTED[0];
					}
					return SHAPES_RETRACTED[3];
				} else if(state.get(FACING).getAxis() == Axis.X) {
					if(state.get(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[1];
					}
					return SHAPES_RETRACTED[10];
				} else if(state.get(FACING).getAxis() == Axis.Z) {
					if(state.get(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[2];
					}
					return SHAPES_RETRACTED[11];
				}
			}
		} else {
			if(state.get(DRAWN)) {
				if(state.get(FACING).getAxis() == Axis.Y) {
					return SHAPES[3];
				} else if(state.get(FACING).getAxis() == Axis.X) {
					return SHAPES[4];
				} else if(state.get(FACING).getAxis() == Axis.Z) {
					return SHAPES[5];
				}
			} else {
				if(state.get(FACING).getAxis() == Axis.Y) {
					if(state.get(FACING).getAxisDirection() == AxisDirection.POSITIVE) {
						return SHAPES_RETRACTED[4];
					} else {
						return SHAPES_RETRACTED[7];
					}
				} else if(state.get(FACING).getAxis() == Axis.X) {
					if(state.get(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[5];
					}
					return SHAPES_RETRACTED[8];
				} else if(state.get(FACING).getAxis() == Axis.Z) {
					if(state.get(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[6];
					}
					return SHAPES_RETRACTED[9];
				}
			}
		}
		return null;
	}
	
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && state.get(DRAWN)) {
			entityIn.setMotionMultiplier(state, new Vec3d(0.25D, 0.5D, 0.25D));
			if(state.get(ELDER)) ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 3));
			if (!worldIn.isRemote && (entityIn.lastTickPosX != entityIn.posX || entityIn.lastTickPosZ != entityIn.posZ)) {
				double d0 = Math.abs(entityIn.posX - entityIn.lastTickPosX);
				double d1 = Math.abs(entityIn.posZ - entityIn.lastTickPosZ);
				if (d0 >= 0.003D || d1 >= 0.003D) {
					if(state.get(ELDER)) {
						entityIn.attackEntityFrom(DamageSource.CACTUS, 3.0F);
					} else {
						entityIn.attackEntityFrom(DamageSource.CACTUS, 2.0F);
					}
				}
			}
		}
	}
	
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, DRAWN, ELDER);
	}
	
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean p_220069_6_) {
		if (!worldIn.isRemote) {
			boolean flag = state.get(DRAWN);
			if (flag != worldIn.isBlockPowered(pos)) {
				if (flag) {
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1);
				} else {
					worldIn.setBlockState(pos, state.cycle(DRAWN), 2);
				}
			}
		}
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (state.get(DRAWN) && !worldIn.isBlockPowered(pos)) {
				worldIn.setBlockState(pos, state.cycle(DRAWN), 2);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	    return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getFace();
		BlockState state = context.getWorld().getBlockState(context.getPos().offset(direction.getOpposite()));
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return state.getBlock() == this && state.get(FACING) == direction ? this.getDefaultState().with(FACING, direction.getOpposite()).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER)).with(DRAWN, Boolean.valueOf(context.getWorld().isBlockPowered(context.getPos()))) : this.getDefaultState().with(FACING, direction).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER)).with(DRAWN, Boolean.valueOf(context.getWorld().isBlockPowered(context.getPos())));
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
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
}
