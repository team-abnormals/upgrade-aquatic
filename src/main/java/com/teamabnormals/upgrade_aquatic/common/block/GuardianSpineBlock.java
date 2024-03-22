package com.teamabnormals.upgrade_aquatic.common.block;

import com.teamabnormals.upgrade_aquatic.core.other.UADamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GuardianSpineBlock extends DirectionalBlock implements SimpleWaterloggedBlock {
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
			Block.box(15.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D),
			Block.box(6.0D, 6.0D, 15.0D, 10.0D, 10.0D, 16.0D),
			Block.box(6.0D, 15.0D, 6.0D, 10.0D, 16.0D, 10.0D),
			//Elder
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 1.0D, 12.0D),
			Block.box(15.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D),
			Block.box(4.0D, 4.0D, 15.0D, 12.0D, 12.0D, 16.0D),
			Block.box(4.0D, 15.0D, 4.0D, 12.0D, 16.0D, 12.0D),

			//Fixers Elder
			Block.box(0.0D, 4.0D, 4.0D, 1.0D, 12.0D, 12.0D),
			Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 1.0D),
			//Fixes
			Block.box(0.0D, 6.0D, 6.0D, 1.0D, 10.0D, 10.0D),
			Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 1.0D),
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
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return state.getValue(DRAWN);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
		if (!state.getValue(ELDER)) {
			if (state.getValue(DRAWN)) {
				if (state.getValue(FACING).getAxis() == Axis.Y) {
					return SHAPES[0];
				} else if (state.getValue(FACING).getAxis() == Axis.X) {
					return SHAPES[1];
				} else if (state.getValue(FACING).getAxis() == Axis.Z) {
					return SHAPES[2];
				}
			} else {
				if (state.getValue(FACING).getAxis() == Axis.Y) {
					if (state.getValue(FACING).getAxisDirection() == AxisDirection.POSITIVE) {
						return SHAPES_RETRACTED[0];
					}
					return SHAPES_RETRACTED[3];
				} else if (state.getValue(FACING).getAxis() == Axis.X) {
					if (state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[1];
					}
					return SHAPES_RETRACTED[10];
				} else if (state.getValue(FACING).getAxis() == Axis.Z) {
					if (state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[2];
					}
					return SHAPES_RETRACTED[11];
				}
			}
		} else {
			if (state.getValue(DRAWN)) {
				if (state.getValue(FACING).getAxis() == Axis.Y) {
					return SHAPES[3];
				} else if (state.getValue(FACING).getAxis() == Axis.X) {
					return SHAPES[4];
				} else if (state.getValue(FACING).getAxis() == Axis.Z) {
					return SHAPES[5];
				}
			} else {
				if (state.getValue(FACING).getAxis() == Axis.Y) {
					if (state.getValue(FACING).getAxisDirection() == AxisDirection.POSITIVE) {
						return SHAPES_RETRACTED[4];
					} else {
						return SHAPES_RETRACTED[7];
					}
				} else if (state.getValue(FACING).getAxis() == Axis.X) {
					if (state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[5];
					}
					return SHAPES_RETRACTED[8];
				} else if (state.getValue(FACING).getAxis() == Axis.Z) {
					if (state.getValue(FACING).getAxisDirection() == AxisDirection.NEGATIVE) {
						return SHAPES_RETRACTED[6];
					}
					return SHAPES_RETRACTED[9];
				}
			}
		}
		return null;
	}

	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (entity instanceof LivingEntity && state.getValue(DRAWN)) {
			entity.makeStuckInBlock(state, new Vec3(0.25D, 0.5D, 0.25D));
			if (!entity.isInvulnerable()) {
				if (state.getValue(ELDER))
					((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 40));
			}
			if (!level.isClientSide() && (entity.xOld != entity.getX() || entity.zOld != entity.getZ() || entity.yOld != entity.getY())) {
				double d0 = Math.abs(entity.getX() - entity.xOld);
				double d1 = Math.abs(entity.getZ() - entity.zOld);
				double d2 = Math.abs(entity.getY() - entity.yOld);
				if (d0 >= 0.003D || d1 >= 0.003D || d2 >= 0.003D) {
					if (state.getValue(ELDER)) {
						entity.hurt(UADamageTypes.guardianSpine(level, true), 3.0F);
					} else {
						entity.hurt(UADamageTypes.guardianSpine(level, false), 2.0F);
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

	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, DRAWN, ELDER);
	}

	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean p_220069_6_) {
		if (!worldIn.isClientSide) {
			boolean flag = state.getValue(DRAWN);
			if (flag != worldIn.hasNeighborSignal(pos)) {
				if (flag) {
					worldIn.scheduleTick(pos, this, 1);
				} else {
					float pitch = state.getValue(ELDER) ? 0.85F : 1.0F;
					worldIn.playSound(null, pos, SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 0.3F, pitch);
					worldIn.setBlock(pos, state.cycle(DRAWN), 2);
				}
			}
		}
	}

	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		if (!worldIn.isClientSide) {
			if (state.getValue(DRAWN) && !worldIn.hasNeighborSignal(pos)) {
				float pitch = state.getValue(ELDER) ? 0.85F : 1.0F;
				worldIn.playSound(null, pos, SoundEvents.PISTON_CONTRACT, SoundSource.BLOCKS, 0.3F, pitch);
				worldIn.setBlock(pos, state.cycle(DRAWN), 2);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction direction = context.getClickedFace();
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direction.getOpposite()));
		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return state.getBlock() == this && state.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER).setValue(DRAWN, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos()))) : this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER)).setValue(DRAWN, Boolean.valueOf(context.getLevel().hasNeighborSignal(context.getClickedPos())));
	}

	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return BlockPathTypes.LAVA;
	}

}
