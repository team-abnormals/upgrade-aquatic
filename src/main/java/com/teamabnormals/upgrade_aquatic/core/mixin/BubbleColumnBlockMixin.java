package com.teamabnormals.upgrade_aquatic.core.mixin;

import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin {

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo info) {
		if (!state.getValue(BubbleColumnBlock.DRAG_DOWN) && UAConfig.COMMON.renewableSandRequiresMagmaBlocks.get()) {
			return;
		}

		BlockPos abovePos = pos.above();
		Block aboveBlock = world.getBlockState(abovePos).getBlock();
		boolean noFallingBlockAbove = world.getEntitiesOfClass(FallingBlockEntity.class, new AABB(pos)).isEmpty();

		if (noFallingBlockAbove) {
			if (UAConfig.COMMON.renewableSand.get()) {
				UABlocks.SAND_FALLABLES.forEach((inputBlock, outputBlock) -> {
					if (inputBlock.get() == aboveBlock) {
						this.spawnFallingBlock(world, pos, outputBlock.get());
					}
				});

				if (UABlocks.ATMOSPHERIC_SAND_FALLABLES != null) {
					UABlocks.ATMOSPHERIC_SAND_FALLABLES.forEach((inputBlock, outputBlock) -> {
						if (inputBlock.get() == aboveBlock) {
							this.spawnFallingBlock(world, pos, outputBlock.get());
						}
					});
				}
			}

			if (UAConfig.COMMON.renewableGravel.get()) {
				UABlocks.GRAVEL_FALLABLES.forEach((inputBlock, outputBlock) -> {
					if (inputBlock.get() == aboveBlock) {
						this.spawnFallingBlock(world, pos, outputBlock.get());
					}
				});
			}
		}
	}

	private void spawnFallingBlock(ServerLevel world, BlockPos pos, Block block) {
		FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(world, pos, block.defaultBlockState());
		fallingblockentity.time = 1;
		world.addFreshEntity(fallingblockentity);
	}
}