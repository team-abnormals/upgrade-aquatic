package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrownedModel.class)
public class DrownedModelMixin<T extends ZombieEntity> extends ZombieModel<T> {

	private DrownedModelMixin(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
		super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
	}

	@Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/entity/monster/ZombieEntity;FFFFF)V")
	private void setRotationAngles(T drowned, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo info) {
		if (drowned.isVisuallySwimming()) {
			if (drowned.isInWater() && drowned.getVehicle() == null && this.getHorizontalMotion(drowned.getDeltaMovement()) >= 0.025F && drowned.getCommandSenderWorld().getFluidState(drowned.blockPosition().below()).is(FluidTags.WATER)) {
				float limbSwingRemainder = limbSwing % 26.0F;
				HandSide handside = this.getAttackArm(drowned);
				float rightArmSwimAnimTicks = handside == HandSide.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
				float leftArmSwimAnimTicks = handside == HandSide.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
				if (limbSwingRemainder < 14.0F) {
					this.leftArm.xRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.xRot, 0.0F);
					this.rightArm.xRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.xRot, 0.0F);
					this.leftArm.yRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.yRot, (float) Math.PI);
					this.rightArm.yRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.yRot, (float) Math.PI);
					this.leftArm.zRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.zRot, (float) Math.PI + 1.8707964F * this.getArmAngleSq(limbSwingRemainder) / this.getArmAngleSq(14.0F));
					this.rightArm.zRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.zRot, (float) Math.PI - 1.8707964F * this.getArmAngleSq(limbSwingRemainder) / this.getArmAngleSq(14.0F));
				} else if (limbSwingRemainder >= 14.0F && limbSwingRemainder < 22.0F) {
					float multiplier1 = (limbSwingRemainder - 14.0F) / 8.0F;
					this.leftArm.xRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.xRot, ((float) Math.PI / 2F) * multiplier1);
					this.rightArm.xRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.xRot, ((float) Math.PI / 2F) * multiplier1);
					this.leftArm.yRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.yRot, (float) Math.PI);
					this.rightArm.yRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.yRot, (float) Math.PI);
					this.leftArm.zRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.zRot, 5.012389F - 1.8707964F * multiplier1);
					this.rightArm.zRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.zRot, 1.2707963F + 1.8707964F * multiplier1);
				} else if (limbSwingRemainder >= 22.0F && limbSwingRemainder < 26.0F) {
					float multiplier2 = (limbSwingRemainder - 22.0F) / 4.0F;
					this.leftArm.xRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.xRot, ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * multiplier2);
					this.rightArm.xRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.xRot, ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * multiplier2);
					this.leftArm.yRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.yRot, (float) Math.PI);
					this.rightArm.yRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.yRot, (float) Math.PI);
					this.leftArm.zRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.zRot, (float) Math.PI);
					this.rightArm.zRot = MathHelper.lerp(rightArmSwimAnimTicks, this.rightArm.zRot, (float) Math.PI);
				}
				if (drowned.isVisuallySwimming()) {
					this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float) Math.PI / 4F));
				} else {
					this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, headPitch * ((float) Math.PI / 180F));
				}
				this.leftLeg.xRot = MathHelper.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float) Math.PI));
				this.rightLeg.xRot = MathHelper.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
			}
		}
	}

	private float getArmAngleSq(float limbSwing) {
		return -65.0F * limbSwing + limbSwing * limbSwing;
	}

	private float getHorizontalMotion(Vector3d motion) {
		double x = motion.x();
		double z = motion.z();
		return MathHelper.sqrt(x * x + z * z);
	}
}