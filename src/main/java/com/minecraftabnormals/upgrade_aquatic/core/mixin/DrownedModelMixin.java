package com.minecraftabnormals.upgrade_aquatic.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

@Mixin(DrownedModel.class)
public class DrownedModelMixin<T extends ZombieEntity> extends ZombieModel<T> {

	private DrownedModelMixin(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
	    super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
	}

	@Inject(at = @At("TAIL"), method = "setRotationAngles(Lnet/minecraft/entity/monster/ZombieEntity;FFFFF)V")
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo info) {
	    if (entityIn.isInWater() && entityIn.getRidingEntity() == null) {
             float limbSwingRemainder = limbSwing % 26.0F;
             entityIn.setPose(Pose.SWIMMING);
             HandSide handside = this.getMainHand(entityIn);
             float rightArmSwimAnimTicks = handside == HandSide.RIGHT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
             float leftArmSwimAnimTicks = handside == HandSide.LEFT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
             if (limbSwingRemainder < 14.0F) {
                this.bipedLeftArm.rotateAngleX = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleX, 0.0F);
                this.bipedRightArm.rotateAngleX = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleX, 0.0F);
                this.bipedLeftArm.rotateAngleY = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleY, (float)Math.PI);
                this.bipedRightArm.rotateAngleY = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleY, (float)Math.PI);
                this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(limbSwingRemainder) / this.getArmAngleSq(14.0F));
                this.bipedRightArm.rotateAngleZ = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(limbSwingRemainder) / this.getArmAngleSq(14.0F));
             } else if (limbSwingRemainder >= 14.0F && limbSwingRemainder < 22.0F) {
                float multiplier1 = (limbSwingRemainder - 14.0F) / 8.0F;
                this.bipedLeftArm.rotateAngleX = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleX, ((float)Math.PI / 2F) * multiplier1);
                this.bipedRightArm.rotateAngleX = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleX, ((float)Math.PI / 2F) * multiplier1);
                this.bipedLeftArm.rotateAngleY = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleY, (float)Math.PI);
                this.bipedRightArm.rotateAngleY = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleY, (float)Math.PI);
                this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleZ, 5.012389F - 1.8707964F * multiplier1);
                this.bipedRightArm.rotateAngleZ = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleZ, 1.2707963F + 1.8707964F * multiplier1);
            } else if (limbSwingRemainder >= 22.0F && limbSwingRemainder < 26.0F) {
                float multiplier2 = (limbSwingRemainder - 22.0F) / 4.0F;
                this.bipedLeftArm.rotateAngleX = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * multiplier2);
                this.bipedRightArm.rotateAngleX = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * multiplier2);
                this.bipedLeftArm.rotateAngleY = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleY, (float)Math.PI);
                this.bipedRightArm.rotateAngleY = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleY, (float)Math.PI);
                this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(leftArmSwimAnimTicks, this.bipedLeftArm.rotateAngleZ, (float)Math.PI);
                this.bipedRightArm.rotateAngleZ = MathHelper.lerp(rightArmSwimAnimTicks, this.bipedRightArm.rotateAngleZ, (float)Math.PI);
             }
             if (entityIn.isActualySwimming()) {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.bipedHead.rotateAngleX, (-(float)Math.PI / 4F));
             } else {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.bipedHead.rotateAngleX, headPitch * ((float)Math.PI / 180F));
             }
             this.bipedLeftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedLeftLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
             this.bipedRightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedRightLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
             } else {
                entityIn.setPose(Pose.STANDING);
	      }
	}
	
   private float getArmAngleSq(float limbSwing) {
       return -65.0F * limbSwing + limbSwing * limbSwing;
   }
}
