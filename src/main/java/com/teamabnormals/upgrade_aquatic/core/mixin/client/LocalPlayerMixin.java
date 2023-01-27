package com.teamabnormals.upgrade_aquatic.core.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.ProfilePublicKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin extends AbstractClientPlayer {

	public LocalPlayerMixin(ClientLevel p_i50991_1_, GameProfile p_i50991_2_, @Nullable ProfilePublicKey p_234114_) {
		super(p_i50991_1_, p_i50991_2_, p_234114_);
	}

	@Inject(at = @At(shift = Shift.AFTER, value = "HEAD"), method = "rideTick()V")
	private void tempFixClientDismount(CallbackInfo info) {
		if (this.wantsToStopRiding() && this.isPassenger())
			Minecraft.getInstance().player.input.shiftKeyDown = false;
	}

}