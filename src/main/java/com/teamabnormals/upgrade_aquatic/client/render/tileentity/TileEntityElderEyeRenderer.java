package com.teamabnormals.upgrade_aquatic.client.render.tileentity;

import com.teamabnormals.upgrade_aquatic.client.render.model.ModelElderEye;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityElderEyeRenderer extends TileEntityRenderer<TileEntityElderEye> {
	
	private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation(":textures/entity/guardian_beam.png");
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
        
        IBlockState eyeState = te.hasWorld() ? te.getBlockState() : (IBlockState) UABlocks.ELDER_EYE.getDefaultState();
        
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
        if(eyeState.get(BlockElderEye.FACING) == EnumFacing.SOUTH) {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        } else if(eyeState.get(BlockElderEye.FACING) == EnumFacing.EAST) {
        	GlStateManager.translatef((float) x - 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        } else if(eyeState.get(BlockElderEye.FACING) == EnumFacing.NORTH) {
        	GlStateManager.translatef((float) x - 0.5F, (float) y + 1.5F, (float) z + 1.5F);
        } else if(eyeState.get(BlockElderEye.FACING) == EnumFacing.WEST) {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 1.5F);
        } else if (eyeState.get(BlockElderEye.FACING) == EnumFacing.UP) {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.25F);
        } else {
        	GlStateManager.translatef((float) x + 0.5F, (float) y + 1.75F, (float) z + 1.75F);
        }
        GlStateManager.scalef(1.0F, -1.0F, -1.0F);

        float angle = eyeState.get(BlockElderEye.FACING).getHorizontalAngle();
        
        if (Math.abs(angle) > 1.0E-5D) {
        	GlStateManager.translatef(0.5F, 0.5F, 0.5F);
        	if(eyeState.get(BlockElderEye.FACING) == EnumFacing.UP) {
        		GlStateManager.rotatef(-90F, 1.0F, 0.0F, 0.0F);
        	} else if(eyeState.get(BlockElderEye.FACING) == EnumFacing.DOWN) {
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
	
	public void makeBeamForDirection(TileEntity te, EnumFacing facing, int x, int y, int z, float partialTicks) {
		
	}
}
