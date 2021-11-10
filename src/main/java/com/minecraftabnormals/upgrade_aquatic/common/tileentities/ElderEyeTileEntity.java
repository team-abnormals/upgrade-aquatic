package com.minecraftabnormals.upgrade_aquatic.common.tileentities;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.ElderEyeBlock;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UATileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.stream.IntStream;

public class ElderEyeTileEntity extends TileEntity implements ITickableTileEntity {

	public ElderEyeTileEntity() {
		super(UATileEntities.ELDER_EYE.get());
	}

	@Override
	@SuppressWarnings("deprecation")
	public void tick() {
		if (level.getGameTime() % 4 == 0 && !level.isClientSide) {
			BlockState state = level.getBlockState(worldPosition);

			if (!(state.getBlock() instanceof ElderEyeBlock)) return;

			Direction facing = state.getValue(ElderEyeBlock.FACING);
			AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 1, 1).move(worldPosition.relative(facing)).expandTowards(facing.getStepX() * calcRange(facing), facing.getStepY() * calcRange(facing), facing.getStepZ() * calcRange(facing));
			List<Entity> entities = level.getEntitiesOfClass(Entity.class, bb);

			int entityCount = entities.size();
			boolean hasEntity = entityCount > 0;

			if (hasEntity) {

				for (int i = 0; i < entities.size(); i++) {
					Entity entity = entities.get(i);

					if (!(entity instanceof LivingEntity) || entity instanceof ArmorStandEntity) {
						entityCount--;
					}

					if (entityCount <= 0) {
						hasEntity = false;
					}

					int[] posCheck = {
							facing.getStepX() * (MathHelper.floor(entity.getX()) - this.worldPosition.getX()),
							facing.getStepY() * (MathHelper.floor(entity.getY()) - this.worldPosition.getY()),
							facing.getStepZ() * (MathHelper.floor(entity.getZ()) - this.worldPosition.getZ())
					};

					for (int b = 1; b < Math.abs(IntStream.of(posCheck).sum()); b++) {

						if (!level.getBlockState(this.worldPosition.relative(facing, b)).isAir()) {
							if (level.getBlockState(this.worldPosition.relative(facing, b)).getMaterial().blocksMotion()) {
								entityCount--;
								if (entityCount <= 0) {
									hasEntity = false;
									break;
								}
							}
						}

					}
				}
			}

			if (state.getValue(ElderEyeBlock.POWERED) != hasEntity && state.getValue(ElderEyeBlock.ACTIVE)) {
				level.setBlockAndUpdate(worldPosition, state.getBlockState().setValue(ElderEyeBlock.POWERED, hasEntity));
				((ElderEyeBlock) state.getBlock()).updateRedstoneNeighbors(state, getLevel(), getBlockPos());
			}
		}
	}

	public int calcRange(Direction direction) {
		int i;
		for (i = 1; i < 13; i++) {
			if (level.getBlockState(worldPosition.relative(direction, i)).getMaterial() != Material.AIR) {

				if (level.getBlockState(worldPosition.relative(direction, i)).getMaterial() != Material.WATER) {
					break;
				}

			}
		}
		return i;
	}

	public BlockPos getBlockPosForRange(Direction direction) {
		return worldPosition.relative(direction, this.calcRange(direction));
	}
}
