package com.teamabnormals.upgrade_aquatic.client.model.jellyfish;

import com.teamabnormals.blueprint.client.ClientInfo;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorEntityModel;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatorModelRenderer;
import com.teamabnormals.upgrade_aquatic.common.entity.animal.jellyfish.BoxJellyfishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

/**
 * BoxJelly - SnakeBlock
 * Created using Tabula 7.0.0
 */
public class BoxJellyfishModel<E extends BoxJellyfishEntity> extends EndimatorEntityModel<E> {
	public EndimatorModelRenderer body;
	public EndimatorModelRenderer tentacleW;
	public EndimatorModelRenderer tentacleS;
	public EndimatorModelRenderer tentacleE;
	public EndimatorModelRenderer tentacleN;
	public EndimatorModelRenderer tentacleNE;
	public EndimatorModelRenderer tentacleSE;
	public EndimatorModelRenderer tentacleNW;
	public EndimatorModelRenderer tentacleSW;

	public BoxJellyfishModel() {
		this.texWidth = 48;
		this.texHeight = 42;
		this.tentacleN = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleN.setPos(0.0F, 5.0F, -3.0F);
		this.tentacleN.addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1, 0.0F);
		this.setRotateAngle(tentacleN, 0.0F, 3.141592653589793F, 0.0F);
		this.tentacleNW = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleNW.setPos(3.0F, 5.0F, -3.0F);
		this.tentacleNW.addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1, 0.0F);
		this.setRotateAngle(tentacleNW, 0.0F, 2.356194490192345F, 0.0F);
		this.tentacleW = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleW.setPos(3.0F, 5.0F, 0.0F);
		this.tentacleW.addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1, 0.0F);
		this.setRotateAngle(tentacleW, 0.0F, 1.5707963267948966F, 0.0F);
		this.tentacleE = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleE.setPos(-3.0F, 5.0F, 0.0F);
		this.tentacleE.addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1, 0.0F);
		this.setRotateAngle(tentacleE, 0.0F, 1.5707963267948966F, 0.0F);
		this.tentacleS = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleS.setPos(0.0F, 5.0F, 3.0F);
		this.tentacleS.addBox(-0.5F, 0.0F, -0.5F, 1, 18, 1, 0.0F);
		this.tentacleNE = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleNE.setPos(-3.0F, 5.0F, -3.0F);
		this.tentacleNE.addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1, 0.0F);
		this.setRotateAngle(tentacleNE, 0.0F, -2.356194490192345F, 0.0F);
		this.body = new EndimatorModelRenderer(this, 0, 0);
		this.body.setPos(0.0F, 19.0F, 0.0F);
		this.body.addBox(-6.0F, -5.0F, -6.0F, 12, 10, 12, 0.0F);
		this.tentacleSE = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleSE.setPos(-3.0F, 5.0F, 3.0F);
		this.tentacleSE.addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1, 0.0F);
		this.setRotateAngle(tentacleSE, 0.0F, 5.497787143782138F, 0.0F);
		this.tentacleSW = new EndimatorModelRenderer(this, 0, 22);
		this.tentacleSW.setPos(3.0F, 5.0F, 3.0F);
		this.tentacleSW.addBox(-0.5F, 0.0F, -0.5F, 1, 16, 1, 0.0F);
		this.setRotateAngle(tentacleSW, 0.0F, 0.7853981633974483F, 0.0F);
		this.body.addChild(this.tentacleN);
		this.body.addChild(this.tentacleNW);
		this.body.addChild(this.tentacleW);
		this.body.addChild(this.tentacleE);
		this.body.addChild(this.tentacleS);
		this.body.addChild(this.tentacleNE);
		this.body.addChild(this.tentacleSE);
		this.body.addChild(this.tentacleSW);

		this.setDefaultBoxValues();
	}


	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		this.animateModel(this.entity);
		this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.revertBoxesToDefaultValues();

		float[] rotations = entity.getRotationController().getRotations(ClientInfo.getPartialTicks());
		this.body.yRot = (float) Math.toRadians(rotations[0]);
		this.body.xRot = (float) Math.toRadians(rotations[1]);
	}

	@Override
	public void animateModel(E jellyfish) {
		super.animateModel(jellyfish);

		if (jellyfish.isEndimationPlaying(BoxJellyfishEntity.SWIM_ANIMATION)) {
			this.setEndimationToPlay(BoxJellyfishEntity.SWIM_ANIMATION);

			this.startKeyframe(10);
			this.rotate(this.tentacleN, 0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleNW, 0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleNE, 0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, -0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleSE, 0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleS, 0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleSW, 0.52F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, 0.52F, 0.0F, 0.0F);

			this.scale(this.body, 0.5F, -0.15F, 0.5F);
			this.endKeyframe();

			this.resetKeyframe(10);
		} else if (jellyfish.isEndimationPlaying(BoxJellyfishEntity.BOOST_ANIMATION)) {
			this.setEndimationToPlay(BoxJellyfishEntity.BOOST_ANIMATION);

			this.startKeyframe(10);
			this.rotate(this.tentacleN, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleNW, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleNE, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleE, -0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleSE, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleS, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleSW, 0.3F, 0.0F, 0.0F);
			this.rotate(this.tentacleW, 0.3F, 0.0F, 0.0F);

			this.scale(this.body, 0.25F, -0.1F, 0.25F);
			this.endKeyframe();

			this.resetKeyframe(10);
		}

		this.body.setShouldScaleChildren(false);
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
