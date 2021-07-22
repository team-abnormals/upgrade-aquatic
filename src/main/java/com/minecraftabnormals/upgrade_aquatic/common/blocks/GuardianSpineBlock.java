package com.minecraftabnormals.upgrade_aquatic.common.blocks;

import com.minecraftabnormals.upgrade_aquatic.core.other.UADamageSources;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Direction.AxisDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class GuardianSpineBlock extends DirectionalBlock implements IBucketPickupHandler, ILiquidContainer {
	public static final BooleanProperty DRAWN = BooleanProperty.create("drawn");
	public static final BooleanProperty ELDER = BooleanProperty.create("elder");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape[] SHAPES = { 
		Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), 
		Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D),
		Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D),
		//Elder
		Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
		Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
		Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D),
	};
	protected static final VoxelShape[] SHAPES_RETRACTED = { 
		Block.box(6.0D, 0.0D, 6.0D, 10.0D, 1.0D, 10.0D), 
		Block.box(16.0D, 6.0D, 6.0D, 15.0D, 10.0D, 10.0D),
		Block.box(6.0D, 6.0D, 16.0D, 10.0D, 10.0D, 15.0D),
		Block.box(6.0D, 16.0D, 6.0D, 10.0D, 15.0D, 10.0D),
		//Elder
		Block.box(4.0D, 0.0D, 4.0D, 12.0D, 1.0D, 12.0D),
		Block.box(16.0D, 4.0D, 4.0D, 15.0D, 12.0D, 12.0D),
		Block.box(4.0D, 4.0D, 16.0D, 12.0D, 12.0D, 15.0D),
		Block.box(4.0D, 16.0D, 4.0D, 12.0D, 15.0D, 12.0D),
		
		//Fixers Elder
		Block.box(0.0D, 4.0D, 4.0D, 1.0D, 12.0D, 12.0D),
		Block.box(4.0D, 4.0D, 1.0D, 12.0D, 12.0D, 0.0D),
		//Fixes
		Block.box(0.0D, 6.0D, 6.0D, 1.0D, 10.0D, 10.0D),
		Block.box(6.0D, 6.0D, 1.0D, 10.0D, 10.0D, 0.0D),
	};
	
	public GuardianSpineBlock(Properties props, boolean elder) {
		super(props);
		this.registerDefaultState(this.stateDefinition.any()
			.setValue(WATERLOGGED, false)
			.setValue(DRAWN, false)
			.setValue(ELDER, elder)
			.setValue(FACING, Direction.SOUTH)
		);
	}
	
	@Override
	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return state.getValue(DRAWN);
	}
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		if(!state.getValue(ELDER)) {
			if(state.getValue(DRAWN)) {
				if(state.getValue(FACING).getAxis() == Axis.Y) {
					return SHAPES[0];
				} else if(state.getValue(FACING).getAxis() == Axis.X) {
					return SHAPES[1];
				} else if(state.getValue(FACING).getAxis() == Axis.Z) {
					return SHAPES[2];
				}
			} else {
				if(state.getValue(FACING).getAxis() == Axis.Y) {
					if(state.getValue(FACING).getAxisDirection() == AxisDirection.POSITIVE) {
						return SHAPES_RETRACTED[0];
					}
					return SHAPES_RETRACTED[3];
				} else if(state.getValue(FACING).getAxis() == Axis.X) {
					if(state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[1];
					}
					return SHAPES_RETRACTED[10];
				} else if(state.getValue(FACING).getAxis() == Axis.Z) {
					if(state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[2];
					}
					return SHAPES_RETRACTED[11];
				}
			}
		} else {
			if(state.getValue(DRAWN)) {
				if(state.getValue(FACING).getAxis() == Axis.Y) {
					return SHAPES[3];
				} else if(state.getValue(FACING).getAxis() == Axis.X) {
					return SHAPES[4];
				} else if(state.getValue(FACING).getAxis() == Axis.Z) {
					return SHAPES[5];
				}
			} else {
				if(state.getValue(FACING).getAxis() == Axis.Y) {
					if(state.getValue(FACING).getAxisDirection() == AxisDirection.POSITIVE) {
						return SHAPES_RETRACTED[4];
					} else {
						return SHAPES_RETRACTED[7];
					}
				} else if(state.getValue(FACING).getAxis() == Axis.X) {
					if(state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[5];
					}
					return SHAPES_RETRACTED[8];
				} else if(state.getValue(FACING).getAxis() == Axis.Z) {
					if(state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[6];
					}
					return SHAPES_RETRACTED[9];
				}
			}
		}
		return null;
	}
	
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && state.getValue(DRAWN)) {
			entityIn.makeStuckInBlock(state, new Vector3d(0.25D, 0.5D, 0.25D));
			if(!entityIn.isInvulnerable()) {
				if(state.getValue(ELDER)) ((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, 40));
			}
			if (!worldIn.isClientSide && (entityIn.xOld != entityIn.getX() || entityIn.zOld != entityIn.getZ() || entityIn.yOld != entityIn.getY())) {
				double d0 = Math.abs(entityIn.getX() - entityIn.xOld);
				double d1 = Math.abs(entityIn.getZ() - entityIn.zOld);
				double d2 = Math.abs(entityIn.getY() - entityIn.yOld);
				if (d0 >= 0.003D || d1 >= 0.003D || d2 >= 0.003D) {
					if(state.getValue(ELDER)) {
						entityIn.hurt(UADamageSources.ELDER_GUARDIAN_SPINE, 3.0F);
					} else {
						entityIn.hurt(UADamageSources.GUARDIAN_SPINE, 2.0F);
					}
				}
			}
		}
	}
	
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}
	
	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
	
	public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, DRAWN, ELDER);
	}
	
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean p_220069_6_) {
		if (!worldIn.isClientSide) {
			boolean flag = state.getValue(DRAWN);
			if (flag != worldIn.hasNeighborSignal(pos)) {
				if (flag) {
					worldIn.getBlockTicks().scheduleTick(pos, this, 1);
				} else {
					float pitch = state.getValue(ELDER) ? 0.85F : 1.0F;
					worldIn.playSound((PlayerEntity)null, pos, SoundEvents.PISTON_EXTEND, SoundCategory.BLOCKS, 0.3F, pitch);
					worldIn.setBlock(pos, state.cycle(DRAWN), 2);
				}
			}
		}
	}

	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isClientSide) {
			if (state.getValue(DRAWN) && !worldIn.hasNeighborSignal(pos)) {
				float pitch = state.getValue(ELDER) ? 0.85F : 1.0F;
				worldIn.playSound((PlayerEntity)null, pos, SoundEvents.PISTON_CONTRACT, SoundCategory.BLOCKS, 0.3F, pitch);
				worldIn.setBlock(pos, state.cycle(DRAWN), 2);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
	    return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getClickedFace();
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return state.getBlock() == this && state.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER)).setValue(DRAWN, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos()))) : this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER)).setValue(DRAWN, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
	}
	
	@Override
	public Fluid takeLiquid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
	        return Fluids.EMPTY;
		}
	}
	
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.getValue(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.getValue(WATERLOGGED) && fluidStateIn.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
	
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
		return PathNodeType.LAVA;
	}
	
}
