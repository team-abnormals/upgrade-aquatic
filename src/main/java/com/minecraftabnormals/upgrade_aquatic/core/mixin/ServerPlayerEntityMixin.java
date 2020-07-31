package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecraftabnormals.upgrade_aquatic.common.blocks.BedrollBlock;
import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

	public ServerPlayerEntityMixin(World world, BlockPos pos, GameProfile profile) {
		super(world, pos, profile);
	}
	
	@Inject(
		at = @At("HEAD"),
		method = "func_241153_a_(Lnet/minecraft/util/RegistryKey;Lnet/minecraft/util/math/BlockPos;ZZ)V",
		remap = false,
		cancellable = true
	)
	private void preventBedrollSpawnPoint(RegistryKey<World> key, @Nullable BlockPos pos, boolean p_234567_2_, boolean p_234567_3_, CallbackInfo info) {
		if (pos != null && this.world.getBlockState(pos).getBlock() instanceof BedrollBlock) info.cancel();
	}

}