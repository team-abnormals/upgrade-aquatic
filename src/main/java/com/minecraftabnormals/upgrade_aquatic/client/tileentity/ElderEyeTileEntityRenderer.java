package com.minecraftabnormals.upgrade_aquatic.client.tileentity;

import com.minecraftabnormals.upgrade_aquatic.client.model.ElderEyeModel;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.ElderEyeBlock;
import com.minecraftabnormals.upgrade_aquatic.common.tileentities.ElderEyeTileEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElderEyeTileEntityRenderer extends TileEntityRenderer<ElderEyeTileEntity> {
	private static final ResourceLocation ELDER_EYE_TEXTURE = new ResourceLocation(UpgradeAquatic.MODID + ":textures/tile/guardian_eye.png");
	private static final ResourceLocation ELDER_EYE_DIM_TEXTURE = new ResourceLocation(UpgradeAquatic.MODID + ":textures/tile/guardian_eye_dim.png");
	private ElderEyeModel model;
	
	public ElderEyeTileEntityRenderer(TileEntityRendererDispatcher renderDispatcher) {
		super(renderDispatcher);
		this.model = new ElderEyeModel();
	}
	
	@Override
	public void render(ElderEyeTileEntity te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStack.push();
		matrixStack.translate(0.5D, 1.5D, 0.5D);
		
		BlockState eyeState = te.hasWorld() ? te.getBlockState() : UABlocks.ELDER_EYE.get().getDefaultState();
		Direction facing = eyeState.get(ElderEyeBlock.FACING);
		
		if(facing == Direction.DOWN) {
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0F));
			matrixStack.translate(0.0F, 1.25F, 0.75F);
		} else if(facing == Direction.UP){
			matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0F));
			matrixStack.translate(0.0F, 1.25F, -1.25F);
		} else if(facing == Direction.NORTH) {
			matrixStack.rotate(Vector3f.YP.rotationDegrees(180.0F));
		} else if(facing == Direction.EAST) {
			matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F));
		} else if(facing == Direction.WEST) {
			matrixStack.rotate(Vector3f.YP.rotationDegrees(-90.0F));
		}
		
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(this.getEyeTexture(eyeState.get(ElderEyeBlock.ACTIVE))));
		this.model.render(matrixStack, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStack.pop();
	}
	
	private ResourceLocation getEyeTexture(boolean active) {
		return active ? ELDER_EYE_TEXTURE : ELDER_EYE_DIM_TEXTURE;
	}
}
