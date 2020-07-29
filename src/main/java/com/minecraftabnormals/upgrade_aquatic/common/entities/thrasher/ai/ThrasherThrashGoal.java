package com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ai;

import java.util.EnumSet;

import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.ThrasherEntity;
import com.minecraftabnormals.abnormals_core.core.library.endimator.entity.EndimatedEntity;
import com.minecraftabnormals.abnormals_core.core.utils.NetworkUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class ThrasherThrashGoal extends Goal {
	public ThrasherEntity thrasher;
	private float originalYaw;
	private float thrashedTicks;
	
	public ThrasherThrashGoal(ThrasherEntity thrasher) {
		this.thrasher = thrasher;
		this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		Entity passenger = !thrasher.getPassengers().isEmpty() ? this.thrasher.getPassengers().get(0) : null;
		if(passenger instanceof PlayerEntity) {
			if(((PlayerEntity) passenger).isCreative() || passenger.isSpectator()) {
				return false;
			}
		}
		return !this.thrasher.isStunned() && passenger != null && this.thrasher.isNoEndimationPlaying() && this.thrasher.getRNG().nextFloat() < 0.1F;
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		Entity passenger = !thrasher.getPassengers().isEmpty() ? this.thrasher.getPassengers().get(0) : null;
		if(passenger instanceof PlayerEntity) {
			if(((PlayerEntity) passenger).isCreative() || passenger.isSpectator()) {
				return false;
			}
		}
		return !this.thrasher.isStunned() && this.thrashedTicks <= 55 && passenger != null;
	}
	
	@Override
	public void startExecuting() {
		this.originalYaw = this.thrasher.rotationYaw;
		this.thrasher.setHitsTillStun(this.thrasher.getRNG().nextInt(2) + 2);
		NetworkUtil.setPlayingAnimationMessage(this.thrasher, ThrasherEntity.THRASH_ANIMATION);
	}
	
	@Override
	public void resetTask() {
		this.originalYaw = 0;
		this.thrashedTicks = 0;
		NetworkUtil.setPlayingAnimationMessage(this.thrasher, EndimatedEntity.BLANK_ANIMATION);
	}
	
	@Override
	public void tick() {
		this.thrashedTicks++;
		
		this.thrasher.getNavigator().clearPath();
		
		this.thrasher.prevRotationYaw = this.thrasher.rotationYaw;
		
		this.thrasher.renderYawOffset = (float) ((this.originalYaw) + 75 * MathHelper.cos(this.thrasher.ticksExisted * 0.5F) * 1F);
		this.thrasher.rotationYaw = (float) ((this.originalYaw) + 75 * MathHelper.cos(this.thrasher.ticksExisted * 0.5F) * 1F);
		
		Entity entity = this.thrasher.getPassengers().get(0);
		
		if(entity instanceof PlayerEntity) {
			this.disablePlayersShield((PlayerEntity) entity);
		}
		
		entity.setSneaking(false);
		
		if(this.thrashedTicks % 5 == 0 && this.thrashedTicks > 0) {
			this.thrasher.playSound(this.thrasher.getThrashingSound(), 1.0F, Math.max(0.75F, this.thrasher.getRNG().nextFloat()));
			entity.attackEntityFrom(DamageSource.causeMobDamage(this.thrasher), (float) this.thrasher.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
		}
	}
	
	private void disablePlayersShield(PlayerEntity player) {
		player.getCooldownTracker().setCooldown(Items.SHIELD, 30);
	}
}