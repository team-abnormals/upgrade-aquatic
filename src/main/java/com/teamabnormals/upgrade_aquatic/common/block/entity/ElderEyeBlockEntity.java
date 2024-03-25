package com.teamabnormals.upgrade_aquatic.common.block.entity;

import com.teamabnormals.upgrade_aquatic.common.block.ElderEyeBlock;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ElderEyeBlockEntity extends BlockEntity {

	public ElderEyeBlockEntity(BlockPos pos, BlockState state) {
		super(UABlockEntityTypes.ELDER_EYE.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, ElderEyeBlockEntity elderEye) {
		if (level.getGameTime() % 4 == 0 && !level.isClientSide && state.getValue(ElderEyeBlock.ACTIVE)) {
			if (!(state.getBlock() instanceof ElderEyeBlock)) return;

			Direction facing = state.getValue(ElderEyeBlock.FACING);
			int range = calcRange(level, pos, facing);
			AABB bb = new AABB(0, 0, 0, 1, 1, 1).move(pos.relative(facing)).expandTowards(facing.getStepX() * range, 0, facing.getStepZ() * range);
			boolean hasEntity = level.getEntitiesOfClass(Entity.class, bb).size() > 0;

			int entityDistance = -1;
			if (hasEntity) {
				for (int i = 1; i < range; i++) {
					BlockPos newPos = pos.relative(facing, i);
					if (level.getEntitiesOfClass(LivingEntity.class, new AABB(0, 0, 0, 1, 1, 1).move(newPos)).size() > 0) {
						entityDistance = i;
						break;
					}
				}
			}

			BlockState newState = state.setValue(ElderEyeBlock.POWER, 0);
			if (hasEntity && entityDistance > -1) {
				newState = state.setValue(ElderEyeBlock.POWER, 16 - entityDistance);
			}

			if (state != newState) {
				level.setBlockAndUpdate(pos, newState);
				((ElderEyeBlock) state.getBlock()).updateRedstoneNeighbors(state, level, pos);
			}
		}
	}

	public static int calcRange(Level level, BlockPos pos, Direction direction) {
		int i;
		for (i = 1; i <= 15; i++) {
			BlockPos newPos = pos.relative(direction, i);
			if (level.getBlockState(newPos).isViewBlocking(level, pos)) {
				return i - 1;
			}
		}
		return i;
	}

}
