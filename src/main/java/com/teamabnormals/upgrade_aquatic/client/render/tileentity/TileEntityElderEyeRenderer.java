package com.teamabnormals.upgrade_aquatic.client.render.tileentity;

import com.teamabnormals.upgrade_aquatic.client.render.model.ModelElderEye;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockElderEye;
import com.teamabnormals.upgrade_aquatic.common.tileentities.TileEntityElderEye;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.upgrade_aquatic.core.util.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityElderEyeRenderer extends TileEntityRenderer<TileEntityElderEye> {
	
	private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation(":textures/entity/guardian_beam.png");
	private static final ResourceLocation ELDER_EYE_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/block/guardian_eye.png");
	private static final ResourceLocation ELDER_EYE_DIM_TEXTURE = new ResourceLocation(Reference.MODID + ":textures/block/guardian_eye_dim.png");
	private ModelElderEye model;
	public int time;
	
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
        
        //this.makeBeamForDirection(te, eyeState.get(BlockElderEye.FACING), (int) x, (int) y, (int) z, partialTicks);
        
        GlStateManager.popMatrix();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
        time++;
        if(time >= 60) {
        	time = 0;
        }
    }
	
	//WIP EXTREME; I can't do gl lol
	public void makeBeamForDirection(TileEntityElderEye te, EnumFacing facing, int x, int y, int z, float partialTicks) {
		Vec3d tilePos = new Vec3d(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
		Vec3d facingBlockPos = new Vec3d(te.getBlockPosForRange(facing).getX(), te.getBlockPosForRange(facing).getY(), te.getBlockPosForRange(facing).getZ());;
		Vec3d relativePos = tilePos.subtract(facingBlockPos).normalize();
		
		float f = (time + partialTicks) / 30;
		
		GlStateManager.texParameteri(3553, 10242, 10497);
	    GlStateManager.texParameteri(3553, 10243, 10497);
	    GlStateManager.disableLighting();
	    GlStateManager.disableCull();
	    GlStateManager.disableBlend();
	    GlStateManager.depthMask(true);
	    OpenGlHelper.glMultiTexCoord2f(OpenGlHelper.GL_TEXTURE1, 240, 240);
	    GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        
        this.bindTexture(GUARDIAN_BEAM_TEXTURE);
        
        float f2 = (float)te.getWorld().getGameTime() + partialTicks;
        float f3 = f2 * 0.5F % 1.0F;
        float f4 = te.getPos().getY();
        
        GlStateManager.pushMatrix();
        GlStateManager.scalef(1.5F, 1.5F, 1.5F);
        GlStateManager.translatef((float)x, (float)y + f4, (float)z);
        
        double d0 = relativePos.length() + 1.0D;
        float f5 = (float)Math.acos(relativePos.y);
        float f6 = (float)Math.atan2(relativePos.z, relativePos.x);
        
        GlStateManager.rotatef((((float)Math.PI / 2F) + -f6) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(f5 * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
        
        double d1 = (double)f2 * 0.05D * -1.5D;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        float f7 = f * f;
        int j = 64 + (int)(f7 * 191.0F);
        int k = 32 + (int)(f7 * 191.0F);
        int l = 128 - (int)(f7 * 64.0F);
        double d4 = 0.0D + Math.cos(d1 + 2.356194490192345D) * 0.282D;
        double d5 = 0.0D + Math.sin(d1 + 2.356194490192345D) * 0.282D;
        double d6 = 0.0D + Math.cos(d1 + (Math.PI / 4D)) * 0.282D;
        double d7 = 0.0D + Math.sin(d1 + (Math.PI / 4D)) * 0.282D;
        double d8 = 0.0D + Math.cos(d1 + 3.9269908169872414D) * 0.282D;
        double d9 = 0.0D + Math.sin(d1 + 3.9269908169872414D) * 0.282D;
        double d10 = 0.0D + Math.cos(d1 + 5.497787143782138D) * 0.282D;
        double d11 = 0.0D + Math.sin(d1 + 5.497787143782138D) * 0.282D;
        double d12 = 0.0D + Math.cos(d1 + Math.PI) * 0.2D;
        double d13 = 0.0D + Math.sin(d1 + Math.PI) * 0.2D;
        double d14 = 0.0D + Math.cos(d1 + 0.0D) * 0.2D;
        double d15 = 0.0D + Math.sin(d1 + 0.0D) * 0.2D;
        double d16 = 0.0D + Math.cos(d1 + (Math.PI / 2D)) * 0.2D;
        double d17 = 0.0D + Math.sin(d1 + (Math.PI / 2D)) * 0.2D;
        double d18 = 0.0D + Math.cos(d1 + (Math.PI * 3D / 2D)) * 0.2D;
        double d19 = 0.0D + Math.sin(d1 + (Math.PI * 3D / 2D)) * 0.2D;
        double d22 = (double)(-1.0F + f3);
        double d23 = d0 * 2.5D + d22;
        
        bufferbuilder.pos(d12, d0, d13).tex(0.4999D, d23).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d12, 0.0D, d13).tex(0.4999D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d14, 0.0D, d15).tex(0.0D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d14, d0, d15).tex(0.0D, d23).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d16, d0, d17).tex(0.4999D, d23).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d16, 0.0D, d17).tex(0.4999D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d18, 0.0D, d19).tex(0.0D, d22).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d18, d0, d19).tex(0.0D, d23).color(j, k, l, 255).endVertex();
        
        double d24 = 0.0D;

        bufferbuilder.pos(d4, d0, d5).tex(0.5D, d24 + 0.5D).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d6, d0, d7).tex(1.0D, d24 + 0.5D).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d10, d0, d11).tex(1.0D, d24).color(j, k, l, 255).endVertex();
        bufferbuilder.pos(d8, d0, d9).tex(0.5D, d24).color(j, k, l, 255).endVertex();
        tessellator.draw();
        
        GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		
        GlStateManager.popMatrix();
	}
}
