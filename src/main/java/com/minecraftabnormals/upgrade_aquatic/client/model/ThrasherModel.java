package com.minecraftabnormals.upgrade_aquatic.client.model;

import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatorEntityModel;
import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatorModelRenderer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;

/**
 * ModelThrasher - SmellyModder
 * Created using Tabula 7.0.0
 */
public class ThrasherModel<E extends ThrasherEntity> extends EndimatorEntityModel<E> {
	public EndimatorModelRenderer neck;
	public EndimatorModelRenderer body;
	public EndimatorModelRenderer top_jaw;
	public EndimatorModelRenderer bottom_jaw;
	public EndimatorModelRenderer left_fin;
	public EndimatorModelRenderer right_fin;
	public EndimatorModelRenderer fin;
	public EndimatorModelRenderer tail_holder;
	public EndimatorModelRenderer right_fin_2;
	public EndimatorModelRenderer left_fin_2;
	public EndimatorModelRenderer tail_holder_2;
	public EndimatorModelRenderer tail;
	public EndimatorModelRenderer tail_linear;

	public ThrasherModel() {
        this.texWidth = 100;
        this.texHeight = 100;
        this.right_fin_2 = new EndimatorModelRenderer(this, 33, 16);
        this.right_fin_2.setPos(-6.0F, 5.0F, 6.0F);
        this.right_fin_2.addBox(-13.0F, 0.0F, -4.0F, 13, 1, 8, 0.0F);
        this.setRotateAngle(right_fin_2, 0.0F, 0.17453292519943295F, -0.20943951023931953F);
        this.top_jaw = new EndimatorModelRenderer(this, 0, 0);
        this.top_jaw.setPos(0.0F, -2.5F, -4.0F);
        this.top_jaw.addBox(-6.0F, -2.5F, -10.0F, 12, 5, 10, 0.0F);
        this.tail = new EndimatorModelRenderer(this, 30, 70);
        this.tail.setPos(0.0F, 0.1F, 8.0F);
        this.tail.addBox(0.0F, -3.0F, 0.0F, 0, 6, 18, 0.0F);
        this.neck = new EndimatorModelRenderer(this, 0, 24);
        this.neck.setPos(0.0F, 17.0F, -10.0F);
        this.neck.addBox(-6.0F, -5.0F, -4.0F, 12, 11, 8, 0.0F);
        this.body = new EndimatorModelRenderer(this, 0, 43);
        this.body.setPos(0.0F, -4.0F, 4.0F);
        this.body.addBox(-6.0F, 0.0F, 0.0F, 12, 10, 13, 0.0F);
        this.fin = new EndimatorModelRenderer(this, 50, 50);
        this.fin.setPos(0.0F, -5.0F, -2.5F);
        this.fin.addBox(0.0F, -7.0F, 0.0F, 0, 7, 12, 0.0F);
        this.setRotateAngle(fin, -0.17453292519943295F, 0.0F, 0.0F);
        this.tail_holder = new EndimatorModelRenderer(this, 0, 66);
        this.tail_holder.setPos(0.0F, 2.5F, 13.0F);
        this.tail_holder.addBox(-4.0F, -1.0F, 0.0F, 8, 7, 8, 0.0F);
        this.tail_linear = new EndimatorModelRenderer(this, 30, 70);
        this.tail_linear.setPos(-3.0F, 0.0F, 0.0F);
        this.tail_linear.addBox(0.0F, 0.0F, 0.0F, 6, 0, 18, 0.0F);
        this.right_fin = new EndimatorModelRenderer(this, 0, 16);
        this.right_fin.setPos(-6.0F, 0.5F, -0.5F);
        this.right_fin.addBox(-10.0F, 0.0F, -2.5F, 10, 1, 6, 0.0F);
        this.setRotateAngle(right_fin, 0.0F, 0.17453292519943295F, -0.20943951023931953F);
        this.bottom_jaw = new EndimatorModelRenderer(this, 44, 0);
        this.bottom_jaw.setPos(0.0F, 4.0F, -4.0F);
        this.bottom_jaw.addBox(-6.0F, -2.0F, -10.0F, 12, 4, 10, 0.0F);
        this.left_fin = new EndimatorModelRenderer(this, 0, 16);
        this.left_fin.mirror = true;
        this.left_fin.setPos(6.0F, 0.5F, -0.5F);
        this.left_fin.addBox(0.0F, 0.0F, -2.5F, 10, 1, 6, 0.0F);
        this.setRotateAngle(left_fin, 0.0F, -0.17453292519943295F, 0.20943951023931953F);
        this.left_fin_2 = new EndimatorModelRenderer(this, 33, 16);
        this.left_fin_2.mirror = true;
        this.left_fin_2.setPos(6.0F, 5.0F, 6.0F);
        this.left_fin_2.addBox(-0.0F, 0.0F, -4.0F, 13, 1, 8, 0.0F);
        this.setRotateAngle(left_fin_2, 0.0F, -0.17453292519943295F, 0.20943951023931953F);
        this.tail_holder_2 = new EndimatorModelRenderer(this, 0, 81);
        this.tail_holder_2.setPos(0.0F, 2.5F, 8.0F);
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
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.animateModel(this.entity);
		this.neck.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotateAngle(EndimatorModelRenderer EndimatorRendererModel, float x, float y, float z) {
		EndimatorRendererModel.xRot = x;
		EndimatorRendererModel.yRot = y;
		EndimatorRendererModel.zRot = z;
    }
	
	@Override
	public void setupAnim(E thrasher, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(thrasher, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.revertBoxesToDefaultValues();
		
		float tailAnimation = thrasher.getTailAnimation(ageInTicks - thrasher.tickCount);
		float finWaveAnimation = thrasher.getFinAnimation(ageInTicks - thrasher.tickCount);
		
		this.neck.xRot = headPitch * (float) (Math.PI / 180F);
		this.neck.yRot = netHeadYaw * (float) (Math.PI / 180F);
		
		EndimatorModelRenderer[] boxes = new EndimatorModelRenderer[] {this.body, this.tail_holder, this.tail_holder_2};
		for(int i = 1; i < boxes.length + 1; i++) {
			boxes[i - 1].yRot = MathHelper.sin(tailAnimation) * (float) Math.PI * (0.045F * i);
		}
		
		if(thrasher.isMoving() && thrasher.isInWater()) {
			this.tail.zRot = (float) MathHelper.sin((float) ((thrasher.tickCount + ageInTicks) * 2 * Math.PI * 0.8125D));
		}
		
		this.right_fin.zRot = (float) (-MathHelper.cos(finWaveAnimation) * (float) Math.PI * 0.13);
		this.right_fin_2.zRot = (float) (-MathHelper.cos(finWaveAnimation - 1.5F) * (float) Math.PI * 0.1);
		this.left_fin.zRot = (float) (MathHelper.cos(finWaveAnimation) * (float) Math.PI * 0.13);
		this.left_fin_2.zRot = (float) (MathHelper.cos(finWaveAnimation - 1.5F) * (float) Math.PI * 0.1);
	}
	
	@Override
	public void animateModel(E thrasher) {
		super.animateModel(thrasher);
		
		if(thrasher.isEndimationPlaying(ThrasherEntity.SNAP_AT_PRAY_ANIMATION)) {
			this.setEndimationToPlay(ThrasherEntity.SNAP_AT_PRAY_ANIMATION);
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, -0.55F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, 0.55F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.0F, 0.0F, 0.0F);
			this.endKeyframe();
		} else if(thrasher.isEndimationPlaying(ThrasherEntity.HURT_ANIMATION)) {
			this.setEndimationToPlay(ThrasherEntity.HURT_ANIMATION);
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, -0.35F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, 0.35F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.0F, 0.0F, 0.0F);
			this.endKeyframe();
		} else if(thrasher.isEndimationPlaying(ThrasherEntity.THRASH_ANIMATION)) {
			this.setEndimationToPlay(ThrasherEntity.THRASH_ANIMATION);
			
			this.startKeyframe(5);
			
			this.rotate(this.top_jaw, -0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, 0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.neck, 0.0F, 0.75F, 0.0F);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.neck, 0.0F, 0.75F, 0.0F);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.15F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, -0.15F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, 0.0F, 0.0F, 0.0F);
			this.endKeyframe();
		} else if(thrasher.isEndimationPlaying(ThrasherEntity.SONAR_FIRE_ANIMATION)) {
			this.setEndimationToPlay(ThrasherEntity.SONAR_FIRE_ANIMATION);
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, -0.35F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, 0.35F, 0.0F, 0.0F);
			this.endKeyframe();
			
			this.setStaticKeyframe(20);
			
			this.startKeyframe(5);
			this.rotate(this.top_jaw, 0.0F, 0.0F, 0.0F);
			this.rotate(this.bottom_jaw, 0.0F, 0.0F, 0.0F);
			this.endKeyframe();
		}
	}
}