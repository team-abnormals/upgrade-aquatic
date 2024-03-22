package com.teamabnormals.upgrade_aquatic.core.mixin.client;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin extends AbstractClientPlayer {

	public LocalPlayerMixin(ClientLevel p_250460_, GameProfile p_249912_) {
		super(p_250460_, p_249912_);
	}

	@Inject(at = @At(shift = Shift.AFTER, value = "HEAD"), method = "rideTick()V")
	private void tempFixClientDismount(CallbackInfo info) {
		if (this.wantsToStopRiding() && this.isPassenger())
			Minecraft.getInstance().player.input.shiftKeyDown = false;
	}

}