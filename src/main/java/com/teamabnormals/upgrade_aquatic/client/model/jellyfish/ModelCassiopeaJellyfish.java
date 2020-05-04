package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.upgrade_aquatic.api.UpgradeAquaticAPI.ClientInfo;
import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatorEntityModel;
import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatorModelRenderer;
import com.teamabnormals.upgrade_aquatic.common.entities.jellyfish.EntityCassiopeaJellyfish;

/**
 * ModelCassiopeaJellyfish - SnakeBlock
 * Created using Tabula 7.0.0
 */
public class ModelCassiopeaJellyfish<E extends EntityCassiopeaJellyfish> extends EndimatorEntityModel<E> {
    public EndimatorModelRenderer cap;
    public EndimatorModelRenderer cross1;
    public EndimatorModelRenderer cross2;
    public EndimatorModelRenderer thing;
    public EndimatorModelRenderer tentacleT;
    public EndimatorModelRenderer tentacleE;
    public EndimatorModelRenderer tentacleN;
    public EndimatorModelRenderer tentacleW;

    public ModelCassiopeaJellyfish() {
        this.textureWidth = 44;
        this.textureHeight = 52;
        this.tentacleW = new EndimatorModelRenderer(this, 0, 24);
        this.tentacleW.setRotationPoint(4.5F, 0.5F, 0.0F);
        this.tentacleW.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
        this.setRotateAngle(tentacleW, 0.0F, 1.5707963267948966F, 0.0F);
        this.cross2 = new EndimatorModelRenderer(this, 18, 24);
        this.cross2.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.cross2.addBox(-3.5F, 0.0F, 0.0F, 7, 13, 0, 0.0F);
        this.setRotateAngle(cross2, 0.0F, 1.5707963267948966F, 0.0F);
        this.thing = new EndimatorModelRenderer(this, 0, 14);
        this.thing.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.thing.addBox(-4.5F, -0.5F, -4.5F, 9, 1, 9, 0.0F);
        this.tentacleN = new EndimatorModelRenderer(this, 0, 32);
        this.tentacleN.setRotationPoint(0.0F, 0.5F, -4.5F);
        this.tentacleN.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
        this.setRotateAngle(tentacleN, 0.0F, -3.141592653589793F, 0.0F);
        this.cap = new EndimatorModelRenderer(this, 0, 0);
        this.cap.setRotationPoint(0.0F, 21.5F, 0.0F);
        this.cap.addBox(-5.5F, -1.5F, -5.5F, 11, 3, 11, 0.0F);
        this.cross1 = new EndimatorModelRenderer(this, 18, 24);
        this.cross1.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.cross1.addBox(-3.5F, 0.0F, 0.0F, 7, 13, 0, 0.0F);
        this.tentacleE = new EndimatorModelRenderer(this, 0, 24);
        this.tentacleE.setRotationPoint(-4.5F, 0.5F, 0.0F);
        this.tentacleE.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
        this.setRotateAngle(tentacleE, 0.0F, -1.5707963267948966F, 0.0F);
        this.tentacleT = new EndimatorModelRenderer(this, 0, 32);
        this.tentacleT.setRotationPoint(0.0F, 0.5F, 4.5F);
        this.tentacleT.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
        this.thing.addChild(this.tentacleW);
        this.cap.addChild(this.cross2);
        this.cap.addChild(this.thing);
        this.thing.addChild(this.tentacleN);
        this.cap.addChild(this.cross1);
        this.thing.addChild(this.tentacleE);
        this.thing.addChild(this.tentacleT);
        
        this.createScaleController();
        this.setDefaultBoxValues();
    }
    
    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
    	this.animateModel(this.entity);
    	this.cap.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
    
    @Override
    public void setRotationAngles(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    	super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    	
    	float[] rotations = entity.getRotationController().getRotations(ClientInfo.getPartialTicks());
    	this.cap.rotateAngleY = (float) Math.toRadians(rotations[0]);
    	this.cap.rotateAngleX = (float) Math.toRadians(rotations[1]);
    }
    
    @Override
    public void animateModel(E jellyfish) {
    	this.endimator.updateAnimations(jellyfish);
    	
    	if(jellyfish.isEndimationPlaying(EntityCassiopeaJellyfish.SWIM_ANIMATION)) {
    		this.endimator.setAnimationToPlay(EntityCassiopeaJellyfish.SWIM_ANIMATION);
    		
    		this.endimator.startKeyframe(10);
    		this.endimator.rotate(this.tentacleN, 0.3F, 0.0F, 0.0F);
    		this.endimator.rotate(this.tentacleE, 0.3F, 0.0F, 0.0F);
    		this.endimator.rotate(this.tentacleT, 0.3F, 0.0F, 0.0F);
    		this.endimator.rotate(this.tentacleW, 0.3F, 0.0F, 0.0F);
    		
    		this.endimator.move(this.getScaleController(), 0.3F, -0.1F, 0.3F);
    		this.endimator.move(this.cap, 0.0F, -0.1F, 0.0F);
    		this.endimator.endKeyframe();
    		
    		this.endimator.resetKeyframe(10);
    	} else if(jellyfish.isEndimationPlaying(EntityCassiopeaJellyfish.BOOST_ANIMATION)) {
    		this.endimator.setAnimationToPlay(EntityCassiopeaJellyfish.BOOST_ANIMATION);
    		
    		this.endimator.startKeyframe(10);
    		this.endimator.rotate(this.tentacleN, 0.3F, 0.0F, 0.0F);
    		this.endimator.rotate(this.tentacleE, 0.3F, 0.0F, 0.0F);
    		this.endimator.rotate(this.tentacleT, 0.3F, 0.0F, 0.0F);
    		this.endimator.rotate(this.tentacleW, 0.3F, 0.0F, 0.0F);
    		
    		this.endimator.move(this.getScaleController(), 0.3F, -0.1F, 0.3F);
    		this.endimator.move(this.cap, 0.0F, -0.1F, 0.0F);
    		this.endimator.endKeyframe();
    		
    		this.endimator.resetKeyframe(10);
    	}
    	
    	this.cap.setShouldScaleChildren(false);
    	this.cap.setScale(this.getScaleController().rotationPointX, this.getScaleController().rotationPointY, this.getScaleController().rotationPointZ);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(EndimatorModelRenderer EndimatorModelRenderer, float x, float y, float z) {
        EndimatorModelRenderer.rotateAngleX = x;
        EndimatorModelRenderer.rotateAngleY = y;
        EndimatorModelRenderer.rotateAngleZ = z;
    }
}
