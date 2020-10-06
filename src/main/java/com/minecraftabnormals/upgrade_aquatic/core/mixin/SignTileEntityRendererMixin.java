package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.upgrade_aquatic.api.IGlowable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignTileEntityRenderer.class)
public class SignTileEntityRendererMixin {
	private SignTileEntity te;

	@Inject(method = "render", at = @At("HEAD"))
	public void captureTe(SignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn, CallbackInfo ci) {
		this.te = tileEntityIn;
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;func_238416_a_(Lnet/minecraft/util/text/ITextProperties;FFIZLnet/minecraft/util/math/vector/Matrix4f;Lnet/minecraft/client/renderer/IRenderTypeBuffer;ZII)I"))
	public int redirectFont(FontRenderer fontRenderer, ITextProperties properties, float x, float y, int color, boolean dropShadow, Matrix4f matrix, IRenderTypeBuffer buffer, boolean transparent, int backgroundColor, int packedLight) {
		return fontRenderer.func_238416_a_(properties, x, y, color, dropShadow, matrix, buffer, transparent, backgroundColor, ((IGlowable)this.te).isGlowing() ? 15728880 : packedLight);
	}
}
