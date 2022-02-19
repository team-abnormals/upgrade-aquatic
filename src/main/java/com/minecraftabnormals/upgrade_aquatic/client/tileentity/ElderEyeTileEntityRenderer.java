package com.minecraftabnormals.upgrade_aquatic.client.tileentity;

import com.minecraftabnormals.upgrade_aquatic.client.model.ElderEyeModel;
import com.minecraftabnormals.upgrade_aquatic.common.blocks.ElderEyeBlock;
import com.minecraftabnormals.upgrade_aquatic.common.tileentities.ElderEyeTileEntity;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.minecraftabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import com.mojang.math.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElderEyeTileEntityRenderer extends BlockEntityRenderer<ElderEyeTileEntity> {
	private static final ResourceLocation ELDER_EYE_TEXTURE = new ResourceLocation(UpgradeAquatic.MOD_ID + ":textures/tile/guardian_eye.png");
	private static final ResourceLocation ELDER_EYE_DIM_TEXTURE = new ResourceLocation(UpgradeAquatic.MOD_ID + ":textures/tile/guardian_eye_dim.png");
	private final ElderEyeModel model;

	public ElderEyeTileEntityRenderer(BlockEntityRenderDispatcher renderDispatcher) {
		super(renderDispatcher);
		this.model = new ElderEyeModel();
	}

	@Override
	public void render(ElderEyeTileEntity te, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStack.pushPose();
		matrixStack.translate(0.5D, 1.5D, 0.5D);

		BlockState eyeState = te.hasLevel() ? te.getBlockState() : UABlocks.ELDER_EYE.get().defaultBlockState();
		Direction facing = eyeState.getValue(ElderEyeBlock.FACING);

		if (facing == Direction.DOWN) {
			matrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
			matrixStack.translate(0.0F, 1.25F, 0.75F);
		} else if (facing == Direction.UP) {
			matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
			matrixStack.translate(0.0F, 1.25F, -1.25F);
		} else if (facing == Direction.NORTH) {
			matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		} else if (facing == Direction.EAST) {
			matrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
		} else if (facing == Direction.WEST) {
			matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
		}

		matrixStack.scale(1.0F, -1.0F, -1.0F);

		VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutoutNoCull(this.getEyeTexture(eyeState.getValue(ElderEyeBlock.ACTIVE))));
		this.model.render(matrixStack, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStack.popPose();
	}

	private ResourceLocation getEyeTexture(boolean active) {
		return active ? ELDER_EYE_TEXTURE : ELDER_EYE_DIM_TEXTURE;
	}
}
