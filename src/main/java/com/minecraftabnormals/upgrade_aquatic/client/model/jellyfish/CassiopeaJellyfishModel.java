package com.minecraftabnormals.upgrade_aquatic.client.model.jellyfish;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorModelRenderer;
import com.minecraftabnormals.upgrade_aquatic.common.entities.jellyfish.CassiopeaJellyfishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

/**
 * ModelCassiopeaJellyfish - SnakeBlock
 * Created using Tabula 7.0.0
 */
public class CassiopeaJellyfishModel<E extends CassiopeaJellyfishEntity> extends EndimatorEntityModel<E> {
	public EndimatorModelRenderer cap;
	public EndimatorModelRenderer cross1;
	public EndimatorModelRenderer cross2;
	public EndimatorModelRenderer thing;
	public EndimatorModelRenderer tentacleT;
	public EndimatorModelRenderer tentacleE;
	public EndimatorModelRenderer tentacleN;
	public EndimatorModelRenderer tentacleW;

	public CassiopeaJellyfishModel() {
		this.texWidth = 44;
		this.texHeight = 52;
		this.tentacleW = new EndimatorModelRenderer(this, 0, 24);
		this.tentacleW.setPos(4.5F, 0.5F, 0.0F);
		this.tentacleW.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
		this.setRotateAngle(tentacleW, 0.0F, 1.5707963267948966F, 0.0F);
		this.cross2 = new EndimatorModelRenderer(this, 18, 24);
		this.cross2.setPos(0.0F, 1.5F, 0.0F);
		this.cross2.addBox(-3.5F, 0.0F, 0.0F, 7, 13, 0, 0.0F);
		this.setRotateAngle(cross2, 0.0F, 1.5707963267948966F, 0.0F);
		this.thing = new EndimatorModelRenderer(this, 0, 14);
		this.thing.setPos(0.0F, 2.0F, 0.0F);
		this.thing.addBox(-4.5F, -0.5F, -4.5F, 9, 1, 9, 0.0F);
		this.tentacleN = new EndimatorModelRenderer(this, 0, 32);
		this.tentacleN.setPos(0.0F, 0.5F, -4.5F);
		this.tentacleN.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
		this.setRotateAngle(tentacleN, 0.0F, -3.141592653589793F, 0.0F);
		this.cap = new EndimatorModelRenderer(this, 0, 0);
		this.cap.setPos(0.0F, 21.5F, 0.0F);
		this.cap.addBox(-5.5F, -1.5F, -5.5F, 11, 3, 11, 0.0F);
		this.cross1 = new EndimatorModelRenderer(this, 18, 24);
		this.cross1.setPos(0.0F, 1.5F, 0.0F);
		this.cross1.addBox(-3.5F, 0.0F, 0.0F, 7, 13, 0, 0.0F);
		this.tentacleE = new EndimatorModelRenderer(this, 0, 24);
		this.tentacleE.setPos(-4.5F, 0.5F, 0.0F);
		this.tentacleE.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
		this.setRotateAngle(tentacleE, 0.0F, -1.5707963267948966F, 0.0F);
		this.tentacleT = new EndimatorModelRenderer(this, 0, 32);
		this.tentacleT.setPos(0.0F, 0.5F, 4.5F);
		this.tentacleT.addBox(-4.5F, 0.0F, 0.0F, 9, 7, 0, 0.0F);
		this.thing.addChild(this.tentacleW);
		this.cap.addChild(this.cross2);
		this.cap.addChild(this.thing);
		this.thing.addChild(this.tentacleN);
		this.cap.addChild(this.cross1);
		this.thing.addChild(this.tentacleE);
		this.thing.addChild(this.tentacleT);

		this.setDefaultBoxValues();
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.animateModel(this.entity);
		this.cap.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float[] rotations = entity.getRotationController().getRotations(ClientInfo.getPartialTicks());
		this.cap.yRot = (float) Math.toRadians(rotations[0]);
		this.cap.xRot = (float) Math.toRadians(rotations[1]);
	}

	@Override
	public void animateModel(E jellyfish) {
		super.animateModel(jellyfish);

		if (jellyfish.isEndimationPlaying(CassiopeaJellyfishEntity.SWIM_ANIMATION)) {
			this.setEndimationToPlay(CassiopeaJellyfishEntity.SWIM_ANIMATION);

			this.startKeyframe(5);
			this.rotate(this.tentacleN, 0.25F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, 0.25F, 0.0F, 0.0F);
			this.rotate(this.tentacleT, 0.25F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, 0.25F, 0.0F, 0.0F);

			this.scale(this.cap, 0.2F, -0.1F, 0.2F);
			this.endKeyframe();

			this.startKeyframe(5);
			this.rotate(this.tentacleN, -0.15F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, -0.15F, 0.0F, 0.0F);
			this.rotate(this.tentacleT, -0.15F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, -0.15F, 0.0F, 0.0F);

			this.scale(this.cap, -0.1F, 0.15F, -0.1F);
			this.endKeyframe();

			this.startKeyframe(5);
			this.rotate(this.tentacleN, 0.15F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, 0.15F, 0.0F, 0.0F);
			this.rotate(this.tentacleT, 0.15F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, 0.15F, 0.0F, 0.0F);
			this.endKeyframe();

			this.resetKeyframe(5);
		} else if (jellyfish.isEndimationPlaying(CassiopeaJellyfishEntity.BOOST_ANIMATION)) {
			this.setEndimationToPlay(CassiopeaJellyfishEntity.BOOST_ANIMATION);

			this.startKeyframe(5);
			this.rotate(this.tentacleN, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleT, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, 0.3F, 0.0F, 0.0F);

			this.scale(this.cap, 0.2F, -0.1F, 0.2F);
			this.endKeyframe();

			this.startKeyframe(5);
			this.rotate(this.tentacleN, -0.2F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, -0.2F, 0.0F, 0.0F);
			this.rotate(this.tentacleT, -0.2F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, -0.2F, 0.0F, 0.0F);

			this.scale(this.cap, -0.1F, 0.15F, -0.1F);
			this.endKeyframe();

			this.startKeyframe(5);
			this.rotate(this.tentacleN, 0.2F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, 0.2F, 0.0F, 0.0F);
			this.rotate(this.tentacleT, 0.2F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, 0.2F, 0.0F, 0.0F);
			this.endKeyframe();

			this.resetKeyframe(5);
		}

		this.cap.setShouldScaleChildren(false);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(EndimatorModelRenderer EndimatorModelRenderer, float x, float y, float z) {
		EndimatorModelRenderer.xRot = x;
		EndimatorModelRenderer.yRot = y;
		EndimatorModelRenderer.zRot = z;
	}
}
