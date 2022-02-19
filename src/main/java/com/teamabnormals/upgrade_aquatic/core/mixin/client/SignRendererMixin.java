package com.teamabnormals.upgrade_aquatic.core.mixin.client;

import com.teamabnormals.upgrade_aquatic.api.IGlowable;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignRenderer.class)
public class SignRendererMixin {
	private SignBlockEntity te;

	@Inject(method = "render", at = @At("HEAD"))
	public void captureTe(SignBlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn, CallbackInfo ci) {
		this.te = tileEntityIn;
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/util/FormattedCharSequence;FFIZLcom/mojang/math/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;ZII)I"))
	public int redirectFont(Font fontRenderer, FormattedCharSequence processor, float x, float y, int color, boolean dropShadow, Matrix4f matrix, MultiBufferSource buffer, boolean transparent, int backgroundColor, int packedLight) {
		return fontRenderer.drawInBatch(processor, x, y, color, dropShadow, matrix, buffer, transparent, backgroundColor, ((IGlowable) this.te).isGlowing() ? 15728880 : packedLight);
	}
}
