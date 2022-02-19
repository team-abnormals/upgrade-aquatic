package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import net.minecraft.client.model.DrownedModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrownedModel.class)
public class DrownedModelMixin<T extends Zombie> extends ZombieModel<T> {

	public DrownedModelMixin(ModelPart p_171090_) {
		super(p_171090_);
	}

	@Inject(at = @At("TAIL"), method = "setupAnim(Lnet/minecraft/world/entity/monster/Zombie;FFFFF)V")
	private void setRotationAngles(T drowned, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo info) {
		if (drowned.isVisuallySwimming()) {
			if (drowned.isInWater() && drowned.getVehicle() == null && this.getHorizontalMotion(drowned.getDeltaMovement()) >= 0.025F && drowned.getCommandSenderWorld().getFluidState(drowned.blockPosition().below()).is(FluidTags.WATER)) {
				float limbSwingRemainder = limbSwing % 26.0F;
				HumanoidArm handside = this.getAttackArm(drowned);
				float rightArmSwimAnimTicks = handside == HumanoidArm.RIGHT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
				float leftArmSwimAnimTicks = handside == HumanoidArm.LEFT && this.attackTime > 0.0F ? 0.0F : this.swimAmount;
				if (limbSwingRemainder < 14.0F) {
					this.leftArm.xRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.xRot, 0.0F);
					this.rightArm.xRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.xRot, 0.0F);
					this.leftArm.yRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.yRot, (float) Math.PI);
					this.rightArm.yRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.yRot, (float) Math.PI);
					this.leftArm.zRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.zRot, (float) Math.PI + 1.8707964F * this.getArmAngleSq(limbSwingRemainder) / this.getArmAngleSq(14.0F));
					this.rightArm.zRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.zRot, (float) Math.PI - 1.8707964F * this.getArmAngleSq(limbSwingRemainder) / this.getArmAngleSq(14.0F));
				} else if (limbSwingRemainder >= 14.0F && limbSwingRemainder < 22.0F) {
					float multiplier1 = (limbSwingRemainder - 14.0F) / 8.0F;
					this.leftArm.xRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.xRot, ((float) Math.PI / 2F) * multiplier1);
					this.rightArm.xRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.xRot, ((float) Math.PI / 2F) * multiplier1);
					this.leftArm.yRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.yRot, (float) Math.PI);
					this.rightArm.yRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.yRot, (float) Math.PI);
					this.leftArm.zRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.zRot, 5.012389F - 1.8707964F * multiplier1);
					this.rightArm.zRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.zRot, 1.2707963F + 1.8707964F * multiplier1);
				} else if (limbSwingRemainder >= 22.0F && limbSwingRemainder < 26.0F) {
					float multiplier2 = (limbSwingRemainder - 22.0F) / 4.0F;
					this.leftArm.xRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.xRot, ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * multiplier2);
					this.rightArm.xRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.xRot, ((float) Math.PI / 2F) - ((float) Math.PI / 2F) * multiplier2);
					this.leftArm.yRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.yRot, (float) Math.PI);
					this.rightArm.yRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.yRot, (float) Math.PI);
					this.leftArm.zRot = this.rotlerpRad(leftArmSwimAnimTicks, this.leftArm.zRot, (float) Math.PI);
					this.rightArm.zRot = Mth.lerp(rightArmSwimAnimTicks, this.rightArm.zRot, (float) Math.PI);
				}
				if (drowned.isVisuallySwimming()) {
					this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, (-(float) Math.PI / 4F));
				} else {
					this.head.xRot = this.rotlerpRad(this.swimAmount, this.head.xRot, headPitch * ((float) Math.PI / 180F));
				}
				this.leftLeg.xRot = Mth.lerp(this.swimAmount, this.leftLeg.xRot, 0.3F * Mth.cos(limbSwing * 0.33333334F + (float) Math.PI));
				this.rightLeg.xRot = Mth.lerp(this.swimAmount, this.rightLeg.xRot, 0.3F * Mth.cos(limbSwing * 0.33333334F));
			}
		}
	}

	private float getArmAngleSq(float limbSwing) {
		return -65.0F * limbSwing + limbSwing * limbSwing;
	}

	private float getHorizontalMotion(Vec3 motion) {
		double x = motion.x();
		double z = motion.z();
		return Mth.sqrt((float) (x * x + z * z));
	}
}