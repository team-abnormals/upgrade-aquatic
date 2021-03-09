package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import com.minecraftabnormals.abnormals_core.client.tile.AbnormalsSignTileEntityRenderer;
import com.minecraftabnormals.abnormals_core.common.tileentity.AbnormalsSignTileEntity;
import com.minecraftabnormals.upgrade_aquatic.api.IGlowable;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbnormalsSignTileEntityRenderer.class)
public class AbnormalsSignTileEntityRendererMixin {
	private AbnormalsSignTileEntity te;

	@Inject(method = "render", at = @At("HEAD"), remap = false)
	public void captureTe(AbnormalsSignTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn, CallbackInfo ci) {
		this.te = tileEntityIn;
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;func_238416_a_(Lnet/minecraft/util/IReorderingProcessor;FFIZLnet/minecraft/util/math/vector/Matrix4f;Lnet/minecraft/client/renderer/IRenderTypeBuffer;ZII)I"))
	public int redirectFont(FontRenderer fontRenderer, IReorderingProcessor processor, float x, float y, int color, boolean dropShadow, Matrix4f matrix, IRenderTypeBuffer buffer, boolean transparent, int backgroundColor, int packedLight) {
		return fontRenderer.func_238416_a_(processor, x, y, color, dropShadow, matrix, buffer, transparent, backgroundColor, ((IGlowable)this.te).isGlowing() ? 15728880 : packedLight);
	}
}
