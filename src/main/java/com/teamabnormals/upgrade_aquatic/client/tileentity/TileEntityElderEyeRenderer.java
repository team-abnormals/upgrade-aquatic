package com.teamabnormals.upgrade_aquatic.client.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.upgrade_aquatic.client.model.ModelElderEye;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityElderEyeRenderer extends TileEntityRenderer<TileEntityElderEye> {
	private static final ResourceLocation ELDER_EYE_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/block/guardian_eye.png");
	private static final ResourceLocation ELDER_EYE_DIM_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/block/guardian_eye_dim.png");
	private ModelElderEye model;
	
	public TileEntityElderEyeRenderer(TileEntityRendererDispatcher renderDispatcher) {
		super(renderDispatcher);
		this.model = new ModelElderEye();
	}
	
	@Override
	public void render(TileEntityElderEye te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStack.push();
		matrixStack.translate(0.5D, 1.5D, 1.5D);
		
		BlockState eyeState = te.hasWorld() ? te.getBlockState() : UABlocks.ELDER_EYE.get().getDefaultState();
		Direction facing = eyeState.get(BlockElderEye.FACING);
		
		if(facing == Direction.DOWN) {
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			matrixStack.translate(0.0F, 1.125F, 1.0F);
		} else if(facing == Direction.UP){
			matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0F));
			matrixStack.translate(0.0F, 1.125F, -1.0F);
		} else if(facing == Direction.NORTH) {
			matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
		} else if(facing == Direction.EAST) {
			matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
		} else if(facing == Direction.WEST) {
			matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		}
		
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(this.getEyeTexture(eyeState.get(BlockElderEye.ACTIVE))));
		this.model.render(matrixStack, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStack.pop();
	}
	
	private ResourceLocation getEyeTexture(boolean active) {
		return active ? ELDER_EYE_TEXTURE : ELDER_EYE_DIM_TEXTURE;
	}
}
