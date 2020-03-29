package com.teamabnormals.upgrade_aquatic.common.tileentities;

import java.util.List;
import java.util.stream.IntStream;

import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.core.registry.UATileEntities;

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

public class TileEntityElderEye extends TileEntity implements ITickableTileEntity {
	
	public TileEntityElderEye() {
		super(UATileEntities.ELDER_EYE.get());
	}

	@Override
	@SuppressWarnings("deprecation")
	public void tick() {
		if(world.getGameTime() % 4 == 0 && !world.isRemote) {
			BlockState state = world.getBlockState(pos);
			
			if(!(state.getBlock() instanceof BlockElderEye)) return;

			Direction facing = state.get(BlockElderEye.FACING);
			AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 1, 1).offset(pos.offset(facing)).expand(facing.getXOffset() * calcRange(facing), facing.getYOffset() * calcRange(facing), facing.getZOffset() * calcRange(facing));
			List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, bb);

			int entityCount = entities.size();
			boolean hasEntity = entityCount > 0;

			if(hasEntity) {
				
				for(int i = 0; i < entities.size(); i++) {
					Entity entity = entities.get(i);
					
                    if(!(entity instanceof LivingEntity) || entity instanceof ArmorStandEntity) {
						entityCount--;
					}
					
					if(entityCount <= 0) {
						hasEntity = false;
					}

					int[] posCheck = {
						facing.getXOffset() * (MathHelper.floor(entity.getPosX()) - this.pos.getX()),
						facing.getYOffset() * (MathHelper.floor(entity.getPosY()) - this.pos.getY()),
						facing.getZOffset() * (MathHelper.floor(entity.getPosZ()) - this.pos.getZ())
					};
					
					for(int b = 1; b < Math.abs(IntStream.of(posCheck).sum()); b++) {
						
						if(!world.getBlockState(this.pos.offset(facing, b)).isAir()) {
							if(world.getBlockState(this.pos.offset(facing, b)).getMaterial().blocksMovement()) {
								entityCount--;
								if(entityCount <= 0) {
									hasEntity = false;
									break;
								}
							}
						}
						
					}
				}
			}

			if(state.get(BlockElderEye.POWERED) != hasEntity && state.get(BlockElderEye.ACTIVE)) {
				world.setBlockState(pos, state.getBlockState().with(BlockElderEye.POWERED, hasEntity));
				((BlockElderEye)state.getBlock()).updateRedstoneNeighbors(state, getWorld(), getPos());
			}
		}
	}
	
	public int calcRange(Direction direction) {
		int i;
		for(i = 1; i < 13; i++) {
			if(world.getBlockState(pos.offset(direction, i)).getMaterial() != Material.AIR) {
				
				if(world.getBlockState(pos.offset(direction, i)).getMaterial() != Material.WATER) {
					break;
				}
				
			}
		}
		return i;
	}
	
	public BlockPos getBlockPosForRange(Direction direction) {
		return pos.offset(direction, this.calcRange(direction));
	}
}
