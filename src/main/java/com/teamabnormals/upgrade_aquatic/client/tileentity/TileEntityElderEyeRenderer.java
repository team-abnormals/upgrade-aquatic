package com.teamabnormals.upgrade_aquatic.client.tileentity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teamabnormals.upgrade_aquatic.client.model.ModelElderEye;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityElderEyeRenderer extends TileEntityRenderer<TileEntityElderEye> {
	private static final ResourceLocation ELDER_EYE_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/block/guardian_eye.png");
	private static final ResourceLocation ELDER_EYE_DIM_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/block/guardian_eye_dim.png");
	private ModelElderEye model;
	
	public TileEntityElderEyeRenderer() {
		this.model = new ModelElderEye();
	}
	
	@Override
    public void render(TileEntityElderEye te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        
        BlockState eyeState = te.hasWorld() ? te.getBlockState() : (BlockState) UABlocks.ELDER_EYE.getDefaultState();
        
        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(4F, 4F, 1F);
            GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
        	if(eyeState.get(BlockElderEye.ACTIVE)) {
        		this.bindTexture(ELDER_EYE_TEXTURE);
        	} else {
        		this.bindTexture(ELDER_EYE_DIM_TEXTURE);
        	}
        }
        
        GlStateManager.pushMatrix();
        
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(eyeState.get(BlockElderEye.FACING) == Direction.SOUTH) {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        } else if(eyeState.get(BlockElderEye.FACING) == Direction.EAST) {
        	GlStateManager.translatef((float) x - 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        } else if(eyeState.get(BlockElderEye.FACING) == Direction.NORTH) {
        	GlStateManager.translatef((float) x - 0.5F, (float) y + 1.5F, (float) z + 1.5F);
        } else if(eyeState.get(BlockElderEye.FACING) == Direction.WEST) {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 1.5F);
        } else if (eyeState.get(BlockElderEye.FACING) == Direction.UP) {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.25F);
        } else {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 1.75F, (float) z + 1.75F);
        }
        GlStateManager.scalef(1.0F, -1.0F, -1.0F);

        float angle = eyeState.get(BlockElderEye.FACING).getHorizontalAngle();
        
        if (Math.abs(angle) > 1.0E-5D) {
        	GlStateManager.translatef(0.5F, 0.5F, 0.5F);
        	if(eyeState.get(BlockElderEye.FACING) == Direction.UP) {
        		GlStateManager.rotatef(-90F, 1.0F, 0.0F, 0.0F);
        	} else if(eyeState.get(BlockElderEye.FACING) == Direction.DOWN) {
        		GlStateManager.rotatef(90F, 1.0F, 0.0F, 0.0F);
        	} else {
        		GlStateManager.rotatef(angle, 0.0F, 1.0F, 0.0F);
        	}
        	GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
        }
        
        model.renderAll();
        
        GlStateManager.popMatrix();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}
