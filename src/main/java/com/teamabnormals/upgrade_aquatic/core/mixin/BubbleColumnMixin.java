package com.teamabnormals.upgrade_aquatic.core.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.teamabnormals.upgrade_aquatic.core.UAHooks;

import net.minecraft.block.BlockState;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

@Mixin(BubbleColumnBlock.class)
public class BubbleColumnMixin {
	
    @Inject(at = @At("HEAD"), method = "tick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V")
    private void tick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
    	UAHooks.addBubbleColumnBehaviors(state, world, pos);
    }
}