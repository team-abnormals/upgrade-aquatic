package com.teamabnormals.upgrade_aquatic.core.mixin;

import com.teamabnormals.upgrade_aquatic.core.UAConfig;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps;
import com.teamabnormals.upgrade_aquatic.core.other.UADataMaps.BubbleColumnRenewable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
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
	private void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo info) {
		if (!state.getValue(BubbleColumnBlock.DRAG_DOWN) && UAConfig.COMMON.renewableSandRequiresMagmaBlocks.get()) {
			return;
		}

		BlockPos abovePos = pos.above();
		Block aboveBlock = level.getBlockState(abovePos).getBlock();
		if (level.getEntitiesOfClass(FallingBlockEntity.class, new AABB(pos)).isEmpty()) {
			if (UAConfig.COMMON.renewableSand.get()) {
				Registry<Block> blocks = level.registryAccess().registryOrThrow(Registries.BLOCK);
				BubbleColumnRenewable renewable = blocks.getData(UADataMaps.BUBBLE_COLUMN_RENEWABLES, blocks.getResourceKey(aboveBlock).get());
				if (renewable != null) {
					FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(level, pos, renewable.fallingBlock().value().defaultBlockState());
					fallingblockentity.time = 1;
				}
			}
		}
	}
}