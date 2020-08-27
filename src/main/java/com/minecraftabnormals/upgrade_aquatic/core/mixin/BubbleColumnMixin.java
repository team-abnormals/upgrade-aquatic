package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

@Mixin(BubbleColumnBlock.class)
public final class BubbleColumnMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
    	BlockPos abovePos = pos.up();
		Block aboveBlock = world.getBlockState(abovePos).getBlock();
		boolean noFallingBlockAbove = world.getEntitiesWithinAABB(FallingBlockEntity.class, new AxisAlignedBB(pos)).isEmpty();
		
		if (noFallingBlockAbove) {
			UABlocks.FALLABLES.forEach((inputBlock, outputBlock) -> {
				if (inputBlock.get() == aboveBlock) {
					this.spawnFallingBlock(world, pos, outputBlock.get());
				}
			});
			
			if (UABlocks.ATMOSHPERIC_FALLABLES != null) {
				UABlocks.ATMOSHPERIC_FALLABLES.forEach((inputBlock, outputBlock) -> {
					if (inputBlock.get() == aboveBlock) {
						this.spawnFallingBlock(world, pos, outputBlock.get());
					}
				});
			}
		}
    }
    
    private void spawnFallingBlock(ServerWorld world, BlockPos pos, Block block) {
		FallingBlockEntity fallingblockentity = new FallingBlockEntity(world, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, block.getDefaultState());
		fallingblockentity.fallTime = 1;
		world.addEntity(fallingblockentity);
	}
}