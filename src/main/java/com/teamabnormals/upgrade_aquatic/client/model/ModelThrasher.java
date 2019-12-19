package com.teamabnormals.upgrade_aquatic.client.model;

import com.teamabnormals.upgrade_aquatic.api.endimator.Endimator;
import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatorEntityModel;
import com.teamabnormals.upgrade_aquatic.api.endimator.EndimatorRendererModel;
import com.teamabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;

import net.minecraft.util.math.MathHelper;

/**
 * ModelThrasher - SmellyModder
 * Created using Tabula 7.0.0
 */
public class ModelThrasher<E extends EntityThrasher> extends EndimatorEntityModel<E> {
	public EndimatorRendererModel neck;
	public EndimatorRendererModel body;
	public EndimatorRendererModel top_jaw;
	public EndimatorRendererModel bottom_jaw;
	public EndimatorRendererModel left_fin;
	public EndimatorRendererModel right_fin;
	public EndimatorRendererModel fin;
	public EndimatorRendererModel tail_holder;
	public EndimatorRendererModel right_fin_2;
	public EndimatorRendererModel left_fin_2;
	public EndimatorRendererModel tail_holder_2;
	public EndimatorRendererModel tail;
	public EndimatorRendererModel tail_linear;
	
	public Endimator endimator = new Endimator();

	public ModelThrasher() {
        this.textureWidth = 100;
        this.textureHeight = 100;
        this.right_fin_2 = new EndimatorRendererModel(this, 33, 16);
        this.right_fin_2.setRotationPoint(-6.0F, 5.0F, 6.0F);
        this.right_fin_2.addBox(-13.0F, 0.0F, -4.0F, 13, 1, 8, 0.0F);
        this.setRotateAngle(right_fin_2, 0.0F, 0.17453292519943295F, -0.20943951023931953F);
        this.top_jaw = new EndimatorRendererModel(this, 0, 0);
        this.top_jaw.setRotationPoint(0.0F, -2.5F, -4.0F);
        this.top_jaw.addBox(-6.0F, -2.5F, -10.0F, 12, 5, 10, 0.0F);
        this.tail = new EndimatorRendererModel(this, 30, 70);
        this.tail.setRotationPoint(0.0F, 0.1F, 8.0F);
        this.tail.addBox(0.0F, -3.0F, 0.0F, 0, 6, 18, 0.0F);
        this.neck = new EndimatorRendererModel(this, 0, 24);
        this.neck.setRotationPoint(0.0F, 17.0F, -10.0F);
        this.neck.addBox(-6.0F, -5.0F, -4.0F, 12, 11, 8, 0.0F);
        this.body = new EndimatorRendererModel(this, 0, 43);
        this.body.setRotationPoint(0.0F, -4.0F, 4.0F);
        this.body.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 13, 0.0F);
        this.fin = new EndimatorRendererModel(this, 50, 50);
        this.fin.setRotationPoint(0.0F, -5.0F, -2.5F);
        this.fin.addBox(0.0F, -7.0F, 0.0F, 0, 7, 12, 0.0F);
        this.setRotateAngle(fin, -0.17453292519943295F, 0.0F, 0.0F);
        this.tail_holder = new EndimatorRendererModel(this, 0, 66);
        this.tail_holder.setRotationPoint(0.0F, 2.5F, 13.0F);
        this.tail_holder.addBox(-4.0F, -1.0F, 0.0F, 8, 7, 8, 0.0F);
        this.tail_linear = new EndimatorRendererModel(this, 30, 70);
        this.tail_linear.setRotationPoint(-3.0F, 0.0F, 0.0F);
        this.tail_linear.addBox(0.0F, 0.0F, 0.0F, 6, 0, 18, 0.0F);
        this.right_fin = new EndimatorRendererModel(this, 0, 16);
        this.right_fin.setRotationPoint(-6.0F, 0.5F, -0.5F);
        this.right_fin.addBox(-10.0F, 0.0F, -2.5F, 10, 1, 6, 0.0F);
        this.setRotateAngle(right_fin, 0.0F, 0.17453292519943295F, -0.20943951023931953F);
        this.bottom_jaw = new EndimatorRendererModel(this, 44, 0);
        this.bottom_jaw.setRotationPoint(0.0F, 4.0F, -4.0F);
        this.bottom_jaw.addBox(-6.0F, -2.0F, -10.0F, 12, 4, 10, 0.0F);
        this.left_fin = new EndimatorRendererModel(this, 0, 16);
        this.left_fin.mirror = true;
        this.left_fin.setRotationPoint(6.0F, 0.5F, -0.5F);
        this.left_fin.addBox(0.0F, 0.0F, -2.5F, 10, 1, 6, 0.0F);
        this.setRotateAngle(left_fin, 0.0F, -0.17453292519943295F, 0.20943951023931953F);
        this.left_fin_2 = new EndimatorRendererModel(this, 33, 16);
        this.left_fin_2.mirror = true;
        this.left_fin_2.setRotationPoint(6.0F, 5.0F, 6.0F);
        this.left_fin_2.addBox(-0.0F, 0.0F, -4.0F, 13, 1, 8, 0.0F);
        this.setRotateAngle(left_fin_2, 0.0F, -0.17453292519943295F, 0.20943951023931953F);
        this.tail_holder_2 = new EndimatorRendererModel(this, 0, 81);
        this.tail_holder_2.setRotationPoint(0.0F, 2.5F, 8.0F);
        this.tail_holder_2.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 8, 0.0F);
        this.body.addChild(this.right_fin_2);
        this.neck.addChild(this.top_jaw);
        this.tail_holder_2.addChild(this.tail);
        this.neck.addChild(this.body);
        this.neck.addChild(this.fin);
        this.body.addChild(this.tail_holder);
        this.tail.addChild(this.tail_linear);
        this.neck.addChild(this.right_fin);
        this.neck.addChild(this.bottom_jaw);
        this.neck.addChild(this.left_fin);
        this.body.addChild(this.left_fin_2);
        this.tail_holder.addChild(this.tail_holder_2);
        
        this.setDefaultBoxValues();
	}

	@Override
	public void render(E entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.animateModel(entity, f, f1, f2, f3, f4, f5);
		this.neck.render(f5);
	}

	public void setRotateAngle(EndimatorRendererModel EndimatorRendererModel, float x, float y, float z) {
		EndimatorRendererModel.rotateAngleX = x;
		EndimatorRendererModel.rotateAngleY = y;
		EndimatorRendererModel.rotateAngleZ = z;
    }
    
	@Override
	public void setRotationAngles(E thrasher, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.revertBoxesToDefaultValues();
		
		float tailAnimation = thrasher.getTailAnimation(ageInTicks - thrasher.ticksExisted);
		float finWaveAnimation = thrasher.getFinAnimation(ageInTicks - thrasher.ticksExisted);
		
		this.neck.rotateAngleX = headPitch * (float) (Math.PI / 180F);
		this.neck.rotateAngleY = netHeadYaw * (float) (Math.PI / 180F);
		
		EndimatorRendererModel[] boxes = new EndimatorRendererModel[] {this.body, this.tail_holder, this.tail_holder_2};
		for(int i = 1; i < boxes.length + 1; i++) {
			boxes[i - 1].rotateAngleY = MathHelper.sin(tailAnimation) * (float) Math.PI * (0.045F * i);
		}
		
		if(thrasher.isMoving() && thrasher.isInWater()) {
			this.tail.rotateAngleZ = (float) MathHelper.sin((float) ((thrasher.ticksExisted + ageInTicks) * 2 * Math.PI * 0.8125D));
		}
		
		this.right_fin.rotateAngleZ = (float) (-MathHelper.cos(finWaveAnimation) * (float) Math.PI * 0.13);
		this.right_fin_2.rotateAngleZ = (float) (-MathHelper.cos(finWaveAnimation - 1.5F) * (float) Math.PI * 0.1);
		this.left_fin.rotateAngleZ = (float) (MathHelper.cos(finWaveAnimation) * (float) Math.PI * 0.13);
		this.left_fin_2.rotateAngleZ = (float) (MathHelper.cos(finWaveAnimation - 1.5F) * (float) Math.PI * 0.1);
	}
	
	@Override
	public void animateModel(E thrasher, float f, float f1, float f2, float f3, float f4, float f5) {
		super.animateModel(thrasher, f, f1, f2, f3, f4, f5);
		this.endimator.updateAnimations(thrasher);
		
		if(thrasher.isAnimationPlaying(EntityThrasher.SNAP_AT_PRAY_ANIMATION)) {
			this.endimator.setAnimationToPlay(EntityThrasher.SNAP_AT_PRAY_ANIMATION);
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, -0.55F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, 0.55F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.0F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
		} else if(thrasher.isAnimationPlaying(EntityThrasher.HURT_ANIMATION)) {
			this.endimator.setAnimationToPlay(EntityThrasher.HURT_ANIMATION);
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, -0.35F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, 0.35F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.0F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
		} else if(thrasher.isAnimationPlaying(EntityThrasher.THRASH_ANIMATION)) {
			this.endimator.setAnimationToPlay(EntityThrasher.THRASH_ANIMATION);
			
			this.endimator.startKeyframe(5);
			
			this.endimator.rotate(this.top_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.neck, 0.0F, 0.75F, 0.0F);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.neck, 0.0F, 0.75F, 0.0F);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, 0.0F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
		} else if(thrasher.isAnimationPlaying(EntityThrasher.SONAR_FIRE_ANIMATION)) {
			this.endimator.setAnimationToPlay(EntityThrasher.SONAR_FIRE_ANIMATION);
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, -0.35F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, 0.35F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
			
			this.endimator.setStaticKeyframe(20);
			
			this.endimator.startKeyframe(5);
			this.endimator.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.endimator.rotate(this.bottom_jaw, 0.0F, 0.0F, 0.0F);
			this.endimator.endKeyframe();
		}
	}
}